package SOEN487.Beans;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
* Temperature Resource (REST Web Service)
*
* @author  Pietro Tortorici
* @version 1.0
* @since   2015-3-14
*/
@Stateless
@Path("/temperature")
public class TemperatureResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TemperatureResource
     */
    public TemperatureResource() { }

    /**
     * Retrieves representation of an instance of SOEN487.Beans.TemperatureResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        return "<temperature>" + TemperatureBean.getInstance().getTemperature() +
                "</temperature>";
    }

    /**
     * PUT method for updating or creating an instance of TemperatureResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
        TemperatureBean.getInstance().setTemperature(TemperatureBean.getInstance().parseTemperature(content));
    }
}