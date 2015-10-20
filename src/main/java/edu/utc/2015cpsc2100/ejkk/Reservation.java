import java.util.ArrayList;

public class Reservation
{
	private Date pickUpDate;
	private Date dropOffDate;
	private Vehicle vehicle;
	private Customer customer;
	private int ccNumber;
	private int reservationDates; //what is this again???
	private int price;
	private static int reservationNumber;
	public int resNumber;
	
	public Date getPickUpDate() { return pickUpDate; }
	public Date getdropOffDate() { return dropOffDate; }
	public Vehicle getVehicle() { return vehicle; }
	public Customer getCustomer() { return customer; }
	public int getCreditCardInfo() { return ccNumber; }
	public int getReservationDates() { return reservationDates; }
	public int getPrice() { return price; }
	
	/**
	 * Creates a reservation object which stores temporal, vehicle, and customer information.
	 * @param pickUp	the date on which the customer is rented the car
	 * @param dropOff	the date on which the customer must return the car
	 * @param vehicle	the vehicle which the customer is renting
	 * @param customer	the customer who reserved the vehicle
	 * @param ccNumber	the credit card number of the customer
	 */
	public Reservation(Date pickUp, Date dropOff, Vehicle vehicle, Customer customer, int ccNumber)
	{
		reservationNumber++;
		pickUpDate = pickUp;
		dropOffDate = dropOff;
		this.vehicle = vehicle;
		this.customer = customer;
		this.ccNumber = ccNumber;
		reservationDates = pickUpDate.daysUntil(dropOffDate);
		price = vehicle.pricePerDay * reservationDates;
		resNumber = reservationNumber;
	}
	
	/**
	 * Returns a String that represents the reservation.
	 * @return s	the String that represents the reservation
	 */
	public String toString()
	{
		String s = "";
		s = s + "Dates: " +  pickUpDate.toShortString() + " - " + dropOffDate.toShortString() + "\n" +
				"Vehicle: " + vehicle.toString() + "\n" +
				"Customer: " + customer.getName() + "\n" +
				"Price: " + price + "\n" +
				"Reservation Number: " + resNumber;
		return s;
	}
}
