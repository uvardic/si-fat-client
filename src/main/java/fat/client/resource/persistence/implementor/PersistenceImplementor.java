package fat.client.resource.persistence.implementor;

import java.util.List;

public interface PersistenceImplementor {

    void deleteById(Object id);

    Object save(Object request);

    Object findById(Object id);

    List<Object> findAll();

}
