package server;

import messages.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import utils.JSONMessageParser;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Server server;
    private final PrintWriter messageOut;
    private final Scanner messageInput;

    public ClientHandler(Socket clientSocket, Server server) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;

        messageOut = new PrintWriter(clientSocket.getOutputStream());
        messageInput = new Scanner(clientSocket.getInputStream());
    }

    public void receiveMessage(Message message) {
        //messageOut.writeObject(message);
        JSONObject json = JSONMessageParser.getJSONFromMessage(message);

        messageOut.println(json);
        messageOut.flush();
    }

    @Override
    public void run() {
        while (messageInput.hasNext()) {
            try {
                Message message = JSONMessageParser.getMessageFromJSON(messageInput.nextLine());
                server.sendMessageToChat(message);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        ServerData.clients.remove(this); // may break because there is no synchronization
        server.sendMessageToChat(new Message("Клиент отключился"));
    }

}
