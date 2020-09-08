package GateWay;

import Model.*;
import UseCases.UserUseCase.IAdminOperation;
import UseCases.UserUseCase.IUserOperation;

import java.sql.SQLException;

public interface IDataInsertion {

    /**
     * Insert the record for this new user in the related table
     * @param user the user who needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
     void normalUserInsertion(IUserAccount user) throws SQLException ;

    /**
     * Insert the record for this new user in the related table
     * @param user the user who needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    void adminUserInsertion(IUserAccount user) throws Exception;

    /**
     * Insert the record for this new UserAttributeLists in the related table
     * @param user the UserOperation which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
     void userOperationInsertion(IUserOperation user) throws Exception;

    /**
     * Insert the record for this new AdminAttributeLists in the related table
     * @param user the AdminOperation which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
     void adminOperationInsertion(IAdminOperation user) throws Exception;

    /**
     * Delete the record for this AdminAttributeLists in the related table
     * @param user the IUserOperation which needed be inserted
     * @throws Exception Exception delete error.
     * @see Exception
     */
     void operationInsertion(IUserOperation user) throws Exception;

    /**
     * Insert the record for this new item in the related table
     * @param item the item which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    void itemInsertion(ItemInterface item) throws Exception;

    /**
     * Insert the record for this new oneWayTransaction in the related table
     * @param oneWayTransaction the oneWayTransaction which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
    void oneWayTransactionsInsertion(ITransaction oneWayTransaction) throws Exception;

    /**
     * Insert the record for this new twoWayTransaction in the related table
     * @param twoWayTransaction the twoWayTransaction which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
     void twoWayTransactionsInsertion(ITransaction twoWayTransaction) throws Exception;

    /**
     * Insert the record for this meeting in the related table
     * @param meeting the meeting which needed be inserted
     * @throws Exception Exception insert error.
     * @see Exception
     */
     void meetingsInsertion(IMeeting meeting) throws Exception;


}
