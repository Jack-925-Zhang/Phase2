package Controller;

import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.UserManager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * navigates the methods from IUserManager
 * @author Hongyu Chen
 * @version 1.8
 */
public class UserManagerGUINavigator {

    private final IUserManager userManager;
    private static final UserManagerGUINavigator userManagerGUINavigator = new UserManagerGUINavigator();

    /**
     * the constructor for class UserManagerGUINavigator
     */
    public UserManagerGUINavigator() {
        userManager =  UserManager.getInstance();
    }

    /**
     * gets the attribute USER_MANAGER_GUI_NAVIGATOR
     * @return USER_MANAGER_GUI_NAVIGATOR
     */
    public static UserManagerGUINavigator getInstance(){
        return userManagerGUINavigator;
    }

    /**
     * This method navigates the method of one user requesting to add an
     * item from the class UserManager. When a user needs to request to
     * add an item, the class MyItemsMenu calls this method.
     *
     * @param user The user who requests to add an item.
     * @param item The item which user requests to add.
     */
    public void requestToAddItemNavigator(IUserAccount user, ItemInterface item) {
        userManager.requestToAddItem(user, item);

    }

    /**
     * This method navigates the method of one user requesting to unfreeze.
     * @param user The user who requests to unfreeze.
     */
    public void requestToUnfreezeNavigator(IUserAccount user) {
        userManager.requestToUnfreeze(user);

    }

    /**
     * This method navigates the method of getting the List of NormalUser
     * stored in the class UserManager from the class UserManager. When the
     * List of NormalUser stored in the class UserManager is needed, the
     * class BorrowItemsMenu, MyAccountMenu, or ManageUserMenu calls this method.
     *
     * @return The Map with the key to be user id (String) and
     * value to be IUserAccount stored in the class UserManager.
     */
    public HashMap<String, IUserAccount> getNormalUsersNavigator() {
        return userManager.getNormalUsers();
    }

    /**
     * This method navigates the method of getting the List of AdminUser
     * stored in the class UserManager from the class UserManager. When the
     * List of AdminUser stored in the class UserManager is needed, the
     * class BorrowItemsMenu, MyAccountMenu, or ManageUserMenu calls this method.
     *
     * @return The Map with the key to be user id (String) and
     * value to be IUserAccount stored in the class UserManager.
     */
    public HashMap<String, IUserAccount> getAdminUsersNavigator() {
        return userManager.getAdminUsers();
    }

    /**
     * This method navigates the findFrequentTradingPartners method from the UserManager class.
     * This will find the top three most frequent trading partners of the user, and this will
     * be used in the MyTradesMenu when the user requests to view his/her most frequent trading partners.
     * @param userAccount the user who requests to find the top three most frequent trading partners
     */
    public void findFrequentTradingPartnersNavigator(IUserAccount userAccount){
        userManager.findFrequentTradingPartners(userAccount);
    }

    /**
     * This method navigates the method of finding the item in the user's item list
     * @param itemName name of the item
     * @param itemList list of items
     * @return a list of item if the item is found
     */
    public List<ItemInterface> getItemNavigator(String itemName, List<ItemInterface> itemList){
        return userManager.findItem(itemName, itemList);
    }

    /**
     * navigates the method getting user by userId
     * @param userId the id of the user needed
     * @return the user needed
     */
    public IUserAccount getUserByIdNavigator(String userId) {
        return userManager.getUserById(userId);
    }

    /**
     * navigates the methods setting user's vacation timer
     * @param user the user whose vacation timer needs to be set
     * @param startDate the start date of user's vacation
     * @param endDate the and date of user's vacation
     */
    public void setUserVacationTimerNavigator(IUserAccount user, Date startDate, Date endDate) {
        userManager.setUserVacationTimer(user, startDate, endDate);
    }

    /**
     * navigates the method changing user's password
     * @param user the user who wants to change password
     * @param password new password
     */
    public void changeUserPasswordNavigator(IUserAccount user, String password){
        userManager.changeUserPassword(user, password);
    }

    /**
     * navigates the method getting the initial administrative user
     * @return the initial administrative user
     */
    public IUserAccount getInitialAdminNavigator() {
        return userManager.getInitialAdmin();
    }

}
