package entities;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SpotifyAPICaller {
    private String accessToken;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    /**
     * Create a new SpotifyAPICaller for the Client Credentials Authorization Flow
     * @param clientId The application's client secret
     * @param clientSecret The application's client ID
     */
    public SpotifyAPICaller(String clientId, String clientSecret) {
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
        generateNewToken();
    }

    /**
     * Generate a new accessToken from the client ID and client secret
     */
    private void generateNewToken() {
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

    /**
     * Returns a Song that has information corresponding to the track on Spotify with trackId
     * @param trackId The Spotify ID of the track
     * @return a Song that has information corresponding to the track on Spotify with trackId
     */
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
              
                if (!responseBody.getJSONObject("album").getJSONArray("images").isEmpty()) {
                    URL ImageURL = new URL(responseBody.getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));
                  Image albumArt = new ImageIcon(ImageURL).getImage();
                  return new Song(trackId, songName, artists, album, albumArt);
                } else {
                    // if no images available
                    throw new RuntimeException("No album cover found for the track");
                }
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a list of Song objects matching the search query
     * @param query The text search query
     * @return A list of Song objects (max 10) matching the search query
     */
    public List<Song> searchForTracks(String query) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://api.spotify.com/v1/search?q=%s&type=track&limit=10", query))
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
                    return searchForTracks(query);
                } else {
                    //throw an exception here?? something to suggest the request went wrong somewhere
                    throw new RuntimeException();
                }
            } else {
                JSONArray trackObjects = responseBody.getJSONObject("tracks").getJSONArray("items");
                List<Song> tracks = new ArrayList<>();
                for (Object o: trackObjects) {
                    JSONObject trackObject = (JSONObject) o;
                    String songName = trackObject.getString("name");
                    String songId = trackObject.getString("id");
                    String album = trackObject.getJSONObject("album").getString("name");
                    List<String> artists = new ArrayList<>();
                    for (Object artist : trackObject.getJSONArray("artists")) {
                        JSONObject artistObject = (JSONObject) artist;
                        artists.add(artistObject.getString("name"));
                    }
  
                    if (!responseBody.getJSONObject("album").getJSONArray("images").isEmpty()) {
                        URL ImageURL = new URL(responseBody.getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));
                        Image albumArt = new ImageIcon(ImageURL).getImage();
                        tracks.add(new Song(songId, songName, artists, album, albumArt));
                    } else {
                    // if no images available
                    throw new RuntimeException("No album cover found for the track");
                    }
                }
                return tracks;
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
