import java.util.Scanner;

/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */

public class Manager extends Employee
{
	Scanner s = new Scanner(System.in);
	
	/**
	 * Creates an instance of Manager.
	 * @param rf	The RegistrationForm from which the Manager's information is retrieved.
	 */
	public Manager(RegistrationForm rf)
	{
		super(rf);
		super.setRole("Manager");
	}

	/**
	 * Creates a new Customer or Employee account.
	 */
	public void createAccount()
	{
		String firstName = "";
		String lastName = "";
		String address = "";
		String emailAddress = "";
		String phoneNumber = "";
		String username = "";
		String password = "";
		String role = "";
		
		String answer = "";
		
		while(! answer.equals("0"))
		{
			boolean b = (firstName != "" && lastName != "" && address != "" && emailAddress != "" && phoneNumber != "" && username != "" && password != "");
			
			System.out.println("Enter a number to change a field.");
			System.out.println("(1) First name: " + firstName);
			System.out.println("(2) Last name: " + lastName);
			System.out.println("(3) Address: " + address);
			System.out.println("(4) Email address: " + emailAddress);
			System.out.println("(5) Phone number: " + phoneNumber);
			System.out.println("(6) Username: " + username);
			System.out.println("(7) Password: " + password);
			if (b) { System.out.println("(8) Save as customer account \n(9) Save as employee account"); }
			System.out.println("(0) To go back");
			
			answer = s.nextLine();
			
			if (answer.equals("1"))
			{
				System.out.println("Enter a first name: ");
				firstName = s.nextLine();
			}
			else if (answer.equals("2"))
			{
				System.out.println("Enter a last name: ");
				lastName = s.nextLine();
			}
			else if (answer.equals("3"))
			{
				System.out.println("Enter an address: ");
				address = s.nextLine();
			}
			else if (answer.equals("4"))
			{
				System.out.println("Enter an email address: ");
				emailAddress = s.nextLine();
			}
			else if (answer.equals("5"))
			{
				System.out.println("Enter a phone number: ");
				phoneNumber = s.nextLine();
			}
			else if (answer.equals("6"))
			{
				System.out.println("Enter a username: ");
				username = s.nextLine();
				if (! User.checkUsernameAvailability(username))
				{
					System.out.println("The username that you entered is already taken.");
					username = "";
				}
			}
			else if (answer.equals("7"))
			{
				System.out.println("Enter a password: ");
				password = s.nextLine();
			}
			else if (b && answer.equals("8"))
			{
				role = "Customer";
				break;
			}
			else if (b && answer.equals("9"))
			{
				role = "Employee";
				break;
			}
			else if (answer.equals("0")) { break; }
			else { System.out.println("Invalid response."); }
		}
		if (! answer.equals("0"))
		{
			RegistrationForm rf = new RegistrationForm(firstName, lastName, address, emailAddress, phoneNumber, username, password);
			User.createAccount(role, rf);
			System.out.println("Your " + role + " account has been created.");
		}
	}

	/**
	 * Updates an existing account.
	 * @param u		The user account which will be updated.
	 */
	public void updateAccount(User u)
	{
		String oldUsername = u.getUsername();
		
		String firstName = u.getFirstName();
		String lastName = u.getLastName();
		String address = u.getAddress();
		String emailAddress = u.getEmailAddress();
		String phoneNumber = u.getPhoneNumber();
		String username = u.getUsername();
		String password = u.getPassword();
		String role = u.getRole();
		
		String answer = "";
		
		while(! answer.equals("0"))
		{
			System.out.println("Enter a number to change a field.");
			System.out.println("(1) First name: " + firstName);
			System.out.println("(2) Last name: " + lastName);
			System.out.println("(3) Address: " + address);
			System.out.println("(4) Email address: " + emailAddress);
			System.out.println("(5) Phone number: " + phoneNumber);
			System.out.println("(6) Username: " + username);
			System.out.println("(7) Password: " + password);
			System.out.println("(8) Save changes \n(9) Discard changes");
			
			answer = s.nextLine();
			
			if (answer.equals("1"))
			{
				System.out.println("Enter a first name: ");
				firstName = s.nextLine();
			}
			else if (answer.equals("2"))
			{
				System.out.println("Enter a last name: ");
				lastName = s.nextLine();
			}
			else if (answer.equals("3"))
			{
				System.out.println("Enter an address: ");
				address = s.nextLine();
			}
			else if (answer.equals("4"))
			{
				System.out.println("Enter an email address: ");
				emailAddress = s.nextLine();
			}
			else if (answer.equals("5"))
			{
				System.out.println("Enter a phone number: ");
				phoneNumber = s.nextLine();
			}
			else if (answer.equals("6"))
			{
				System.out.println("Enter a username: ");
				username = s.nextLine();
				if (! User.checkUsernameAvailability(username))
				{
					System.out.println("The username that you entered is already taken.");
					username = "";
				}
			}
			else if (answer.equals("7"))
			{
				System.out.println("Enter a password: ");
				password = s.nextLine();
			}
			else if (answer.equals("8") || answer.equals("9")) { break; }
			else { System.out.println("Invalid response."); }
		}
		if (! answer.equals("9"))
		{
			UserDatabase.Session.deleteUser(oldUsername);
			RegistrationForm rf = new RegistrationForm(firstName, lastName, address, emailAddress, phoneNumber, username, password);
			User.createAccount(role, rf);
			System.out.println("Your edits have been saved.");
		}
	}

	/**
	 * Deletes an existing account.
	 * @param u		The user account which will be deleted.
	 */
	public void deleteAccount(User u)
	{
		UserDatabase.Session.deleteUser(u.getUsername());
		System.out.println("Account deleted.");
	}

	/**
	 * Cancels an existing Reservation.
	 * @param r		The Reservation which will be canceled.
	 */
	public void cancelReservation(Reservation r)
	{
		r.cancelReservation();
		System.out.println("Reservation canceled.");
	}
}
