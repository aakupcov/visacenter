package by.anjei.visacenter.db.dao;

import by.anjei.visacenter.db.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserByName();
    boolean addUser(User user);
    boolean deleteUser(User user);
}
