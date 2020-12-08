package pl.coderslab.dao;

import org.springframework.stereotype.Component;
import pl.coderslab.entity.EntityModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
abstract public class Dao<T extends EntityModel> {

    @PersistenceContext
    EntityManager entityManager;

    protected Class<T> type;

    public void save(T entity){
        if(entity.getId() == null){
            entityManager.persist(entity);
        }else{
            entityManager.merge(entity);
        }
    }

    public T findById(Long id){
        return entityManager.find(type, id);
    }

    public boolean deleteById(Long id){
        T entity = findById(id);
        if(entity != null) {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            return true;
        }
        return false;
    }

}

//    public List<Book> findAllByPublisher(Publisher publisher) {
//        Query query = entityManager.createQuery(
//                "SELECT b FROM Book b JOIN FETCH b.authors a JOIN b.publisher p WHERE b.publisher = :publisher");
//        query.setParameter("publisher", publisher);
//        return query.getResultList();
//    }