package data_access;

import entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.cglib.core.Local;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PromptDataAccessObjectTest {

    private Prompt p1;

    private Response a1;
    private Prompt p2;

    private Prompt p3;
    private UserFactory uf;
    private File f;
    private SpotifyAPICaller c;
    private PromptDataAccessObject pudao;
    @BeforeEach
    void init() throws IOException {
        uf = new CommonUserFactory();
        f = new File("./test/mock-prompts.csv");
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(f));
            w.write("prompt_question,prompt_ID,date,responses");
            w.newLine();
            w.write("what's your favorite song,b569900c-c426-4d87-9014-384bf5514cdc,2023-12-04,b123131c-c426-4d87-9014-384bf5514cdc");
            w.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        p3 = new Prompt("what's your favorite song?", LocalDate.now(), UUID.fromString("b569900c-c426-4d87-9014-384bf5514cdc"));
        c = new SpotifyAPICaller("216b6438ba554128ade0b63afa48ddd8", "56e33aff04bc4aa28dc3d9e54bb231cd");
        pudao = new PromptDataAccessObject(f.getPath());
        UUID uuid = UUID.fromString("acfd0f7a-314d-4ac8-98d7-d87144f8eebb");
        p1 = new Prompt("how are you?", LocalDate.now(), uuid);
        UUID responseId = UUID.fromString("a527c399-ea0e-4d43-a41e-30f787a166d0");
        UUID userId = UUID.fromString("5b4a3f56-9005-4b7b-ab43-673793776624");
        List<String> artist = new ArrayList<>();
        artist.add("Shakira");
        a1 = new Response(responseId, uuid, userId, new Song("songId",
                "hips don't lie", artist, "some album", null));
    }
    @AfterEach
    void tearDown() {
        try {
            PrintWriter writer = new PrintWriter(f);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            fail("no exception should have been thrown!");
        }
    }

    @Test
    void emptyFile() {
        try {
            PrintWriter writer = new PrintWriter(f);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            fail("no exception should have been thrown!");
        }
        try {
            pudao = new PromptDataAccessObject(f.getPath());
        } catch (IOException e) {
            fail("No error expected");
        }
    }
    @Test
    void save() {
        pudao.save(p1, a1);
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String header = reader.readLine();
            assert header.equals("prompt_question,prompt_ID,date,responses");
            reader.readLine();
            assertEquals("what's your favorite song,b569900c-c426-4d87-9014-384bf5514cdc,2023-12-04,b123131c-c426-4d87-9014-384bf5514cdc", reader.readLine());
        } catch (IOException e) {
            fail("No exception expected!");
        }
    }

    @Test
    void getCurrentPrompt() {
        assertEquals(pudao.getCurrentPrompt().getPromptId(), p3.getPromptId());
    }

    @Test
    void answeredCurrentPrompt() {
    }

    @Test
    void deleteResponse() {
        pudao.deleteResponse(UUID.fromString("b123131c-c426-4d87-9014-384bf5514cdc"));
        assertEquals(pudao.getResponses(UUID.fromString("b569900c-c426-4d87-9014-384bf5514cdc")), new ArrayList<>());
    }

    @Test
    void getResponses() {
        ArrayList list = new ArrayList<>();
        list.add(UUID.fromString("b123131c-c426-4d87-9014-384bf5514cdc"));
        assertEquals(pudao.getResponses(UUID.fromString("b569900c-c426-4d87-9014-384bf5514cdc")),list);
    }

    @Test
    void getPromptByID() {
        String string = new String("what's your favorite song");
        assert pudao.getPromptByID(UUID.fromString("b569900c-c426-4d87-9014-384bf5514cdc")).getPromptText().equals(string);
    }

    @Test
    void setResponse() {
    }
}