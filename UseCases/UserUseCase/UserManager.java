package UseCases.UserUseCase;

import Model.*;
import UseCases.*;

import java.text.ParseException;
import java.util.*;

/**
 * UserManager stores a collection of users (normal users and administrative users) and adds new accounts
 * to the corresponding array list.
 * removes items from wishToLend and/or wishToBorrow list upon the user's request
 * adds items to wishToBorrow list upon the user's request
 * sends adding item request to administrative user if the user wants to add an item to wishToLend list
 * sends unfreeze account request to administrative users
 * finds the top three most frequent trading partners
 * @author BINGQING WAN
 * @version 1.8
 */
public class UserManager implements IUserManager {

    private final HashMap<String, IUserAccount> normalUsers;
    private final HashMap<String, IUserAccount> adminUsers;
    private final HashMap<String, IUserAccount> guestUser;
    private final List<Object> addingRequest;
    private IUserAccount unfreezeRequester;
    private HashMap<String, Integer> tradingPartners;
    private static final UserManager userManager = new UserManager();
    private final IOpManager opManager;
    /**
     * the constructor of class UserManager
     */
    public UserManager() {
        normalUsers = new HashMap<>();
        adminUsers = new HashMap<>();
        guestUser = new HashMap<>();
        addingRequest = new ArrayList<>();
        tradingPartners = new HashMap<>();
        opManager = OpManager.getInstance();
    }

    /**
     * gets instance userManager
     * @return userManager
     */
    public static IUserManager getInstance(){
        return userManager;
    }

    /**
     * This method adds the user to his/her corresponding user HashMap
     * @param userId the id of the user
     * @param userPassword the password of the user
     * @param userStatus the status of the user (normal, administrative or guest)
     */
    @Override
    public void addUser(String userId, String userPassword, String userStatus, String homeCity, boolean isInitialAdmin) {
        switch (userStatus) {
            case "Normal":
                NormalUser m = new NormalUser(userId, userPassword, userStatus, homeCity);
                normalUsers.put(m.getUserId(), m);
                break;
            case "Admin":
                AdminUser a = new AdminUser(userId, userPassword, userStatus, homeCity, isInitialAdmin);
                adminUsers.put(a.getUserId(), a);
                break;
            case "Guest":
                GuestUser g = new GuestUser();
                guestUser.put(g.getUserId(), g);
                break;
        }
    }

    /**
     * This method takes the item name of String type to finds the item in the item list of the
     * ItemInterface type
     * @param itemName name of the item in String type
     * @param itemList list of items in ItemInterface type
     * @return list of item if the item is found in the list
     */
    @Override
    public List<ItemInterface> findItem(String itemName, List<ItemInterface> itemList){
        List<ItemInterface> itemFound = new ArrayList<>();
        for (ItemInterface items: itemList){
            if (items.getItemName().equals(itemName)){
                itemFound.add(items);
            }
        }
        return itemFound;
    }

    /**
     * This method finds the HashMap, which stores the id of the user's trading partners as String and
     * their number of completed tradings as Integer. The HashMap is sorted from the least frequent
     * trading partners to most frequent. The top three frequent trading partners are stored to the
     * frequentPartners variable.
     * @param user the user who requests to view his/her top three frequent trading partners
     */
    @Override
    public void findFrequentTradingPartners(IUserAccount user) {
        IUserOperation m = opManager.getProperOperationByUser(user);
        HashMap<String, Integer> partners = m.getTradingPartners();
        if (partners.size() != 0) {
            tradingPartners = partners;
            LinkedList<Map.Entry<String, Integer>> list = new LinkedList<>(tradingPartners.entrySet());
            list.sort(Map.Entry.comparingByValue());

            HashMap<String, Integer> frequentPartners = new HashMap<>();

            if (partners.size()>=3) {
                for (int i = 0; i < 3; i++) {
                    Map.Entry<String, Integer> last = list.getLast();
                    frequentPartners.put(last.getKey(), last.getValue());
                    list.removeLast();
                }
            } else {
                for (int i = 0; i < partners.size(); i++) {
                    Map.Entry<String, Integer> last = list.getLast();
                    frequentPartners.put(last.getKey(), last.getValue());
                    list.removeLast();
                }
            }
            if (user.getStatus().equals("Normal")){
                IUserAccount n = normalUsers.get(user.getUserId());

                opManager.getProperOperationByUser(n).setThreePartners(frequentPartners);
            } else if (user.getStatus().equals("Admin")){
                IUserAccount a = adminUsers.get(user.getUserId());
                opManager.getProperOperationByUser(a).setThreePartners(frequentPartners);
            }
        }else {
            m.setThreePartners(new HashMap<String, Integer>());
        }
    }

    /**
     * This method sets the adding item request from a normal user
     * @param user the normal user who requests to add an item to his/her wishToBorrow list
     * @param item the item to be added
     */
    @Override
    public void requestToAddItem(IUserAccount user, ItemInterface item) {
        addingRequest.add(user);
        addingRequest.add(item);
        opManager.updateAllAdminRequestedItems(item, "add");
    }

    /**
     * This method sets the user who requests to get unfreeze
     * @param user the normal user who requests to unfreeze his/her account
     */
    @Override
    public void requestToUnfreeze(IUserAccount user) {
        unfreezeRequester = user;
        ((IAdminOperation)opManager.getProperOperationByUser(user)).addUnfreezeNewMessages(user.getUserId(),
                        "Unfreezing Request: " + user.getUserId() +
                                " has requested to be unfrozen. ");
        ((IAdminOperation)opManager.getProperOperationByUser(user)).addUnfrozenRequestUsers(user.getUserId());
//            }
//            i++;
//        }
    }

    /**
     * This method gets the user who requests to unfreeze his/her account
     * @return the NormalUser who requests to unfreeze his/her account
     */
    @Override
    public IUserAccount getUnfreezeRequester() {
        return this.unfreezeRequester;
    }

    /**
     * This method gets the adding item request from a user
     * @return the list that stores the user and the item requested to add
     */
    @Override
    public List<Object> getAddingRequest() {
        return this.addingRequest;
    }

    /**
     * This method gets a collection of normal users
     * @return HashMap with the key to be user id (String) and value to be NormalUser
     */
    @Override
    public HashMap<String, IUserAccount> getNormalUsers() {
        return normalUsers;
    }

    /**
     * This method gets a collection of admin users
     * @return HashMap with the key to be user id (String) and value to be AdminUser
     */
    @Override
    public HashMap<String, IUserAccount> getAdminUsers() {
        return adminUsers;
    }


    /**
     * This method gets a collection of guest users
     * @return HashMap with the key to be user id (String) and value to be GuestUser
     */
    @Override
    public HashMap<String, IUserAccount> getGuestUsers() {
        return guestUser;
    }

    /**
     * gets the initial administrative user
     * @return the initial administrative user
     */
    @Override
    public IUserAccount getInitialAdmin() {
        IUserAccount initialAdmin = new AdminUser();

        for (Map.Entry<String, IUserAccount> adminUser : adminUsers.entrySet()) {
            if (adminUser.getValue().getIsInitialAdmin()) {
                initialAdmin = adminUser.getValue();
            }
        }
        return initialAdmin;
    }

    /**
     * method to find user instance by userId.
     * @param userId the id that we want to search
     * @return the User instance
     */
    @Override
    public IUserAccount getUserById(String userId){
        if (adminUsers.containsKey(userId)){
            return adminUsers.get(userId);
        } else if (normalUsers.containsKey(userId)){
            return normalUsers.get(userId);
        } else return guestUser.get(userId);
    }


    /**
     * This method converts the user's information to String
     * @return a list of users' IDs and statuses, separated by commas
     */
    @Override
    public String toString(){
        StringBuilder collect = new StringBuilder();
        Iterator<Map.Entry<String, IUserAccount>> mIterator = this.normalUsers.entrySet().iterator();
        Iterator<Map.Entry<String, IUserAccount>> aIterator = this.adminUsers.entrySet().iterator();
        Iterator<Map.Entry<String, IUserAccount>> gIterator = this.guestUser.entrySet().iterator();
        while (mIterator.hasNext()) {
            Map.Entry<String, IUserAccount> mapElement = mIterator.next();
            NormalUser m = (NormalUser) mapElement.getValue();
            collect.append(m.getUserId());
            collect.append(", ");
            collect.append(m.getStatus());
            collect.append(", ");
        }
        while (aIterator.hasNext()) {
            Map.Entry<String, IUserAccount> mapElement = aIterator.next();
            AdminUser a = (AdminUser) mapElement.getValue();
            collect.append(a.getUserId());
            collect.append(", ");
            collect.append(a.getStatus());
            collect.append(", ");
        }
        while (gIterator.hasNext()) {
            Map.Entry<String, IUserAccount> mapElement = gIterator.next();
            GuestUser g = (GuestUser) mapElement.getValue();
            collect.append(g.getUserId());
            collect.append(", ");
            collect.append(g.getStatus());
            collect.append(", ");
        }
        return collect.toString();
    }

    /**
     * Remove this user from normalUsers list.
     * @param user the user which is needed to be removed from normalUsers list.
     */
    @Override
    public void removeUser(IUserAccount user){
        normalUsers.remove(user.getUserId());
    }

    /**
     * This method changes the admin user to initial admin user if this user is the first
     * user to the system
     * @param user the first user to the system
     */
    @Override
    public void changeToInitialAdmin(IUserAccount user){
        IUserAccount a = this.adminUsers.get(user.getUserId());
        a.setIsInitialAdmin(true);
    }

    /**
     * changes user's password
     * @param user user who changes password
     * @param password new password
     */
    @Override
    public void changeUserPassword(IUserAccount user, String password){
        if (user.getStatus().equals("Admin")){
            IUserAccount a = adminUsers.get(user.getUserId());
            a.setPassword(password);
        }
        else if (user.getStatus().equals("Normal")){
            IUserAccount n = normalUsers.get(user.getUserId());
            n.setPassword(password);
        }
    }

    /**
     * adds user to related list
     * @param user the user whose related list is needed
     */
    @Override
    public void addUserToRelatedList(IUserAccount user){
        if (user.getStatus().equals("Admin")) {
            adminUsers.put(user.getUserId(),user);
        }
        else if (user.getStatus().equals("Normal")){
            normalUsers.put(user.getUserId(),user);
        }
    }

    /**
     * sets user's vacation timer
     * @param user the user whose vacation timer is set
     * @param startDate start date of user's vacation
     * @param endDate end date of user's vacation
     */
    @Override
    public void setUserVacationTimer(IUserAccount user, Date startDate, Date endDate) {
        IUserAccount vacationUser;
        if (user.getStatus().equals("Admin")) {
            vacationUser = adminUsers.get(user.getUserId());
        }
        else {
            vacationUser = normalUsers.get(user.getUserId());
        }
        try {
            new TimerOfIsOnVacation(vacationUser, startDate, endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
