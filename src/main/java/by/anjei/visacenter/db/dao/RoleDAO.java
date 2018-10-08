package by.anjei.visacenter.db.dao;

import by.anjei.visacenter.db.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();
    Role getRoleById(Integer id);
    boolean addRole(Role role);
    boolean deleteRole(Role role);
}
