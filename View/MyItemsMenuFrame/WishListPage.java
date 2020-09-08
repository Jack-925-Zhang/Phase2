package View.MyItemsMenuFrame;

import Controller.ItemGUINavigator;
import Controller.UINavigator;
import Controller.UserOpGUINavigator;
import Model.IUserAccount;
import Model.ItemInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * the class for wish list page
 * @author Yinzhe Li
 * @version 1.8
 */

public class WishListPage{
    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    private final UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    MyItemsMenu myItemsMenu;
    JFrame wishListPageFrame;
    ResourceBundle languageRB;

    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    WishListPage window = new WishListPage(user, languageRB);
                    window.wishListPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class WishListPage
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public WishListPage(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.languageRB = languageRB;
        this.myItemsMenu = new MyItemsMenu(user, languageRB);
        initWishListPage(user);
    }

    /**
     * this is the initializer for class WishList
     * @param user the user who sees this menu
     */
    private void initWishListPage(IUserAccount user) throws Exception {
        String wishListButtonText = "Wish List";
        int height = userOpGUINavigator.getWishToBorrowNavigator(user).size();

        wishListPageFrame = new JFrame();
        wishListPageFrame.setTitle(wishListButtonText);
        wishListPageFrame.setSize(300,(height+3)*50);
        wishListPageFrame.setLocationRelativeTo(null);
        wishListPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wishListPageFrame.setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // adding JPanel
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(height+3, 1));

        JLabel headerLabel1 = new JLabel(wishListButtonText, JLabel.CENTER);
        panel1.add(headerLabel1);
        wishListPageOption(user, panel1);
        mainPanel.add(panel1);
        wishListPageFrame.add(mainPanel);
        wishListPageFrame.setVisible(true);
    }

    /**
     * sets the options shown on the window for wishlist
     * @param user the user who sees this menu
     * @param panel2 the JPanel for the buttons
     */
    private void wishListPageOption(IUserAccount user, JPanel panel2) throws Exception {

        String noItemInWishToBorrowLabelText = languageRB.getString("noItemInWishToBorrowLabelText");

        if (userOpGUINavigator.getWishToBorrowNavigator(user).size() == 0) {
            JLabel noItemInWishToBorrow = new JLabel(noItemInWishToBorrowLabelText, JLabel.CENTER);
            panel2.add(noItemInWishToBorrow);
        } else {
            for (ItemInterface wishToBorrowItem : userOpGUINavigator.getWishToBorrowNavigator(user)) {
                JLabel item = new JLabel(itemGUINavigator.getItemNameNavigator(wishToBorrowItem), JLabel.CENTER);
                panel2.add(item);
            }
        }
        myItemsMenu.backToParentMenu(user, wishListPageFrame, panel2, languageRB);
    }


}
