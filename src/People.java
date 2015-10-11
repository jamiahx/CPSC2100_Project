

public class People {

	protected final String firstName;
	protected final String lastName;
	protected final String userName;
	protected String passHash;


	public People(String firstName , String lastName, String userName, String password)
	{
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passHash = password;
	}


	public String getFirstName()
	{
		
		return firstName;
	}
	
	public String getLastName()
	{
	
		return lastName;
	}
	
	public String getUserName()
	{
		
		return userName;
	}
	
	public String getPassHash()
	{
		return passHash;
	}
	
	public void setPassHash(String passHash)
	{
		this.passHash = passHash;
	}

	
	
}




