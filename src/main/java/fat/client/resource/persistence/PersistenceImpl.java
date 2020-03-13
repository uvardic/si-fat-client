package fat.client.resource.persistence;

import fat.client.resource.persistence.implementor.PersistenceImplementor;

import java.util.List;

public class PersistenceImpl implements Persistence {

    private final PersistenceImplementor implementor;

    public PersistenceImpl(PersistenceImplementor implementor) {
        this.implementor = implementor;
    }

    @Override
    public void deleteById(Object table, Object id) {
        implementor.deleteById(table, id);
    }

    @Override
    public Object save(Object table, Object request) {
        return implementor.save(table, request);
    }

    @Override
    public Object update(Object table, Object existingId, Object request) {
        return implementor.update(table, existingId, request);
    }

    @Override
    public Object findById(Object table, Object id) {
        return implementor.findById(table, id);
    }

    @Override
    public List<Object> findAll(Object table) {
        return implementor.findAll(table);
    }
}
