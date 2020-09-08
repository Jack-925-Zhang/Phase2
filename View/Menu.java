package View;

import Controller.UINavigator;
import View.ManageUserFrame.ManageUserMenu;
import View.MyAccountMenuFrame.MyAccountMenu;
import View.MyItemsMenuFrame.MyItemsMenu;
import View.MyTradesMenu.MyTradesMenu;
import View.NewMessagesFrame.NewMessagesInterface;
import View.StartTradeMenuFrame.SelectTradeType;
import View.StartTradeMenuFrame.StartTradeMenu;
import Model.IUserAccount;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.*;

/**
 * This class, Menu, prints to the screen any menu options
 * @author Hongyu Chen
 * @version 1.8
 */
public class Menu extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();

    JFrame menuFrame;

    ResourceBundle languageRB;

    /**
     * calls the menu frame for the main menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Menu window = new Menu(user, languageRB);
                    window.menuFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class Menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public Menu(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        init(user);
    }

    /**
     * the initializer for class Menu
     * @param user the user who sees this window
     */
    private void init(IUserAccount user) {

        String mainMenuTitle = languageRB.getString("mainMenuTitle");

        menuFrame = new JFrame();
        menuFrame.setTitle(mainMenuTitle);
        menuFrame.setSize(350,9*50);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setResizable(true);
        uiNavigator.initBgColour(menuFrame);

        JLabel headerLabel = new JLabel(mainMenuTitle, JLabel.CENTER);

        String welcomeLabelText = languageRB.getString("welcomeLabelText")+", "+user.getUserId()+"!";
        JLabel welcomeLabel = new JLabel(welcomeLabelText, JLabel.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(9,1));

        optionPanel.add(headerLabel);
        optionPanel.add(welcomeLabel);

        initMenuOptions(user, optionPanel);

        mainPanel.add(optionPanel);
        menuFrame.add(mainPanel);
    }

    /**
     * sets the options shown on the window for the main menu
     * @param user the user who sees this main menu
     * @param optionPanel the JPanel for the buttons
     */
    private void initMenuOptions(IUserAccount user, JPanel optionPanel) {

        String startNewTransactionButtonText = languageRB.getString("startNewTransactionButtonText");
        String myItemsButtonText = languageRB.getString("myItemsButtonText");
        String myTradesButtonText = languageRB.getString("myTradesButtonText");
        String myAccountButtonText = languageRB.getString("myAccountButtonText");
        String manageUserButtonText = languageRB.getString("manageUserButtonText");
        String newMessagesButtonText = languageRB.getString("newMessagesButtonText");
        String logOutButtonText = languageRB.getString("logOutButtonText");

        JButton startNewTrans = new JButton(startNewTransactionButtonText);
        JButton myItems = new JButton(myItemsButtonText);
        JButton myTrades = new JButton(myTradesButtonText);
        JButton myAccount = new JButton(myAccountButtonText);
        JButton manageUser = new JButton(manageUserButtonText);
        JButton newMessages = new JButton(newMessagesButtonText);
        JButton logOut = new JButton(logOutButtonText);

        startNewTrans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectTradeType.MenuScreen(user, languageRB);
                menuFrame.setVisible(false);
            }
        });
        myItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyItemsMenu.MenuScreen(user, languageRB);
                menuFrame.setVisible(false);
            }
        });
        myTrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyTradesMenu.MenuScreen(user, languageRB);
                menuFrame.setVisible(false);
            }
        });
        myAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyAccountMenu.MenuScreen(user, languageRB);
                menuFrame.setVisible(false);
            }
        });
        manageUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageUserMenu.MenuScreen(user, languageRB);
                menuFrame.setVisible(false);
            }
        });
        newMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewMessagesInterface.MenuScreen(user, languageRB);
                menuFrame.setVisible(false);
            }
        });
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginMenu(languageRB);
                menuFrame.setVisible(false);
            }
        });

        if (!user.getStatus().equals("Guest")) {
            uiNavigator.formatButtonSize(startNewTrans, optionPanel);
            uiNavigator.formatButtonSize(myTrades, optionPanel);
            uiNavigator.formatButtonSize(newMessages, optionPanel);
        }
        uiNavigator.formatButtonSize(myItems, optionPanel);
        uiNavigator.formatButtonSize(myAccount, optionPanel);
        if (user.getStatus().equals("Admin")){
            uiNavigator.formatButtonSize(manageUser, optionPanel);
        }
        uiNavigator.formatButtonSize(logOut, optionPanel);
    }

}
