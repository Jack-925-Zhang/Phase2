package Model;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashMap;
/**
 * This class, Item, stores the identity of the Item for the Users and could cooperate with the System for lending and borrowing.
 * add all the information related to the item into this class.
 * get and change the description of the item upon User's request.
 * change the name of the item upon admin's or owner's request.
 * change and set the status of item available/not available upon the owner's request and the admin User's request.
 * get the item's name upon requests from system or admin or user or owner.
 * get the owner's id upon requests from system or admin or user or owner.
 * get a String of item's information upon requests from system or admin or user or owner.
 * save the data of the item's information.
 * @author Yupeng Zhang
 * @version 1.8
 */
@DatabaseTable(tableName = "item")
public class Item implements ItemInterface {

    // for QueryBuilder to be able to find the fields
    public static final String DESCRIPTION_FIELD_NAME = "description";
    public static final String ITEM_NAME_FIELD_NAME = "itemName";
    public static final String STATUS_FIELD_NAME = "status";
    public static final String OWNER_FIELD_NAME = "owner";
    public static final String UNIQUE_ID_FIELD_NAME = "uniqueId";
    public static final String ITEM_ID_FIELD_NAME = "itemId";
    public static final String PRICE_FOR_TEMP_FIELD_NAME = "priceForTemp";
    public static final String PRICE_FOR_PERM_FIELD_NAME = "priceForPerm";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = DESCRIPTION_FIELD_NAME)
    public String description;

    @DatabaseField(columnName = ITEM_NAME_FIELD_NAME, canBeNull = false)
    public String itemName;

    @DatabaseField(columnName = STATUS_FIELD_NAME)
    public Boolean status;

    @DatabaseField(columnName = OWNER_FIELD_NAME)
    public String owner;

    @DatabaseField(columnName = UNIQUE_ID_FIELD_NAME)
    public static int uniqueId;

    @DatabaseField(columnName = ITEM_ID_FIELD_NAME)
    public int itemId;

    // sellable and rent able

    @DatabaseField(columnName = PRICE_FOR_TEMP_FIELD_NAME)
    private int priceForTemp;

    @DatabaseField(columnName = PRICE_FOR_PERM_FIELD_NAME)
    private int priceForPerm;
    public HashMap<Integer, ItemInterface> ItemMap;

    public Item(){}


    /**
     * create instance for the Item.
     * @param description the description of the Item.
     * @param itemName the name of this Item.
     * @param owner the owner's id of this Item
     * @param status the status of this Item, shows available or not available.
     */
    public Item(String itemName, String description, String owner, Boolean status){
        this.itemName = itemName;
        this.description = description;
        this.status = status;
        this.owner = owner;
        this.itemId = uniqueId;
        uniqueId += 1;
        ItemMap = new HashMap<>();

    }
    /**
     * This method returns the Item's description for users and system.
     *
     * @return Item's description.
     */
    @Override
    public String getDescription() {
        return description;
    }
    /**
     * This method sets the description of the Item for owner, also able to be modified by admin Users.
     *
     * @param description the description of the Item, explain the detail of this item.
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * This method sets the name of the Item for owner, also able to be modified by admin Users.
     *
     * @param itemName the Name of the Item, tells the name of the item.
     */
    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    /**
     * This method sets the name of the Item for owner, also able to be modified by admin Users.
     *
     * @param status the status of the Item, tells the availability of the item.
     */
    @Override
    public void changeStatus(boolean status) {
        this.status = status;
    }
    /**
     * This method returns the Item's name for users and system.
     *
     * @return Item's name.
     */
    @Override
    public String getItemName() {
        return itemName;
    }
    /**
     * This method returns the Item's status for users, and system.
     *
     * @return Item's status.
     */
    @Override
    public Boolean getStatus() {
        return status;
    }
    /**
     * This method returns the Item's owner for users, and system.
     *
     * @return Item's owner.
     */
    @Override
    public String getOwner() {
        return owner;
    }
    /**
     * This method converts the item's information to String
     * @return a String including all the identity related to the item, used to show to the user.
     */
    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", itemName='" + itemName + '\'' +
                ", status=" + status +
                ", owner='" + owner + '\'' +
                '}';
    }
    /**
     * This method set the Item's Id when system request.
     *
     * @param  itemId item's id.
     */
    @Override
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    /**
     * This method returns the Item's Id for users, and system.
     *
     * @return Item's Id.
     */
    @Override
    public int getItemId() {
        return itemId;
    }

    /**
     * This method converts the item's information to String
     * @return a String including all the identity related to the item, used to
     * show the saved item data in database.
     */
    @Override
    public String savedData(){
        return itemId + "&" + description + "&"+ itemName +"&" + status +"&" + owner;
    }



    /**
     * method to get the price for temp trade
     * @return the price for temp trade
     */
    public int getPriceForTemp() {
        return priceForTemp;
    }

    /**
     * setter for price for temp trade
     * @param priceForTemp
     */

    public void setPriceForTemp(int priceForTemp) {
        this.priceForTemp = priceForTemp;
    }

    /**
     * method to get price for perm trade
     * @return
     */
    public int getPriceForPerm() {
        return priceForPerm;
    }

    /**
     * setter price for perm trade
     * @param priceForPerm
     */
    public void setPriceForPerm(int priceForPerm) {
        this.priceForPerm = priceForPerm;
    }

    /**
     * adds item
     * @param key item key
     * @param Item added item
     */
    @Override
    public void addItem(int key, ItemInterface Item) {
        if(!(ItemMap.containsKey(key) && ItemMap.containsValue(Item))){
            ItemMap.put(key, Item);
        }
    }

    /**
     * searches item by key
     * @param key item key
     * @return item result
     */
    public ItemInterface SearchItemByKey(int key){
        return ItemMap.get(key);
    }


}
