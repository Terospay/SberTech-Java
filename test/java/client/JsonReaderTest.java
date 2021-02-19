package client;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void jsonRead() throws IOException, ParseException {
        Client cl = (Client) JsonReader.jsonRead("src\\test\\resources\\testJson.json");
        assertEquals("ООО Матрешка 13242352 some information", cl.info());
    }
}