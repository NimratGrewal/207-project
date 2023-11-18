import entities.Song;
import entities.SpotifyAPICaller;

public class SampleCall {
    //Get Client ID and Client Secret
    private static final String CLIENT_ID = System.getenv("CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");

    public static void main(String[] args) {
        SpotifyAPICaller caller = new SpotifyAPICaller(CLIENT_ID, CLIENT_SECRET);
        System.out.println(caller.getTrack("11dFghVXANMlKmJXsNCbNl").getName());
        for (Song s: caller.searchForTracks("doughnut")) {
            System.out.println(s.getName());
        }
    }
}
