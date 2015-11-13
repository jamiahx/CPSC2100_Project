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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */
@Stateless
@DeclareRoles({"Customer", "Employee", "Manager"})
public class VehicleDatabase
{
	@PersistenceContext
	EntityManager em;
	
	/**
	 * Creates a new instance of VehicleDatabase.
	 */
	public VehicleDatabase()
	{}
	
	/**
	 * Adds the given Vehicle to the database.
	 * @param vehicle	the Vehicle to be added.
	 */
	@RolesAllowed("Employee")
	public void addVehicle(Vehicle vehicle)
	{
		em.persist(vehicle);
	}
	
	/**
	 * Deletes a Vehicle from the database.
	 * @param vin	the ID of the Vehicle to be deleted.
	 */
	@RolesAllowed("Employee")
	public void deleteVehicle(String vin)
	{
		Vehicle vehicle = em.find(Vehicle.class, vin);
		em.remove(vehicle);
	}
	
	
	
	/**
	 * Searches the database for Vehicles that match the given SearchParameter.
	 * @param sp	the SearchParameter to be searched for.
	 * @return results	the ArrayList of Vehicles that match the search.
	 */
	public List<Vehicle> search(SearchParameter sp)
	{
		List<Vehicle> results = new ArrayList<Vehicle>();
		for (int i = 0; i < vehicleList.size(); i++)
		{
			Vehicle v = vehicleList.get(i);
			if (sp.field.equalsIgnoreCase("make"))
			{
				String make = v.getMake();
				if (sp.result.equalsIgnoreCase(make)) { results.add(v); }
			}
			if (sp.field.equalsIgnoreCase("model"))
			{
				String model = v.getModel();
				if (sp.result.equalsIgnoreCase(model)) { results.add(v); }
			}
			if (sp.field.equalsIgnoreCase("year"))
			{
				String year = "" + v.getYear();
				if (sp.result.equalsIgnoreCase(year)) { results.add(v); }
			}
			if (sp.field.equalsIgnoreCase("category"))
			{
				String category = v.getCategory();
				if (sp.result.equalsIgnoreCase(category)) { results.add(v); }
			}
			if (sp.field.equalsIgnoreCase("rate"))
			{
				String rate = "" + v.getRate();
				if (sp.result.equalsIgnoreCase(rate)) { results.add(v); }
			}
			if (sp.field.equalsIgnoreCase("description"))
			{
				String description = v.getDescription();
				if (sp.result.equalsIgnoreCase(description)) { results.add(v); }
			}
			if (sp.field.equalsIgnoreCase("vin"))
			{
				String vin = v.getVIN();
				if (sp.result.equalsIgnoreCase(vin)) { results.add(v); }
			}
					
		}
    	return results;
	}
	
	/**
	 * Searches the database for Vehicles that do not match the given SearchParameter.
	 * @param sp	the SearchParameter to be searched for.
	 * @return results	the ArrayList of Vehicles that match the search.
	 */
	public List<Vehicle> oppositeSearch(SearchParameter sp)
	{
		List<Vehicle> results = new ArrayList<Vehicle>();
		List<Vehicle> oppositeResults = search(sp);
		for (int i = 0; i < vehicleList.size(); i++)
		{
			for (int j = 0; j < oppositeResults.size(); i++)
			{
				if (!oppositeResults.get(j).getVIN().trim().equalsIgnoreCase(vehicleList.get(i).getVIN().trim()))
				{
					results.add(vehicleList.get(i));
				}
			}
		}
		return results;
	}

	/**
	 * Searches the database for Vehicles that match the SearchParameters in the given list.
	 * @param spList the ArrayList of SearchParameters to be searched for.
	 * @return results the ArrayList of Vehicles that match the search.
	 */
	public List<Vehicle> searchMultiple(ArrayList<SearchParameter> spList)
	{
		List<Vehicle> results = new ArrayList<Vehicle>();
		List<Vehicle> results2 = new ArrayList<Vehicle>();
		results = getAll();
		for (int i = 0; i < spList.size(); i++)
		{
			results2 = results;
			VehicleDatabase temp = new VehicleDatabase();
			temp.load(results2);
			results = temp.search(spList.get(i));
		}
		return results;
	}
	
	/**
	 * Gets all the Vehicles within the database.
	 * @return allVehicles	the ArrayList of all the Vehicles in the database.
	 */
	public List<Vehicle> getAll()
	{
		List<Vehicle> allVehicles = new ArrayList<Vehicle>();
		allVehicles.addAll(vehicleList);
		return allVehicles;
	}
	
	/**
	 * Makes a given ArrayLis of Vehicles into a viewable list.
	 * @param vehicles	the ArrayList to be converted.
	 * @return String the String representing the list.
	 */
	public static String makeOptionList(List<Vehicle> vehicles)
	{
		String s = "";
		for (int i = 0; i < vehicles.size(); i++)
		{
			s = s + "(" + (i+1) + ") " + vehicles.get(i).toShortString() + "\n";
		}
		return s;
	}
}
