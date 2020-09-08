package GateWay.Reader;

import GateWay.DataBuilder;
import Model.Item;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the reader for the Items.
 * It would read from the sql database and return the Items.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */
public class DataItemReader implements IDataItemReader{

    private final DataBuilder dataBuilder ;
    private final Dao<Item, Integer> itemsDao ;
    private final List<Item> items ;

    /**
     * This method provides the reading of Items from the SQLite database
     * and it would use queryForAll() method and make Items base on the
     * specific information from database.
     * @throws  SQLException It would throws the SQLException as long as it is
     * reading the two-way transactions information from the database.
     */

    public DataItemReader() throws SQLException {
        List<Item> items1;
        dataBuilder = DataBuilder.getInstance();
        itemsDao = dataBuilder.getItemsDao();
        items1 = new ArrayList<>();
        // query for all items in the database
        items1 = itemsDao.queryForAll();
        items = items1;
    }

    /**
     * It is a getter for Items.
     * @return the list of Items.
     */

    public List<Item> getItems() {
        return items;
    }
}
