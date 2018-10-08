package by.anjei.visacenter.db.dao;

import by.anjei.visacenter.db.model.Role;
import by.anjei.visacenter.db.util.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private DBManager dbmanager;

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String query = "select * from role order by id";
        try {
            PreparedStatement preparedStatement = dbmanager.executeQuery(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Role role = new Role(id, name);
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role getRoleById(Integer id) {
        Role role = new Role();
        String query = "select name from role where id = ?";
        try {
            PreparedStatement preparedStatement = dbmanager.executeQuery(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                role.setId(id);
                role.setName(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public boolean addRole(Role role) {
        String query = "insert into role (name) values (?)";
        try {
            PreparedStatement preparedStatement = dbmanager.executeQuery(query);
            preparedStatement.setString(1, role.getName());
            int flag = preparedStatement.executeUpdate();
            if (flag == -1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteRole(Role role) {
        String query = "delete from role where (id, name) values (?, ?)";
        try {
            PreparedStatement preparedStatement = dbmanager.executeQuery(query);
            preparedStatement.setInt(1, role.getId());
            preparedStatement.setString(2, role.getName());
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
