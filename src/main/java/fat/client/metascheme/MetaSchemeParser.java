package fat.client.metascheme;

import fat.client.gui.MainFrame;
import fat.client.resource.Attribute;
import fat.client.resource.DatabaseInfo;
import fat.client.resource.Repository;
import fat.client.resource.Resource;
import fat.client.resource.factory.ResourceFactory;
import fat.client.resource.factory.ResourceType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.IntStream;

// todo check values
public class MetaSchemeParser {

    private static final ResourceFactory resourceFactory = new ResourceFactory();

    private static Resource createResource(JSONObject JSON, ResourceType type, Resource parent) {
        String name = JSON.getString(MetaSchemeKeyword.NAME.name());
        Resource resource = resourceFactory.getResource(type, name, parent);
        resource.addObserver(MainFrame.getInstance().getTree());
        return resource;
    }

    private MetaSchemeParser() {}

    public static void parseSchema(String schemaPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(schemaPath))) {
            parseSchemaWorker(new JSONObject(new JSONTokener(reader)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void parseSchemaWorker(JSONObject workspaceJSON) {
        Resource parent = MainFrame.getInstance().getTree().getRoot().getResource();

        JSONArray repositoryJSONs = workspaceJSON.getJSONArray(MetaSchemeKeyword.REPOSITORY.name());

        IntStream.range(0, repositoryJSONs.length())
                .mapToObj(repositoryJSONs::getJSONObject)
                .forEach(repositoryJSON -> parseRepositoryJSON(repositoryJSON, parent));
    }

    private static void parseRepositoryJSON(JSONObject repositoryJSON, Resource parent) {
        Resource repository = createResource(repositoryJSON, ResourceType.REPOSITORY, parent);
        ((Repository) repository).setDatabaseInfo(parseDatabaseInfo(repositoryJSON));

        JSONArray entityJSONs = repositoryJSON.getJSONArray(MetaSchemeKeyword.ENTITY.name());

        IntStream.range(0, entityJSONs.length())
                .mapToObj(entityJSONs::getJSONObject)
                .forEach(entityJSON -> parseEntityJSON(entityJSON, repository));
    }

    private static DatabaseInfo parseDatabaseInfo(JSONObject repositoryJSON) {
        return new DatabaseInfo.Builder()
                .server(repositoryJSON.getString(MetaSchemeKeyword.SERVER.name()))
                .name(repositoryJSON.getString(MetaSchemeKeyword.DATABASE.name()))
                .username(repositoryJSON.getString(MetaSchemeKeyword.USERNAME.name()))
                .password(repositoryJSON.getString(MetaSchemeKeyword.PASSWORD.name()))
                .create();
    }

    private static void parseEntityJSON(JSONObject entityJSON, Resource parent) {
        Resource entity = createResource(entityJSON, ResourceType.ENTITY, parent);

        // todo relations

        JSONArray attributeJSONs = entityJSON.getJSONArray(MetaSchemeKeyword.ATTRIBUTE.name());

        IntStream.range(0, attributeJSONs.length())
                .mapToObj(attributeJSONs::getJSONObject)
                .forEach(attributeJSON -> parseAttributeJSON(attributeJSON, entity));
    }

    private static void parseAttributeJSON(JSONObject attributeJSON, Resource parent) {
        Resource attribute = createResource(attributeJSON, ResourceType.ATTRIBUTE, parent);
        ((Attribute) attribute).setKey(attributeJSON.getBoolean(MetaSchemeKeyword.KEY.name()));
    }

}
