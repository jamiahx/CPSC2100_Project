package edu.utc._2015cpsc2100.ejkk;

import java.util.ArrayList;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class SearchCLI
 */
@Stateless
@Asynchronous
public class SearchCLI implements SearchViewRemote, SearchViewLocal {

    /**
     * Default constructor. 
     */
    public SearchCLI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * Allows the Employee to search through the Users in the UserDatabase.
	 * @return selectedUser	The user that the Employee selects during the search process.
	 */
	public User searchUsers()
	{
		User selectedUser = null;
		
		String answer = "";
		System.out.println("(1) To search all users");
		System.out.println("(2) To search active users");
		System.out.println("(3) To search inactive users");
		System.out.println("(4) To go back");
		
		answer = s.next();
		
		UserDatabase temp = new UserDatabase();
		
		while (true)
		{
			String answer3 = "";
			
			while (true)
			{
				if (answer.equals("1"))
				{
					ArrayList<User> allUsers = UserDatabase.Session.getAll();
					temp.load(allUsers);
					break;
				}
				else if (answer.equals("2"))
				{
					SearchParameter sp = new SearchParameter("reservations", "0");
					ArrayList<User> activeUsers = UserDatabase.Session.oppositeSearch(sp);
					temp.load(activeUsers);
					break;
				}
				else if (answer.equals("3"))
				{
					SearchParameter sp = new SearchParameter("reservations", "0");
					ArrayList<User> inactiveUsers = UserDatabase.Session.search(sp);
					temp.load(inactiveUsers);
					break;
				}
				else if (answer.equals("4"))
				{
					break;
				}
				else { System.out.println("Invalid response."); }
			}
			if (answer.equals("4")) { break; }
			
			String firstName = "";
			String lastName = "";
			String username = "";
			String role = "";
			
			ArrayList<SearchParameter> spList = new ArrayList<SearchParameter>();
			String answer1 = "";
			
			while (true)
			{
				System.out.println("Select a search field to change it.");
				System.out.println("(1) First name: " + firstName);
				System.out.println("(2) Last name: " + lastName);
				System.out.println("(3) Username: " + username);
				System.out.println("(4) Role: " + role);
				System.out.println("(5) Search \n(6) Go back");
				
				answer1 = s.next();
				
				if (answer1.equals("1"))
				{
					System.out.println("Enter a first name: ");
					firstName = s.next();
				}
				else if (answer1.equals("2"))
				{
					System.out.println("Enter a last name: ");
					lastName = s.next();
				}
				else if (answer1.equals("3"))
				{
					System.out.println("Enter a username: ");
					username = s.next();
				}
				else if (answer1.equals("4"))
				{
					while (true)
					{
						System.out.println("(1) For customer \n(2) For employee \n(3) For manager");
						String option = s.next();
						if (option.equals("1"))
						{
							role = "Customer";
							break;
						}
						else if (option.equals("2"))
						{
							role = "Employee";
							break;
						}
						else if (option.equals("3"))
						{
							role = "Manager";
							break;
						}
						else { System.out.println("Invalid response."); }
					}
				}
				else if (answer1.equals("5") || answer1.equals("6")) { break; }
				else { System.out.println("Invalid response."); }
			}
			if (answer1.equals("6")) { break; }
			
			while (! answer1.equals("6"))
			{
				if (firstName != "") { spList.add(new SearchParameter("firstName", firstName)); }
				if (lastName != "") { spList.add(new SearchParameter("lastName", lastName)); }
				if (username != "") { spList.add(new SearchParameter("username", username)); }
				if (role != "") { spList.add(new SearchParameter("role", role)); }
				
				ArrayList<User> results = temp.searchMultiple(spList);
				
				if (results.size() == 0) { System.out.println("No users match your search."); }
				else
				{
					System.out.println(results.size() + " User(s) found.");
					for(int i = 0; i < results.size(); i++)
					{
						System.out.println("(" + (i+1) + ") " + results.get(i).toString());
					}
					System.out.println("Enter a number to select a user or");
				}
				System.out.println("(0) To go back");
				int answer2;
				
				while (true)
				{
					answer2 = s.nextInt();
					
					if (answer2 == 0) { break; }
					else if (answer2 > results.size() || answer2 < 0)
					{
						System.out.println("Invalid response.");
						if (results.size() != 0) { System.out.println("Enter a number to select a user or"); }
						System.out.println("(0) To go back");
					}
					else { break; }
				}
				if (answer2 == 0) { break; } 
				
				selectedUser = results.get(answer2 - 1);
				System.out.println(selectedUser.getFirstName() + " " + selectedUser.getLastName() + " selected.");
				
				boolean viewed = false;
				
				while (true)
				{
					while (true)
					{
						if (! viewed) { System.out.println("(1) To view this user's rental record"); }
						System.out.println("(0) To select a different user");
						if (this.getRole().equals("Manager")) { System.out.println("(U) To update/delete this account"); }
						answer3 = s.next();
						
						if (answer3.equals("1") && !viewed)
						{
							System.out.println(selectedUser.getFirstName() + " " + selectedUser.getLastName() + "'s Rental Record: ");
							ArrayList<Reservation> rentalRecord = selectedUser.getRentalRecord();
							if (rentalRecord.size() == 0) { System.out.println("No reservations exist at this time."); }
							for (int i = 0; i < rentalRecord.size(); i++)
							{
								System.out.println("(" + (i+1) + ") " + rentalRecord.get(i).toString());
							}
							viewed = true;
							break;
						}
						else if (answer3.equals("0") || (this.getRole().equals("Manager") && answer3.equalsIgnoreCase("U"))) { break; }
						else { System.out.println("Invalid response."); }
					}
					if (answer3.equals("0") || answer3.equalsIgnoreCase("U")) { break; }
				}
				if (answer3.equalsIgnoreCase("U")) { break; }
			}
			if (answer3.equalsIgnoreCase("U")) { break; }
		}
		return selectedUser;
	}

	/**
	 * Allows the Employee to search through the Reservations in the ReservationDatabase.
	 * @return selectedReservation	The Reservation that the Employee selects during the search process.
	 */
	public Reservation searchReservations()
	{
		Reservation selectedReservation = null;
		
		String pickUpDate = "";
		String dropOffDate = "";
		String username = "";
		String vin = "";
		String resID = "";
		
		String answer = "";
		
		boolean pud = false;
		boolean dod = false;
		boolean datesGood = false;
		
		while (true)
		{
			System.out.println("Select a search field to change it.");
			System.out.println("(1) Pick-up date: " + pickUpDate);
			System.out.println("(2) Drop-off date: " + dropOffDate);
			System.out.println("(3) Customer username: " + username);
			System.out.println("(4) VIN: " + vin);
			System.out.println("(5) Reservation ID: " + resID);
			System.out.println("(6) Search \n(7) Go back");
			answer = s.next();
			
			if (answer.equals("1"))
			{
				System.out.println("Enter a pick-up date: (Example: 6/26/1996)");
				pickUpDate = s.next();
				pud = true;
			}
			else if (answer.equals("2"))
			{
				System.out.println("Enter a drop-off date: (Example: 6/26/1996)");
				dropOffDate = s.next();
				dod = true;
			}
			else if (answer.equals("3"))
			{
				System.out.println("Enter a username: ");
				username = s.next();
			}
			else if (answer.equals("4"))
			{
				System.out.println("Enter a VIN: ");
				vin = s.next();
			}
			else if (answer.equals("5"))
			{
				System.out.println("Enter a reservation ID: ");
				resID = s.next();
			}
			else if (answer.equals("6") && datesGood)
			{
				break;
			}
			else if (answer.equals("7"))
			{
				break;
			}
			else { System.out.println("Invalid response."); }
			
			if (pud && dod)
			{
				boolean b1 = Date.fromShortString(pickUpDate).isBefore(Date.fromShortString(dropOffDate));
				boolean b2 = Date.fromShortString(pickUpDate).checkTimeTravel();
				
				if (b1 && !b2) { datesGood = true; }
				else { System.out.println("Dates invalid."); }
			}
		}
		
		ArrayList<Reservation> results = new ArrayList<Reservation>();
		
		if (! answer.equals("7"))
		{
			ArrayList<SearchParameter> spList = new ArrayList<SearchParameter>();
			
			if (! pickUpDate.equals("")) { spList.add(new SearchParameter("pickUpDate", pickUpDate)); }
			if (! dropOffDate.equals("")) { spList.add(new SearchParameter("dropOffDate", dropOffDate)); }
			if (! username.equals("")) { spList.add(new SearchParameter("customerUsername", username)); }
			if (! vin.equals("")) { spList.add(new SearchParameter("vin", vin)); }
			if (! resID.equals("")) { spList.add(new SearchParameter("resID", resID)); }
			
			results = ReservationDatabase.Session.searchMultiple(spList);
			String answer2;
			
			if (results.size() == 0) { System.out.println("No reservations match your search. \n(0) To go back"); }
			else
			{
				System.out.println(results.size() + " Reservation(s) found.");
				for (int i = 0; i < results.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + results.get(i).toString());
				}
				if (this.getRole().equalsIgnoreCase("Manager"))
				{
					System.out.println("Enter a number to select a reservation or");
				}
				System.out.println("(0) To go back");
			}
			
			while (true)
			{
				answer2 = s.next();
				
				if (Integer.valueOf(answer2) > results.size() || Integer.valueOf(answer2) < 0)
				{
					selectedReservation = results.get(Integer.valueOf(answer2) - 1);
					break;
				}
				else if (answer2.equals("0")) { break; }
				else { System.out.println("Invalid response."); }
			}
		}
		return selectedReservation;
	}

	/**
	 * Updates an existing Reservation.
	 * @param r		The Reservation which will be updated.
	 */
	public void updateReservation(Reservation r)
	{
		String oldResID = r.getResID();
		
		String answer = "";
		
		while(true)
		{
			String pickUpDate = r.getPickUpDate().toShortString();
			String dropOffDate = r.getDropOffDate().toShortString();
			String vehicle = r.getVehicle().toShortString();
			String customer = r.getCustomer().getFirstName() + " " + r.getCustomer().getLastName();
			String card = r.getCreditCardInfo().getProvider() + " " + r.getCreditCardInfo().getNumber();
			String price = r.getPrice() + "";
			
			System.out.println("Enter a number to change a field.");
			System.out.println("(1) Pick-up date: " + pickUpDate);
			System.out.println("(2) Drop-off date: " + dropOffDate);
			System.out.println("(3) Vehicle: " + vehicle);
			System.out.println("(4) Customer: " + customer);
			System.out.println("(5) Card Information: " + card);
			System.out.println("(6) Total price: $" + price);
			System.out.println("(7) Save changes \n(8) Discard changes");
			
			answer = s.next();
			
			boolean pud = false;
			boolean dod = false;
			boolean datesGood = false;
			
			if (answer.equals("1"))
			{
				System.out.println("Enter a pick-up date (Example: 7/17/2015) ");
				pickUpDate = s.next();
				pud = true;
			}
			else if (answer.equals("2"))
			{
				System.out.println("Enter a drop-off date (Example: 7/17/2015) ");
				dropOffDate = s.next();
				dod = true;
			}
			else if (answer.equals("3"))
			{
				while(true)
				{
					System.out.println("Available vehicles: ");
					ArrayList<Vehicle> results = ReservationDatabase.Session.vehicleAvailabilitySearch(pickUpDate, dropOffDate);
					for (int i = 0; i < results.size(); i++)
					{
						System.out.println("(" + (i+1) + ") " + results.get(i).toShortString());
					}
					String answer1 = "";
					System.out.println("Enter a number to view a vehicle or \n(0) To go back");
					answer1 = s.next();
					if (answer1.equals("0")) { break; }
					else
					{
						System.out.println(results.get(Integer.valueOf(answer1) - 1).toString());
						System.out.println("(1) To select this vehicle \n(2) To go back to vehicle list");
						String answer2 = s.next();
						if (answer2.equals("1"))
						{
							r.setVehicle(results.get(Integer.valueOf(answer1) - 1));
							break;
						}
						else if (answer2.equals("2")) {}
						else { System.out.println("Invalid response."); }
					}
				}
			}
			else if (answer.equals("4"))
			{
				System.out.println("Enter a username: ");
				String username1 = s.next();
				SearchParameter sp = new SearchParameter("Username", username1);
				ArrayList<User> result = UserDatabase.Session.search(sp);
				if (result.size() == 0) { System.out.println("No user found."); }
				else
				{
					r.setCustomer(result.get(0));
				}
			}
			else if (answer.equals("5"))
			{
				String number = r.getCreditCardInfo().getNumber();
				String provider = r.getCreditCardInfo().getProvider();
				String expDate = r.getCreditCardInfo().getExpDate();
				String cvc = r.getCreditCardInfo().getcvc();
				
				String answer1;
				
				while (true)
				{
					System.out.println("Enter a number to change a credit card field.");
					System.out.println("(1) Credit card number: ************" + number.substring(12));
					System.out.println("(2) Provider: " + provider);
					System.out.println("(3) Expiration date: " + expDate);
					System.out.println("(4) cvc: " + cvc);
					System.out.println("(5) To submit credit card changes.");
					System.out.println("(6) To discard credit card changes.");
					
					answer1 = s.next();
					
					if (answer1.equals("1"))
					{
						System.out.println("Enter a credit card number: ");
						number = s.next();
					}
					else if (answer1.equals("2"))
					{
						System.out.println("Enter a credit card provider: ");
						provider = s.next();
					}
					else if (answer1.equals("3"))
					{
						System.out.println("Enter an expiration date (Example: 9/16)");
						expDate = s.next();
					}
					else if (answer1.equals("4"))
					{
						System.out.println("Enter a cvc: ");
						cvc = s.next();
					}
					else if (answer1.equals("5") || answer1.equals("6")) { break; }
					else { System.out.println("Invalid response."); }
				}
				if (answer1.equals("5"))
				{
					CreditCard card1 = new CreditCard(number, provider, expDate, cvc);
					if (card1.verify())
					{
						r.setCard(card1);
						System.out.println("Credit card change saved.");
					}
					else { System.out.println("This card cannot be verified. Change discarded."); }
				}
				
			}
			else if (answer.equals("6"))
			{
				System.out.println("Enter a new total price (Example: 450)");
				price = s.next();
				r.setPrice(Integer.valueOf(price));
			}
			else if ((answer.equals("7") && datesGood) || answer.equals("8")) { break; }
			else { System.out.println("Invalid response."); }
			
			if (pud && dod)
			{
				boolean b1 = Date.fromShortString(pickUpDate).isBefore(Date.fromShortString(dropOffDate));
				boolean b2 = Date.fromShortString(pickUpDate).checkTimeTravel();
				
				if (b1 && !b2) { datesGood = true; }
				else { System.out.println("Dates invalid."); }
			}
		}
		if (! answer.equals("8"))
		{
			ReservationDatabase.Session.removeReservation(oldResID);
			ReservationDatabase.Session.addReservation(r);
			System.out.println("Your edits have been saved.");
		}
	}

    
}
