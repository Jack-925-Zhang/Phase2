package UseCases;

import Model.IUserAccount;
import Model.ItemInterface;

import java.util.List;
import java.util.HashMap;

/**
 * RecommendationInterface is the interface implements by Recommendation.
 *
 * @author Yupeng Zhang
 * @version 1.8
 */

public interface RecommendationInterface {
    /**
     * set the city map by adding all cities and their corresponding users.
     * @param city the city input.
     * @param user the users who all lives in this city.
     */
    void setUserCityMap(String city, IUserAccount user);

    /**
     * It shows the city map of all cities and their corresponding users.
     * @return SameCityUser.toString().
     */
    HashMap<String, List<IUserAccount>> getUserCityMap();

    /**
     * It provides a recommendation list of users who live in the same city.
     * @param city the city input
     * @return SameCityUser.toString()
     */
    String SameCityUserRecommend(String city);


    /**
     * It provides a suggestion of match items the user want to borrow with the same items needed to be lent
     * by other users.
     * @param user1 the user who wish to lend the item
     * @param user2 the user who wish to borrow the items
     * @return SuggestItem
     */
    List<ItemInterface> SuggestLend(IUserAccount user1, IUserAccount user2);

    /**
     * It provides a list of recommended items base on the comparision of the userItemRating and
     * itemUserCollection given by user to other items.
     * @param user the user who is looking for recommended items.
     * @return RecommendItems.
     */
    List<ItemInterface> RecommendItem(IUserAccount user);

    /**
     * It recommends user with a list of other user depending on their similarity in the userItemRating and collection
     * and userItemCollection.
     * @param user the user who is looking for recommended users
     * @return userAccountList
     */
    List<IUserAccount> RecommendUser(IUserAccount user);

}
