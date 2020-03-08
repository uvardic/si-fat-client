package fat.client.resource.persistence;

import java.util.List;

public interface Persistence {

    void deleteById(Object id);

    Object save(Object request);

    Object findById(Object id);

    List<Object> findAll();

}
