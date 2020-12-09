package pl.coderslab.service;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;

import java.util.List;

public interface UserService {

    void save(User user);
    User findByComitId(String comitId);
    boolean deleteByComitId(String comitId);
    List<User> findAllUsers();
    List<User> findAllByGroup(UserGroup userGroup);
}
