import okhttp3.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class SampleCall {
    //Get Client ID and Client Secret
    private static final String CLIENT_ID = "216b6438ba554128ade0b63afa48ddd8";
    private static final String CLIENT_SECRET = "56e33aff04bc4aa28dc3d9e54bb231cd";

    public static void main(String[] args) {
        String accessToken;

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", CLIENT_ID, CLIENT_SECRET), mediaType);
        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            accessToken = responseBody.getString("access_token");
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

        String trackId = "11dFghVXANMlKmJXsNCbNl";

        request = new Request.Builder()
                .url(String.format(String.format("https://api.spotify.com/v1/recommendations?seed_tracks=%s&limit=10", trackId)))
                .get()
                .addHeader("Authorization", String.format("Bearer %s", accessToken))
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray trackObjects = responseBody.getJSONArray("tracks");
            ArrayList<String> tracks = new ArrayList<>();
            for (int i = 0; i < trackObjects.length(); i++) {
                StringBuilder trackInfo = new StringBuilder(trackObjects.getJSONObject(i).getString("name") + " by ");
                for (int j = 0; j < trackObjects.getJSONObject(i).getJSONArray("artists").length(); j++) {
                    trackInfo.append(trackObjects.getJSONObject(i).getJSONArray("artists").getJSONObject(j).getString("name"));
                }
                tracks.add(trackInfo.toString());
            }
            System.out.println(tracks);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
