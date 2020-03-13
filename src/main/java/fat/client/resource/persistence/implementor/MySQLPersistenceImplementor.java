package fat.client.resource.persistence.implementor;

import fat.client.resource.Entity;
import fat.client.resource.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLPersistenceImplementor implements PersistenceImplementor {

    @Override
    public void deleteById(Object table, Object id) {

        if (!(table instanceof Entity))
            throw new IllegalArgumentException("Table must be an Entity!");
    }

    @Override
    public Object save(Object table, Object request) {
        return new Object[0];
    }

    @Override
    public Object update(Object table, Object existingId, Object request) {
        return new Object[0];
    }

    @Override
    public Object findById(Object table, Object id) {
        return new Object[0];
    }

    @Override
    public List<Object> findAll(Object table) {
        if (!(table instanceof Entity))
            throw new IllegalArgumentException("Table must be an Entity!");

        Entity entity = (Entity) table;
        String SQL = "SELECT * FROM " + entity.getName();
        List<Object> foundObjects = new ArrayList<>();

        try (Statement statement = Repository.getConnection().createStatement()) {
            ResultSet selectResult = statement.executeQuery(SQL);

            while (selectResult.next()) {
                Object[] object = new Object[entity.getChildren().size()];

                for (int i = 0; i < entity.getChildren().size(); i++)
                    object[i] = selectResult.getObject(entity.getChildren().get(i).getName());

                foundObjects.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundObjects;
    }
}
