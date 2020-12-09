package pl.coderslab.dao;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;

import javax.persistence.Query;
import java.util.List;

@Repository
@Slf4j
public class UserDao extends Dao<User> {

//    public UserDao() {
//        type = User.class;
//    }

    @Override
    public void save(User entity) {
        log.trace("saving User " + entity.getId());
        if (entity.getPassword() != null)
            entity.setPassword(hashPassword(entity.getPassword()));
        if (entity.getId() == null){
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    public User findUserByComitId(String queryComitId) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.comitId = :queryComitId");
        query.setParameter("queryComitId", queryComitId);
        return (User) query.getSingleResult();
    }

    public List<User> findAllUsers() {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u");
        return query.getResultList();
    }

    public List<User> findAllByGroup(UserGroup userGroup) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.userGroup = :userGroup");
        query.setParameter("userGroup", userGroup);
        return query.getResultList();
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean passwordMatches(User user, String passwordToCheck) {
        return BCrypt.checkpw(passwordToCheck, user.getPassword());
    }

    public boolean passwordMatches(String comitId, String passwordToCheck) {
        return passwordMatches(findUserByComitId(comitId).getPassword(), passwordToCheck);
    }

}
