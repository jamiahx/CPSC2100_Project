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
import java.util.UUID;
import java.util.Arrays;
import java.nio.charset.Charset;


public final class UUID_Generator<DigestorT extends MessageDigest>
{
    private final UUID namespaceID;

    public static UUID generateUUID( UUID namespaceID , String name )
    {	String namespaceIDString
	    =   namespaceID
	    .toString()
	    ;
	assert( namespaceIDString
		.length()
		== 16
		)
	    ;
	byte[] namespaceIDBytes
	    = new
	    byte[ 8 ]
	    ;
	for (int ii = 0; ii < 8; ii++)
	    {
		namespaceIDBytes[ii] = Byte.decode("0x" + namespaceIDString.substring( ii*4 , ii*4 + 4))
		    ;
	    }
	byte[] nameBytes
	    =   name
	    .getBytes( Charset
		       .forName( "UTF-8" )
		       )
	    ;
	byte[] hash
	    =   DigestorT
	    .getInstance( "SHA-1" )
	    .digest(   Stream
		       .concat(   Arrays
				  .stream( namespaceIDBytes )
				  ,
				  Arrays
				  .stream( nameBytes )
				  )
		       .toArray( byte[]::new)
		       )
	    ;
	hash[ 7 ]
	    =   ( hash[ 7 ]
		  & 0x0F
		  )
	    | 0x50
	    ;
	hash[ 8 ]
	    =   ( hash[ 8 ]
		  & 0x3F
		  )
	    | 0x80
	    ;
	return UUID
	    ;
    }
    public generateUUID( String name )
    {   return generateUUID( namespaceID , name )
	    ;
    }

    public UUID_Generator( UUID namespaceID )
    {
	this.namespaceID = namespaceID
	    ;
    }
    public UUID_Generator( UUID prevNamespaceID , String namespaceName )
    {
	namespaceID = generateUUID( prevNamespaceID , namespaceName )
	    ;
    }
}
