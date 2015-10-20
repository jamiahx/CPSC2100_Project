/**
 * Copyright 2015 Emma Perez, jamiahx, Kate Siprelle, Kaleb Sanchez
 * jamiahx@gmail.com
 * kalebsanchez23@yahoo.com
 * ksiprelle@gmail.com
 * 
 * This file is a part of CPSC2100_ORS.
 *
 * CPSC2100_ORS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * CPSC2100_ORS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with CPSC2100_ORS.  If not, see <http://www.gnu.org/licenses/>.
 */


package edu.utc.2015cpsc2100.ejkk;

public class RegestrationForm {

    public String customerFirstName;
    public String customerLastName;
    public String address;
    public String emailAddress;
    public String phoneNumber;
    public String userName;
    public String password;
	
	
    public RegestrationForm(String customerFirstName, String customerLastName, String address, String emailAddress, String phoneNumber, String userName, String password)
    {
	this.customerFirstName = customerFirstName;
	this.customerLastName = customerLastName;
	this.address = address;
	this.emailAddress = emailAddress;
	this.phoneNumber = phoneNumber;
	this.userName = userName;
	this.password = password;
    }
	
    public String toString()
    {
	return customerFirstName +" " + customerLastName + " " + address + " " + emailAddress + " " + phoneNumber + " " + userName + " " + password;
    }
}
