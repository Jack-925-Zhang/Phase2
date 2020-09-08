package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * NormalUser stores all the setter and getter of the basic information of their account, including:
 * is for normal users to set their id and password,
 * make change to their wishToLend and wishToBorrow list so that other users can see and trade with them,
 * show whether the account is freeze,
 * show transaction limit the user left to continue,
 * show how many items the user has lent,borrowed, and traded,
 * show items the user is currently lending and borrowing,
 * show the top three most frequent trading partners,
 * show the top three most recent trading recent in transactions,
 * show how many more items lent than items borrowed are needed for the user to continue to trade.
 *
 * @author Tianyan Zhou
 * @version 1.8
 */

@DatabaseTable(tableName = "normalUser")
public class NormalUser implements IUserAccount{

    //for QueryBuilder to be able to find the fields
    public static final String USER_ID_FIELD_NAME = "userId";
    public static final String PASSWORD_FIELD_NAME = "password";
    public static final String STATUS_FIELD_NAME = "status";
    public static final String TRANSACTION_LIMIT_FIELD_NAME = "transactionLimit";
    public static final String NUM_BORROWED_FIELD_NAME = "numBorrowed";
    public static final String NUM_LENT_FIELD_NAME = "numLent";
    public static final String CURRENT_TRANS_FIELD_NAME = "currentTrans";
    public static final String IS_FREEZE_FIELD_NAME = "isFreeze";
    public static final String THRESHOLD_FIELD_NAME = "threshold";
    public static final String ON_VACATION_FIELD_NAME = "OnVacation";
    public static final String HOME_CITY_FIELD_NAME = "homeCity";
    public static final String START_DATE_FIELD_NAME = "startDate";
    public static final String END_DATE_FIELD_NAME = "endDate";
    public static final String CREDIT_FIELD_NAME = "credit";


    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = USER_ID_FIELD_NAME, canBeNull = false)
    private String userId;

    @DatabaseField(columnName = PASSWORD_FIELD_NAME)
    private String userPassword;

    @DatabaseField(columnName = STATUS_FIELD_NAME)
    private String userStatus;

    @DatabaseField(columnName = TRANSACTION_LIMIT_FIELD_NAME)
    private int transactionLimit;

    @DatabaseField(columnName = NUM_BORROWED_FIELD_NAME)
    private int numBorrowed;

    @DatabaseField(columnName = NUM_LENT_FIELD_NAME)
    private int numLent;

    @DatabaseField(columnName = CURRENT_TRANS_FIELD_NAME)
    private int currentTrans;

    @DatabaseField(columnName = IS_FREEZE_FIELD_NAME)
    private boolean isFreeze;

    @DatabaseField(columnName = THRESHOLD_FIELD_NAME)
    private int threshold;

    @DatabaseField(columnName = ON_VACATION_FIELD_NAME)
    private boolean OnVacation;

    @DatabaseField(columnName = HOME_CITY_FIELD_NAME)
    private String homeCity;

    @DatabaseField(columnName = START_DATE_FIELD_NAME)
    private Date startDate;

    @DatabaseField(columnName = END_DATE_FIELD_NAME)
    private Date endDate;

    @DatabaseField(columnName = CREDIT_FIELD_NAME)
    private int credit; // the amount of money this user has.

    public NormalUser(){}


    /**
     * Created the instance of NormalUser
     * @param userId the id of user.
     * @param userPassword the password of user
     * @param userStatus the status of user whether normal or administrative.
     * @param homeCity the home city of user.
     */
    public NormalUser(String userId,String userPassword,String userStatus, String homeCity) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.homeCity = homeCity;
        this.transactionLimit = 0;
        this.numBorrowed = 0;
        this.numLent = 0;
        this.currentTrans = 0;
        this.isFreeze = false;
        this.threshold = 0;
        this.OnVacation = false;
        this.startDate = null;
        this.endDate = null;
        this.credit = 0;
    }

    /**
     * This method returns the user id of one user.
     *
     * @return user id
     */
    @Override
    public String getUserId() {
        return userId;
    }

    /**
     * This method sets the user id of one user.
     *
     * @param userId The user id used to logged in.
     */
    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method return the password of one user's account.
     *
     * @return userPassword
     */
    @Override
    public String getPassword() {
        return userPassword;
    }

    /**
     * This method set password to one user's account.
     *
     * @param userPassword the password of user
     */
    @Override
    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**
     * This method returns whether an user account is normal or admin.
     *
     * @return userStatus
     */
    @Override
    public String getStatus() {
        return userStatus;
    }

    /**
     * This method sets the account status to be normal or admin.
     *
     * @param userStatus whether the user is normal or admin account
     */
    @Override
    public void setStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * This method shows the remaining transaction limit of one user.
     *
     *
     * @return transactionLimit
     */
    @Override
    public int getTransactionLimit(){
        return transactionLimit;
    }

    /**
     * This method sets the transaction limit of one user.
     *
     * @param transactionLimit the transaction limit left of one user
     */
    @Override
    public void setTransactionLimit(int transactionLimit) {
        this.transactionLimit = transactionLimit;
    }

    /**
     * This method shows the number of items the user has borrowed from others.
     *
     * @return numBorrowed
     */
    @Override
    public int getNumBorrowed(){
        return numBorrowed;
    }

    /**
     * This methods sets the number of items the user has borrowed from others.
     *
     * @param numBorrowed The number of items the user has borrowed from others
     *
     */
    @Override
    public void setNumBorrowed(int numBorrowed){
        this.numBorrowed = numBorrowed;
    }

    /**
     * This method shows the number of items the user has lent from others.
     *
     * @return numLent
     */
    @Override
    public int getNumLent() {
        return numLent;
    }

    /**
     * This methods sets the number of items the user has lent to others.
     *
     * @param numLent the number of items the user has lent to others
     */
    @Override
    public void setNumLent(int numLent) {
        this.numLent = numLent;
    }

    /**
     * This methods shows the number of transaction the user is currently having.
     *
     * @return currentTrans
     */
    @Override
    public int getCurrentTrans(){
        return currentTrans;
    }
    /**
     * This method sets the number of transaction the user is currently having.
     *
     * @param currentTrans the number of transactions the user is currently having
     */
    @Override
    public void setCurrentTrans(int currentTrans){
        this.currentTrans = currentTrans;
    }

    /**
     * This method shows how many more times the numLent than numBorrowed of one user needs to be to continue its trade.
     * @return threshold
     */
    @Override
    public int getThreshold(){
        return threshold;
    }

    /**
     * This method sets how many more times the numLent than numBorrowed of one user needs to be to continue its trade.
     *
     * @param threshold how many more times the numLent than numBorrowed of one user needs to be to continue its trade.
     */
    @Override
    public void setThreshold(int threshold){
        this.threshold = threshold;
    }

    /**
     * This method shows whether one user account is freeze or not.
     *
     * @return isFreeze
     */
    @Override
    public boolean getIsFreeze(){
        return isFreeze;
    }

    /**
     * This method sets whether one user account is freeze or not.
     *
     * @param isFreeze whether or not the user is freeze
     */
    @Override
    public void setIsFreeze(boolean isFreeze){
        this.isFreeze = isFreeze;
    }


    @Override
    public String toString() {
        return  userStatus + " {" +
                ", userId='" + userId + '\'' +
                ", homeCity='" + homeCity + '\'' +
                ", transactionLimit=" + transactionLimit +
                ", numBorrowed=" + numBorrowed +
                ", numLent=" + numLent +
                ", currentTrans=" + currentTrans +
                ", isFreeze=" + isFreeze +
                ", threshold=" + threshold +
                ", OnVacation=" + OnVacation +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", credit=" + credit +
                '}';
    }

    /**
     * This method shows whether one user is on vacation or not.
     * @return OnVacation
     */
    @Override
    public boolean getOnVacation(){
        return !OnVacation;
    }

    /**
     * This method set the onVacation status of user.
     * @param OnVacation whether the user is on vacation.
     */
    @Override
    public void setOnVacation(boolean OnVacation) {
        this.OnVacation = OnVacation;
    }

    /**
     * This method is for user who cancel the status for on vacation
     */
    @Override
    public void cancelOnVacation(){
        this.setOnVacation(false);
    }

    /**
     * This method shows the home city of the user.
     * @return homeCity
     */
    @Override
    public String getHomeCity() {
        return homeCity;
    }

    /**
     * This method sets the home city of the user.
     * @param homeCity the home city of the user.
     */
    @Override
    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    /**
     * This method show the start date the user who would be on vacation.
     * @return startDate.
     */
    @Override
    public Date getStartDateOfVacation() {
        return startDate;
    }

    /**
     * This methods sets the start date of vacation of user.
     * @param startDate the start date of vacation.
     */
    @Override
    public void setStartDateOfVacation(Date startDate){this.startDate = startDate;}

    /**
     * This method shows the end date the user who would be on vacation.
     * @return endDate.
     */
    @Override
    public Date getEndDateOfVacation() {
        return endDate;
    }

    /**
     * This method sets the end date of vacation of user.
     * @param endDate the end date of vacation.
     */
    @Override
    public void setEndDateOfVacation(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * return the amount of money this user has
     * @return amount of money
     */
    @Override
    public int getCredit() {
        return credit;
    }

    /**
     * set the amount of money this user has
     * @param credit new credit for this user
     */
    @Override
    public void setCredit(int credit) {
        this.credit = credit;
    }

    /**
     * Get the adminUser status whether is initial adminUser or not
     * @return the value of the adminUser status whether is initial adminUser or not
     */
    @Override
    public boolean getIsInitialAdmin(){return false;}

    /**
     * Set the adminUser status whether is initial adminUser or not
     * @param isInitialAdmin the adminUser status whether is initial adminUser or not
     */
    @Override
    public void setIsInitialAdmin(boolean isInitialAdmin) {}



}
