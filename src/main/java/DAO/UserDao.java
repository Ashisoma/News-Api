package DAO;

import models.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> getAllUser();

    User getUserById(int id);

}
