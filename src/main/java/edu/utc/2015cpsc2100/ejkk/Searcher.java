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
}
