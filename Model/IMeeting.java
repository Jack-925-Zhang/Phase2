package Model;

import java.util.Map;

/**
 * this class is the interface for class Meeting
 * @author Qian Tang
 * @version 1.8
 */
public interface IMeeting {
    /**
     * method to meeting id.
     * @return meeting id.
     */
    int getMeetingId();

    /**
     * set up new id for meeting.
     * @param meetingId new id for meeting.
     */
    void setMeetingId(int meetingId);

    /**
     * method to get the place for real world meeting
     * @return place to meet
     */
    String getPlace();

    /**
     * method to set up a new place for real world meeting
     * @param place new place to meet
     */
    void setPlace(String place);

    /**
     * method to get the time for real world meeting
     * @return time they meet
     */
    String getTime();

    /**
     * method to set up a new time for real world meeting
     * @param time new time to meet.
     */
    void setTime(String time);

    /**
     * method to get the corresponding Transaction id.
     * @return transaction id
     */
    int getCorrespondTransId();

    /**
     * set up new corresponding Transaction id.
     * @param correspondTransId new trans id.
     */
    void setCorrespondTransId(int correspondTransId);

    /**
     * method to get how many time start user have edited it.
     * @return times that starter user edited.
     */
    int getEditStartUser();


    /**
     * method to get how many time target user have edited it.
     * @return times that target user edited.
     */
    int getEditTargetUser();

    void setEditStartUser(int editStartUser);

    void setEditTargetUser(int editTargetUser);
    /**
     * method to help to update the time start user changes this meeting.
     */
    void startUserEditSuccess();

    /**
     *  method to help to update the time target user changes this meeting.
     */
    void targetUserEditSuccess();

    /**
     * Used to get true iff transaction has been confirmed by both user.
     * @return true iff transaction has been confirmed by both user.
     */

    boolean isConfirmTrans();

    /**
     * Used to set determiner for whether transaction has been confirmed by both user.
     * @param confirmTrans determiner for whether transaction has been confirmed by both user.
     */
    void setConfirmTrans(boolean confirmTrans);

    /**
     * Used to get real world trading success determiner for startUser.
     * @return real world trading success determiner for startUser.
     */
    boolean isMeetingFinishedStarter();

    /**
     * Used to set real world trading success setter for startUser.
     * @param meetingFinishedStarter real world trading success setter for startUser.
     */
    void setMeetingFinishedStarter(boolean meetingFinishedStarter);

    /**
     * Used to get real world trading success determiner for targetUser.
     * @return real world trading success determiner for targetUser.
     */
    boolean isMeetingFinishedTarget();

    /**
     * Used to set real world trading success setter for targetUser.
     * @param meetingFinishedTarget real world trading success setter for targetUser.
     */
    void setMeetingFinishedTarget(boolean meetingFinishedTarget);

    /**
     * get the indicator whether if this Transaction is updated.
     * @return whether this transaction is updated.
     */
    boolean isUpdated();

    /**
     * set the indicator whether if this Transaction is updated.
     * @param updated new status for this transaction
     */
    void setUpdated(boolean updated);


    /**
     * method to get the id for this meeting
     * @return id for the meeting
     */
    int getId();

    /**
     * method to set the id for this meeting
     * @param id new id for this meeting
     */
    void setId(int id);

}
