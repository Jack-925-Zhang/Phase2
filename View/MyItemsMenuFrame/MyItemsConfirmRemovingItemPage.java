package View.MyItemsMenuFrame;

import Controller.*;
import Model.IUserAccount;
import Model.ItemInterface;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * the class for confirm removing item page
 * @author Hongyu Chen
 * @version 1.8
 */
public class MyItemsConfirmRemovingItemPage extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    private final ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    private final UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    private final UserWishListOpGUINavigator userWishListOpGUINavigator = UserWishListOpGUINavigator.getInstance();
    MyItemsMenu myItemsMenu;

    String removedItemName;
    JFrame confirmRemovingItemPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the confirm removing item page
     * @param user the user who sees this window
     * @param removedItemName the name of the item confirmed removing
     * @param languageRB the language bundle chosen by user
     */
    static void confirmRemovingItemPageScreen(IUserAccount user,
                                              String removedItemName,
                                              ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyItemsConfirmRemovingItemPage window =
                            new MyItemsConfirmRemovingItemPage(user, removedItemName, languageRB);
                    window.confirmRemovingItemPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of the class for confirm removing item page
     * @param user the user who sees this window
     * @param removedItemName the name of the item confirmed removing
     * @param languageRB the language bundle chosen by user
     */
    MyItemsConfirmRemovingItemPage(IUserAccount user, String removedItemName, ResourceBundle languageRB) throws Exception {
        this.myItemsMenu = new MyItemsMenu(user, languageRB);
        this.removedItemName = removedItemName;
        this.languageRB = languageRB;
        initConfirmRemovingItemPage(user);
    }

    /**
     * initializer for confirm removing item page
     * @param user the user who sees this page
     */
    private void initConfirmRemovingItemPage(IUserAccount user) throws Exception {

        String confirmRemovingItemLabelText = languageRB.getString("confirmRemovingItemLabelText");

        // setting the frame of the page for confirming removing item
        confirmRemovingItemPageFrame = new JFrame();
        confirmRemovingItemPageFrame.setTitle(confirmRemovingItemLabelText);
        confirmRemovingItemPageFrame.setSize(1025,600);
        confirmRemovingItemPageFrame.setLocationRelativeTo(null);
        confirmRemovingItemPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmRemovingItemPageFrame.getContentPane().setLayout(null);
        confirmRemovingItemPageFrame.setVisible(true);

        confirmRemovingItemPageFrame.setResizable(true);
        confirmRemovingItemPageFrame.setLayout(new GridLayout(5, 1));
        uiNavigator.initBgColour(confirmRemovingItemPageFrame);

        JLabel headerLabel7 = new JLabel(confirmRemovingItemLabelText, JLabel.CENTER);

        confirmRemovingItemPageFrame.add(headerLabel7);

        // adding JPanel
        JPanel panel7 = new JPanel();
        confirmRemovingItemPageFrame.add(panel7);

        confirmRemovingItemPageOption(user, panel7);

    }

    /**
     * sets the options shown on the window for confirm removing item menu
     * @param user the user who sees this menu
     * @param panel7 the JPanel for the buttons
     */
    private void confirmRemovingItemPageOption(IUserAccount user, JPanel panel7) throws Exception {

        if (userOpGUINavigator.getWishToLendNavigator(user).size() == 0 &&
                userOpGUINavigator.getWishToBorrowNavigator(user).size() == 0) {

            String itemNotFoundLabelText = languageRB.getString("itemNotFoundLabelText");

            JLabel itemNotFound  = new JLabel(itemNotFoundLabelText, JLabel.CENTER);
            confirmRemovingItemPageFrame.add(itemNotFound); // TODO: 为啥这到底为啥没有实例化

        }else if (userManagerGUINavigator.getItemNavigator(                  //item is in the WishToBorrow list
                removedItemName, userOpGUINavigator.getWishToBorrowNavigator(user)).size() != 0) {

            ItemInterface itemInWishlist = userManagerGUINavigator.getItemNavigator(
                    removedItemName, userOpGUINavigator.getWishToBorrowNavigator(user)).get(0);

            String itemInWishlistLabelText = languageRB.getString("itemInWishlistLabelText");
            String confirmationButtonText = languageRB.getString("confirmationButtonText");

            JLabel itemFound  = new JLabel(itemInWishlistLabelText + " ", JLabel.CENTER);
            confirmRemovingItemPageFrame.add(itemFound);

            JLabel itemInfo  = new JLabel(itemGUINavigator.itemToStringNavigator(itemInWishlist), JLabel.CENTER);
            confirmRemovingItemPageFrame.add(itemInfo);

            JButton confirmation = new JButton(confirmationButtonText);
            panel7.add(confirmation);

            confirmation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        userWishListOpGUINavigator.removeFromWishToBorrowNavigator(user, itemInWishlist);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    confirmRemovingItemPageFrame.dispose();
                }
            });

        }else if (userManagerGUINavigator.getItemNavigator(                //item is in the WishToLend list
                removedItemName, userOpGUINavigator.getWishToLendNavigator(user)).size() != 0) {

            ItemInterface itemInInventory = userManagerGUINavigator.getItemNavigator(
                    removedItemName, userOpGUINavigator.getWishToLendNavigator(user)).get(0);

            String itemInInventoryLabelText = languageRB.getString("itemInInventoryLabelText");
            String confirmationButtonText = languageRB.getString("confirmationButtonText");

            JLabel itemFound  = new JLabel(itemInInventoryLabelText + " ", JLabel.CENTER);
            confirmRemovingItemPageFrame.add(itemFound);

            JLabel itemInfo  = new JLabel(itemGUINavigator.itemToStringNavigator(itemInInventory), JLabel.CENTER);
            confirmRemovingItemPageFrame.add(itemInfo);

            JButton confirmation = new JButton(confirmationButtonText);
            panel7.add(confirmation);

            confirmation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        userWishListOpGUINavigator.removeFromWishToBorrowNavigator(user, itemInInventory);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    confirmRemovingItemPageFrame.dispose();
                }
            });

        }

        confirmRemovingItemPageFrame.add(panel7);
        panel7.setLayout(new FlowLayout());
        panel7.setBackground(Color.white);

        myItemsMenu.backToParentMenu(user, confirmRemovingItemPageFrame, panel7, languageRB);

    }

}
