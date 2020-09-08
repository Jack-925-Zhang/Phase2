package Model;
/**
 * ItemInterface is the interface for Items, including all the method to modify
 * the Item related identities.
 * @author Yupeng Zhang
 * @version 1.8
 */
public interface ItemInterface {
    /**
     * This method returns the Item's description for users and system.
     *
     * @return Item's description.
     */
    String getDescription();
    /**
     * This method sets the description of the Item for owner, also able to be modified by admin Users.
     *
     * @param description the description of the Item, explain the detail of this item.
     */
    void setDescription(String description);
    /**
     * This method sets the name of the Item for owner, also able to be modified by admin Users.
     *
     * @param itemName the Name of the Item, tells the name of the item.
     */
    void setItemName(String itemName);
    /**
     * This method sets the name of the Item for owner, also able to be modified by admin Users.
     *
     * @param status the status of the Item, tells the availability of the item.
     */
    void changeStatus(boolean status);
    /**
     * This method returns the Item's name for users and system.
     *
     * @return Item's name.
     */
    String getItemName();
    /**
     * This method returns the Item's status for users, and system.
     *
     * @return Item's status.
     */
    Boolean getStatus();
    /**
     * This method returns the Item's owner for users, and system.
     *
     * @return Item's owner.
     */
    String getOwner();
    /**
     * This method converts the item's information to String
     * @return a String including all the identity related to the item, used to
     * show the saved item data in database.
     */
    String savedData();
    /**
     * This method converts the item's information to String
     * @return a String including all the identity related to the item, used to show to the user.
     */
    String toString();
    /**
     * This method set the Item's Id when system request.
     *
     * @param  itemId item's id.
     */
    void setItemId(int itemId);
    /**
     * This method returns the Item's Id for users, and system.
     *
     * @return Item's Id.
     */
    int getItemId();

    /**
     * method to get the price for temp trade
     * @return the price for temp trade
     */

    int getPriceForTemp();
    /**
     * setter for price for temp trade
     * @param priceForTemp
     */
    void setPriceForTemp(int priceForTemp);
    /**
     * method to get price for perm trade
     * @return
     */
    int getPriceForPerm();
    /**
     * setter price for perm trade
     * @param priceForPerm
     */
    void setPriceForPerm(int priceForPerm);

    /**
     * adds item
     * @param key the key of item
     * @param Item the item needs to be added
     */
    void addItem(int key, ItemInterface Item);

    /**
     * searches item by item key
     * @param key item key
     * @return item having this key
     */
    ItemInterface SearchItemByKey(int key);
}
