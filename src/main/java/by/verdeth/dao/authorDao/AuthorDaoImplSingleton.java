package by.verdeth.dao.authorDao;

import by.verdeth.helpers.CreateDataSource;

public class AuthorDaoImplSingleton {

    private static AuthorDaoImplSingleton instance;
    private AuthorDao authorDao;

    private AuthorDaoImplSingleton()
    {
        authorDao  = new AuthorDaoJdbcImpl(CreateDataSource.getInstance().getDriverManagerDataSource());
    }

    public static AuthorDaoImplSingleton getInstance()
    {
        if (instance == null)
        {
            instance = new AuthorDaoImplSingleton();
        }
        return instance;
    }

    public AuthorDao getAuthorDao() {
        return authorDao;
    }
}
