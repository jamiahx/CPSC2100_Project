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

import java.time.Duration;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */
@Entity
public class Reservation
{	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private String resID;
    private ParticularDuration duration;
    @ManyToOne(cascade=CascadeType.ALL)
    private Vehicle vehicle;
    @Transient
    private User customer;
    @Transient
    private CreditCard card;
    private double price;
    private boolean canceled;
	
    public Date getPickUpDate() { return duration.getStartTime(); }
    public Date getDropOffDate() { return duration.getEndTime(); }
    public Vehicle getVehicle()
    {
    	if (vehicle != null) { return vehicle; }
    	else { return new Vehicle(null, null, 0, null, 0, null, null); }
    }
    public User getCustomer() { return customer; }
    public CreditCard getCreditCardInfo() { return card; }
    public Duration getDuration()
    {
    	return duration.getDuration();
    }
    public double getPrice() { return price; }
    public String getResID() {return resID; }
    
    /**
     * Cancels this instance of Reservation
     */
    public void cancelReservation() { canceled = true; }
    
    /**
     * Sets the pick-up date of this Reservation to the given date.
     * @param d	The new date of pick-up.
     */
    public void setPickUpDate(Date d)
    {
    	duration.setStartTime(d);
    }
    
    /**
     * Sets the drop-off date of this Reservation to the given date.
     * @param d	The new  date of drop-off.
     */
    public void setDropOffDate(Date d)
    {
    	duration.setEndTime(d);
    }
    
    /**
     * Sets the Vehicle rented for this Reservation to the given vehicle.
     * @param v	The new vehicle.
     */
    public void setVehicle(Vehicle v) { vehicle = v; }
    
    /**
     * Sets the renting Customer of this reservation to the given Customer.
     * @param u The new customer.
     */
    public void setCustomer(User u) { customer = u; }
    
    /**
     * Sets the CreditCard for this Reservation to the given CreditCard.
     * @param c	The new card.
     */
    public void setCard(CreditCard c) { card = c; }
    
    /**
     * Sets the total price of the Reservation to the given price.
     * @param p	The new price.
     */
    public void setPrice(double p) { price = p; }
	
    /**
     * Creates a reservation object which stores temporal, vehicle, and customer information.
     * @param pickUp	the date on which the customer is rented the car
     * @param dropOff	the date on which the customer must return the car
     * @param vehicle	the vehicle which the customer is renting
     * @param customer	the customer who reserved the vehicle
     * @param ccNumber	the credit card number of the customer
     */
    public Reservation(Date pickUp, Date dropOff, Vehicle vehicle, User customer, CreditCard card)
    {
    	duration = new ParticularDuration(pickUp, dropOff);
		this.vehicle = vehicle;
		this.customer = customer;
		this.card = card;
		price = vehicle.getRate() * getDuration().toHours();
		canceled = false;

    }
    
    protected Reservation(){}
	
    /**
     * Creates a String that represents this Reservation.
     * @return s	the String that represents the reservation
     */
    public String toString()
    {
		String s = "";
		String c = "";
		if (canceled) { c = "Canceled Reservation: " + resID; }
		else
		{
			c = "Reservation: " + resID;
		}
		s = c + "\n    Dates: " +  duration.toString() + "\n" +
		    "    Vehicle: " + vehicle.toString() + "\n" +
		    "    Customer: " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
		    "    Total Price: $" + price;
		return s;
    }

    /**
     * Prints this Reservation
     */
    public void print()
    {
    	System.out.println("No printing implementation exists at this time.");
    }
}
