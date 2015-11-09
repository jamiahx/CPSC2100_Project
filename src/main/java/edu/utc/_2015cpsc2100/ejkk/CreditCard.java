/**
 * Copyright 2015 Emma Perez, Jeremiah Gaertner, Kate Siprelle, Kaleb Sanchez
 * emma11.gene@gmail.com
 * jamiahx@gmail.com
 * kalebsanchez23@yahoo.com
 * ksiprelle@gmail.com
 * 
 * This file is a part of ORS.
 *
 * ORS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * ORS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ORS.  If not, see <http://www.gnu.org/licenses/>.
 */


import java.util.Calendar;


/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */
public class CreditCard
{
	public String number;
	public String provider;
	public int expMonth;
	public int expYear;
	public int cvc;
	public boolean verified;
	
	public String getNumber() { return "************" + number.substring(12); }
	public String getProvider() { return provider; }
	public String getExpDate() { return (expMonth + "/" + (expYear+"").substring(2)); }
	public String getcvc() { return cvc + ""; }
	
	/**
	 * Creates an instance of CreditCard.
	 * @param number	The credit card number.
	 * @param provider	The credit card provider.
	 * @param expDate	The expiration date on the card.
	 * @param cvc		The card's cvc.
	 */
	public CreditCard(String number, String provider, String expDate, String cvc)
	{
		this.number = number;
		this.provider = provider;
		this.expMonth = Integer.valueOf(expDate.substring(0, expDate.indexOf("/")));
		this.expYear = Integer.valueOf("20" + expDate.substring(expDate.indexOf("/") + 1));
		this.cvc = Integer.valueOf(cvc);
	}
	
	/**
	 * Verifies the information for a CreditCard object.
	 * @return verified		The boolean representing the verification status of the card.
	 */
	public boolean verify()
	{
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		
		verified = false;
		boolean b1 = (number.length() == 16);
		boolean b2 = (expYear > currentYear);
		boolean b3 = (expYear == currentYear);
		boolean b4 = (expMonth > currentMonth);
		if ((b1 && b2) || (b1 && b3 && b4)) { verified = true; }
		return verified;
	}
	
	/**
	 * Bills the credit card for a certain amount.
	 * @param billAmount	The amount for which the card is billed.
	 * @return billed	The boolean representing whether or not the card has been billed.
	 */
	public boolean bill(double billAmount)
	{
		boolean billed = false;
		if (verified) { billed = true; }
		return billed;
	}
	
	/**
	 * Creates a String which represents a CreditCard instance that can be stored in the text file databases.
	 * @return	s	The String representing the card.
	 */
	public String toStorageString()
	{
		String s = "";
		s = s + number + ", " + provider + ", " + expMonth + ", " + expYear + ", " + cvc;
		return s;
	}
	
	/**
	 * Creates a new instance of CreditCard from a String.
	 * @param c	The String representing the card
	 * @return card	The CreditCard instance which is translated from the String c.
	 */
	public static CreditCard fromStorageString(String c)
	{
		CreditCard card = null;
    	String number;
    	String provider;
    	String expMonth;
    	String expYear;
    	String cvc;
    	
    	int comma = c.indexOf(",");
    	int comma2 = c.indexOf(",", comma + 1);
    	int comma3 = c.indexOf(",", comma2 + 1);
    	int comma4 = c.indexOf(",", comma3 + 1);
    	
    	number = c.substring(0, comma);
    	provider = c.substring(comma + 2, comma2);
    	expMonth = c.substring(comma2 + 2, comma3);
    	expYear = c.substring(comma3 + 2, comma4);
    	cvc = c.substring(comma4 + 2);
    	
    	card = new CreditCard(number, provider, expMonth + "/" + expYear, cvc);
    	return card;
	}
}
