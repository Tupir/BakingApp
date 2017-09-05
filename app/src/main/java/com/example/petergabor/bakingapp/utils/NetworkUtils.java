package com.example.petergabor.bakingapp.utils;


import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Create network connection and retrieve data
 *
 */

public final class NetworkUtils {

    private NetworkUtils() {}

    // url
    final static String RECEPT_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    /**
     * Builds the URL.
     * Vysklada URL adresu
     */
    public static URL buildUrl() {

        Uri builtUri = Uri.parse(RECEPT_URL).buildUpon().build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println(url.toString());
        return url;
    }


    /**
     * This method returns the entire result from the HTTP response.
     * Vrati response zo stranky (cely JSON)
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            urlConnection.setConnectTimeout(5000);  // if there are some connection problems hold on 5 seconds
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}