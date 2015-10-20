import java.lang.reflect.Method;
import java.lang.reflect.Type;

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
