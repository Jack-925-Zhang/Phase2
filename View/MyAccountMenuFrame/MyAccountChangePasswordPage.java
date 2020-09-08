package View.MyAccountMenuFrame;

import Controller.UINavigator;
import Controller.UserManagerGUINavigator;
import Model.IUserAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * sets the change password page in my account menu
 * @author BINGQING WAN
 * @version 1.8
 */
public class MyAccountChangePasswordPage {

    ResourceBundle languageRB;
    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();

    /**
     * calls the menu frame for the change password
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MyAccountChangePasswordPage(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class MyAccountChangePasswordPage
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public MyAccountChangePasswordPage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        initializeChangePassword(user);
    }

    /**
     * this is the initializer for class MyAccountChangePasswordPage
     * @param user the user who sees this menu
     */
    private void initializeChangePassword(IUserAccount user){

        String changePasswordLabelText = languageRB.getString("changePasswordLabelText");
        String oldPasswordLabelText = languageRB.getString("oldPasswordLabelText");
        String newPasswordLabelText = languageRB.getString("newPasswordLabelText");
        String confirmNewPasswordLabelText = languageRB.getString("confirmNewPasswordLabelText");

        JFrame changePasswordFrame = new JFrame(changePasswordLabelText);
        changePasswordFrame.setSize(300,5*50);
        changePasswordFrame.setLocationRelativeTo(null);
        changePasswordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changePasswordFrame.setResizable(true);
        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 2));
        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));

        JLabel oldPasswordLabel = new JLabel(oldPasswordLabelText, JLabel.CENTER);
        infoPanel.add(oldPasswordLabel);
        JPasswordField oldPasswordInput = new JPasswordField();
        infoPanel.add(oldPasswordInput);
        JLabel newPasswordLabel  = new JLabel(newPasswordLabelText, JLabel.CENTER);
        infoPanel.add(newPasswordLabel);
        JPasswordField newPasswordInput = new JPasswordField();
        infoPanel.add(newPasswordInput);
        JLabel confirmPasswordLabel  = new JLabel(confirmNewPasswordLabelText, JLabel.CENTER);
        infoPanel.add(confirmPasswordLabel);
        JPasswordField confirmPasswordInput = new JPasswordField();
        infoPanel.add(confirmPasswordInput);

        String completeButtonText = languageRB.getString("completeButtonText");
        String myAccountButtonText = languageRB.getString("myAccountButtonText");

        JButton btnComplete = new JButton(completeButtonText);
        uiNavigator.formatButtonSize(btnComplete, buttonPanel);
        JButton btnMyAccount = new JButton(myAccountButtonText);
        uiNavigator.formatButtonSize(btnMyAccount, buttonPanel);

        btnComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean oldCorrect = true;
                boolean newCorrect = true;
                boolean compareCorrect = true;
                if (!oldPasswordInput.getText().equals(user.getPassword())){
                    String wrongOldPasswordMessage = languageRB.getString("wrongOldPasswordMessage");

                    oldCorrect = false;
                    JOptionPane.showMessageDialog(null, wrongOldPasswordMessage);
                }
                if (!newPasswordInput.getText().equals(confirmPasswordInput.getText())){
                    String confirmationNotMatchMessage = languageRB.getString("confirmationNotMatchMessage");

                    newCorrect = false;
                    JOptionPane.showMessageDialog(null, confirmationNotMatchMessage);
                }
                if (newPasswordInput.getText().equals(oldPasswordInput.getText())){
                    String samePasswordMessage = languageRB.getString("samePasswordMessage");
                    compareCorrect = false;
                    JOptionPane.showMessageDialog(null, samePasswordMessage);
                }
                else if (oldCorrect&&newCorrect&&compareCorrect) {
                    String successMessage = languageRB.getString("successMessage");
                    userManagerGUINavigator.changeUserPasswordNavigator(user, newPasswordInput.getText());
                    JOptionPane.showMessageDialog(null, successMessage);
                    MyAccountMenu.MenuScreen(user, languageRB);
                    changePasswordFrame.setVisible(false);
                }
            }
        });

        btnMyAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyAccountMenu.MenuScreen(user, languageRB);
                changePasswordFrame.setVisible(false);
            }
        });

        mainPanel.add(infoPanel);
        mainPanel.add(buttonPanel);
        changePasswordFrame.add(mainPanel);
        changePasswordFrame.setVisible(true);
    }
}
