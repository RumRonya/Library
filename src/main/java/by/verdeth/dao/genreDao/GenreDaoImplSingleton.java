package by.verdeth.dao.genreDao;

import by.verdeth.helpers.CreateDataSource;

public class GenreDaoImplSingleton {

    private static GenreDaoImplSingleton instance;
    private GenreDao genreDao;

    private GenreDaoImplSingleton()
    {
        genreDao = new GenreDaoJdbcImpl(CreateDataSource.getInstance().getDriverManagerDataSource());
    }

    public static GenreDaoImplSingleton getInstance() {
        if (instance==null)
        {
            instance = new GenreDaoImplSingleton();
        }
        return instance;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }
}
