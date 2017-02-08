package lukaszlusz.windows;

import javax.swing.*;

public class Dialog {
    public static DialogResult RETRY_CLOSE(String message) {
        Object[] options = { "Spróbój ponownie", "Zamknij" };
        int i = JOptionPane.showOptionDialog(null,
                message,
                "Błąd",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);
        if (i == 0) return DialogResult.RETRY;
        else return DialogResult.CLOSE;
    }

    public static DialogResult YES_NO(String message) {
        Object[] options = {"Tak", "Nie"};
        int i = JOptionPane.showOptionDialog(null,
                message,
                "Pytanie",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (i == 0) return DialogResult.YES;
        else return DialogResult.NO;
    }
}
