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


package edu.utc._2015cpsc2100.ejkk;

import java.util.ArrayList;
import java.security.Principal;
import java.sql.Time;
import javax.persistence.*;


@Entity
@IdClass(ReservationId.class)
public class Reservation
{
    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="RES_NUM")
    private int resNumber;
    @ManyToOne(cascade=persist)
    private Vehicle vehicle;
    private Time pickUpTime;
    private Time dropOffTime;
    private Principal customer;
    private int price;


    public Date getPickUpTime() { return pickUpTime; }
    public Date getDropOffTime() { return dropOffTime; }
    public Vehicle getVehicle() { return vehicle; }
    public Principal getCustomer() { return customer; }
    public int getReservationDates() { return reservationDates; }
    public int getPrice() { return price; }
	
    /**
     * Creates a reservation object which stores temporal, vehicle, and customer information.
     * @param pickUp	the time at which the customer is scheduled to pick up
     *                  the car
     * @param dropOff	the time at which the customer is scheduled to drop off
     *                  the car
     * @param vehicle	the vehicle which the customer is renting
     * @param customer	the customer principal who reserved the vehicle
     */
    public Reservation(Vehicle vehicle, Time pickUp, Time dropOff,
		       Principal customer)
    {
	pickUpTime = pickUp;
	dropOffTime = dropOff;
	this.vehicle = vehicle;
	this.customer = customer;
	price = vehicle.rate * (dropOffTime - pickUpTime);
    }
	
    /**
     * Returns a String that represents the reservation.
     * @return s	the String that represents the reservation
     */
    public String toString()
    {
	String s = "";
	s = s + "Times: " +  pickUpTime.toShortString() + " - " + dropOffDate.toShortString() + "\n" +
	    "Vehicle: " + vehicle.toString() + "\n" +
	    "Customer: " + customer.getName() + "\n" +
	    "Price: " + price + "\n" +
	    "Reservation Number: " + resNumber;
	return s;
    }
    
}
