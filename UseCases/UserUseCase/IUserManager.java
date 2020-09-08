package UseCases.UserUseCase;

import Model.*;

import java.util.*;

/**
 * IUserManager is the interface for UserManager
 * @author BINGQING WAN
 * @version 1.8
 */

public interface IUserManager {

    IUserManager userManager = new UserManager() {
    };

    /**
     * gets instance userManager
     * @return userManager
     */
    static IUserManager getInstance(){
        return userManager;
    }

    /**
     * get a collection of normal users
     * @return HashMap with the key to be user id (String) and value to be NormalUser
     */
    HashMap<String, IUserAccount> getNormalUsers();

    /**
     * get a collection of admin users
     * @return HashMap with the key to be user id (String) and value to be AdminUser
     *
     */
    HashMap<String, IUserAccount> getAdminUsers();

    /**
     * get a collection of guest users
     * @return HashMap with the key to be user id (String) and value to be GuestUser
     *
     */
    HashMap<String, IUserAccount> getGuestUsers();

    /**
     * get the adding item request from the user
     * @return a list that stores the user and the item requested to add
     */
    List<Object> getAddingRequest();

    /**
     * get the user who requests to unfreeze account
     * @return the NormalUser who requests to unfreeze account
     */
    IUserAccount getUnfreezeRequester();

    /**
     * add an user to his/her corresponding user list
     * @param userId the id of the user
     * @param userPassword the password of the user
     * @param userStatus the status of the user (normal or administrative)
     * @param homeCity the home city of the user
     */
    void addUser(String userId, String userPassword, String userStatus, String homeCity, boolean isInitialAdmin);

    /**
     * method to find user instance by userId.
     * @param userId the id that we want to search
     * @return the User instance
     */
    IUserAccount getUserById(String userId);


    /**
     * Remove this user from normalUsers list.
     * @param user the user which is needed to be removed from normalUsers list.
     */
    void removeUser(IUserAccount user);

    /**
     * This method takes the item name of String type to finds the item in the item list of the
     * ItemInterface type
     * @param itemName name of the item in String type
     * @param itemList list of items in ItemInterface type
     * @return list of item if the item is found in the list
     */
    List<ItemInterface> findItem(String itemName, List<ItemInterface> itemList);

    /**
     * This method finds the HashMap, which stores the id of the user's trading partners as String and
     * their number of completed tradings as Integer. The HashMap is sorted from the least frequent
     * trading partners to most frequent. The top three frequent trading partners are stored to the
     * frequentPartners variable.
     * @param user the user who requests to view his/her top three frequent trading partners
     */
    void findFrequentTradingPartners(IUserAccount user);

    /**
     * This method sets the adding item request from a normal user
     * @param user the normal user who requests to add an item to his/her wishToBorrow list
     * @param item the item to be added
     */
    void requestToAddItem(IUserAccount user, ItemInterface item);

    /**
     * This method sets the user who requests to get unfreeze
     * @param user the normal user who requests to unfreeze his/her account
     */
    void requestToUnfreeze(IUserAccount user);

    /**
     * This method converts the user's information to String
     * @return a list of users' IDs and statuses, separated by commas
     */
    String toString();

    /**
     * This method changes the admin user to initial admin user if this user is the first
     * user to the system
     * @param user the first user to the system
     */
    void changeToInitialAdmin(IUserAccount user);

    /**
     * changes user's password
     * @param user user who changes password
     * @param password new password
     */
    void changeUserPassword(IUserAccount user, String password);

    /**
     * adds user to related list
     * @param user the user whose related list is needed
     */
    void addUserToRelatedList(IUserAccount user);

    /**
     * sets user's vacation timer
     * @param user the user whose vacation timer is set
     * @param startDate start date of user's vacation
     * @param endDate end date of user's vacation
     */
    void setUserVacationTimer(IUserAccount user, Date startDate, Date endDate);

    /**
     * gets the initial administrative user
     * @return the initial administrative user
     */
    IUserAccount getInitialAdmin();
}
