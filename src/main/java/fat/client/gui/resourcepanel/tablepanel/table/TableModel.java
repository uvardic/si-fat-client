package fat.client.gui.resourcepanel.tablepanel.table;

import fat.client.resource.persistence.Persistence;
import fat.client.resource.persistence.PersistenceImpl;
import fat.client.resource.persistence.implementor.MySQLPersistenceImplementor;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class TableModel extends DefaultTableModel {

    private final Table table;

    TableModel(Table table) {
        this.table = table;
        fillTable();
    }

    private void fillTable() {
        Persistence persistence = new PersistenceImpl(new MySQLPersistenceImplementor());
        List<Map<String, Object>> foundObjects = persistence.findAll(table.getResource());

        for (Map<String, Object> objectMap : foundObjects)
            addRow(objectMap.values().toArray());
    }

    void updateTable() {
        setRowCount(0);
        fillTable();
    }

    @Override
    public int getColumnCount() {
        return table.getResource().getChildren().size();
    }

    @Override
    public String getColumnName(int column) {
        return table.getResource().getChildren().get(column).getName();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("TableModel{table=%s}", table);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TableModel) {
            TableModel other = (TableModel) o;

            return Objects.equals(this.table, other.table);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(table);
    }

}
