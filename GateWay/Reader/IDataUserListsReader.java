package GateWay.Reader;

import UseCases.UserUseCase.UserOperation;

import java.util.List;

/**
 * This class is interface of the class the reader for the UserLists.
 * It would return the instances of UserLists
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */
public interface IDataUserListsReader {

    /**
     * It is a getter for UserLists.
     * @return the list of UserLists.
     */
    List<UserOperation> getUserOperations();
}
