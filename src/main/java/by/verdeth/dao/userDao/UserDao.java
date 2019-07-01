package by.verdeth.dao.userDao;

import by.verdeth.dao.CrudDao;
import by.verdeth.models.User;

public interface UserDao extends CrudDao<User> {
    boolean isHasUser(String nameUser, String password);
    boolean isHasAdminitrator(String nameUser, String password);
    boolean addUser(String nameUser, String password);
}
