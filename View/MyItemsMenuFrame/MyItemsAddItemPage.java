package View.MyItemsMenuFrame;

import Controller.UINavigator;
import Model.IUserAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * the class for user's add item page
 * @author Hongyu Chen
 * @version 1.8
 */
public class MyItemsAddItemPage extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    MyItemsMenu myItemsMenu;
    JFrame addItemPageFrame;
    ResourceBundle languageRB;

    /**
     * calls the page frame for the add item page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    static void addItemPageScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyItemsAddItemPage window = null;
                try {
                    window = new MyItemsAddItemPage(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assert window != null;
                window.addItemPageFrame.setVisible(true);
            }
        });
    }

    /**
     * the constructor of class for add item page
     * @param user the user who sees this page
     * @param languageRB the language bundle chosen by user
     */
    MyItemsAddItemPage(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.myItemsMenu = new MyItemsMenu(user, languageRB);
        this.languageRB = languageRB;
        initAddItemPage(user);
    }

    /**
     * initializer for add item page
     * @param user the user who sees this page
     */
    private void initAddItemPage(IUserAccount user) throws Exception {

        String addItemLabelText = languageRB.getString("addItemLabelText");

        // setting the frame of the page for adding item
        addItemPageFrame = new JFrame();
        addItemPageFrame.setTitle(addItemLabelText);
        addItemPageFrame.setSize(300, 4*50);
        addItemPageFrame.setLocationRelativeTo(null);
        addItemPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addItemPageFrame.setResizable(true);

        // adding JPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel headerLabel5 = new JLabel(addItemLabelText, JLabel.CENTER);
        panel.add(headerLabel5);

        addItemPageOption(user, panel);
        mainPanel.add(panel);
        addItemPageFrame.add(mainPanel);
    }

    /**
     * sets the options shown on the window for add item menu
     * @param user the user who sees this menu
     * @param panel5 the JPanel for the buttons
     */
    private void addItemPageOption(IUserAccount user, JPanel panel5) throws Exception {

        if (!user.getStatus().equals("Guest")) {
            String addNewItemButtonText = languageRB.getString("addNewItemButtonText");
            JButton addNewItemButton = new JButton(addNewItemButtonText);
            uiNavigator.formatButtonSize(addNewItemButton, panel5);
            addNewItemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyItemsAddNewItemPage.addNewItemPageScreen(user, languageRB);
                    addItemPageFrame.setVisible(false);
                }
            });
        }
        String addToWishlistButtonText = languageRB.getString("addToWishlistButtonText");

        JButton addToWishlistButton = new JButton(addToWishlistButtonText);
        uiNavigator.formatButtonSize(addToWishlistButton, panel5);

        addToWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyItemsAddToWishlistPage.addToWishlistPageScreen(user, languageRB);
                addItemPageFrame.setVisible(false);
            }
        });

        myItemsMenu.backToParentMenu(user, addItemPageFrame, panel5, languageRB);

    }

}
