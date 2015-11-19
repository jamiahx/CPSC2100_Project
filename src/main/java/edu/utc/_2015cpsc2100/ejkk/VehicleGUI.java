package edu.utc._2015cpsc2100.ejkk;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;


/**
 * Session Bean implementation class VehicleGUI
 */
@Stateless
@Asynchronous
public class VehicleGUI implements VehicleViewRemote, VehicleViewLocal
{
	/**
	 * Allows the User to select a Vehicle from a given list
	 * @param list	the ArrayList from which the Vehicle is selected.
	 * @param choiceNumber	the reference number of the User's chosen Vehicle.
	 * @return
	 */
	public Vehicle selectVehicle(ArrayList<Vehicle> list, String choiceNumber)
	{
		Vehicle v = list.get(Integer.valueOf(choiceNumber)-1);
		return v;
	}
	
	/**
	 * Gets a quote for the given vehicle over the given period.
	 * @param vehicle	the Vehicle for which the quote is retrieved.
	 * @param pickUpDate	the start date of the presumed rental period.
	 * @param dropOffDate	the end date of the presumed rental period.
	 * @return
	 */
	public String getQuote(Vehicle vehicle, String pickUpDate, String dropOffDate)
	{
		double quote;
		Date pickUp = Date.fromShortString(pickUpDate);
		Date dropOff = Date.fromShortString(dropOffDate);
		double price = vehicle.getRate();
		int days = pickUp.daysUntil(dropOff);
		Quote myQuote = new Quote(price, days);
		quote = myQuote.getTotal();
		return "$" + quote;
	}
	
	/**
	 * Allows the User to reserve a Vehicle over the given rental period.
	 * @param vehicle	the Vehicle to be rented.
	 * @param pickUpDate	the start date of the rental period.
	 * @param dropOffDate	the end date of the rental period.
	 * @param card	the CreditCard to be billed for the rental.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public void reserveVehicle(Vehicle vehicle, String pickUpDate, String dropOffDate, CreditCard card) throws MandrillApiError, IOException
	{
		Date pickUp = Date.fromShortString(pickUpDate);
		Date dropOff = Date.fromShortString(dropOffDate);
		Reservation r = new Reservation(pickUp, dropOff, vehicle, this, card);
		ReservationDatabase.Session.addReservation(r);
		rentalRecord.add(r);
		card.bill(Double.valueOf(getQuote(vehicle, pickUpDate, dropOffDate).substring(1)));
		
		JOptionPane.showMessageDialog( null,"Your vehicle has been reserved. You will be sent an email containing your confirmation number",
				"Thank, you", JOptionPane.INFORMATION_MESSAGE);
	
		System.out.println("Your vehicle has been reserved. You will be sent an email containing your confirmation number.");
		
		MandrillApi mandrillApi = new MandrillApi("eoYBAgIUJLYb1A5LAQb3RA");

		// create your message
		MandrillMessage message = new MandrillMessage();
		message.setSubject("Your rental car reservation!");
		message.setHtml("<h1>Thank you for shopping with ORS, We have your reservation!</h1><br />Thank you for choosing ORS ! If you have any issues or need to cancel your reservation"
		+ " you can call our 24/7 customer support line at 423-555-0543.");
		message.setAutoText(true);
		message.setFromEmail("ejkkors@gmail.com");
		message.setFromName("Online Reservation System");
		// add recipients
		ArrayList<Recipient> recipients = new ArrayList<Recipient>();
		Recipient recipient = new Recipient();
		recipient.setEmail(getEmailAddress());
		recipient.setName("Customer");
		recipients.add(recipient);
		recipient = new Recipient();
		recipient.setEmail("dqv679@mocs.utc.edu");
		recipients.add(recipient);
		message.setTo(recipients);
		message.setPreserveRecipients(true);
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("test");
		tags.add("helloworld");
		message.setTags(tags);
		// ... add more message details if you want to!
		// then ... send
		MandrillMessageStatus[] messageStatusReports = mandrillApi
		        .messages().send(message, false);
			
		while (true)
		{
			System.out.println("(0) To print this reservation");
			System.out.println("(1) To go back");
			String answer = s.next();
			if (answer.equals("0")) { r.print(); }
			else if (answer.equals("1")) { break; }
			else { System.out.println("Invalid response."); }
		}
	}
}