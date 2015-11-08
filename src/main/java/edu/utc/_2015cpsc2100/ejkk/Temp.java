
public class Temp
{
	private int myInt;
	private String myString;
	private boolean myBool;
	
	public int getInt() { return myInt; }
	public String getString() { return myString; }
	public boolean getBool() { return myBool; }
	
	public Temp(int i, String s, boolean b)
	{
		myInt = i;
		myString = s;
		myBool = b;
	}
	
	public String toString()
	{
		String s = "";
		s = s + myInt + " " + myString + " " + myBool;
		return s;
	}
}
