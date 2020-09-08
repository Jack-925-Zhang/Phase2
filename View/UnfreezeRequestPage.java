package View;

import Controller.AdminOpGUINavigator;
import Controller.UserAccountGUINavigator;
import Controller.UserManagerGUINavigator;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * the class for requesting for unfreezing page
 * @author Hongyu Chen
 * @version 1.8
 */
public class UnfreezeRequestPage extends JFrame {

    private final AdminOpGUINavigator adminOpGUINavigator = AdminOpGUINavigator.getInstance();
    private final UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    private final UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();

    JFrame unfreezeRequestFrame;
    ResourceBundle languageRB;

    /**
     * calls the request for unfreezing page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UnfreezeRequestPage window = new UnfreezeRequestPage(user, languageRB);
                    window.unfreezeRequestFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class UnfreezeRequestPage
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    UnfreezeRequestPage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        init(user);
    }

    /**
     * the initializer for class UnfreezeRequestPage
     * @param user the user who sees this menu
     */
    private void init(IUserAccount user) {

        unfreezeRequestFrame = new JFrame();

        String unfreezeRequestText = languageRB.getString("unfreezeRequestText");

        unfreezeRequestFrame.setTitle(unfreezeRequestText);

        unfreezeRequestFrame.setSize(350,4*50);
        unfreezeRequestFrame.setLocationRelativeTo(null);
        unfreezeRequestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        unfreezeRequestFrame.setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(3,1));

        JLabel headerLabel1 = new JLabel(unfreezeRequestText, JLabel.CENTER);
        optionPanel.add(headerLabel1);
        unfreezeRequestOption(user, optionPanel);
        mainPanel.add(optionPanel);
        unfreezeRequestFrame.add(mainPanel);
    }

    /**
     * sets the options shown on the window for request for unfreezing page
     * @param user the user who sees this menu
     * @param optionPanel the JPanel for the buttons
     */
    private void unfreezeRequestOption(IUserAccount user, JPanel optionPanel) {

        String unfreezeRequestText = languageRB.getString("unfreezeRequestText");
        String backButtonText = languageRB.getString("backButtonText");

        JButton unfreezeRequest = new JButton(unfreezeRequestText);
        JButton loginMenu = new JButton(backButtonText);

        unfreezeRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userManagerGUINavigator.requestToUnfreezeNavigator(user);

                String requestMessage = userAccountGUINavigator.getUserIdNavigator(user) + ": " + unfreezeRequestText;

                Map<String, IUserAccount> adminUsers = userManagerGUINavigator.getAdminUsersNavigator();
                Random random = new Random();
                int randomAdminUserIndex = random.nextInt(adminUsers.size() + 1);
                IUserAccount chosenAdmin;
                int i = 0;
                for (Map.Entry<String, IUserAccount> adminUser : adminUsers.entrySet()) {
                    if (i == randomAdminUserIndex) {
                        chosenAdmin = adminUser.getValue();

                        try {
                            adminOpGUINavigator.addUnfreezeRequestUserNavigator(chosenAdmin, user);
                            adminOpGUINavigator.addUnfreezeNewMessageNavigator(
                                    chosenAdmin, userAccountGUINavigator.getUserIdNavigator(user), requestMessage);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                    i++;
                }

                new LoginMenu(languageRB);
                unfreezeRequestFrame.setVisible(false);
            }
        });
        loginMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginMenu(languageRB);
                unfreezeRequestFrame.setVisible(false);
            }
        });

        optionPanel.add(unfreezeRequest);
        optionPanel.add(loginMenu);

    }

}
