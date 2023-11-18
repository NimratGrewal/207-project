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

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("userId", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creation_time", 3);
        headers.put("responses",4);

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
                    List<Prompt> responses = new ArrayList<>();
                    for (String responseStr: responseInfo) {
                        String[] responseData = responseStr.split(":");
                        UUID responseID = UUID.fromString(responseData[0]);
                        UUID promptID = UUID.fromString(responseData[1]);
                        String songID = responseData[2];

                        Response response = new Response(responseID, promptID, user, songID);
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
     * @param user The user to be saved
     */
    @Override
    //TODO: add save method to UserSignUpDataAccessInterface
    public void save(User user) {
        accounts.put(user.getUserId(), user);
        this.save();
    }

    @Override
    //TODO: add save method to SaveResponse
    public void save(Response response) {
        responses.put(response.getUser(), response);
    }

    @Override
    public User get(UUID userId) {
        return accounts.get(userId);
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
                StringBuilder responses = new StringBuilder();
                for (Response response : user.getHistory().values()) {
                    //TODO: create getResponseId method in Response class
                    String responseText = "%s:%s:%s".formatted(
                            response.getResponseId(), response.getPromptId(), response.getSongId());
                    responses.append(responseText).append(';');
                }
                responses.deleteCharAt(responses.length() - 1);
                String responseString = responses.toString();
                String line = "%s,%s,%s,%s".formatted(
                        //TODO: create getCreationTime method in User class + interface
                        user.getUsername(), user.getPassword(), user.getCreationTime(), responseString);
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    //TODO: add existsById method to UserSignUpDataAccessInterface??
    public boolean existsById(UUID identifier) {
        return accounts.containsKey(identifier);
    }
}