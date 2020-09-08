package Controller;

import GateWay.*;
import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.*;
import UseCases.UserUseCase.*;

public class UserWishListOpGUINavigator {

    private static final UserWishListOpGUINavigator userWishListOpGUINavigator = new UserWishListOpGUINavigator();
    private final IUserWishListOperation userWishListOperation;
    private final IDataDeleter dataDeleter;
    private final IDataInsertion dataInsertion;
    private final IUserManager userManager;
    private final IOpManager opManager;

    /**
     * the constructor of class UserWishListOpGUINavigator
     */
    public UserWishListOpGUINavigator() {
        userWishListOperation = UserWishListOperation.getInstance();
        dataDeleter = new DataDeleter();
        dataInsertion = new DataInsertion();
        userManager = UserManager.getInstance();
        opManager = OpManager.getInstance();
    }

    /**
     * gets instance userWishListOpGUINavigator
     * @return userWishListOpGUINavigator
     */
    public static UserWishListOpGUINavigator getInstance(){
        return userWishListOpGUINavigator;
    }

    /**
     * This method navigates the method adds items the user would like to lend to a list.
     * @param item The item user wants to lend to others.
     * @param user the requested user
     */
    public void addToWishToLendNavigator(IUserAccount user, ItemInterface item) throws Exception {
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        dataDeleter.operationDeleter(properOperation);
        userWishListOperation.addToWishToLend(user,item);
        dataInsertion.operationInsertion(properOperation);
    }

    /**
     * This method navigates the method removes items from the list they can lend to others.
     * @param item the items user wants to lend
     * @param user the requested user
     */
    public void removeFromWishToLendNavigator(IUserAccount user, ItemInterface item) throws Exception {
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        dataDeleter.operationDeleter(properOperation);
        userWishListOperation.removeFromWishToLend(user, item);
        dataInsertion.operationInsertion(properOperation);
    }

    /**
     * This method navigates the method adds items the user would like to borrow from to a list.
     * @param item the item user would like to add to wishToBorrow
     * @param user the requested user id
     */
    public void addToWishToBorrowNavigator(IUserAccount user, ItemInterface item) throws Exception {
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        dataDeleter.operationDeleter(properOperation);
        userWishListOperation.addToWishToBorrow(user, item);
        dataInsertion.operationInsertion(properOperation);

    }

    /**
     * This method navigates the method removes items the user would like to borrow from the list.
     * @param item the items user want to borrow.
     * @param user the requested user
     */
    public void removeFromWishToBorrowNavigator(IUserAccount user, ItemInterface item) throws Exception {
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        dataDeleter.operationDeleter(properOperation);
        userWishListOperation.addToWishToBorrow(user, item);
        dataInsertion.operationInsertion(properOperation);
    }

}
