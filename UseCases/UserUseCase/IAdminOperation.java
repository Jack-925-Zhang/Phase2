package UseCases.UserUseCase;

import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;

import java.util.HashMap;
import java.util.List;

/**
 * This class, IAdmin, is the interface for the AdminUser,
 * gives the needed method related to all actions of the administrator
 * @author LINNI XIE
 * @version 1.8
 */
public interface IAdminOperation extends IUserOperation{
    /**
     * This method is used to change an specific user's threshold.
     *
     * @param user         the user who should be changed his/her/its threshold.
     * @param newThreshold the new threshold should be given to user.
     */
    void changeThreshold(IUserAccount user, int newThreshold);

    /**
     * Add adminUser to adminUser list.
     * initial administrative user should be able to add subsequent administrative users to the system.
     *
     * @param user the new adminUser
     */
    void addAdminUser(IUserAccount user) throws Exception;

    /**
     * This method used to confirm to unfreeze an user account if this user requested.
     * @param user the user who want to unfreeze account.
     * @param newMessage the new message about confirmed unfreeze user this action
     */
    void confirmedUnFreezeUser(IUserAccount user, String newMessage) throws Exception;

    /**
     * This method used to reject to unfreeze an user account if this user requested.
     * @param user the user who want to unfreeze account.
     * @param newMessage the new message about rejecting unfreeze user this action
     */
    void rejectedUnFreezeUser(IUserAccount user, String newMessage) throws Exception;

    /**
     * This method used to freeze an user account if the system reminded these users to be frozen.
     * @param user user which is reminded to be frozen by the system.
     * @param freezeUserMessage the message about freezing this user
     */
    void freezeUser(IUserAccount user, String freezeUserMessage) throws Exception;

    /**
     * This method is used to confirm that the item should be added.
     * @param user the user who want to add an item to wishToLend list.
     * @param newItem the item which is the user want to add to wishToLend list.
     * @param newMessage the new message of this request
     */
    void confirmAddItem(IUserAccount user, ItemInterface newItem, String newMessage) throws Exception;

    /**
     * This method is used to reject that the item should be added.
     * @param user the user who want to add an item to wishToLend list.
     * @param newItem the item which is the user want to add to wishToLend list.
     * @param newMessage the new message of this request
     */
    void rejectAddItem(IUserAccount user, ItemInterface newItem, String newMessage) throws Exception;

    /**
     * Get requested items from stored map
     *
     * @return the value of requested items
     */

    List<ItemInterface> getRequestedItems();

    /**
     * Add the requested item to stored map
     *
     * @param requestedItem the item was requested need to be added
     */

    void addRequestedItem(ItemInterface requestedItem);

    /**
     * remove the requested item to stored map
     *
     * @param requestedItem the item was requested need to be added
     */

    void removeRequestedItem(ItemInterface requestedItem);

    /**
     * Get the adding item requests from users
     *
     * @return the adding item requests from users
     */
    List<String> getAddingItemRequestUsers();

    /**
     * Add the adding item request to the list
     *
     * @param addingItemRequestUser the userId who want to add adding item this request
     */

    void addAddingItemRequestUsers(String addingItemRequestUser);

    /**
     * Remove the adding item request to the list
     *
     * @param addingItemRequestUser the userId who want to remove adding item this request
     */

    void removeAddingItemRequestUsers(String addingItemRequestUser);

    /**
     * Get the users who want to request to unfreeze their accounts
     *
     * @return the value of users who want to request to unfreeze their accounts
     */

    List<String> getUnfrozenRequestUsers();

    /**
     * Add the users who want to request to unfreeze their accounts
     *
     * @param unfrozenRequestUser the userIds who want to request to unfreeze their accounts
     */

    void addUnfrozenRequestUsers(String unfrozenRequestUser);

    /**
     * Remove the users who want to request to unfreeze their accounts
     *
     * @param unfrozenRequestUser the userIds who want to request to unfreeze their accounts
     */

    void removeUnfrozenRequestUsers(String unfrozenRequestUser);

    /**
     * Set the requestedItems for this user
     *
     * @param requestedItems the requested items for this user
     */
    void setRequestedItems(List<ItemInterface> requestedItems);

    /**
     * Set the addingItemRequestUsers for this user
     *
     * @param addingItemRequestUsers the adding items request for this users
     */
    void setAddingItemRequestUsers(List<String> addingItemRequestUsers);

    /**
     * Set the unfrozenRequestUsers for this user
     *
     * @param unfrozenRequestUsers the unfrozen request for this users
     */
    void setUnfrozenRequestUsers(List<String> unfrozenRequestUsers);

    /**
     * adjust all of the threshold values for users
     */
    void adjustThreshold();

    /**
     * Cancel add item this request if this user want it depend on adminUser
     *  @param user    the user who want to cancel add item this request
     * @param newItem the new item
     * @param newMessage the new message of this request
     */
    void cancelAddItemRequest(IUserAccount user, ItemInterface newItem, String newMessage) throws Exception;

    /**
     * This method used to cancel the request for unfreezing an user account if this user requested.
     * @param user the user who want to cancel request to unfreeze
     * @param newMessage the new message of this request
     */
    void cancelUnfreezeRequest(IUserAccount user, String newMessage) throws Exception;

    /**
     * This method used for canceling the confirmed transaction
     * @param user        the user who want to cancel confirmed transaction
     * @param transaction the transaction which is this user want to cancel
     */
    void cancelConfirmedTransaction(IUserAccount user, ITransaction transaction) throws Exception;

    /**
     * This method used for this user to canceling invited transaction
     * @param user        the user who want to reject this transaction
     * @param transaction the invited transaction from other user
     */
    void cancelInvitedTransaction(IUserAccount user, ITransaction transaction) throws Exception;

    /**
     * Cancel the state that this user is on vacation
     * @param user the user who is on vacation.
     *
     */
    void cancelOnVacationForUser(IUserAccount user) throws Exception;

    /**
     * Find the new item id by using this userId
     * @param userId the userId which is the user who want to add new item
     * @return the value of new itemId
     */
    int findRequestedNewItem(String userId);

    /**
     * Get the addingItemNewMessages for this user
     *
     * @return the addingItemNewMessages of this user
     */
    HashMap<Integer, String> getAddingItemNewMessages();

    /**
     * Add addingItemNewMessage to addingItemNewMessages
     * @param itemId the id for the item user want to add
     * @param addingItemNewMessage the addingItemNewMessage which is needed to be added
     */
    void addAddingItemNewMessages(int itemId, String addingItemNewMessage);

    /**
     * Remove addingItemNewMessage in addingItemNewMessages
     * @param itemId the id for the item user want to remove
     * @param addingItemNewMessage the addingItemNewMessage which is needed to be removed
     */
    void removeAddingItemNewMessages(int itemId, String addingItemNewMessage);

    /**
     * Get the freezeNewMessages for this user
     *
     * @return the freezeNewMessages of this user
     */
    HashMap<String, String> getFreezeNewMessages();

    /**
     * Add freezeNewMessage to freezeNewMessages
     * @param userId the id for the user system wants to freeze
     * @param freezeNewMessage the freezeNewMessage which is needed to be added
     */
    void addFreezeNewMessages(String userId, String freezeNewMessage);

    /**
     * Remove freezeNewMessage in freezeNewMessages
     * @param userId the id for the user system wants to freeze
     * @param freezeNewMessage the freezeNewMessage which is needed to be removed
     */
    void removeFreezeNewMessages(String userId, String freezeNewMessage);

    /**
     * Get the unfreezeNewMessages for this user
     *
     * @return the unfreezeNewMessages of this user
     */
    HashMap<String, String> getUnfreezeNewMessages();

    /**
     * Add unfreezeNewMessage to unfreezeNewMessages
     * @param userId the id for the user who wants to be unfrozen
     * @param unfreezeNewMessage the unfreezeNewMessage which is needed to be added
     */
    void addUnfreezeNewMessages(String userId, String unfreezeNewMessage);

    /**
     * Remove unfreezeNewMessage in unfreezeNewMessages
     * @param userId the id for the user who wants to be unfrozen
     * @param unfreezeNewMessage the unfreezeNewMessage which is needed to be removed
     */
    void removeUnfreezeNewMessages(String userId, String unfreezeNewMessage);

    /**
     * Get the user id
     * @return the value of user id
     */
    String getUserId();

    /**
     * sets adding item new messages
     * @param addingItemNewMessages adding item new messages
     */
    void setAddingItemNewMessages(HashMap<Integer, String> addingItemNewMessages);

    /**
     * sets freeze new messages
     * @param freezeNewMessages freeze new messages
     */
    void setFreezeNewMessages(HashMap<String, String> freezeNewMessages);

    /**
     * sets unfreeze new messages
     * @param unfreezeNewMessages unfreeze new messages
     */
    void setUnfreezeNewMessages(HashMap<String, String> unfreezeNewMessages);

    /**
     * gets the key for adding item new messages
     * @return key for adding item new messages
     */
    String getAddingItemNewMessagesKey();

    /**
     * gets the value for adding item new messages
     * @return value for adding item new messages
     */
    String getAddingItemNewMessagesValue();

    /**
     * gets the key for freeze new messages
     * @return key for freeze new messages
     */
    String getFreezeNewMessagesKey();

    /**
     * gets the value for freeze new messages
     * @return value for freeze new messages
     */
    String getFreezeNewMessagesValue();

    /**
     * gets the key for unfreeze new messages
     * @return key for unfreeze new messages
     */
    String getUnfreezeNewMessagesKey();

    /**
     * gets the value for unfreeze new messages
     * @return value for unfreeze new messages
     */
    String getUnfreezeNewMessagesValue();

    /**
     * gets the database for requested items
     * @return database for requested items
     */
    String getRequestedItemsDb();

    /**
     * gets users sending adding request
     * @return users sending adding request
     */
    String getAddingRequestUsers();

    /**
     * gets unfrozen users
     * @return unfrozen users
     */
    String getUnfrozenUsers();

    ItemInterface getItemById(int id);

}
