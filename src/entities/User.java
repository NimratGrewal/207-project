package entities;

import java.util.Map;
import java.util.UUID;

public interface User {
    String getUsername();

    String getPassword();

    UUID getUserId();

    Map<Prompt, Response> getHistory();

    Response getResponse(Prompt prompt);

    void setResponse(Prompt prompt, Response response);

    void deleteResponse(Prompt prompt);

    void changeResponse(Prompt prompt, Response response);

}
