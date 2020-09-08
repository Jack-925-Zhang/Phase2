package Model;

/**
 * interface of class Transaction
 * @author Qian Tang
 * @version 1.8
 */
public interface ITransaction{
    /**
     * Used to get startUser of this transaction.
     * @return startUser of this transaction.
     */
    IUserAccount getStartUser();

    /**
     * Used to get targetUser of this transaction.
     * @return targetUser of this transaction.
     */
    IUserAccount getTargetUser();

    /**
     * Used to get the Item that startUser want to trade in this transaction.
     * @return the Item that startUser want to trade in this transaction.
     */
    ItemInterface getStartUserWantToTradeItem();

    /**
     *  set that is The Item startUser want to trade.
     * @param startUserWantToTradeItem new Item startUser want to trade.
     */
    void setStartUserWantToTradeItem(ItemInterface startUserWantToTradeItem);

//    /**
//     * Used to get the meeting time for them to trade.
//     * @return the meeting time for them to trade.
//     */
//    String getMeetingTime();
//
//    /**
//     * Used to set the new meeting time.
//     * @param meetingTime the new meeting time.
//     */
//    void setMeetingTime(String meetingTime);
//
//    /**
//     * Used to get meetingPlace of this transaction.
//     * @return meetingPlace of this transaction.
//     */
//    String getMeetingPlace();
//
//    /**
//     * Used to set the new meeting place.
//     * @param meetingPlace the new meeting place.
//     */
//    void setMeetingPlace(String meetingPlace);

    /**
     * Used to get true iff this is a permanent transaction otherwise false.
     * @return true iff this is a permanent transaction.
     */
    boolean isPermanent();

    /**
     * Used to set he new boolean to define if this is a permanent trade.
     * @param permanent the new boolean to define if this is a permanent trade.
     */
    void setPermanent(boolean permanent);

    /**
     * Used to get the tradeType of starter want to make, "borrow" or "lend".
     * @return the tradeType of starter want to make, "borrow" or "lend".
     */
    String getTradeType();

    /**
     * Used to set new tradeType for startUser.
     * @param tradeType new tradeType for startUser.
     */
    void setTradeType(String tradeType);

//    /**
//     * Used to get how many times startUser has edited this transaction.
//     * @return how many times startUser has edited this transaction.
//     */
//    int getEditStartUser();
//
//    /**
//     * Used to get how many times targetUser has edited this transaction.
//     * @return how many times targetUser has edited this transaction.
//     */
//    int getEditTargetUser();

//    /**
//     * Used to get real world trading success determiner for startUser.
//     * @return real world trading success determiner for startUser.
//     */
//
//    boolean isMeetingFinishedStarter();
//
//    /**
//     * Used to set real world trading success setter for startUser.
//     * @param meetingFinishedStarter real world trading success setter for startUser.
//     */
//    void setMeetingFinishedStarter(boolean meetingFinishedStarter);
//
//    /**
//     * Used to get real world trading success determiner for targetUser.
//     * @return real world trading success determiner for targetUser.
//     */
//    boolean isMeetingFinishedTarget();
//
//    /**
//     * Used to set real world trading success setter for targetUser.
//     * @param meetingFinishedTarget real world trading success setter for targetUser.
//     */
//    void setMeetingFinishedTarget(boolean meetingFinishedTarget);

    /**
     * Used to String form for Transaction.
     * @return Print for Transaction.
     */
    String toString();

    /**
     * Used to get the Item targetUser want to trade.
     * @return the Item targetUser want to trade.
     */
    ItemInterface getTargetUserWantToTradItem();

//    /**
//     * Get confirm transaction information whether true or false.
//     * @return the result of confirm transaction information
//     */
//    boolean isConfirmTrans();
//
//    /**
//     * Set the confirm transaction information whether true or false.
//     * @param confirmTrans the decision of confirm transaction information
//     */
//    void setConfirmTrans(boolean confirmTrans);

    /**
     * Used to set the Item targetUser want to trade.
     * @param targetUserWantToTradItem set the Item targetUser want to trade.
     */
    void setTargetUserWantToTradeItem(ItemInterface targetUserWantToTradItem);

    /**
     * Used to get trade type from targetUser
     * @return the trade type targetUser want to make.
     */
    String getTradeTypeForTarget();

    /**
     * Used to set the trade type targetUser want to make.
     * @param tradeTypeForTarget the trade type targetUser want to make.
     */
    void setTradeTypeForTarget(String tradeTypeForTarget);

    /**
     * Used to get the unique transaction id for this transaction.
     * @return the unique transaction id for this transaction.
     */
    int getTranId();

    /**
     * get how long this transaction last.
     * @return how long this transaction last.
     */
    int getValidTime();

    /**
     * get whether it is a oneWay or twoWayTrans.
     * @return 1 for oneWayTrans, 2 for twoWayTrans.
     */
    int getOneOrTwo();

//    /**
//     * set how many times startUser have edit the transaction.
//     * @param editStartUser how many time startUser have edit the transaction
//     */
//    void setEditStartUser(int editStartUser);
//
//    /**
//     * set how many times targetUser have edit the transaction.
//     * @param editTargetUser how many time targetUser have edit the transaction
//     */
//    void setEditTargetUser(int editTargetUser);
//    /**
//     * used to indicate how the data is stored.
//     * @return the form that the data is store,
//     */
//    String savedData();

    /**
     * set up a tranId for transaction.
     * @param tranId new tranId for the transaction.
     */
    void setTranId(int tranId);

    /**
     * set up a validTime for transaction.
     * @param validTime new validTime for transaction.
     */
    void setValidTime(int validTime);

    /**
     * set up indicator to indicate if it is a oneWayTransaction or twoWay
     * @param oneOrTwo new indicator
     */
    void setOneOrTwo(int oneOrTwo);

//    /**
//     * get the indicator whether if this Transaction is updated.
//     * @return whether this transaction is updated.
//     */
//    boolean isUpdated();
//    /**
//     * set the indicator whether if this Transaction is updated.
//     * @param updated new status for this transaction
//     */
//    void setUpdated(boolean updated);

    /**
     * get the rate for oneWay item
     * @return the rate for oneWay item
     */
    int getRateForOneWayItem();

    /**
     * set up the rate for oneWay item
     * @param rateForOneWayItem the new rate for oneWay item
     */
    void setRateForOneWayItem(int rateForOneWayItem);

    /**
     * get the rate for TwoWayItem
     * @return  the rate for TwoWayItem
     */
    int getRateForTwoWayItem();

    /**
     * set up the rate for twoWay item
     * @param rateForTwoWayItem the new rate for twoWay item
     */
    void setRateForTwoWayItem(int rateForTwoWayItem);

    /**
     * gets id of start user
     * @return id of start user
     */
    String getStartUserId();

    /**
     * gets id of target user
     * @return id of target user
     */
    String getTargetUserId();

    /**
     * gets id of trade item
     * @return id of trade item
     */
    int getItemId();

    /**
     * method to set up a new start user for this transaction
     * @param startUser start user
     */
    void setStartUser(IUserAccount startUser);

    /**
     * method to set up a target user for this transaction
     * @param targetUser target user.
     */
    void setTargetUser(IUserAccount targetUser);
}
