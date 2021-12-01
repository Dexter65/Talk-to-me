package messages;

public class Message {
    private final int statusCode;
    private final String from;
    private final String messageText;

    public Message(int statusCode, String from, String messageText) {
        this.messageText = messageText;
        this.statusCode = statusCode;
        this.from = from;
    }

    public Message(String messageText) {
        this(200, "Server", messageText);
    }

    public String getMessageText() {
        return messageText;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getFrom() {
        return from;
    }
}
