package fat.client.resource;

import fat.client.resource.visitor.ResourceVisitor;

public class Repository extends Resource {

    private DatabaseInfo databaseInfo;

    public Repository(String name, Resource parent) {
        super(name, parent);
        getParent().addChild(this);
    }

    public Repository(String name, Resource parent, DatabaseInfo databaseInfo) {
        super(name, parent);
        this.databaseInfo = databaseInfo;
        getParent().addChild(this);
    }

    public DatabaseInfo getDatabaseInfo() {
        return databaseInfo;
    }

    // Prljavstina zbog factory sablona
    public void setDatabaseInfo(DatabaseInfo databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

    @Override
    public void acceptVisitor(ResourceVisitor visitor) {
        visitor.visit(this);
    }

}
