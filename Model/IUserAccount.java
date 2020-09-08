package Model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * IUserAccount  is the interface for all users whether normal or administrative.
 * Allow to getter or setter user data.
 * @author LINNI XIE
 * @version 1.8
 */
public interface IUserAccount {
    /**
     * Get the id of an user.
     *
     * @return the value of user id
     */
    String getUserId();

    /**
     * Set the id of an user.
     *
     * @param userId the id of an user
     */
    void setUserId(String userId);

    /**
     * Get the password of an user.
     *
     * @return the value of user password
     */
    String getPassword();

    /**
     * Set the password of an user.
     *
     * @param userPassword the password of an user
     */
    void setPassword(String userPassword);

    /**
     * Get the status of an user whether normal or administrative
     *
     * @return the value of user status
     */
    String getStatus();

    /**
     * Set the status of an user whether normal or administrative
     *
     * @param userStatus the status of an user whether normal or administrative
     */
    void setStatus(String userStatus);

    /**
     * Get the number of item which is the user already lent.
     *
     * @return the value of the number of lent item.
     */
    int getNumLent();

    /**
     * Get the number of item which is the user already borrowed.
     *
     * @return the value of the number of borrowed item.
     */
    int getNumBorrowed();

    /**
     * Get the the number of times that this user lend items before he/she/it can borrow.
     *
     * @return the value of times that this user lend items before he/she/it can borrow
     */
    int getThreshold();

    /**
     * Get the freezing status of the user whether frozen or not.
     *
     * @return the value of the user whether frozen or not.
     */
    boolean getIsFreeze();

    /**
     * Set the freezing status of the user whether frozen or not.
     *
     * @param isFreeze the freezing status of the user
     */
    void setIsFreeze(boolean isFreeze);

    /**
     * Set the the number of times that this user lend items before he/she/it can borrow.
     *
     * @param threshold the number of times that this user lend items before he/she/it can borrow
     */
    void setThreshold(int threshold);


    /**
     * Get the number of item which is the user already borrowed.
     *
     * @param numBorrowed the number of item which is the user already borrowed.
     */
    void setNumBorrowed(int numBorrowed);

    /**
     * Get the number of item which is the user already lent.
     *
     * @param numLent the number of item which is the user already lent
     */
    void setNumLent(int numLent);

    /**
     * Set the number of transactions which is already finished.
     *
     * @param currentTrans the number of transactions which is already finished
     */
    void setCurrentTrans(int currentTrans);

    /**
     * Get the number of transactions which is already finished.
     *
     * @return the value of transactions which is finished
     */
    int getCurrentTrans();

    /**
     * Set the limit times of transaction for this user
     *
     * @param transactionLimit the limit times of transaction
     */
    void setTransactionLimit(int transactionLimit);

    /**
     * Get the limit number of transaction which is for this user.
     *
     * @return the value of limit times for transaction
     */
    int getTransactionLimit();

//    /**
//     * Used for save user account data
//     *
//     * @return the value of user data
//     */
//    String savedData();

    /**
     * This method set the user sets whether one user is on vacation or not.
     *
     * @param isOnVocation whether or not this user is on vacation.
     */
    void setOnVacation(boolean isOnVocation);

    /**
     * This method shows whether one user is on vacation or not.
     *
     * @return isOnVacation
     */
    boolean getOnVacation();

    /**
     * This method is for user who cancel the status for on vacation
     */
    void cancelOnVacation();

    /**
     * This method shows the home city of the user.
     *
     * @return homeCity
     */
    String getHomeCity();

    /**
     * This method sets the home city of the user.
     *
     * @param homeCity the home city of the user.
     */
    void setHomeCity(String homeCity);


    /**
     * This method show the start date the user who would be on vacation.
     *
     * @return startDate.
     */
    Date getStartDateOfVacation();

    /**
     * This methods sets the start date of vacation of user.
     *
     * @param startDate the start date of vacation.
     */
    void setStartDateOfVacation(Date startDate);

    /**
     * This method shows the end date the user who would be on vacation.
     *
     * @return endDate.
     */
    Date getEndDateOfVacation();

    /**
     * This method sets the end date of vacation of user.
     *
     * @param endDate the end date of vacation.
     */
    void setEndDateOfVacation(Date endDate);


    /**
     * return the amount of money this user has
     *
     * @return amount of money
     */
    int getCredit();

    /**
     * set the amount of money this user has
     *
     * @param credit new credit for this user
     */
    void setCredit(int credit);

    /**
     * Get the adminUser status whether is initial adminUser or not
     * @return the value of the adminUser status whether is initial adminUser or not
     */
    boolean getIsInitialAdmin();

    /**
     * Set the adminUser status whether is initial adminUser or not
     * @param isInitialAdmin the adminUser status whether is initial adminUser or not
     */
    void setIsInitialAdmin(boolean isInitialAdmin);
}
