package GateWay.Reader;

import Model.TwoWayTransaction;

import java.util.List;

/**
 * This class is interface of the class the reader for the TwoWayTransactio
 * It would return the instances of TwoWayTransactio
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */
public interface IDataTwoWayTransactionReader {

    /**
     * It is a getter for two-way transactions.
     * @return the list of two-way transactions.
     */
    List<TwoWayTransaction> getTwoWayTransactions();
}
