/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */

public class RegistrationForm
{
    public String firstName;
    public String lastName;
    public String address;
    public String emailAddress;
    public String phoneNumber;
    public String username;
    public String password;
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public String getEmailAddress() { return emailAddress; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
	
    /**
     * Creates a new RegistrationForm object.
     * @param customerFirstName	The first name of the user that the RegistrationForm describes.
     * @param customerLastName	The last name of the user.
     * @param address	The address of the user.
     * @param emailAddress	The email address of the user.
     * @param phoneNumber	The phone number of the user.
     * @param userName	The username of the user.
     * @param password	The password of the user.
     */
    public RegistrationForm(String customerFirstName, String customerLastName, String address, String emailAddress, String phoneNumber, String userName, String password)
    {
		this.firstName = customerFirstName;
		this.lastName = customerLastName;
		this.address = address;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.username = userName;
		this.password = password;
    }
	
    /**
     * Converts the RegistrationForm object to a String.
     * @return s	The String describing the RegistrationForm
     */
    public String toString()
    {
    	String s = firstName +" " + lastName + ", " + address + ", " + emailAddress + ", " + phoneNumber + ", " + username + ", " + password;
    	return s;
	}
}
