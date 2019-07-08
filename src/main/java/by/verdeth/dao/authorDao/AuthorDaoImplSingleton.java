package by.verdeth.dao.authorDao;

import by.verdeth.helpers.CreateDataSource;

//Pattern Singleton for implements Author DAO
public class AuthorDaoImplSingleton {

    //for pattern
    private static AuthorDaoImplSingleton instance;

    //for impl AuthorDAO
    private AuthorDao authorDao;

    //private constructor for
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
