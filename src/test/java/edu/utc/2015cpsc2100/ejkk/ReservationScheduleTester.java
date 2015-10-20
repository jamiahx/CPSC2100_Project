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

import java.lang.reflect.Method;
import java.util.ArrayList;


public class ReservationScheduleTester
{

    public static void main(String[] args)
    {
	ArrayList<Object> test = new ArrayList<Object>();
	test.add(new Temp(1, "A", true));
	test.add(new Temp(2, "B", false));
	test.add(new Temp(3, "C", true));
	test.add(new Temp(4, "D", true));
		
	Searcher mySearch = new Searcher(test);
	ArrayList<Object> results = new ArrayList<Object>();
	Method myMethod = test.get(0).class.getMethod("getInt", null);
	results = mySearch.search(new SearchParameter(myMethod, 3));
	for (int i = 0; i < results.size(); i++)
	    {
		System.out.println(results.get(i).toString());
	    }
		
    }

}
