package by.verdeth.dao.seriaDao;

import by.verdeth.dao.CrudDao;
import by.verdeth.models.Seria;

import java.util.HashMap;

public interface SeriaDao extends CrudDao<Seria> {
    HashMap<String, Integer> findAllSeriasAndCountBooks();
}
