package GateWay.Reader;

import Model.Meeting;

import java.util.List;

/**
 * This class is interface of the class the reader for the Meeting.
 * It would return the instances of Meeting
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */
public interface IDataMeetingReader {

    /**
     * It is a getter for Meetings.
     * @return the list of Meetings.
     */
    List<Meeting> getMeetings();
}
