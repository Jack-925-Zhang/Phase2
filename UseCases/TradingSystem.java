package UseCases;
import Model.*;
import UseCases.MeetingSystemUseCase.IMeetingSystem;
import UseCases.MeetingSystemUseCase.MeetingSystem;
import UseCases.UserUseCase.*;

import java.util.*;

/**
 * This class, TradingSystem, gives the necessary methods to View.Main Class for User's login, register, modified their
 * wishToLend & wishToBorrow and transactions related details, and add their ratings of item to a list.
 * It lets the first user of this application become the initial admin user.
 * It provides method automatically flag a list of user qualified to be frozen,
 * also remind the admin user to check it.
 * It allows users to add/remove the items in wishToLend/wishToBorrow.
 * It allows users to send, edit, receive, confirm the transactions invitation.
 * It allows users to add/remove the notification from system, invited transaction and the messages.
 * It allows admin users to cancel the vacation status of normal users.
 * It allows users to add their ratings of items into a list.
 *
 * @author Yupeng Zhang
 * @version 1.8
 */
public class TradingSystem implements TradingSystemInterface {
    public HashMap<Integer, ItemInterface> generalInventory;
    public IUserManager userManager;
    public IUserAccount normalUser;
    public IUserAccount guestUser;
    public RecommendationInterface Recommendation;
    public List<IUserAccount> flagged ;
    public IMeetingSystem meetingSystem;
    private static final TradingSystem tradingSystem = new TradingSystem();
    private final IOpManager opManager;


    /**
     * This method is the default constructor of the TradingSystem.
     * Load configuration file
     *
     */
    public TradingSystem(){
        generalInventory = new HashMap<>();
        userManager = UserManager.getInstance();
        guestUser = new GuestUser();
        Recommendation = new Recommendation();
        flagged = new ArrayList<>();
        meetingSystem = MeetingSystem.getInstance();
        opManager = OpManager.getInstance();
    }


    /**
     * This method is providing get instance of trading system.
     * @return tradingSystem the instance of trading system.
     *
     */
    public static TradingSystem getInstance(){
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
     * @return
     */
    @Override
    public boolean register(String id, String password, String homeCity){
        IUserAccount user = null;
        HashMap<String, IUserAccount> normalUser = userManager.getNormalUsers();
        HashMap<String, IUserAccount> adminUser = userManager.getAdminUsers();
        while(true){
            if(adminUser.containsKey(id)||normalUser.containsKey(id)){
                return false;
            }

            else if(adminUser.size() == 0) {
                user = new AdminUser(id, null, "Admin",null, false);
                user.setPassword(password);
                user.setHomeCity(homeCity);
                user.setIsInitialAdmin(true);
                Recommendation.setUserCityMap(user.getHomeCity(), user);
                userManager.addUser(user.getUserId(), user.getPassword(), "Admin", user.getHomeCity(), user.getIsInitialAdmin());
                IAdminOperation adminOperation = opManager.CreateAdminOperation(user);
                String SameCityUserRecommend = Recommendation.SameCityUserRecommend(user.getHomeCity());
                System.out.print(SameCityUserRecommend);
                userManager.changeToInitialAdmin(user);
                return true;
            }

            else{
                user = new NormalUser(id, null, "Normal",null);
                user.setPassword(password);
                user.setHomeCity(homeCity);
                Recommendation.setUserCityMap(user.getHomeCity(), user);
                userManager.addUser(user.getUserId(), user.getPassword(), "Normal", user.getHomeCity(), false);
                IUserOperation userOperation = opManager.CreateUserOperation(user);
                String SameCityUserRecommend = Recommendation.SameCityUserRecommend(user.getHomeCity());
                System.out.print(SameCityUserRecommend);
                return true;
            }
        }
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
    @Override
    public HashMap<Integer, IUserAccount> normalUserLogin(String username, String password) {
        boolean login = false;
        HashMap<Integer, IUserAccount> user = new HashMap<>();
        HashMap<String, IUserAccount> normalUsers= userManager.getNormalUsers();
        Set<String> set = normalUsers.keySet();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            String User_id = iterator.next();
            IUserAccount normalUser = userManager.getUserById(User_id);
            if(normalUser.getUserId().equals(username) && normalUser.getPassword().equals(password)){
                boolean freeze = normalUser.getIsFreeze();
                if(freeze){
                    user.put(2, null);
                } else {
                    user.put(0, normalUser);
                }
            }
            else{
                user.put(1, null);
            }
        }
        return user;
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
    @Override
    public HashMap<Integer, IUserAccount> adminUserLogin(String username, String password) {
        boolean login = false;
        HashMap<Integer, IUserAccount> user = new HashMap<>();
        HashMap<String, IUserAccount> adminUsers = userManager.getAdminUsers();
        Set<String> set = adminUsers.keySet();
        Iterator<String> iterator = set.iterator();
        IUserAccount adminUser = null;
        while (iterator.hasNext()) {
            String User_id = iterator.next();
            adminUser = userManager.getUserById(User_id);
            if (adminUser.getUserId().equals(username) && adminUser.getPassword().equals(password)) {
                boolean freeze = adminUser.getIsFreeze();
                if(freeze){
                    user.put(2, null);
                } else {
                    user.put(0, adminUser);
                }
            } else {
                user.put(1, null);
            }
        }
        return user;
    }


    /**
     * User can choose to login as a guest user without register
     * Guest user can view various parts of the application without being able to trade or communicate with the administrative user.
     */
    @Override
    public HashMap<Integer, IUserAccount> guestUserLogin(){
        boolean login = true;
        HashMap<Integer, IUserAccount> user = new HashMap<>();
        user.put(0, guestUser);
        return user;
    }

    /**
     * This method provides exit method for the Main method,
     * before it is exit it would write all information in buffer into the
     * database by cooperate with IFile.
     */
    @Override
    public void exit(){
        System.exit(0);
    }
    /**
     * This method provides initial to update the normal user to become a admin user.
     *
     * @param user The user that is going to be updated.
     * @return
     */
    @Override
    public AdminUser updateAccount(IUserAccount user) throws Exception {
        userManager.removeUser(user);
        IUserOperation userOperation = opManager.getProperOperationByUser(user);
        AdminUser adminUser = new AdminUser(user.getUserId(), user.getPassword(), "Admin",user.getHomeCity(), false);
        adminUser.setOnVacation(user.getOnVacation());
        adminUser.setEndDateOfVacation(user.getEndDateOfVacation());
        adminUser.setStartDateOfVacation(user.getStartDateOfVacation());
        adminUser.setCredit(user.getCredit());
        adminUser.setCurrentTrans(user.getCurrentTrans());
        adminUser.setNumBorrowed(user.getNumBorrowed());
        adminUser.setNumLent(user.getNumLent());
        adminUser.setThreshold(user.getThreshold());
        adminUser.setTransactionLimit(user.getTransactionLimit());
        opManager.getAllUserOperations().remove(user.getUserId(), userOperation);
        userManager.addUser(adminUser.getUserId(), adminUser.getPassword(), "Admin",adminUser.getHomeCity(), false);
        IAdminOperation adminOperation = new AdminOperation(adminUser);
        opManager.getAllAdminOperations().put(adminOperation.getUserId(),adminOperation);
        System.out.println(userManager);
        System.out.println(userManager.getNormalUsers());
        System.out.println(userManager.getAdminUsers());
        return adminUser;
    }
    /**
     * This method provides user the ability to update the general inventory,
     * when the user has add new item into their inventory, the system would automatically check
     * and update it into the general inventory.
     *
     * @param user The user that is going to be update their inventory.
     */
    @Override
    public void updateInventory(IUserAccount user){
        List<ItemInterface> userInventory = opManager.getProperOperationByUser(normalUser).getWishToLend();
        Iterator<ItemInterface> iterator = userInventory.iterator();
        while(iterator.hasNext()){
            ItemInterface user_item = iterator.next();
            if(!(generalInventory.containsKey(user_item.getItemId()))){
                generalInventory.put(user_item.getItemId(), user_item);
            }
        }
    }

    /**
     * This method provides admin user a remind of which user is qualified to be frozen, when
     * the admin user is login, the system will automatically check and flag the user could be
     * frozen and then send out a string to remind the admin user to check it.
     */
    @Override
    public void flag() {
        //UserManager normal = new UserManager();
        HashMap<String, IUserAccount> normalUser = userManager.getNormalUsers();
        Set<String> set = normalUser.keySet();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()) {
            String User_id = iterator.next();
            IUserAccount NormalUser = userManager.getUserById(User_id);
            int diff = NormalUser.getNumLent() - NormalUser.getNumBorrowed();
            if(diff < NormalUser.getThreshold()){
                this.flagged.add(NormalUser);
            }
        }
    }
    /**
     * This method is a getter for flagged.
     *
     * @return flagged
     */
    @Override
    public List<IUserAccount> getFlagged() {
        flag();
        return this.flagged;
    }
    /**
     * This method would notify a random admin user if flagged is not empty.
     * if the flagged is not empty, then it would choose a random admin user and notify him/her
     * by adding a new message into their account, the new message will pop out when they login.
     */
    @Override
    public void notifyAdminUserFreezing(IUserAccount user) {
        if (!(flagged.isEmpty())) {
            for (IUserAccount flaggedUser : flagged) {
                ((IAdminOperation)opManager.getProperOperationByUser(user)).addFreezeNewMessages(user.getUserId(),
                        "Freezing Request: " + "System has sent the request for freezing" +
                                user.getUserId() + ". ");
            }
        }
    }
    /**
     * This method is a getter for item contains in GeneralInventory by item id.
     *
     * @param Id Item's id as the key for the GeneralInventory
     * @return ItemInterface Item
     */
    @Override
    public ItemInterface getItemById(int Id) {
        return generalInventory.get(Id);
    }
    /**
     * This method clears the flagged list.
     *
     * @param user
     */
    public void removeFlagged(IUserAccount user) {this.flagged.remove(user);}

    /**
     * gets general inventory
     * @return general inventory
     */
    @Override
    public HashMap<Integer, ItemInterface> getGeneralInventory() {
        return generalInventory;
    }

    public void setGeneralInventory(HashMap<Integer, ItemInterface> generalInventory) {
        this.generalInventory = generalInventory;
    }

    /**
     * sets user manager
     * @param userManager user manager
     */
    @Override
    public void setUserManager(IUserManager userManager){
        this.userManager = userManager;
    }

    /**
     * gets user manager
     * @return user manager
     */
    @Override
    public IUserManager getUserManager(){
        return this.userManager;
    }
}


