package messages;

import client.ui.MainWindowController;

public class UIMessageViewer implements IMessageViewer {
    public static final UIMessageViewer uiMessageViewer = new UIMessageViewer();
    private static MainWindowController mainWindowController;

    @Override
    public void viewMessage(Message message) {
        //mainWindowController.printMessage(message);
    }

    @Override
    public void viewMessage(String message) {
        viewMessage(new Message(message));
    }

    public static void setMainWindowController(MainWindowController mwc) {
        mainWindowController = mwc;
    }
}
