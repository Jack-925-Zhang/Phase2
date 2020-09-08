package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * OneWayTransaction is an abstract class for TwoWayTransaction it contains unique trans id, who start the Transaction,
 * who is the target User, what Item the startUser want to trade,what kind of trade the startUser want to make "borrow",
 * whether it is a permanent trade, where and when to meet in the real world. Also how many times they have edit the
 * transaction whether they have confirmed the information for this transaction and do they confirm for their real world
 * trade and how long does this transaction valid.
 * @author Qian Tang
 * @version JAVA 1.8
 */

@DatabaseTable(tableName = "oneWayTransaction")
public abstract class OneWayTransaction  implements ITransaction {

    // for QueryBuilder to be able to find the fields
    public static final String ONE_OR_TWO_FIELD_NAME = "oneOrTwo";
    public static final String UNIQUE_ID_FIELD_NAME = "uniqueId";
    public static final String TRAN_ID_FIELD_NAME = "tranId";
    public static final String VALID_TIME_FIELD_NAME = "validTime";
    public static final String START_USER_FIELD_NAME = "startUser";
    public static final String TARGET_USER_FIELD_NAME = "targetUser";
    public static final String START_USER_WANT_TO_TRADE_ITEM_FIELD_NAME = "startUserWantToTradeItem";
    public static final String IS_PERMANENT_FIELD_NAME = "isPermanent";
    public static final String TRADE_TYPE_FIELD_NAME = "tradeType";
    public static final String RATE_FOR_ONE_WAY_ITEM_FIELD_NAME = "rateForOneWayItem";


    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = ONE_OR_TWO_FIELD_NAME)
    public int oneOrTwo;
    @DatabaseField(columnName = UNIQUE_ID_FIELD_NAME)
    public static int uniqueId;

    @DatabaseField(columnName = TRAN_ID_FIELD_NAME, canBeNull = false)
    public int tranId;
    @DatabaseField(columnName = VALID_TIME_FIELD_NAME)
    public int validTime;

    public IUserAccount startUser;
    public IUserAccount targetUser;
    public ItemInterface startUserWantToTradeItem;

    @DatabaseField(columnName = START_USER_FIELD_NAME)
    public String startUserId;

    @DatabaseField(columnName = TARGET_USER_FIELD_NAME)
    public String targetUserId;

    @DatabaseField(columnName = START_USER_WANT_TO_TRADE_ITEM_FIELD_NAME)
    public int itemId ;
    @DatabaseField(columnName = IS_PERMANENT_FIELD_NAME)
    public boolean isPermanent;
    @DatabaseField(columnName = TRADE_TYPE_FIELD_NAME)
    public String tradeType;
    @DatabaseField(columnName = RATE_FOR_ONE_WAY_ITEM_FIELD_NAME)
    public int rateForOneWayItem;

    /**
     * the default constructor of class OneWayTransaction
     */
    public OneWayTransaction(){}

    /**
     * Created the instance of one way transaction
     * @param startUser The user who placed the transaction request.
     * @param targetUser The user who start user want to trade with.
     * @param startUserWantToTradeItem The item that starterUser want to trade.
     * @param isPermanent indicate this transaction is permanent or temporary
     * @param tradeType indicate what kind of trade starter want to make, borrow one or lent one
     */

    public OneWayTransaction(IUserAccount startUser, IUserAccount targetUser,
                             ItemInterface startUserWantToTradeItem,
                             boolean isPermanent, String tradeType){
        this.oneOrTwo = 1;
        this.startUser = startUser;
        this.targetUser = targetUser;
        this.startUserWantToTradeItem = startUserWantToTradeItem;
        this.isPermanent = isPermanent;
        this.tradeType = tradeType;
        this.validTime = 30;
        this.tranId = uniqueId;
        uniqueId += 1;
        startUserId = startUser.getUserId();
        targetUserId = startUser.getUserId();
        itemId = startUserWantToTradeItem.getItemId();

    }

//    /**
//     * set how many times startUser have edit the transaction.
//     * @param editStartUser how many time startUser have edit the transaction
//     */
//    public void setEditStartUser(int editStartUser) {
//        this.editStartUser = editStartUser;
//    }
//
//    /**
//     * set how many times targetUser have edit the transaction.
//     * @param editTargetUser how many time targetUser have edit the transaction
//     */
//    public void setEditTargetUser(int editTargetUser) {
//        this.editTargetUser = editTargetUser;
//    }


    /**
     * Used to get startUser of this transaction.
     * @return startUser of this transaction.
     */
    @Override
    public IUserAccount getStartUser() {
        return startUser;
    }

    /**
     * Used to get targetUser of this transaction.
     * @return targetUser of this transaction.
     */
    @Override
    public IUserAccount getTargetUser() {
        return targetUser;
    }

    /**
     * Used to get the Item that startUser want to trade in this transaction.
     * @return the Item that startUser want to trade in this transaction.
     */
    @Override
    public ItemInterface getStartUserWantToTradeItem() {
        return startUserWantToTradeItem;
    }

//    /**
//     * Used to get the meeting time for them to trade.
//     * @return the meeting time for them to trade.
//     */
//    @Override
//    public String getMeetingTime() {
//        return meetingTime;
//    }
//
//    /**
//     * Used to set the new meeting time.
//     * @param meetingTime the new meeting time.
//     */
//    @Override
//    public void setMeetingTime(String meetingTime) {
//        this.meetingTime = meetingTime;
//    }
//
//    /**
//     * Used to get meetingPlace of this transaction.
//     * @return meetingPlace of this transaction.
//     */
//    @Override
//    public String getMeetingPlace() {
//        return meetingPlace;
//    }
//
//    /**
//     * Used to set the new meeting place.
//     * @param meetingPlace the new meeting place.
//     */
//    @Override
//    public void setMeetingPlace(String meetingPlace) {
//        this.meetingPlace = meetingPlace;
//    }

    /**
     * Used to get true iff this is a permanent transaction otherwise false.
     * @return true iff this is a permanent transaction.
     */
    @Override
    public boolean isPermanent() {
        return isPermanent;
    }

    /**
     * Used to set he new boolean to define if this is a permanent trade.
     * @param permanent the new boolean to define if this is a permanent trade.
     */
    @Override
    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    /**
     * Used to get the tradeType of starter want to make, "borrow" or "lend".
     * @return the tradeType of starter want to make, "borrow" or "lend".
     */
    @Override
    public String getTradeType() {
        return tradeType;
    }

    /**
     * Used to set new tradeType for startUser.
     * @param tradeType new tradeType for startUser.
     */
    @Override
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

//    /**
//     * Used to get how many times startUser has edited this transaction.
//     * @return how many times startUser has edited this transaction.
//     */
//    @Override
//    public int getEditStartUser() {
//        return editStartUser;
//    }

//    /**
//     * Used to get how many times targetUser has edited this transaction.
//     * @return how many times targetUser has edited this transaction.
//     */
//    @Override
//    public int getEditTargetUser() {
//        return editTargetUser;
//    }

//    /**
//     * Used to get real world trading success determiner for startUser.
//     * @return real world trading success determiner for startUser.
//     */
//
//    @Override
//    public boolean isMeetingFinishedStarter() {
//        return meetingFinishedStarter;
//    }
//
//    /**
//     * Used to set real world trading success setter for startUser.
//     * @param meetingFinishedStarter real world trading success setter for startUser.
//     */
//    @Override
//    public void setMeetingFinishedStarter(boolean meetingFinishedStarter) {
//        this.meetingFinishedStarter = meetingFinishedStarter;
//    }
//
//    /**
//     * Used to get real world trading success determiner for targetUser.
//     * @return real world trading success determiner for targetUser.
//     */
//    @Override
//    public boolean isMeetingFinishedTarget() {
//        return meetingFinishedTarget;
//    }
//
//    /**
//     * Used to set real world trading success setter for targetUser.
//     * @param meetingFinishedTarget real world trading success setter for targetUser.
//     */
//    @Override
//    public void setMeetingFinishedTarget(boolean meetingFinishedTarget) {
//        this.meetingFinishedTarget = meetingFinishedTarget;
//    }
//
//    /**
//     * Used to get true iff transaction has been confirmed by both user.
//     * @return true iff transaction has been confirmed by both user.
//     */
//
//    public boolean isConfirmTrans() {
//        return confirmTrans;
//    }
//
//    /**
//     * Used to set determiner for whether transaction has been confirmed by both user.
//     * @param confirmTrans determiner for whether transaction has been confirmed by both user.
//     */
//
//    @Override
//    public void setConfirmTrans(boolean confirmTrans) {
//        this.confirmTrans = confirmTrans;
//    }

    /**
     * Used to get the unique transaction id for this transaction.
     * @return the unique transaction id for this transaction.
     */
    @Override
    public int getTranId() {
        return tranId;
    }

    /**
     * get how long this transaction last.
     * @return how long this transaction last.
     */
    public int getValidTime() {
        return validTime;
    }

    /**
     * get whether it is a oneWay or twoWayTrans.
     * @return 1 for oneWayTrans, 2 for twoWayTrans.
     */
    public int getOneOrTwo() {
        return oneOrTwo;
    }

    /**
     *  set that is The Item startUser want to trade.
     * @param startUserWantToTradeItem new Item startUser want to trade.
     */
    public void setStartUserWantToTradeItem(ItemInterface startUserWantToTradeItem) {
        this.startUserWantToTradeItem = startUserWantToTradeItem;
    }

    /**
     * set up a tranId for transaction.
     * @param tranId new tranId for the transaction.
     */
    @Override
    public void setTranId(int tranId) {
        this.tranId = tranId;
    }

    /**
     * set up a validTime for transaction.
     * @param validTime new validTime for transaction.
     */
    @Override
    public void setValidTime(int validTime) {
        this.validTime = validTime;
    }

    /**
     * set up indicator to indicate if it is a oneWayTransaction or twoWay
     * @param oneOrTwo new indicator
     */
    public void setOneOrTwo(int oneOrTwo) {
        this.oneOrTwo = oneOrTwo;
    }

    /**
     * Used to String form for Transaction.
     * @return Print for Transaction.
     */
    @Override
    public String toString() {
        return "OneWayTransaction{" +
                "id=" + id +
                ", oneOrTwo=" + oneOrTwo +
                ", tranId=" + tranId +
                ", validTime=" + validTime +
                ", startUser=" + startUser +
                ", targetUser=" + targetUser +
                ", startUserWantToTradeItem=" + startUserWantToTradeItem +
                ", isPermanent=" + isPermanent +
                ", tradeType='" + tradeType + '\'' +
                ", rateForOneWayItem=" + rateForOneWayItem +
                '}';
    }

    /**
     * get the rate for oneWay item
     * @return the rate for oneWay item
     */
    public int getRateForOneWayItem() {
        return rateForOneWayItem;
    }

    /**
     * set up the rate for oneWay item
     * @param rateForOneWayItem the new rate for oneWay item
     */
    public void setRateForOneWayItem(int rateForOneWayItem) {
        this.rateForOneWayItem = rateForOneWayItem;
    }

    /**
     * get the rate for TwoWayItem
     *
     * @return the rate for TwoWayItem
     */
    public abstract int getRateForTwoWayItem();

    /**
     * set up the rate for twoWay item
     *
     * @param rateForTwoWayItem the new rate for twoWay item
     */
    public abstract void setRateForTwoWayItem(int rateForTwoWayItem);

    /**
     * gets id of start user
     * @return id of start user
     */
    @Override
    public String getStartUserId() {
        return startUserId;
    }

    /**
     * gets id of target user
     * @return id of target user
     */
    @Override
    public String getTargetUserId() {
        return targetUserId;
    }

    /**
     * gets id of trade item
     * @return id of trade item
     */
    @Override
    public int getItemId() {
        return itemId;
    }

    /**
     * sets start user
     * @param startUser start user
     */
    @Override
    public void setStartUser(IUserAccount startUser) {
        this.startUser = startUser;
    }

    /**
     * sets target user
     * @param targetUser target user.
     */
    @Override
    public void setTargetUser(IUserAccount targetUser) {
        this.targetUser = targetUser;
    }
}
