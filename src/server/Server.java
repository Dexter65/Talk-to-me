package server;

import messages.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(ServerData.getPort());
            ServerData.messageViewer.viewMessage("Host: " + ServerData.getHost());
            ServerData.messageViewer.viewMessage("Port: " + ServerData.getPort());
            ServerData.messageViewer.viewMessage(ServerData.getStartServerMessage());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                sendMessageToChat(new Message(ServerData.getWelcomeServerMessage()));

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                ServerData.clients.add(clientHandler);

                new Thread(clientHandler).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stopServer();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void stopServer() throws IOException {
        serverSocket.close();
        ServerData.messageViewer.viewMessage(ServerData.getEndServerMessage());
    }

    public void sendMessageToChat(Message message) {
        if (ServerData.isCheckingChat()){
            ServerData.messageViewer.viewMessage(message);
        }

        for (ClientHandler client : ServerData.clients) {
            client.receiveMessage(message);
        }
    }
}
