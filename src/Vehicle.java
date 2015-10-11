/**
 * 
 * @author emmaperez
 *
 */
public class Vehicle {

	public String make;
	public String model;
	public String year;
	public String type;
	public String pricePerDay;
	public String description;

	/**
	 * Vehicle constructor for the Vehicle class
	 * @param make
	 * @param model
	 * @param year
	 * @param type
	 * @param pricePerDay
	 * @param description
	 */
	public Vehicle(String make, String model, String year, String type, String pricePerDay, String description)
	{
		this.make = make;
		this.model = model;
		this.year = year;
		this.type = type;
		this.pricePerDay = pricePerDay;
		this.description = description;
	}
	
	/**
	 * To convert the items in the array list to strings. 
	 */
	public String toString()
	{
		return make + " " + model + " " + year + " " + type + " " + pricePerDay + " " + description;
	}
}
