package utils;

import java.net.HttpURLConnection;
import java.net.URL;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* HTTP Utils
*
* @author  Pietro Tortorici
* @version 1.0
* @since   2015-02-15
*/
public class HTTPUtils {

    /**
     * Get Content - Overloaded method to provide multiple input options
     *
     * @param String urlString The URL
     */
    public static String getContent(String urlString) {
        return getContent(urlString, "", "");
    }

    /**
     * Get Content
     *
     * @param String urlString The URL
     * @param String username The username
     * @param String password The password
     */
    public static String getContent(String urlString, String username, String password) {
        URL url                = null;
        HttpURLConnection conn = null;
        BufferedReader reader  = null;
        String line            = "";
        String content         = "";


        try {
            // Init the url with the url string provided
            // @TODO We possibly want to add url validation here
            url  = new URL(urlString);

            // Open connect
            conn = (HttpURLConnection) url.openConnection();

            // Set the request method
            conn.setRequestMethod("GET");

            // If username is provided we setup the auth header
            if(username.equalsIgnoreCase("") == false) {
                conn.setRequestProperty("Authorization", "Basic " +
                        Base64.encode((username + ":" + password).getBytes()));
            }

            // Ensure the char-type is UTF-8 , otherwise we encounter encoding issues
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            // Read & store each line of the output
            while ((line = reader.readLine()) != null) {
               content += line;
            }

            reader.close();
         } catch (Exception ex) { }

        // return output
        return content;
    }
}
