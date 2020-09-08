package GateWay.Reader;

import GateWay.DataBuilder;
import Model.TwoWayTransaction;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the reader for the two-way transactions.
 * It would read from the sql database and return the two-way transactions.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */

public class DataTwoWayTransactionReader implements IDataTwoWayTransactionReader{

    private final DataBuilder dataBuilder;

    private final Dao<TwoWayTransaction, Integer> twoWayTransactionsDao;

    private List<TwoWayTransaction> twoWayTransactions;

    /**
     * This method provides the reading of two-way transactions from the SQLite database
     * and it would use queryForAll() method and make two-way transactions base on the
     * specific information from database.
     * @throws  SQLException It would throws the SQLException as long as it is
     * reading the two-way transactions information from the database.
     */
    public DataTwoWayTransactionReader() throws SQLException {
        dataBuilder = DataBuilder.getInstance();
        twoWayTransactionsDao = dataBuilder.getTwoWayTransactionsDao();
        twoWayTransactions = new ArrayList<>();
        // query for all items in the database
        twoWayTransactions = twoWayTransactionsDao.queryForAll();
    }

    /**
     * It is a getter for two-way transactions.
     * @return the list of two-way transactions.
     */
    public List<TwoWayTransaction> getTwoWayTransactions() {
        return twoWayTransactions;
    }
}
