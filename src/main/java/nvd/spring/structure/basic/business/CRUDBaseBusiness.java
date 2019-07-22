package nvd.spring.structure.basic.business;

import java.util.List;
import java.util.Optional;

public interface CRUDBaseBusiness<E, ID> extends BaseBusniness {

    public Optional<E> save(E entity);
    public List<E> saveAll(List<E> list);

    public boolean delete(E entity);

    public Optional<E> get(ID id);
    public List<E> getAll(List<ID> ids);
}
