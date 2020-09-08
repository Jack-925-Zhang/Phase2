package UseCases;

import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.UserUseCase.*;

import java.util.HashMap;
import java.util.Map;

/**
 * this class is used to Manager all operations that corresponding to the user.
 * @author Qian Tang
 * @version 1.8
 */
public class OpManager implements IOpManager {

    private Map<String, IUserOperation> allUserOperations;
    private Map<String, IAdminOperation> allAdminOperations;
    private Map<String, IGuestOperation> allGuestOperations;

    private static final OpManager opManager = new OpManager();

    /**
     * gets instance opManager
     * @return opManager
     */
    public  static IOpManager getInstance(){return opManager;}

    /**
     * the constructor for class OPManager
     */
    public OpManager(){
        allUserOperations = new HashMap<>();
        allAdminOperations = new HashMap<>();
        allGuestOperations = new HashMap<>();
    }

    /**
     * method to get all user operation map
     * @return UserOperation Map
     */
    @Override
    public Map<String, IUserOperation> getAllUserOperations() {
        return allUserOperations;
    }

    /**
     * set up new user operation map
     * @param allUserOperations new user operation map
     */
    @Override
    public void setAllUserOperations(Map<String, IUserOperation> allUserOperations) {
        this.allUserOperations = allUserOperations;
    }

    /**
     *  method to get all admin user operation map
     * @return all admin user operation map
     */
    @Override
    public Map<String, IAdminOperation> getAllAdminOperations() {
        return allAdminOperations;
    }

    /**
     * set up new admin user operation map
     * @param allAdminOperations new admin user operation map
     */
    @Override
    public void setAllAdminOperations(Map<String, IAdminOperation> allAdminOperations) {
        this.allAdminOperations = allAdminOperations;
    }

    /**
     *  method to get all admin user operation map
     * @return all admin user operation map
     */
    @Override
    public Map<String, IGuestOperation> getAllGuestOperations() {
        return allGuestOperations;
    }

    /**
     * set up new admin user operation map
     * @param allGuestOperations new admin user operation map
     */
    @Override
    public void setAllGuestOperations(Map<String, IGuestOperation> allGuestOperations) {
        this.allGuestOperations = allGuestOperations;
    }

//    /**
//     * method to get all user Transactions operations.
//     * @return all user Transactions operations.
//     */
//    @Override
//    public Map<String, IUserTransactionOperation> getAllUserTransactionOperation() {
//        return allUserTransactionOperation;
//    }
//
//    /**
//     * method to setup user Transactions Operations
//     * @param allUserTransactionOperation
//     */
//    @Override
//    public void setAllUserTransactionOperation(Map<String, IUserTransactionOperation> allUserTransactionOperation) {
//        this.allUserTransactionOperation = allUserTransactionOperation;
//    }

    /**
     * method to create UserOperation and it to corresponding hashmap
     * @param user the user corresponding to the operation
     * @return new operation
     */
    @Override
    public IUserOperation CreateUserOperation(IUserAccount user){
        IUserOperation newOperation = new UserOperation(user.getUserId());
        allUserOperations.put(user.getUserId(),newOperation);
        return newOperation;
    }

    /**
     * method to create UserOperation and it to corresponding hashmap
     * @param user the user corresponding to the operation
     * @return new operation
     */
    @Override
    public IAdminOperation CreateAdminOperation(IUserAccount user){
        IAdminOperation newOperation = new AdminOperation(user);
        allAdminOperations.put(user.getUserId(), newOperation);
        return  newOperation;
    }

    /**
     * method to create GuestOperation and it to corresponding hashmap
     * @param user the user corresponding to the operation
     * @return new operation
     */
    @Override
    public IGuestOperation CreateGuestOperation(IUserAccount user) {
        IGuestOperation newOperation = new GuestOperation();
        allGuestOperations.put(user.getUserId(), newOperation);
        return newOperation;
    }

//    /**
//     * method to created IUserTransactionOperation and  add it to corresponding hashmap
//     * @param user the user corresponding to this operation
//     * @return new transactionOperation
//     */
//    @Override
//    public IUserTransactionOperation CreateUserTransactionOperation(IUserAccount user){
//        IUserTransactionOperation newOperation = new UserTransactionOperation(user);
//        allUserTransactionOperation.put(user.getUserId(), newOperation);
//        return newOperation;
//
//    }
    /**
     * method to get normal user operation using id.
     *
     * @param userId the id we want
     * @return the normal operation we want
     */
    @Override
    public IUserOperation getUserOperationById(String userId) {
        return this.allUserOperations.get(userId);
    }

    /**
     * method to get normal admin user operation by id.
     *
     * @param userId the id we want
     * @return the admin operation we want
     */
    @Override
    public IAdminOperation getAdminOperationById(String userId) {
        return this.allAdminOperations.get(userId);
    }

    /**
     * method to get guest user operation by id.
     * @param guestId the guest id we want
     * @return the guest operation we want
     */
    @Override
    public IGuestOperation getGuestOperationById(String guestId) {
        return this.allGuestOperations.get(guestId);
    }

    /**
     * Method to get a proper operation for user account without knowing it is a admin or normal
     * @param userAccount the user we need
     * @return operation we want
     */
    @Override
    public IUserOperation getProperOperationByUser(IUserAccount userAccount){
        if (userAccount.getStatus().equals("Normal")) {
            return this.getUserOperationById(userAccount.getUserId());
        } else {
            return this.getAdminOperationById(userAccount.getUserId());
        }
    }

    /**
     * method to get a Guest operation, since it is different than AdminOperation and UserOperation.
     * @param guestAccount the account that we want to find for guest user.
     * @return corresponding Operation for this user.
     */
    @Override
    public IGuestOperation getProperOperationByGuest(IUserAccount guestAccount) {
        return this.getGuestOperationById(guestAccount.getUserId());
    }

    /**
     * method to update all admin Users UnfrozenRequestUsers list.
     * @param userAccount corresponding user
     * @param movement "add" or "remove"
     */
    @Override
    public void updateAllAdminUnfrozenRequestUsers(String userAccount, String movement){
        for ( String adminId: this.allAdminOperations.keySet()){
            if (movement.toLowerCase().equals("add")){
                allAdminOperations.get(adminId).getUnfrozenRequestUsers().add(userAccount);
            }else {
                allAdminOperations.get(adminId).getUnfrozenRequestUsers().remove(userAccount);
            }
        }
    }

    /**
     * Method to update all admin User's addingItemNewMessages.
     * @param itemId corresponding item
     * @param message corresponding message
     * @param movement "add" or "remove"
     */
    @Override
    public void updateAllAdminAddingItemNewMessages(int itemId, String message, String movement){
        for ( String adminId: this.allAdminOperations.keySet()){
            if (movement.toLowerCase().equals("add")){
                allAdminOperations.get(adminId).getAddingItemNewMessages().put(itemId,message);
            }else {
                allAdminOperations.get(adminId).getAddingItemNewMessages().remove(itemId);
            }
        }
    }

    /**
     *  Method to update all admin User's FreezeNeMessages.
     * @param userAccount corresponding user account
     * @param message corresponding message
     * @param movement "add" or "remove"
     */
    @Override
    public void updateAllAdminFreezeNewMessages(String userAccount, String message,String movement){
        for ( String adminId: this.allAdminOperations.keySet()){
            if (movement.toLowerCase().equals("add")){
                allAdminOperations.get(adminId).getFreezeNewMessages().put(userAccount,message);
            }else {
                allAdminOperations.get(adminId).getFreezeNewMessages().remove(userAccount);
            }
        }
    }

    /**
     * Method to update all admin Users' unfreezeNewMessages.
     * @param userAccount corresponding user
     * @param message corresponding message
     * @param movement "add" or "remove"
     */
    @Override
    public void updateAllAdminUnfreezeNewMessages(String userAccount, String message, String movement){
        for ( String adminId: this.allAdminOperations.keySet()){
            if (movement.toLowerCase().equals("add")){
                allAdminOperations.get(adminId).getUnfreezeNewMessages().put(userAccount, message);
            }else {
                allAdminOperations.get(adminId).getUnfreezeNewMessages().remove(userAccount);
            }
        }
    }

    /**
     * Method to update all admin Users' requestedItems.
     * @param itemInterface corresponding item
     * @param movement "add" or "remove"
     */
    @Override
    public void updateAllAdminRequestedItems(ItemInterface itemInterface, String movement){
        for ( String adminId: this.allAdminOperations.keySet()){
            if (movement.toLowerCase().equals("add")){
                allAdminOperations.get(adminId).getRequestedItems().add(itemInterface);
            }else {
                allAdminOperations.get(adminId).getRequestedItems().remove(itemInterface);
            }
        }
    }

    /**
     * Method to update all admin Users' addingItemRequestUsers.
     * @param userAccount corresponding item
     * @param movement "add" or "remove"
     */
    @Override
    public void updateAllAdminAddingItemRequestUsers(String userAccount, String movement){
        for ( String adminId: this.allAdminOperations.keySet()){
            if (movement.toLowerCase().equals("add")){
                allAdminOperations.get(adminId).getAddingItemRequestUsers().add(userAccount);
            }else {
                allAdminOperations.get(adminId).getAddingItemRequestUsers().remove(userAccount);
            }
        }
    }
}
