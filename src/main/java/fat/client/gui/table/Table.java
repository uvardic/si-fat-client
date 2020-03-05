package fat.client.gui.table;

import fat.client.resource.Resource;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;

public class Table extends JTable {

    private final Resource resource;

    public Table(Resource resource) {
        this.resource = resource;
        setName(resource.getName());
        initialize();
    }

    private void initialize() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setModel(new TableModel());
    }

    public String format() {
        return String.format("%s - %s", resource.getParent().getName(), resource.getName());
    }

    @Override
    public String toString() {
        return String.format("Table{resource=%s}", resource);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Table) {
            Table other = (Table) o;

            return Objects.equals(this.resource, other.resource);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resource);
    }

    private class TableModel extends DefaultTableModel {

        @Override
        public int getColumnCount() {
            return resource.getChildren().size();
        }

        @Override
        public String getColumnName(int column) {
            return resource.getChildren().get(column).getName();
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    }

}
