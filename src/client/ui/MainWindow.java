package client.ui;


import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Talk to me");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        new Thread() {
            @Override
            public void run() {
                Client client = new Client("localhost", 3443,
                        MainWindowController.getMainWindowController(),
                        MainWindowController.getMainWindowController());
            }
        }.start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}
