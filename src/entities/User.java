package entities;

import java.util.Map;

public interface User {
    String getUsername();

    String getPassword();

    Map<Prompt, Response> getHistory();

    Response getResponse(Prompt prompt);

    void setResponse(Prompt prompt, Response response);

    void deleteResponse(Prompt prompt);

    void changeResponse(Prompt prompt, Response response);

}
