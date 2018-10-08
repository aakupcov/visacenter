package by.anjei.visacenter.db.dao;

import by.anjei.visacenter.db.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserByLogin(String login);
    boolean addUser(User user);
    boolean deleteUser(User user);
}
