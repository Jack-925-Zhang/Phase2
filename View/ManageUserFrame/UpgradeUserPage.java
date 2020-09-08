package View.ManageUserFrame;

import Controller.AdminOpGUINavigator;
import Controller.TradingSystemGUINavigator;
import Controller.UINavigator;
import Controller.UserManagerGUINavigator;
import Model.IUserAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * sets upgrade normal user to admin user page
 * @author BINGQING WAN
 * @version 1.8
 */
public class UpgradeUserPage {

    JFrame upgradeUserFrame;
    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    TradingSystemGUINavigator tradingSystemGUINavigator = TradingSystemGUINavigator.getInstance();
    AdminOpGUINavigator adminOpGUINavigator = AdminOpGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    ResourceBundle languageRB;

    /**
     * calls the menu frame for the upgrade user menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UpgradeUserPage window = new UpgradeUserPage(user, languageRB);
                    window.upgradeUserFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class UpgradeUserPage
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public UpgradeUserPage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        initUpgradeUserPage(user, languageRB);
    }

    /**
     * this method initializes the window properties and buttons for the UpgradeUserPage
     */
    private void initUpgradeUserPage(IUserAccount user, ResourceBundle languageRB) {

        String upgradeButtonText = languageRB.getString("upgradeButtonText");
        String manageUserButtonText = languageRB.getString("manageUserButtonText");
        String mainMenuButtonText = languageRB.getString("mainMenuButtonText");
        String confirmButtonText = languageRB.getString("confirmButtonText");
        String userIdText = languageRB.getString("userLabelText");
        String checkButtonText = languageRB.getString("getNormalUserButtonText");

        upgradeUserFrame = new JFrame();
        upgradeUserFrame.setTitle(upgradeButtonText);
        upgradeUserFrame.setSize(350,5*50);
        upgradeUserFrame.setLocationRelativeTo(null);
        upgradeUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        upgradeUserFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1,1));
        JLabel titleLabel = new JLabel(upgradeButtonText, JLabel.CENTER);
        titlePanel.add(titleLabel);

        // input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1,2));
        JLabel inputLabel = new JLabel(userIdText, JLabel.CENTER);
        inputPanel.add(inputLabel);
        JTextField inputIdText = new JTextField();
        inputPanel.add(inputIdText);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        JButton btnConfirm = new JButton(confirmButtonText);
        uiNavigator.formatButtonSize(btnConfirm, buttonPanel);

        JButton btnCheck = new JButton(checkButtonText);
        uiNavigator.formatButtonSize(btnCheck, buttonPanel);

        JButton btnManageUserMenu = new JButton(manageUserButtonText);
        uiNavigator.formatButtonSize(btnManageUserMenu, buttonPanel);

        try {
            uiNavigator.backToMainMenu(user, upgradeUserFrame, buttonPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainPanel.add(titlePanel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        upgradeUserFrame.add(mainPanel);

        HashMap<String, IUserAccount> normalUser = userManagerGUINavigator.getNormalUsersNavigator();

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IUserAccount newAdminUser = userManagerGUINavigator.getUserByIdNavigator(inputIdText.getText());
                String upgradeFailMessage = languageRB.getString("upgradeUserFailMessage");
                String successMessage = languageRB.getString("successMessage");
                String alreadyAdminMessage = languageRB.getString("alreadyAdminMessage");
                if (newAdminUser == null){
                    JOptionPane.showMessageDialog(null, upgradeFailMessage);
                }
                else if (newAdminUser.getStatus()=="Admin") {
                    JOptionPane.showMessageDialog(null, alreadyAdminMessage);
                }
                else {
                    try {
                        tradingSystemGUINavigator.updateAccountNavigator(newAdminUser);
                        JOptionPane.showMessageDialog(null, successMessage);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayAllNormalUserIdPage.MenuScreen(normalUser, languageRB);
            }
        });

        btnManageUserMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageUserMenu.MenuScreen(user, languageRB);
                upgradeUserFrame.setVisible(false);
            }
        });
    }
}
