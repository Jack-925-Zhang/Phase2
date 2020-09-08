package GateWay.Reader;

import Model.NormalUser;

import java.util.List;

/**
 * This class is interface of the class the reader for the Normal user.
 * It would return the instances of Normal user
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */

public interface IDataNormalReader {

    /**
     * It is a getter for normalUsers.
     * @return the list of normalUsers.
     */

    List<NormalUser> getNormalUsersAccounts();
}
