
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.time.Year;


public class VehicleTester {

    public static void main (String [] args) throws IOException{
		
	Scanner scanner = new Scanner(System.in);
	System.out.println("Please enter your file path. Do not leave a single character out. For example: /Users/emmaperez/Desktop/data(1).txt");
	File file = new File(scanner.nextLine());
	System.out.println("You selected this file: " + file.getName());


	Scanner s = new Scanner(file).useDelimiter(",");
	
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		
	while(s.hasNext())
	    {
		vehicles.add(new Vehicle(s.next(), s.next(), Year.parse(s.next()), s.next(), Integer.valueOf(s.next()), s.next()));
	    }
	for (int i = 0; i < vehicles.size(); i++)
	    {
		System.out.println(vehicles.get(i).toString());
	    }

    }
}
