package UseCases;

import Model.IMeeting;
import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;

/**
 * stores the operation of user's transaction
 * @author Qian Tang
 * @version 1.8
 */
public interface IUserTransactionOperation {

    /**
     * Add the new invited transaction into this user's list
     * @param transaction the new invited transaction
     */
    void addInvitedTrans(ITransaction transaction, String userId);

    /**
     * Remove the new invited transaction into this user's list
     * @param transaction the invited transaction which is needed to be removed
     */
    void removeInvitedTrans(ITransaction transaction, String userId);

    /**
     * Used to receive transaction from one user.
     * @param meeting the transaction information which is used for two user.
     */
    void receiveTransaction(IMeeting meeting, String userId);


    /**
     * Used to edit the specific information about transaction.
     * @param trans the transaction information which is used for two user.
     * @param tradeItem the item which is users want to trade
     * @param time the meeting time of trading
     * @param place the meeting type of trading
     * @param tradeType the trading type of this transaction
     */
    void editTransaction(ITransaction trans, ItemInterface tradeItem, String time, String place
            , String tradeType, IUserAccount user);

    /**
     * Send the one way transaction information to target user.
     * @param target the user who start user want to trade with.
     * @param trans  the one way transaction between this user and target user
     */
    void sendTransaction(IUserAccount target, ITransaction trans, IUserAccount user);

    /**
     * Confirm this transaction and change the value of confirmRealWorldTrade
     * @param trans the transaction information which is used for two user.
     */
    void confirmRealWorldTrade(IUserAccount user, ITransaction trans);

    /**
     * Confirm this transaction and change the value of confirmTrans
     * @param trans the transaction information which is used for two user.

     */
    void confirmTrans(ITransaction trans);

    void declineTrans(ITransaction trans) ;

    /**
     * Add the transaction which is confirmed into this user confirmedTransaction list
     * @param transId the transaction which is confirmed by user and he/she want to add
     */
    void addConfirmedTransaction(int transId, String userId);

    /**
     * Remove the transaction which is confirmed into this user confirmedTransaction list
     * @param transId the transaction which is confirmed by user and he/she want to remove
     */
    void removeConfirmedTransaction(int transId, String userId);

    /**
     * this method is used for targetUser to perform final confirm of the transaction.
     * @param trans the transaction need to get a final confirm.
     */
    void finalConfirm(ITransaction trans,boolean confirmation, String userId);

    void addUserItemRating(IUserAccount user, int ItemId, int rating);
}
