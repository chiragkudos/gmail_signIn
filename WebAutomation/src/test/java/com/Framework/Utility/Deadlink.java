package com.Framework.Utility;

import java.net.HttpURLConnection;
import java.net.URL;

public class Deadlink {
	
	public static boolean getResponseCode(String urlString) {
        boolean isValid = false;
        try {
            URL u = new URL(urlString);
           // System.out.println("URL========="+urlString);
            HttpURLConnection h = (HttpURLConnection) u.openConnection();
            h.setRequestMethod("GET");
    
            h.connect();
           /* System.out.println("Response Code"+h.getResponseCode());*/
            ////Here, 204-no info to return, 400-Bad Request, 403-Forbidden, 404-Not found, 422-Unprocessable Entity, 500-Internal Server Error, 503-Service Unavailable
           /* if (h.getResponseCode() != 204 && h.getResponseCode() != 400 && h.getResponseCode() != 403 && h.getResponseCode() != 404 && h.getResponseCode() != 422 && h.getResponseCode() != 500 && h.getResponseCode() != 503) {
                isValid = true;
            }*/
            
            
           
            if (h.getResponseCode() != 204 && h.getResponseCode() != 400 && h.getResponseCode() != 404 && h.getResponseCode() != 422 && h.getResponseCode() != 500 && h.getResponseCode() != 503) {
                isValid = true;
            }
        } catch (Exception e) {

        }
        return isValid;
    }
}
