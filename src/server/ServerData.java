package server;

import messages.console.ConsoleMessageViewer;
import messages.IMessageViewer;

import java.util.ArrayList;

public class ServerData {
    public final static IMessageViewer messageViewer = ConsoleMessageViewer.consoleMessageViewer;
    public final static ArrayList<ClientHandler> clients = new ArrayList<>();
    public final static String version = "v0.1";
    private static String host = "localhost";
    private static int port = 3443;
    private static String name = "Talk to me";
    private static String startServerMessage = "The server has been successfully started!";
    private static String endServerMessage = "The server has been stopped!";
    private static String welcomeServerMessage = "A new member has joined the chat!";
    private static boolean isCheckingChat = true;

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        ServerData.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        ServerData.port = port;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ServerData.name = name;
    }

    public static String getStartServerMessage() {
        return startServerMessage;
    }

    public static void setStartServerMessage(String startServerMessage) {
        ServerData.startServerMessage = startServerMessage;
    }

    public static String getEndServerMessage() {
        return endServerMessage;
    }

    public static void setEndServerMessage(String endServerMessage) {
        ServerData.endServerMessage = endServerMessage;
    }

    public static String getWelcomeServerMessage() {
        return welcomeServerMessage;
    }

    public static void setWelcomeServerMessage(String startServerMessage) {
        ServerData.welcomeServerMessage = startServerMessage;
    }

    public static boolean isCheckingChat() {
        return isCheckingChat;
    }

    public static void setIsCheckingChat(boolean isCheckingChat) {
        ServerData.isCheckingChat = isCheckingChat;
    }
}
