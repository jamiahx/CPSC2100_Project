/**
 * Copyright 2015 jamiahx
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

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.UUID;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


package edu.utc.2015cpsc2100.ejkk;

public final class UUID_Generator
{
    private final UUID namespaceID;

    /**
     * Generate an RFC4122 version 5 UUID
     * 
     * @param namespaceID UUID of the current namespace
     * @param name name of thing for which we are generating a UUID
     */
    public static
	< DigestorT extends MessageDigest , BBT extends ByteBuffer >
	UUID gen
	( UUID namespaceID , String name )
    {	byte[] namespaceIDBytes = 
	    BBT 
	    .allocateDirect(16)
	    .order( ByteOrder .BIG_ENDIAN )
	    .putLong( 0 ,
		      namespaceID .getMostSignificantBits()
		      )
	    .putLong( 8 ,
		      namespaceID .getLeastSignificantBits()
		      )
	    .array()
	    ;
	byte[] nameBytes =
	    BBT
	    .wrap(name
		  .getBytes( Charset
			     .forName( "UTF-8" )
			     )
		  )
	    .order( ByteOrder .BIG_ENDIAN )
	    .array()
	    ;
	byte[] concatenatedBytes = 
	    Arrays
	    .copyOf( namespaceIDBytes ,
		     namespaceIDBytes.length + nameBytes.length
		     )
	    ;
	System .arraycopy( nameBytes ,
			   0 ,
			   concatenatedBytes ,
			   namespaceIDBytes.length ,
			   nameBytes.length
			   )
	    ;
	byte[] hash =
	    DigestorT
	    .getInstance( "SHA-1" )
	    .digest( concatenatedBytes )
	    ;
	hash[ 7 ] =
	    ( hash[ 7 ]
	      & 0b0000_1111
	    )
	    | 0b0101_0000
	    ;
	hash[ 8 ] =
	    ( hash[ 8 ]
	      & 0b0011_1111
	    )
	    | 0b1000_0000
	    ;
	ByteBuffer hashBB =
	    BBT
	    .wrap(hash)
	    .order( ByteOrder .BIG_ENDIAN )
	    ;
	return new UUID(hashBB.getLong(0), hashBB.getLong(8))
	    ;
    }

    /**
     * Generate an RFC4122 version 5 UUID. Identical to
     * generateUUID(UUID namespaceID, String name) but uses 
     * an already-generated UUID of the current namespace
     * as namespaceID.
     * @param name name of thing for which we are generating a UUID
     */
    public UUID gen( String name )
    {   return gen( this.namespaceID , name )
	    ;
    }

    /**
     * Instanciates a UUID_Generator using an already-generated
     * UUID as the namespaceID
     * @param namespaceID pre-generated UUID to use as the namespace
     */
    public UUID_Generator( UUID namespaceID )
    {
	this.namespaceID = namespaceID
	    ;
    }
    /**
     * Instanciates a UUID_Generator using an auto-generated UUID
     * as the namespaceID
     *
     * @param prevNamespaceID UUID for the namespace that encloses the current one
     * @param namespaceName name of the current namespace
     */
    public UUID_Generator( UUID prevNamespaceID , String namespaceName )
    {
	namespaceID = gen( prevNamespaceID , namespaceName )
	    ;
    }
}
