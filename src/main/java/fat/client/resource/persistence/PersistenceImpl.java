package fat.client.resource.persistence;

import fat.client.resource.persistence.implementor.PersistenceImplementor;

import java.util.List;

public class PersistenceImpl implements Persistence {

    private final PersistenceImplementor implementor;

    public PersistenceImpl(PersistenceImplementor implementor) {
        this.implementor = implementor;
    }

    @Override
    public void deleteById(Object id) {
        implementor.deleteById(id);
    }

    @Override
    public Object save(Object request) {
        return implementor.save(request);
    }

    @Override
    public Object findById(Object id) {
        return implementor.findById(id);
    }

    @Override
    public List<Object> findAll() {
        return implementor.findAll();
    }
}
