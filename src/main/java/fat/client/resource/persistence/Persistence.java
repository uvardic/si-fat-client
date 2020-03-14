package fat.client.resource.persistence;

import java.util.List;
import java.util.Map;

public interface Persistence {

    void deleteById(Object table, Map<String, Object> id);

    Map<String, Object> save(Object table, Map<String, Object> request);

    Map<String, Object> update(Object table, Map<String, Object> existingId, Map<String, Object> request);

    Map<String, Object> findById(Object table, Map<String, Object> id);

    List<Map<String, Object>> findAll(Object table);

}
