/**
 * Copyright 2015 Emma Perez, jamiahx, Kate Siprelle, Kaleb Sanchez
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

import java.util.Scanner;


public class Settings //TOOK OUT FINAL KEYWORD
{
    private static Scanner SettingsSource = new Scanner("settings.txt");

    /// these need to be put behind a guard object
    private static String SystemName = new Scanner("System_Name.txt"); //.next();
    /// Need to make a proper parser or method of configuring


    public static String getSystemName()
    {
	return SystemName;
    }
}
