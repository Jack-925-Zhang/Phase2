package GateWay.Reader;

import GateWay.DataBuilder;
import Model.Item;
import Model.Meeting;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the reader for the Meetings.
 * It would read from the sql database and return the Meetings.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */
public class DataMeetingReader implements IDataMeetingReader{

    private final DataBuilder dataBuilder ;

    private final Dao<Meeting, Integer> meetingsDao;
    private List<Meeting> meetings ;

    /**
     * This method provides the reading of Meetings from the SQLite database
     * and it would use queryForAll() method and make Meetings base on the
     * specific information from database.
     * @throws  SQLException It would throws the SQLException as long as it is
     * reading the two-way transactions information from the database.
     */

    public DataMeetingReader() throws SQLException {
        dataBuilder = DataBuilder.getInstance();
        meetingsDao = dataBuilder.getMeetingsDao();
        meetings = new ArrayList<>();
        // query for all items in the database
        meetings = meetingsDao.queryForAll();
    }

    /**
     * It is a getter for Meetings.
     * @return the list of Meetings.
     */

    public List<Meeting> getMeetings() {
        return meetings;
    }

}
