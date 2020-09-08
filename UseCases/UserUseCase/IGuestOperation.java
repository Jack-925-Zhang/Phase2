package UseCases.UserUseCase;

import Model.ItemInterface;

import java.util.List;

/**
 * This class is the interface for the GuestOperation,
 * gives the needed method related to all actions of a guest user
 * @author Yinzhe Li
 * @version 1.8
 */

public interface IGuestOperation{
    /**
     * gets user id
     * @return user id
     */
    String getUserId();

    /**
     * This method shows the list of one user would like to borrow from others.
     *
     * @return wishToBorrow
     */
    List<ItemInterface> getWishToBorrow();

    /**
     * This method shows the list of one user would like to lend to others.
     *
     * @return wishToLend
     */
    List<ItemInterface> getWishToLend();

    /**
     * This method sets list the user would like to borrow from others
     *
     * @param wishToBorrow items the users would like to borrow from others.
     */
    void setWishToBorrow(List<ItemInterface> wishToBorrow);

    /**
     * This method sets list the user would like to lend to others
     *
     * @param wishToLend items the users would like to lend to others.
     */
    void setWishToLend(List<ItemInterface> wishToLend);

    /**
     * gets wish to borrow list
     * @return wish to borrow list
     */
    String getWishToBorrowList();

    /**
     * gets wish to lend list
     * @return wish to lend list
     */
    String getWishToLendList();
}
