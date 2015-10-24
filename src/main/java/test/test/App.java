package test.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA_2_3.portable.InputStream;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.DbInfo;
import com.cloudant.client.api.model.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
    	
    	JsonObject testing = new JsonObject();
    	
    	//retrieves data from the db
    	testing = db.find( JsonObject.class ,"212352755");
    	
    	//retrieves the timetable array
    	JsonArray hap = testing.get("schedule").getAsJsonArray();
    	
    	//adds new element to the array
    	hap.add(new JsonPrimitive("testing one two three") ); 
    	
    	//updates element in the timetable array for wednesday
    	hap.get(2).getAsJsonObject().addProperty("rm322", "19:00 java");
    	
    	//sends updates to the db page
    	testing.add("schedule", hap);
    	Response res = db.update( testing );
    	
    	System.out.println(testing.get("schedule").getAsJsonArray().get(0).toString());
    }
}
