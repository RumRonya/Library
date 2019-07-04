package by.verdeth.servlets;

import by.verdeth.dao.userDao.UserDaoImplSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    @Override
    public void init() {
//        //CreateDataSource createDataSource = new CreateDataSource();
//        DriverManagerDataSource dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();
//
//        userDao = new UserDaoJdbcImpl(dataSource);
    }

    //get-method url 'register'
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //just show register page
        req.getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    //post-method url 'register'
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get parameters from req, if they is any
        String nameUser = req.getParameter("user-name");
        String password = req.getParameter("password");
        String passwordCheck = req.getParameter("password-check");

        //check, if get all parameter
        //if there aren't, refresh page 'register'
        if ((nameUser==null)||(password==null)||(passwordCheck==null))
        {
           resp.sendRedirect("/register");
        }

        //if password and passwordCheck aren't same
        if (!password.equals(passwordCheck))
        {
            req.getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
        }

        //add user to db
        if (UserDaoImplSingleton.getInstance().getUserDao().addUser(nameUser, password))
        {
            req.getServletContext().getRequestDispatcher("/login").forward(req, resp);
        }

        //if user didn't added
        resp.sendRedirect("/register");
    }
}
