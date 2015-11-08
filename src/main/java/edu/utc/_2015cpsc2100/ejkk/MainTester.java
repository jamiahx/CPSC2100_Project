import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;

/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */

public class MainTester
{
	public static void main(String[]args) throws MandrillApiError, IOException
	{
		Scanner s = new Scanner(System.in);
		
		UserDatabase udb = new UserDatabase();
		VehicleDatabase vdb = new VehicleDatabase();
		ReservationDatabase rdb = new ReservationDatabase();
		
		udb.load();
		vdb.load();
		rdb.load();
		
		System.out.println("Databases loaded.");
		
		int loginAttempts = 0;
		
		String answer = "0";
		
		RegistrationForm rf = new RegistrationForm(null, null, null, null, null, null, null);
		User u = new User(rf);
		
		while(! answer.equalsIgnoreCase("Q"))
		{	
			if (answer.equals("0"))
			{
				if (! u.getRole().equalsIgnoreCase("User")) //LOGGED IN OPTIONS
				{
					System.out.println("(1) Basic Search");
					System.out.println("(2) Advanced Search");
					if (u.getRole().equalsIgnoreCase("Employee") || u.getRole().equalsIgnoreCase("Manager")) { System.out.println("(3) To access employee options"); }
					if (u.getRole().equalsIgnoreCase("Manager")) { System.out.println("(4) To access manager options"); }
					System.out.println("(Q) Quit");
					
					answer = s.next();
					
					if (answer.equals("1")) { answer = "3"; }
					else if (answer.equals("2")) { answer = "4"; }
					else if (answer.equals("3") && u.getRole().equalsIgnoreCase("Employee")) { answer = "5"; }
					else if (answer.equals("4") && u.getRole().equalsIgnoreCase("Manager")) { answer = "6"; }
					else if (answer.equalsIgnoreCase("Q")) { break; }
					else
					{
						System.out.println("Invalid response.");
						answer = "0";
					}
				}
				else //NOT LOGGED IN OPTIONS
				{
					System.out.println("(1) Login");
					System.out.println("(2) Create Account");
					System.out.println("(3) Basic Search");
					System.out.println("(4) Advanced Search");
					System.out.println("(Q) Quit");
					
					answer = s.next();
					
					if (answer.equalsIgnoreCase("Q")) { break; }
					else if (! (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")))
					{
						System.out.println("Invalid response.");
						answer = "0";
					}
				}
			}
			if (answer.equals("1")) //LOGIN PROCESS
			{
				
				if (loginAttempts > 0)
				{
					System.out.println("Login attempts remaining: " + (3 - loginAttempts));
				}
				System.out.println("Enter your username.");
				String username = s.next();
				System.out.println("Enter your password.");
				String password = s.next();
				
				if (User.login(username, password) != null)
				{
					u = User.login(username, password);
					if (u.getRole().equalsIgnoreCase("Customer"))
					{
						u = (Customer) u;
						answer = "0";
					}
					if (u.getRole().equalsIgnoreCase("Employee"))
					{
						u = (Employee) u;
						answer = "5";
					}
					if (u.getRole().equalsIgnoreCase("Manager"))
					{
						u = (Manager) u;
						answer = "6";
					}
					System.out.println("Login successful.");
				}
				else if (loginAttempts < 2)
				{
					loginAttempts++;
					while (true)
					{
						System.out.println("Username or password is incorrect.");
						System.out.println("(1) Login again");
						System.out.println("(2) Create new account");
						
						answer = s.next();
						
						if (answer.equals("1") || answer.equals("2")) { break; }
						else { System.out.println("Invalid response."); }
					}
				}
				else
				{
					while (true)
					{
						System.out.println("Login terminated.");
						System.out.println("(1) Create a new account");
						System.out.println("(2) Go back");
						
						String option = s.next();
						
						if (option.equals("1"))
						{
							answer = "2";
							break;
						}
						else if (option.equals("2"))
						{
							answer = "0";
							break;
						}
						else { System.out.println("Invalid response."); }
					}
				}
			}
			else if (answer.equals("2")) //ACCOUNT CREATION PROCESS--USER
			{
				System.out.println("Enter your first name.");
				String firstName = s.next();
				System.out.println("Enter your last name.");
				String lastName = s.next();
				System.out.println("Enter your address.");
				String address = s.next();
				System.out.println("Enter your email address.");
				String emailAddress = s.next();
				System.out.println("Enter your phone number.");
				String phoneNumber = s.next();
				System.out.println("Choose a username.");
				String username = s.next();
				System.out.println("Choose a password.");
				String password = s.next();
				
				if (User.checkUsernameAvailability(username))
				{
					RegistrationForm newForm = new RegistrationForm(firstName, lastName, address, emailAddress, phoneNumber, username, password);
					User.createAccount("Customer", newForm);
					System.out.println("Your account has been created.");
					answer = "0";
				}
				else
				{
					boolean firstTime = true;
					
					System.out.println("The username that you chose is already taken. Enter a different username or");
					System.out.println("(1) To cancel");
					
					username = s.next();
					
					while(! username.equals("1"))
					{
						if (!firstTime)
						{
							System.out.println("The username that you chose is already taken. Enter a different username or");
							System.out.println("(1) To cancel.");
							
							username = s.nextLine();
						}
						if (! username.equals("(1)"))
						{
							if (User.checkUsernameAvailability(username))
							{
								RegistrationForm newForm = new RegistrationForm(firstName, lastName, address, emailAddress, phoneNumber, username, password);
								User.createAccount("Customer", newForm);
								System.out.println("Your account has been created.");
							}
						}
						else { answer = "0"; }
						firstTime = false;
					}
					if (username.equals("1")) { answer = "0"; }
					
				}
			}
			else if (answer.equals("3")) //BASIC SEARCH PROCESS
			{
				answer = u.basicSearch();
				
			}
			else if (answer.equals("4")) //ADVANCED SEARCH PROCESS
			{
				answer = u.advancedSearch();
			}
			else if (answer.equals("5") && (u.getRole().equalsIgnoreCase("Employee") || u.getRole().equalsIgnoreCase("Manager"))) //EMPLOYEE POST-LOGIN OPTIONS
			{
				System.out.println("(1) To register a vehicle");
				System.out.println("(2) To search and update a vehicle");
				System.out.println("(3) To search for a user");
				System.out.println("(4) To search and update a reservation");
				System.out.println("(5) To access customer options");
				if (u.getRole().equalsIgnoreCase("Manager")) { System.out.println("(6) To access manager options"); }
				System.out.println("(Q) Quit");
				
				String answer1 = s.next();
				
				if (answer1.equals("1")) { ((Employee) u).registerVehicle(); }
				else if (answer1.equals("2")) { ((Employee) u).searchVehicles(); }
				else if (answer1.equals("3")) { ((Employee) u).searchUsers(); }
				else if (answer1.equals("4"))
				{
					Reservation selectedReservation = ((Employee) u).searchReservations();
					while (selectedReservation != null)
					{
						System.out.println("(1) To update this reservation");
						System.out.println("(2) To go back to employee options");
						
						String answer2 = s.next();
						
						if (answer2.equals("1")) { ((Employee) u).updateReservation(selectedReservation); }
						else if (answer2.equals("2")) { break; }
						else { System.out.println("Invalid response."); }
					}
				}
				else if (answer1.equals("5")) { answer = "0"; }
				else if (answer1.equals("6") && u.getRole().equalsIgnoreCase("Manager")) { answer = "6"; }
				else if (answer1.equalsIgnoreCase("Q")) { break; }
				else { System.out.println("Invalid response."); }
			}
			else if (answer.equals("6") && u.getRole().equalsIgnoreCase("Manager")) //MANAGER POST-LOGIN OPTIONS
			{
				System.out.println("(1) To create an account");
				System.out.println("(2) To update or delete an existing account");
				System.out.println("(3) To update or cancel a reservation");
				System.out.println("(4) To access employee options");
				System.out.println("(5) To access customer options");
				System.out.println("(Q) Quit");
				
				String answer1 = s.next();
				
				if (answer1.equals("1"))
				{
					((Manager) u).createAccount();
				}
				else if (answer1.equals("2"))
				{
					User selectedUser = ((Employee) u).searchUsers();
					while (selectedUser != null)
					{
						System.out.println("(1) To update this account");
						System.out.println("(2) To delete this account");
						System.out.println("(3) To go back to manager options");
						
						String answer2 = s.next();
						
						if (answer2.equals("1"))
						{
							((Manager) u).updateAccount(selectedUser);
							break;
						}
						else if (answer2.equals("2"))
						{
							((Manager) u).deleteAccount(selectedUser);
							break;
						}
						else if (answer2.equals("3")) { break; }
						else { System.out.println("Invalid response."); }
					}
				}
				else if (answer1.equals("3"))
				{
					Reservation selectedReservation = ((Employee) u).searchReservations();
					while (selectedReservation != null)
					{
						System.out.println("(1) To update this reservation");
						System.out.println("(2) To cancel this reservation");
						System.out.println("(3) To go back to manager options");
						
						String answer2 = s.next();
						
						if (answer2.equals("1"))
						{
							((Employee) u).updateReservation(selectedReservation);
							break;
						}
						else if (answer2.equals("2"))
						{
							((Manager) u).cancelReservation(selectedReservation);
							break;
						}
						else if (answer2.equals("3")) { break; }
						else { System.out.println("Invalid response."); }
					}
				}
				else if (answer1.equals("4"))
				{
					answer = "5";
				}
				else if (answer1.equals("5"))
				{
					answer = "0";
				}
				else if (answer1.equalsIgnoreCase("Q")) { break; }
				else { System.out.println("Invalid response."); }
			}
		}
		
		try
		{
			udb.save();
		} catch (FileNotFoundException e)
		{
			System.out.println("No User Database file found. Writing to new file.");
		}
		try
		{
			vdb.save();
		} catch (FileNotFoundException e)
		{
			System.out.println("No Vehicle Database file found. Writing to new file.");
		}
		try
		{
			rdb.save();
		} catch (FileNotFoundException e)
		{
			System.out.println("No Reservation Database file found. Writing to new file.");
		}
	}
}