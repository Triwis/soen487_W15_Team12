package StockExchange;

import business.Business;
import com.sun.research.ws.wadl.Param;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.xml.sax.InputSource;

/**
* Temperature Resource (REST Web Service)
*
*/
@Stateless
@Path("exchange/tmx/business/{symbol}")
public class StockExchangeResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance
     */
    public StockExchangeResource() { }

    /**
     * Retrieves representation of an instance of SOEN487.Beans.TemperatureResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public Response getXml(@PathParam("symbol") String symbol) {
        StockExchangeBean instance = StockExchangeBean.getInstance();
        Business bus = instance.getBusiness(symbol);
        
        return Response.ok("<business>" + 
                "  <symbol>" + bus.getSymbol() + "</symbol>" +
                "  <price>" + bus.getPrice() + "</price>" +
                "</business>").build();
    }

    @GET
    @Produces("application/json")
    public Response getJson(@PathParam("symbol") String symbol) {
        StockExchangeBean instance = StockExchangeBean.getInstance();
        Business bus = instance.getBusiness(symbol);
       
        try {
            JSONObject response = new JSONObject();
            response.put("symbol", bus.getSymbol());
            response.put("price", bus.getPrice());
            return Response.ok(response).build();
        }
        catch (JSONException e) { 
            return Response.serverError().build(); 
        }
    }
    
    @POST
    @Consumes("application/json")
    public Response postJson(String content) {
        StockExchangeBean instance = StockExchangeBean.getInstance();
        
        try {
            JSONObject json = new JSONObject(content);
            InputSource is = new InputSource(new StringReader(content));
            String symbol = json.getString("symbol");
            double price = json.getDouble("price");
            
            Business newBus = new Business(symbol, price);
            instance.setBusiness(newBus);
            
            return Response.ok().build();
        }
        catch (Exception e) { return Response.serverError().build(); }
    }
    
    @POST
    @Consumes("application/xml")
    public Response postXml(String content) {
        StockExchangeBean instance = StockExchangeBean.getInstance();
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        try {
            InputSource is = new InputSource(new StringReader(content));
            String symbol = xpath.evaluate("/business/symbol", is);
            double price = Double.parseDouble(xpath.evaluate("/business/price", is));
            Business newBus = new Business(symbol, price);
            instance.setBusiness(newBus);
            return Response.ok().build();
        }
        catch (Exception e) { return Response.serverError().build(); }
    }
    
    /**
     * 
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public Response putXml(@PathParam("symbol") String symbol, String content) {
        StockExchangeBean instance = StockExchangeBean.getInstance();
        Business bus = instance.getBusiness(symbol);
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        
        try {
            InputSource is = new InputSource(new StringReader(content));
            double price = Double.parseDouble(xpath.evaluate("/business/price", is));
            bus.setPrice(price);
            instance.setBusiness(bus);
            
            return Response.ok().build();
        }
        catch (Exception e) { 
            return Response.serverError().build(); 
        }
    }
    
    @PUT
    @Consumes("application/json")
    public Response putJson(@PathParam("symbol") String symbol, String content) {
        StockExchangeBean instance = StockExchangeBean.getInstance();
        Business bus = instance.getBusiness(symbol);
        
        try {
            JSONObject json = new JSONObject(content);
            double unitPrice = json.getDouble("price");
            bus.setPrice(unitPrice);
            instance.setBusiness(bus);
            
            return Response.ok().build();
        }
        catch (Exception e) {
            return Response.serverError().build();
        }
    }
    
    @DELETE
    public Response delete(@PathParam("symbol") String symbol) {
        try {
            StockExchangeBean instance = StockExchangeBean.getInstance();

            Business bus = instance.getBusiness(symbol);
            instance.deleteBusiness(bus);

            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
