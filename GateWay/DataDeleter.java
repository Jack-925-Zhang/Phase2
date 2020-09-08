package GateWay;

import Model.*;
import UseCases.UserUseCase.AdminOperation;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.UserOperation;
import com.j256.ormlite.dao.Dao;



/**
 * This class DataDeleter will delete one record for the specific table in the previously created databases
 * It inherits DataBuilder
 * @author LINNI XIE
 * @version 1.8
 */
public class DataDeleter implements IDataDeleter{

    private static DataBuilder dataBuilder;

    private final Dao<NormalUser, Integer> normalUsersDao;
    private final Dao<AdminUser, Integer> adminUsersDao;
    private final Dao<UserOperation, Integer> userOperationDao;
    private final Dao<AdminOperation, Integer> adminOperationDao;

    private final Dao<Item, Integer> itemsDao;

    private final Dao<OneWayTransaction, Integer> oneWayTransactionsDao;
    private final Dao<TwoWayTransaction, Integer> twoWayTransactionsDao;
    private final Dao<Meeting, Integer> meetingsDao;

    /**
     * The constructor of this class
     */
    public DataDeleter() {
        dataBuilder = DataBuilder.getInstance();

        normalUsersDao = dataBuilder.getNormalUsersDao();
        adminUsersDao = dataBuilder.getAdminUsersDao();
        userOperationDao = dataBuilder.getUserOperationDao();
        adminOperationDao = dataBuilder.getAdminOperationDao();
        itemsDao = dataBuilder.getItemsDao();
        oneWayTransactionsDao = dataBuilder.getOneWayTransactionsDao();
        twoWayTransactionsDao = dataBuilder.getTwoWayTransactionsDao();
        meetingsDao = dataBuilder.getMeetingsDao();
    }

    /**
     * Delete the record for this user in the related table
     * @param user the user who needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void normalUserDeleter(NormalUser user) throws Exception {
        normalUsersDao.delete(user);
    }

    /**
     * Delete the record for this user in the related table
     * @param user the user who needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void adminUserDeleter(AdminUser user) throws Exception{
        adminUsersDao.delete(user);
    }


    /**
     * Delete the record for this UserAttributeLists in the related table
     * @param user the UserOperation which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void userOperationDeleter(UserOperation user) throws Exception{
        userOperationDao.delete(user);
    }

    /**
     * Delete the record for this AdminAttributeLists in the related table
     * @param user the AdminOperation which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void adminOperationDeleter(AdminOperation user) throws Exception{
        adminOperationDao.delete(user);
    }

    /**
     * Delete the record for this AdminAttributeLists in the related table
     * @param user the IUserOperation which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void operationDeleter(IUserOperation user) throws Exception{
        if(user.getUserStatus().equals("Normal")){
            userOperationDeleter((UserOperation) user);}
        else{adminOperationDeleter((AdminOperation) user);}
    }

    /**
     * Delete the record for this item in the related table
     * @param item the item which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void itemDeleter(Item item) throws Exception{
        itemsDao.delete(item);
    }

    /**
     * Delete the record for this oneWayTransaction in the related table
     * @param oneWayTransaction the oneWayTransaction which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void oneWayTransactionsDeleter(OneWayTransaction oneWayTransaction) throws Exception{
        oneWayTransactionsDao.delete(oneWayTransaction);
    }

    /**
     * Delete the record for this meeting in the related table
     * @param meeting the meeting which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void meetingsDeleter(Meeting meeting) throws Exception{
        meetingsDao.delete(meeting);
    }

    /**
     * Delete the record for this twoWayTransaction in the related table
     * @param twoWayTransaction the twoWayTransaction which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void twoWayTransactionsDeleter(TwoWayTransaction twoWayTransaction) throws Exception{
        twoWayTransactionsDao.delete(twoWayTransaction);
    }

}
