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
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet ("/login")
public class LogInServlet extends HttpServlet {

    private UserDao userDao;


    @Override
    public void init() throws ServletException {

        DriverManagerDataSource dataSource;
        CreateDataSource createDataSource = new CreateDataSource();

        dataSource = createDataSource.getDriverManagerDataSource();
        this.userDao = new UserDaoJdbcImpl(dataSource);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameUser = req.getParameter("user-name");
        String password = req.getParameter("password");
        int i = 0;
        if (userDao.isHasUser(nameUser, password))
        {
            resp.sendRedirect("/index");
            //add session
            HttpSession session = req.getSession();
            session.setAttribute("nameUser", nameUser);
        }
        else {
            resp.sendRedirect("/login");
        }
        //super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }
}
