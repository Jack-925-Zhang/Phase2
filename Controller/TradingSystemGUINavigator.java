package Controller;

import GateWay.*;
import Model.*;
import UseCases.IOpManager;
import UseCases.MeetingSystemUseCase.IMeetingSystem;
import UseCases.MeetingSystemUseCase.MeetingSystem;
import UseCases.OpManager;
import UseCases.TradingSystem;
import UseCases.TradingSystemInterface;
import UseCases.UserUseCase.IAdminOperation;
import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.UserManager;

import java.util.HashMap;
import java.util.List;

/**
 * navigates the methods from TradingSystemInterface
 * @author Hongyu Chen
 * @version 1.8
 */
public class TradingSystemGUINavigator {

    private final TradingSystemInterface tradingSystem;
    private static final TradingSystemGUINavigator tradingSystemGUINavigator = new TradingSystemGUINavigator();
    private final IMeetingSystem meetingSystem ;
    private final DataDriver dataDriver;
    private final IDataUpdater dataUpdater;
    private final IDataDeleter dataDeleter;
    private final IDataInsertion dataInsertion;
    private final IUserManager userManager;
    private final IOpManager opManager;

    /**
     * the constructor for class TradingSystemGUINavigator
     */
    public TradingSystemGUINavigator() {
        tradingSystem = TradingSystem.getInstance();
        meetingSystem = MeetingSystem.getInstance();
        dataDriver = DataDriver.getInstance();
        dataUpdater = new DataUpdater();
        dataDeleter = new DataDeleter();
        dataInsertion = new DataInsertion();
        userManager = UserManager.getInstance();
        opManager = OpManager.getInstance();

    }

    /**
     * gets the attribute TRADING_SYSTEM_GUI_NAVIGATOR
     * @return TRADING_SYSTEM_GUI_NAVIGATOR
     */
    public static TradingSystemGUINavigator getInstance(){
        return tradingSystemGUINavigator;
    }

    /**
     * This method navigates the method getting flagged user list
     * @return flagged user list
     */
    public List<IUserAccount> getFlaggedNavigator() {
        return tradingSystem.getFlagged();
    }

    /**
     * navigates the method removing the flagged user
     * @param user the user removed
     */
    public void removeFlagged(IUserAccount user){
        tradingSystem.removeFlagged(user);
    }

    /**
     * navigates the method getting item by itemId
     * @param itemId the id of the item needed
     * @return the item needed
     */
    public ItemInterface getItemByIdNavigator(int itemId) {
        return tradingSystem.getItemById(itemId);
    }

    /**
     * navigates the getter for general inventory
     * @return general inventory
     */
    public HashMap<Integer, ItemInterface> getInventoryNavigator() {
        return tradingSystem.getGeneralInventory();
    }

    /**
     * This method provides exit method for the Main method,
     * before it is exit it would write all information in buffer into the
     * database by cooperate with IFile.
     */

    public void exitNavigator() throws Exception {
        tradingSystem.exit();
        HashMap<String,IUserAccount> users = userManager.getAdminUsers();
        users.putAll(userManager.getNormalUsers());
        dataUpdater.allUserUpdater(users.values());
        dataUpdater.allItemsUpdater(tradingSystem.getGeneralInventory().values());
        dataUpdater.allTransactionUpdater(meetingSystem.getAllTransactions().values());
        System.exit(0);
        dataDriver.closeConnectionSource();
    }

    /**
     * navigates the method notify adminUser to freezing user account.
     * @param user the user who needed to be frozen
     */

    public void notifyAdminUserFreezingNavigator(IUserAccount user){
        tradingSystem.notifyAdminUserFreezing(user);
    }

    /**
     * This method provides the login method for normal user, it takes in UserId and
     * Password by Scanner. After the users is successfully login the user will be connected
     * to the menu on UserInterface.
     * The method would print out the instruction to remind at which time what information the users
     * should put in, and after the users have successfully login, it also would remind the user that the
     * users have login.
     *
     * @param username user id
     * @param password user password
     * @return the integer in the HashMap represent the login status
     * 0 - login successfully;
     * 1 - login fail due to wrong user_id/password;
     * 2 - login fail due to frozen account
     * the value of the HashMap will return the user's log in status.
     */
    public HashMap<Integer, IUserAccount> normalUserLoginNavigator(String username, String password){
        return tradingSystem.normalUserLogin(username, password);
    }

    /**
     * This method provides the login method for admin user, it takes in UserId and
     * Password by Scanner. After the users is successfully login the user will be connected
     * to the menu on UserInterface.
     * The method would print out the instruction to remind at which time what information the users
     * should put in, and after the users have successfully login, it also would remind the user that the
     * users have login.
     *
     */
    public HashMap<Integer, IUserAccount> adminUserLoginNavigator(String username, String password){
        return tradingSystem.adminUserLogin(username, password);
    }

    /**
     * User can choose to login as a guest user without register
     * Guest user can view various parts of the application without being able to trade or communicate with the administrative user.
     */
    public HashMap<Integer, IUserAccount> guestUserLoginNavigator(){
        return tradingSystem.guestUserLogin();
    }

    public TradingSystemInterface getTradingSystemNavigator(){
        return tradingSystem;
    }

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
     * @return whether this use is able to register
     */
    public boolean registerNavigator(String id, String password, String homeCity) throws Exception{
        boolean answer = tradingSystem.register(id, password, homeCity);
        IUserAccount user = userManager.getUserById(id);
        IUserOperation operation = opManager.getProperOperationByUser(user);
        if (user.getStatus().equals("Admin")) {
            // insert adminUser and adminUserOperation
            dataInsertion.adminUserInsertion(user);
            dataInsertion.adminOperationInsertion((IAdminOperation) operation);

            // update adminUser and adminUserOperation
            dataUpdater.adminUserUpdater(user);
        }
        else{
            dataInsertion.normalUserInsertion(user);
            dataInsertion.userOperationInsertion(operation);
            dataUpdater.normalUserUpdater(user);
        }
        return answer;
    }

    /**
     * This method provides initial to update the normal user to become a admin user.
     *
     * @param user The user that is going to be updated.
     */
    public void updateAccountNavigator(IUserAccount user) throws Exception{
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        dataDeleter.normalUserDeleter((NormalUser) user);
        dataDeleter.operationDeleter(userOperation);
        IUserAccount AdminUser = tradingSystem.updateAccount(user);
        dataInsertion.adminUserInsertion(AdminUser);
        IAdminOperation adminOperation = (IAdminOperation) opManager.getProperOperationByUser(AdminUser);
        dataInsertion.adminOperationInsertion(adminOperation);
    }

}
