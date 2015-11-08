import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */

public class ReservationDatabase
{
	private ArrayList<Reservation> reservationList;
	public static ReservationDatabase Session;
	private static boolean FirstInstance = true;
	File rdbFile;
	
	/**
	 * Creates a new instance of ReservationDatabase.
	 */
	public ReservationDatabase()
	{
		reservationList = new ArrayList<Reservation>();
		if (FirstInstance)
		{
			FirstInstance = false;
			Session = this;
		}
	}
	
	/**
	 * Loads an ArrayList of Reservations to the ReservationDatabase.
	 * @param list	the list of Reservations that is loaded.
	 */
	public void load(ArrayList<Reservation> list)
	{
		ArrayList<Reservation> loadList = new ArrayList<Reservation>();
		loadList.addAll(list);
		reservationList = loadList;
	}
	
	/**
	 * Loads Reservations to the ReservationDatabase from a given file
	 * @return	fileFound	the boolean representing whether or not the given file could be found.
	 */
	public boolean load()
	{
		System.out.println("Please enter a file name from which to load your Reservation Database.");
		boolean fileFound = false;

		try
		{
			Scanner s = new Scanner(System.in);
			String rdbFileName = s.next();
			rdbFile = new File(rdbFileName);
			Scanner fileScanner = new Scanner(rdbFile);
			while(fileScanner.hasNextLine())
			{
				String res = fileScanner.next();
				String reservation = res + fileScanner.nextLine();
				Reservation r = Reservation.fromStorageString(reservation);
				reservationList.add(r);
				r.getCustomer().addToRecord(r);
			}
			fileFound = true;
			return fileFound;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("No Reservation Database file was found.");
			load();
			return fileFound;
		}
	}
	
	/**
	 * Saves the ReservationDatabase to the original file that was given for loading.
	 * @throws FileNotFoundException
	 */
	public void save() throws FileNotFoundException
	{
		PrintWriter p = new PrintWriter(rdbFile);
		for (int i = 0; i < reservationList.size(); i++)
		{
			p.println(reservationList.get(i).toStorageString());
		}
		p.close();
	}
	
	/**
	 * Adds a Reservation object to the ReservationDatabase
	 * @param r	the Reservation object that is added.
	 */
	public void addReservation(Reservation r)
	{
		reservationList.add(r);
	}
	
	/**
	 * Removes a Reservation object from the ReservationDatabase
	 * @param resID	the ID of the Reservation to be removed.
	 * @return b	the boolean that indicates if the reservation was successfully removed.
	 */
	public boolean removeReservation(String resID)
	{
		boolean b = false;
		SearchParameter sp = new SearchParameter("resId", resID);
		ArrayList<Reservation> result = search(sp);
		if (result.size() != 0)
		{
			reservationList.remove(result.get(0));
			b = true;
		}
		return b;
	}

	/**
	 * Searches the ReservationDatabase for the given SearchParameter.
	 * @param sp	the SearchParameter to search for.
	 * @return results	the ArrayList of Reservation objects that match the SearchParameter.
	 */
	public ArrayList<Reservation> search(SearchParameter sp)
	{
		ArrayList<Reservation> results = new ArrayList<Reservation>();
		for (int i = 0; i < reservationList.size(); i++)
		{
			Reservation r = reservationList.get(i);
			if (sp.field.equalsIgnoreCase("pickUpDate"))
			{
				String pickUpDate = r.getPickUpDate().toShortString().trim();
				if (sp.result.equalsIgnoreCase(pickUpDate)) { results.add(r); }
			}
			if (sp.field.equalsIgnoreCase("dropOffDate"))
			{
				String dropOffDate = r.getDropOffDate().toShortString().trim();
				if (sp.result.equalsIgnoreCase(dropOffDate)) { results.add(r); }
			}
			if (sp.field.equalsIgnoreCase("customerUsername"))
			{
				String customerUsername = r.getCustomer().getUsername().trim();
				if (sp.result.equalsIgnoreCase(customerUsername)) { results.add(r); }
			}
			if (sp.field.equalsIgnoreCase("vin"))
			{
				String vin = r.getVehicle().getVIN().trim();
				if (sp.result.equalsIgnoreCase(vin)) { results.add(r); }
			}
			if (sp.field.equalsIgnoreCase("resID"))
			{
				String resID = r.getResID();
				if (sp.result.equalsIgnoreCase(resID)) { results.add(r); }
			}
		}
    	return results;
	}

	/**
	 * Searches the ReservationDatabase for the SearchParameters in the given ArrayList.
	 * @param spList	the ArrayList of SearchParameters to search for.
	 * @return results	the ArrayList of Reservation objects that match the SearchParameters in spList.
	 */
	public ArrayList<Reservation> searchMultiple(ArrayList<SearchParameter> spList)
	{
		ArrayList<Reservation> results = new ArrayList<Reservation>();
		ArrayList<Reservation> results2 = new ArrayList<Reservation>();
		results = getAll();
		for (int i = 0; i < spList.size(); i++)
		{
			if (i == 0) { results = search(spList.get(i)); }
			
			results2 = results;
			ReservationDatabase temp = new ReservationDatabase();
			temp.load(results2);
			results = temp.search(spList.get(i));
		}
		return results;
	}
	
	/**
	 * Gets all of the Reservation objects in the ReservationDatabase.
	 * @return allReservations	the ArrayList of all the Reservations.
	 */
	public ArrayList<Reservation> getAll()
	{
		ArrayList<Reservation> allReservations = new ArrayList<Reservation>();
		allReservations.addAll(reservationList);
		return allReservations;
	}
	
	/**
	 * Searches the ReservationDatabase and the VehicleDatabase to find Vehicles that are available for the given period.
	 * @param pickUpDate	the starting date of the period.
	 * @param dropOffDate	the ending date of the period.
	 * @return results	the ArrayList of Vehicles that are available during the period.
	 */
	public ArrayList<Vehicle> basicVehicleAvailabilitySearch(String pickUpDate, String dropOffDate)
	{
		Vehicle notAvailable = null;
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (int i = 0; i < reservationList.size(); i++)
		{
			if (reservationList.get(i).getPickUpDate().isBefore(Date.fromShortString(dropOffDate)) && Date.fromShortString(pickUpDate).isBefore(reservationList.get(i).getDropOffDate()))
			{
				notAvailable = reservationList.get(i).getVehicle();
				SearchParameter sp = new SearchParameter("vin", notAvailable.getVIN());
				if (i == 0)
				{
					results = VehicleDatabase.Session.oppositeSearch(sp);
				}
				else
				{
					VehicleDatabase vdb = new VehicleDatabase();
					vdb.load(results);
					results = vdb.oppositeSearch(sp);
				}
			}
		}
		if (results.size() == 0)
		{
			results = VehicleDatabase.Session.getAll();
		}
		return results;
	}
	
	/**
	 * Searches the ReservationDatabase and VehicleDatabase to find Vehicles that are both available for the given period and match the SearchParameters in the given ArrayList.
	 * @param pickUpDate	the starting date of the period.
	 * @param dropOffDate	the ending date of the period.
	 * @param spList	the ArrayList of SearchParameters to search for.
	 * @return results	the ArrayList of Vehicles that match the SearchParameters in spList and are available in the period.
	 */
	public ArrayList<Vehicle> advancedVehicleAvailabilitySearch(String pickUpDate, String dropOffDate, ArrayList<SearchParameter> spList)
	{
		VehicleDatabase vdb = new VehicleDatabase();
		vdb.load(basicVehicleAvailabilitySearch(pickUpDate, dropOffDate));
		ArrayList<Vehicle> results = vdb.searchMultiple(spList);
		return results;
	}

	/**
	 * Changes the Vehicle associated with a Reservation.
	 * @param resID	the ID of the Reservation that must be changed.
	 * @param v	the new Vehicle to be set on the Reservation.
	 */
	public void vehicleChange(String resID, Vehicle v)
	{
		for (int i = 0; i < reservationList.size(); i++)
		{
			if (reservationList.get(i).getResID().equals(resID))
			{
				reservationList.get(i).setVehicle(v);
			}
		}
	}
}
