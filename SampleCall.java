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
    }
}
