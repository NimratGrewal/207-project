package data_access;

import entities.Prompt;
import entities.User;
import entities.UserFactory;
import use_case.login.PromptDataAccessInterface;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class PromptDataAccessObject implements PromptDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Prompt> prompts = new LinkedHashMap<>();

    private final Map<UUID, UUID> responses = new LinkedHashMap<>();

    public PromptDataAccessObject(String csvPath) throws IOException {


        csvFile = new File(csvPath);
        headers.put("prompt_question", 0);
        headers.put("date", 1);
        headers.put("creation_time", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                assert header.equals("prompt_question,date,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String prompts_string = String.valueOf(col[headers.get("prompt_question")]);
                    String dates = String.valueOf(col[headers.get("date")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);

                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    Prompt prompt  = new Prompt(prompts_string, dates);
                    prompts.put(dates, prompt);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void save(Prompt prompt) {
        prompts.put(prompt.getPromptText(), prompt);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Prompt prompt : prompts.values()) {
                String line = "%s,%s,%s".formatted(
                        prompt.getPromptText(), prompt.getDate(), prompt.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prompt getCurrentPrompt() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDateTime date = LocalDateTime.now();
        String currentDate = dateFormat.format(date);
        try{
            return(prompts.get(currentDate));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean answeredCurrentPrompt(UUID promptID) {

    }
}
