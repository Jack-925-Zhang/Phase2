package UseCases;
import Model.*;
import UseCases.UserUseCase.IUserManager;

import java.util.HashMap;
import java.util.List;

/**
 * This class, TradingSystemInterface, is the interface for the TradingSystem,
 * gives the needed method related to login, register, general inventory and notifying
 * AdminUser about the user could be freeze.
 *
 * @author Yupeng Zhang
 * @version 1.8
 */
public interface TradingSystemInterface {

    /**
     * This method provides the register method for user, it uses a scanner to take
     * in the information needed, such as id, password, etc. When the user first time
     * uses this application, they would be ask to register, and by using this method
     * the first user register in this application would be automatically become the initial
     * admin user, otherwise it would be added to be the normal user.
     * This method would reject the same id user to be registered.
     * When the user is successfully registered, this method would print out the message to
     * remind user.
     *
     * @return boolean true if registered successfully
     */
    boolean register(String id, String password, String homeCity) throws Exception;

    /**
     * This method provides the login method for normal user, it takes in UserId and
     * Password by Scanner. After the users is successfully login the user will be connected
     * to the menu on UserInterface.
     * The method would print out the instruction to remind at which time what information the users
     * should put in, and after the users have successfully login, it also would remind the user that the
     * users have login.
     *
     * @return the integer in the HashMap represent the login status
     * 0 - login successfully;
     * 1 - login fail due to wrong user_id/password;
     * 2 - login fail due to frozen account
     * the value of the HashMap will return the user if log in is successful
     */
    HashMap<Integer, IUserAccount> normalUserLogin(String username, String password);

    /**
     * This method provides the login method for admin user, it takes in UserId and
     * Password by Scanner. After the users is successfully login the user will be connected
     * to the menu on UserInterface.
     * The method would print out the instruction to remind at which time what information the users
     * should put in, and after the users have successfully login, it also would remind the user that the
     * users have login.
     *
     */
    HashMap<Integer, IUserAccount> adminUserLogin(String username, String password);

    /**
     * this method provides the login method for GuestUser. It will create a random account for
     * Guest user.
     */
    HashMap<Integer, IUserAccount> guestUserLogin();

    /**
     * This method provides exit method for the Main method,
     * before it is exit it would write all information in buffer into the
     * database by cooperate with IFile.
     */
    void exit() throws Exception;
    /**
     * This method provides initial to update the normal user to become a admin user.
     *
     * @param user The user that is going to be updated.
     * @return
     */
    AdminUser updateAccount(IUserAccount user) throws Exception;
    /**
     * This method provides initial admin user, and the System the ability to update the
     * normal user to the admin user, it also would delete the user from the normalUsers when
     * they have been update to the admin user and add into adminUsers.
     *
     * @param user The user that is going to be update to admin User from normal User.
     */
    void updateInventory(IUserAccount user) throws Exception;
    /**
     * This method provides admin user a remind of which user is qualified to be frozen, when
     * the admin user is login, the system will automatically check and flag the user could be
     * frozen and then send out a string to remind the admin user to check it.
     */
    void flag();
    /**
     * This method is a getter for flagged.
     *
     * @return flagged
     */
    List<IUserAccount> getFlagged();
    /**
     * This method would notify a random admin user if flagged is not empty.
     * if the flagged is not empty, then it would choose a random admin user and notify him/her
     * by adding a new message into their account, the new message will pop out when they login.
     */
    void notifyAdminUserFreezing(IUserAccount user);
    /**
     * This method is a getter for item contains in GeneralInventory by item id.
     *
     * @param Id Item's id as the key for the GeneralInventory
     * @return ItemInterface Item
     */
    ItemInterface getItemById(int Id);
    /**
     * This method clears the flagged list.
     *
     * @param user
     */
    void removeFlagged(IUserAccount user);


    /**
     * gets general inventory
     * @return general inventory
     */
    HashMap<Integer, ItemInterface> getGeneralInventory();

    /**
     * sets user manager
     * @param userManager user manager
     */
    void setUserManager(IUserManager userManager);

    /**
     * gets user manager
     * @return user manager
     */
    IUserManager getUserManager();

    void setGeneralInventory(HashMap<Integer, ItemInterface> generalInventory);
}

