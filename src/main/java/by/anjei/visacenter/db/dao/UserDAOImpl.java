package by.anjei.visacenter.db.dao;

import by.anjei.visacenter.db.model.User;
import by.anjei.visacenter.db.util.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private DBManager dbManager;

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "select * from user order by id";
        try {
            PreparedStatement preparedStatement = dbManager.executeQuery(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                Integer roleId = resultSet.getInt("role_id");
                User user = new User(id, login, password, roleId);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        String query = "select (id, password, role_id) from user where name = ?";
        try {
            PreparedStatement preparedStatement = dbManager.executeQuery(query);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                Integer roleId = resultSet.getInt("role_id");
                user = new User(id, login, password, roleId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        String query = "insert into user (login, password, role_id) values (?, ?, ?)";
        return executeQuery(user, query);
    }

    @Override
    public boolean deleteUser(User user) {
        String query = "delete from user (login, password, role_id) values (?, ?, ?)";
        return executeQuery(user, query);
    }

    private boolean executeQuery(User user, String query) {
        try {
            PreparedStatement preparedStatement = dbManager.executeQuery(query);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRoleId());
            int flag = preparedStatement.executeUpdate();
            if (flag == -1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
