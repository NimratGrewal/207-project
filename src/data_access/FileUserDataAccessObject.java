package data_access;

import entities.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class FileUserDataAccessObject {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<UUID, User> accounts = new LinkedHashMap<>();
    private final Map<User, List<Response>> responses = new LinkedHashMap<>();
    private UserFactory userFactory;
    private User loggedInUser;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory, SpotifyAPICaller caller) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("userId", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creation_time", 3);
        headers.put("responses", 4);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("userId,username,password,creation_time,responses");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    UUID userId = UUID.fromString(String.valueOf(col[headers.get("userId")]));
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String responsesText = String.valueOf(col[headers.get("responses")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);

                    User user = this.userFactory.create(userId, username, password, ldt);
                    accounts.put(userId, user);

                    if (!Objects.equals(responsesText, "null")) {
                        String[] responseInfo = responsesText.split(";");
                        for (String responseStr : responseInfo) {
                            String[] responseData = responseStr.split(":");
                            UUID responseID = UUID.fromString(responseData[0]);
                            UUID promptID = UUID.fromString(responseData[1]);
                            String songID = responseData[2];

                            Response response = new Response(responseID, promptID, userId, caller.getTrack(songID));
                            if (!this.responses.containsKey(user)){
                                this.responses.put(user, new ArrayList<>());
                            }
                            this.responses.get(user).add(response);
                            user.setResponse(promptID, response);
                        }
                    }
                }
            }
        }
    }

    /**
     * Save a new User to accounts
     *
     * @param user The user to be saved
     */
    public void save(User user) {
        accounts.put(user.getUserId(), user);
        responses.put(user, new ArrayList<>(user.getHistory().values()));
        this.save();
    }

    public User getUser(UUID userId) {
        return accounts.get(userId);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(accounts.values());
    }

    /**
     * Save the current state of this DataAccessObject in the data file
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                List<String> responses = new ArrayList<>();
                String responseString;
                if (user.getHistory().values().isEmpty()) {
                    responseString = "null";
                } else {
                    for (Response response : user.getHistory().values()) {
                        String responseText = "%s:%s:%s".formatted(
                                response.getResponseId(), response.getPromptId(), response.getSong().getSongId());
                        responses.add(responseText);
                    }
                    responseString = String.join(";", responses);
                }
                String line = "%s,%s,%s,%s,%s".formatted(
                        user.getUserId().toString(), user.getUsername(), user.getPassword(), user.getCreationTime(), responseString);
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Sets the current logged in user's response to response
     * @param response The response for the user with the prompt ID from response.getPromptId()
     */
    public void setResponse(Response response) {
        accounts.get(response.getUserId()).setResponse(response.getPromptId(), response);
        responses.get(loggedInUser).add(response);
    }

    public Response getResponseById(UUID userId, UUID responseId) {
        User user = accounts.get(userId);
        if (user != null && responses.containsKey(user)) {
            List<Response> userResponses = responses.get(user);
            for (Response response : userResponses) {
                if (response.getResponseId().equals(responseId)) {
                    return response;
                }
            }
        }
        return null; // Response not found
    }

    public Response getResponseById(UUID responseId) {
        for (List<Response> responseList: this.responses.values())
            for (Response response : responseList) {
                if (response.getResponseId().equals(responseId)) {
                    return response;
                }
            }
        return null; // Response not found
    }

    public boolean responseExistsById(UUID responseId) {
        for (List<Response> responseList: responses.values()){
            for (Response response: responseList) {
                if (responseId.equals(response.getResponseId())){
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteResponse(UUID responseId) {
        for (List<Response> responseList: responses.values()){
            for (Response r: responseList) {
                if (responseId.equals(r.getResponseId())) {
                    responseList.remove(r);
                    accounts.get(r.getUserId()).deleteResponse(r.getPromptId());
                }
            }
        }
        save();
    }

    public UUID getLoggedInUserId() {return loggedInUser.getUserId(); }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public List<UUID> getResponseIds(User user) {
        List<UUID> responseIds = new ArrayList<>();

        if (responses.containsKey(user)) {
            List<Response> userResponses = responses.get(user);
            for (Response response : userResponses) {
                responseIds.add(response.getResponseId());
            }
        }
        return responseIds;
    }
}