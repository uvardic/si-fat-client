package fat.client.resource.persistence.implementor;

import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MySQLPersistenceImplementor implements PersistenceImplementor {

    @Override
    public void deleteById(Object table, Object id) {

    }

    @Override
    public Map<String, Object> save(Object table, Map<String, Object> request) {
        if (!(table instanceof Entity))
            throw new IllegalArgumentException("Table must be an Entity!");

        Entity entity = (Entity) table;
        String SQL = String.format("INSERT INTO %s VALUES (%s)", entity.getName(), parseRequest(request));
        System.out.println(SQL);
        try (Statement statement = Repository.getConnection().createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return request;
    }

    private String parseRequest(Map<String, Object> request) {
        System.out.println(request);

        String values =  request.values().stream()
                .map(o -> String.format("'%s',", o))
                .collect(Collectors.joining());

        // remove last ,
        return values.substring(0, values.length() - 1);
    }

    @Override
    public Map<String, Object> update(Object table, Object existingId, Map<String, Object> request) {
        return null;
    }

    @Override
    public Map<String, Object> findById(Object table, Object id) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findAll(Object table) {
        if (!(table instanceof Entity))
            throw new IllegalArgumentException("Table must be an Entity!");

        Entity entity = (Entity) table;
        String SQL = "SELECT * FROM " + entity.getName();
        List<Map<String, Object>> foundObjects = new ArrayList<>();

        try (Statement statement = Repository.getConnection().createStatement()) {
            ResultSet selectResult = statement.executeQuery(SQL);

            while (selectResult.next()) {
                Map<String, Object> objectMap = new LinkedHashMap<>();

                for (Resource child : entity.getChildren())
                    objectMap.put(child.getName(), selectResult.getObject(child.getName()));

                foundObjects.add(objectMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundObjects;
    }
}
