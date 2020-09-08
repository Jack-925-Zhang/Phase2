package View;

import Controller.TradingSystemGUINavigator;
import Controller.UINavigator;
import Model.IUserAccount;
import UseCases.TradingSystem;
import UseCases.TradingSystemInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.IOException;
import java.util.HashMap;

import java.util.ResourceBundle;

/**
 * sets login menu
 * @author BINGQING WAN
 * @version 1.8
 */
public class LoginMenu {

    TradingSystemGUINavigator tradingSystemGUINavigator = TradingSystemGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    ResourceBundle languageRB;

    /**
     * the constructor of class LoginMenu
     * @param languageRB the language bundle chosen by user
     */
    public LoginMenu(ResourceBundle languageRB) {
        this.languageRB = languageRB;

        String loginMenuTitle = languageRB.getString("loginMenuTitle");
        JFrame loginFrame = new JFrame(loginMenuTitle);
        loginFrame.setSize(350,320);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(2, 2));

        String userLabelText = languageRB.getString("userLabelText");
        JLabel userLabel  = new JLabel(userLabelText, JLabel.CENTER);
        loginPanel.add(userLabel);

        JTextField userIdInput = new JTextField(20);
        loginPanel.add(userIdInput);

        String passwordLabelText = languageRB.getString("passwordLabelText");
        JLabel passwordLabel  = new JLabel(passwordLabelText, JLabel.CENTER);
        loginPanel.add(passwordLabel);

        JPasswordField passwordInput = new JPasswordField(20);
        loginPanel.add(passwordInput);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));

        String registerButtonText = languageRB.getString("registerButtonText");
        JButton btnRegister = new JButton(registerButtonText);
        uiNavigator.formatButtonSize(btnRegister, buttonPanel);

        String normalLoginButtonText = languageRB.getString("normalLoginButtonText");
        JButton btnNormalLogin = new JButton(normalLoginButtonText);
        uiNavigator.formatButtonSize(btnNormalLogin, buttonPanel);

        String adminLoginButtonText = languageRB.getString("adminLoginButtonText");
        JButton btnAdminLogin = new JButton(adminLoginButtonText);
        uiNavigator.formatButtonSize(btnAdminLogin, buttonPanel);

        String guestLoginButtonText = languageRB.getString("guestLoginButtonText");
        JButton btnGuestLogin = new JButton(guestLoginButtonText);
        uiNavigator.formatButtonSize(btnGuestLogin, buttonPanel);

        String exitButtonText = languageRB.getString("exitButtonText");
        JButton btnExit = new JButton(exitButtonText);
        uiNavigator.formatButtonSize(btnExit, buttonPanel);

        String changeLanguageText = languageRB.getString("changeLanguageText");
        JButton btnChangeLanguage = new JButton(changeLanguageText);
        uiNavigator.formatButtonSize(btnChangeLanguage, buttonPanel);

        String wrongUserInfoMessage = languageRB.getString("wrongUserInfoMessage");
        String wrongUserTypeMessage = languageRB.getString("wrongUserTypeMessage");

        btnNormalLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userIdInput.getText();
                String password = passwordInput.getText();
                HashMap<Integer, IUserAccount> userLogin = tradingSystemGUINavigator.normalUserLoginNavigator(id, password);
                if (userLogin.containsKey(0)){ // success
                    Menu.MenuScreen(userLogin.get(0), languageRB);
                    loginFrame.setVisible(false);
                } else if (userLogin.containsKey(1)) { // fail due to wrong id/password
                    JOptionPane.showMessageDialog(null, wrongUserInfoMessage);
                } else if (userLogin.containsKey(2)) { // fail due to frozen account
                    String frozenMessage = languageRB.getString("frozenMessage");
                    JOptionPane.showMessageDialog(null, frozenMessage);
                    UnfreezeRequestPage.MenuScreen(userLogin.get(0), languageRB);
                } else if (userLogin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, wrongUserTypeMessage);
                }
            }
        });

        btnAdminLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userIdInput.getText();
                String password = passwordInput.getText();
                HashMap<Integer, IUserAccount> userLogin = tradingSystemGUINavigator.adminUserLoginNavigator(id,password);
                if (userLogin.containsKey(0)){ // success
                    Menu.MenuScreen(userLogin.get(0), languageRB);
                    loginFrame.setVisible(false);
                    tradingSystemGUINavigator.notifyAdminUserFreezingNavigator(userLogin.get(0));
                    //tradingSystem.notifyAdminUserFreezing(userLogin.get(0));
                } else if (userLogin.containsKey(1)) { // fail due to wrong id/password
                    JOptionPane.showMessageDialog(null, wrongUserInfoMessage);
                } else if (userLogin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, wrongUserTypeMessage);
                }
            }
        });

        btnGuestLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<Integer, IUserAccount> userLogin = tradingSystemGUINavigator.guestUserLoginNavigator();
                if (userLogin.containsKey(0)){ // success
                    Menu.MenuScreen(userLogin.get(0), languageRB);
                    loginFrame.setVisible(false);
                    //JOptionPane.showMessageDialog(null, loginSuccessMessage);
                } else if (userLogin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, wrongUserTypeMessage);
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterMenu.MenuScreen(tradingSystemGUINavigator.getTradingSystemNavigator(), languageRB);
                loginFrame.setVisible(false);
            }

        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tradingSystemGUINavigator.exitNavigator();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                loginFrame.setVisible(false);
            }
        });

        btnChangeLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseLanguagePage.chooseLanguageScreen();
                loginFrame.setVisible(false);
            }
        });
        mainPanel.add(loginPanel);
        mainPanel.add(buttonPanel);
        loginFrame.add(mainPanel);
        loginFrame.setVisible(true);
    }
}
