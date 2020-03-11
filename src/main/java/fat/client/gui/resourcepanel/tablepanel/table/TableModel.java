package fat.client.gui.resourcepanel.tablepanel.table;

import javax.swing.table.DefaultTableModel;
import java.util.Objects;

class TableModel extends DefaultTableModel {

    private final Table table;

    TableModel(Table table) {
        this.table = table;
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
