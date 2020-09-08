package GateWay;
import Model.*;
import UseCases.UserUseCase.AdminOperation;
import UseCases.UserUseCase.GuestOperation;
import UseCases.UserUseCase.UserOperation;
import com.j256.ormlite.dao.Dao;

import java.util.Collection;

/**
 * This class DataUpdater will update record for the specific table in the previously created databases
 * @author LINNI XIE
 * @version 1.8
 */
public class DataUpdater implements IDataUpdater{

    private static DataBuilder dataBuilder;

    private final Dao<NormalUser, Integer> normalUsersDao;
    private final Dao<AdminUser, Integer> adminUsersDao;
    private final Dao<GuestUser, Integer> guestUsersDao;

    private final Dao<UserOperation, Integer> userOperationDao ;
    private final Dao<AdminOperation, Integer> adminOperationDao;
    private final Dao<GuestOperation, Integer> guestOperationDao;

    private final Dao<Item, Integer> itemsDao ;

    private final Dao<OneWayTransaction, Integer> oneWayTransactionsDao ;
    private final Dao<TwoWayTransaction, Integer> twoWayTransactionsDao;
    private final Dao<Meeting, Integer> meetingsDao ;

    /**
     * the constructor of class DataUpdater
     */
    public DataUpdater() {
        dataBuilder = DataBuilder.getInstance();

        normalUsersDao = dataBuilder.getNormalUsersDao();
        adminUsersDao = dataBuilder.getAdminUsersDao();
        guestUsersDao = dataBuilder.getGuestUsersDao();

        userOperationDao = dataBuilder.getUserOperationDao();
        adminOperationDao = dataBuilder.getAdminOperationDao();
        guestOperationDao = dataBuilder.getGuestOperationDao();

        itemsDao = dataBuilder.getItemsDao();
        oneWayTransactionsDao = dataBuilder.getOneWayTransactionsDao();
        twoWayTransactionsDao = dataBuilder.getTwoWayTransactionsDao();
        meetingsDao = dataBuilder.getMeetingsDao();

    }

    /**
     * Update the record for this new user in the related table
     * @param user the user who needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void normalUserUpdater(IUserAccount user) throws Exception{
        normalUsersDao.update((NormalUser) user);
    }

    /**
     * Update the record for this new user in the related table
     * @param user the user who needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void adminUserUpdater(IUserAccount user) throws Exception{
        adminUsersDao.update((AdminUser) user);
    }

    /**
     * Update the record for this new user in the related table
     * @param user the user who needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void guestUserUpdater(IUserAccount user) throws Exception{
        guestUsersDao.update((GuestUser) user);
    }

    /**
     * Update the record for this new item in the related table
     * @param item the item which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void itemUpdater(ItemInterface item) throws Exception{
        itemsDao.update((Item) item);
    }

    /**
     * Update the record for this new oneWayTransaction in the related table
     * @param oneWayTransaction the oneWayTransaction which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void oneWayTransactionsUpdater(ITransaction oneWayTransaction) throws Exception{
        oneWayTransactionsDao.update((OneWayTransaction) oneWayTransaction);
    }

    /**
     * Update the record for this new twoWayTransaction in the related table
     * @param twoWayTransaction the twoWayTransaction which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void twoWayTransactionsUpdater(ITransaction twoWayTransaction) throws Exception{
        twoWayTransactionsDao.update((TwoWayTransaction) twoWayTransaction);
    }

    /**
     * Update the record for this new Transaction in the related table
     * @param transaction the Transaction which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void transactionsUpdater(ITransaction transaction) throws Exception{
        if(transaction.getOneOrTwo()==1){
            oneWayTransactionsUpdater(transaction);
        }
        else{
            twoWayTransactionsUpdater(transaction);
        }
    }



    /**
     * Update the record for this meeting in the related table
     * @param meeting the meeting which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void meetingsUpdater(IMeeting meeting) throws Exception{
        meetingsDao.update((Meeting) meeting);
    }

    /**
     * Update the record for all users in the related table
     * @param allUsers all users which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void allUserUpdater(Collection<IUserAccount> allUsers) throws Exception{
        for(IUserAccount user: allUsers){
            switch (user.getStatus()) {
                case "Admin":
                    adminUserUpdater(user);
                    break;
                case "Normal":
                    normalUserUpdater(user);
                    break;
                case "Guest":
                    guestUserUpdater(user);
                    break;
            }
        }
    }

    /**
     * Update the record for all items in the related table
     * @param items all items which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void allItemsUpdater(Collection<ItemInterface> items) throws Exception{
        for(ItemInterface item:items){
            itemUpdater(item);
        }
    }

    /**
     * Update the record for all transactions in the related table
     * @param transactions all transactions which needed be updated
     * @throws Exception Exception update error.
     * @see Exception
     */
    public void allTransactionUpdater(Collection<ITransaction> transactions) throws Exception{
        for(ITransaction transaction: transactions){
            if(transaction.getOneOrTwo() == 1){
                oneWayTransactionsUpdater(transaction);
            }
            else{
                twoWayTransactionsUpdater(transaction);
            }
        }
    }


}

