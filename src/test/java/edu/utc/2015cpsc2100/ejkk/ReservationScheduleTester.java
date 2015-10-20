import java.lang.reflect.Method;
import java.util.ArrayList;


package edu.utc.2015cpsc2100.ejkk;

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
