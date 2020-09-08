package GateWay.Reader;

import UseCases.UserUseCase.AdminOperation;

import java.util.List;

/**
 * This class is interface of the class the reader for the AdminLists.
 * It would return the AdminLists.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */

public interface IDataAdminListsReader {

    /**
     * It is a getter for AdminLists.
     * @return the list of AdminLists.
     */
    List<AdminOperation> getAdminOperations();
}
