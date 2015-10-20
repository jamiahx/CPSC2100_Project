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


import java.util.ArrayList;


package edu.utc.2015cpsc2100.ejkk;

public class ReservationSchedule
{
	
    private ArrayList<Reservation> resList;
	
    /**
     * Creates a schedule which stores reservations.
     */
    public ReservationSchedule()
    {
	resList = new ArrayList<Reservation>();
    }
	
    /**
     * Adds a reservation to the list.
     * @param res	the reservation to be added
     */
    public void add(Reservation res)
    {
	resList.add(res);
    }
	
    /**
     * Returns a reservation by searching the list for its reservation number.
     * @param resNumber	the number of the reservation to be found
     * @return result	the reservation that is returned
     */
    public Reservation get(int resNumber)
    {
	Reservation result;
	for (int i = 0; i < resList.size(); i++)
	    {
		if (resNumber == resList.get(i).resNumber)
		    {
			result = new Reservation(resList.get(i).getPickUpDate(), resList.get(i).getDropOffDate(), 
						 resList.get(i).getVehicle(), resList.get(i).getCustomer(),
						 resList.get(i).getCreditCardInfo());
		    }
	    }
	if (result == null)
	    {
		System.out.println("No reservation found.");
	    }
	return result;
    }
	
    /**
     * Removes a reservation from the list by searching for its reservation number.
     * @param resNumber	the number of the reservation to be removed
     */
    public void remove(int resNumber)
    {
	boolean tf = false;
	for (int i = 0; i < resList.size(); i++)
	    {
		if (resNumber == resList.get(i).resNumber)
		    {
			resList.remove(i);
			tf = true;
		    }
	    }
	if (!tf)
	    {
		System.out.println("No reservation found.");
	    }
    }
}
