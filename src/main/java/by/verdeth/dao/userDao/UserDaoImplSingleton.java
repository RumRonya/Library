package by.verdeth.dao.userDao;

import by.verdeth.helpers.CreateDataSource;

public class UserDaoImplSingleton {
    private static UserDaoImplSingleton instance;
    private UserDao userDao;

    private UserDaoImplSingleton() {
        userDao = new UserDaoJdbcImpl(CreateDataSource.getInstance().getDriverManagerDataSource());
    }

    public static UserDaoImplSingleton getInstance() {
        if (instance==null)
        {
            instance = new UserDaoImplSingleton();
        }
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
