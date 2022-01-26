package client;

/**
 * Client's {@link Main Main} class to start client application.
 * Create instance of {@link Client Clinet} class with
 * default arguments (at now it's localhost and 3443 port).
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client("localhost", 3443);
    }
}
