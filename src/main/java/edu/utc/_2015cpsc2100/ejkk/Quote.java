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


/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */
public class Quote
{	
	private double pricePerDay;
	private int daysOfRental;
	private double total;
	
	/**
	 * Gets the total price from an instance of Quote.
	 * @return total	The total price.
	 */
	public double getTotal() { return total; }
	
	/**
	 * Creates an instance of the Quote class, which computes the total cost of a reservation.
	 * @param price		The price of the vehicle per day of rental.
	 * @param days		The number of days for which the vehicle will be rented.
	 */
	public Quote(double price, int days)
	{
		pricePerDay = price;
		daysOfRental = days;
		total = pricePerDay * daysOfRental;
	}
	
}
