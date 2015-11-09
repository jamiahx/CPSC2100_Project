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
public class UserDatabase
{
	private ArrayList<User> userList;
	public static UserDatabase Session;
	private static boolean FirstInstance = true;
	File udbFile;
	
	/**
	 * Creates a new instance of UserDatabase.
	 */
	public UserDatabase()
	{
		userList = new ArrayList<User>();
		if (FirstInstance)
		{
			FirstInstance = false;
			Session = this;
		}
	}
	
	/**
	 * Loads the Users from the given ArrayList to the UserDatabase.
	 * @param list	the ArrayList of Users that is loaded.
	 */
	public void load(ArrayList<User> list)
	{
		ArrayList<User> loadList = new ArrayList<User>();
		loadList.addAll(list);
		userList = loadList;
	}
	
	/**
	 * Loads Users to the UserDatabase from a given text file.
	 */
	public boolean load()
	{
		System.out.println("Please enter a file name from which to load your User Database.");
		boolean fileFound = false;

		try
		{
			Scanner s = new Scanner(System.in);
			String udbFileName = s.next();
			udbFile = new File(udbFileName);
			Scanner fileScanner = new Scanner(udbFile);
			while(fileScanner.hasNext())
			{
				User u = User.fromStorageString(fileScanner.nextLine());
				userList.add(u);
			}
			fileFound = true;
			return fileFound;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("No User Database file was found.");
			load();
			return fileFound;
		}
	}
	
	/**
	 * Saves the UserDatabase to the same text file given at load time.
	 * @throws FileNotFoundException
	 */
	public void save() throws FileNotFoundException
	{
		PrintWriter p = new PrintWriter(udbFile);
		for (int i = 0; i < userList.size(); i++)
		{
			p.println(userList.get(i).toStorageString());
		}
		p.close();
	}
	
	/**
	 * Adds a User to the UserDatabase
	 * @param user	the User to be added
	 */
	public void addUser(User user)
	{
		userList.add(user);
	}

	/**
	 * Deletes a User from the UserDatabase.
	 * @param username	the username of the User to be deleted.
	 */
	public void deleteUser(String username)
	{
		for (int i = 0; i < userList.size(); i++)
		{
			if (username.equals(userList.get(i).getUsername()))
			{
				userList.remove(i);
				break;
			}
		}
	}
	
	/**
	 * Searches the database for Users that match the given SearchParameter.
	 * @param sp	the SearchParameter to search for.
	 * @return results 	the ArrayList of Users that match the SearchParameter.
	 */
	public ArrayList<User> search(SearchParameter sp)
	{
		ArrayList<User> results = new ArrayList<User>();
		for (int i = 0; i < userList.size(); i++)
		{
			User u = userList.get(i);
			if (sp.field.equalsIgnoreCase("firstName"))
			{
				String firstName = u.getFirstName();
				if (sp.result.equalsIgnoreCase(firstName)) { results.add(u); }
			}
			if (sp.field.equalsIgnoreCase("lastName"))
			{
				String lastName = u.getLastName();
				if (sp.result.equalsIgnoreCase(lastName)) { results.add(u); }
			}
			if (sp.field.equalsIgnoreCase("username"))
			{
				String username = u.getUsername();
				if (sp.result.equalsIgnoreCase(username))
				{
					results.add(u);
					break;
				}
			}
			if (sp.field.equalsIgnoreCase("role"))
			{
				String role = u.getRole();
				if (sp.result.equalsIgnoreCase(role)) { results.add(u); }
			}
			if (sp.field.equalsIgnoreCase("reservations"))
			{
				int reservations = u.getRentalRecord().size();
				if (sp.result.equals(reservations+"")) { results.add(u); }
			}
		}
    	return results;
	}
	
	/**
	 * Searches the database for Users who do not match the given SearchParameter.
	 * @param sp	the SearchParameter to search for.
	 * @return results	the ArrayList of Users that do not match the SearchParameter.
	 */
	public ArrayList<User> oppositeSearch(SearchParameter sp)
	{
		ArrayList<User> results = getAll();
		ArrayList<User> oppositeResults = search(sp);
		for (int i = 0; i < oppositeResults.size(); i++)
		{
			for (int j = 0; j < results.size(); j++)
			{
				if (results.get(j).getUsername().equals(oppositeResults.get(i).getUsername()))
				{
					results.remove(j);
				}
			}
		}
		return results;
	}

	/**
	 * Searches the database for Users who match the SearchParameters from the given ArrayList.
	 * @param spList	the ArrayList of SearchParameters to search for.
	 * @return results	the ArrayList of Users who match the SearchParameters.
	 */
	public ArrayList<User> searchMultiple(ArrayList<SearchParameter> spList)
	{
		ArrayList<User> results = new ArrayList<User>();
		ArrayList<User> results2 = new ArrayList<User>();
		results = getAll();
		for (int i = 0; i < spList.size(); i++)
		{
			if (i == 0) { results = search(spList.get(i)); }
			
			results2 = results;
			UserDatabase temp = new UserDatabase();
			temp.load(results2);
			results = temp.search(spList.get(i));
		}
		return results;
	}
	
	/**
	 * Gets all the Users within the database.
	 * @return allUsers	the ArrayList of all Users within the database.
	 */
	public ArrayList<User> getAll()
	{
		ArrayList<User> allUsers = new ArrayList<User>();
		allUsers.addAll(userList);
		return allUsers;
	}
}
