package fat.client.resource.persistence.implementor;

import java.util.List;
import java.util.Map;

public interface PersistenceImplementor {

    void deleteById(Object table, Object id);

    Map<String, Object> save(Object table, Map<String, Object> request);

    Map<String, Object> update(Object table, Object existingId, Map<String, Object> request);

    Map<String, Object> findById(Object table, Object id);

    List<Map<String, Object>> findAll(Object table);

}
