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
        String query = "select * from role";
        try {
            PreparedStatement preparedStatement = dbmanager.executeQuery(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role getRoleById() {
        return null;
    }

    @Override
    public boolean addRole(Role role) {
        return false;
    }

    @Override
    public boolean deleteRole(Role role) {
        return false;
    }
}
