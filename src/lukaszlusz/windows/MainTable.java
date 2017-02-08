package lukaszlusz.windows;

import lukaszlusz.library.sql.Database;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainTable extends JTable{
    public MainTable(ResultSet mainResultSet, ResultSet statusesResultSet, ResultSet categoriesResultSet) {
        prepareGUI(mainResultSet, statusesResultSet, categoriesResultSet);
    }

    private void prepareGUI(ResultSet mainResultSet, ResultSet statusesResultSet, ResultSet categoriesResultSet) {
        String[] nonEditableColumns = {"ItemID"};
        setModel(new QueryResultTableModel(mainResultSet, nonEditableColumns));
        JComboBox statusesComboBox = prepareComboBox(statusesResultSet);
        JComboBox categoriesComboBox = prepareComboBox(categoriesResultSet);
        getColumn("Status").setCellEditor(new DefaultCellEditor(statusesComboBox));
        getColumn("Kategoria").setCellEditor(new DefaultCellEditor(categoriesComboBox));
        setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
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
