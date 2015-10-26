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
import java.lang.reflect.Type;
import java.util.ArrayList;


public class Searcher
{
    private ArrayList<Object> searchableList;
	
    public Searcher(ArrayList<Object> listOfObjects)
    {
    	searchableList = listOfObjects;
    }
	
    public ArrayList<Object> search(SearchParameter sp)
    {
    	ArrayList<Object> resultObjects = new ArrayList<Object>();
    	for (int i = 0; i < searchableList.size(); i++)
	    {
    		if (sp.method.invoke(searchableList.get(i)).equals(sp.result))
		    {
    			resultObjects.add(searchableList.get(i));
		    }
	    }
    	return resultObjects;
    }
    
    public ArrayList<Object> searchMultiple(ArrayList<SearchParameter> spList)
    {
    	ArrayList<Object> resultObjects = new ArrayList<Object>();
    	for (int i = 0; i < spList.size(); i++)
	    {
    		if (i == 0)
		    {
    			resultObjects.add(search(spList.get(i)));
		    }
    		else
		    {
    			ArrayList<Object> holder = new ArrayList<Object>();
        		holder.add(resultObjects);
        		Searcher temp = new Searcher(holder);
        		resultObjects.clear();
        		resultObjects.add(this.search(spList.get(i)));
		    }
	    }
	return resultObjects;
    }
}
