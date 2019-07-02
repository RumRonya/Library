package by.verdeth.dao.bookDao;

import by.verdeth.helpers.CreateDataSource;

public class BookDaoImplSingleton {

    private static BookDaoImplSingleton instance;
    private BookDao bookDao;

    private BookDaoImplSingleton()
    {
        bookDao = new BookDaoJdbcImpl(CreateDataSource.getInstance().getDriverManagerDataSource());
    }

    public static BookDaoImplSingleton getInstance()
    {
        if (instance==null)
        {
            instance = new BookDaoImplSingleton();
        }
        return instance;
    }

    public BookDao getBookDao() {
        return bookDao;
    }
}
