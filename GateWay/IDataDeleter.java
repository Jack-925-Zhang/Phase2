package GateWay;

import Model.*;
import UseCases.UserUseCase.AdminOperation;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.UserOperation;

/**
 * This interface of class DataDeleter will delete one record for the specific table in the previously created databases
 * @author LINNI XIE
 * @version 1.8
 */
public interface IDataDeleter {

    /**
     * Delete the record for this user in the related table
     * @param user the user who needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */

    void normalUserDeleter(NormalUser user) throws Exception;

    /**
     * Delete the record for this user in the related table
     * @param user the user who needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    void adminUserDeleter(AdminUser user) throws Exception;


    /**
     * Delete the record for this UserAttributeLists in the related table
     * @param user the UserOperation which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    void userOperationDeleter(UserOperation user) throws Exception;

    /**
     * Delete the record for this AdminAttributeLists in the related table
     * @param user the AdminOperation which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    void adminOperationDeleter(AdminOperation user) throws Exception;

    /**
     * Delete the record for this AdminAttributeLists in the related table
     * @param user the IUserOperation which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    void operationDeleter(IUserOperation user) throws Exception;

    /**
     * Delete the record for this item in the related table
     * @param item the item which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    void itemDeleter(Item item) throws Exception;

    /**
     * Delete the record for this oneWayTransaction in the related table
     * @param oneWayTransaction the oneWayTransaction which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    void oneWayTransactionsDeleter(OneWayTransaction oneWayTransaction) throws Exception;

    /**
     * Delete the record for this meeting in the related table
     * @param meeting the meeting which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
    void meetingsDeleter(Meeting meeting) throws Exception;

    /**
     * Delete the record for this twoWayTransaction in the related table
     * @param twoWayTransaction the twoWayTransaction which needed be deleted
     * @throws Exception Exception delete error.
     * @see Exception
     */
     void twoWayTransactionsDeleter(TwoWayTransaction twoWayTransaction) throws Exception;

}
