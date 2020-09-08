package UseCases.UserUseCase;

import GateWay.DataSaverHelper;
import Model.ITransaction;
import Model.IUserAccount;
import Model.Item;
import Model.ItemInterface;
import UseCases.*;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.*;

/**
 * this class stores the operations admin users can access
 * @author LINNI XIE
 * @version 1.8
 */

@DatabaseTable(tableName = "adminOperation")
public class AdminOperation extends UserOperation implements IAdminOperation {

    private IUserManager userManager= UserManager.getInstance();
    private TradingSystemInterface tradingSystem = TradingSystem.getInstance();
    private IOpManager opManager = OpManager.getInstance();
    private IUserWishListOperation userWishListOperation = UserWishListOperation.getInstance();
    private IUserMessagesOperation userMessagesOperation = UserMessagesOperation.getInstance();
    private IUserTransactionOperation userTransactionOperation=UserTransactionOperation.getInstance();
    private IUserAccount user;

    private HashMap<Integer, String> addingItemNewMessages; // key is itemId
    private HashMap<String, String> freezeNewMessages; // key is userId
    private HashMap<String, String> unfreezeNewMessages; // key is userId

    private List<ItemInterface> requestedItems;
    private List<String> addingItemRequestUsers;
    private List<String> unfrozenRequestUsers;

    private static AdminOperation adminOperation;

    public static final String USER_ID_FIELD_NAME = "uId";
    public static final String ADDING_ITEM_NEW_MESSAGES_KEY_FIELD_NAME = "addingItemNewMessagesKey";
    public static final String ADDING_ITEM_NEW_MESSAGES_Value_FIELD_NAME = "addingItemNewMessagesValue";
    public static final String FREEZE_NEW_MESSAGES_KEY_FIELD_NAME = "freezeNewMessagesKey";
    public static final String FREEZE_NEW_MESSAGES_VALUE_FIELD_NAME = "freezeNewMessagesValue";
    public static final String UNFREEZE_NEW_MESSAGES_KEY_FIELD_NAME = "unfreezeNewMessagesKey";
    public static final String UNFREEZE_NEW_MESSAGES_VALUE_FIELD_NAME = "unfreezeNewMessagesValue";
    public static final String REQUESTED_ITEMS_DB_FIELD_NAME = "requestedItemsDb";
    public static final String ADDING_ITEM_REQUEST_USERS_FIELD_NAME = "addingItemRequestUsers";
    public static final String UNFROZEN_REQUEST_USERS_FIELD_NAME = "unfrozenRequestUsers";

    @DatabaseField(columnName = USER_ID_FIELD_NAME)
    private String uId;

    @DatabaseField(columnName = ADDING_ITEM_NEW_MESSAGES_KEY_FIELD_NAME)
    private String addingItemNewMessagesKey ;
    @DatabaseField(columnName = ADDING_ITEM_NEW_MESSAGES_Value_FIELD_NAME)
    private String addingItemNewMessagesValue ;

    @DatabaseField(columnName = FREEZE_NEW_MESSAGES_KEY_FIELD_NAME)
    private String freezeNewMessagesKey ;
    @DatabaseField(columnName = FREEZE_NEW_MESSAGES_VALUE_FIELD_NAME)
    private String freezeNewMessagesValue ;

    @DatabaseField(columnName = UNFREEZE_NEW_MESSAGES_KEY_FIELD_NAME)
    private String unfreezeNewMessagesKey ;
    @DatabaseField(columnName = UNFREEZE_NEW_MESSAGES_VALUE_FIELD_NAME)
    private String unfreezeNewMessagesValue;

    @DatabaseField(columnName = REQUESTED_ITEMS_DB_FIELD_NAME)
    private String requestedItemsDb ;

    @DatabaseField(columnName = ADDING_ITEM_REQUEST_USERS_FIELD_NAME)
    private String addingRequestUsers ;

    @DatabaseField(columnName = UNFROZEN_REQUEST_USERS_FIELD_NAME)
    private String unfrozenUsers;

    /**
     * The constructor of this class AdminOperation
     * @param user the admin user who operates
     */
    public AdminOperation(IUserAccount user) {
        super(user.getUserId());
        if (user.getStatus().equals("Admin")) {
            this.user = user;
            this.uId = user.getUserId();
            unfreezeNewMessages = new HashMap<>();
            addingItemNewMessages = new HashMap<>();
            freezeNewMessages = new HashMap<>();
            requestedItems = new ArrayList<>();
            addingItemRequestUsers= new ArrayList<>();
            unfrozenRequestUsers= new ArrayList<>();
            DataSaverHelper dataSaverHelper = DataSaverHelper.getInstance();
            Set<Integer> addingKey = addingItemNewMessages.keySet();
            Collection<String> addingValue = addingItemNewMessages.values();
            Set<String> freezeKey = freezeNewMessages.keySet();
            Collection<String> freezeValue = freezeNewMessages.values();
            Set<String> unfreezeKey = unfreezeNewMessages.keySet();
            Collection<String> unfreezeValue = unfreezeNewMessages.values();
            addingItemNewMessagesKey = dataSaverHelper.KeySetIntegerConvert(addingKey);
            addingItemNewMessagesValue = dataSaverHelper.StringCollectionStringCovert(addingValue);
            freezeNewMessagesKey = dataSaverHelper.KeySetStringConvert(freezeKey);
            freezeNewMessagesValue = dataSaverHelper.StringCollectionStringCovert(freezeValue);
            unfreezeNewMessagesKey = dataSaverHelper.KeySetStringConvert(unfreezeKey);
            unfreezeNewMessagesValue = dataSaverHelper.StringCollectionStringCovert(unfreezeValue);
            requestedItemsDb = dataSaverHelper.ItemInterfaceListCovert(requestedItems);
            addingRequestUsers = dataSaverHelper.StringListConvert01(addingItemRequestUsers);
            unfrozenUsers = dataSaverHelper.StringListConvert01(unfrozenRequestUsers);
            
        }
    }

    /**
     * default constructor for class AdminOperation
     */
    public AdminOperation() {
        super();
    }

    /**
     * gets instance adminOperation
     * @param user the user who operates
     * @return adminOperation
     */
    public static IAdminOperation getInstance(IUserAccount user) {
        if(adminOperation == null){
        adminOperation = new AdminOperation(user);}
        return adminOperation;
    }

    /**
     * This method used to freeze an user account if the system reminded these users to be frozen.
     * @param user user which is reminded to be frozen by the system.
     * @param freezeUserMessage the message about freezing this user
     *
     */
    @Override
    public void freezeUser(IUserAccount user, String freezeUserMessage) {
        int diff1 = user.getNumLent() - user.getNumBorrowed();
        int diff2 = user.getNumBorrowed() - user.getNumLent();

        List<String> userInfo = new ArrayList<>();
        userInfo.add(user.getUserId());
        userInfo.add("This user has lent " + user.getNumLent() + " times. ");
        userInfo.add("This user has borrowed " + user.getNumBorrowed() + " times. ");
        if (user.getNumLent() > user.getNumBorrowed()) {
            userInfo.add("This user has lent " + diff1 + " more items than borrowed");
        }else {
            userInfo.add("This user has lent " + diff2 + " less items than borrowed");
        }

        for (String info : userInfo) {
            System.out.println(info);
        }
        if(diff1 < user.getThreshold()){
            if (user.getStatus().equals("Admin")) {
                user.setIsFreeze(this.user.getIsInitialAdmin());
            }
            user.setIsFreeze(true);

            String requestAccepted = "Sorry, " + "you are now frozen. ";
            userMessagesOperation.addNotification(requestAccepted, user.getUserId());
        }else {
            user.setIsFreeze(false);
        }
        removeFreezeNewMessages(user.getUserId(),freezeUserMessage);
        tradingSystem.removeFlagged(user);
    }

    /**
     * This method used to confirm to unfreeze an user account if this user requested.
     * @param user the user who want to unfreeze account.
     * @param newMessage the new message about confirmed unfreeze user this action
     */

    @Override
    public void confirmedUnFreezeUser(IUserAccount user, String newMessage){
        int diff = user.getNumLent() - user.getNumBorrowed();

        if(diff < user.getThreshold()){
            if (user.getStatus().equals("Admin")) {
                user.setIsFreeze(!(this.user.getIsInitialAdmin()));
            }
            user.setIsFreeze(false);

            String requestAccepted = "Congratulations, " +
                    "your request for unfreezing is accepted! ";

            userMessagesOperation.addNotification(requestAccepted, user.getUserId());
            opManager.updateAllAdminUnfreezeNewMessages(user.getUserId(),newMessage,"remove");
            opManager.updateAllAdminUnfrozenRequestUsers(user.getUserId(),"remove");
            removeUnfrozenRequestUsers(user.getUserId());
            removeUnfreezeNewMessages(user.getUserId(),newMessage);
        }
    }

    /**
     * This method used to reject to unfreeze an user account if this user requested.
     * @param user the user who want to unfreeze account.
     * @param newMessage the new message about rejecting unfreeze user this action
     */
    @Override
    public void rejectedUnFreezeUser(IUserAccount user, String newMessage){
        user.setIsFreeze(true);
        String requestRejected = "Sorry, " +
                "your request for unfreezing is rejected. ";
        userMessagesOperation.addNotification(requestRejected, user.getUserId());
        opManager.updateAllAdminUnfreezeNewMessages(user.getUserId(),newMessage,"remove");
        removeUnfreezeNewMessages(user.getUserId(),newMessage);
    }

    /**
     * This method is used to change an specific user's threshold.
     * @param user the user who should be changed his/her/its threshold.
     * @param newThreshold the new threshold should be given to user.
     */

    @Override
    public void changeThreshold(IUserAccount user, int newThreshold){
        user.setThreshold(newThreshold);
    }

    /**
     * This method is used to confirm that the item should be added.
     * @param user the user who want to add an item to wishToLend list.
     * @param newItem the item which is the user want to add to wishToLend list.
     * @param newMessage the new message of this request
     */
    @Override
    public void confirmAddItem(IUserAccount user, ItemInterface newItem, String newMessage) throws Exception {

        userWishListOperation.addToWishToLend(user, newItem);
        String requestAccepted = "Congratulations, your request for adding the item " +
                newItem.getItemName() + " is accepted! ";
        userMessagesOperation.addNotification(requestAccepted, user.getUserId());

        opManager.updateAllAdminAddingItemNewMessages(newItem.getItemId(), newMessage,"remove");
        opManager.updateAllAdminAddingItemRequestUsers(user.getUserId(),"remove");
        removeRequestedItem(newItem);
        opManager.updateAllAdminRequestedItems(newItem,"remove");
        removeAddingItemRequestUsers(user.getUserId());
        removeAddingItemNewMessages(newItem.getItemId(),newMessage);
    }

    /**
     * This method is used to reject that the item should be added.
     * @param user the user who want to add an item to wishToLend list.
     * @param newItem the item which is the user want to add to wishToLend list.
     * @param newMessage the new message of this request
     */
    @Override
    public void rejectAddItem(IUserAccount user, ItemInterface newItem, String newMessage){
        String requestRejected = "Sorry, your request for adding the item "
                + newItem.getItemName() + " is rejected.  ";
        userMessagesOperation.addNotification(requestRejected, user.getUserId());

        removeAddingItemNewMessages(newItem.getItemId(), newMessage);
        opManager.updateAllAdminAddingItemNewMessages(newItem.getItemId(), newMessage, "remove");
    }

    /**
     * Add adminUser to adminUser list.
     * initial administrative user should be able to add subsequent administrative users to the system.
     * @param user the new adminUser
     */
    @Override
    public void addAdminUser(IUserAccount user) throws Exception {
        if(!user.getIsInitialAdmin() && !user.getStatus().equals("Admin")){
            if(this.user.getIsInitialAdmin()){
                user.setStatus("Admin");
                tradingSystem.updateAccount(user);
                String newMessage = "Congratulations, your account is updated to be Admin ";
                userMessagesOperation.addNotification(newMessage, user.getUserId());
            }
        }
    }

    /**
     * Get the addingItemNewMessages for this user
     * @return the addingItemNewMessages of this user
     */
    @Override
    public HashMap<Integer, String> getAddingItemNewMessages() {
        return addingItemNewMessages;
    }

    /**
     * Add addingItemNewMessage to addingItemNewMessages
     * @param itemId the id for the item user want to add
     * @param addingItemNewMessage the addingItemNewMessage which is needed to be added
     */
    @Override
    public void addAddingItemNewMessages(int itemId, String addingItemNewMessage) {
        opManager.updateAllAdminAddingItemNewMessages(itemId,addingItemNewMessage,"add");
        this.addingItemNewMessages.put(itemId, addingItemNewMessage);
    }

    /**
     * Remove addingItemNewMessage in addingItemNewMessages
     * @param itemId the id for the item user want to remove
     * @param addingItemNewMessage the addingItemNewMessage which is needed to be removed
     */
    @Override
    public void removeAddingItemNewMessages(int itemId, String addingItemNewMessage) {
        opManager.updateAllAdminAddingItemNewMessages(itemId,addingItemNewMessage,"remove");
        this.addingItemNewMessages.remove(itemId, addingItemNewMessage);
    }

    /**
     * Get the freezeNewMessages for this user
     * @return the freezeNewMessages of this user
     */
    @Override
    public HashMap<String, String> getFreezeNewMessages() {
        return freezeNewMessages;
    }

    /**
     * Add freezeNewMessage to freezeNewMessages
     * @param userId the id for the user system wants to freeze
     * @param freezeNewMessage the freezeNewMessage which is needed to be added
     */
    @Override
    public void addFreezeNewMessages(String userId, String freezeNewMessage) {
        opManager.updateAllAdminFreezeNewMessages(userId,freezeNewMessage,"add");
        this.freezeNewMessages.put(userId, freezeNewMessage);
    }

    /**
     * Remove freezeNewMessage in freezeNewMessages
     * @param userId the id for the user system wants to freeze
     * @param freezeNewMessage the freezeNewMessage which is needed to be removed
     */
    @Override
    public void removeFreezeNewMessages(String userId, String freezeNewMessage) {
        opManager.updateAllAdminFreezeNewMessages(userId,freezeNewMessage,"remove");
//        this.freezeNewMessages.remove(userId, freezeNewMessage);
    }

    /**
     * Get the unfreezeNewMessages for this user
     * @return the unfreezeNewMessages of this user
     */
    @Override
    public HashMap<String, String> getUnfreezeNewMessages() {
        return unfreezeNewMessages;
    }

    /**
     * Add unfreezeNewMessage to unfreezeNewMessages
     * @param userId the id for the user who wants to be unfrozen
     * @param unfreezeNewMessage the unfreezeNewMessage which is needed to be added
     */
    @Override
    public void addUnfreezeNewMessages(String userId, String unfreezeNewMessage) {

//        this.freezeNewMessages.put(userId, unfreezeNewMessage);
        opManager.updateAllAdminUnfreezeNewMessages(userId,unfreezeNewMessage,"add");
    }

    /**
     * Remove unfreezeNewMessage in unfreezeNewMessages
     * @param userId the id for the user who wants to be unfrozen
     * @param unfreezeNewMessage the unfreezeNewMessage which is needed to be removed
     */
    @Override
    public void removeUnfreezeNewMessages(String userId, String unfreezeNewMessage) {
        this.freezeNewMessages.remove(userId, unfreezeNewMessage);
        opManager.updateAllAdminUnfreezeNewMessages(userId,unfreezeNewMessage,"remove");
    }

    /**
     * Get requested items from stored map
     * @return the value of requested items
     */
    @Override
    public List<ItemInterface> getRequestedItems() {
        return requestedItems;
    }

    /**
     * Set the requestedItems for this user
     * @param requestedItems the requested items for this user
     */
    @Override
    public void setRequestedItems(List<ItemInterface> requestedItems) {
        this.requestedItems = requestedItems;
    }

    /**
     * Add the requested item to stored map
     * @param requestedItem the item was requested need to be added
     */
    @Override
    public void addRequestedItem(ItemInterface requestedItem) {
        requestedItems.add(requestedItem);
        opManager.updateAllAdminRequestedItems(requestedItem,"add");
    }

    /**
     * remove the requested item to stored map
     * @param requestedItem the item was requested need to be added
     */
    @Override
    public void removeRequestedItem(ItemInterface requestedItem) {
        requestedItems.remove(requestedItem);
        opManager.updateAllAdminRequestedItems(requestedItem,"remove");
    }

    /**
     * Get the adding item requests from users
     * @return the adding item requests from users
     */
    @Override
    public List<String> getAddingItemRequestUsers() {
        return addingItemRequestUsers;
    }

    /**
     * Set the addingItemRequestUsers for this user
     * @param addingItemRequestUsers the user who want to add adding item this request
     */
    @Override
    public void setAddingItemRequestUsers(List<String> addingItemRequestUsers) {
        this.addingItemRequestUsers = addingItemRequestUsers;

    }

    /**
     * Add the adding item request to the list
     * @param addingItemRequestUser the user who want to add adding item this request
     */
    @Override
    public void addAddingItemRequestUsers(String addingItemRequestUser) {
        this.addingItemRequestUsers.add(addingItemRequestUser);
        opManager.updateAllAdminAddingItemRequestUsers(addingItemRequestUser,"add");
    }

    /**
     * Remove the adding item request to the list
     * @param addingItemRequestUser the user who want to remove adding item this request
     */
    @Override
    public void removeAddingItemRequestUsers(String addingItemRequestUser) {
        this.addingItemRequestUsers.remove(addingItemRequestUser);
        opManager.updateAllAdminAddingItemRequestUsers(addingItemRequestUser,"remove");
    }


    /**
     * Get the users who want to request to unfreeze their accounts
     * @return the value of users who want to request to unfreeze their accounts
     */
    @Override
    public List<String> getUnfrozenRequestUsers() {
        return unfrozenRequestUsers;
    }

    /**
     * Set the unfrozenRequestUsers for this user
     * @param unfrozenRequestUsers the unfrozen request for this users
     */

    @Override
    public void setUnfrozenRequestUsers(List<String> unfrozenRequestUsers) {
        this.unfrozenRequestUsers = unfrozenRequestUsers;
    }

    /**
     * Add the users who want to request to unfreeze their accounts
     * @param unfrozenRequestUser the users who want to request to unfreeze their accounts
     */
    @Override
    public void addUnfrozenRequestUsers(String unfrozenRequestUser) {
        opManager.updateAllAdminUnfrozenRequestUsers(unfrozenRequestUser,"add");
        this.unfrozenRequestUsers.add(unfrozenRequestUser);
    }

    /**
     * Remove the users who want to request to unfreeze their accounts
     * @param unfrozenRequestUser the users who want to request to unfreeze their accounts
     */
    @Override
    public void removeUnfrozenRequestUsers(String unfrozenRequestUser) {
        opManager.updateAllAdminUnfrozenRequestUsers(unfrozenRequestUser,"remove");
        this.unfrozenRequestUsers.remove(unfrozenRequestUser);
    }

    /**
     * adjust all of the threshold values for users
     * according to the rule：The number of lent items must be greater than 30% of the size of wishToBorrow
     * the number of borrowed items
     * which means threshold is 0.3* the size of wishToBorrow list for this user
     */
    @Override
    public void adjustThreshold(){
        HashMap<String,IUserAccount> users = userManager.getAdminUsers();
        users.putAll(userManager.getNormalUsers());
        if (!(users.size() ==0)){
            for(IUserAccount user: users.values()){
                changeThreshold(user, (int) (0.3*opManager.getProperOperationByUser(user).getWishToBorrow().size()));
            }
        }
    }

    /**
     * Cancel add item this request if this user want it depend on adminUser
     * @param user the user who want to cancel add item this request
     * @param newItem the new item
     * @param newMessage the new message of this request
     */
    @Override
    public void cancelAddItemRequest(IUserAccount user, ItemInterface newItem, String newMessage){
        String requestCanceled = "your request for adding the item " + newItem.getItemName() + " is canceled! ";
        userMessagesOperation.addNotification(requestCanceled, user.getUserId());
        removeAddingItemRequestUsers(user.getUserId());
        removeAddingItemNewMessages(newItem.getItemId(), newMessage);
        removeRequestedItem(newItem);
    }

    /**
     * This method used to cancel the request for unfreezing an user account if this user requested.
     * @param user the user who want to cancel request to unfreeze
     * @param newMessage the new message of this request
     */
    @Override
    public void cancelUnfreezeRequest(IUserAccount user, String newMessage){
        if (!user.getIsInitialAdmin()) {
            removeUnfrozenRequestUsers(user.getUserId());
        }
        String requestCanceled = "your request for unfreezing is canceled by adminUser! ";
        userMessagesOperation.addNotification(requestCanceled, user.getUserId());
        removeUnfreezeNewMessages(user.getUserId(),newMessage);
    }

    /**
     * This method used for canceling the confirmed transaction
     * @param user the user who want to cancel confirmed transaction
     * @param transaction the transaction which is this user want to cancel
     */
    @Override
    public void cancelConfirmedTransaction(IUserAccount user, ITransaction transaction){
        meetingSystem.getCorrespondMeetingByTransId(transaction.getTranId()).setConfirmTrans(false);
        userTransactionOperation.removeConfirmedTransaction(transaction.getTranId(),user.getUserId());
        String cancelingConfirmedTransaction = "your confirmed transaction is canceled by adminUser! ";
        userMessagesOperation.addNotification(cancelingConfirmedTransaction,user.getUserId());

    }

    /**
     * This method used for this user to cancel invited transaction
     * @param user the user who want to reject this transaction
     * @param transaction the invited transaction from other user
     */
    @Override
    public void cancelInvitedTransaction(IUserAccount user, ITransaction transaction){
        userTransactionOperation.removeInvitedTrans(transaction,user.getUserId());
        String rejectingInvitedTransaction = "your invited transaction is canceled by adminUser! ";
        userMessagesOperation.addNotification(rejectingInvitedTransaction, user.getUserId());
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        String newMessage = userOperation.getInviteTransNewMessages().get(transaction.getTranId());
        userMessagesOperation.removeInviteTransNewMessage(transaction.getTranId(), newMessage,user.getUserId());
    }

    /**
     * Cancel the state that this user is on vacation
     * @param user the user who is on vacation.
     *
     */
    @Override
    public void cancelOnVacationForUser(IUserAccount user){
        String cancelVacation = "your onVacation status was canceled by adminUser";
        userMessagesOperation.addNotification(cancelVacation, user.getUserId());
        user.cancelOnVacation();
    }

    /**
     * Find the new item id by using this userId
     * @param userId the userId which is the user who want to add new item
     * @return the value of new itemId
     */
    @Override
    public int findRequestedNewItem(String userId){
        int result = 0;
        for (ItemInterface item: requestedItems){
            if (item.getOwner().equals(userId)){
                result = item.getItemId();
            }
        }
        return result;
    }

    /**
     * Get the user id
     * @return the value of user id
     */
    @Override
    public String getUserId(){
        return uId;
    }

    /**
     * sets adding item new messages
     * @param addingItemNewMessages adding item new messages
     */
    public void setAddingItemNewMessages(HashMap<Integer, String> addingItemNewMessages) {
        this.addingItemNewMessages = addingItemNewMessages;
    }

    /**
     * sets freeze new messages
     * @param freezeNewMessages freeze new messages
     */
    public void setFreezeNewMessages(HashMap<String, String> freezeNewMessages) {
        this.freezeNewMessages = freezeNewMessages;
    }

    /**
     * sets unfreeze new messages
     * @param unfreezeNewMessages unfreeze new messages
     */
    public void setUnfreezeNewMessages(HashMap<String, String> unfreezeNewMessages) {
        this.unfreezeNewMessages = unfreezeNewMessages;
    }

    /**
     * gets the key for adding item new messages
     * @return key for adding item new messages
     */
    @Override
    public String getAddingItemNewMessagesKey() {
        return addingItemNewMessagesKey;
    }

    /**
     * gets the value for adding item new messages
     * @return value for adding item new messages
     */
    @Override
    public String getAddingItemNewMessagesValue() {
        return addingItemNewMessagesValue;
    }

    /**
     * gets the key for freeze new messages
     * @return key for freeze new messages
     */
    @Override
    public String getFreezeNewMessagesKey() {
        return freezeNewMessagesKey;
    }

    /**
     * gets the value for freeze new messages
     * @return value for freeze new messages
     */
    @Override
    public String getFreezeNewMessagesValue() {
        return freezeNewMessagesValue;
    }

    /**
     * gets the key for unfreeze new messages
     * @return key for unfreeze new messages
     */
    @Override
    public String getUnfreezeNewMessagesKey() {
        return unfreezeNewMessagesKey;
    }

    /**
     * gets the value for unfreeze new messages
     * @return value for unfreeze new messages
     */
    @Override
    public String getUnfreezeNewMessagesValue() {
        return unfreezeNewMessagesValue;
    }

    /**
     * gets the database for requested items
     * @return database for requested items
     */
    @Override
    public String getRequestedItemsDb() {
        return requestedItemsDb;
    }

    /**
     * gets users sending adding request
     * @return users sending adding request
     */
    @Override
    public String getAddingRequestUsers() {
        return addingRequestUsers;
    }

    /**
     * gets unfrozen users
     * @return unfrozen users
     */
    @Override
    public String getUnfrozenUsers() {
        return unfrozenUsers;
    }

    @Override
    public ItemInterface getItemById(int id){
        if(!(requestedItems.size() == 0)){
        for(ItemInterface item: requestedItems){
            if(item.getItemId() == id){
                return item;
            }
        }}
        return new Item(null, null, null,false);
    }
}
