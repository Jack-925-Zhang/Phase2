package GateWay;

import Model.IMeeting;
import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;

import java.util.Collection;
/**
 * This interface class IDataUpdater will update record for the specific table in the previously created databases
 * @author LINNI XIE
 * @version 1.8
 */
public interface IDataUpdater {

    /**
     * Update the record for this new user in the related table
     * @param user the user who needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void normalUserUpdater(IUserAccount user) throws Exception;

    /**
     * Update the record for this new user in the related table
     * @param user the user who needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void adminUserUpdater(IUserAccount user) throws Exception;


    /**
     * Update the record for this new user in the related table
     * @param user the user who needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void guestUserUpdater(IUserAccount user) throws Exception;

    /**
     * Update the record for this new item in the related table
     * @param item the item which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void itemUpdater(ItemInterface item) throws Exception;

    /**
     * Update the record for this new oneWayTransaction in the related table
     * @param oneWayTransaction the oneWayTransaction which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void oneWayTransactionsUpdater(ITransaction oneWayTransaction) throws Exception;

    /**
     * Update the record for this new twoWayTransaction in the related table
     * @param twoWayTransaction the twoWayTransaction which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void twoWayTransactionsUpdater(ITransaction twoWayTransaction) throws Exception;

    /**
     * Update the record for this new Transaction in the related table
     * @param transaction the Transaction which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void transactionsUpdater(ITransaction transaction) throws Exception;

    /**
     * Update the record for this meeting in the related table
     * @param meeting the meeting which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void meetingsUpdater(IMeeting meeting) throws Exception;

    /**
     * Update the record for all users in the related table
     * @param allUsers all users which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void allUserUpdater(Collection<IUserAccount> allUsers) throws Exception;

    /**
     * Update the record for all items in the related table
     * @param items all items which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void allItemsUpdater(Collection<ItemInterface> items) throws Exception;

    /**
     * Update the record for all transactions in the related table
     * @param transactions all transactions which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    void allTransactionUpdater(Collection<ITransaction> transactions) throws Exception;

}
