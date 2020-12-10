package pl.coderslab.service;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;

import javax.persistence.Query;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        log.trace("constructing UserServiceImpl");
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findByComitId(String comitId) {
        return this.userDao.findUserByComitId(comitId);
    }

    @Override
    public boolean deleteByComitId(String comitId) {
        return this.userDao.deleteEntity(findByComitId(comitId));
    }

    @Override
    public boolean passwordMatches(User user, String passwordToCheck) {
        return userDao.passwordMatches(passwordToCheck, user.getPassword());
    }

    @Override
    public boolean passwordMatches(String comitId, String passwordToCheck) {
        return passwordMatches(findByComitId(comitId).getPassword(), passwordToCheck);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userDao.findAllUsers();
    }

    @Override
    public List<User> findAllByGroup(UserGroup userGroup) {
        return this.findAllByGroup(userGroup);
    }
}
