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

    public static List<User> parseJSON(String urlJson) {
        List<User> users = new ArrayList<>();
        try {
            URL url = new URL(urlJson);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            JSONObject jsonObject = new JSONObject(builder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i=0; i < jsonArray.length(); i++) {
                try {
                    User user = new User();
                    JSONObject oneObject = jsonArray.getJSONObject(i);

                    // Pulling items from the array
                    JSONObject oneObjectPicture = oneObject.getJSONObject("picture");
                    user.setLargePicture( oneObjectPicture.getString("large") );
                    user.setMediumPicture( oneObjectPicture.getString("medium") );
                    user.setThumbnail( oneObjectPicture.getString("thumbnail") );
                    user.setUsername(oneObject.getJSONObject("login").getString("username"));
                    user.setPhone( oneObject.getString("phone") );

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
}
