package GateWay;

import Model.ItemInterface;
import UseCases.UserUseCase.IUserManager;

import java.util.HashMap;
import java.util.List;


/**
 * this interface for class loads data
 * @author LINNI XIE
 * @version 1.8
 */
public interface IDataLoading {

    /**
     * loads account
     */
    IUserManager accountLoading();

    /**
     * loads item
     */
    HashMap<Integer, ItemInterface> itemLoading();

    /**
     * loads meeting system
     */
    void meetingSystemLoading();

    /**
     * loads admins lists
     */
    void adminsOperationsLoading();

    /**
     * loads users lists
     */
    void usersListsLoading();










}
