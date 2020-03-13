package fat.client.resource.persistence;

import java.util.List;

public interface Persistence {

    void deleteById(Object table, Object id);

    Object save(Object table, Object request);

    Object update(Object table, Object existingId, Object request);

    Object findById(Object table, Object id);

    List<Object> findAll(Object table);

}
