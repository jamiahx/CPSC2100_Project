/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */

public class Customer extends User
{
	/**
	 * Creates an instance of Customer.
	 * @param rf The registration form from which the customer's information is retrieved.
	 */
	public Customer(RegistrationForm rf)
	{
		super(rf);
		super.setRole("Customer");
	}
}
