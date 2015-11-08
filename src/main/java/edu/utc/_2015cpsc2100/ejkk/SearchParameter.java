/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */

public class SearchParameter
{
    public String field;
    public String result;
	
    /**
     * Creates a SearchParamter object which holds the field to search for on a list of objects and the desired result from the search
     * @param field		The field which will be searched for on a list of objects
     * @param result	The desired result from the search
     */
    public SearchParameter(String field, String result)
    {
    	this.field = field;
    	this.result = result;
    }
	
}
