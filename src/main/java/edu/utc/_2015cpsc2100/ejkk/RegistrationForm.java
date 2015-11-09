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
