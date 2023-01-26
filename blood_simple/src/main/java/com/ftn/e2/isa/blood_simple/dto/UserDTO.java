package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String personalId;
    private String email;
    private String password;
    private String name;
    private String surname;
    private GenderENUM gender;
//    private Address address;

    private Long addressId;
    private String addressStreet;
    private String addressNumber;
    private String addressCity;
    private String addressCountry;
    private double addressX;
    private double addressY;


    private DonationForm donationForm;

    private String phoneNumber;
    private String job;
    private String bio;
    private RoleENUM role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.personalId = user.getPersonalId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.gender = user.getGender();
        this.addressId = user.getAddress().getId();
        this.addressStreet = user.getAddress().getStreet();
        this.addressNumber = user.getAddress().getNumber();
        this.addressCity = user.getAddress().getCity();
        this.addressCountry = user.getAddress().getCountry();
        this.addressX = user.getAddress().getX();
        this.addressY = user.getAddress().getY();
        this.phoneNumber = user.getPhoneNumber();
        this.job = user.getJob();
        this.bio = user.getBio();
        this.role = user.getRole();
        if (user.getDonationForm() != null)
            this.donationForm = user.getDonationForm();


    }

    public static User MapToUser(Map<String, String> map) {

        double[] coordinates = getCoordinates(map.get("addressCity"), map.get("addressCountry"), map.get("addressStreet"), map.get("addressNumber"));

        assert coordinates != null;
        System.out.println("Coordinates: " + coordinates[0] + " " + coordinates[1]);
        return new User(
                map.get("personalId"),
                map.get("email"),
                map.get("password"),
                map.get("firstName"),
                map.get("lastName"),
                new Address(
                        map.get("addressStreet"),
                        map.get("addressNumber"),
                        map.get("addressCity"),
                        map.get("addressCountry"),
                        coordinates[0],
                        coordinates[1]
                ),
                map.get("phoneNumber"),
                map.get("job"),
                map.get("bio"),
                RoleENUM.valueOf(map.get("role")),
                GenderENUM.valueOf(map.get("gender"))
        );
    }


    public static double[] getCoordinates(String city, String country, String street, String streetNumber) {
        try {
            // Build the API URL
            String query = street + " " + streetNumber + " " + city + " " + country;
            String apiUrl = "https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8) +"&format=json&addressdetails=1";
            URL url = new URL(apiUrl);

            // Open the connection and send the GET request
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
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
                JSONArray jsonArray = new JSONArray(response.toString());
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                double lat = jsonObject.getDouble("lat");
                double lon = jsonObject.getDouble("lon");

                if (lat == 0 && lon == 0) {
                    return new double[]{0, 0};
                }
                return new double[]{lat, lon};
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
