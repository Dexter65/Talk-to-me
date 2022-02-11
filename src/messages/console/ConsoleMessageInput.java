package messages.console;

import messages.IMessageInput;
import messages.Message;

import java.util.Scanner;

public class ConsoleMessageInput implements IMessageInput {
    public static final ConsoleMessageInput consoleMessageInput = new ConsoleMessageInput();
    private Scanner in = new Scanner(System.in);

    private ConsoleMessageInput() {

    }

    @Override
    public String getNextLine() {
        return in.nextLine();
    }
}
