package lukaszlusz.GUI;

import javax.swing.*;

public class ErrorBox {
    private String title;
    private String message;

    public ErrorBox(String title, String message) {
        this.title = title;
        this.message = message;
        showOneMoreTime();
    }

    public void showOneMoreTime() {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }
}
