package View.MyItemsMenuFrame;

import Controller.ItemGUINavigator;
import Controller.UINavigator;
import Controller.UserOpGUINavigator;
import Model.IUserAccount;
import Model.ItemInterface;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ResourceBundle;

/**
 * the class for user's borrowed items page
 * @author Hongyu Chen
 * @version 1.8
 */
public class MyItemsBorrowedItemsPage extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    private final UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    MyItemsMenu myItemsMenu;

    JFrame borrowedItemsPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the user's borrowed items page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    static void borrowedItemsPageScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyItemsBorrowedItemsPage window = new MyItemsBorrowedItemsPage(user, languageRB);
                    window.borrowedItemsPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class for borrowed items page
     * @param user the user who sees this page
     * @param languageRB the language bundle chosen by user
     */
    MyItemsBorrowedItemsPage(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.myItemsMenu = new MyItemsMenu(user, languageRB);
        this.languageRB = languageRB;
        initBorrowedItemsPage(user);
    }

    /**
     * initializer for borrowed items page
     * @param user the user who sees this page
     */
    private void initBorrowedItemsPage(IUserAccount user) throws Exception {

        String borrowedItemsLabelText = languageRB.getString("borrowedItemsLabelText");
        List<ItemInterface> borrowedItem = userOpGUINavigator.getCurrentBorrowingNavigator(user);

        // setting the frame of the page for user's borrowed items
        borrowedItemsPageFrame = new JFrame();
        borrowedItemsPageFrame.setTitle(borrowedItemsLabelText);
        borrowedItemsPageFrame.setSize(300, (borrowedItem.size()+3)*50);
        borrowedItemsPageFrame.setLocationRelativeTo(null);
        borrowedItemsPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        borrowedItemsPageFrame.setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout((borrowedItem.size()+3), 1));

        JLabel headerLabel4 = new JLabel(borrowedItemsLabelText, JLabel.CENTER);
        panel.add(headerLabel4);

        borrowedItemsPageOption(user, panel);
        mainPanel.add(panel);
        borrowedItemsPageFrame.add(mainPanel);
    }

    /**
     * sets the options shown on the window for user's borrowed items menu
     * @param user the user who sees this menu
     * @param panel4 the JPanel for the buttons
     */
    private void borrowedItemsPageOption(IUserAccount user, JPanel panel4) throws Exception {

        String noBorrowedItemLabelText = languageRB.getString("noBorrowedItemLabelText");

        if (userOpGUINavigator.getCurrentBorrowingNavigator(user).isEmpty()) {
            JLabel noBorrowedItem = new JLabel(noBorrowedItemLabelText, JLabel.CENTER);
            panel4.add(noBorrowedItem);
        }else {
            for (ItemInterface borrowedItem : userOpGUINavigator.getCurrentBorrowingNavigator(user)) {
                JLabel item = new JLabel(itemGUINavigator.getItemNameNavigator(borrowedItem), JLabel.CENTER);
                panel4.add(item);
            }
        }

        myItemsMenu.backToParentMenu(user, borrowedItemsPageFrame, panel4, languageRB);

    }

}
