package by.anjei.visacenter.db.dao;

import by.anjei.visacenter.db.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();
    Role getRoleById();
    boolean addRole(Role role);
    boolean deleteRole(Role role);
}
