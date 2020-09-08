package UseCases;

import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.UserUseCase.*;

import java.util.Map;

/**
 * manages the user's operation
 * @author Qian Tang
 * @version 1.8
 */

public interface IOpManager {

    /**
     * method to get all user operation map
     * @return UserOperation Map
     */
    Map<String, IUserOperation> getAllUserOperations();

    /**
     * set up new user operation map
     * @param allUserOperations new user operation map
     */
    void setAllUserOperations(Map<String, IUserOperation> allUserOperations);


    /**
     *  method to get all admin user operation map
     * @return all admin user operation map
     */
    Map<String, IAdminOperation> getAllAdminOperations() ;

    /**
     * set up new admin user operation map
     * @param allAdminOperations new admin user operation map
     */
    void setAllAdminOperations(Map<String, IAdminOperation> allAdminOperations);

    /**
     * method to get all user operation map
     * @return UserOperation Map
     */
    Map<String, IGuestOperation> getAllGuestOperations();

    /**
     * set up new user operation map
     * @param allGuestOperations new user operation map
     */
    void setAllGuestOperations(Map<String, IGuestOperation> allGuestOperations);

//    /**
//     * method to get all user Transactions operations.
//     * @return all user Transactions operations.
//     */
//    Map<String, IUserTransactionOperation> getAllUserTransactionOperation();
//
//    /**
//     * method to setup user Transactions Operations
//     * @param allUserTransactionOperation
//     */
//    void setAllUserTransactionOperation(Map<String, IUserTransactionOperation> allUserTransactionOperation);

    /**
     * method to create UserOperation and it to corresponding hashmap
     * @param user the user corresponding to the operation
     * @return new operation
     */
    IUserOperation CreateUserOperation(IUserAccount user);

    /**
     * method to create UserOperation and it to corresponding hashmap
     * @param user the user corresponding to the operation
     * @return new operation
     */
    IAdminOperation CreateAdminOperation(IUserAccount user);

    /**
     * method to create GuestOperation and it to corresponding hashmap
     * @param user the user corresponding to the operation
     * @return new operation
     */
    IGuestOperation CreateGuestOperation(IUserAccount user);

//    /**
//     * method to created IUserTransactionOperation and  add it to corresponding hashmap
//     * @param user the user corresponding to this operation
//     * @return new transactionOperation
//     */
//    IUserTransactionOperation CreateUserTransactionOperation(IUserAccount user);

    /**
     * method to get normal user operation using id.
     * @param userId the id we want
     * @return the normal operation we want
     */
    IUserOperation getUserOperationById(String userId);

    /**
     * method to get normal admin user operation by id.
     * @param userId the id we want
     * @return the admin operation we want
     */
    IAdminOperation getAdminOperationById(String userId);

    IGuestOperation getGuestOperationById(String guestId);

    /**
     * Method to get a proper operation for user account without knowing it is a admin or normal
     * @param userAccount the user we need
     * @return operation we want
     */
    IUserOperation getProperOperationByUser(IUserAccount userAccount);

    /**
     * method to get a Guest operation, since it is different than AdminOperation and UserOperation.
     * @param user the account that we want to find for guest user.
     * @return corresponding Operation for this user.
     */
    IGuestOperation getProperOperationByGuest(IUserAccount user);

    /**
     * method to update all admin Users UnfrozenRequestUsers list.
     * @param userAccount corresponding user
     * @param movement "add" or "remove"
     */
    void updateAllAdminUnfrozenRequestUsers(String userAccount, String movement);

    /**
     * Method to update all admin User's addingItemNewMessages.
     * @param itemId corresponding item
     * @param message corresponding message
     * @param movement "add" or "remove"
     */
    void updateAllAdminAddingItemNewMessages(int itemId, String message, String movement);

    /**
     *  Method to update all admin User's FreezeNeMessages.
     * @param userAccount corresponding user account
     * @param message corresponding message
     * @param movement "add" or "remove"
     */
    void updateAllAdminFreezeNewMessages(String userAccount, String message,String movement);

    /**
     * Method to update all admin Users' unfreezeNewMessages.
     * @param userAccount corresponding user
     * @param message corresponding message
     * @param movement "add" or "remove"
     */
    void updateAllAdminUnfreezeNewMessages(String userAccount, String message, String movement);

    /**
     * Method to update all admin Users' requestedItems.
     * @param itemInterface corresponding item
     * @param movement "add" or "remove"
     */
    void updateAllAdminRequestedItems(ItemInterface itemInterface, String movement);

    /**
     * Method to update all admin Users' addingItemRequestUsers.
     * @param userAccount corresponding item
     * @param movement "add" or "remove"
     */
    void updateAllAdminAddingItemRequestUsers(String userAccount, String movement);
}
