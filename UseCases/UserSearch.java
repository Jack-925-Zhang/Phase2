package UseCases;

import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.UserManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.*;

/**
 * UserSearch allows users to search for the items by entering the name of item, and it also allows users to search for
 * other users by entering the user id.
 *
 * @author Yupeng Zhang
 * @version 1.8
 */

public class UserSearch implements UserSearchInterface{

    public IUserManager userManager;
    public TradingSystem Trading;

    /**
     * the constructor of class UserSearch
     */
    public UserSearch() {
        this.userManager = UserManager.getInstance();
        Trading = TradingSystem.getInstance();
    }

    /**
     * provides the result of items searched by the user
     * @param ItemName the name of items
     * @return ItemSearch
     */
    @Override
    public List<ItemInterface> UserSearchItem(List<String> ItemName){
        List<ItemInterface> ItemSearch = new ArrayList<>();
        if(ItemName.size() != 0){
        Map<Double, ItemInterface> TempRecommend = new HashMap<>();
        HashMap<Integer, ItemInterface> generalInventory = Trading.generalInventory;
        Collection<ItemInterface> col = generalInventory.values();
        for(String a : ItemName){
        for (ItemInterface item : col) {
            if (item.getItemName().contains(a)) {
                double result;
                int fullLength = item.getItemName().length();
                int afterLength = item.getItemName().replace(a, "").length();
                result = (fullLength - afterLength);
                if (TempRecommend.size()!=0){
                Collection<ItemInterface> co = TempRecommend.values();
                if (!(co.contains(item))) {
                    TempRecommend.put(result, item);
                }
                else{
                    for(Object key: TempRecommend.keySet()){
                        if(TempRecommend.get(key).equals(item)){
                            TempRecommend.remove(key);
                            key =((Double.parseDouble(key.toString()) + result) / 2) ;
                            TempRecommend.put((Double.parseDouble(key.toString())), item);

                        }
                    }
                }
                }
                else {
                    TempRecommend.put(result, item);
                }
            }
        }
        }

        Set<Double> DegreeSet =TempRecommend.keySet();
        Object[] arr = DegreeSet.toArray();
        Arrays.sort(arr);
        int k = 0;
        for(Object key : arr){
            if (k < 5) {
                ItemInterface Item =  TempRecommend.get(Double.parseDouble(key.toString()));
                ItemSearch.add(Item);
                k++;
            }
            else{
                break;
            }
        }
        }
        return ItemSearch;
    }

    /**
     * provides the result of users searched by the user.
     * @param UserID the id of user.
     * @return UserSearch
     */
    @Override
    public List<IUserAccount> UserSearchUser(List<String> UserID){
        List<IUserAccount> UserSearch = new ArrayList<>();
        if(UserID.size() != 0){
            Map<Double, IUserAccount> TempRecommend = new HashMap<>();
            HashMap<String, IUserAccount> Users = userManager.getNormalUsers();
            Collection<IUserAccount> col = Users.values();
            for(String a : UserID){
                for (IUserAccount user : col) {
                    if (user.getUserId().contains(a)) {
                        double result;
                        int fullLength = user.getUserId().length();
                        int afterLength = user.getUserId().replace(a, "").length();
                        result = (fullLength - afterLength);
                        if (TempRecommend.size()!=0){
                            Collection<IUserAccount> co = TempRecommend.values();
                            if (!(co.contains(user))) {
                                TempRecommend.put(result, user);
                            }
                            else{
                                for(Object key: TempRecommend.keySet()){
                                    if(TempRecommend.get(key).equals(user)){
                                        TempRecommend.remove(key);
                                        key =((Double.parseDouble(key.toString()) + result) / 2) ;
                                        TempRecommend.put((Double.parseDouble(key.toString())), user);

                                    }
                                }
                            }
                        }
                        else {
                            TempRecommend.put(result, user);
                        }
                    }
                }
            }

            Set<Double> DegreeSet =TempRecommend.keySet();
            Object[] arr = DegreeSet.toArray();
            Arrays.sort(arr);
            int k = 0;
            for(Object key : arr){
                if (k < 5) {
                    IUserAccount Item =  TempRecommend.get(Double.parseDouble(key.toString()));
                    UserSearch.add(Item);
                    k++;
                }
                else{
                    break;
                }
            }
        }
        return UserSearch;
    }

}
