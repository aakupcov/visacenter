package by.anjei.visacenter.servlet;

import by.anjei.visacenter.db.dao.RoleDAO;
import by.anjei.visacenter.db.dao.RoleDAOImpl;
import by.anjei.visacenter.db.dao.UserDAO;
import by.anjei.visacenter.db.dao.UserDAOImpl;
import by.anjei.visacenter.db.model.Role;
import by.anjei.visacenter.db.model.User;
import by.anjei.visacenter.db.util.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    DBManager dbmanager;
    RoleDAO roleDAO;
    UserDAO userDAO;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbmanager = (DBManager) getServletContext().getAttribute("dbmanager");
        roleDAO = new RoleDAOImpl();
        userDAO = new UserDAOImpl();

        StringBuilder requestDispatcherURL = new StringBuilder("/jsp/");

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {

        } else if (user.getRoleId() == null) {

        } else {
            Role role = roleDAO.getRoleById(user.getRoleId());
            switch (role.getName()) {
                case "client" :
                case "manager" :
                case "head_manager" : requestDispatcherURL.append(role.getName()).append(".jsp"); break;
                default: break;
            }
        }

        request.getRequestDispatcher(requestDispatcherURL.toString()).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
