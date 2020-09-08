package GateWay.Reader;

import Model.OneWayTransaction;

import java.util.List;

/**
 * This class is interface of the class the reader for the OneWayTransaction
 * It would return the instances of OneWayTransaction
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */
public interface IDataOneWayTransactionReader {

    /**
     * It is a getter for one-way transactions.
     * @return a list of one-way transactions
     */
    List<OneWayTransaction> getOneWayTransactions();
}
