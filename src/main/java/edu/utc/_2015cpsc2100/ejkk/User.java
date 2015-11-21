/**
  * Copyright 2015 Emma Perez, Jeremiah Gaertner, Kate Siprelle, Kaleb Sanchez
 * emma11.gene@gmail.com
 * jamiahx@gmail.com
 * kalebsanchez23@yahoo.com
 * ksiprelle@gmail.com
 * 
 * This file is a part of ORS.
 *
 * ORS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * ORS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ORS.  If not, see <http://www.gnu.org/licenses/>.
 */


package edu.utc._2015cpsc2100.ejkk;

import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;


/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */
public class User
{
	Scanner s = new Scanner(System.in);
	
	private String firstName;
	private String lastName;
	private String address;
	private String emailAddress;
	private String phoneNumber;
	private String username;
	private String password;
	private String role;
	private ArrayList<Reservation> rentalRecord;
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getAddress() { return address; }
	public String getEmailAddress() { return emailAddress; }
	public String getPhoneNumber() { return phoneNumber; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getRole() { return role; }
	public ArrayList<Reservation> getRentalRecord() { return rentalRecord; }
	
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setUsername(String username)
	{
		if (checkUsernameAvailability(username)) { this.username = username; }
		else { System.out.println("Username already taken."); }
	}
	public void setPassword(String password) { this.password = password; }
	public void setRole(String role) { this.role = role; }
	public void addToRecord(Reservation r) { rentalRecord.add(r); }
	
	/**
	 * Creates an instance of User.
	 * @param rf	The RegistrationForm from which the User's information is retrieved.
	 */
	public User(RegistrationForm rf)
	{
		firstName = rf.getFirstName();
		lastName = rf.getLastName();
		username = rf.getUsername();
		password = rf.getPassword();
		address = rf.getAddress();
		emailAddress = rf.getEmailAddress();
		phoneNumber = rf.getPhoneNumber();
		role = "User";
		rentalRecord = new ArrayList<Reservation>();
	}

	/**
	 * Logs the user into the system.
	 * @param username	The user's username.
	 * @param password	The user's password
	 * @return u	A new instance of user containing the individual user's information.
	 */
	public static User login(String username, String password)
	{
		User u = null;
		SearchParameter sp = new SearchParameter("username", username);
		ArrayList<User> result = UserDatabase.Session.search(sp);
		if (result.size() != 0)
		{
			if (result.get(0).getPassword().equals(password))
			{
				u = result.get(0);
			}
		}
		return u;
	}
	
	/**
	 * Creates a new User account.
	 * @param role	the role of the User.
	 * @param rf	the RegistrationForm from which the new User's information is taken.
	 * @return u	the User object which represents the new account.
	 */
	public static User createAccount(String role, RegistrationForm rf)
	{
		User u = null;
		if (checkUsernameAvailability(rf.getUsername()))
		{
			if (role.equalsIgnoreCase("Manager")) { u = new Manager(rf); }
			if (role.equalsIgnoreCase("Employee")) { u = new Employee(rf); }
			if (role.equalsIgnoreCase("Customer")) { u = new Customer(rf); }
			UserDatabase.Session.addUser(u);
		}
		else
		{
			System.out.println("Username already taken.");
		}
		return u;
	}
	
	/**
	 * Allows the User to search for Vehicles available during a specific time period.
	 * @return answer	the String representing the User's next location in the program after the method completes.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public String basicSearch() throws MandrillApiError, IOException
	{
		String answer = "";
		String pickUpDate;
		String dropOffDate;
		
		while (true)
		{
			System.out.println("Enter your pick-up date. Example: 7/6/2015");
			pickUpDate = s.next();
			System.out.println("Enter your drop-off date.");
			dropOffDate = s.next();
			
			boolean b1 = Date.fromShortString(pickUpDate).isBefore(Date.fromShortString(dropOffDate));
			boolean b2 = Date.fromShortString(pickUpDate).checkTimeTravel();
			
			if (b1 && !b2) { break; }
			else { System.out.println("Invalid response."); }
		}
		
		ArrayList<Vehicle> results = ReservationDatabase.Session.vehicleAvailabilitySearch(pickUpDate, dropOffDate);
		
		answer = searchProcessing(results, pickUpDate, dropOffDate);
		return answer;
	}
	
	/**
	 * Allows the User to search for Vehicles available during a specific time period that also match a set of search parameters.
	 * @return answer	the String representing the User's next location in the program after the method completes.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public String advancedSearch() throws MandrillApiError, IOException
	{
		String answer = "";
		//String pickUpDate;
		//String dropOffDate;
		
		JFrame adSearch = new JFrame();
		adSearch.setSize(400, 600);
		adSearch.setLocationRelativeTo(null);
		adSearch.setLayout(new FlowLayout());
		
		final int TEXT_FIELD_SIZE = 20;
		final JTextField pickupDate = new JTextField(TEXT_FIELD_SIZE);
		adSearch.add(pickupDate);
		pickupDate.setText("Enter your pick-up date.");
		pickupDate.setToolTipText("Example: 7/6/2015");
		String strPickupDate = pickupDate.getText();
		
		final JTextField dropOffDate = new JTextField(TEXT_FIELD_SIZE);
		adSearch.add(dropOffDate);
		dropOffDate.setText("Enter your drop-off date.");
		dropOffDate.setToolTipText("Example: 7/8/2015");
		String strDropDate = dropOffDate.getText();
		
		boolean b1 = Date.fromShortString(strPickupDate).isBefore(Date.fromShortString(strDropDate));
		boolean b2 = Date.fromShortString(strPickupDate).checkTimeTravel();
		
		if (b1 && !b2){  }
		else 
		{ 
			JOptionPane.showMessageDialog( null, "Invalid response.",
					"Warning", JOptionPane.ERROR_MESSAGE);
		}
		
//		while (true)
//		{
			//System.out.println("Enter your pick-up date. Example: 7/6/2015");
			//pickUpDate = s.next();
			//System.out.println("Enter your drop-off date.");
			//dropOffDate = s.next();
			
			//boolean b1 = Date.fromShortString(pickUpDate).isBefore(Date.fromShortString(dropOffDate));
			//boolean b2 = Date.fromShortString(pickUpDate).checkTimeTravel();
			
//			if (b1 && !b2) { break; }
//			else { System.out.println("Invalid response."); }
//		}
		final JTextField make = new JTextField(TEXT_FIELD_SIZE);
		adSearch.add(make);
		make.setText("Enter make");
		String strMake = make.getText();
		
		final JTextField model = new JTextField(TEXT_FIELD_SIZE);
		adSearch.add(model);
		model.setText("Enter model");
		String strModel = model.getText();
		
		final JTextField year = new JTextField(TEXT_FIELD_SIZE);
		adSearch.add(year);
		year.setText("Enter year");
		String strYear = year.getText();
		
		final JTextField category = new JTextField(TEXT_FIELD_SIZE);
		adSearch.add(category);
		category.setText("Enter category");
		String strCategory = category.getText();
		
		final JTextField rate = new JTextField(TEXT_FIELD_SIZE);
		adSearch.add(rate);
		rate.setText("Enter rate");
		String strRate = rate.getText();
		
		//String make = "";
		//String model = "";
		//String year = "";
		//String category = "";
		//String rate = "";
		
		String answer1 = "";
		
		while(! answer1.equals("6"))
		{
			System.out.println("Select a search field to change it.");
			System.out.println("(1) Make: " + make);
			System.out.println("(2) Model: " + model);
			System.out.println("(3) Year: " + year);
			System.out.println("(4) Category: " + category);
			System.out.println("(5) Maximum Rate Per Day: " + rate);
			System.out.println("(6) Search \n(7) Go back");
			
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
				System.out.println("Available Makes:");
				for (int i = 0; i < availableMakes.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + availableMakes.get(i));
				}
				make = availableMakes.get(Integer.valueOf(s.next())-1);
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
				System.out.println("Available Models:");
				for (int i = 0; i < availableModels.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + availableModels.get(i));
				}
				model = availableModels.get(Integer.valueOf(s.next())-1);
			}
			else if (answer1.equals("3")) //YEAR SELECTION
			{
				ArrayList<String> availableYears = new ArrayList<String>();
				for (int i = 0; i < vdbSize; i++)
				{
					boolean duplicate = false;
					for (int j = 0; j < availableYears.size(); j++)
					{
						if (VehicleDatabase.Session.getAll().get(i).getYear() + "" == availableYears.get(j))
						{
							duplicate = true;
						}
					}
					if (!duplicate) { availableYears.add(VehicleDatabase.Session.getAll().get(i).getYear() + ""); }
				}
				System.out.println("Available Years:");
				for (int i = 0; i < availableYears.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + availableYears.get(i));
				}
				year = availableYears.get(Integer.valueOf(s.next())-1);
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
				System.out.println("Available Categories:");
				for (int i = 0; i < availableCategories.size(); i++)
				{
					System.out.println("(" + (i+1) + ") " + availableCategories.get(i));
				}
				category = availableCategories.get(Integer.valueOf(s.next())-1);
			}
			else if (answer1.equals("5")) //MAX PRICE SELECTION
			{
				System.out.println("Enter your maximum price per day (Example: 32)");
				rate = s.nextLine();
			}
			else if (answer1.equals("6"))
			{
				break;
			}
			else if (answer1.equals("7"))
			{
				answer = "0";
				break;
			}
			else { System.out.println("Invalid response."); }
		}
		
		ArrayList<SearchParameter> spList = new ArrayList<SearchParameter>();
		
		if (! make.equals("")) { spList.add(new SearchParameter("make", make)); }
		if (! model.equals("")) { spList.add(new SearchParameter("model", model)); }
		if (! year.equals("")) { spList.add(new SearchParameter("year", year)); }
		if (! category.equals("")) { spList.add(new SearchParameter("category", category)); }
		
		VehicleDatabase tempDatabase = new VehicleDatabase();
		tempDatabase.load(ReservationDatabase.Session.vehicleAvailabilitySearch(pickUpDate, dropOffDate));
		ArrayList<Vehicle> resultList = tempDatabase.searchMultiple(spList);
		
		ArrayList<Vehicle> resultList2 = new ArrayList<Vehicle>();
		
		if (! answer.equals("0"))
		{
			if (! rate.equals(""))
			{
				for (int i = 0; i < resultList.size(); i++)
				{
					if (resultList.get(i).getRate() <= Integer.valueOf(rate))
					{
						resultList2.add(resultList.get(i));
					}
				}
				answer = searchProcessing(resultList2, pickUpDate, dropOffDate);
			}
			else { answer = searchProcessing(resultList, pickUpDate, dropOffDate); }
		}

		return answer;
	}
	
	/**
	 * Allows the User to view a specific Vehicle from his/her search results, request a quote for the Vehicle, and attempt to reserve the Vehicle.
	 * @param results	the results from the previous search.
	 * @param pickUpDate	the beginning date of the presumed rental period.
	 * @param dropOffDate	the end date of the presumed rental period.
	 * @return answer	the String representing the User's next location in the program after the method completes.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public String searchProcessing(ArrayList<Vehicle> results, String pickUpDate, String dropOffDate) throws MandrillApiError, IOException
	{
		JFrame search = new JFrame();
		search.setSize(400, 600);
		search.setLocationRelativeTo(null);
		search.setLayout(new FlowLayout());
		
		String answer = "";
		if (results.size() == 0) //NO SEARCH RESULTS FOUND
		{
			JOptionPane.showMessageDialog( null, "No vehicles match your search",
					"Warning", JOptionPane.ERROR_MESSAGE);
			//System.out.println("No vehicles match your search.");
			answer = "0";
		}
		else //SEARCH RESULTS FOUND
		{
			while (true)
			{
				// System.out.println("Available vehicles: ");
				JLabel label = new JLabel("Available vehicles");
				for (int i = 0; i < results.size(); i++)
				{
					//System.out.println("(" + (i+1) + ") " + results.get(i).toShortString());
					JLabel label2 = new JLabel("(" + (i+1) + ")" + results.get(i).toShortString());
				}
				//System.out.println("Enter a number to view a vehicle or \n(0) To go back");
				//String option = s.next();
				final int TEXT_FIELD_SIZE = 20;
				final JTextField option = new JTextField(TEXT_FIELD_SIZE);
				search.add(option);
				option.setText("Enter a number to number to view a vehicle?");
				String strOption = option.getText();
				
				if (strOption.equals("0")) //GO BACK OPTION
				{
					answer = "0";
					break;
				}
				else if (Integer.valueOf(strOption) <= results.size())//CAR SELECTED OPTION
				{
					Vehicle selectedVehicle = selectVehicle(results, strOption);
					String option2;
					
					while (true)
					{
						System.out.println("(0) " + selectedVehicle.toString());
						System.out.println("(1) To get a quote for this vehicle \n(2) To go back");
						
						option2 = s.next();
						
						if (! (option2.equals("1") || option2.equals("2"))) { System.out.println("Invalid response."); }
						else { break; }
					}

					if (option2.equals("1"))
					{
						while (true)
						{
							System.out.println("Your requested quote for this vehicle is " + getQuote(selectedVehicle, pickUpDate, dropOffDate));
							System.out.println("(1) To reserve this vehicle \n(2) To go back");
							
							option2 = s.next();
							
							if (! (option2.equals("1") || option2.equals("2"))) { System.out.println("Invalid response."); }
							else { break; }
						}
						
						if (option2.equals("1") && this.getRole().equalsIgnoreCase("User"))
						{
							while(true)
							{
								System.out.println("You must be logged in to reserve this vehicle.");
								System.out.println("(1) To log in");
								System.out.println("(2) To create an account");
								System.out.println("(3) To go back");
								
								String option3 = s.next();
								
								if (! (option3.equals("1") || option3.equals("2"))) { System.out.println("Invalid response."); }
								else
								{
									answer = option3;
									break;
								}
							}
							break;
						}
						else if (option2.equals("1") && !this.getRole().equalsIgnoreCase("User"))
						{
							boolean verified = false;
							while (true)
							{
								System.out.println("Please enter your credit card number.");
								String ccNumber = s.next();
								System.out.println("Please enter your credit card provider.");
								String provider = s.next();
								System.out.println("Please enter your expiration date. Example: 7/15");
								String expDate = s.next();
								System.out.println("Please enter your cvc.");
								String cvc = s.next();
								CreditCard card = new CreditCard(ccNumber, provider, expDate, cvc);
								verified = card.verify();
								if (verified)
								{
									VehicleCLI.reserveVehicle(selectedVehicle, pickUpDate, dropOffDate, card, this);
									answer = "0";
									break;
								}
								else
								{
									String option4;
									while (true)
									{
										System.out.println("Your card cannot be verified.");
										System.out.println("(1) To re-enter your information");
										System.out.println("(2) To cancel");
										
										option4 = s.next();
										
										if (! (option4.equals("1") || option4.equals("2"))) { System.out.println("Invalid response."); }
										else { break; }
									}
									if (option4.equals("2")) { break; }
								}
							}
							break;
						}
					}
				}
				else { System.out.println("Invalid response."); }
			}
		}
		search.setVisible(true);
		return answer;
	}
	

	/**
	 * Creates a String which represents this User.
	 * @return s	the String representing the User.
	 */
	public String toString()
	{
		String role = this.getRole();
		String s = "";
		s = s + role + ": " + firstName + " " + lastName + "\n" +
				"    Username: " + username;
		return s;
	}
	
	
	/**
	 * Creates a String which represents this User for storage on the UserDatabase text file.
	 * @return s	the String represernting the User.
	 */
	public String toStorageString()
	{	
		String s = "";
		s = s + role + ", " + firstName + ", " + lastName + ", " + address + ", " + emailAddress + ", " + phoneNumber + ", " + username + ", " + password;
		return s;
	}
	
	
	/**
	 * Creates a new User object from a String taken from the UserDatabase text file.
	 * @return user	the instanc of User that was translated from the String.
	 */
	public static User fromStorageString(String u)
	{
		RegistrationForm rf = new RegistrationForm(null, null, null, null, null, null, null);
		User user = new User(rf);
		
		String role;
		String firstName;
		String lastName;
		String address;
		String emailAddress;
		String phoneNumber;
		String username;
		String password;
		
		int comma = u.indexOf(",");
		int comma2 = u.indexOf(",", comma + 1);
		int comma3 = u.indexOf(",", comma2 + 1);
		int comma4 = u.indexOf(",", comma3 + 1);
		int comma5 = u.indexOf(",", comma4 + 1);
		int comma6 = u.indexOf(",", comma5 + 1);
		int comma7 = u.indexOf(",", comma6 + 1);
		
    	role = u.substring(0, comma);
    	firstName = u.substring(comma + 2, comma2);
    	lastName = u.substring(comma2 + 2, comma3);
    	address = u.substring(comma3 + 2, comma4);
    	emailAddress = u.substring(comma4 + 2, comma5);
    	phoneNumber = u.substring(comma5 + 2, comma6);
    	username = u.substring(comma6 + 2, comma7);
    	password = u.substring(comma7 + 2);
    	
    	rf = new RegistrationForm(firstName, lastName, address, emailAddress, phoneNumber, username, password);
    	
    	if (role.equalsIgnoreCase("Manager")) { user = new Manager(rf); }
    	else if (role.equalsIgnoreCase("Employee")) { user = new Employee(rf); }
    	else if (role.equalsIgnoreCase("Customer")) { user = new Customer(rf); }
    	
		return user;
	}
	
	
	/**
	 * Checks the availability of a given username.
	 * @param username	the username to be checked.
	 * @return b	the boolean representing whether or not the username is available.
	 */
	public static boolean checkUsernameAvailability(String username)
	{
		boolean b = true;
		SearchParameter sp = new SearchParameter("username", username);
		if (UserDatabase.Session.search(sp).size() != 0) { b = false; }
		return b;
	}
}
