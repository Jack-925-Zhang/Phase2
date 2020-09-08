package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * TwoWayTransaction contains all information for a TwoWayTransaction, and it inherent the OneWayTransaction class
 * @author Qian Tang
 */

@DatabaseTable(tableName = "twoWayTransaction")
public class TwoWayTransaction extends OneWayTransaction {

    // for QueryBuilder to be able to find the fields
    public static final String TARGET_USER_WANT_TO_TRADE_ITEM_FIELD_NAME = "targetUserWantToTradItem";
    public static final String TRADE_TYPE_FOR_TARGET_FIELD_NAME = "tradeTypeForTarget";
    public static final String RATE_FOR_TWO_WAY_ITEM_FIELD_NAME = "rateForTwoWayItem";


    public ItemInterface targetUserWantToTradItem;

    @DatabaseField(columnName = TARGET_USER_WANT_TO_TRADE_ITEM_FIELD_NAME)
    public int itemId = targetUserWantToTradItem.getItemId();

    @DatabaseField(columnName = TRADE_TYPE_FOR_TARGET_FIELD_NAME)
    public String tradeTypeForTarget;

    @DatabaseField(columnName = RATE_FOR_TWO_WAY_ITEM_FIELD_NAME)
    private int rateForTwoWayItem;

    public TwoWayTransaction(){}

    /**
     * Created the instance of two way transaction
     * @param startUser The user who placed the transaction request.
     * @param targetUser The user who start user want to trade with.
     * @param startUserWantTOTradeItem The item that starterUser want to trade.
     * @param isPermanent indicate this transaction is permanent or temporary
     * @param tradeType indicate what kind of trade starter want to make, borrow one or lent one
     * @param targetUserWantToTradItem the Item targetUser want ot trade.
     */

    public TwoWayTransaction(IUserAccount startUser, IUserAccount targetUser,
                             ItemInterface startUserWantTOTradeItem, boolean isPermanent, String tradeType,
                             ItemInterface targetUserWantToTradItem) {
        super(startUser, targetUser, startUserWantTOTradeItem, isPermanent, tradeType);
        this.targetUserWantToTradItem = targetUserWantToTradItem;
    }

    /**
     * Used to get the Item targetUser want to trade.
     * @return the Item targetUser want to trade.
     */
    public ItemInterface getTargetUserWantToTradItem() {
        return targetUserWantToTradItem;
    }

    /**
     * Used to set the Item targetUser want to trade.
     * @param targetUserWantToTradItem set the Item targetUser want to trade.
     */

    public void setTargetUserWantToTradeItem(ItemInterface targetUserWantToTradItem) {
        this.targetUserWantToTradItem = targetUserWantToTradItem;
    }

    /**
     * Used to get the trade type targetUser want to make.
     * @return the trade type targetUser want to make.
     */

    public String getTradeTypeForTarget() {
        return tradeTypeForTarget;
    }

    /**
     * Used to set the trade type targetUser want to make.
     * @param tradeTypeForTarget the trade type targetUser want to make.
     */

    public void setTradeTypeForTarget(String tradeTypeForTarget) {
        this.tradeTypeForTarget = tradeTypeForTarget;
    }

    /**
     * Used to get the string form for TwoWayTransaction.
     * @return the String form for TwoWayTransaction.
     */

    @Override
    public String toString() {
        return "TwoWayTransaction{" +
                ", targetUserWantToTradItem=" + targetUserWantToTradItem +
                ", tradeTypeForTarget='" + tradeTypeForTarget + '\'' +
                ", rateForTwoWayItem=" + rateForTwoWayItem +
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
     * get the rate for TwoWayItem
     * @return  the rate for TwoWayItem
     */
    public int getRateForTwoWayItem() {
        return rateForTwoWayItem;
    }

    /**
     * set up the rate for twoWay item
     * @param rateForTwoWayItem the new rate for twoWay item
     */
    public void setRateForTwoWayItem(int rateForTwoWayItem) {
        this.rateForTwoWayItem = rateForTwoWayItem;
    }

}
