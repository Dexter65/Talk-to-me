package utils;

import messages.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The {@link JSONMessageParser JSONMessageParser} class using to parse String in json object
 * and unparse json to object of Message class.
 */
public class JSONMessageParser {
    /**
     * Returns object of {@link Message Message} class from json format String
     * or throws {@link org.json.simple.parser.ParseException ParseException}
     * if it's not a json string.
     * @param jsonString json format string to get {@link Message Message} object.
     * @return the new object of {@link Message Message} class with parameters from jsonString.
     * @throws ParseException if jsonString is not a json string.
     */
    public static Message getMessageFromJSON(String jsonString) throws ParseException {
        JSONObject json = (JSONObject) new JSONParser().parse(jsonString);

        int messageCode = ((Long) json.get("statusCode")).intValue();
        String from = (String) json.get("from");
        String messageText = (String) json.get("message");
        return new Message(messageCode, from, messageText);
    }

    /**
     * Returns object of {@link JSONObject JSONObject} class from
     * object of {@link Message Message} class.
     * @param message object of {@link Message Message} class
     *                to get {@link JSONObject JSONObject} object.
     * @return {@link JSONObject JSONObject} object obtained from the fields of the given message.
     */
    public static JSONObject getJSONFromMessage(Message message) {
        JSONObject json = new JSONObject();
        json.put("statusCode", message.getStatusCode());
        json.put("from", message.getFrom());
        json.put("message", message.getMessageText());

        return json;
    }
}
