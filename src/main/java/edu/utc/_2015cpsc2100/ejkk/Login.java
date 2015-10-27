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
 *
 * CPSC2100_ORS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CPSC2100_ORS.  If not, see <http://www.gnu.org/licenses/>.
 */


package edu.utc._2015cpsc2100.ejkk;

import javax.security.auth.login.LoginContext;
import com.sun.security.auth.callback.TextCallbackHandler;


public final class Login
{
    public static final void main(String... args)
    {
	try {
	    LoginContext login
		= new LoginContext
		( System
		  .getProperty("edu.utc._2015cpsc2100.ejkk.ORS.LoginConf"),
		  new TextCallbackHandler());
	}
	catch (LoginException failure)
	    {
		System.err.println("LoginContext creation failed.");
		System.err.println(failure.getMessage());
		System.exit(-1);
	    }
	catch (SecurityException failure)
	    {
		System.err.println("LoginContext creation failed.");
		System.err.println(failure.getMessage());
		System.exit(-1);
	    }
	try {
	    login.login();
	}
	catch (LoginException failure)
	    {
		System.err.println("Authentication failed.");
		System.err.println(failure.getMessage());
		System.exit(-1);
	    }

	System.out.println("Authentication succeeded.");
    }
}
