package otal.egym.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    /**
     * Parses the JSON from the URL and returns a list of User
     *
     * @param urlJson
     * @return
     */
    public static List<User> parseJSON(String urlJson) {
        List<User> users = new ArrayList<>();
        try {
            // Get the JSON from the URL
            URL url = new URL(urlJson);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            // Parse the JSON String to a JSONObject
            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            JSONObject jsonObject = new JSONObject(builder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            // Instantiate an user for every element in the JSON array
            for (int i=0; i < jsonArray.length(); i++) {
                try {
                    User user = new User();
                    JSONObject oneObject = jsonArray.getJSONObject(i);

                    buildUser(user, oneObject);

                    // Add to list of users
                    users.add(user);
                } catch (JSONException e) {
                    Log.e("JSONParser", "" + e);
                }
            }

            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            Log.e("JSONParser", "" + e);
        }

        return users;
    }

    /**
     * Adds all the data from the json object to the User user.
     *
     * @param user
     * @param jsonObject
     */
    private static void buildUser(User user, JSONObject jsonObject) {
        try {
            JSONObject jsonPicture = jsonObject.getJSONObject("picture");
            user.setLargePicture(jsonPicture.getString("large"));
            user.setThumbnail(jsonPicture.getString("thumbnail"));

            user.setUsername(jsonObject.getJSONObject("login").getString("username"));
            user.setPhone(jsonObject.getString("phone"));
            user.setGender(User.Gender.valueOf(jsonObject.getString("gender").toUpperCase()));

            JSONObject jsonName = jsonObject.getJSONObject("name");
            user.setTitle(jsonName.getString("title"));
            user.setFirstName(jsonName.getString("first"));
            user.setLastName(jsonName.getString("last"));

            JSONObject jsonLocation = jsonObject.getJSONObject("location");
            user.setStreet(jsonLocation.getString("street"));
            user.setCity(jsonLocation.getString("city"));
            user.setState(jsonLocation.getString("state"));
            user.setPostcode(jsonLocation.getString("postcode"));
        }
        catch (JSONException e) {
            Log.e("JSONParser", "" + e);
        }

    }
}
