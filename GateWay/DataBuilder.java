package GateWay;

import Model.*;
import UseCases.UserUseCase.AdminOperation;
import UseCases.UserUseCase.GuestOperation;
import UseCases.UserUseCase.UserOperation;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * This class DataBuilder will be used to create tables in the previously created databases
 * It will Setup all databases and DAOs
 * It also have getters for all attributes
 * @author LINNI XIE
 * @version 1.8
 */
public class DataBuilder {

    private static Dao<NormalUser, Integer> normalUsersDao;
    private static Dao<AdminUser, Integer> adminUsersDao;
    private static Dao<GuestUser, Integer> guestUsersDao;

    private static Dao<UserOperation, Integer> userOperationDao;
    private static Dao<AdminOperation, Integer> adminOperationDao;
    private static Dao<GuestOperation, Integer> guestOperationDao;

    private static Dao<Item, Integer> itemsDao;

    private static Dao<OneWayTransaction, Integer> oneWayTransactionsDao;
    private static Dao<TwoWayTransaction, Integer> twoWayTransactionsDao;
    private static Dao<Meeting, Integer> meetingsDao;

    private static DataBuilder dataBuilder;

    static {
        try {
            dataBuilder = new DataBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * gets instance of dataBuilder
     * @return dataBuilder
     */
    public static DataBuilder getInstance(){
        return dataBuilder;
    }

    /**
     * the constructor for class DataBuilder
     * @throws Exception exception thrown when table is built repetitively
     */
    public DataBuilder() {
    }


    /**
     * Setup UserAccount database and DAOs
     */
    public void setupUserAccount(ConnectionSource connectionSource) throws Exception {

        normalUsersDao = DaoManager.createDao(connectionSource, NormalUser.class);
        adminUsersDao = DaoManager.createDao(connectionSource, AdminUser.class);
        userOperationDao = DaoManager.createDao(connectionSource, UserOperation.class);
        adminOperationDao = DaoManager.createDao(connectionSource, AdminOperation.class);

        // create the tables
        TableUtils.createTableIfNotExists(connectionSource, NormalUser.class);
        TableUtils.createTableIfNotExists(connectionSource, AdminUser.class);
        TableUtils.createTableIfNotExists(connectionSource, UserOperation.class);
        TableUtils.createTableIfNotExists(connectionSource, AdminOperation.class);
    }

    /**
     * Setup Item database and DAOs
     */
    public void setupItemDataBase(ConnectionSource connectionSource) throws Exception {

        itemsDao = DaoManager.createDao(connectionSource, Item.class);

        // create the table
        TableUtils.createTableIfNotExists(connectionSource, Item.class);
    }

    /**
     * Setup Transaction database and DAOs
     */
    public void setupTransactionDataBase(ConnectionSource connectionSource) throws Exception {

        oneWayTransactionsDao = DaoManager.createDao(connectionSource, OneWayTransaction.class);
        twoWayTransactionsDao = DaoManager.createDao(connectionSource, TwoWayTransaction.class);
        meetingsDao = DaoManager.createDao(connectionSource,Meeting.class);

        // create the table
        TableUtils.createTableIfNotExists(connectionSource, OneWayTransaction.class);
        TableUtils.createTableIfNotExists(connectionSource, TwoWayTransaction.class);
        TableUtils.createTableIfNotExists(connectionSource, Meeting.class);

    }

    /**
     * Get the Dao for normalUser this table
     * @return the value of Dao for this table
     */
    public Dao<NormalUser, Integer> getNormalUsersDao() {
        return normalUsersDao;
    }

    /**
     * Get the Dao for AdminUser this table
     * @return the value of Dao for this table
     */
    public Dao<AdminUser, Integer> getAdminUsersDao(){return adminUsersDao;}


    /**
     * Get the Dao for GuestUser this table
     * @return the value of Dao for this table
     */
    public Dao<GuestUser, Integer> getGuestUsersDao(){return guestUsersDao;}

    /**
     * Get the Dao for AdminUser this table
     * @return the value of Dao for this table
     */
    public Dao<Item, Integer> getItemsDao() {
        return itemsDao;
    }

    /**
     * Get the Dao for AdminUser this table
     * @return the value of Dao for this table
     */
    public Dao<OneWayTransaction, Integer> getOneWayTransactionsDao() {
        return oneWayTransactionsDao;
    }

    /**
     * Get the Dao for AdminUser this table
     * @return the value of Dao for this table
     */
    public Dao<TwoWayTransaction, Integer> getTwoWayTransactionsDao() {
        return twoWayTransactionsDao;
    }

    /**
     * Get the Dao for AdminUser this table
     * @return the value of Dao for this table
     */
    public Dao<Meeting, Integer> getMeetingsDao() {
        return meetingsDao;
    }

    /**
     * Get the Dao for AdminOperation this table
     * @return the value of Dao for this table
     */
    public Dao<AdminOperation, Integer> getAdminOperationDao() {
        return adminOperationDao;
    }

    /**
     * Get the Dao for NormalUserOperation this table
     * @return the value of Dao for this table
     */
    public Dao<UserOperation, Integer> getUserOperationDao() {
        return userOperationDao; }

    /**
     * Get the Dao for GuestUserOperation this table
     * @return the value of Dao for this table
     */
    public Dao<GuestOperation, Integer> getGuestOperationDao() {
        return guestOperationDao; }
}
