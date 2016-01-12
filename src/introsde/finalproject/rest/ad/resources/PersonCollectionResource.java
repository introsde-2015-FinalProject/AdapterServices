package introsde.finalproject.rest.ad.resources;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.ClientConfig;
import org.h2.util.IOUtils;
import org.json.JSONObject;

import twitter4j.*;
import twitter4j.auth.AccessToken;
 
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;






/**
 * This class is a collection Resource for the Person
 * and provides some methods to perform GET and POST requests.
 * 
 * GET
 * getCount()
 * getPersonsBrowser(@QueryParam("measureType") String measureName,@QueryParam("min") Double min,@QueryParam("max") Double max)
 * 
 * POST
 * newPerson(Person person)
 * 
 * 
 * 
 * 
 * @author Carlo Nicolo'
 *
 */
@SuppressWarnings("deprecation")
@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/person")
public class PersonCollectionResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    private WebTarget service;
    
    
    /**
	 * This is method allow to create an object for this class
	 * with the param connection string that represents the address of the
	 * server that we want interact with
	 * 
	 * @param conn is the connection string representing the server that we want contact
	 */
	public PersonCollectionResource(){
		//String connCarlo = "https://enigmatic-sierra-2066.herokuapp.com/sdelab";
		
		/*
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURIChuckNorris());
		this.service = service;
		System.out.println("Inside the constructor");
		*/
		
	}
    
	/**
	 * This method is used to create URI used to connect the cliet to the server
	 * passed as String in the param
	 * 
	 * @param conn the connection string of the server
	 * @return 
	 */
	private static URI getBaseURIChuckNorris() {
		String conn = "http://api.icndb.com";
		return UriBuilder.fromUri(conn).build(); //my server
		//return UriBuilder.fromUri("https://peaceful-hamlet-5616.herokuapp.com/sdelab").build(); //Andrea
	}
	
	/*
	private static URI getBaseURITwitter() {
		String conn = "http://api.icndb.com";
		return UriBuilder.fromUri(conn).build(); //my server
		//return UriBuilder.fromUri("https://peaceful-hamlet-5616.herokuapp.com/sdelab").build(); //Andrea
	}
	*/
	
	/**
	 * This method is used to create URI used to connect the cliet to the server
	 * passed as String in the param
	 * 
	 * @param conn the connection string of the server
	 * @return 
	 */
	private static URI getBaseURI(String conn) {
		return UriBuilder.fromUri(conn).build(); //my server
		//return UriBuilder.fromUri("https://peaceful-hamlet-5616.herokuapp.com/sdelab").build(); //Andrea
	}
	
	
	

    /**
     * This method is used to know the number of people
     * 
     * @return String.valueOf(count) that is a String representing the number of the people
     */
    @GET
    @Path("/motivation")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSuggestion() {
    	
    	ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURIChuckNorris());
		this.service = service;
		System.out.println("Inside the method getSuggestion()");
    	
    	
        System.out.println("Getting count...");
        String randomChuck = "/jokes/random";
        System.out.println("Service to string" + service.toString());
        Response response_motivation = service.path(randomChuck).request().accept(MediaType.APPLICATION_JSON).get(Response.class);
        
        
        
        String jsonGetRandom = response_motivation.readEntity(String.class);
        System.out.println("jsonGetRandom: " + jsonGetRandom );
        
        JSONObject getAll_json = new JSONObject(jsonGetRandom);
		//getAll_json.getJSONObject("value");
        
        System.out.println("Get json for value: " + getAll_json.getJSONObject("value"));
        
        JSONObject value;
        
        value = getAll_json.getJSONObject("value");
        
        System.out.println("Value as object: " + value.toString());
        System.out.println("Joke the element of value object: " + value.getString("joke").toString());
        
        String value_phrase = value.getString("joke").toString();
        System.out.println("You have a message from your friend Cuck Norris: " + value_phrase);
        String output = "You have a message from your friend Cuck Norris: " + value_phrase;
        
        return output;
        
        //System.out.println("JsonObject" + getAll_json.toString());
		//JSONObject getValue_json = new JSONObject(getAll_json.getJSONObject("value"));
    }
    
    
    
    @GET
    @Path("/twitter")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTwitter() throws MalformedURLException, IOException, TwitterException{
    	/*
    	ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURIChuckNorris());
		this.service = service;
		System.out.println("Inside the method getSuggestion()");
		*/
    	
    	
    	
    	
    	//Your Twitter App's Consumer Key
        String consumerKey = TokenConnection.consumerKey;
 
        //Your Twitter App's Consumer Secret
        String consumerSecret = TokenConnection.consumerSecret;  
 
        //Your Twitter Access Token
        String accessToken = TokenConnection.accessToken;
 
        //Your Twitter Access Token Secret
        String accessTokenSecret = TokenConnection.accessTokenSecret;
 
        //Instantiate a re-usable and thread-safe factory
        TwitterFactory twitterFactory = new TwitterFactory();
 
        //Instantiate a new Twitter instance
        Twitter twitter = twitterFactory.getInstance();
 
        //setup OAuth Consumer Credentials
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
 
        //setup OAuth Access Token
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
 
        //Instantiate and initialize a new twitter status update
        StatusUpdate statusUpdate = new StatusUpdate(
                //your tweet or status message
                "@andrebonte you know that SOAP is the best !");
        
        //attach any media, if you want to
        /*
        statusUpdate.setMedia(
                //title of media
                "http://simpledeveloper.com"
                , new URL("https://si0.twimg.com/profile_images/1733613899/Published_Copy_Book.jpg").openStream());
        */
 
 
        //tweet or update status
        Status status = twitter.updateStatus(statusUpdate);
 
        //response from twitter server
        System.out.println("status.toString() = " + status.toString());
        System.out.println("status.getInReplyToScreenName() = " + status.getInReplyToScreenName());
        System.out.println("status.getSource() = " + status.getSource());
        System.out.println("status.getText() = " + status.getText());
        
        System.out.println("status.getURLEntities() = " + Arrays.toString(status.getURLEntities()));
        System.out.println("status.getUserMentionEntities() = " + Arrays.toString(status.getUserMentionEntities()));
    	
    	
        
        
        
        
    	return status.getText();
    	
    	
    	/*
        System.out.println("Getting count...");
        String randomChuck = "/jokes/random";
        System.out.println("Service to string" + service.toString());
        Response response_motivation = service.path(randomChuck).request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		return randomChuck;
        */
            }
    

    
    
       
}