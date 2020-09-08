package GateWay;
import Model.*;
import UseCases.UserUseCase.AdminOperation;
import UseCases.UserUseCase.IAdminOperation;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.UserOperation;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * This class DataInsertion will insert new record for the specific table in the previously created databases
 * It inherits DataBuilder
 * @author LINNI XIE
 * @version 1.8
 */
public class DataInsertion implements IDataInsertion{

    private static DataBuilder dataBuilder;

    private final Dao<NormalUser, Integer> normalUsersDao;
    private final Dao<AdminUser, Integer> adminUsersDao;
    private final Dao<UserOperation, Integer> userOperationDao ;
    private final Dao<AdminOperation, Integer> adminOperationDao;

    private final Dao<Item, Integer> itemsDao ;

    private final Dao<OneWayTransaction, Integer> oneWayTransactionsDao ;
    private final Dao<TwoWayTransaction, Integer> twoWayTransactionsDao;
    private final Dao<Meeting, Integer> meetingsDao ;

    /**
     * the constructor of this class
     */
    public DataInsertion() {

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
     * Insert the record for this new user in the related table
     * @param user the user who needed be inserted
     * @throws SQLException Exception insert error.
     * @see Exception
     */
    @Override
    public void normalUserInsertion(IUserAccount user) throws SQLException {
        normalUsersDao.create((NormalUser) user);
    }

    /**
     * Insert the record for this new user in the related table
     * @param user the user who needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    @Override
    public void adminUserInsertion(IUserAccount user) throws Exception{
        adminUsersDao.create((AdminUser) user);
    }

    /**
     * Insert the record for this new UserAttributeLists in the related table
     * @param user the UserOperation which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    @Override
    public void userOperationInsertion(IUserOperation user) throws Exception{
        userOperationDao.create((UserOperation) user);
    }

    /**
     * Insert the record for this new AdminAttributeLists in the related table
     * @param user the AdminOperation which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    @Override
    public void adminOperationInsertion(IAdminOperation user) throws Exception{
        adminOperationDao.create((AdminOperation) user);
    }

    /**
     * Delete the record for this AdminAttributeLists in the related table
     * @param user the IUserOperation which needed be inserted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    @Override
    public void operationInsertion(IUserOperation user) throws Exception{
        if(user.getUserStatus().equals("Normal")){
            userOperationInsertion(user);}
        else{adminOperationInsertion((IAdminOperation) user);}
    }


    /**
     * Insert the record for this new item in the related table
     * @param item the item which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    @Override
    public void itemInsertion(ItemInterface item) throws Exception{
        itemsDao.create((Item) item);
    }

    /**
     * Insert the record for this new oneWayTransaction in the related table
     * @param oneWayTransaction the oneWayTransaction which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    @Override
    public void oneWayTransactionsInsertion(ITransaction oneWayTransaction) throws Exception{
        oneWayTransactionsDao.create((OneWayTransaction) oneWayTransaction);
    }

    /**
     * Insert the record for this new twoWayTransaction in the related table
     * @param twoWayTransaction the twoWayTransaction which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    @Override
    public void twoWayTransactionsInsertion(ITransaction twoWayTransaction) throws Exception{
        twoWayTransactionsDao.create((TwoWayTransaction) twoWayTransaction);
    }

    /**
     * Insert the record for this meeting in the related table
     * @param meeting the meeting which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    @Override
    public void meetingsInsertion(IMeeting meeting) throws Exception{
        meetingsDao.create((Meeting) meeting);
    }

}