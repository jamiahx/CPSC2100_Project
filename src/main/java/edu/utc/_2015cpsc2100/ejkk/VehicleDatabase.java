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

public class VehicleDatabase
{
	private ArrayList<Vehicle> vehicleList;
	public static VehicleDatabase Session;
	private static boolean FirstInstance = true;
	private File vdbFile;
	
	/**
	 * Creates a new instance of VehicleDatabase.
	 */
	public VehicleDatabase()
	{
		vehicleList = new ArrayList<Vehicle>();
		if (FirstInstance)
		{
			FirstInstance = false;
			Session = this;
		}
	}
	
	/**
	 * Loads Vehicles to the database from the given ArrayList.
	 * @param list	the ArrayList of Vehicles to load.
	 */
	public void load(ArrayList<Vehicle> list)
	{
		ArrayList<Vehicle> loadList = new ArrayList<Vehicle>();
		loadList.addAll(list);
		vehicleList = loadList;
	}
	
	/**
	 * Loads Vehicles to the database from a given text file.
	 * @return fileFound	the boolean indicating whether or not the text file was found.
	 */
	public boolean load()
	{
		System.out.println("Please enter a file name from which to load your Vehicle Database.");
		boolean fileFound = false;

		try
		{
			Scanner s = new Scanner(System.in);
			String vdbFileName = s.next();
			vdbFile = new File(vdbFileName);
			Scanner fileScanner = new Scanner(vdbFile);
			while(fileScanner.hasNext())
			{
				Vehicle v = Vehicle.fromStorageString(fileScanner.nextLine());
				vehicleList.add(v);
			}
			fileFound = true;
			return fileFound;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("No Vehicle Database file was found.");
			load();
			return fileFound;
		}
	}
	
	/**
	 * Saves the Vehicles in the database to the text file that was given at load time.
	 * @throws FileNotFoundException
	 */
	public void save() throws FileNotFoundException
	{
		PrintWriter p = new PrintWriter(vdbFile);
		for (int i = 0; i < vehicleList.size(); i++)
		{
			p.println(vehicleList.get(i).toStorageString());
		}
		p.close();
	}
	
	/**
	 * Adds the given Vehicle to the database.
	 * @param vehicle	the Vehicle to be added.
	 */
	public void addVehicle(Vehicle vehicle)
	{
		vehicleList.add(vehicle);
	}
	
	/**
	 * Deletes a Vehicle from the database.
	 * @param vin	the ID of the Vehicle to be deleted.
	 */
	public void deleteVehicle(String vin)
	{
		for (int i = 0; i < vehicleList.size(); i++)
		{
			if (vehicleList.get(i).getVIN().equalsIgnoreCase(vin))
			{
				vehicleList.remove(i);
				break;
			}
		}
	}
	
	/**
	 * Searches the database for Vehicles that match the given SearchParameter.
	 * @param sp	the SearchParameter to be searched for.
	 * @return results	the ArrayList of Vehicles that match the search.
	 */
	public ArrayList<Vehicle> search(SearchParameter sp)
	{
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
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
	public ArrayList<Vehicle> oppositeSearch(SearchParameter sp)
	{
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		ArrayList<Vehicle> oppositeResults = search(sp);
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
	public ArrayList<Vehicle> searchMultiple(ArrayList<SearchParameter> spList)
	{
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		ArrayList<Vehicle> results2 = new ArrayList<Vehicle>();
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
	public ArrayList<Vehicle> getAll()
	{
		ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
		allVehicles.addAll(vehicleList);
		return allVehicles;
	}
	
	/**
	 * Makes a given ArrayLis of Vehicles into a viewable list.
	 * @param vehicles	the ArrayList to be converted.
	 * @return String the String representing the list.
	 */
	public static String makeOptionList(ArrayList<Vehicle> vehicles)
	{
		String s = "";
		for (int i = 0; i < vehicles.size(); i++)
		{
			s = s + "(" + (i+1) + ") " + vehicles.get(i).toShortString() + "\n";
		}
		return s;
	}
}
