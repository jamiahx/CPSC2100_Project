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


import java.util.ArrayList;


package edu.utc.2015cpsc2100.ejkk;

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
