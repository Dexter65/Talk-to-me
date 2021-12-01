package utils;

import messages.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONMessageParser {
    public static Message getMessageFromJSON(String jsonString) throws ParseException {
        JSONObject json = (JSONObject) new JSONParser().parse(jsonString);

        int messageCode = ((Long) json.get("statusCode")).intValue();
        String from = (String) json.get("from");
        String messageText = (String) json.get("message");
        return new Message(messageCode, from, messageText);
    }

    public static JSONObject getJSONFromMessage(Message message) {
        JSONObject json = new JSONObject();
        json.put("statusCode", message.getStatusCode());
        json.put("from", message.getFrom());
        json.put("message", message.getMessageText());

        return json;
    }
}
