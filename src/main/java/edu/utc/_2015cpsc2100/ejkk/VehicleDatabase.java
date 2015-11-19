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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;


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
	public List<Vehicle> search(Predicate... restrictions)
	{
		CriteriaBuilder cBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> cQuery = cBuilder.createQuery(Vehicle.class);
		cQuery.select(cQuery.from(Vehicle.class));
		cQuery.where(restrictions);
		TypedQuery<Vehicle> tQuery = em.createQuery(cQuery);
		return tQuery.getResultList();
	}
	
	/**
	 * Gets all the Vehicles within the database.
	 * @return allVehicles	the ArrayList of all the Vehicles in the database.
	 */
	public List<Vehicle> getAll()
	{
		CriteriaBuilder cBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> cQuery = cBuilder.createQuery(Vehicle.class);
		cQuery.select(cQuery.from(Vehicle.class));
		TypedQuery<Vehicle> tQuery = em.createQuery(cQuery);
		return tQuery.getResultList();
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
