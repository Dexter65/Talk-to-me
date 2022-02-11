package messages.console;

import messages.IMessageViewer;
import messages.Message;

public class ConsoleMessageViewer implements IMessageViewer {
    public static final ConsoleMessageViewer consoleMessageViewer = new ConsoleMessageViewer();

    private ConsoleMessageViewer() { }

    @Override
    public void viewMessage(Message message) {
        System.out.println("[" + message.getFrom() + "]\t" + message.getMessageText());
    }

    @Override
    public void viewMessage(String message) {
        viewMessage(new Message(message));
    }
}
