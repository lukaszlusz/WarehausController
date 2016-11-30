package lukaszlusz.GUI;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.Map;

public class QueryResultTableModel extends AbstractTableModel {
    private Map<String, String> fieldNameToColumnNameMap = null;
    private String[] nonEditableColumns = null;
    private ResultSet resultSet = null;

    /** Constructs model where columns headers will be taken from fields names from database.
     * By defalaut field are editable.
     * @param resultSet result of SQL query from JDBC package, must be SCROLL_SENSITIVE and CONCUR_UPDATABLE **/
    public QueryResultTableModel(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    /** Constructs model where columns headers will be taken from given map.
     * By defalaut field are editable.
     * @param resultSet result of SQL query from JDBC package, must be SCROLL_INTENSIVE  and CONCUR_UPDATABLE
     * @param fieldNameToColumnNameMap Map with name of field from database as key and name which will be shown as column header**/
    public QueryResultTableModel(ResultSet resultSet, Map<String, String> fieldNameToColumnNameMap) {
        this.resultSet = resultSet;
        this.fieldNameToColumnNameMap = fieldNameToColumnNameMap;
    }

    /** Constructs model where columns headers will be taken from given map and database fields names given in String[] won't be editable.
     * By defalaut field are editable.
     * @param resultSet result of SQL query from JDBC package, must be SCROLL_INTENSIVE  and CONCUR_UPDATABLE
     * @param fieldNameToColumnNameMap Map with name of field from database as key and name which will be shown as column header
     * @param nonEditableColumns database fields names which wont't be editable**/
    public QueryResultTableModel(ResultSet resultSet, Map<String, String> fieldNameToColumnNameMap, String[] nonEditableColumns) {
        this.fieldNameToColumnNameMap = fieldNameToColumnNameMap;
        this.nonEditableColumns = nonEditableColumns;
        this.resultSet = resultSet;
    }

    @Override
    public String getColumnName(int column) {
        ResultSetMetaData resultSetMetaData;
        String columnName = "Błąd";
        String translatedColumnName;
        try {
            resultSetMetaData = resultSet.getMetaData();
            columnName = resultSetMetaData.getColumnName(++column);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (fieldNameToColumnNameMap != null) {
            translatedColumnName = fieldNameToColumnNameMap.get(columnName);
            if (translatedColumnName != null) return translatedColumnName;
        }
        return columnName;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (nonEditableColumns != null) {
            ResultSetMetaData resultSetMetaData;
            String columnName = "Błąd";
            try {
                resultSetMetaData = resultSet.getMetaData();
                columnName = resultSetMetaData.getColumnName(++columnIndex);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (String s : nonEditableColumns) {
                if (s.equalsIgnoreCase(columnName)) return false;
            }
        }
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            resultSet.absolute(++rowIndex);
            resultSet.updateObject(++columnIndex, aValue);
            resultSet.updateRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        try {
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        ResultSetMetaData resultSetMetaData;
        int columnCount = 0;
        try {
            resultSetMetaData = resultSet.getMetaData();
            columnCount = resultSetMetaData.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        try {
            resultSet.absolute(++rowIndex);
            value = resultSet.getObject(++columnIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
}
