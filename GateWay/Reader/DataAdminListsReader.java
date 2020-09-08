package GateWay.Reader;

import GateWay.DataBuilder;
import UseCases.UserUseCase.AdminOperation;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the reader for the AdminLists.
 * It would read from the sql database and return the AdminLists.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */

public class DataAdminListsReader implements IDataAdminListsReader{

    private final DataBuilder dataBuilder;

    private final Dao<AdminOperation, Integer> AdminOperationDao;
    private List<AdminOperation> AdminOperations;

    /**
     * This method provides the reading of AdminLists from the SQLite database
     * and it would use queryForAll() method and make AdminLists base on the
     * specific information from database.
     * @throws  SQLException It would throws the SQLException as long as it is
     * reading the two-way transactions information from the database.
     */

    public DataAdminListsReader() throws SQLException {
        dataBuilder = DataBuilder.getInstance();
        AdminOperationDao = dataBuilder.getAdminOperationDao();
        AdminOperations = new ArrayList<AdminOperation>();
        // query for all items in the database
        AdminOperations = AdminOperationDao.queryForAll();
    }

    /**
     * It is a getter for AdminLists.
     * @return the list of AdminLists.
     */

    public List<AdminOperation> getAdminOperations(){
        return AdminOperations;
    }





}
