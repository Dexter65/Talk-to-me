package client;

import messages.IMessageInput;
import messages.IMessageViewer;
import messages.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import utils.JSONMessageParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * {@link Client Client} class is responsible for full control
 * of client's logic.
 */
public class Client {
    //private final IMessageViewer messageViewer = ConsoleMessageViewer.consoleMessageViewer;
//    private final IMessageViewer messageViewer = UIMessageViewer.uiMessageViewer;
//    private final IMessageViewer messageViewer = MainWindowController.getMainWindowController();
    private final IMessageViewer userMessageViewer;
//    private Scanner in = new Scanner(System.in);
//    private IMessageInput in = ConsoleMessageInput.consoleMessageInput;
    private final IMessageInput userMessageInput;

    private final String serverHost;
    private final int serverPort;
    private Socket clientSocket;
    private final String clientName;

    private PrintWriter messageOut;
    private Scanner messageInput;

    /**
     * <p>
     *  Trying to connect to the host server. If connection established
     *  starting new thread to receive messages and sends them to <strong>messageViewer</strong>.
     *  Then start in current thread waiting of user input from <strong>in</strong>.
     * </p>
     * <p>
     *  If the connection cannot be established or it was broken
     *  the program will print stack trace and finish the application.
     * </p>
     * @param serverHost String host to connect to the server.
     * @param serverPort int port to connect to the server.
     * @param userMessageViewer instance of {@link IMessageViewer IMessageViewer}
                            its the method that will be used to
                            display the messages.
     */
    public Client(String serverHost, int serverPort,
                  IMessageViewer userMessageViewer, IMessageInput userMessageInput) {
        this.userMessageInput = userMessageInput;
        this.userMessageViewer = userMessageViewer;
        this.serverHost = serverHost;
        this.serverPort = serverPort;

        userMessageViewer.viewMessage(new Message(1, "Клиент", "Введите ваше имя"));
//        clientName = in.nextLine();
        clientName = userMessageInput.getNextLine();
        userMessageViewer.viewMessage(new Message(1, "Клиент",
                "Ваше имя установлено: " + clientName));

        try {
            clientSocket = new Socket(this.serverHost, this.serverPort);
            messageOut = new PrintWriter(clientSocket.getOutputStream());
            messageInput = new Scanner(clientSocket.getInputStream());

            userMessageViewer.viewMessage(new Message(1, "Клиент",
                    "Соединение с сервером успешно установлено"));

            new Thread() {
                @Override
                public void run() {
                    startReceiveMessage();
                }
            }.start();

            while (true) {
//                sendMessage(new Message(200, clientName, in.nextLine()));
                sendMessage(new Message(200, clientName, userMessageInput.getNextLine()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    /**
     * The method creates json from message and sends it
     * to the connected server.
     * @param message instance of {@link Message Message} class.
     *                It will be using to create json message
     *                that will be sent to the server.
     */
    public void sendMessage(Message message) {
        JSONObject json = JSONMessageParser.getJSONFromMessage(message);

        messageOut.println(json);
        messageOut.flush();
    }

    /**
     * The method that starts an infinity while loop
     * of waiting new json message from the server, converting json
     * to instance of {@link Message Message} class and
     * sending it to messageViewer.
     */
    private void startReceiveMessage() {
        while (true) {
            if (messageInput.hasNext()) {
                try {
                    Message message = JSONMessageParser.getMessageFromJSON(messageInput.nextLine());
                    userMessageViewer.viewMessage(message);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
