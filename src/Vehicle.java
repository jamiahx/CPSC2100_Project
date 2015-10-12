/**
 * 
 * @author emmaperez
 *
 */

import java.time.Year;

public class Vehicle {

    private final String make;
    private final String model;
    private final Year year;
    private final String category;
    protected int rate;
    protected String description;

    public String getMake(){return make;}
    public String getModel(){return model;}
    public Year getYear(){return year;}
    public String getCategory(){return category;}
    public int getRate(){return rate;}
    public String getDescription(){return description;}

    /**
     * Vehicle constructor for the Vehicle class
     * @param make Vehicle Manufacturer
     * @param model 
     * @param year Model Year of vehicle
     * @param category Vehicle category
     * @param rate Price per day rented
     * @param description Vehicle description
     */
    public Vehicle(String make, String model, Year year, String category, int rate, String description)
    {
	this.make = make;
	this.model = model;
	this.year = year;
	this.category = category;
	this.rate = rate;
	this.description = description;
    }
	
    /**
     * To convert the items in the array list to strings. 
     */
    public String toString()
    {
	return (make + "," + model + "," + year.toString() + "," + category + "," + rate + "," + description);
    }
}
