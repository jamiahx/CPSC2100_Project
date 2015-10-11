
import java.util.ArrayList;
import java.io.File;

import java.io.IOException;
import java.util.Scanner;


public class VehicleTester {

	public static void main (String [] args) throws IOException{
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your file path. Do not leave a single character out. For example: /Users/emmaperez/Desktop/data(1).txt");
		String fileName = scanner.nextLine();
		System.out.println("You selected this file: " + fileName);
		
		File file = new File(fileName);

		Scanner s = new Scanner(file).useDelimiter(", ");
	
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		while(s.hasNext())
		{
			ArrayList<String> info = new ArrayList<String>();
			for (int i = 0; i < 6; i++)
			{
				info.add(s.next());
			}
			vehicles.add(new Vehicle(info.get(0), info.get(1), info.get(2), info.get(3), info.get(4), info.get(5)));
		}
		for (int i = 0; i < vehicles.size(); i++)
		{
			System.out.println(vehicles.get(i).toString());
		}

		}
}
