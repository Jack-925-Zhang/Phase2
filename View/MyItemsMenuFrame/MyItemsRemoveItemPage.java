package View.MyItemsMenuFrame;

import Controller.UINavigator;
import Controller.UserOpGUINavigator;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ResourceBundle;

/**
 * the class for remove item page
 * @author Hongyu Chen
 * @version 1.8
 */
public class MyItemsRemoveItemPage extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();

    MyItemsMenu myItemsMenu;

    JFrame removeItemPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the remove item page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    static void removeItemPageScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyItemsRemoveItemPage window = new MyItemsRemoveItemPage(user, languageRB);
                    window.removeItemPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of the class for remove item page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    MyItemsRemoveItemPage(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.myItemsMenu = new MyItemsMenu(user, languageRB);
        this.languageRB = languageRB;
        initRemoveItemPage(user);
    }

    /**
     * initializer for remove item page
     * @param user the user who sees this page
     */
    private void initRemoveItemPage(IUserAccount user) throws Exception {

        String removeItemLabelText = languageRB.getString("removeItemLabelText");

        // setting the frame of the page for removing item
        removeItemPageFrame = new JFrame();
        removeItemPageFrame.setTitle(removeItemLabelText);
        removeItemPageFrame.setSize(600,4*50);
        removeItemPageFrame.setLocationRelativeTo(null);
        removeItemPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        removeItemPageFrame.setVisible(true);
        removeItemPageFrame.setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 1));
        JLabel headerLabel6 = new JLabel(removeItemLabelText, JLabel.CENTER);
        titlePanel.add(headerLabel6);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 2));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));

        removeItemPageOption(user, inputPanel, buttonPanel);
        mainPanel.add(titlePanel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        removeItemPageFrame.add(mainPanel);
    }

    /**
     * sets the options shown on the window for remove item menu
     * @param user the user who sees this menu
     * @param panel6 the JPanel for the buttons
     */
    private void removeItemPageOption(IUserAccount user, JPanel panel6, JPanel btnPanel) throws Exception {

        String removeItemNameLabelText = languageRB.getString("removeItemNameLabelText");
        String removeButtonText = languageRB.getString("removeButtonText");

        JLabel removeItemName  = new JLabel(removeItemNameLabelText, JLabel.CENTER);
        panel6.add(removeItemName);

        JTextField itemNameInput = new JTextField(20);
        panel6.add(itemNameInput);

        JButton removeButton = new JButton(removeButtonText);
        uiNavigator.formatButtonSize(removeButton, btnPanel);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removedItemName = removeItemName.getText();

                if ((userOpGUINavigator.getWishToLendNavigator(user) != null ||
                        userOpGUINavigator.getWishToBorrowNavigator(user) != null) ||
                        (!(removedItemName.equals("")))) {

                    MyItemsConfirmRemovingItemPage.confirmRemovingItemPageScreen(
                            user, removedItemName, languageRB);
                    removeItemPageFrame.setVisible(false);
                }
            }
        });

        myItemsMenu.backToParentMenu(user, removeItemPageFrame, btnPanel, languageRB);
    }

}
