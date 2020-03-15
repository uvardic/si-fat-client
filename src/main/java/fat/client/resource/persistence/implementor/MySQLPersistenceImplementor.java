package fat.client.resource.persistence.implementor;

import fat.client.gui.Dialog;
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
    public void deleteById(Object table, Map<String, Object> id) {
        if (!(table instanceof Entity))
            throw new IllegalArgumentException("Table must be an Entity!");

        Entity entity = (Entity) table;
        String SQL = String.format(
                "DELETE FROM %s WHERE (%s) = (%s)", entity.getName(), getMapKeys(id), getMapValues(id)
        );
        try (Statement statement = Repository.getConnection().createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            Dialog.error("Delete Error", e.getMessage());
            System.err.println(SQL);
        }
    }

    private String getMapKeys(Map<String, Object> map) {
        String keys = map.keySet().stream()
                .map(key -> String.format("%s,", key))
                .collect(Collectors.joining());

        // remove last ,
        return keys.substring(0, keys.length() - 1);
    }


    private String getMapValues(Map<String, Object> map) {
        String values =  map.values().stream()
                .map(value -> String.format("'%s',", value))
                .collect(Collectors.joining());

        // remove last ,
        return values.substring(0, values.length() - 1);
    }

    @Override
    public Map<String, Object> save(Object table, Map<String, Object> request) {
        if (!(table instanceof Entity))
            throw new IllegalArgumentException("Table must be an Entity!");

        Entity entity = (Entity) table;
        String SQL = String.format("INSERT INTO %s VALUES (%s)", entity.getName(), getMapValues(request));
        try (Statement statement = Repository.getConnection().createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            Dialog.error("Save Error", e.getMessage());
            System.err.println(SQL);
        }

        return request;
    }

    @Override
    public Map<String, Object> update(Object table, Map<String, Object> existingId, Map<String, Object> request) {
        if (!(table instanceof Entity))
            throw new IllegalArgumentException("Table must be an Entity!");

        Entity entity = (Entity) table;
        String SQL = String.format(
                "UPDATE %s SET %s WHERE %s", entity.getName(), getRequestQuery(request),
                createConditionFromId(existingId)
        );

        try (Statement statement = Repository.getConnection().createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            Dialog.error("Find Error", e.getMessage());
            System.err.println(SQL);
        }

        return request;
    }

    private String getRequestQuery(Map<String, Object> request) {
        StringBuilder requestQuery = new StringBuilder();

        request.keySet().forEach(
                key -> requestQuery.append(key)
                        .append(" = '")
                        .append(request.get(key))
                        .append("', ")
        );

        // remove the last ', '
        return requestQuery.substring(0, requestQuery.length() - 2);
    }

    private String createConditionFromId(Map<String, Object> id) {
        StringBuilder condition = new StringBuilder();

        id.keySet().forEach(
                key -> condition.append(key)
                        .append(" = '")
                        .append(id.get(key))
                        .append("' AND ")
        );

        // remove the last ' AND '
        return condition.substring(0, condition.length() - 5);
    }

    @Override
    public Map<String, Object> findById(Object table, Map<String, Object> id) {
        if (!(table instanceof Entity))
            throw new IllegalArgumentException("Table must be an Entity!");

        Entity entity = (Entity) table;
        String SQL = String.format("SELECT * FROM %s WHERE %s", entity.getName(), createConditionFromId(id));
        Map<String, Object> foundObject = new LinkedHashMap<>();

        try (Statement statement = Repository.getConnection().createStatement()) {
            ResultSet selectResult = statement.executeQuery(SQL);

            if (selectResult.next()) {
                for (Resource child : entity.getChildren())
                    foundObject.put(child.getName(), selectResult.getObject(child.getName()));
            }
        } catch (SQLException e) {
            Dialog.error("Find Error", e.getMessage());
            System.err.println(SQL);
        }

        return foundObject;
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
            Dialog.error("Find All Error", e.getMessage());
            System.err.println(SQL);
        }

        return foundObjects;
    }
}
