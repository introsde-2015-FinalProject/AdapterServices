package introsde.finalproject.rest.ad.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/help") // indicates the path under which this resource will be available

/**
 * This class is intended to be a helper 
 * containing all the information about the use and
 * the allowed request that a client can ask to the server
 * 
 * @author Carlo Nicolo'
 *
 */
public class HelpResource {
	
	/**
	 * This method is used to print all the request that a client 
	 * can ask to this server. The mediaType is in HTML then this method
	 * could be requested through a browser.
	 * 
	 * @return the complete list of commands that this server performs 
	 */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String helpHtml() {
        return "<html> " + "<title>" + "" + "</title>"
                + "<body>"
                + "<h1>" + "Welcome to my server !" + "</h1>"
                + "<h2>" + "*** List of all methods that my server provides ***" + "</h2>"
                + "<h3>" +  "[Request #1] GET /person list all the people in database" + "</h3>"
    			+ "<h3>" + "[Request #2] GET /person/{id} give all the personal information plus current measures of person identified by {id}" + "</h3>"
    			+ "<h3>" + "[Request #3] PUT /person/{id} update the personal information of the person identified by {id}" + "</h3>"
    			+ "<h3>" + "[Request #4] POST /person create a new person and return the newly created person with its assigned id" + "</h3>"
    			+ "<h3>" + "[Request #5] DELETE /person/{id} delete the person identified by {id} from the system" + "</h3>"
    			+ "<h3>" + "[Request #6] GET /person/{id}/{measureType} return the list of values (the history) of {measureType} (e.g. weight) for person identified by {id}" + "</h3>"
    			+ "<h3>" + "[Request #7] GET /person/{id}/{measureType}/{mid} return the value of {measureType} (e.g. weight) identified by {mid} for person identified by {id}" + "</h3>"
    			+ "<h3>" + "[Request #8] POST /person/{id}/{measureType} save a new value for the {measureType} (e.g. weight) of person identified by {id} and archive the old value in the history" + "</h3>"
    			+ "<h3>" + "[Request #9] GET /measureTypes should return the list of measures supported" + "</h3>"
    			+ "<h3>" + "[Extra-Request #10] PUT /person/{id}/{measureType}/{mid} update the value for the {measureType} (e.g., weight) identified by {mid}, related to the person identified by {id}" + "</h3>"
    			+ "<h3>" + "[Extra-Request #11] GET /person/{id}/{measureType}?before={beforeDate}&after={afterDate} return the history of {measureType} (e.g., weight) for person {id} in the specified range of date" + "</h3>"
    			+ "<h3>" + "[Extra-Request #12] GET /person?measureType={measureType}&max={max}&min={min} retrieves people whose {measureType} (e.g., weight) value is in the [{min},{max}] range (if only one for the query params is provided, use only that)" + "</h3>"
                + "</body>"
                + "</html> ";
    }
    
//    @GET
//    @Produces(MediaType.TEXT_XML)
//    public String helpXML() {
//        return "<?xml version=\"1.0\"?>" + 
//    "<msg>" + "Help server command"+ "</msg>";
//    }
    
    /**
     * This method is used to print all the request that a client 
	 * can ask to this server. The mediaType is TEXT_PLAIN then this method
	 * could be requested through a shell.
     * 
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helpTEXT() {
        
    	String help =  "[Request #1] GET /person list all the people in database\n"
    			+ "\n[Request #2] GET /person/{id} give all the personal information plus current measures of person identified by {id}\n"
    			+ "\n[Request #3] PUT /person/{id} update the personal information of the person identified by {id}\n"
    			+ "\n[Request #4] POST /person create a new person and return the newly created person with its assigned id\n"
    			+ "\n[Request #5] DELETE /person/{id} delete the person identified by {id} from the system\n"
    			+ "\n[Request #6] GET /person/{id}/{measureType} return the list of values (the history) of {measureType} (e.g. weight) for person identified by {id}\n"
    			+ "\n[Request #7] GET /person/{id}/{measureType}/{mid} return the value of {measureType} (e.g. weight) identified by {mid} for person identified by {id}\n"
    			+ "\n[Request #8] POST /person/{id}/{measureType} save a new value for the {measureType} (e.g. weight) of person identified by {id} and archive the old value in the history\n"
    			+ "\n[Request #9] GET /measureTypes should return the list of measures supported\n"
    			+ "\n[Extra-Request #10] PUT /person/{id}/{measureType}/{mid} update the value for the {measureType} (e.g., weight) identified by {mid}, related to the person identified by {id}\n"
    			+ "\n[Extra-Request #11] GET /person/{id}/{measureType}?before={beforeDate}&after={afterDate} return the history of {measureType} (e.g., weight) for person {id} in the specified range of date\n"
    			+ "\n[Extra-Request #12] GET /person?measureType={measureType}&max={max}&min={min} retrieves people whose {measureType} (e.g., weight) value is in the [{min},{max}] range (if only one for the query params is provided, use only that)";
    	return help; 
    }
    
    
}