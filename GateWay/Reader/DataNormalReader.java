package GateWay.Reader;

import GateWay.DataBuilder;
import Model.*;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the reader for the NormalUser.
 * It would read from the sql database and return the NormalUser.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */

public class DataNormalReader implements IDataNormalReader{

    private final DataBuilder dataBuilder;

    private final Dao<NormalUser, Integer> normalUsersDao;
    private final List<NormalUser> normals;

    /**
     * This method provides the reading of NormalUser from the SQLite database
     * and it would use queryForAll() method and make NormalUsers base on the
     * specific information from database.
     * @throws  SQLException It would throws the SQLException as long as it is
     * reading the two-way transactions information from the database.
     */

    public DataNormalReader() throws Exception {
        List<NormalUser> normals1;
        dataBuilder = DataBuilder.getInstance();
        normalUsersDao = dataBuilder.getNormalUsersDao();
        normals1 = new ArrayList<>();
        // query for all items in the database
        normals1 = normalUsersDao.queryForAll();
        normals = normals1;
    }
    /**
     * It is a getter for normalUsers.
     * @return the list of normalUsers.
     */
    public List<NormalUser> getNormalUsersAccounts() {
        return normals;
    }
}
