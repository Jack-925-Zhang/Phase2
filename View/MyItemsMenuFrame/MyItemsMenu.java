package View.MyItemsMenuFrame;

import Controller.UINavigator;
import Model.IUserAccount;
//import sun.tools.jps.Jps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ResourceBundle;

/**
 * the class for user's items menu
 * @author Hongyu Chen
 * @version 1.8
 */
public class MyItemsMenu extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();

    JFrame myItemsMenuFrame;

    ResourceBundle languageRB;

    /**
     * calls the menu frame for the my items menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyItemsMenu window = new MyItemsMenu(user, languageRB);
                    window.myItemsMenuFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class MyItemsMenu
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public MyItemsMenu(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.languageRB = languageRB;
        init(user);
    }

    /**
     * this is the initializer for class MyItemsMenu
     * @param user the user who sees this menu
     */
    private void init(IUserAccount user) throws Exception {

        String myItemsButtonText = languageRB.getString("myItemsButtonText");

        myItemsMenuFrame = new JFrame();
        myItemsMenuFrame.setTitle(myItemsButtonText);
        myItemsMenuFrame.setSize(300, 8*50);
        myItemsMenuFrame.setLocationRelativeTo(null);
        myItemsMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myItemsMenuFrame.setResizable(true);
        // adding JPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        JLabel headerLabel1 = new JLabel(myItemsButtonText, JLabel.CENTER);
        panel.add(headerLabel1);

        myItemsMenuOption(user, panel);
        mainPanel.add(panel);
        myItemsMenuFrame.add(mainPanel);

    }

    /**
     * sets the options shown on the window for my items menu
     * @param user the user who sees this menu
     * @param panel the JPanel for the buttons
     */
    private void myItemsMenuOption(IUserAccount user, JPanel panel) throws Exception {

        String myLentItemsLabelText = languageRB.getString("myLentItemsLabelText");
        String myNotLentItemsLabelText = languageRB.getString("myNotLentItemsLabelText");
        String borrowedItemsLabelText = languageRB.getString("borrowedItemsLabelText");
        String addItemLabelText = languageRB.getString("addItemLabelText");
        String wishListLabelText = languageRB.getString("wishListButtonText");
        String removeItemLabelText = languageRB.getString("removeItemLabelText");

        JButton allItemsLent = new JButton(myLentItemsLabelText);
        JButton allItemsNotLent = new JButton(myNotLentItemsLabelText);
        JButton borrowedItems = new JButton(borrowedItemsLabelText);
        JButton addItems = new JButton(addItemLabelText);
        JButton removeItems = new JButton(removeItemLabelText);
        JButton wishList = new JButton(wishListLabelText);

        if (!user.getStatus().equals("Guest")) {
            allItemsLent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyItemsMyLentItemsPage.myLentItemsPageScreen(user, languageRB);
                    myItemsMenuFrame.setVisible(false);
                }
            });
            allItemsNotLent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyItemsMyNotLentItemsPage.myNotLentItemsPageScreen(user, languageRB);
                    myItemsMenuFrame.setVisible(false);
                }
            });
            borrowedItems.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyItemsBorrowedItemsPage.borrowedItemsPageScreen(user, languageRB);
                    myItemsMenuFrame.setVisible(false);
                }
            });
            uiNavigator.formatButtonSize(allItemsLent, panel);
            uiNavigator.formatButtonSize(allItemsNotLent, panel);
            uiNavigator.formatButtonSize(borrowedItems, panel);
        }

        addItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyItemsAddItemPage.addItemPageScreen(user, languageRB);
                myItemsMenuFrame.setVisible(false);
            }
        });
        removeItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyItemsRemoveItemPage.removeItemPageScreen(user, languageRB);
                myItemsMenuFrame.setVisible(false);
            }
        });
        wishList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WishListPage.MenuScreen(user, languageRB);
                myItemsMenuFrame.setVisible(false);
            }
        });

        uiNavigator.formatButtonSize(wishList, panel);
        uiNavigator.formatButtonSize(addItems, panel);
        uiNavigator.formatButtonSize(removeItems, panel);
        uiNavigator.backToMainMenu(user,myItemsMenuFrame, panel, languageRB);

    }

    /**
     * goes back to my items menu
     * @param user the user who sees this
     * @param currentFrame the JFrame which will be disposed
     * @param panel the JPanel where the buttons are added
     * @param languageRB user selected language
     */
    void backToParentMenu(IUserAccount user, JFrame currentFrame, JPanel panel, ResourceBundle languageRB) throws Exception {

        String myItemsButtonText = languageRB.getString("myItemsButtonText");
        JButton myItemsMenu = new JButton(myItemsButtonText);
        uiNavigator.formatButtonSize(myItemsMenu, panel);

        myItemsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuScreen(user, languageRB);
                currentFrame.setVisible(false);
            }
        });
    }

}
