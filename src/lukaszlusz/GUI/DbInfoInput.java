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

    public DbInfoInput() {
        prepareGUI();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DbInfo dbInfo = new DbInfo();
                dbInfo.address = addressTextField.getText();
                dbInfo.dbName = dbNameTextField.getText();
                dbInfo.port = portTextField.getText();
                dbInfo.user = userTextField.getText();
                dbInfo.password = new String(passwordField.getPassword());
                if (dbInfo.isCorrect()) {
                    ConfigWriter.WRITE_DB_INFO(dbInfo);
                    frame.dispose();
                } else {
                    wrongDataLabel.setVisible(true);
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });
    }

    public void prepareGUI() {
        frame = new JFrame("Wprowadź dane dostępu do bazy danych");
        wrongDataLabel.setVisible(false);
        frame.setPreferredSize(new Dimension(700, 400));
        frame.setContentPane(contentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
