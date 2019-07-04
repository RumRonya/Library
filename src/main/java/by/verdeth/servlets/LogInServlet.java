package by.verdeth.servlets;

import by.verdeth.dao.userDao.UserDaoImplSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet ("/login")
public class LogInServlet extends HttpServlet {



    @Override
    public void init(){
//        DriverManagerDataSource dataSource;
//        //CreateDataSource createDataSource = new CreateDataSource();
//        dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();
//
//        this.userDao = new UserDaoJdbcImpl(dataSource);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameUser = req.getParameter("user-name");
        String password = req.getParameter("password");
        //int i = 0;

        if (UserDaoImplSingleton.getInstance().getUserDao().isHasUser(nameUser, password))
        {
            resp.sendRedirect("/index");
            //add session
            HttpSession session = req.getSession();
            session.setAttribute("nameUser", nameUser);

            //check admin
            session.setAttribute("isAdmin", UserDaoImplSingleton.getInstance().getUserDao().isHasAdminitrator(nameUser, password));
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
