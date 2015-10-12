import java.security.Principle;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.naming.NameAlreadyBoundException;
import Settings;


/// How to instantiate a Customer
// import javax.security.auth.Subject;
// import java.util.Vector;
// new Subject(false, new Vector<Principle> {new User(username, Name(firstName, lastName)), new Customer()}, {}, new Vector<PassHash> {PassHash(passHash)});


public final class Name
{
    private String firstName;
    private String lastName;
    public String getFirstName()
    {
	return firstName;
    }
    public String getLastName()
    {	
	return lastName;
    }
    public Name(String firstName, String lastName)
    {
	this.firstName = firstName;
	this.lastName = lastName;
    }
}

public final class User extends Principle
{
    private final String username;
    private Name name; /// Hide me behind a GuardObject
    private final UUID uuid;
    private static final UUID_Generator = new UUID_Generator(UUID_Generator.generateUUID(new UUID(),Settings.getSystemName()), "User");
    private static ConcurrentHashMap<UUID, User> userDB;

    public String getUsername()
    {
	return username;
    }
    public getName(){return getUsername();}
    public UUID getUUID()
    {
	return uuid;
    }

    public boolean equals(Object another)
    {
	return (this.getUUID().equals(another.getUUID()));
    }

    public User(String username, Name name) throws NameAlreadyBoundException
    {
	this.name = name;
	this.username = username;
	uuid = UUID.randomUUID();
	if (userDB.containsKey(uuid))
	    throw NameAlreadyBoundException;

	userDB.put(uuid, this);
    }
}

public final class Employee extends Principle{}

public final class Manager extends Principle{}

public final class Customer extends Principle{}

public final class PassHash
{
    private byte[] passHash;
    public byte[] getPassHash()
    {
	return passHash;
    }
    public PassHash(byte[] passHash)
    {
	this.passHash = passHash;
    }
}
