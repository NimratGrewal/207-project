package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PromptTest {
    private Prompt prompt;
    private final UUID uuid = UUID.randomUUID();
    @BeforeEach
    void init(){
        prompt = new Prompt("What's your favorite song", LocalDate.now(), uuid);
    }
    @Test
    void getPromptId() {
        assertEquals(uuid, prompt.getPromptId());
    }

    @Test
    void isActive() {
        assertTrue(prompt.isActive());
    }

    @Test
    void setActive() {
        prompt.setActive(true);
        assertTrue(prompt.isActive());
    }

    @Test
    void getPromptText() {
        assertEquals("What's your favorite song", prompt.getPromptText());
    }

    @Test
    void getPromptDate() {
        assertEquals(LocalDate.now(), prompt.getPromptDate());
    }

    @Test
    void getPromptResponse() {
    }

    @Test
    void setResponses() {
    }
}