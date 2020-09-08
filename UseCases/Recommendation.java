package UseCases;

import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.UserUseCase.UserManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * This class serve for  
 * it provides a city map that stores all cities and their corresponding users.
 * It introduces user to others who are on the same cities.
 * It provides suggestion of the matched items from the user would like to borrow with the other users would
 * like to lend.
 * It recommends user with other users base on the degree of similarity in their ratings for their items and
 * the similarity in their collection of items.
 * It provides a recommendation degree map of items to user based on their ratings and collection of items.
 * It provides a list of all users in this city by entering a city input.
 *
 * @author Yupeng Zhang
 * @version 1.8
 */

public class Recommendation implements RecommendationInterface {
    public HashMap<String, List<IUserAccount>> UserCityMap;
    int[][] sparseMatrix;
    public Map<String, Integer> userItemLength;
    public Map<String, Set<String>> itemUserCollection;
    public Set<Integer> items;
    public Map<String, Integer> userID;
    public Map<Integer, String> IDUser;
    public UserManager manager;
    public ItemInterface ItemManager;
    private final IOpManager opManager;
    /**
     * Creates instance of recommendation
     */
    public Recommendation(){
        UserCityMap = new HashMap<>();
        userItemLength = new HashMap<>();
        itemUserCollection = new HashMap<>();
        items = new HashSet<>();
        userID = new HashMap<>();
        IDUser = new HashMap<>();
        opManager = OpManager.getInstance();
    }

    /**
     * set the city map by adding all cities and their corresponding users.
     * @param city the city input.
     * @param user the users who all lives in this city.
     */
    @Override
    public void setUserCityMap(String city, IUserAccount user){
        List<IUserAccount> UserList = new ArrayList<>();
        city = city.toLowerCase();
        if(UserCityMap.size() == 0 ){
            UserList.add(user);
            UserCityMap.put(city, UserList);

        }
        if (!(UserCityMap.containsKey(city))){
            UserList.add(user);
            UserCityMap.put(city, UserList);
        }
        else {
            city = city.toLowerCase();
            if(UserCityMap.containsKey(city)){
                UserCityMap.get(city).add(user);
            }
        }
    }

    /**
     * It shows the city map of all cities and their corresponding users.
     * @return SameCityUser.toString().
     */
    @Override
    public HashMap<String, List<IUserAccount>> getUserCityMap() {
        return UserCityMap;
    }

    /**
     * It provides a recommendation list of users who live in the same city.
     * @param city the city input
     * @return SameCityUser.toString()
     */
    @Override
    public String SameCityUserRecommend(String city) {
        StringBuilder SameCityUser = new StringBuilder();
        city = city.toLowerCase();
        List<IUserAccount> UserList = getCityUser(city);
        if(UserList.size() != 0) {
            for (IUserAccount userAccount : UserList) {
                SameCityUser.append(userAccount.getUserId()).append(" ");
            }
        }
        else{
            SameCityUser.append(" ");
        }
        return SameCityUser.toString();
    }

    /**
     * It provides a suggestion of match items the user want to borrow with the same items needed to be lent
     * by other users.
     * @param user1 the user who wish to lend the item
     * @param user2 the user who wish to borrow the items
     * @return SuggestItem
     */
    @Override
    public List<ItemInterface> SuggestLend(IUserAccount user1, IUserAccount user2) {
        List<ItemInterface> SuggestItem = new ArrayList<>();
        List<ItemInterface> user1WishToLend = opManager.getProperOperationByUser(user1).getWishToLend();
        List<ItemInterface> user2WishToBorrow = opManager.getProperOperationByUser(user2).getWishToBorrow();
        for(ItemInterface item : user1WishToLend){
            if (user2WishToBorrow.contains(item)){
                SuggestItem.add(item);
            }
        }
        return SuggestItem;
    }

    /**
     * It recommends user with a list of other user depending on their similarity in the userItemRating and collection
     * and userItemCollection.
     * @param user the user who is looking for recommended users
     * @return userAccountList
     */
    @Override
    public List<IUserAccount> RecommendUser(IUserAccount user){
        List<IUserAccount> userAccountList = new ArrayList<>();
        HashMap<String, IUserAccount> normalUsers= manager.getNormalUsers();
        Map<Double, String> RecommendationDegreeMap = new HashMap<>();
        Set<String> set = normalUsers.keySet();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            int l = 0;
            String UserId = iterator.next();
            int size = opManager.getProperOperationByUser(manager.getUserById(UserId)).getUserItemRating().size();
            userItemLength.put(UserId, size);
            userID.put(UserId, l);
            IDUser.put(l, UserId);
            Set<Integer> TempCol = opManager.getProperOperationByUser(user).getUserItemRating().keySet();
            l = l + 1;
            for(int k: TempCol){
                if(!(items.contains(k))){
                    items.add(k);
                    itemUserCollection.put(String.valueOf(k), new HashSet<String>());
                }
                itemUserCollection.get(String.valueOf(k)).add(UserId);
            }
        }
        Set<Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
        Iterator<Entry<String, Set<String>>> ite = entrySet.iterator();
        while(ite.hasNext()){
            Set<String> commonUsers = ite.next().getValue();
            for (String user_v : commonUsers) {
                if(user.getUserId().equals(user_v)){
                    continue;
                }
                sparseMatrix[userID.get(user.getUserId())][userID.get(user_v)] += 1;
                }
            }
        String recommendUser = user.getUserId();
        int recommendUserId = userID.get(recommendUser);
        for (int j = 0;j < sparseMatrix.length; j++) {
            if(j != recommendUserId){
                double Similarity = sparseMatrix[recommendUserId][j]/Math.sqrt(
                        userItemLength.get(IDUser.get(recommendUserId))*userItemLength.get(IDUser.get(j)));
                RecommendationDegreeMap.put(Similarity, IDUser.get(j));
            }
        }
        Set<Double> DegreeSet =RecommendationDegreeMap.keySet();
        Object[] arr = DegreeSet.toArray();
        Arrays.sort(arr);
        int k = 0;
        for(Object key : arr){
            if (k < 3) {
                String UserId = RecommendationDegreeMap.get(Double.parseDouble(key.toString()));
                userAccountList.add(manager.getUserById(UserId));
                k++;
            }
            else{
                break;
            }
        }
        return userAccountList;
    }

    /**
     * It provides a list of recommended items base on the comparision of the userItemRating and
     * itemUserCollection given by user to other items.
     * @param user the user who is looking for recommended items.
     * @return RecommendItems.
     */
    @Override
    public List<ItemInterface> RecommendItem(IUserAccount user) {
        List<ItemInterface> RecommendItem = new ArrayList<>();
        Map<Double, Integer> RecommendationDegreeMap = new HashMap<>();
        HashMap<String, IUserAccount> normalUsers = manager.getNormalUsers();
        Set<String> set = normalUsers.keySet();
        Iterator<String> iterator = set.iterator();
        int l = 0;
        while (iterator.hasNext()) {
            String UserId = iterator.next();
            int size = opManager.getProperOperationByUser(manager.getUserById(UserId)).getUserItemRating().size();
            userItemLength.put(UserId, size);
            userID.put(UserId, l);
            IDUser.put(l, UserId);
            Set<Integer> TempCol = opManager.getProperOperationByUser(user).getUserItemRating().keySet();
            l++;
            for (int k : TempCol) {
                if (!(items.contains(k))) {
                    items.add(k);
                    itemUserCollection.put(String.valueOf(k), new HashSet<String>());
                }
                itemUserCollection.get(String.valueOf(k)).add(UserId);
            }
        }
        Set<Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
        Iterator<Entry<String, Set<String>>> ite = entrySet.iterator();
        while (ite.hasNext()) {
            Set<String> commonUsers = ite.next().getValue();
            for (String user_v : commonUsers) {
                if (user.getUserId().equals(user_v)) {
                    continue;
                }
                sparseMatrix[userID.get(user.getUserId())][userID.get(user_v)] += 1;
            }
        }
        String recommendUser = user.getUserId();
        for (int item : items) {
            Set<String> users = itemUserCollection.get(String.valueOf(item));
            if (!users.contains(recommendUser)) {
                double itemRecommendDegree = 0.0;
                for (String UserId : users) {
                    itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(UserId)] /
                            Math.sqrt(userItemLength.get(recommendUser) * userItemLength.get(UserId));
                }
                System.out.println("The item " + item + " for " + recommendUser +
                        "'s recommended degree:" + itemRecommendDegree);
                RecommendationDegreeMap.put(itemRecommendDegree, item);
            }
        }
        Set<Double> DegreeSet =RecommendationDegreeMap.keySet();
        Object[] arr = DegreeSet.toArray();
        Arrays.sort(arr);
        int k = 0;
        for(Object key : arr){
            if (k < 3) {
                int ItemId =  RecommendationDegreeMap.get(Double.parseDouble(key.toString()));
                ItemInterface Item = ItemManager.SearchItemByKey(ItemId);
                RecommendItem.add(Item);
                k++;
            }
            else{
                break;
            }
        }
        return RecommendItem;
    }

    /**
     * It provides a list of all users who live in this city.
     * @param city the city input
     * @return UserCityMap.get(city)
     */
    public List<IUserAccount> getCityUser(String city) {
        city = city.toLowerCase();
        return UserCityMap.get(city);
    }


}

