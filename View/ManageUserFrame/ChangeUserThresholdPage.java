package View.ManageUserFrame;

import Controller.AdminOpGUINavigator;
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
 * sets the window to change a user's threshold value
 * @author BINGQING WAN
 * @version 1.8
 */
public class ChangeUserThresholdPage {
    JFrame changeUserThresholdFrame;
    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    AdminOpGUINavigator adminOpGUINavigator = AdminOpGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    ResourceBundle languageRB;

    /**
     * calls the menu frame for the change user threshold menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ChangeUserThresholdPage window = new ChangeUserThresholdPage(user, languageRB);
                    window.changeUserThresholdFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class ChangeUserThresholdPage
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public ChangeUserThresholdPage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        initUpgradeUserPage(user, languageRB);
    }

    /**
     * this is the initializer for class ChangeUserThresholdPage
     */
    private void initUpgradeUserPage(IUserAccount user, ResourceBundle languageRB) {

        String changeThresholdButtonText = languageRB.getString("changeThresholdButtonText");
        String confirmButtonText = languageRB.getString("confirmButtonText");
        String userIdText = languageRB.getString("userLabelText");
        String checkButtonText = languageRB.getString("getNormalUserButtonText");
        String thresholdLabelText = languageRB.getString("thresholdLabelText");
        String mainMenuButtonText = languageRB.getString("mainMenuButtonText");
        String manageUserButtonText = languageRB.getString("manageUserButtonText");

        changeUserThresholdFrame = new JFrame();
        changeUserThresholdFrame.setTitle(changeThresholdButtonText);
        changeUserThresholdFrame.setSize(350,350);
        changeUserThresholdFrame.setLocationRelativeTo(null);
        changeUserThresholdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changeUserThresholdFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1,1));
        JLabel titleLabel = new JLabel(changeThresholdButtonText, JLabel.CENTER);
        titlePanel.add(titleLabel);

        // input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2,2));
        JLabel inputLabel = new JLabel(userIdText, JLabel.CENTER);
        inputPanel.add(inputLabel);
        JTextField inputIdText = new JTextField();
        inputPanel.add(inputIdText);
        JLabel thresholdLabel = new JLabel(thresholdLabelText, JLabel.CENTER);
        inputPanel.add(thresholdLabel);
        JTextField inputThresholdText = new JTextField();
        inputPanel.add(inputThresholdText);

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
            uiNavigator.backToMainMenu(user, changeUserThresholdFrame, buttonPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainPanel.add(titlePanel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        changeUserThresholdFrame.add(mainPanel);

        HashMap<String, IUserAccount> normalUser = userManagerGUINavigator.getNormalUsersNavigator();

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IUserAccount nUser = userManagerGUINavigator.getUserByIdNavigator(inputIdText.getText());
                String upgradeFailMessage = languageRB.getString("upgradeUserFailMessage");
                String successMessage = languageRB.getString("successMessage");
                String nonIntegerFailMessage = languageRB.getString("nonIntegerFailMessage");
                int newThreshold = 0;
                if (nUser == null){
                    JOptionPane.showMessageDialog(null, upgradeFailMessage);
                }
                else {
                    try {
                        newThreshold = Integer.parseInt(inputThresholdText.getText());
                        adminOpGUINavigator.changeThresholdNavigator(user, nUser, newThreshold);
                        JOptionPane.showMessageDialog(null, successMessage);
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null, nonIntegerFailMessage);
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
                changeUserThresholdFrame.setVisible(false);
            }
        });
    }
}
