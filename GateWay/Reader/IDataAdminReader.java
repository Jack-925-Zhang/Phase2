package GateWay.Reader;

import Model.AdminUser;

import java.util.List;

/**
 * This class is interface of the class the reader for the Admin.
 * It would return the Admin.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */
public interface IDataAdminReader {

    /**
     * It is a getter for Admins.
     * @return the list of Admins.
     */
    List<AdminUser> getAdmins();
}
