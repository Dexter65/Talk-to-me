package client;

import messages.ConsoleMessageViewer;
import messages.IMessageViewer;
import messages.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import utils.JSONMessageParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final IMessageViewer messageViewer = ConsoleMessageViewer.consoleMessageViewer;
    private final String serverHost;
    private final int serverPort;
    private Socket clientSocket;
    private Scanner in = new Scanner(System.in);
    private final String clientName;

    private PrintWriter messageOut;
    private Scanner messageInput;


    public Client(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;

        messageViewer.viewMessage(new Message(1, "Клиент", "Введите ваше имя"));
        clientName = in.nextLine();

        try {
            clientSocket = new Socket(this.serverHost, this.serverPort);
            messageOut = new PrintWriter(clientSocket.getOutputStream());
            messageInput = new Scanner(clientSocket.getInputStream());

            messageViewer.viewMessage(new Message(1, "Клиент", "Соединение с сервером успешно установлено"));

            new Thread() {
                @Override
                public void run() {
                    startReceiveMessage();
                }
            }.start();

            while (true) {
                sendMessage(new Message(200, clientName, in.nextLine()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(Message message) {
        JSONObject json = JSONMessageParser.getJSONFromMessage(message);

        messageOut.println(json);
        messageOut.flush();
    }

    private void startReceiveMessage() {
        while (true) {
            if (messageInput.hasNext()) {
                try {
                    Message message = JSONMessageParser.getMessageFromJSON(messageInput.nextLine());
                    messageViewer.viewMessage(message);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
