package View.ManageUserFrame;

import Controller.UINavigator;
import View.AdminFrame.AdminUserMainMenu;
import Model.IUserAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * sets manage user menu for administrative users
 * @author BINGQING WAN
 * @version 1.8
 */
public class ManageUserMenu {

    JFrame manageUserMenuFrame;
    UINavigator uiNavigator = UINavigator.getInstance();
    ResourceBundle languageRB;

    /**
     * calls the menu frame for the manage user menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ManageUserMenu window = new ManageUserMenu(user, languageRB);
                    window.manageUserMenuFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class ManageUserMenu
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public ManageUserMenu(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        init(user);
    }

    /**
     * this is the initializer for class ManageUserMenu
     */
    private void init(IUserAccount user) {

        String manageUserButtonText = languageRB.getString("manageUserButtonText");

        manageUserMenuFrame = new JFrame();
        manageUserMenuFrame.setTitle(manageUserButtonText);
        manageUserMenuFrame.setSize(350,200);
        manageUserMenuFrame.setLocationRelativeTo(null);
        manageUserMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manageUserMenuFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        String changeThresholdButtonText = languageRB.getString("changeThresholdButtonText");
        String upgradeButtonText = languageRB.getString("upgradeButtonText");
        String mainMenuButtonText = languageRB.getString("mainMenuButtonText");
        String adminUserMenuButtonText = languageRB.getString("adminUserMenuButtonText");

        JButton changeThreshold = new JButton(changeThresholdButtonText);
        uiNavigator.formatButtonSize(changeThreshold, buttonPanel);

        JButton upgrade = new JButton(upgradeButtonText);
        if (user.getIsInitialAdmin()) {
            uiNavigator.formatButtonSize(upgrade, buttonPanel);
        }

        JButton adminUserMenu = new JButton(adminUserMenuButtonText);
        uiNavigator.formatButtonSize(adminUserMenu, buttonPanel);

        try {
            uiNavigator.backToMainMenu(user, manageUserMenuFrame, buttonPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainPanel.add(buttonPanel);
        manageUserMenuFrame.add(mainPanel);

        changeThreshold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeUserThresholdPage.MenuScreen(user,languageRB);
                manageUserMenuFrame.setVisible(false);
            }
        });
        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpgradeUserPage.MenuScreen(user, languageRB);
                manageUserMenuFrame.setVisible(false);
            }
        });
        adminUserMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUserMainMenu.MenuScreen(user, languageRB);
                manageUserMenuFrame.setVisible(false);
            }
        });
    }
}
