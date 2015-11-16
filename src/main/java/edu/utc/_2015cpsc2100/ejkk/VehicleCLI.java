package edu.utc._2015cpsc2100.ejkk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;

/**
 * Session Bean implementation class VehicleCLI
 */
@Stateless
@Asynchronous
public class VehicleCLI implements VehicleViewRemote, VehicleViewLocal
{
	Scanner s = new Scanner(System.in);

    /**
     * Default constructor. 
     */
    public VehicleCLI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * Allows the Employee to search through the Vehicles in the VehicleDatabase.
	 */
	public void searchVehicles()
	{
		String make = "";
		String model = "";
		String year = "";
		String category = "";
		String rate = "";
		String vin = "";
		
		String answer1 = "";
		
		ArrayList<SearchParameter> spList = new ArrayList<SearchParameter>();
		
		while(true)
		{
			System.out.println("Select a search field to change it.");
			System.out.println("(1) Make: " + make);
			System.out.println("(2) Model: " + model);
			System.out.println("(3) Year: " + year);
			System.out.println("(4) Category: " + category);
			System.out.println("(5) Rate Per Day: " + rate);
			System.out.println("(6) VIN: " + vin);
			System.out.println("(7) Search");
			System.out.println("(8) Go back");
			
			answer1 = s.next();
			
			int vdbSize = VehicleDatabase.Session.getAll().size();
			if (answer1.equals("1")) //MAKE SELECTION
			{
				ArrayList<String> availableMakes = new ArrayList<String>();
				for (int i = 0; i < vdbSize; i++)
				{
					boolean duplicate = false;
					for (int j = 0; j < availableMakes.size(); j++)
					{
						if (VehicleDatabase.Session.getAll().get(i).getMake() == availableMakes.get(j))
						{
							duplicate = true;
						}
					}
					if (!duplicate) { availableMakes.add(VehicleDatabase.Session.getAll().get(i).getMake()); }
				}
				System.out.println("Enter a number to select a make:");
				for (int i = 0; i < availableMakes.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + availableMakes.get(i));
				}
				int selection = s.nextInt();
				while (true)
				{
					if (selection > availableMakes.size() || selection <= 0) { System.out.println("Invalid response."); }
					else
					{
						make = availableMakes.get(selection-1);
						break;
					}
				}
			}
			else if (answer1.equals("2")) //MODEL SELECTION
			{
				ArrayList<String> availableModels = new ArrayList<String>();
				for (int i = 0; i < vdbSize; i++)
				{
					boolean duplicate = false;
					for (int j = 0; j < availableModels.size(); j++)
					{
						if (VehicleDatabase.Session.getAll().get(i).getModel() == availableModels.get(j))
						{
							duplicate = true;
						}
					}
					if (!duplicate) { availableModels.add(VehicleDatabase.Session.getAll().get(i).getModel()); }
				}
				System.out.println("Enter a number to select a model:");
				for (int i = 0; i < availableModels.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + availableModels.get(i));
				}
				int selection = s.nextInt();
				while (true)
				{
					if (selection > availableModels.size() || selection <= 0) { System.out.println("Invalid response."); }
					else
					{
						model = availableModels.get(selection);
						break;
					}
				}
			}
			else if (answer1.equals("3")) //YEAR SELECTION
			{
				System.out.println("Enter year:");
				year = s.next();
			}
			else if (answer1.equals("4")) //CATEGORY SELECTION
			{
				ArrayList<String> availableCategories = new ArrayList<String>();
				for (int i = 0; i < vdbSize; i++)
				{
					boolean duplicate = false;
					for (int j = 0; j < availableCategories.size(); j++)
					{
						if (VehicleDatabase.Session.getAll().get(i).getCategory() == availableCategories.get(j))
						{
							duplicate = true;
						}
					}
					if (!duplicate) { availableCategories.add(VehicleDatabase.Session.getAll().get(i).getCategory()); }
				}
				System.out.println("Enter a number to select a category:");
				for (int i = 0; i < availableCategories.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + availableCategories.get(i));
				}
				int selection = s.nextInt();
				while (true)
				{
					if (selection > availableCategories.size() || selection <= 0) { System.out.println("Invalid response."); }
					else
					{
						category = availableCategories.get(selection-1);
						break;
					}
				}
			}
			else if (answer1.equals("5")) //RATE SELECTION
			{
				System.out.println("Enter price per day (Example: 32.5)");
				rate = s.next();
			}
			if (answer1.equals("6")) //VIN SELECTION
			{
				ArrayList<String> availableVINs = new ArrayList<String>();
				for (int i = 0; i < vdbSize; i++)
				{
					availableVINs.add(VehicleDatabase.Session.getAll().get(i).getVIN());
				}
				System.out.println("Enter a number to select a VIN:");
				for (int i = 0; i < availableVINs.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + availableVINs.get(i));
				}
				int selection = s.nextInt();
				while (true)
				{
					if (selection > availableVINs.size() || selection <= 0) { System.out.println("Invalid response."); }
					else
					{
						vin = availableVINs.get(selection-1);
						break;
					}
				}
			}
			else if (answer1.equals("7") || answer1.equals("8")) { break; }
			else if (! (answer1.equals("1") || answer1.equals("2") || answer1.equals("4") || answer1.equals("5") || answer1.equals("6") || answer1.equals("7") || answer1.equals("8")))
			{
				System.out.println("Invalid response.");
			}
		}
		
		if (make != "") { spList.add(new SearchParameter("make", make)); }
		if (model != "") { spList.add(new SearchParameter("modle", model)); }
		if (year != "") { spList.add(new SearchParameter("year", year)); }
		if (category != "") { spList.add(new SearchParameter("category", category)); }
		if (rate != "") { spList.add(new SearchParameter("rate", rate)); }
		if (vin != "") { spList.add(new SearchParameter("vin", vin)); }
	
		if (! answer1.equals("8"))
		{
			ArrayList<Vehicle> results = new ArrayList<Vehicle>();
			results = VehicleDatabase.Session.searchMultiple(spList);
			
			if (results.size() == 0) { System.out.println("No vehicles match your search."); }
			else
			{
				System.out.println(results.size() + " Vehicle(s) found.");
				for(int i = 0; i < results.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + results.get(i).toShortString());
				}
				System.out.println("Select a number to view a vehicle or");
			}
			System.out.println("(0) To go back");
			
			int answer2;
			while(true)
			{
				answer2 = s.nextInt();
				if (answer2 > results.size() || answer2 < 0) { System.out.println("Invalid response."); }
				else { break; }
			}
			
			
			if (answer2 != 0)
			{
				Vehicle v = results.get(Integer.valueOf(answer2) - 1);
				System.out.println("(0) " + v.toString());
				while(true)
				{
					System.out.println("(1) To update this vehicle");
					System.out.println("(2) To delete this vehicle from the database");
					
					String answer3 = s.next();
					
					if (answer3.equals("2"))
					{
						String selectedvin = v.getVIN();
						VehicleDatabase.Session.deleteVehicle(selectedvin);
						System.out.println(v.toShortString() + " has been deleted from the database.");
						break;
					}
					else if (answer3.equals("1"))
					{
						updateVehicle(v);
						break;
					}
					else { System.out.println("Invalid response."); }
				}
			}
		}
	}

	/**
	 * Allows the Employee to update the given Vehicle.
	 * @param v	The vehicle to be updated.
	 */
	public void updateVehicle(Vehicle v)
	{
		String answer;
		String oldVIN = v.getVIN();
		boolean change = false;
		
		while (true)
		{
			System.out.println("Select a field to change it.");
			System.out.println("(1) Make: " + v.getMake());
			System.out.println("(2) Model: " + v.getModel());
			System.out.println("(3) Year: " + v.getYear());
			System.out.println("(4) Category: " + v.getCategory());
			System.out.println("(5) Rate: " + v.getRate());
			System.out.println("(6) Description: " + v.getDescription());
			System.out.println("(7) VIN: " + v.getVIN());
			System.out.println("(0) To submit your changes or \n(D) To discard your changes");
			
			answer = s.next();
			
			if (answer.equals("1"))
			{
				System.out.println("Enter a new make: ");
				String make = s.next();
				v.setMake(make);
				change = true;
			}
			else if (answer.equals("2"))
			{
				System.out.println("Enter a new model: ");
				String model = s.next();
				v.setModel(model);
				change = true;
			}
			else if (answer.equals("3"))
			{
				System.out.println("Enter a new year: ");
				String year = s.next();
				v.setYear(year);
				change = true;
			}
			else if (answer.equals("4"))
			{
				System.out.println("Enter a new category: ");
				String category = s.next();
				v.setCategory(category);
				change = true;
			}
			else if (answer.equals("5"))
			{
				System.out.println("Enter a new rate: (Example: 32)");
				String rate = s.next();
				v.setRate(rate);
				change = true;
			}
			else if (answer.equals("6"))
			{
				System.out.println("Enter a new description: ");
				String des = s.next();
				String description = des + s.nextLine();
				v.setDescription(description);
				change = true;
			}
			else if (answer.equals("7"))
			{
				System.out.println("Enter a new VIN: ");
				String vin = s.next();
				v.setVIN(vin);
				change = true;
			}
			else if (answer.equalsIgnoreCase("D"))
			{
				change = false;
				break;
			}
			else if (answer.equals("0"))
			{
				break;
			}
			else { System.out.println("Invalid response."); }
		}
		
		if (change)
		{
			VehicleDatabase.Session.deleteVehicle(oldVIN);
			VehicleDatabase.Session.addVehicle(v);
			
			SearchParameter sp = new SearchParameter ("vin", oldVIN);
			ArrayList<Reservation> result = ReservationDatabase.Session.search(sp);
			if (result.size() != 0)
			{
				for (int i = 0; i < result.size(); i++)
				{
					String resID = result.get(i).getResID();
					ReservationDatabase.Session.vehicleChange(resID, v);
				}
			}
			
			System.out.println("Your changes have been saved.");
		}
		else
		{
			System.out.println("No changes were saved.");
		}
	}

	/**
	 * Allows the Employee to register a new Vehicle in the VehicleDatabase.
	 */
	public void registerVehicle()
	{
		System.out.println("Enter the make: ");
		String make = s.next();
		System.out.println("Enter the model: ");
		String model = s.next();
		System.out.println("Enter the year: ");
		String year = s.next();
		System.out.println("Enter the category: ");
		String category = s.next();
		System.out.println("Enter the rate: ");
		Double rate = s.nextDouble();
		System.out.println("Enter the description: ");
		String des = s.next();
		String description = des + s.nextLine();
		
		System.out.println("Enter the VIN: ");
		String vin = s.next();
		
		Vehicle v = new Vehicle(make, model, Integer.valueOf(year), category, rate, description, vin);
		
		System.out.println("Your new vehicle has been created.");
		System.out.println("(0) " + v.toString());
		
		String answer;
		
		while(true)
		{
			System.out.println("(1) To save this vehicle to the database");
			System.out.println("(2) To make changes");
			System.out.println("(3) To go back");
			
			answer = s.next();
			
			if (answer.equals("1"))
			{
				VehicleDatabase.Session.addVehicle(v);
				System.out.println("Your vehicle has been saved.");
				break;
			}
			else if (answer.equals("2"))
			{
				updateVehicle(v);
				break;
			}
			else { System.out.println("Invalid response."); }
		}
	}

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
	public void reserveVehicle(Vehicle vehicle, String pickUpDate, String dropOffDate, CreditCard card, User user) throws MandrillApiError, IOException
	{
		Date pickUp = Date.fromShortString(pickUpDate);
		Date dropOff = Date.fromShortString(dropOffDate);
		Reservation r = new Reservation(pickUp, dropOff, vehicle, user, card);
		ReservationDatabase.Session.addReservation(r);
		user.getRentalRecord().add(r);
		card.bill(Double.valueOf(getQuote(vehicle, pickUpDate, dropOffDate).substring(1)));
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
		recipient.setEmail(user.getEmailAddress());
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
