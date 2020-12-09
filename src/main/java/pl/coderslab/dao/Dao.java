package pl.coderslab.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.EntityModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
@Slf4j
abstract public class Dao<T extends EntityModel> {

    @PersistenceContext
    EntityManager entityManager;

    protected Class<T> type;

    public void save(T entity) {
        log.trace("saving entity (Dao) " + entity.getClass() + entity.getId());
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    public boolean deleteEntity(T entity) {
        log.trace("deleting entity " + entity.getClass() + entity.getId());
        if (entity != null) {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            return true;
        }
        return false;
    }

}

//    public T findById(Long id){
//        log.trace("finding entity (Dao)");
//        return entityManager.find(type, id);
//    }

//    public boolean deleteById(Long id){
//        T entity = findById(id);
//        log.trace("deleting entity " + entity.getClass() + entity.getId());
//        if(entity != null) {
//            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
//            return true;
//        }
//        return false;
//    }

//    public List<Book> findAllByPublisher(Publisher publisher) {
//        Query query = entityManager.createQuery(
//                "SELECT b FROM Book b JOIN FETCH b.authors a JOIN b.publisher p WHERE b.publisher = :publisher");
//        query.setParameter("publisher", publisher);
//        return query.getResultList();
//    }