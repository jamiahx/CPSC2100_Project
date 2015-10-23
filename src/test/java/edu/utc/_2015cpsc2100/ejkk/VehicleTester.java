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


package edu.utc._2015cpsc2100.ejkk;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.utc._2015cpsc2100.ejkk.Vehicle;

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
