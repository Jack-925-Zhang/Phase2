package Model;

import Model.NormalUser;
import UseCases.UserUseCase.IAdminOperation;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * AdminUser is for administrative user to confirm that the user account should be frozen (even if the system
 * identifies them for the user to be frozen), or they can add new items to the user’s list of available items
 * or change any thresholds(for example: must How many times can you borrow to borrow items).
 * @author LINNI XIE
 * @version 1.8
 */

@DatabaseTable(tableName = "adminUser")
public class AdminUser extends NormalUser {

    public static final String IS_INITIAL_ADMIN_FIELD_NAME= "isInitialAdmin";

    @DatabaseField(columnName = IS_INITIAL_ADMIN_FIELD_NAME)
    public boolean isInitialAdmin;

    /**
     * default constructor for AdminUser
     */
    public AdminUser(){}

    /**
     * This method used to create an instance of an administrative user.
     * @param userId the id of user.
     * @param userPassword the password of user.
     * @param userStatus the status of user whether normal or administrative.
     * @param homeCity the home city of the user.
     */

    public AdminUser (String userId, String userPassword, String userStatus, String homeCity, boolean isInitialAdmin){
        super(userId, userPassword, userStatus,homeCity);
        this.isInitialAdmin = isInitialAdmin;
    }

    /**
     * Get the adminUser status whether is initial adminUser or not
     * @return the value of the adminUser status whether is initial adminUser or not
     */
    @Override
    public boolean getIsInitialAdmin() {
        return isInitialAdmin;
    }

    /**
     * Set the adminUser status whether is initial adminUser or not
     * @param isInitialAdmin the adminUser status whether is initial adminUser or not
     */
    @Override
    public void setIsInitialAdmin(boolean isInitialAdmin) {
        this.isInitialAdmin = isInitialAdmin;
    }

}
