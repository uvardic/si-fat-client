package fat.client.metascheme;

import fat.client.gui.MainFrame;
import fat.client.resource.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MetaSchemeWriter {

    private static JSONObject rootJSON = new JSONObject();

    private static JSONObject repositoryJSON = new JSONObject();

    private static JSONObject entityJSON = new JSONObject();

    private static JSONObject attributeJSON = new JSONObject();

    private MetaSchemeWriter() {}

    public static void write(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.write(serializeRoot().toString(4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static JSONObject serializeRoot() {
        rootJSON = new JSONObject();
        Resource workspace = MainFrame.getInstance().getTree().getRoot().getResource();
        appendRepositories(workspace);
        return rootJSON;
    }

    private static void appendRepositories(Resource workspace) {
        workspace.getChildren()
                .stream()
                .map(child -> (Repository) child)
                .forEach(MetaSchemeWriter::appendRepository);
    }

    private static void appendRepository(Repository repository) {
        serializeRepository(repository);
        rootJSON.append(MetaSchemeKeyword.REPOSITORY.name(), repositoryJSON);
    }

    private static void serializeRepository(Repository repository) {
        repositoryJSON = new JSONObject();
        repositoryJSON.put(MetaSchemeKeyword.NAME.name(), repository.getName());
        putDatabaseInfo(repository.getDatabaseInfo());
        appendEntities(repository);
    }

    private static void putDatabaseInfo(DatabaseInfo databaseInfo) {
        repositoryJSON.put(MetaSchemeKeyword.SERVER.name(), databaseInfo.getServer());
        repositoryJSON.put(MetaSchemeKeyword.DATABASE.name(), databaseInfo.getName());
        repositoryJSON.put(MetaSchemeKeyword.USERNAME.name(), databaseInfo.getUsername());
        repositoryJSON.put(MetaSchemeKeyword.PASSWORD.name(), databaseInfo.getPassword());
    }

    private static void appendEntities(Repository repository) {
        repository.getChildren()
                .stream()
                .map(child -> (Entity) child)
                .forEach(MetaSchemeWriter::appendEntity);
    }

    private static void appendEntity(Entity entity) {
        serializeEntity(entity);
        repositoryJSON.append(MetaSchemeKeyword.ENTITY.name(), entityJSON);
    }

    private static void serializeEntity(Entity entity) {
        entityJSON = new JSONObject();
        entityJSON.put(MetaSchemeKeyword.NAME.name(), entity.getName());
        entityJSON.put(MetaSchemeKeyword.RELATIONS.name(), mapRelationsToJSON(entity));
        appendAttributes(entity);
    }

    private static JSONArray mapRelationsToJSON(Entity entity) {
        JSONArray relationsJSON = new JSONArray();
        entity.getRelations().forEach(relation -> relationsJSON.put(relation.getName()));
        return relationsJSON;
    }

    private static void appendAttributes(Entity entity) {
        entity.getChildren()
                .stream()
                .map(child -> (Attribute) child)
                .forEach(MetaSchemeWriter::appendAttribute);
    }

    private static void appendAttribute(Attribute attribute) {
        serializeAttribute(attribute);
        entityJSON.append(MetaSchemeKeyword.ATTRIBUTE.name(), attributeJSON);
    }

    private static void serializeAttribute(Attribute attribute) {
        attributeJSON = new JSONObject();
        attributeJSON.put(MetaSchemeKeyword.NAME.name(), attribute.getName());
        attributeJSON.put(MetaSchemeKeyword.KEY.name(), attribute.isKey());
    }

}
