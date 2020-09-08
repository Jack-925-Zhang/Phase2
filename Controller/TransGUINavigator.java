package Controller;

import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;

/**
 * navigates the methods from ITransaction
 * @author Hongyu Chen
 * @version 1.8
 */
public class TransGUINavigator {

    private static final TransGUINavigator transGUINavigator = new TransGUINavigator();

    /**
     * the constructor for class TransGUINavigator
     */
    public TransGUINavigator() {
    }

    /**
     * gets the attribute TRANS_GUI_NAVIGATOR
     * @return TRANS_GUI_NAVIGATOR
     */
    public static TransGUINavigator getInstance(){
        return transGUINavigator;
    }

    /**
     * This method navigates the method of printing one transaction from
     * the class ITransaction. When one transaction needs to be printed,
     * the class MyTradesMenu calls this method.
     *
     * @param transaction The transaction needs to be printed.
     * @return The print of transaction.
     */
    public String transToStringNavigator(ITransaction transaction) {
        return transaction.toString();
    }

    /**
     * This method navigates the method of getting the start user of
     * one transaction from the class ITransaction. When the start user
     * of one transaction is needed, the class MyTradesMenu calls this method.
     *
     * @param transaction The transaction whose start user is needed.
     * @return The start user of transaction.
     */
    public IUserAccount getStartUserNavigator(ITransaction transaction) {
        return transaction.getStartUser();
    }

    /**
     * This method navigates the method of getting the target user of
     * one transaction from the class ITransaction. When the target user
     * of one transaction is needed, the class MyTradesMenu calls this method.
     *
     * @param transaction The transaction whose start user is needed.
     * @return The start user of transaction.
     */
    public IUserAccount getTargetUserNavigator(ITransaction transaction) {
        return transaction.getTargetUser();
    }

    /**
     * This method navigates the method of get startUserWantToTradeItem from the class
     * ItemInterface.
     * @param transaction the transaction support the item which is start user want to trade.
     * @return the value of item
     */
    public ItemInterface getStartUserWantToTradeItemNavigator(ITransaction transaction) {
        return transaction.getStartUserWantToTradeItem();
    }

    /**
     * This method navigates the method of get the transaction id of this transaction
     * @param transaction the transaction which is wanted to know the id
     * @return the value of this transaction id
     */
    public int getTransIdNavigator(ITransaction transaction) {
        return transaction.getTranId();
    }

}
