package UseCases.MeetingSystemUseCase;
import Model.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The meetingSystem class stores all transaction, and process the real world trade, and
 * perform the return process of non-permanent part.
 * @author Qian Tang
 * @version Java1.8
 */
public interface IMeetingSystem {

    IMeetingSystem meetingSystem = new MeetingSystem() {
    };

    /**
     * gets instance meetingSystem
     * @return meetingSystem
     */
    static IMeetingSystem getInstance(){
        return meetingSystem;
    }

    /**
     * Method Used to createOneWayTran and store it.
     * @param startUser the user who start this OneWayTrans.
     * @param targetUser the user that the startUser want to trade with.
     * @param startUserWantTOTradeItem the Item startUser want to trade.
     * @param isPermanent indicate if this transaction is permanent or not.
     * @param tradeType the type of trade they want to make.
     */
    OneWayTransaction createOneWayTrans(IUserAccount startUser, IUserAccount targetUser,
                           ItemInterface startUserWantTOTradeItem, boolean isPermanent, String tradeType);

    /**
     * Method Used to createTwoWayTran and store it.
     * @param startUser the user who start this OneWayTrans.
     * @param targetUser the user that the startUser want to trade with.
     * @param startUserWantTOTradeItem the Item startUser want to trade.
     * @param isPermanent indicate if this transaction is permanent or not.
     * @param tradeType the type of trade they want to make.
     */

    TwoWayTransaction createTwoWayTrans(IUserAccount startUser, IUserAccount targetUser,
                                  ItemInterface startUserWantTOTradeItem, boolean isPermanent, String tradeType);

    /**
     * Method to perform OneWayTransaction
     * @param trans the transaction needed to process.
     */
    void performOneWayTrans(OneWayTransaction trans) throws Exception;

    /**
     * Method to perform TwoWayTransaction
     * @param trans  the transaction needed to process.
     */

    void performTwoWayTrans(TwoWayTransaction trans) throws Exception;

    /**
     * method to get all transactions.
     * @return an HashMap transactions.
     */
    HashMap<Integer, ITransaction> getAllTransactions();

    /**
     * method to removeAllInvalidTrans from
     */
    void removeAllInvalidTrans();

    /**
     * Method to perform OneWayTransaction's returning process
     * @param trans  the transaction needed to process.
     */
    void performOneWayTransReturn(OneWayTransaction trans);

    /**
     * Method to perform TwoWayTransaction's returning process
     * @param trans  the transaction needed to process.
     */
    void performTwoWayTransReturn(TwoWayTransaction trans);

    /**
     * Get the transaction by this transaction id.
     * @param transId the transaction id which we want to get
     * @return the value of ITransaction
     */
    ITransaction getTransById(int transId);

    /**
     * method to get all meetings.
     * @return map of all meetings.
     */
    Map<Integer, IMeeting> getAllMeetings();

    /**
     * method to get a meeting by its id.
     * @param id meeting id
     * @return the meeting want to get
     */
    IMeeting getMeetingById(int id);
    /**
     * method to create a new Meeting.
     * @param place place to meet
     * @param time time to meet
     * @param correspondTransId corresponding Transaction id.
     */
    void createMeeting(String place, String time, int correspondTransId);

    /**
     * method to get Correspond Meeting By TransId
     * @param id trans id
     * @return corresponding meeting
     */
    IMeeting getCorrespondMeetingByTransId(int id);

    void setAllMeetings(HashMap<Integer, IMeeting> allMeetings);

    void setAllTransactions(HashMap<Integer, ITransaction> allTransactions);

}
