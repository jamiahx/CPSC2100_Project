import java.lang.reflect.Method;
import java.lang.reflect.Type;


package edu.utc.2015cpsc2100.ejkk;

public class SearchParameter
{
    public Method method;
    public Type result;
	
    public SearchParameter(Method method, Type result)
    {
	this.method = method;
	this.result = result;
    }
	
}
