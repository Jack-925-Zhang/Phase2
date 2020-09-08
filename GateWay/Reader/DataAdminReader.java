package GateWay.Reader;

import GateWay.DataBuilder;
import Model.AdminUser;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the reader for the Admins.
 * It would read from the sql database and return the Admins.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */

public class DataAdminReader implements IDataAdminReader{

    private final DataBuilder dataBuilder;
    private final Dao<AdminUser, Integer> adminUsersDao;
    private List<AdminUser> admins;

    /**
     * This method provides the reading of Admins from the SQLite database
     * and it would use queryForAll() method and make Admins base on the
     * specific information from database.
     * @throws  SQLException It would throws the SQLException as long as it is
     * reading the two-way transactions information from the database.
     */

    public DataAdminReader() throws SQLException {
        dataBuilder = DataBuilder.getInstance();
        adminUsersDao = dataBuilder.getAdminUsersDao();
        admins = new ArrayList<>();
        // query for all items in the database
        admins = adminUsersDao.queryForAll();
    }

    /**
     * It is a getter for Admins.
     * @return the list of Admins.
     */

    public List<AdminUser> getAdmins() {
        return admins;
    }
}
