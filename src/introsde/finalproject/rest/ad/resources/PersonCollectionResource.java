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

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import introsde.finalproject.rest.ad.resources.TokenConnection;

//import twitter4j.*;
//import twitter4j.auth.AccessToken;
 




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
    private WebTarget service_forecast;
    private WebTarget service_weather;
    
    static final String openweather = "2de143494c0b295cca9337e1e96b00e0";
    
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
	
	
    /**
     * This method is used to get an joke motivation phrase
     * 
     * @return phrase
     */
    @GET
    @Path("/motivation")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSuggestion() {
    	try{
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
        
        if(response_motivation.getStatus() != 200){
        	System.out.println("Error in external service");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
       				.entity(response_motivation.readEntity(String.class)).build();
            }else{
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
                
                //return output;
           	 	return Response.ok(output).build();
            }
    	}catch(Exception e){
    		System.out.print("Error Cath motivation");
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity("Exception in Adapter Services due to: " + e).build();
    	}
        
        
        
        
        
        //System.out.println("JsonObject" + getAll_json.toString());
		//JSONObject getValue_json = new JSONObject(getAll_json.getJSONObject("value"));
    }
    
    

    /**
     * This method is used to get the current weather data for one location.
     * 
     * 
     * @param city location and nation code for which get current weather data
     * @param metric type of units to use for measure
     * @param json type of return data
     * @return jsonWeather 
     */
    @GET
    @Path("/weather")
    @Produces(MediaType.APPLICATION_JSON)
    public String getWeatherTest(@QueryParam("city") String city, @QueryParam("units") String metric,
    		@QueryParam("mode") String json) {
    	
    	//http://127.0.1.1:5700/sdelab/person/weather?city=Trento,it&units=metric&mode=json
    	ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		//WebTarget service_weather = client.target(getBaseURIWeatherTest());
		WebTarget service_weather = client.target("http://api.openweathermap.org/data/2.5/find")
				.queryParam("q", city)
				.queryParam("units", metric)
				.queryParam("mode", json)
				.queryParam("appid", openweather);
                
		System.out.println("Service to string  adding path" + service_weather.toString());
		
		this.service_weather = service_weather;
		
		System.out.println("Service to string  after this.service_weater adding path" + service_weather.toString());
		
    	//String path = "http://api.openweathermap.org/data/2.5/find?q=Trento,it&units=metric&mode=json&appid=a3dbf2f9a2ab9c24905f3ea44cb9e265";
    	
		Response response_weater = service_weather.request().accept(MediaType.APPLICATION_JSON).get(Response.class);
        //System.out.println(weather);
        System.out.println("Service_weather after adding path: " + service_weather.toString());
        
        
        String jsonWeather = response_weater.readEntity(String.class);
        System.out.println("jsonGetRandom: " + jsonWeather );
        return jsonWeather;
    }
    
    
    /**
     * This method is used to get weather forecast for 5 days 
     * with data every 3 hours by city name.
     * 
     * 
     * @param city location and nation code for which get forecast weather data
     * @param metric type of units to use for measure
     * @param json type of return data
     * @return jsonWeather
     */
    @GET
    @Path("/forecast")
    @Produces(MediaType.APPLICATION_JSON)
    public String getForeCast(@QueryParam("city") String city, @QueryParam("units") String metric,
    		@QueryParam("mode") String json) {
    	
    	//http://127.0.1.1:5700/sdelab/person/forecast?city=Trento,it&units=metric&mode=json
    	
    	//http://api.openweathermap.org/data/2.5/forecast?q=Trento,it&units=metric&mode=json&appid=2de143494c0b295cca9337e1e96b00e0
    	ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		//WebTarget service = client.target(getBaseURIForecast());
		WebTarget service_forecast = client.target("http://api.openweathermap.org/data/2.5/forecast")
				.queryParam("q", city)
				.queryParam("units", metric)
				.queryParam("mode", json)
				.queryParam("appid", openweather);
		this.service_forecast = service;
		
		
        System.out.println("Service to string" + service_forecast.toString());
        Response response_forecast = service_forecast.request().accept(MediaType.APPLICATION_JSON).get(Response.class);
        String jsonForecast = response_forecast.readEntity(String.class);
        System.out.println("jsonGetRandom: " + jsonForecast );
        
        return jsonForecast;
        
    }

    
    
    /*
    @GET
    @Path("/twitter")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTwitter() throws MalformedURLException, IOException, TwitterException{
    	
    	ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURIChuckNorris());
		this.service = service;
		System.out.println("Inside the method getSuggestion()");
		
    	
    	
    	
    	
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
        
        statusUpdate.setMedia(
                //title of media
                "http://simpledeveloper.com"
                , new URL("https://si0.twimg.com/profile_images/1733613899/Published_Copy_Book.jpg").openStream());
        
 
 
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
    	
        
            }
    */

    
    
       
}