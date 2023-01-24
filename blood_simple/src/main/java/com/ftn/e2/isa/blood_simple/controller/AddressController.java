package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.service.AddressService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

    @Autowired
    private AddressService addressService;

    public static double[] getCoordinates(String city, String country, String street, String streetNumber) {
        try {
            // Build the API URL
            String apiUrl = "https://nominatim.openstreetmap.org/search?q=" + street + "+" + streetNumber + "+" + city + "+" + country + "&format=json&addressdetails=1";
            URL url = new URL(apiUrl);

            // Open the connection and send the GET request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Read the response
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response
                JSONObject jsonObject = new JSONObject(response.toString());
                double lat = jsonObject.getDouble("lat");
                double lon = jsonObject.getDouble("lon");

                return new double[]{lat, lon};
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
