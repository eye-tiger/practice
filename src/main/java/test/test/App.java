package test.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA_2_3.portable.InputStream;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.DbInfo;
import com.cloudant.client.api.model.Response;
import com.google.gson.JsonObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//logins into the cloudant db account
    	String password = System.getenv("password");
    	String username = System.getenv("account");
    	CloudantClient client = new CloudantClient(username, username, password);

    	//creates a new database
    	client.createDB("db_java");
    	
    	//selects database to be used
    	Database db = client.database("practice", false);
    	
    	//Checks if a doc with id test-java is already there
    	if( !db.contains("test-java1") ){
	    	//inserts data into the db
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("_id", "test-java1");
	    	map.put("title", "it works");
	    	Response response = db.save(map);
    	}
    	
    	
    	
    	//retrieves data from the db
    	Map<String, Object> in = db.find( Map.class ,"test1");
    	System.out.println( in.get("_id") + " " + in.get("age") + " " + in.get("name") );
    }
}
