package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.User;

import javax.persistence.Query;

@Repository
public class UserDao extends Dao<User> {

    public UserDao() {
        type = User.class;
    }

    public User findUserByComitId(String queryComitId) {
        Query query = entityManager.createQuery(
                "SELECT u FROM users u WHERE comitId = :queryComitId");
        query.setParameter("queryComitId", queryComitId);
        return (User) query.getSingleResult();
    }

}
