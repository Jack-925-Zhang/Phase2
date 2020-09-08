package View.MyItemsMenuFrame;

import Controller.*;
import Model.IUserAccount;
import Model.Item;
import UseCases.UserUseCase.IUserWishListOperation;
import UseCases.UserUseCase.UserWishListOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * the class for add item menu page
 * @author Hongyu Chen
 * @version 1.8
 */
public class MyItemsAddNewItemPage extends JFrame {

    UINavigator uiNavigator = UINavigator.getInstance();
    ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    MyItemsMenu myItemsMenu;

    JFrame addNewItemPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the add new item page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    static void addNewItemPageScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyItemsAddNewItemPage window = null;
                try {
                    window = new MyItemsAddNewItemPage(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assert window != null;
                window.addNewItemPageFrame.setVisible(true);
            }
        });
    }

    /**
     * the constructor of class for add new item page
     * @param user the user who sees this page
     * @param languageRB the language bundle chosen by user
     */
    MyItemsAddNewItemPage(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.myItemsMenu = new MyItemsMenu(user, languageRB);
        this.languageRB = languageRB;
        initAddNewItemPage(user);
    }

    /**
     * initializer for add new item page
     * @param user the user who sees this page
     */
    private void initAddNewItemPage(IUserAccount user) throws Exception {

        String addNewItemButtonText = languageRB.getString("addNewItemButtonText");

        // setting the frame of the page for adding item
        addNewItemPageFrame = new JFrame();
        addNewItemPageFrame.setTitle(addNewItemButtonText);
        addNewItemPageFrame.setSize(600, 5*50);
        addNewItemPageFrame.setLocationRelativeTo(null);
        addNewItemPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addNewItemPageFrame.setResizable(true);

        // adding JPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 1));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));

        JLabel headerLabel5 = new JLabel(addNewItemButtonText, JLabel.CENTER);
        titlePanel.add(headerLabel5);

        addNewItemPageOption(user, inputPanel, buttonPanel);
        mainPanel.add(titlePanel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        addNewItemPageFrame.add(mainPanel);

    }

    /**
     * sets the options shown on the window for add new item menu
     * @param user the user who sees this menu
     * @param inputPanel the JPanel for the user input
     * @param buttonPanel the JPanel for buttons
     */
    private void addNewItemPageOption(IUserAccount user, JPanel inputPanel, JPanel buttonPanel) throws Exception {

        String addItemNameLabelText = languageRB.getString("addItemNameLabelText");
        String addItemDescriptionLabelText = languageRB.getString("addItemDescriptionLabelText");
        String addButtonText = languageRB.getString("addButtonText");
        String loginSuccessMessage = languageRB.getString("successMessage");
        String noInputMessage = languageRB.getString("noInputMessage");

        JLabel addItemName  = new JLabel(addItemNameLabelText, JLabel.CENTER);
        inputPanel.add(addItemName);

        JTextField itemNameInput = new JTextField(20);
        inputPanel.add(itemNameInput);

        JLabel addItemDescription  = new JLabel(addItemDescriptionLabelText, JLabel.CENTER);
        inputPanel.add(addItemDescription);

        JTextField itemDescriptionInput = new JTextField(20);
        inputPanel.add(itemDescriptionInput);

        JButton addButton = new JButton(addButtonText);
        uiNavigator.formatButtonSize(addButton, buttonPanel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String addedItemName = itemNameInput.getText();
                String addedItemDescription = itemDescriptionInput.getText();
                if (addedItemName.isEmpty()||addedItemDescription.isEmpty()){
                    JOptionPane.showMessageDialog(null, noInputMessage);
                }
                else {
                    try {
                        itemGUINavigator.addNewItemNavigator(user, addedItemName,addedItemDescription, languageRB);
                        JOptionPane.showMessageDialog(null, loginSuccessMessage);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
        myItemsMenu.backToParentMenu(user, addNewItemPageFrame, buttonPanel, languageRB);
    }
}
