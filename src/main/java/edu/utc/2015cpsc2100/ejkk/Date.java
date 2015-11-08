/**
a * Copyright 2015 Emma Perez, jamiahx, Kate Siprelle, Kaleb Sanchez
 * jamiahx@gmail.com
 * kalebsanchez23@yahoo.com
 * ksiprelle@gmail.com
 * 
 * This file is a part of CPSC2100_ORS.
 *
 * CPSC2100_ORS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * CPSC2100_ORS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with CPSC2100_ORS.  If not, see <http://www.gnu.org/licenses/>.
 */


package edu.utc._2015cpsc2100.ejkk;


public class Date
{
    private int day;
    private int month;
    private int year;
    private String months[];
    private int daysInEachMonth[];
	
    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
	
    /**
     * Creates a Date object which stores temporal info and does basic calculations of days.
     * @param day	the day of the month
     * @param month the month of the year
     * @param year the year of the date
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
     * @param date the latter date to which the number of days must be counted
     * @return days the number of days between the former date and the latter date
     */
    public int daysUntil(Date date)
    {
	int days = 0;
	for (int i = year; i < date.getYear(); i++)
	    {
		Date temp = new Date(31, 12, i);
		days += temp.daysIntoYear();
	    }
	days = days - daysIntoYear() + date.daysIntoYear();
	return days;
    }
	
    /**
     * Returns the number of days in the year up to the date.
     * @return days	the number of days into the year
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
     * Returns a string to describe a date in the form "Month Day, Year".
     * @return s	the string describing the date
     */
    public String toString()
    {
	String s = "";
	for (int i = 0; i < 12; i++)
	    {
		if (month == (i + 1))
		    {
			s = months[i];
		    }
	    }
	s = s + " "  + day + ", " + year;
	return s;
    }
	
    public String toShortString()
    {
	String s = "";
	s = s + month + "/" + day + "/" + year;
	return s;
    }
}
