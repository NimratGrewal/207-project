package data_access;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FileUserDataAccessObjectTest {
    private User u1;
    private User u2;
    private UserFactory uf;
    private File f;
    private SpotifyAPICaller c;
    private FileUserDataAccessObject fudao;

    @BeforeEach
    void init() throws IOException {
        uf = new CommonUserFactory();
        f = new File("./test/mock-users.csv");
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(f));
            w.write("userId,username,password,creation_time,responses");
            w.newLine();
            w.write("d7323358-a716-4626-af40-c7da357b1c97,momomo,abc,2022-12-03T10:15:30,e0fb67a3-f40b-40d9-97f7-9347ece87055:2c83780a-6c01-4989-b503-7397fe881bf6:11dFghVXANMlKmJXsNCbNl;e0fb67a3-f40b-40d9-97f7-9347ece87055:2c83780a-6c01-4989-b503-7397fe881bf6:11dFghVXANMlKmJXsNCbNl");
            w.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        c = new SpotifyAPICaller("216b6438ba554128ade0b63afa48ddd8", "56e33aff04bc4aa28dc3d9e54bb231cd");
        fudao = new FileUserDataAccessObject(f.getPath(), uf, c);
        u1 = uf.create(UUID.fromString("78920e42-5a5b-45ad-93fe-4f4a93d61311"), "momo", "abc", LocalDateTime.parse("2007-12-03T10:15:30"));
        u2 = uf.create(UUID.fromString("8c7664fc-63d8-446d-bb04-d7e638114e21"), "haya", "123", LocalDateTime.parse("2007-12-03T10:15:30"));
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
            fudao = new FileUserDataAccessObject(f.getPath(), uf, c);
        } catch (IOException e) {
            fail("No error expected");
        }
    }

    @Test
    void save() {
        fudao.save(u1);
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String header = reader.readLine();
            assert header.equals("userId,username,password,creation_time,responses");
            reader.readLine();
            assertEquals("78920e42-5a5b-45ad-93fe-4f4a93d61311,momo,abc,2007-12-03T10:15:30,null", reader.readLine());
        } catch (IOException e) {
            fail("No exception expected!");
        }
    }

    @Test
    void getLoggedInUser() {
        User u3 = uf.create(UUID.fromString("d7323358-a716-4626-af40-c7da357b1c97"), "jawad", "non", LocalDateTime.now());
        fudao.setLoggedInUser(u3);
        assertEquals(u3.getUserId(), fudao.getLoggedInUser().getUserId());
        assertEquals(u3.getUsername(), fudao.getLoggedInUser().getUsername());
        assertEquals(u3.getNumberOfResponses(), fudao.getLoggedInUser().getNumberOfResponses());
    }

    @Test
    void getLoggedInUserNull() {
        assertNull(fudao.getLoggedInUser());
    }

    @Test
    void getUser() {
        fudao.save(u1);
        assertEquals(fudao.getUser(u1.getUserId()), u1);
    }

    @Test
    void getResponseIds() {
        Response r1 = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        Response r2 = new Response(UUID.randomUUID(), UUID.randomUUID(), u2.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        u1.setResponse(UUID.randomUUID(), r1);
        u2.setResponse(UUID.randomUUID(), r2);
        fudao.save(u1);
        fudao.save(u2);
        assertEquals(fudao.getResponseIds(u1).get(0), r1.getResponseId());
        assertEquals(1, fudao.getResponseIds(u1).size());
    }

    @Test
    void setResponse() {
        Response r = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        fudao.save(u1);
        fudao.setLoggedInUser(u1);
        fudao.setResponse(r);
        assertEquals(fudao.getResponseById(r.getResponseId()), r);
        assertInstanceOf(Response.class, fudao.getResponseById(r.getResponseId()));
        assertEquals(fudao.getResponseById(r.getResponseId()).getUserId(), fudao.getLoggedInUser().getUserId());
    }

    @Test
    void getResponseById() {
        Response r = new Response(UUID.randomUUID(), u1.getUserId(), UUID.randomUUID(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        fudao.save(u1);
        fudao.setLoggedInUser(u1);
        fudao.setResponse(r);
        assertEquals(r, fudao.getResponseById(u1.getUserId(), r.getResponseId()));
        assertEquals(r, fudao.getResponseById(r.getResponseId()));
        assertEquals(u1.getResponse(r.getPromptId()), fudao.getResponseById(u1.getUserId(), r.getResponseId()));
    }

    @Test
    void getResponseByIdNull() {
        Response r = new Response(UUID.randomUUID(), u1.getUserId(), UUID.randomUUID(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        fudao.save(u1);
        fudao.setLoggedInUser(u1);
        assertNull(fudao.getResponseById(u1.getUserId(), r.getResponseId()));
        assertNull(fudao.getResponseById(r.getResponseId()));
        Response r1 = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        Response r2 = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        fudao.setResponse(r1);
        fudao.setResponse(r2);
        assertNull(fudao.getResponseById(r.getResponseId()));
        assertNull(fudao.getResponseById(u1.getUserId(), r.getResponseId()));
    }

    @Test
    void responseExistsByIdNotExists() {
        Response r = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        fudao.save(u1);
        fudao.setLoggedInUser(u1);
        Response r1 = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        Response r2 = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        fudao.setResponse(r1);
        fudao.setResponse(r2);
        assertFalse(fudao.responseExistsById(r.getResponseId()));
    }

    @Test
    void responseExistsByIdExists() {
        Response r1 = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        Response r2 = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        fudao.save(u1);
        fudao.setLoggedInUser(u1);
        fudao.setResponse(r1);
        fudao.setResponse(r2);
        assertTrue(fudao.responseExistsById(r1.getResponseId()));
    }

    @Test
    void deleteResponse() {
        Response r1 = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        Response r2 = new Response(UUID.randomUUID(), UUID.randomUUID(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));
        fudao.save(u1);
        fudao.setLoggedInUser(u1);
        fudao.setResponse(r1);
        fudao.setResponse(r2);
        assertEquals(2, fudao.getResponseIds(u1).size());
        assertEquals(2, u1.getHistory().size());
        fudao.deleteResponse(r1.getResponseId());
        assertEquals(1, fudao.getResponseIds(u1).size());
        assertEquals(1, u1.getHistory().size());
    }

    @Test
    void setLoggedInUser() {
        fudao.setLoggedInUser(u1);
        assertEquals(u1, fudao.getLoggedInUser());
    }

}