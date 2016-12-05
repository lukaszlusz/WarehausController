package lukaszlusz.GUI;

import lukaszlusz.config.ConfigWriter;
import lukaszlusz.config.DbInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DbInfoInput {
    private JFrame frame;
    private JPanel contentPanel;
    private JLabel addressLabel;
    private JTextField addressTextField;
    private JLabel dbNameLabel;
    private JTextField dbNameTextField;
    private JLabel portLabel;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField portTextField;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel wrongDataLabel;

    private DbInfo dbInfo;
    private boolean isDbInfoLoaded = false;

    public DbInfoInput() {
        prepareGUI();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 DbInfo dbInfoLocal = new DbInfo();
                dbInfoLocal.address = addressTextField.getText();
                dbInfoLocal.dbName = dbNameTextField.getText();
                dbInfoLocal.port = portTextField.getText();
                dbInfoLocal.user = userTextField.getText();
                dbInfoLocal.password = new String(passwordField.getPassword());
                if (dbInfoLocal.isCorrect()) {
                    isDbInfoLoaded =true;
                    dbInfo = dbInfoLocal;
                    frame.dispose();
                } else wrongDataLabel.setVisible(true);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });
    }

    public boolean isDbInfoAvailable() {
        if (dbInfo!= null && isDbInfoLoaded && dbInfo.isCorrect()) return true;
        else return false;
    }

    public void waitUntilDataAvailable() {
        while (!isDbInfoAvailable());
    }

    public DbInfo getDbInfo() {
        return dbInfo;
    }

    private void prepareGUI() {
        frame = new JFrame("Wprowadź dane dostępu do bazy danych");
        wrongDataLabel.setVisible(false);
        frame.setPreferredSize(new Dimension(700, 400));
        frame.setContentPane(contentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
