package yahoo;
import java.net.URLEncoder;
import java.util.ArrayList;
import utils.HTTPUtils;

/**
* Simple Yahoo API client to fetch stock quotes
*
* @author  Pietro Tortorici
* @version 1.0
* @since   2015-3-15
*/
public class YahooAPIClient {
    /**
     * Fetch Yahoo quotes for given business symbols
     *
     * @param ArrayList symbols The symbols
     * @return TemperatureBean
     */
    public static String fetchQuotes(ArrayList<String> symbols) {
        // Check if symbols were indeed passed
        if(symbols.size() < 1) {
            return "";
        }

        String apiUrl = "https://query.yahooapis.com/v1/public/yql";

        String query = "select * from yahoo.finance.quote where symbol in (";

        for(String symbol : symbols) {
            query += "\"" + symbol + "\",";
        }

        query = query.replaceAll(",$", "");

        query += ")";

        try {
        return HTTPUtils.getContent(apiUrl + "?" + URLEncoder.encode("q=" + query, "UTF-8"));
        } catch(Exception e) { return ""; }
    }
}
