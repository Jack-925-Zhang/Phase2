package View;

import Controller.TradingSystemGUINavigator;
import Controller.UINavigator;
import UseCases.TradingSystemInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * sets register menu
 * @author BINGQING WAN
 * @version 1.8
 */
public class RegisterMenu {

    JFrame regFrame;
    ResourceBundle languageRB;
    UINavigator uiNavigator = UINavigator.getInstance();
    TradingSystemGUINavigator tradingSystemGUINavigator = TradingSystemGUINavigator.getInstance();

    /**
     * calls the menu frame for the register menu
     * @param tradingSystem trading system
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(TradingSystemInterface tradingSystem, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    RegisterMenu registerMenu = new RegisterMenu(tradingSystem, languageRB);
                    registerMenu.regFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor for the register menu
     * @param tradingSystem trading system
     * @param languageRB the language bundle chosen by user
     */
    public RegisterMenu(TradingSystemInterface tradingSystem, ResourceBundle languageRB){
        initializeRegister(tradingSystem,languageRB);
    }

    /**
     * the initializer the menu frame for the register menu
     * @param tradingSystem trading system
     */
    private void initializeRegister(TradingSystemInterface tradingSystem, ResourceBundle languageRB){

        String registerWindowTitle = languageRB.getString("registerWindowTitle");
        regFrame = new JFrame(registerWindowTitle);
        regFrame.setSize(350,200);
        regFrame.setLocationRelativeTo(null);
        regFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // register user input panel
        JPanel regPanel = new JPanel();
        regPanel.setLayout(new GridLayout(3, 2));

        String userLabelText = languageRB.getString("userLabelText");
        JLabel userLabel  = new JLabel(userLabelText, JLabel.CENTER);
        regPanel.add(userLabel);

        JTextField userIdInput = new JTextField(20);
        regPanel.add(userIdInput);

        String passwordLabelText = languageRB.getString("passwordLabelText");
        JLabel passwordLabel  = new JLabel(passwordLabelText, JLabel.CENTER);
        regPanel.add(passwordLabel);

        JPasswordField passwordInput = new JPasswordField(20);
        regPanel.add(passwordInput);

        String cityLabelText = languageRB.getString("cityLabelText");
        JLabel cityLabel  = new JLabel(cityLabelText, JLabel.CENTER);
        regPanel.add(cityLabel);

        JTextField cityInput = new JTextField(20);
        regPanel.add(cityInput);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));

        String completeButtonText = languageRB.getString("completeButtonText");

        JButton btnComplete = new JButton(completeButtonText);
        uiNavigator.formatButtonSize(btnComplete, buttonPanel);

        String backButtonText = languageRB.getString("backButtonText");
        JButton btnBack = new JButton(backButtonText);
        uiNavigator.formatButtonSize(btnBack, buttonPanel);

        mainPanel.add(regPanel);
        mainPanel.add(buttonPanel);
        regFrame.add(mainPanel);
        regFrame.setVisible(true);

        btnComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userIdInput.getText();
                String password = passwordInput.getText();
                String city = cityInput.getText();
                try {
                    boolean registered = tradingSystemGUINavigator.registerNavigator(id, password, city);
                    if (registered == true) {
                        regFrame.setVisible(false);
                        String loginSuccessMessage = languageRB.getString("successMessage");
                        JOptionPane.showMessageDialog(null, loginSuccessMessage);
                        new LoginMenu(languageRB);
                    } else {
                        String idExistsMessage = languageRB.getString("idExistsMessage");
                        JOptionPane.showMessageDialog(null, idExistsMessage);
                    }
                } catch (Exception ioException) {
                    ioException.printStackTrace();
                }
            }

        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginMenu(languageRB);
                regFrame.setVisible(false);
            }
        });
    }
}

