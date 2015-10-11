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
