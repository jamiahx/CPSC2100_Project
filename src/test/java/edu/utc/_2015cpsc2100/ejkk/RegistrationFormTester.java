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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class RegistrationFormTester {

	public static void main (String [] args) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your file path. Do not leave a single character out. For example: /Users/emmaperez/Desktop/data(1).txt");
		String fileName = scanner.nextLine();
		System.out.println("You selected this file: " + fileName);
		
		File file = new File(fileName);

		Scanner s = new Scanner(file).useDelimiter(", ");
	
		ArrayList<RegistrationForm> form = new ArrayList<RegistrationForm>();
		
		while(s.hasNext())
		{
			ArrayList<String> info = new ArrayList<String>();
			for (int i = 0; i < 7; i++)
			{
				info.add(s.next());
			}
			form.add(new RegistrationForm(info.get(0), info.get(1), info.get(2), info.get(3), info.get(4), info.get(5), info.get(6)));
		}
		for (int i = 0; i < form.size(); i++)
		{
			System.out.println(form.get(i).toString());
		}

		}

}