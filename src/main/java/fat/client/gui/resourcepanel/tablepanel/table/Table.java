package fat.client.gui.resourcepanel.tablepanel.table;

import fat.client.resource.Resource;

import javax.swing.*;
import java.util.Objects;

public class Table extends JTable {

    private final Resource resource;

    private final TableModel model;

    public Table(Resource resource) {
        this.resource = resource;
        this.model = new TableModel(this);

        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setModel(model);
    }

    public Resource getResource() {
        return resource;
    }

    public String format() {
        return resource.getName();
    }

    public void updateTable() {
        model.updateTable();
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

}
