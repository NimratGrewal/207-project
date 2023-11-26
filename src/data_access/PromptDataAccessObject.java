package data_access;

import entities.Prompt;
import entities.Response;
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

    private final Map<UUID, List<UUID>> responses = new LinkedHashMap<>();

    public PromptDataAccessObject(String csvPath) throws IOException {


        csvFile = new File(csvPath);
        headers.put("prompt_question", 0);
        headers.put("prompt_ID", 1);
        headers.put("date", 2);
        headers.put("responses", 3);
        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                assert header.equals("prompt_question,prompt_ID,date, responses");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String prompts_string = String.valueOf(col[headers.get("prompt_question")]);
                    UUID promptID = UUID.fromString(String.valueOf(col[headers.get("prompt_ID")]));
                    String dates = String.valueOf(col[headers.get("date")]);
                    String responsesText = String.valueOf(col[headers.get("responses")]);

                    Prompt prompt  = new Prompt(prompts_string, dates);
                    prompts.put(dates, prompt);
                    String[] responseInfo = responsesText.split(";");
                    for(String uuid_string:responseInfo){
                        UUID uuid = UUID.fromString(uuid_string);
                        if (!responses.containsKey(prompt.getPromptId())){
                            responses.put(prompt.getPromptId(), new ArrayList<>());
                        }
                        // for each prompt, add each response uuid as a value.
                        responses.get(prompt.getPromptId()).add(uuid);
                    }
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void save(Prompt prompt, Response response) {        if (!responses.containsKey(prompt.getPromptId())){
        responses.put(prompt.getPromptId(), new ArrayList<>());
    }
        responses.get(prompt.getPromptId()).add(response.getResponseId());
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Prompt prompt : prompts.values()) {
                UUID promptID = prompt.getPromptId();
                List<UUID> prompt_responses = responses.get(promptID);
                StringBuilder all_responses = new StringBuilder();
                int size = prompt_responses.size();
                for (int i = 0; i < size; i++) {
                    UUID ID = prompt_responses.get(i);
                    all_responses.append(ID.toString());
                    // should deal with the case where last UUID has a semicolon
                    if (i < size - 1) {
                        all_responses.append(";");
                    }
                }
                String result = all_responses.toString();
                String line = "%s,%s,%s,%s,%s".formatted(
                        prompt.getPromptText(), prompt.getDate(), prompt.getDate(),prompt.getCreationTime().toString(), result);
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
    public boolean answeredCurrentPrompt(UUID answerID) {
        Prompt curr_prompt = getCurrentPrompt();
        UUID prompt_id = curr_prompt.getPromptId();
        try{
            List<UUID> uuids = responses.get(prompt_id);
            return uuids.contains(answerID);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteResponse(UUID responseID){
        for (Map.Entry<UUID, List<UUID>> entry : responses.entrySet()) {
            List<UUID> responseList = entry.getValue();
            if (responseList.contains(responseID)) {
                responseList.remove(responseID);
                break;
            }
        }
    }

    public List<UUID> getResponses(UUID promptID){
        try{
            return responses.get(promptID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Prompt getPromptByID(UUID promptID){
        Collection <Prompt> prompts_list = prompts.values();
        for(Prompt prompt:prompts_list){
            if (prompt.getPromptId() == promptID){
                return prompt;
            }
        }
        return null;
    }
}
