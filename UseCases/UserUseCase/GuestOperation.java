package UseCases.UserUseCase;

import GateWay.DataSaverHelper;
import Model.GuestUser;
import Model.ItemInterface;
import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.List;

/**
 * the class stores the operations guest user can access
 * @author Yinzhe Li
 * @version 1.8
 */
public class GuestOperation implements IGuestOperation{

    private List<ItemInterface> wishToBorrow;
    private List<ItemInterface> wishToLend;
    private final String userId;

    private final DataSaverHelper dataSaverHelper;

    private final String wishToBorrowList;

    private final String wishToLendList;

    /**
     * constructor for class GuestOperation
     */
    public GuestOperation(){
        wishToBorrow = new ArrayList<>();
        wishToLend = new ArrayList<>();
        userId= new GuestUser().getUserId();
        dataSaverHelper = DataSaverHelper.getInstance();
        wishToBorrowList = dataSaverHelper.ItemInterfaceListCovert(getWishToBorrow());
        wishToLendList = dataSaverHelper.ItemInterfaceListCovert(getWishToLend());

    }

    /**
     * gets user's id
     * @return user's id
     */
    @Override
    public String getUserId() {
        return this.userId;
    }

    /**
     * gets wish to borrow list
     * @return wish to borrow list
     */
    @Override
    public List<ItemInterface> getWishToBorrow() {
        return this.wishToBorrow;
    }

    /**
     * gets wish to lend list
     * @return wish to lend list
     */
    @Override
    public List<ItemInterface> getWishToLend() {
        return this.wishToLend;
    }

    /**
     * sets wish to borrow list
     * @param wishToBorrow items the user would like to borrow from others.
     */
    @Override
    public void setWishToBorrow(List<ItemInterface> wishToBorrow) {
        this.wishToBorrow = wishToBorrow;
    }

    /**
     * sets wish to lend list
     * @param wishToLend items the user would like to lend to others.
     */
    @Override
    public void setWishToLend(List<ItemInterface> wishToLend) {
        this.wishToLend = wishToLend;
    }

    /**
     * gets wish to borrow list
     * @return wish to borrow list
     */
    @Override
    public String getWishToBorrowList() {
        return wishToBorrowList;
    }

    /**
     * gets wish to lend list
     * @return wish to lend list
     */
    @Override
    public String getWishToLendList() {
        return wishToLendList;
    }
}
