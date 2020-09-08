package Model;

import java.util.Date;
import java.util.UUID;

/**
 * GuestUser is for the user who want to explore the program without creating an account or joining the trading community
 * The user cannot trade or communicate with the admin user
 *
 * @author Yinzhe Li
 * @version 1.8
 */

public class GuestUser implements IUserAccount {

    private final String guestID = _usingUUID();
    private final String GUEST_STATUS = "Guest";

    /**
     * A helper to form a random user ID
     * @return the value of a random ID
     */
    static String _usingUUID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "").substring(0, 8);
    }

    /**
     * Created the instance of GuestUser
     */
    public GuestUser() {
    }

    /**
     * Get the id of an user.
     * @return the value of user id
     */
    @Override
    public String getUserId() {
        return this.guestID;
    }


    @Override
    public void setUserId(String userId) {

    }

    /**
     * Get the password of an user.
     * @return the value of user password
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * set the password of an user.
     * @param userPassword the password of an user
     */
    @Override
    public void setPassword(String userPassword) {

    }

    /**
     * Get the status of an user whether normal or administrative
     * @return the value of user status
     */
    @Override
    public String getStatus() {
        return this.GUEST_STATUS;
    }

    /**
     * set the user status of an user.
     * @param userStatus the status of an user whether normal or administrative
     */
    @Override
    public void setStatus(String userStatus) {

    }

    /**
     * Get the number fo item lent of an user.
     * @return the value of user number of item lent
     */
    @Override
    public int getNumLent() {
        return 0;
    }

    /**
     * Get the number fo item borrowed of an user.
     * @return the value of user number of item borrowed
     */
    @Override
    public int getNumBorrowed() {
        return 0;
    }

    /**
     * Get the threshold of an user.
     * @return the value of user threshold
     */
    @Override
    public int getThreshold() {
        return 0;
    }

    /**
     * Get whether is frozen or not of an user.
     * @return whether is frozen or not of an user.
     */
    @Override
    public boolean getIsFreeze() {
        return false;
    }

    /**
     * set whether is frozen or not of an user.
     * @param isFreeze the freezing status of the user
     */
    @Override
    public void setIsFreeze(boolean isFreeze) {

    }

    /**
     * set the threshold of user
     * @param threshold the number of times that this user lend items before he/she/it can borrow
     */
    @Override
    public void setThreshold(int threshold) {

    }

    /**
     * set number of item borrowed of an user.
     * @param numBorrowed the number of item which is the user already borrowed.
     */
    @Override
    public void setNumBorrowed(int numBorrowed) {

    }

    /**
     * set number of item lent of an user.
     * @param numLent the number of item which is the user already lent
     */
    @Override
    public void setNumLent(int numLent) {

    }

    /**
     * set current trans number of an user.
     * @param currentTrans the number of transactions which is already finished
     */
    @Override
    public void setCurrentTrans(int currentTrans) {

    }

    /**
     * get current trans number
     * @return current trans number
     */
    @Override
    public int getCurrentTrans() {
        return 0;
    }

    /**
     * set the limit times of transaction
     * @param transactionLimit the limit times of transaction
     */
    @Override
    public void setTransactionLimit(int transactionLimit) {

    }

    /**
     * gets trans limit of this user
     * @return trans limit
     */
    @Override
    public int getTransactionLimit() {
        return 0;
    }

    /**
     * set whether or not this user is on vacation.
     * @param isOnVocation whether or not this user is on vacation.
     */
    @Override
    public void setOnVacation(boolean isOnVocation) {

    }

    /**
     * get whether or not this user is on vacation.
     * @return whether or not this user is on vacation.
     */
    @Override
    public boolean getOnVacation() {
        return false;
    }

    /**
     * cancels user's on vacation status
     */
    @Override
    public void cancelOnVacation() {

    }

    /**
     * gets user's home city
     * @return user's home city
     */
    @Override
    public String getHomeCity() {
        return null;
    }

    /**
     * sets user's home city
     * @param homeCity the home city of the user.
     */
    @Override
    public void setHomeCity(String homeCity) {

    }

    /**
     * gets start date of vacation
     * @return start date of vacation
     */
    @Override
    public Date getStartDateOfVacation() {
        return null;
    }

    /**
     * set the start date of vacation.
     * @param startDate the start date of vacation.
     */
    @Override
    public void setStartDateOfVacation(Date startDate) {

    }

    /**
     * gets end date of vacation
     * @return end date of vacation
     */
    @Override
    public Date getEndDateOfVacation() {
        return null;
    }

    /**
     * set end date of vacation
     * @param endDate the end date of vacation.
     */
    @Override
    public void setEndDateOfVacation(Date endDate) {

    }

    /**
     * gets credit
     * @return credit
     */
    @Override
    public int getCredit() {
        return 0;
    }

    /**
     * sets new credit for this user
     * @param credit new credit for this user
     */
    @Override
    public void setCredit(int credit) {

    }

    /**
     * gets whether user is initial admin
     * @return whether user is initial admin
     */
    @Override
    public boolean getIsInitialAdmin() {
        return false;
    }

    /**
     * sets the adminUser status whether is initial adminUser or not
     * @param isInitialAdmin the adminUser status whether is initial adminUser or not
     */
    @Override
    public void setIsInitialAdmin(boolean isInitialAdmin) {

    }

    /**
     * gets a string describing this user
     * @return a string describing this user
     */
    @Override
    public String toString() {
        return "{" +
                "userID='" + this.guestID + '\'' +
                ", userStatus='" + this.GUEST_STATUS +
                '}';
    }
}