package lukaszlusz.GUI;

import javax.swing.*;

public class ErrorBox {
    private String title = "Błąd";
    private String message;

    public ErrorBox(String message) {
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
