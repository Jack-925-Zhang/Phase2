package View.AdminFrame;

import Controller.*;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * This class creates the frame for administrative user main menu
 * @author Linni Xie
 * @version 1.8
 */
public class AdminUserMainMenu extends JFrame{

    AdminOpGUINavigator adminOpGUINavigator = AdminOpGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    ResourceBundle languageRB;
    JFrame adminUserFrame;

    /**
     * calls the menu frame for admin user
     * @param user the admin user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AdminUserMainMenu window = new AdminUserMainMenu(user, languageRB);
                    window.adminUserFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class AdminUserMainMenu
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public AdminUserMainMenu(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        initAdminUserMainMenu(user);
    }

    /**
     * This constructs the main frame and its characteristics for the admin user
     */
    private void initAdminUserMainMenu(IUserAccount user) {

        String adminUserMenuButtonText = languageRB.getString("adminUserMenuButtonText");

        adminUserFrame = new JFrame();
        adminUserFrame.setTitle(adminUserMenuButtonText);
        adminUserFrame.setSize(350,6*50);
        adminUserFrame.setLocationRelativeTo(null);
        adminUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminUserFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // button panel
        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new GridLayout(6, 1));

        JLabel headerLabel1 = new JLabel(adminUserMenuButtonText, JLabel.CENTER);
        featurePanel.add(headerLabel1);

        adminUserMenuOption(user, featurePanel);

        mainPanel.add(featurePanel);
        adminUserFrame.add(mainPanel);
        adminUserFrame.setVisible(true);
    }

    /**
     * This method sets up the buttons and other menu items for the admin main frame
     * @param user the admin user opening this frame
     */
    private void adminUserMenuOption(IUserAccount user, JPanel featurePanel){

        String freezeUserLabelText = languageRB.getString("freezeUserLabelText");
        String adjustThresholdLabelText = languageRB.getString("adjustThresholdButtonText");
        String requestsLabelText = languageRB.getString("requestsLabelText");
        String cancelActionsLabelText = languageRB.getString("cancelLabelText");

        JButton freezeUser = new JButton(freezeUserLabelText);
        JButton adjustThreshold = new JButton(adjustThresholdLabelText);
        JButton requests = new JButton(requestsLabelText);
        JButton cancelActions = new JButton(cancelActionsLabelText);

        uiNavigator.formatButtonSize(freezeUser, featurePanel);
        uiNavigator.formatButtonSize(adjustThreshold, featurePanel);
        uiNavigator.formatButtonSize(requests, featurePanel);
        uiNavigator.formatButtonSize(cancelActions, featurePanel);
        try {
            uiNavigator.backToMainMenu(user, adminUserFrame, featurePanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        freezeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminFreezeUserPage.MenuScreen(user, languageRB);
                adminUserFrame.setVisible(false);
            }
        });
        adjustThreshold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String successMessage = languageRB.getString("successMessage");
                try {
                    adminOpGUINavigator.adjustThresholdNavigator(user);
                    JOptionPane.showMessageDialog(null,successMessage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        requests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRequestPage.MenuScreen(user, languageRB);
                adminUserFrame.setVisible(false);
            }
        });
        cancelActions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminCancelChoosePage.MenuScreen(user, languageRB);
                adminUserFrame.setVisible(false);
            }
        });
    }

}
