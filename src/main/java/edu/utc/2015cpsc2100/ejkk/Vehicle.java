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


import java.time.Year;


package edu.utc.2015cpsc2100.ejkk;

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
