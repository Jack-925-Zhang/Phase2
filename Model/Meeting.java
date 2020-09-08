package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * @author Qian Tang
 * @version 1.8
 */
@DatabaseTable(tableName = "meeting")
public class Meeting implements IMeeting{
    // for QueryBuilder to be able to find the fields
    public static final String Meeting_ID_FIELD_NAME = "meetingId";
    public static final String PLACE_FIELD_NAME = "place";
    public static final String TIME_FIELD_NAME = "time";
    public static final String CORRESPOND_TRANS_ID_FIELD_NAME ="correspondTransId";
    public static final String EDIT_START_USER_FIELD_NAME = "editStartUser";
    public static final String EDIT_TARGET_USER_FIELD_NAME = "editTargetUser";

    public static final String CONFIRM_TRANS_FIELD_NAME = "confirmTrans";
    public static final String MEETING_FINISHED_STARTER_FIELD_NAME = "meetingFinishedStarter";
    public static final String MEETING_FINISHED_TARGET_FIELD_NAME = "meetingFinishedTarget";
    public static final String IS_UPDATED_FIELD_NAME = "isUpdated";

    public static int uniqueId;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = Meeting_ID_FIELD_NAME)
    private int meetingId;

    @DatabaseField(columnName = PLACE_FIELD_NAME)
    private String place;

    @DatabaseField(columnName = TIME_FIELD_NAME)
    private String time;

    @DatabaseField(columnName = CORRESPOND_TRANS_ID_FIELD_NAME)
    private int correspondTransId;

    @DatabaseField(columnName = EDIT_START_USER_FIELD_NAME)
    private int editStartUser;

    @DatabaseField(columnName = EDIT_TARGET_USER_FIELD_NAME)
    private int editTargetUser;


    @DatabaseField(columnName = CONFIRM_TRANS_FIELD_NAME)
    public boolean confirmTrans;

    @DatabaseField(columnName = MEETING_FINISHED_STARTER_FIELD_NAME)
    public boolean meetingFinishedStarter;
    @DatabaseField(columnName = MEETING_FINISHED_TARGET_FIELD_NAME)
    public boolean meetingFinishedTarget;

    @DatabaseField(columnName = IS_UPDATED_FIELD_NAME)
    public boolean isUpdated;

    /**
     * the default constructor of class Meeting
     */
    public Meeting(){}

    /**
     * the constructor of class Meeting
     * @param place meeting place
     * @param time meeting time
     * @param correspondTransId the id of the corresponding transaction of this meeting
     */
    public Meeting(String place, String time, int correspondTransId){
        this.meetingId = uniqueId;
        uniqueId ++;
        this.place = place;
        this.time = time;
        this.correspondTransId = correspondTransId;
        isUpdated = false;

    }

    /**
     * method to meeting id.
     * @return meeting id.
     */
    @Override
    public int getMeetingId() {
        return meetingId;
    }

    /**
     * set up new id for meeting.
     * @param meetingId new id for meeting.
     */
    @Override
    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    /**
     * method to get the place for real world meeting
     * @return place to meet
     */
    @Override
    public String getPlace() {
        return place;
    }


    /**
     * method to set up a new place for real world meeting
     * @param place new place to meet
     */
    @Override
    public void setPlace(String place) {
        this.place = place;
    }


    /**
     * method to get the time for real world meeting
     * @return time they meet
     */
    @Override
    public String getTime() {
        return time;
    }


    /**
     * method to set up a new time for real world meeting
     * @param time new time to meet.
     */
    @Override
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * method to get the corresponding Transaction id.
     * @return transaction id
     */
    @Override
    public int getCorrespondTransId() {
        return correspondTransId;
    }

    /**
     * set up new corresponding Transaction id.
     * @param correspondTransId new trans id.
     */
    @Override
    public void setCorrespondTransId(int correspondTransId) {
        this.correspondTransId = correspondTransId;
    }


    /**
     * method to get how many time start user have edited it.
     * @return times that starter user edited.
     */
    @Override
    public int getEditStartUser() {
        return editStartUser;
    }

    @Override
    public void setEditStartUser(int editStartUser) {
        this.editStartUser = editStartUser;
    }
    @Override
    public void setEditTargetUser(int editTargetUser) {
        this.editTargetUser = editTargetUser;
    }

    /**
     * method to get how many time target user have edited it.
     * @return times that target user edited.
     */
    @Override
    public int getEditTargetUser() {
        return editTargetUser;
    }


    /**
     * method to help to update the time start user changes this meeting.
     */
    @Override
    public void startUserEditSuccess(){
        this.editStartUser ++;
    }

    /**
     *  method to help to update the time target user changes this meeting.
     */
    @Override
    public void targetUserEditSuccess(){
        this.editTargetUser ++;
    }


    /**
     * Used to get true iff transaction has been confirmed by both user.
     * @return true iff transaction has been confirmed by both user.
     */
    @Override
    public boolean isConfirmTrans() {
        return confirmTrans;
    }

    /**
     * Used to set determiner for whether transaction has been confirmed by both user.
     * @param confirmTrans determiner for whether transaction has been confirmed by both user.
     */

    @Override
    public void setConfirmTrans(boolean confirmTrans) {
        this.confirmTrans = confirmTrans;
    }

    /**
     * Used to get real world trading success determiner for startUser.
     * @return real world trading success determiner for startUser.
     */

    @Override
    public boolean isMeetingFinishedStarter() {
        return meetingFinishedStarter;
    }

    /**
     * Used to set real world trading success setter for startUser.
     * @param meetingFinishedStarter real world trading success setter for startUser.
     */
    @Override
    public void setMeetingFinishedStarter(boolean meetingFinishedStarter) {
        this.meetingFinishedStarter = meetingFinishedStarter;
    }

    /**
     * Used to get real world trading success determiner for targetUser.
     * @return real world trading success determiner for targetUser.
     */
    @Override
    public boolean isMeetingFinishedTarget() {
        return meetingFinishedTarget;
    }

    /**
     * Used to set real world trading success setter for targetUser.
     * @param meetingFinishedTarget real world trading success setter for targetUser.
     */
    @Override
    public void setMeetingFinishedTarget(boolean meetingFinishedTarget) {
        this.meetingFinishedTarget = meetingFinishedTarget;
    }
    /**
     * get the indicator whether if this Transaction is updated.
     * @return whether this transaction is updated.
     */
    @Override
    public boolean isUpdated() {
        return isUpdated;
    }

    /**
     * set the indicator whether if this Transaction is updated.
     * @param updated new status for this transaction
     */
    @Override
    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }


    /**
     * method to get the id for this meeting
     * @return id for the meeting
     */
    @Override
    public int getId() {
        return id;
    }


    /**
     * method to set the id for this meeting
     * @param id new id for this meeting
     */
    @Override//没必要？
    public void setId(int id) {
        this.id = id;
    }




}
