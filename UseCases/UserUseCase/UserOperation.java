package UseCases.UserUseCase;

import GateWay.DataSaverHelper;
import Model.*;
import UseCases.MeetingSystemUseCase.IMeetingSystem;
import UseCases.MeetingSystemUseCase.MeetingSystem;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.*;

/**
 * This class stores all the setters and getters of operations of normal users, including:
 * the lists of items they would like to borrow & lend,
 * the lists of items they are borrowing & lending,
 * the three most recent items they have traded,
 * the top three most frequent trading partners,
 * new mail box,
 * confirmed transactions,
 * invited transactions,
 * new messages of invited transactions,
 * their ratings for items,
 * completed transactions,
 * notifications of transactions,
 * and whether or not the user is an initial admin user.
 *
 * @author Tianyan Zhou
 * @version 1.8
 */

@DatabaseTable(tableName = "userOperation")
public class UserOperation implements IUserOperation {

    IUserManager userManager = UserManager.getInstance();
    IMeetingSystem meetingSystem = MeetingSystem.getInstance();
    DataSaverHelper dataSaverHelper = DataSaverHelper.getInstance();

    private List<ItemInterface> wishToBorrow =  new ArrayList<>();
    private List<ItemInterface> wishToLend ;
    private List<ItemInterface> threeItems;
    private HashMap<String, Integer> tradingPartners ;
    private List<ItemInterface> currentBorrowing;
    private List<ItemInterface> currentLending;
    private HashMap<String, Integer> threePartners;
    private List<Integer> completeTransaction ;
    private HashMap<Integer, Integer> userItemRating;
    private List<String> notifications ; // new messages where no choice is provided
    private HashMap<Integer, String> inviteTransNewMessages; // key is transId
    private List<Integer> confirmedTransaction ;
    private List<Integer> invitedTrans ;

    public static final String USER_ID_FIELD_NAME = "userId";
    public static final String WISH_TO_BORROW_LIST_FIELD_NAME = "wishToBorrowList";
    public static final String WISH_TO_LEND_LIST_FIELD_NAME = "wishToLendList";
    public static final String THREE_ITEMS_FIELD_NAME = "threeItems";
    public static final String TRADING_PARTNERS_KEY_FIELD_NAME = "tradingPartnersKey";
    public static final String TRADING_PARTNERS_VALUE_FIELD_NAME = "tradingPartnersValue";
    public static final String CURRENT_BORROWING_FIELD_NAME = "currentBorrowing";
    public static final String CURRENT_LENDING_FIELD_NAME = "currentLending";
    public static final String NOTIFICATIONS_FIELD_NAME = "notifications";
    public static final String INVITED_TRANS_NEW_MESSAGES_KEY_FIELD_NAME = "inviteTransNewMessagesKey";
    public static final String INVITED_TRANS_NEW_MESSAGES_VALUE_FIELD_NAME = "inviteTransNewMessagesValue";
    public static final String CONFIRMED_TRANSACTIONS_FIELD_NAME = "confirmedTransactions";
    public static final String INVITED_TRANS_FIELD_NAME = "invitedTrans";
    public static final String THREE_PARTNERS_KEY_FIELD_NAME = "threePartnersKey";
    public static final String THREE_PARTNERS_VALUE_FIELD_NAME = "threePartnersValue";
    public static final String COMPLETE_TRANSACTIONS_FIELD_NAME = "completeTransactions";
    public static final String USER_ITEM_RATING_KEYS_FIELD_NAME = "userItemRatingKeys";
    public static final String USER_ITEM_RATING_VALUES_FIELD_NAME = "userItemRatingValues";


    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = USER_ID_FIELD_NAME, canBeNull = false)
    private String userId;

    private String userStatus = null;

    @DatabaseField(columnName = WISH_TO_BORROW_LIST_FIELD_NAME)
    private String wishToBorrowList;

    @DatabaseField(columnName = WISH_TO_LEND_LIST_FIELD_NAME)
    private String wishToLendList;

    @DatabaseField(columnName = THREE_ITEMS_FIELD_NAME)
    private String threeItemsLists;

    @DatabaseField(columnName = TRADING_PARTNERS_KEY_FIELD_NAME)
    private String tradingPartnersKey;
    @DatabaseField(columnName = TRADING_PARTNERS_VALUE_FIELD_NAME)
    private String tradingPartnersValue;

    @DatabaseField(columnName = CURRENT_BORROWING_FIELD_NAME)
    private String currentBorrowingLists;

    @DatabaseField(columnName = CURRENT_LENDING_FIELD_NAME)
    private String currentLendingList;

    @DatabaseField(columnName = NOTIFICATIONS_FIELD_NAME)
    private String notificationsList;

    @DatabaseField(columnName = INVITED_TRANS_NEW_MESSAGES_KEY_FIELD_NAME)
    private String inviteTransNewMessagesKey;
    @DatabaseField(columnName = INVITED_TRANS_NEW_MESSAGES_VALUE_FIELD_NAME)
    private String inviteTransNewMessagesValue;

    @DatabaseField(columnName = CONFIRMED_TRANSACTIONS_FIELD_NAME)
    private String confirmedTransactionsList;

    @DatabaseField(columnName = INVITED_TRANS_FIELD_NAME)
    private String invitedTransList ;

    @DatabaseField(columnName = THREE_PARTNERS_KEY_FIELD_NAME)
    private String threePartnersKey;
    @DatabaseField(columnName = THREE_PARTNERS_VALUE_FIELD_NAME)
    private String threePartnersValue;

    @DatabaseField(columnName = COMPLETE_TRANSACTIONS_FIELD_NAME)
    private String completeTransactionsList;

    @DatabaseField(columnName = USER_ITEM_RATING_KEYS_FIELD_NAME)
    private String userItemRatingKeys;
    @DatabaseField(columnName = USER_ITEM_RATING_VALUES_FIELD_NAME)
    private String userItemRatingValues;

    /**
     * constructor for class UserOperation
     * @param id user id
     */
    public UserOperation(String id){
        this.userId = id;
        this.userStatus = userManager.getUserById(userId).getStatus();
        wishToBorrow = new ArrayList<>();
        wishToLend = new ArrayList<>();
        threeItems= new ArrayList<>();
        tradingPartners = new HashMap<>();
        currentBorrowing = new ArrayList<>();
        currentLending= new ArrayList<>();
        threePartners = new HashMap<>();
        completeTransaction = new ArrayList<>();
        userItemRating = new HashMap<>();
        notifications = new ArrayList<>(); // new messages where no choice is provided
        inviteTransNewMessages = new HashMap<>(); // key is transId
        confirmedTransaction = new ArrayList<>();
        invitedTrans = new ArrayList<>();

        Set<String> tradingPartnerKey = tradingPartners.keySet();
        Collection<Integer> tradingPartnerValue = tradingPartners.values();
        Set<Integer> inviteTransNewMessageKey = inviteTransNewMessages.keySet();
        Collection<String> inviteTransNewMessageValue = inviteTransNewMessages.values();

        Set<String> threePartnerKey = threePartners.keySet();
        Collection<Integer> threePartnerValue = threePartners.values();
        Set<Integer> userItemRatingKey = userItemRating.keySet();
        Collection<Integer> userItemRatingValue = userItemRating.values();

        wishToBorrowList = dataSaverHelper.ItemInterfaceListCovert(wishToBorrow);
        wishToLendList = dataSaverHelper.ItemInterfaceListCovert(wishToLend);
        threeItemsLists = dataSaverHelper.ItemInterfaceListCovert(threeItems);
        tradingPartnersKey = dataSaverHelper.KeySetStringConvert(tradingPartnerKey);
        tradingPartnersValue = dataSaverHelper.IntegerCollectionStringConvert(tradingPartnerValue);
        currentBorrowingLists = dataSaverHelper.ItemInterfaceListCovert(currentBorrowing);
        currentLendingList = dataSaverHelper.ItemInterfaceListCovert(currentLending);
        notificationsList = dataSaverHelper.StringListConvert01(notifications);
        inviteTransNewMessagesKey = dataSaverHelper.KeySetIntegerConvert(inviteTransNewMessageKey);
        inviteTransNewMessagesValue = dataSaverHelper.StringCollectionStringCovert(inviteTransNewMessageValue);
        confirmedTransactionsList = dataSaverHelper.IntegerListConvert01(confirmedTransaction);
        invitedTransList = dataSaverHelper.IntegerListConvert01(invitedTrans);
        threePartnersKey = dataSaverHelper.KeySetStringConvert(threePartnerKey);
        threePartnersValue = dataSaverHelper.IntegerCollectionStringConvert(threePartnerValue);
        completeTransactionsList = dataSaverHelper.IntegerListConvert01(completeTransaction);
        userItemRatingKeys = dataSaverHelper.KeySetIntegerConvert(userItemRatingKey);
        userItemRatingValues = dataSaverHelper.IntegerCollectionStringConvert(userItemRatingValue);
    }

    /**
     * default constructor
     */
    public UserOperation() {
    }
    /**
     * gets user id
     * @return user id
     */
    public String getUserId() {
        return userId;
    }

//    public static IUserOperation getInstance() {
//        return userOperation;
//    }

    /**
     * This method shows the list of one user would like to borrow from others.
     *
     * @return wishToBorrow
     */
    @Override
    public List<ItemInterface> getWishToBorrow(){
        return wishToBorrow;
    }

    /**
     * This method shows the list of one user would like to lend to others.
     *
     * @return wishToLend
     */
    @Override
    public List<ItemInterface> getWishToLend(){
        return wishToLend;
    }

    /**
     * This method sets list the user would like to borrow from others
     *
     * @param wishToBorrow items the users would like to borrow from others.
     */
    @Override
    public void setWishToBorrow(List<ItemInterface> wishToBorrow) {
        this.wishToBorrow = wishToBorrow;
    }

    /**
     * This method sets list the user would like to lend to others
     *
     * @param wishToLend items the users would like to lend to others.
     */
    @Override
    public void setWishToLend(List<ItemInterface> wishToLend){
        this.wishToLend = wishToLend;
    }

    /**
     * This method shows the current items the user is borrowing.
     *
     * @return currentBorrowing
     */
    @Override
    public List<ItemInterface> getCurrentBorrowing(){
        return currentBorrowing;
    }

    /**
     * This method sets the current items the user is borrowing.
     *
     * @param currentBorrowing items the user is currently borrowing
     */
    @Override
    public void setCurrentBorrowing(List<ItemInterface> currentBorrowing){
        this.currentBorrowing = currentBorrowing;
    }

    /**
     * This method shows the current items the user is lending.
     *
     * @return currentLending
     */
    @Override
    public List<ItemInterface> getCurrentLending(){
        return currentLending;
    }

    /**
     * This method sets the current items the user is lending.
     *
     * @param currentLending items the user is currently lending to others.
     */
    @Override
    public void setCurrentLending(List<ItemInterface> currentLending){
        this.currentLending = currentLending;
    }

    /**
     * This method shows  most recent three items that they traded in a one-way or two-way trade of one user.
     *
     * @return threeItems
     */
    @Override
    public List<ItemInterface> getThreeItems(){
        return threeItems;
    }

    /**
     * This method sets the most recent three items that they traded in a one-way or two-way trade of one user.
     *
     * @param threeItems the most recent three items that the user trades
     */
    @Override
    public void setThreeItems(List<ItemInterface> threeItems) {
        this.threeItems = threeItems;
    }

    /**
     * This method shows the top three most frequent trading partners of one user.
     *
     * @return tradingPartners
     */
    @Override
    public HashMap<String, Integer> getTradingPartners() {
        return tradingPartners;
    }

    /**
     * This method sets the trading partners of one user.
     *
     * @param tradingPartners the trading partners of one user.
     */
    @Override
    public void setTradingPartners(HashMap<String, Integer> tradingPartners) {
        this.tradingPartners = tradingPartners;
    }

    /**
     * Get the transactions which is already confirmed
     * @return the transactions which is already confirmed
     */
    @Override
    public List<Integer> getConfirmedTransaction() {
        return confirmedTransaction;
    }

    /**
     * Set the confirmedTransaction for this user
     * @param confirmedTransaction the confirmed transactions for this user
     */
    @Override
    public void setConfirmedTransaction(List<Integer> confirmedTransaction){
        this.confirmedTransaction = confirmedTransaction;
    }

    /**
     * Get the notifications for this user
     * @return the notifications of this user
     */
    @Override
    public List<String> getNotifications() {
        return notifications;
    }

    /**
     * sets notifications
     * @param notifications notifications
     */
    @Override
    public void setNotification(List<String> notifications) {
        this.notifications = notifications;
    }

    /**
     * sets invite trans new messages
     * @param inviteTransNewMessages invite trans new messages
     */
    @Override
    public void setInviteTransNewMessages(HashMap<Integer, String> inviteTransNewMessages) {
        this.inviteTransNewMessages = inviteTransNewMessages;
    }

    /**
     * Get the inviteTransNewMessages for this user
     * @return the inviteTransNewMessages of this user
     */
    @Override
    public HashMap<Integer, String> getInviteTransNewMessages() {
        return inviteTransNewMessages;
    }

    /**
     * Set the invitedTrans for this user
     * @param invitedTrans the invited transactions for this user
     */
    @Override
    public void setInvitedTrans(List<Integer> invitedTrans) {
        this.invitedTrans = invitedTrans;
    }
    /**
     * Get the invited transactions from other users and stored by its' transaction id.
     * @return the invited transactions from other users
     */
    @Override
    public List<Integer> getInvitedTrans() { return invitedTrans;}

    /**
     * This method gets the top three most frequent trading partners and their corresponding total times
     * of transaction
     * @return the top three most frequent trading partners and total trading times
     */
    @Override
    public HashMap<String, Integer> getThreePartners() {
        return this.threePartners;
    }

    /**
     * This method sets the top three most frequent trading partners and their corresponding total times
     * of transaction
     * @param threePartners top three trading partners and the times of transaction
     */
    @Override
    public void setThreePartners(HashMap<String, Integer> threePartners) {
        this.threePartners = new HashMap<>();
        this.threePartners = threePartners;
    }

    /**
     *This method shows the rating of item by item id, given by the user.
     * @return UserItemRating
     */
    @Override
    public HashMap<Integer, Integer> getUserItemRating() {
        return userItemRating;
    }

    /**
     * @param userItemRating the hashmap
     */
    @Override
    public void setUserItemRating(HashMap<Integer, Integer> userItemRating) {
        this.userItemRating = userItemRating;
    }

    /**
     * method to get the list of completed transactions;
     * @return completeTransaction
     */
    @Override
    public List<Integer> getCompleteTransaction() {
        return completeTransaction;
    }

    /**
     * change completeTransaction.
     * @param completeTransaction the user has complete the transaction.
     */
    @Override
    public void setCompleteTransaction(List<Integer> completeTransaction) {
        this.completeTransaction = completeTransaction;
    }

    /**
     * Get the user status
     * @return the value of user status
     */
    @Override
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * gets wish to borrow list
     * @return wish to borrow list
     */
    @Override
    public String getWishToBorrowList() {
        return wishToBorrowList;
    }

    /**
     * gets wish to lend list
     * @return wish to lend list
     */
    @Override
    public String getWishToLendList() {
        return wishToLendList;
    }

    /**
     * gets three items list
     * @return three items list
     */
    @Override
    public String getThreeItemsLists() {
        return threeItemsLists;
    }

    /**
     * gets key for trading partners
     * @return key for trading partners
     */
    @Override
    public String getTradingPartnersKey() {
        return tradingPartnersKey;
    }

    /**
     * gets value for trading partners
     * @return value for trading partners
     */
    @Override
    public String getTradingPartnersValue() {
        return tradingPartnersValue;
    }

    /**
     * gets current borrowing list
     * @return current borrowing list
     */
    @Override
    public String getCurrentBorrowingLists() {
        return currentBorrowingLists;
    }

    /**
     * gets current lending list
     * @return current lending list
     */
    @Override
    public String getCurrentLendingList() {
        return currentLendingList;
    }

    /**
     * gets notifications lists
     * @return notification lists
     */
    @Override
    public String getNotificationsList() {
        return notificationsList;
    }

    /**
     * gets key for invite trans new messages
     * @return key for invite trans new messages
     */
    @Override
    public String getInviteTransNewMessagesKey() {
        return inviteTransNewMessagesKey;
    }

    /**
     * gets value for invite trans new messages
     * @return value for invite trans new messages
     */
    @Override
    public String getInviteTransNewMessagesValue() {
        return inviteTransNewMessagesValue;
    }

    /**
     * gets confirmed transactions list
     * @return confirmed transactions list
     */
    @Override
    public String getConfirmedTransactionsList() {
        return confirmedTransactionsList;
    }

    /**
     * gets invited trans list
     * @return invited trans list
     */
    @Override
    public String getInvitedTransList() {
        return invitedTransList;
    }

    /**
     * gets key for three partners
     * @return key for three partners
     */
    @Override
    public String getThreePartnersKey() {
        return threePartnersKey;
    }

    /**
     * gets value for three partners
     * @return value for three partners
     */
    @Override
    public String getThreePartnersValue() {
        return threePartnersValue;
    }

    /**
     * gets complete transaction list
     * @return complete transaction list
     */
    @Override
    public String getCompleteTransactionsList() {
        return completeTransactionsList;
    }

    /**
     * gets key for user item rating
     * @return key for user item rating
     */
    @Override
    public String getUserItemRatingKeys() {
        return userItemRatingKeys;
    }

    /**
     * gets value for user item rating
     * @return value for user item rating
     */
    @Override
    public String getUserItemRatingValues() {
        return userItemRatingValues;
    }
}

