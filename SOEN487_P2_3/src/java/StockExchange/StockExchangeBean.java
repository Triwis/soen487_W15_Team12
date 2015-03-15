package StockExchange;

import javax.ejb.Singleton;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import java.io.StringReader;
import business.Business;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
* @author Chris Kahn
*/
@Singleton
public class StockExchangeBean {
    // Placeholder for business database
    private Map<String, Business> businesses = new HashMap<String, Business>();

    // Placeholder for StockExchangeBean instance
    private static StockExchangeBean INSTANCE = null;

    /**
     * Constructor
     *
     * @return void
     */
    protected StockExchangeBean() { }

    /**
     * Get the instance
     *
     * @return StockExchangeBean
     */
    public static StockExchangeBean getInstance() {
        // If the instance has not been initialized, we do so
        if (INSTANCE == null) {
            INSTANCE = new StockExchangeBean();
        }

        return INSTANCE;
    }

    /**
     * Get a business
     *
     * @return String
     */
    public Business getBusiness(String symbol) {
        return this.businesses.get(symbol);
    }

    /**
     * Insert or update a business.
     *
     * @return void
     */
    public void setBusiness(Business business) {
        this.businesses.put(business.getSymbol(), business);
    }
    
    /**
     * Remove a business.
     * 
     * @param business 
     */
    public void deleteBusiness(Business business) {
        this.businesses.remove(business.getSymbol());
    }
    
//    /**
//     * Parse a Share from xml data.
//     * @param xml
//     * @return 
//     */
//    public Share parseXmlShare(String xml) {
//        XPathFactory xpathFactory = XPathFactory.newInstance();
//        XPath xpath = xpathFactory.newXPath();
//        
//        try {
//            InputSource is = new InputSource(new StringReader(xml));
//            float price = Float.parseFloat(xpath.evaluate("/share/price", is));
//            String shareType = xpath.evaluate("/share/type", is);
//            String symbol = xpath.evaluate("/share/symbol", is);
//            return new Share(symbol, shareType, price);
//        } 
//        catch(Exception e) { return null; }
//    }
//    
//    /**
//     * Convert a share into its XML representation.
//     * 
//     * @param share
//     * @return 
//     */
//    public String shareToXml(Share share) {
//        return "<share>"
//                + "  <symbol>" + share.businessSymbol + "</symbol>"
//                + "  <shareType>" + share.shareType + "</shareType>"
//                + "  <unitPrice>" + share.unitPrice + "</unitprice>"
//                + "</share>";
//    }
//    
//    /**
//     * Convert a Share into a JSON object.
//     * 
//     * @param share
//     * @return
//     * @throws JSONException 
//     */
//    public JSONObject shareToJson(Share share) throws JSONException {
//        JSONObject jShare = new JSONObject();
//        JSONObject jStockprice = new JSONObject();
//
//        jShare.put("symbol", share.businessSymbol);
//        jShare.put("shareType", share.shareType);
//        jShare.put("unitPrice", share.unitPrice);
//
//        return jShare;
//    }
//    
//    /**
//     * Parse a Share from a JSON object.
//     * @param json
//     * @return
//     * @throws JSONException 
//     */
//    public Share jsonToShare(JSONObject json) throws JSONException {
//        String symbol = (String)json.get("symbol");
//        String shareType = (String)json.get("shareType");
//        float unitPrice = (float)json.getDouble("unitPrice");
//        return new Share(symbol, shareType, unitPrice);
//    }
}