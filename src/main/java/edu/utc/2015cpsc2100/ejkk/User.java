/**
 * Copyright 2015 Emma Perez, jamiahx
 * jamiahx@gmail.com
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

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.naming.NameAlreadyBoundException;
import java.nio.file.attribute.UserPrincipal;


package edu.utc.2015cpsc2100.ejkk;

public final class User extends UserPrincipal
{
    private final String username;
    private Name name; /// Hide me behind a GuardObject
    private static final UUID_Generator user_UUID_generator = new UUID_Generator(UUID_Generator.gen(new UUID(0,0), Settings.getSystemName()), "User");
    private static ConcurrentHashMap<UUID, User> userDB;

    public String getUsername()
    {
	return username;
    }
    public String getName(){return getUsername();}
    public UUID getUUID()
    {
	return user_UUID_generator.gen(username);
    }

    public boolean equals(User another)
    {
	return (this.getUUID().equals(another.getUUID()));
    }

    public User(String username, Name name) throws NameAlreadyBoundException
    {
	this.name = name;
	this.username = username;
	UUID uuid = user_UUID_generator.gen(this.username);
	if (userDB.containsKey(uuid))
	    throw new NameAlreadyBoundException("Username already taken");

	userDB.put(uuid, this);
    }
}
