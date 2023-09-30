import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class SampleCall {
    public void makeAPIRequest() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl")
                .header("Authorization",
                        "Bearer BQDdTy9Rp4pQMCqoDtGbw48ycdJlB41cetiIB-2KkfFL" +
                                "Ou-EjQJAYCzVw2p__OrDkl9XS8VzEk3A_dJX3vkd25mGd0GDCh2GZSXNexWYisuHu9goDpE")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("API Request Successful");
                System.out.println("Response: " + response.body().string());
            } else {
                System.err.println("API Request Failed");
                System.err.println("Response Code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
