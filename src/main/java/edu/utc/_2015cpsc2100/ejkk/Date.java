import java.util.Calendar;

/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */

public class Date
{
	private int day;
	private int month;
	private int year;
	private String months[];
	private int daysInEachMonth[];
	
	public static Date currentDate = new Date(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH)+1, Calendar.getInstance().get(Calendar.YEAR));;
	
	public int getDay() { return day; }
	public int getMonth() { return month; }
	public int getYear() { return year; }
	
	/**
	 * Creates a Date object which stores temporal info and does basic calculations of days.
	 * @param day	the day of the month.
	 * @param month the month of the year.
	 * @param year the year of the date.
	 */
	public Date(int day, int month, int year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
		months = new String[] {"January", "February", "March", "April", "May", "June", 
				"July", "August", "September", "October", "November", "December"};
		daysInEachMonth = (year % 4 == 0) ? new int[] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
		: new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	}

	/**
	 * Returns the number of days between two dates.
	 * The implicit parameter must be a date which falls before the explicit parameter date.
	 * @param date the latter date to which the number of days must be counted.
	 * @return days the number of days between the former date and the latter date.
	 */
	public int daysUntil(Date date)
	{
		int days = 0;
		if (this.isBefore(date))
		{
			for (int i = year; i < date.getYear(); i++)
			{
				Date temp = new Date(31, 12, i);
				days += temp.daysIntoYear();
			}
			days = days - daysIntoYear() + date.daysIntoYear();
		}
		return days + 1;
	}
	
	/**
	 * Returns the number of days in the year up to the date.
	 * @return days	the number of days into the year.
	 */
	public int daysIntoYear()
	{
		int days = 0;
		for (int i = 0; i < month-1; i++)
		{
			days = days + daysInEachMonth[i];
		}
		days += day;
		return days;
	}
	
	/**
	 * Checks if the implicit Date parameter is before the explicit Date parameter.
	 * @param date	The Date object that is checked against the implicit Date object.
	 * @return b	True if the implicit parameter is before the explicit parameter.
	 */
	public boolean isBefore(Date date)
	{
		boolean b = false;
		if (this.year < date.getYear()) { b = true; }
		if (this.year == date.getYear() && this.daysIntoYear() < date.daysIntoYear()) { b = true; }
		return b;
	}
	
	/**
	 * Checks that the implicit Date parameter is not before the current date.
	 * @return b	True if the Date parameter is before the current date.
	 */
	public boolean checkTimeTravel()
	{
		boolean b = false;
		b = this.isBefore(currentDate);
		return b;
	}
	
	/**
	 * Returns a String to describe a date in the form "Month Day, Year".
	 * @return m	the String describing the Date.
	 */
	public String toString()
	{
		String m = "";
		for (int i = 0; i < 12; i++)
		{
			if (month == (i + 1))
			{
				m = months[i];
			}
		}
		m = m + " "  + day + ", " + year;
		return m;
	}
	
	/**
	 * Returns a String to describe a date in the form "Month/Day/Year".
	 * @return m	The String describing the Date.
	 */
	public String toShortString()
	{
		String m = "";
		m = m + month + "/" + day + "/" + year;
		return m;
	}
	
	/**
	 * "Translates" a String that describes a Date object in the form "Month/Day/Year" into a new Date object.
	 * @param date	The String describing the Date object in the form "Month/Day/Year".
	 * @return d	The translated Date object.
	 */
	public static Date fromShortString(String date)
	{
		Date d;
		int day;
		int month;
		int year;
		int dash = date.indexOf("/");
		month = Integer.valueOf(date.substring(0, dash));
		int dash2 = date.indexOf("/", dash + 1);
		day = Integer.valueOf(date.substring(dash + 1, dash2));
		year = Integer.valueOf(date.substring(dash2 + 1));
		d = new Date(day, month, year);
		return d;
	}
}