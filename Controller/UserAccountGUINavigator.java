package Controller;

import Model.IUserAccount;

/**
 * navigates the methods from IUserAccount
 * @author Hongyu Chen
 * @version 1.8
 */
public class UserAccountGUINavigator {

    private static final UserAccountGUINavigator userAccountGUINavigator = new UserAccountGUINavigator();

    /**
     * the constructor for class UserAccountGUINavigator
     */
    public UserAccountGUINavigator() {
    }

    /**
     * gets the attribute USER_ACCOUNT_GUI_NAVIGATOR
     * @return USER_ACCOUNT_GUI_NAVIGATOR
     */
    public static UserAccountGUINavigator getInstance(){
        return userAccountGUINavigator;
    }

    /**
     * This method navigates the method of getting the user id of one user
     * from the class IUserAccount. When the the user id of one user is needed,
     * the class BorrowItemsMenu, MyItemsMenu, or MyTradesMenu calls this method.
     *
     * @param userAccount The user whose user id is needed.
     * @return The user id of userAccount.
     */
    public String getUserIdNavigator(IUserAccount userAccount) {
        return userAccount.getUserId();
    }

    /**
     * This method navigates the method getting whether one user is initial administrative user or not
     * @param user the user as the IUserAccount type
     * @return boolean, whether this user is initial administrative user or not
     */
    public boolean getIsInitialAdminNavigator(IUserAccount user) {
        return user.getIsInitialAdmin();
    }

    /**
     * navigates the getter for user's status
     * @param user the user whose status is needed
     * @return user's status ("Normal" or "Admin")
     */
    public String getUserStatusNavigator(IUserAccount user) {
        return user.getStatus();
    }

}
