package data_access;

import entities.Prompt;
import entities.Response;
import entities.User;
import entities.UserFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileUserDataAccessObject {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<UUID, User> accounts = new LinkedHashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("userId", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creation_time", 3);
        headers.put("prompts",4);
        headers.put("responses",5);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("userId,username,password,creation_time,prompts,responses");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    UUID userId = UUID.fromString(String.valueOf(col[headers.get("userId")]));
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String promptsText = String.valueOf(col[headers.get("prompts")]);
                    String responsesText = String.valueOf(col[headers.get("responses")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    String[] promptIds = promptsText.split(";");
                    //FilePromptDataAccessObject needs a getPromptById method
                    List<Prompt> prompts = new ArrayList<>();
                    for (String promptID: promptIds) {
                    }
                    String[] responseIDs = responsesText.split(";");
                    //FileResponsesDataAccessObject needs a getResponseById method
                    List<Prompt> responses = new ArrayList<>();
                    for (String responseID: responseIDs) {

                    }
                    User user = userFactory.create(userId, username, password, ldt);
                    accounts.put(userId, user);
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
                StringBuilder prompts = new StringBuilder();
                for (Prompt prompt : user.getHistory().keySet()) {
                    prompts.append(prompt.getPromptId()).append(";");
                }
                String promptsString = prompts.toString();

                StringBuilder responses = new StringBuilder();
                for (Response response : user.getHistory().values()) {
                    //TODO: create getResponseId method in Prompt class
                    responses.append(response.getResponseId()).append(';');
                }
                String responseString = responses.toString();
                String line = "%s,%s,%s,%s,%s".formatted(
                        //TODO: create getCreationTime method in User class + interface
                        user.getUsername(), user.getPassword(), user.getCreationTime(), promptsString, responseString);
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
    //TODO: add existsByName method to UserSignUpDataAccessInterface
    public boolean existsByName(UUID identifier) {
        return accounts.containsKey(identifier);
    }

}
