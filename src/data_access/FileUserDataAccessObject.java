package data_access;

import entities.*;
import use_case.delete.DeleteUserDataAccessInterface;
import use_case.set_response.SetResponseDataAccessInterface;
import use_case.toProfile.UserProfileDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileUserDataAccessObject implements SetResponseDataAccessInterface, UserProfileDataAccessInterface, DeleteUserDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<UUID, User> accounts = new LinkedHashMap<>();

    private final Map<User, List<Response>> responses = new LinkedHashMap<>();

    private UserFactory userFactory;

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

                    String[] responseInfo = responsesText.split(";");
                    for (String responseStr : responseInfo) {
                        String[] responseData = responseStr.split(":");
                        UUID responseID = UUID.fromString(responseData[0]);
                        UUID promptID = UUID.fromString(responseData[1]);
                        String songID = responseData[2];

                        Response response = new Response(responseID, promptID, user, caller.getTrack(songID));
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

    /**
     * Save a new User to accounts
     *
     * @param user The user to be saved
     */
    //TODO: add save method to UserSignUpDataAccessInterface
    public void save(User user) {
        accounts.put(user.getUserId(), user);
        this.save();
    }

    public User getUser(UUID userId) {
        return accounts.get(userId);
    }

    @Override
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
                for (Response response : user.getHistory().values()) {
                    String responseText = "%s:%s:%s".formatted(
                            response.getResponseId(), response.getPromptId(), response.getSongId());
                    responses.add(responseText);
                }
                String responseString = String.join(";", responses);
                String line = "%s,%s,%s,%s".formatted(
                        user.getUsername(), user.getPassword(), user.getCreationTime(), responseString);
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setResponse(UUID userId, Response response) {
        accounts.get(userId).setResponse(response.getPromptId(), response);
        responses.get(accounts.get(userId)).add(response);
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

    @Override
    public String getActivePromptText() {
        return null;
    }

    @Override
    public UUID getActivePromptId() {
        return null;
    }

    @Override
    public User getLoggedInUser() {
        return null;
    }

    @Override
    public UUID getLoggedInUserId() {
        return null;
    }

    @Override
    public boolean responseExistsById(UUID responseId) {
        for (Map.Entry<User, List<Response>> entry : responses.entrySet()) {
            List<Response> responses = entry.getValue();
            for (Response response : responses) {
                UUID responseID = response.getResponseId();
                if (responseID == responseId) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void deleteResponse(UUID responseId) {
        for (Map.Entry<User, List<Response>> entry : responses.entrySet()) {
            List<Response> responses = entry.getValue();
            for (Response response : responses) {
                UUID responseID = response.getResponseId();
                if (responseID == responseId) {
                    responses.remove(response);
                    break;
                }

            }
        }
    }
}
