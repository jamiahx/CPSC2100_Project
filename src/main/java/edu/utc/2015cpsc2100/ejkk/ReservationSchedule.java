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
