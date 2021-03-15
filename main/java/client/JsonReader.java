package client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    public static Client jsonRead(String path) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(path));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        String inn = (String) jo.get("inn");
        String describing = (String) jo.get("describing");
        boolean isSanctioned = (boolean) jo.get("isSanctioned");
        String clientType = (String) jo.get("clientType");
        switch (clientType) {
            case "LegalEntity":
                return new LegalEntity(name, inn, describing, isSanctioned);
            case "Holding":
                return new Holding(name, inn, describing, isSanctioned);
            default:
                return new Individual(name, inn, describing, isSanctioned);
        }
    }
}
