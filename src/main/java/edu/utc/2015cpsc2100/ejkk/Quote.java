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
