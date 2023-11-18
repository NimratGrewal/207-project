package entities;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpotifyAPICaller {
    private String accessToken;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    public SpotifyAPICaller(String clientId, String clientSecret) {
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
        generateNewToken();
    }

    public void generateNewToken() {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", CLIENT_ID, CLIENT_SECRET), mediaType);
        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try {
            okhttp3.Response response = client.newCall(request).execute();
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            accessToken = responseBody.getString("access_token");
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public Song getTrack(String trackId) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(String.format("https://api.spotify.com/v1/tracks/%s", trackId)))
                .get()
                .addHeader("Authorization", String.format("Bearer %s", accessToken))
                .build();

        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());
            if (responseBody.has("error")) {
                //status 401 means an expired access token, so generate a new one!
                if (responseBody.getJSONObject("error").getInt("status") == 401){
                    generateNewToken();
                    return getTrack(trackId);
                } else {
                    //throw an exception here?? something to suggest the request went wrong somewhere
                    throw new RuntimeException();
                }
            } else {
                String songName = responseBody.getString("name");
                List<String> artists = new ArrayList<>();
                for (Object artist : responseBody.getJSONArray("artists")) {
                    JSONObject artistObject = (JSONObject) artist;
                    artists.add(artistObject.getString("name"));
                }
                String album = responseBody.getJSONObject("album").getString("name");
                return new Song(trackId, songName, artists, album);
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
