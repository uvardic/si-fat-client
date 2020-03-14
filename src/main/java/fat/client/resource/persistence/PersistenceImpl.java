package fat.client.resource.persistence;

import fat.client.resource.persistence.implementor.PersistenceImplementor;

import java.util.List;
import java.util.Map;

public class PersistenceImpl implements Persistence {

    private final PersistenceImplementor implementor;

    public PersistenceImpl(PersistenceImplementor implementor) {
        this.implementor = implementor;
    }

    @Override
    public void deleteById(Object table, Map<String, Object> id) {
        implementor.deleteById(table, id);
    }

    @Override
    public Map<String, Object> save(Object table, Map<String, Object> request) {
        return implementor.save(table, request);
    }

    @Override
    public Map<String, Object> update(Object table, Map<String, Object> existingId, Map<String, Object> request) {
        return implementor.update(table, existingId, request);
    }

    @Override
    public Map<String, Object> findById(Object table, Map<String, Object> id) {
        return implementor.findById(table, id);
    }

    @Override
    public List<Map<String, Object>> findAll(Object table) {
        return implementor.findAll(table);
    }
}
