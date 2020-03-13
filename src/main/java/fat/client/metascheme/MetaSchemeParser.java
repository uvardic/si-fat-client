package fat.client.metascheme;

import fat.client.gui.MainFrame;
import fat.client.resource.AttributeDescriptionType;
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

        System.out.println("Parsing entities...");
        while (entityResult.next())
            parseEntityMetaData(entityResult);

        entityResult.beforeFirst();

        System.out.println("Parsing relations...");
        while (entityResult.next())
            parseRelationsFor(entityResult.getString("TABLE_NAME"));
    }

    private static void parseEntityMetaData(ResultSet entityResult) throws SQLException {
        String name = entityResult.getString("TABLE_NAME");
        Resource parent = MainFrame.getInstance().getTree().getRoot().getResource();
        Resource entity = resourceFactory.getResource(ResourceType.ENTITY, name, parent);

        ResultSet attributeResult = Repository.getMetaData().getColumns(null, null, entity.getName(), null);

        System.out.println(String.format("Parsing attributes for %s...", entity.getName()));
        while (attributeResult.next())
            parseAttributeMetaData(attributeResult, entity);
    }

    private static void parseAttributeMetaData(ResultSet attributeResult, Resource parent) throws SQLException {
        String name = attributeResult.getString("COLUMN_NAME");
        Resource attribute = resourceFactory.getResource(ResourceType.ATTRIBUTE, name, parent);
        parseAttributeDescriptions(attributeResult, attribute);
    }

    private static void parseAttributeDescriptions(ResultSet attributeResult, Resource attribute) throws SQLException {
        parseAttributeDescription(
                attribute, AttributeDescriptionType.PRIMARY_KEY, String.valueOf(isPrimaryKey(attribute))
        );
        parseAttributeDescription(
                attribute, AttributeDescriptionType.DATA_TYPE, attributeResult.getString("DATA_TYPE")
        );
        parseAttributeDescription(
                attribute, AttributeDescriptionType.COLUMN_SIZE, attributeResult.getString("COLUMN_SIZE")
        );
        parseAttributeDescription(
                attribute, AttributeDescriptionType.NULLABLE, attributeResult.getString("IS_NULLABLE")
        );
        parseAttributeDescription(
                attribute, AttributeDescriptionType.AUTOINCREMENT, attributeResult.getString("IS_AUTOINCREMENT")
        );
    }

    private static void parseAttributeDescription(Resource attribute, AttributeDescriptionType type, String value) {
        resourceFactory.getResource(ResourceType.ATTRIBUTE_DESCRIPTION, formatName(type, value), attribute);
    }

    public static String formatName(AttributeDescriptionType type, String value) {
        return String.format("%s(%s)", type.name(), value);
    }

    private static boolean isPrimaryKey(Resource attribute) throws SQLException {
        ResultSet primaryKeyAttributeResult = Repository.getMetaData().getPrimaryKeys(
                null, null, attribute.getParent().getName()
        );

        while (primaryKeyAttributeResult.next()) {
            if (primaryKeyAttributeResult.getString("COLUMN_NAME").equals(attribute.getName()))
                return true;
        }

        return false;
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