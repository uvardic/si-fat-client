package fat.client.metascheme;

import fat.client.gui.MainFrame;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Resource;
import fat.client.resource.factory.ResourceFactory;
import fat.client.resource.factory.ResourceType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaSchemeParser {

    private static final ResourceFactory resourceFactory = new ResourceFactory();

    private MetaSchemeParser() {}

    public static void parseDatabaseMetaData() throws SQLException {
        ResultSet entityResult = Repository.getMetaData().getTables(
                null, null, null, new String[]{"TABLE"}
        );

        while (entityResult.next())
            parseEntityMetaData(entityResult);

        entityResult.beforeFirst();

        while (entityResult.next())
            parseRelationsFor(entityResult.getString("TABLE_NAME"));
    }

    private static void parseEntityMetaData(ResultSet entityResult) throws SQLException {
        String name = entityResult.getString("TABLE_NAME");
        Resource parent = MainFrame.getInstance().getTree().getRoot().getResource();
        Resource entity = resourceFactory.getResource(ResourceType.ENTITY, name, parent);
        parseAttributeMetaData(entity);
    }

    private static void parseAttributeMetaData(Resource parent) throws SQLException {
        ResultSet primaryKeyAttributeResult = Repository.getMetaData().getPrimaryKeys(null, null, parent.getName());

        while (primaryKeyAttributeResult.next()) {
            Attribute attribute = (Attribute) resourceFactory.getResource(
                    ResourceType.ATTRIBUTE, primaryKeyAttributeResult.getString("COLUMN_NAME"), parent
            );

            attribute.setKey(true);
        }

        ResultSet attributeResult = Repository.getMetaData().getColumns(null, null, parent.getName(), null);

        while (attributeResult.next())
            resourceFactory.getResource(
                    ResourceType.ATTRIBUTE, attributeResult.getString("COLUMN_NAME"), parent
            );
    }

    private static void parseRelationsFor(String tableName) throws SQLException {
        ResultSet foreignKeys = Repository.getMetaData().getImportedKeys(null, null, tableName);
        Resource parent = MainFrame.getInstance().getTree().getRoot().getResource();
        Entity owner = (Entity) parent.findChildByName(tableName);

        while (foreignKeys.next()) {
            Entity relation = (Entity) parent.findChildByName(foreignKeys.getString("PKTABLE_NAME"));
            owner.addRelation(relation);
        }
    }

}
