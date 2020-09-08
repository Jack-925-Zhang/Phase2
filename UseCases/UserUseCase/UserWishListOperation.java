package UseCases.UserUseCase;

import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.IOpManager;
import UseCases.OpManager;
import java.util.List;

public class UserWishListOperation implements IUserWishListOperation {
    private final IUserManager userManager;
    private final IOpManager opManager;
    private static final UserWishListOperation userWishListOperation = new UserWishListOperation();

    /**
     * This method is the default constructor of the UserWishListOperation class.
     * Load configuration file
     */
    public UserWishListOperation(){
        userManager = UserManager.getInstance();
        opManager = OpManager.getInstance();
    }

    /**
     * This method is providing get instance of wish list operation.
     * @return userWishListOperation the instance of wish list operation.
     */
    public static UserWishListOperation getInstance(){
        return userWishListOperation;
    }
    /**
     * This method adds items the user would like to lend to a list.
     * @param item The item user wants to lend to others.
     * @param user the user account
     */
    @Override
    public void addToWishToLend(IUserAccount user, ItemInterface item){
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        List<ItemInterface> wishToLend = properOperation.getWishToLend();
        wishToLend.add(item);
        properOperation.setWishToLend(wishToLend);
    }

    /**
     * This method removes items from the list they can lend to others.
     * @param item the items user wants to lend
     * @param user the requested user account
     */
    @Override
    public void removeFromWishToLend(IUserAccount user, ItemInterface item){
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        List<ItemInterface> wishToLend = properOperation.getWishToLend();
        wishToLend.remove(item);
        properOperation.setWishToLend(wishToLend);
    }

    /**
     * This method adds items the user would like to borrow from to a list.
     * @param item the item user would like to add to wishToBorrow
     * @param user the requested user account
     */
    @Override
    public void addToWishToBorrow(IUserAccount user, ItemInterface item){
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        List<ItemInterface> wishToBorrow = properOperation.getWishToBorrow();
        wishToBorrow.add(item);
        properOperation.setWishToLend(wishToBorrow);
    }

    /**
     * This method removes items the user would like to borrow from the list.
     * @param item the items user want to borrow.
     * @param user the requested user account
     */
    @Override
    public void removeFromWishToBorrow(IUserAccount user, ItemInterface item){
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        List<ItemInterface> wishToBorrow = properOperation.getWishToBorrow();
        wishToBorrow.remove(item);
        properOperation.setWishToLend(wishToBorrow);
    }
}
