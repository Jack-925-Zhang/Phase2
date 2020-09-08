package UseCases.UserUseCase;

import Model.*;

import java.util.HashMap;
import java.util.List;

/**
 * This class is the interface implemented by UserOperation
 *
 * @author Tianyan Zhou
 * @version 1.8
 */
public interface IUserOperation {

    /**
     * gets user's id
     * @return user's id
     */
    String getUserId();

    /**
     * Get the user status
     * @return the value of user status
     */
    String getUserStatus();

    /**
     * This method shows the list of one user would like to borrow from others.
     *
     * @return wishToBorrow
     */
    List<ItemInterface> getWishToBorrow();

    /**
     * This method sets list the user would like to borrow from others
     *
     * @param wishToBorrow items the users would like to borrow from others.
     */
    void setWishToBorrow(List<ItemInterface> wishToBorrow);

    /**
     * This method shows the list of one user would like to lend to others.
     *
     * @return wishToLend
     */
    List<ItemInterface> getWishToLend();

    /**
     * This method sets list the user would like to lend to others
     *
     * @param wishToLend items the users would like to lend to others.
     */
    void setWishToLend(List<ItemInterface> wishToLend);

    /**
     * This method shows the current items the user is borrowing.
     *
     * @return currentBorrowing
     */
    List<ItemInterface> getCurrentBorrowing();

    /**
     * This method sets the current items the user is borrowing.
     *
     * @param currentBorrowing items the user is currently borrowing
     */
    void setCurrentBorrowing(List<ItemInterface> currentBorrowing);

    /**
     * This method shows the current items the user is lending.
     *
     * @return currentLending
     */
    List<ItemInterface> getCurrentLending();

    /**
     * This method sets the current items the user is lending.
     *
     * @param currentLending items the user is currently lending to others.
     */
    void setCurrentLending(List<ItemInterface> currentLending);

    /**
     * This method shows  most recent three items that they traded in a one-way or two-way trade of one user.
     *
     * @return threeItems
     */
    List<ItemInterface> getThreeItems();

    /**
     * This method sets the most recent three items that they traded in a one-way or two-way trade of one user.
     *
     * @param threeItems the most recent three items that the user trades
     */
    void setThreeItems(List<ItemInterface> threeItems);

    /**
     * This method shows the top three most frequent trading partners of one user.
     *
     * @return tradingPartners
     */
    HashMap<String, Integer> getTradingPartners();

    /**
     * This method sets the trading partners of one user.
     *
     * @param tradingPartners the trading partners of one user.
     */
    void setTradingPartners(HashMap<String, Integer> tradingPartners);

    /**
     * sets notifications
     * @param notifications notifications
     */
    void setNotification(List<String> notifications);

    /**
     * Get the notifications for this user
     * @return the notifications of this user
     */
    List<String> getNotifications();

    /**
     * Get the transactions which is already confirmed
     * @return the transactions which is already confirmed
     */
    List<Integer> getConfirmedTransaction();

    /**
     * Set the confirmedTransaction for this user
     * @param confirmedTransaction the confirmed transactions for this user
     */
    void setConfirmedTransaction(List<Integer> confirmedTransaction);

    /**
     * Get the inviteTransNewMessages for this user
     * @return the inviteTransNewMessages of this user
     */
    HashMap<Integer, String> getInviteTransNewMessages();

    /**
     * sets invite trans new messages
     * @param inviteTransNewMessages invite trans new messages
     */
    void setInviteTransNewMessages(HashMap<Integer, String> inviteTransNewMessages);

    /**
     * Get the invited transactions from other users and stored by its' transaction id.
     * @return the invited transactions from other users
     */
    List<Integer> getInvitedTrans();

    /**
     * Set the invitedTrans for this user
     * @param invitedTrans the invited transactions for this user
     */
    void setInvitedTrans(List<Integer> invitedTrans);

    /**
     * This method gets the top three most frequent trading partners and their corresponding total times
     * of transaction
     * @return the top three most frequent trading partners and total trading times
     */
    HashMap<String, Integer> getThreePartners();

    /**
     * This method sets the top three most frequent trading partners and their corresponding total times
     * of transaction
     * @param threePartners top three trading partners and the times of transaction
     */
    void setThreePartners(HashMap<String, Integer> threePartners);

    /**
     *This method shows the rating of item by item id, given by the user.
     * @return UserItemRating
     */
    HashMap<Integer, Integer> getUserItemRating();

    /**
     * @param userItemRating the hashmap
     */
    void setUserItemRating(HashMap<Integer, Integer> userItemRating);

    /**
     * method to get the list of completed transactions;
     * @return completeTransaction
     */
    List<Integer> getCompleteTransaction();

    /**
     * change completeTransaction.
     * @param completeTransaction the user has complete the transaction.
     */
    void setCompleteTransaction(List<Integer> completeTransaction);

    /**
     * gets wish to borrow list
     * @return wish to borrow list
     */
    String getWishToBorrowList();

    /**
     * gets wish to lend list
     * @return wish to lend list
     */
    String getWishToLendList();

    /**
     * gets three items list
     * @return three items list
     */
    String getThreeItemsLists();

    /**
     * gets key for trading partners
     * @return key for trading partners
     */
    String getTradingPartnersKey();

    /**
     * gets value for trading partners
     * @return value for trading partners
     */
    String getTradingPartnersValue();

    /**
     * gets current borrowing list
     * @return current borrowing list
     */
    String getCurrentBorrowingLists();

    /**
     * gets current lending list
     * @return current lending list
     */
    String getCurrentLendingList();

    /**
     * gets notifications lists
     * @return notification lists
     */
    String getNotificationsList();

    /**
     * gets key for invite trans new messages
     * @return key for invite trans new messages
     */
    String getInviteTransNewMessagesKey();

    /**
     * gets value for invite trans new messages
     * @return value for invite trans new messages
     */
    String getInviteTransNewMessagesValue();

    /**
     * gets confirmed transactions list
     * @return confirmed transactions list
     */
    String getConfirmedTransactionsList();

    /**
     * gets invited trans list
     * @return invited trans list
     */
    String getInvitedTransList();

    /**
     * gets key for three partners
     * @return key for three partners
     */
    String getThreePartnersKey();

    /**
     * gets value for three partners
     * @return value for three partners
     */
    String getThreePartnersValue();

    /**
     * gets complete transaction list
     * @return complete transaction list
     */
    String getCompleteTransactionsList();

    /**
     * gets key for user item rating
     * @return key for user item rating
     */
    String getUserItemRatingKeys();

    /**
     * gets value for user item rating
     * @return value for user item rating
     */
    String getUserItemRatingValues();
}

