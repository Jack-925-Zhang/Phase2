package Controller;

import GateWay.*;
import Model.*;
import UseCases.IOpManager;
import UseCases.OpManager;
import UseCases.UserUseCase.IGuestOperation;
import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.UserManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * navigates the methods from IUserOption
 * @author Hongyu Chen
 * @version 1.8
 */
public class UserOpGUINavigator {

    private static final UserOpGUINavigator userOpGUINavigator = new UserOpGUINavigator();
    private final IOpManager opManager = OpManager.getInstance();

    /**
     * the constructor for class UserOpGUINavigator
     */
    public UserOpGUINavigator() {

    }

    /**
     * gets the attribute USER_OP_GUI_NAVIGATOR
     * @return USER_OP_GUI_NAVIGATOR
     */
    public static UserOpGUINavigator getInstance(){
        return userOpGUINavigator;
    }

    /**
     * This method navigates the method of getting the List of current lending
     * ItemInterface of one user from the class IUserAccount. When the List of
     * current lending ItemInterface of one user is needed, the class BorrowItemsMenu,
     * or MyItemsMenu calls this method.
     *
     * @param user the user whose current lending is needed
     * @return The List of userAccount's current lending ItemInterface store in userAccount.
     */
    public List<ItemInterface> getCurrentLendingNavigator(IUserAccount user) {
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        return userOperation.getCurrentLending();
    }

    /**
     * This method navigates the method of getting the List of ItemInterface
     * which one user would like to lend out of this user from the class IUserAccount.
     * When the List of ItemInterface which one user would like to lend out of this
     * user is needed, the class LendItemsMenu, MyItemsMenu, or MyTradesMenu calls this method.
     *
     * @param user the user whose wish to lend is needed
     * @return The List of userAccount's ItemInterface which userAccount would like to lend out stored in userAccount.
     */
    public List<ItemInterface> getWishToLendNavigator(IUserAccount user) {
        if (!user.getStatus().equals("Guest")) {
            IUserOperation userOperation = opManager.getProperOperationByUser(user);
            return userOperation.getWishToLend();
        } else {
            List<ItemInterface> itemInterfaceList = new ArrayList<>();
            if(opManager.getProperOperationByGuest(user) != null){
                IGuestOperation guestOperation = opManager.getProperOperationByGuest(user);
                itemInterfaceList.addAll(guestOperation.getWishToLend());
            }
            return itemInterfaceList;
        }
    }

    /**
     * This method navigates the method of getting the List of ItemInterface
     * which one user would like to borrow from other users.
     * This method is navigated from the class IUserAccount.
     * When the List of ItemInterface which one user would like to refer back to the WishToBorrow list or
     * remove items from it, the class MyItemsMenu calls this method.
     *
     * @param user the user whose wish to borrow is needed
     * @return The List of userAccount's ItemInterface which userAccount would like to borrow stored in userAccount.
     */
    public List<ItemInterface> getWishToBorrowNavigator(IUserAccount user) {
        List<ItemInterface> itemInterfaceList = new ArrayList<>();
        if (!user.getStatus().equals("Guest")) {
            IUserOperation userOperation = opManager.getProperOperationByUser(user);
            itemInterfaceList.addAll(userOperation.getWishToBorrow());
        } else {
            if(opManager.getProperOperationByGuest(user) != null){
            IGuestOperation guestOperation = opManager.getProperOperationByGuest(user);
                itemInterfaceList.addAll(guestOperation.getWishToBorrow());
            }
        }
        return itemInterfaceList;
    }

    /**
     * This method navigates the getter of threePartners from the IUserAccount interface.
     * This method gets the top three most frequent trading partners upon the user request
     * @param user the user whose three frequent partners are needed
     * @return the Map of the top three most frequent trading partners the String variable is the user id
     *         the Integer variable is the number of transactions
     */
    public Map<String, Integer> getThreePartnersNavigator(IUserAccount user) {
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        return userOperation.getThreePartners();
    }

    /**
     * This method navigates the getter of threeItems from the IUserAccount interface.
     * This method gets the three most recent trading items from the user
     * @param user user whose three items are needed
     * @return the List of the three recent trading items
     */
    public List<ItemInterface> getThreeItemsNavigator(IUserAccount user) {
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        return userOperation.getThreeItems();
    }

    /**
     * This method navigates the method of getting the List of current borrowing
     * ItemInterface of one user from the class IUserAccount. When the List of
     * current borrowing ItemInterface of one user is needed, the class MyItemsMenu calls
     * this method.
     * @param user the user whose current borrowing is needed
     * @return The List of userAccount's current borrowing ItemInterface store in userAccount.
     */
    public List<ItemInterface> getCurrentBorrowingNavigator(IUserAccount user) {
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        return userOperation.getCurrentBorrowing();
    }

    /**
     * This method navigates the method getting notifications from this user.
     * @param user the user whose notifications is needed
     * @return notifications received by userAccount
     */
    public List<String> getNotificationsNavigator(IUserAccount user) {
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        return userOperation.getNotifications();
    }

    /**
     * This method navigates the method getting inviteTransNewMessages from this user.
     * @param user the user whose invite trans new messages is needed
     * @return inviteTransNewMessages received by userAccount
     */
    public HashMap<Integer, String> getInviteTransNewMessagesNavigator(IUserAccount user) {
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        return userOperation.getInviteTransNewMessages();
    }

    /**
     * navigates the getter for confirmedTransaction List
     * @param user gets user's confirmed trans
     * @return user's confirmedTransaction List
     */
    public List<Integer> getConfirmedTransactionNavigator(IUserAccount user) {
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        return userOperation.getConfirmedTransaction();
    }

    /**
     * navigates the getter for invitedTrans List
     * @param user gets user's invited trans
     * @return user's invitedTrans List
     */
    public List<Integer> getInvitedTransNavigator(IUserAccount user) {
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        return userOperation.getInvitedTrans();
    }

}
