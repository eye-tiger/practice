package test.test;

import java.util.List;

import com.cloudant.client.api.CloudantClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String password = System.getenv("password");
    	String username = System.getenv("account");
    	CloudantClient client = new CloudantClient(username, username, password);

    	System.out.println("Connected to Cloudant");

    	List<String> databases = client.getAllDbs();
    	System.out.println("All my databases : ");
    	for ( String db : databases ) {
    	    System.out.println(db);
    	}
        
    }
}
