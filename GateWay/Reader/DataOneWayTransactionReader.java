package GateWay.Reader;

import GateWay.DataBuilder;
import Model.OneWayTransaction;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the reader for the one-way transactions.
 * It would read from the sql database and return the one-way transactions.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */

public class DataOneWayTransactionReader implements IDataOneWayTransactionReader {

    private final DataBuilder dataBuilder;

    private final Dao<OneWayTransaction, Integer> oneWayTransactionsDao;
    private List<OneWayTransaction> oneWayTransactions ;

    /**
     * This method provides the reading of one-way transactions from the SQLite database
     * and it would use queryForAll() method and make one-way transactions base on the
     * specific information from database.
     * @throws  SQLException It would throws the SQLException as long as it is
     * reading the two-way transactions information from the database.
     */

    public DataOneWayTransactionReader() throws SQLException {
        dataBuilder = DataBuilder.getInstance();
        oneWayTransactionsDao = dataBuilder.getOneWayTransactionsDao();
        oneWayTransactions = new ArrayList<>();
        // query for all items in the database
        oneWayTransactions = oneWayTransactionsDao.queryForAll();
    }
    /**
     * It is a getter for one-way transactions.
     * @return a list of one-way transactions
     */
    public List<OneWayTransaction> getOneWayTransactions() {
        return oneWayTransactions;
    }
}
