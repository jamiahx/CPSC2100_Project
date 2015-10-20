import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

package edu.utc.2015cpsc2100.ejkk;

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
        		resultObjects.add(Searcher.search(spList.get(i)));
    		}
    	}
    }
}
