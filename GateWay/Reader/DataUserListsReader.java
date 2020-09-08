package GateWay.Reader;

import GateWay.DataBuilder;

import UseCases.UserUseCase.UserOperation;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the reader for the UserLists.
 * It would read from the sql database and return the UserLists.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */

public class DataUserListsReader implements IDataUserListsReader{

    private final DataBuilder dataBuilder;

    private final Dao<UserOperation, Integer> userOperationDao;
    private List<UserOperation> userOperations;

    /**
     * This method provides the reading of UserLists from the SQLite database
     * and it would use queryForAll() method and make UserLists base on the
     * specific information from database.
     * @throws  SQLException It would throws the SQLException as long as it is
     * reading the two-way transactions information from the database.
     */

    public DataUserListsReader() throws SQLException {
        dataBuilder = DataBuilder.getInstance();
        userOperationDao = dataBuilder.getUserOperationDao();
        userOperations = new ArrayList<UserOperation>();
        // query for all items in the database
        userOperations = userOperationDao.queryForAll();
    }

    /**
     * It is a getter for UserLists.
     * @return the list of UserLists.
     */

    @Override
    public List<UserOperation> getUserOperations() {
        return userOperations;
    }
}
