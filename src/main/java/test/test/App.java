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
    	String password = "eng40000";
    	CloudantClient client = new CloudantClient("eyeofthetiger","eyeofthetiger",password);

    	System.out.println("Connected to Cloudant");

    	List<String> databases = client.getAllDbs();
    	System.out.println("All my databases : ");
    	for ( String db : databases ) {
    	    System.out.println(db);
    	}
        
    }
}
