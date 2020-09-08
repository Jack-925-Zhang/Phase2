package Controller;

import GateWay.DataInsertion;
import GateWay.IDataInsertion;
import Model.IUserAccount;
import Model.Item;
import Model.ItemInterface;
import UseCases.UserUseCase.IUserWishListOperation;
import UseCases.UserUseCase.UserManager;
import UseCases.UserUseCase.UserWishListOperation;

import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * navigates the methods from ItemInterface
 * @author Hongyu Chen
 * @version 1.8
 */
public class ItemGUINavigator {

    private static final ItemGUINavigator itemGUINavigator = new ItemGUINavigator();
    UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    IUserWishListOperation userWishListOperation = UserWishListOperation.getInstance();
    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    IDataInsertion dataInsertion = new DataInsertion();
    ResourceBundle languageRB;
    AdminOpGUINavigator adminOpGUINavigator = AdminOpGUINavigator.getInstance();

    /**
     * the constructor for class ItemGUINavigator
     */
    public ItemGUINavigator() {
    }

    /**
     * gets the attribute ITEM_GUI_NAVIGATOR
     * @return ITEM_GUI_NAVIGATOR
     */
    public static ItemGUINavigator getInstance(){
        return itemGUINavigator;
    }

    /**
     * This method navigates the method of getting the name of one itemInterface
     * from the class ItemInterface. When the name of one itemInterface is needed,
     * the class BorrowItemsMenu, LendItemsMenu, or MyTradesMenu calls this method.
     *
     * @param itemInterface The item whose name is needed.
     * @return The name of itemInterface.
     */
    public String getItemNameNavigator(ItemInterface itemInterface) {
        return itemInterface.getItemName();
    }

    /**
     * This method navigates the method of printing one item from the class
     * ItemInterface. When one item needs to be printed, the class LendItemsMenu
     * calls this method.
     *
     * @param itemInterface The item needs to be printed.
     * @return THe print of itemInterface.
     */
    public String itemToStringNavigator(ItemInterface itemInterface) {
        return itemInterface.toString();
    }

    /**
     * adds item
     * @param user the user who requests for adding new item
     * @param addedItemName the name of the item user wants to add
     * @param addedItemDescription the description of the item user wants to add
     */
    public void addNewItemNavigator(IUserAccount user, String addedItemName, String addedItemDescription, ResourceBundle languageRB) throws Exception {
        Item newItem = new Item(addedItemName, addedItemDescription,
                userAccountGUINavigator.getUserIdNavigator(user), true);
        if (userAccountGUINavigator.getIsInitialAdminNavigator(user)) {
            userWishListOperation.addToWishToLend(user, newItem);
        }else {

            userManagerGUINavigator.requestToAddItemNavigator(user, newItem);

            String addingItemRequest = languageRB.getString("addingItemRequest");
            String addingItemRequestMessage = languageRB.getString("addingItemRequestMessage");
            String request = addingItemRequest + " " + userAccountGUINavigator.getUserIdNavigator(user) +
                    " " + addingItemRequestMessage + " " + itemGUINavigator.getItemNameNavigator(newItem) + ". ";

            if (userAccountGUINavigator.getUserStatusNavigator(user).equals("Normal")) {
                Map<String, IUserAccount> adminUsers = userManagerGUINavigator.getAdminUsersNavigator();
                Random random = new Random();
                int randomAdminUserIndex = random.nextInt(adminUsers.size() + 1);
                IUserAccount chosenAdmin;
                int i = 0;
                for (Map.Entry<String, IUserAccount> adminUser : adminUsers.entrySet()) {
                    if (i == randomAdminUserIndex) {
                        chosenAdmin = adminUser.getValue();
//                        int newItemId = adminOpGUINavigator.getRequestedItemsNavigator(chosenAdmin).size();
//                        System.out.println("comes normal");
                        adminOpGUINavigator.addRequestedItemNavigator(chosenAdmin, newItem);
                        adminOpGUINavigator.addAddingItemRequestUserNavigator(chosenAdmin, user);
                        adminOpGUINavigator.addAddingItemNewMessageNavigator(chosenAdmin,newItem.itemId, request);
                    }
                    i++;
                }
            }else {
                IUserAccount initialAdmin = userManagerGUINavigator.getInitialAdminNavigator();
//                int newItemId = adminOpGUINavigator.getRequestedItemsNavigator(initialAdmin).size();

                adminOpGUINavigator.addRequestedItemNavigator(initialAdmin, newItem);
                adminOpGUINavigator.addAddingItemRequestUserNavigator(initialAdmin, user);
                adminOpGUINavigator.addAddingItemNewMessageNavigator(initialAdmin, newItem.itemId, request);
            }
        }
        dataInsertion.itemInsertion(newItem);

    }
}
