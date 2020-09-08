package Controller;

import GateWay.*;
import Model.*;
import UseCases.MeetingSystemUseCase.IMeetingSystem;
import UseCases.MeetingSystemUseCase.MeetingSystem;

/**
 * navigates the methods from IMeetingSystem
 * @author Hongyu Chen
 * @version 1.8
 */
public class MeetingSystemGUINavigator {

    private final IMeetingSystem meetingSystem;
    private static final MeetingSystemGUINavigator meetingSystemGUINavigator = new MeetingSystemGUINavigator();
    private final IDataUpdater dataUpdater;
    private final IDataInsertion dataInsertion;
    private final IDataDeleter dataDeleter;

    /**
     * the constructor for class MeetingSystemGUINavigator
     */
    public MeetingSystemGUINavigator() {
        meetingSystem = MeetingSystem.getInstance();
        dataUpdater = new DataUpdater();
        dataInsertion = new DataInsertion();
        dataDeleter = new DataDeleter();
    }

    /**
     * gets the attribute MEETING_SYSTEM_GUI_NAVIGATOR
     * @return MEETING_SYSTEM_GUI_NAVIGATOR
     */
    public static MeetingSystemGUINavigator getInstance(){
        return meetingSystemGUINavigator;
    }

    /**
     * This method navigates the method of creating one way transaction from the class OneWayTransaction
     * @param startUser the user who start the transaction
     * @param targetUser the user who is the target for this transaction
     * @param startUserWantTOTradeItem The item that starterUser want to trade.
     * @param isPermanent indicate this transaction is permanent or temporary
     * @param tradeType indicate what kind of trade starter want to make, borrow one or lent one
     * @return the value of OneWayTransaction
     */
    public OneWayTransaction createOneWayTransNavigator(IUserAccount startUser, IUserAccount targetUser,
                                                        ItemInterface startUserWantTOTradeItem,
                                                        boolean isPermanent, String tradeType) throws Exception {
        OneWayTransaction oneWayTransaction =  meetingSystem.createOneWayTrans(
                startUser, targetUser, startUserWantTOTradeItem, isPermanent, tradeType);
        dataInsertion.oneWayTransactionsInsertion(oneWayTransaction);
        return oneWayTransaction;
    }

    /**
     * This method navigates the method of creating two way transaction from the class TwoWayTransaction
     * @param startUser The user who placed the transaction request.
     * @param targetUser The user who start user want to trade with.
     * @param startUserWantTOTradeItem The item that starterUser want to trade.
     * @param isPermanent indicate this transaction is permanent or temporary
     * @param tradeType indicate what kind of trade starter want to make, borrow one or lent one
     * @return the value of TwoWayTransaction
     */
    public TwoWayTransaction createTwoWayTransNavigator(IUserAccount startUser, IUserAccount targetUser,
                                                        ItemInterface startUserWantTOTradeItem,
                                                        boolean isPermanent, String tradeType) throws Exception {
        TwoWayTransaction twoWayTransaction =  meetingSystem.createTwoWayTrans(
                startUser, targetUser, startUserWantTOTradeItem, isPermanent, tradeType);
        dataInsertion.twoWayTransactionsInsertion(twoWayTransaction);
        return twoWayTransaction;
    }

    /**
     * navigates the method getting trans by transId
     * @param transId the id of the trans needed
     * @return the trans needed
     */
    public ITransaction getTransByIdNavigator(int transId) {
        return meetingSystem.getTransById(transId);
    }

    /**
     * This method navigates the method of knowing whether one transaction
     * is updated or not from the class ITransaction. When the information
     * about whether one transaction is updated or not is needed, the class
     * MyTradesMenu calls this method.
     *
     * @param meeting The meeting whose information about whether is updated or not is needed.
     * @return Whether transaction is updated or not.
     */
    public boolean isUpdatedNavigator(IMeeting meeting) throws Exception {
        dataUpdater.meetingsUpdater(meeting);
        return meeting.isUpdated();
    }

    /**
     * gets corresponding meeting by transaction id
     * @param transId transaction id
     * @return corresponding meeting
     */
    public IMeeting getMeetingByTransIdNavigator(int transId) throws Exception {
        IMeeting meeting = meetingSystem.getCorrespondMeetingByTransId(transId);
        dataDeleter.meetingsDeleter((Meeting) meeting);
        dataInsertion.meetingsInsertion(meeting);
        return meeting;
    }

}
