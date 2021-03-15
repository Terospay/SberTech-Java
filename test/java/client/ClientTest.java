package client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    @Test
    void infoTest() {
        Client cl = (Client) ClientType.INDIVIDUAL.createClient("Alex", "12345", "some information", true);
        assertEquals("Alex 12345 some information", cl.info());
    }

    @Test
    void typeCorrect() {
        Client cl = (Client) ClientType.HOLDING.createClient("ООО АБВ", "12345", "some information", true);
        assertEquals("client.Holding", cl.getClass().getName());
    }
}
