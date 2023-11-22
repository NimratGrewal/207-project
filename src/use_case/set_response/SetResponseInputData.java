package use_case.set_response;

import java.util.UUID;

public class SetResponseInputData {
    private final String songId;

    public SetResponseInputData(String songId) {
        this.songId = songId;
    };

    public String getSongId() {
        return songId;
    }
}
