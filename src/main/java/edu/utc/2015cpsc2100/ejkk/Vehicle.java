/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */

public class Vehicle
{
    private String make;
    private String model;
    private int year;
    private String category;
    private double rate;
    private String description;
    private String vin;

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getCategory() { return category; }
    public double getRate() { return rate; }
    public String getDescription() { return description; }
    public String getVIN() { return vin; }
    
    public void setMake(String make) { this.make = make; }
    public void setModel(String model) { this.model = model; }
    public void setYear(String year) { this.year = Integer.valueOf(year); }
    public void setCategory(String category) { this.category = category; }
    public void setRate(String rate) { this.rate = Integer.valueOf(rate); }
    public void setDescription(String description) { this.description = description; }
    public void setVIN(String vin) { this.vin = vin; }

    /**
     * Vehicle constructor for the Vehicle class
     * @param make	Vehicle manufacturer
     * @param model	Vehicle model.
     * @param year	Model Year of vehicle
     * @param category	Vehicle category
     * @param rate 		Price per day rented
     * @param description	Vehicle description
     */
    public Vehicle(String make, String model, int year, String category, double rate, String description, String vin)
    {
		this.make = make;
		this.model = model;
		this.year = year;
		this.category = category;
		this.rate = rate;
		this.description = description;
		this.vin = vin;
    }
    
    /**
     * Creates a String that describes the Vehicle.
     * @return s	The String describing the Vehicle.
     */
    public String toString()
    {
    	String s = "";
    	s = s + year + " " + make + " " + model + "\n" + 
    			"    Description: " + description + "\n" +
    			"    Price per day: $" + rate;
    	return s;
    }
	
    /**
     * Creates a String that is a Short description of the Vehicle for display purposes within the program.
     * @return s	The String describing the Vehicle.
     */
    public String toShortString()
    {
    	String s = "";
    	s = s + year + " " + make + " " + model;
    	return s;
    }
    
    /**
     * Creates a String that describes the Vehicle for permanent storage of the VehicleDatabase.
     * @return s	The String that describes the Vehicle.
     */
    public String toStorageString()
    {
    	String s = "";
    	s = s + make + ", " + model + ", " + year + ", " + category + ", " + rate + ", " + description + ", " + vin;
		return s;
    }
    
    /**
     * "Translates" a String from the VehicleDatabase load file into a new Vehicle object.
     * @param v		The String describing the Vehicle.
     * @return	vehicle		The new Vehicle that has been translated from the storage String.
     */
    public static Vehicle fromStorageString(String v)
    {
    	Vehicle vehicle;
    	String make;
    	String model;
    	int year;
    	String category;
    	double rate;
    	String description;
    	String vin;
    	
    	int comma = v.indexOf(",");
    	int comma2 = v.indexOf(",", comma + 1);
    	int comma3 = v.indexOf(",", comma2 + 1);
    	int comma4 = v.indexOf(",", comma3 + 1);
    	int comma5 = v.indexOf(",", comma4 + 1);
    	int comma6 = v.indexOf(",", comma5 + 1);
    	
    	make = v.substring(0, comma);
    	model = v.substring(comma + 2, comma2);
    	year = Integer.valueOf(v.substring(comma2 + 2, comma3));
    	category = v.substring(comma3 + 2, comma4);
    	rate = Double.valueOf(v.substring(comma4 + 2, comma5));
    	description = v.substring(comma5 + 2, comma6);
    	vin = v.substring(comma6 + 2);
    	
    	vehicle = new Vehicle(make, model, year, category, rate, description, vin);
    	return vehicle;
    }
    
}

