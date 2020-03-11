package fat.client.gui.resourcepanel.tablepanel.table;

import fat.client.resource.Resource;

import javax.swing.*;
import java.util.Objects;

public class Table extends JTable {

    private final Resource resource;

    public Table(Resource resource) {
        this.resource = resource;

        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setModel(new TableModel(this));
    }

    public Resource getResource() {
        return resource;
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

}
