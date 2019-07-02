package by.verdeth.servlets;

import by.verdeth.dao.userDao.UserDao;
import by.verdeth.dao.userDao.UserDaoJdbcImpl;
import by.verdeth.helpers.CreateDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        //CreateDataSource createDataSource = new CreateDataSource();
        DriverManagerDataSource dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();

        userDao = new UserDaoJdbcImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameUser = req.getParameter("user-name");
        String password = req.getParameter("password");
        String passwordCheck = req.getParameter("password-check");
        int i = 0;
        if (!password.equals(passwordCheck))
        {
            int j = 0;
            req.getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
        }
        else
        {
            int k = 0;
            if (userDao.addUser(nameUser, password)) {
                req.getServletContext().getRequestDispatcher("/login").forward(req, resp);
            }
            else
            {
                resp.sendRedirect("/register");
            }

        }
    }
}
