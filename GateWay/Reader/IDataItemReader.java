package GateWay.Reader;

import Model.Item;

import java.util.List;

/**
 * This class is interface of the class the reader for the Item.
 * It would return the instances of item.
 * This class is uses the Dao package from ormlite.dao.
 * This class would require the ormlite.jar.
 *
 * @author LINNI XIE
 * @version 1.8
 */
public interface IDataItemReader {

    /**
     * It is a getter for Items.
     * @return the list of Items.
     */
    List<Item> getItems();
}
