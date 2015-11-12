package edu.utc._2015cpsc2100.ejkk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import javax.naming.AuthenticationException;
import javax.naming.NameAlreadyBoundException;

public class MainTester {

	public static void main(String [] args) throws AuthenticationException, NameAlreadyBoundException, IOException
	{
		
	
		Scanner scanner = new Scanner(System.in);
		System.out.println("(1) login");
		System.out.println("(2) create account");
		System.out.println("(3) rent a car");
		
		if (scanner.nextLine().equals("1"))
		{
			System.out.println("Please enter username");
			String username = scanner.nextLine();
			System.out.println("Please enter password");
			PassHash password = new PassHash(scanner.nextLine());
			//PassHash storedPass;
			PassHash storedPass = null;
			if (!password.equals(storedPass))
			{
				throw new AuthenticationException("Incorrect Login credentials");
			}

		} else if ( scanner.nextLine().equals("2"))
			{
				System.out.println("Please enter username");
				String username = scanner.nextLine();
		
				System.out.println("Please enter password");
				//String password = scanner.nextLine();
				PassHash password = new PassHash(scanner.nextLine());
				System.out.println("First Name:");
				String firstName = scanner.nextLine();
				System.out.println("Last Name:");
				String lastName = scanner.nextLine();
				User u = new User(username, new Name(firstName, lastName));
				
				
				System.out.println("Email:");
				String email = scanner.nextLine();
				
			}
		
		 else if ( scanner.nextLine().equals("3"))
			{
				ArrayList<Vehicle> vehicles = VehicleTester.main(null);
				
				
				System.out.println("Enter the vehicle make you wish to rent");
				String make = scanner.nextLine();
				System.out.println("Enter the vehicle model you wish to rent");
				String model = scanner.nextLine();
				// this is where we should search
				
				
		
			}
	

		
			}
	}

