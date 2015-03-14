package SOEN487.Beans;
import javax.ejb.Singleton;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import java.io.StringReader;

/**
* Singleton temperature bean
*
* @author  Pietro Tortorici
* @version 1.0
* @since   2015-3-14
*/
@Singleton
public class TemperatureBean {
    // Placeholder for temperature
    private String temperature = "0.0";

    // Placeholder for TemperatureBean instance
    private static TemperatureBean INSTANCE = null;

    /**
     * Constructor
     *
     * @return void
     */
    protected TemperatureBean() { }

    /**
     * Get the instance
     *
     * @return TemperatureBean
     */
    public static TemperatureBean getInstance() {
        // If the instance has not been initialized, we do so
        if (INSTANCE == null) {
            INSTANCE = new TemperatureBean();
        }

        return INSTANCE;
    }

    /**
     * Get the temperature
     *
     * @return String
     */
    public String getTemperature() {
        return this.temperature;
    }

    /**
     * Set the temperature
     *
     * @param String t Temperature
     * @return void
     */
    public void setTemperature(String t) {
        this.temperature = t;
    }

    /**
     * Parse temperature from xml
     *
     * @param String xml XML
     * @return String
     */
    public String parseTemperature(String xml) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        
        try {
        return xpath.evaluate("/temperature", new InputSource(new StringReader(xml)));
        } catch(Exception e) { return ""; }
    }
}