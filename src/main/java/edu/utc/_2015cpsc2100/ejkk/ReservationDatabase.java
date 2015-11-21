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

import java.util.Date;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;


/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */
@Stateless
@DeclareRoles({"Customer", "Employee", "Manager"})
public class ReservationDatabase
{
	@PersistenceContext
	EntityManager em;
	
	/**
	 * does nothing
	 */
	public ReservationDatabase() {}

	/**
	 * Adds a Reservation object to the Database
	 * @param r	the Reservation object that is added.
	 */
	public void addReservation(Reservation toAdd)
	{ em.persist(toAdd); }
	
	/**
	 * Removes a Reservation object from the Database
	 * @param the Reservation to be removed.
	 */
	public void removeReservation(Reservation toRemove)
	{ em.remove(toRemove); }
	
	/**
	 * Removes a Reservation object from the Database
	 * @param resID	the ID of the Reservation to be removed.
	 */
	public void removeReservation(String resID)
	{ this.removeReservation(this.getReservation(resID)); }
	
	/**
	 * Returns the corresponding Reservation object
	 * @param resID
	 * @return
	 */
	public Reservation getReservation(String resID)
	{ return em.find(Reservation.class, resID); }

	/**
	 * Searches the Reservations using the given restrictions
	 * @param restrictions	search restrictions
	 * @return results	List of Reservation objects that match the restrictions
	 */
	public List<Reservation> search(Predicate... restrictions)
	{
		CriteriaBuilder cBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Reservation> cQuery = cBuilder.createQuery(Reservation.class);
		cQuery.select(cQuery.from(Reservation.class));
		cQuery.where(restrictions);
		TypedQuery<Reservation> tQuery = em.createQuery(cQuery);
		return tQuery.getResultList();
	}
	
	/**
	 * Gets all Reservation objects stored in the Database.
	 * @return List of all Reservations in the Database
	 */
	public List<Reservation> getAll()
	{
		return search();
	}
	
	/**
	 * Searches the Database to find Vehicles that are available for the given period.
	 * @param pickUpDate	the starting date of the period.
	 * @param dropOffDate	the ending date of the period.
	 * @param restrictions	additional search restrictions
	 * @return results	List of Vehicles that match the restrictions and are available during period specified
	 */
	public List<Vehicle> vehicleAvailabilitySearch(Date pickUpDate, Date dropOffDate, Predicate... restrictions)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> cQuery = cb.createQuery(Vehicle.class);
		Root<Vehicle> vehicle = cQuery.from(Vehicle.class);
		SetJoin<Vehicle, Reservation> reservation = vehicle.join(Vehicle_.reservations);
		Subquery<Reservation> subquery = cQuery.subquery(Reservation.class);
		Root<Reservation> subReservation = cQuery.from(Reservation.class);
		Predicate isSubReservation = cb.equal(reservation, subReservation);
		Predicate isBefore = cb.greaterThan(subReservation.get(Reservation_.pickUpDate), dropOffDate);
		Predicate isAfter = cb.lessThan(subReservation.get(Reservation_.dropOffDate), pickUpDate);
		subquery.where(cb.and(isSubReservation, cb.not(cb.or(isBefore, isAfter))));
		cQuery.where(cb.not(cb.exists(subquery)));
		cQuery.where(restrictions);
		TypedQuery<Vehicle> tQuery = em.createQuery(cQuery);
		return tQuery.getResultList();
	}

	/**
	 * Changes the Vehicle associated with a Reservation.
	 * @param toUpdate	the Reservation to be updated
	 * @param newVehicle	the new Vehicle to be set on the Reservation.
	 */
	public void vehicleChange(Reservation toUpdate, Vehicle newVehicle)
	{
		toUpdate.setVehicle(newVehicle);
	}
	
	/**
	 * Changes the Vehicle associated with a Reservation.
	 * @param resID	the ID of the Reservation to be updated
	 * @param newVehicle	the new Vehicle to be set on the Reservation.
	 */
	public void vehicleChange(String resID, Vehicle newVehicle)
	{
		vehicleChange(this.getReservation(resID), newVehicle);
	}
}
