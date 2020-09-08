package UseCases.UserUseCase;

import Model.IUserAccount;
import Model.ItemInterface;

public interface IUserWishListOperation {

    /**
     * This method adds items the user would like to lend to a list.
     * @param item The item user wants to lend to others.
     * @param user the user account
     */
    void addToWishToLend(IUserAccount user, ItemInterface item) throws Exception;

    /**
     * This method removes items from the list they can lend to others.
     * @param item the items user wants to lend
     * @param user the requested user account
     */
    void removeFromWishToLend(IUserAccount user, ItemInterface item) throws Exception;

    /**
     * This method adds items the user would like to borrow from to a list.
     * @param item the item user would like to add to wishToBorrow
     * @param user the requested user account
     */
    void addToWishToBorrow(IUserAccount user, ItemInterface item) throws Exception;

    /**
     * This method removes items the user would like to borrow from the list.
     * @param item the items user want to borrow.
     * @param user the requested user account
     */
    void removeFromWishToBorrow(IUserAccount user, ItemInterface item) throws Exception;
}
