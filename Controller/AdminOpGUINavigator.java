package Controller;

import GateWay.*;
import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.IOpManager;
import UseCases.OpManager;
import UseCases.UserUseCase.*;

import java.util.HashMap;
import java.util.List;

/**
 * navigates the methods from AdminUser
 * @author Hongyu Chen
 * @version 1.8
 */
public class AdminOpGUINavigator {

    private static final AdminOpGUINavigator adminOpGUINavigator = new AdminOpGUINavigator();
    private final IDataUpdater dataUpdater;
    private final IDataDeleter dataDeleter ;
    private final IDataInsertion dataInsertion;
    private final IUserManager userManager;
    private final IOpManager opManager;

    /**
     * the constructor for class UserAccountGUINavigator
     */
    public AdminOpGUINavigator(){
        dataUpdater = new DataUpdater();
        dataDeleter = new DataDeleter();
        dataInsertion = new DataInsertion();
        userManager = UserManager.getInstance();
        opManager = OpManager.getInstance();
    }

    /**
     * gets the attribute ADMIN_OP_GUI_NAVIGATOR
     * @return ADMIN_OP_GUI_NAVIGATOR
     */
    public static AdminOpGUINavigator getInstance(){

        return adminOpGUINavigator;
    }

    /**
     * This method navigates the method getting addingItemNewMessages from this user.
     * @return addingItemNewMessages received by userAccount
     */
    public HashMap<Integer, String> getAddingItemNewMessagesNavigator(IUserAccount adminUser) {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        return adminOperation.getAddingItemNewMessages();
    }

    /**
     * This method navigates the method adding addingItemNewMessage to this user's addingItemNewMessages.
     * @param itemId the id of item userAccount wants to add
     * @param addingItemNewMessage the addingItemNewMessage which is needed to be added
     */
    public void addAddingItemNewMessageNavigator(IUserAccount adminUser, int itemId, String addingItemNewMessage) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        adminOperation.addAddingItemNewMessages(itemId, addingItemNewMessage);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataUpdater.adminUserUpdater(adminUser);

    }

    /**
     * This method navigates the method adding unfreezeNewMessage to this user's unfreezeNewMessages.
     * @param userId the id of userAccount requested
     * @param unfreezeNewMessage the unfreezeNewMessage which is needed to be unfrozen
     */
    public void addUnfreezeNewMessageNavigator(
            IUserAccount adminUser, String userId, String unfreezeNewMessage) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        adminOperation.addUnfreezeNewMessages(userId, unfreezeNewMessage);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataUpdater.adminUserUpdater(adminUser);

    }

    /**
     * This method navigates the method adding unfreezing request user
     * @param requestUser the user who requested
     */
    public void addUnfreezeRequestUserNavigator(IUserAccount adminUser, IUserAccount requestUser) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(requestUser);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.addUnfrozenRequestUsers(requestUser.getUserId());
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.adminUserUpdater(adminUser);
    }

    /**
     * This method navigates the method getting freezeNewMessages from this user.
     * @return freezeNewMessages received by userAccount
     */
    public HashMap<String, String> getFreezeNewMessagesNavigator(IUserAccount adminUser) {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        return adminOperation.getFreezeNewMessages();
    }

    /**
     * This method navigates the method getting unfreezeNewMessages from this user.
     * @return unfreezeNewMessages received by userAccount
     */
    public HashMap<String, String> getUnfreezeNewMessagesNavigator(IUserAccount adminUser) {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        return adminOperation.getUnfreezeNewMessages();
    }

    /**
     * This method navigates the method getting requested items list
     * @return the user's requested items list
     */
    public List<ItemInterface> getRequestedItemsNavigator(IUserAccount adminUser) {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        return adminOperation.getRequestedItems();
    }

    /**
     * This method navigates the method adding requested item
     * @param requestedItem the requested item which needs to be added
     */
    public void addRequestedItemNavigator(IUserAccount adminUser, ItemInterface requestedItem) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        adminOperation.addRequestedItem(requestedItem);
        for (String admin: opManager.getAllAdminOperations().keySet()){
            opManager.getProperOperationByUser(userManager.getUserById(admin));
            dataInsertion.adminOperationInsertion(adminOperation);
            dataUpdater.adminUserUpdater(adminUser);
        }

    }

    /**
     * This method navigates the method getting unfrozen request users list
     * @return the user's unfrozen request users list
     */
    public List<String> getUnfrozenRequestUsersNavigator(IUserAccount adminUser) {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        return adminOperation.getUnfrozenRequestUsers();
    }

    /**
     * This method navigates the method confirming adding item
     * @param requestUser the user who requests
     * @param itemInterface the item which is wanted to be added
     */
    public void confirmAddItemNavigator(IUserAccount adminUser, IUserAccount requestUser, ItemInterface itemInterface, String newMessage) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(requestUser);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.confirmAddItem(requestUser, itemInterface, newMessage);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.adminUserUpdater(adminUser);
        dataInsertion.itemInsertion(itemInterface);
        dataUpdater.normalUserUpdater(userManager.getUserById(itemInterface.getOwner()));// 需要吗
    }

    /**
     * This method navigates the method unfreezing user
     * @param requestUser the user who requested to be unfrozen
     * @param newMessage the new message about unfreeze user this action
     */
    public void confirmedUnfreezeUserNavigator(IUserAccount adminUser,
                                               IUserAccount requestUser,
                                               String newMessage) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(requestUser);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.confirmedUnFreezeUser(requestUser,newMessage);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.adminUserUpdater(adminUser);
        dataUpdater.normalUserUpdater(requestUser);
    }

    /**
     * This method navigates the method freezing user
     * @param freezeUser the users who need to be frozen
     */
    public void freezeUserNavigator(IUserAccount adminUser, IUserAccount freezeUser, String newMessage) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(freezeUser);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.freezeUser(freezeUser, newMessage);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.adminUserUpdater(adminUser);
        dataUpdater.normalUserUpdater(freezeUser);
    }

    /**
     * This method navigates the method adding adding item request user
     * @param requestUser the user who requested
     */
    public void addAddingItemRequestUserNavigator(IUserAccount adminUser, IUserAccount requestUser) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(requestUser);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.addAddingItemRequestUsers(requestUser.getUserId());
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.adminUserUpdater(adminUser);
    }

    /**
     * This method navigates the method adjust all of the threshold values for users
     */
    public void adjustThresholdNavigator(IUserAccount adminUser) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        adminOperation.adjustThreshold();
        for(String user : userManager.getNormalUsers().keySet()){
            dataUpdater.normalUserUpdater(userManager.getUserById(user));
        }
    }

    /**
     * navigates the getter for addingItemRequestUsers List
     * @return user's addingItemRequestUsers List
     */
    public List<String> getAddingItemRequestUsersNavigator(IUserAccount adminUser){
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        return adminOperation.getAddingItemRequestUsers();
    }

    /**
     * navigates the method finding requested new item
     * @param userId the id of the user who requests
     * @return requested new item
     */
    public int findRequestedNewItemNavigator(IUserAccount adminUser, String userId){
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        return adminOperation.findRequestedNewItem(userId);
    }

    /**
     * This method navigates the method of changing the threshold of one user
     * from the class IUserAccount. When one administrative user wants to change
     * one user's threshold, the class ManageUserMenu calls this method.
     *
     * @param userAccount The user whose threshold is the threshold adminUser wants to change.
     * @param newThreshold The new threshold adminUser wants to change the threshold of userAccount to.
     */
    public void changeThresholdNavigator(IUserAccount adminUser, IUserAccount userAccount, int newThreshold) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        adminOperation.changeThreshold(userAccount, newThreshold);
        dataUpdater.normalUserUpdater(userAccount);
    }

    /**
     * This method navigates the method used to reject to unfreeze an user account if this user requested.
     * @param newMessage the new message about unfreeze user this action
     * @param user the user who want to unfreeze account.
     */
    public void rejectedUnFreezeUserNavigator(IUserAccount adminUser, IUserAccount user, String newMessage) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.rejectedUnFreezeUser(user,newMessage);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.normalUserUpdater(user);
        dataUpdater.adminUserUpdater(user);
    }

    /**
     * This method navigates the method is used to reject that the item should be added.
     * @param user the user who want to add an item to wishToLend list.
     * @param newItem the item which is the user want to add to wishToLend list.
     * @param newMessage the new message of this request
     */
    public void rejectAddItemNavigator(IUserAccount adminUser, IUserAccount user, ItemInterface newItem, String newMessage) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.rejectAddItem(user,newItem,newMessage);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.normalUserUpdater(user);
        dataUpdater.adminUserUpdater(user);
    }

    /**
     * This method navigates the method cancel add item this request if this user want it depend on adminUser
     * @param user the user who want to cancel add item this request
     * @param newItem the new item
     * @param newMessage the new message of this request
     */
    public void cancelAddItemRequestNavigator(IUserAccount adminUser, IUserAccount user, ItemInterface newItem, String newMessage) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.cancelAddItemRequest(user,newItem,newMessage);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.adminUserUpdater(adminUser);
        dataUpdater.normalUserUpdater(user);
    }

    /**
     * This method navigates the method used to cancel the request for unfreezing an user account if
     * this user requested.
     * @param newMessage the new message of this request
     * @param user the user who want to cancel request to unfreeze
     */
    public void cancelUnfreezeRequestNavigator(IUserAccount adminUser, IUserAccount user, String newMessage) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.cancelUnfreezeRequest(user,newMessage);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
    }

    /**
     * This method navigates the method used for canceling the confirmed transaction
     * @param user the user who want to cancel confirmed transaction
     * @param transaction the transaction which is this user want to cancel
     */
    public void cancelConfirmedTransactionNavigator(IUserAccount adminUser, IUserAccount user, ITransaction transaction) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.cancelConfirmedTransaction(user,transaction);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.adminUserUpdater(adminUser);
        dataUpdater.normalUserUpdater(user);
    }

    /**
     * This method navigates the method used for this user to canceling invited transaction
     * @param user the user who want to reject this transaction
     * @param transaction the invited transaction from other user
     */
    public void cancelInvitedTransactionNavigator(IUserAccount adminUser, IUserAccount user, ITransaction transaction) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.cancelInvitedTransaction(user,transaction);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.adminUserUpdater(adminUser);
        dataUpdater.normalUserUpdater(user);
    }

    /**
     * This method navigates the method cancel the state that this user is on vacation
     * @param user the user who is on vacation.
     */
    public void cancelOnVacationForUserNavigator(IUserAccount adminUser, IUserAccount user) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataDeleter.userOperationDeleter((UserOperation) userOperation);
        adminOperation.cancelOnVacationForUser(user);
        dataInsertion.adminOperationInsertion(adminOperation);
        dataInsertion.userOperationInsertion(userOperation);
        dataUpdater.adminUserUpdater(adminUser);
        dataUpdater.normalUserUpdater(user);
    }

    /**
     * This method navigates the method adding adminUser
     * @param newAdminUser the normal user who will be the admin user
     */
    public void addAdminUserNavigator(IUserAccount adminUser,IUserAccount newAdminUser) throws Exception {
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        dataDeleter.userOperationDeleter((UserOperation) opManager.getUserOperationById(newAdminUser.getUserId()));
        adminOperation.addAdminUser(newAdminUser);
        dataDeleter.adminOperationDeleter((AdminOperation) adminOperation);
        dataUpdater.adminUserUpdater(adminUser);
        dataInsertion.adminUserInsertion(newAdminUser);
        dataInsertion.adminOperationInsertion((IAdminOperation) opManager.getUserOperationById(newAdminUser.getUserId()));
        dataUpdater.adminUserUpdater(newAdminUser);
    }

    /**
     * Get the item which is from the admin requests Item list
     * @param adminUser the adminUser
     * @param id the item id
     * @return the value of item
     */
    public ItemInterface getItemByIdNavigator(IUserAccount adminUser,int id){
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(adminUser);
        return adminOperation.getItemById(id);
    }
}
