package lukaszlusz.GUI;

import lukaszlusz.config.DbInfo;
import lukaszlusz.sql.DBConnector;
import lukaszlusz.sql.DBConnectorMySQL;
import lukaszlusz.sql.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainTable extends JTable {
    public MainTable(ResultSet resultSet) {
        prepareGUI(resultSet);
    }

    private void prepareGUI(ResultSet resultSet) {
        String[] nonEditableColumns = {"ItemID"};
        setModel(new QueryResultTableModel(resultSet, nonEditableColumns));
        try {
            JComboBox statusesComboBox = prepareComboBox(Database.getInstance().getStatuses());
            JComboBox categoriesComboBox = prepareComboBox(Database.getInstance().getCategories());
            getColumn("Status").setCellEditor(new DefaultCellEditor(statusesComboBox));
            getColumn("Category").setCellEditor(new DefaultCellEditor(categoriesComboBox));
        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorBox("Błąd połączenia z bazą dancyh");
        }
    }

    private JComboBox prepareComboBox(ResultSet resultSet) {
        JComboBox jComboBox = new JComboBox();
        try {
            while (resultSet.next()) {
                jComboBox.addItem(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jComboBox;
    }
}
