package UseCases;

import Model.IUserAccount;
import Model.ItemInterface;

import java.util.List;

/**
 * This class is the interface implements by UserSearch
 * @author Yupeng Zhang
 * @version 1.8
 */

public interface UserSearchInterface {
    /**
     * provides the result of items searched by the user
     * @param ItemName the name of items
     * @return  ItemSearch
     */
    List<ItemInterface> UserSearchItem(List<String> ItemName);

    /**
     * provides the result of users searched by the user.
     * @param UserID the id of user.
     * @return UserSearch
     */
    List<IUserAccount> UserSearchUser(List<String> UserID);
}
