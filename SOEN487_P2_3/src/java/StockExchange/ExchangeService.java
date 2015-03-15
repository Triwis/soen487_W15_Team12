/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import org.w3c.dom.Document;
import utils.xmlUtils;

/**
 * REST Web Service
 *
 * @author Louis-Maxime
 */
@Path("stockexchange")
public class ExchangeService {

    @Context
    private UriInfo context;
    private String s;
    private int i;
    private List<String> list;
    /**
     * Creates a new instance of ExchangeService
     */
    public ExchangeService() {
        String path = "C:\\Users\\Louis-Maxime\\Documents";
        path += "\\stocklist.xml";
        Document doc = xmlUtils.getDocumentFromPath(path);
        list = xmlUtils.instantiateRandomPrice(doc, path);
        
    }

    /**
     * Retrieves representation of an instance of StockExchange.ExchangeService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        //TODO return proper representation object
        String s = "<html><head></head><body>";
        
        for (String t : list) {
            s += (t + "<br />");
        }
        
        s += "</body></html>";
        return s;
        
        
    }

    /**
     * PUT method for updating or creating an instance of ExchangeService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }
}
