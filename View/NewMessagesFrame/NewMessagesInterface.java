package View.NewMessagesFrame;

import Controller.UINavigator;
import Controller.UserAccountGUINavigator;
import Controller.UserMessagesOpGUINavigator;
import Controller.UserOpGUINavigator;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.List;

/**
 * the class for new messages interface
 * @author Hongyu Chen
 * @version 1.8
 */
public class NewMessagesInterface extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    private final UserMessagesOpGUINavigator userMessagesOpGUINavigator = UserMessagesOpGUINavigator.getInstance();
    private final UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();

    JFrame newMessagesPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the new message interface
     * @param user the user who sees this page
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    NewMessagesInterface window = new NewMessagesInterface(user, languageRB);
                    window.newMessagesPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor for the new message interface
     * @param user the user who sees this page
     * @param languageRB the language bundle chosen by user
     */
    public NewMessagesInterface(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.languageRB = languageRB;
        init(user);
    }

    /**
     * this is the initializer for class BorrowItemsMenu
     * @param user the user who sees this page
     */
    private void init(IUserAccount user) throws Exception {

        newMessagesPageFrame = new JFrame();
        newMessagesPageFrame.setTitle("New Messages ");
        newMessagesPageFrame.setSize(1025,600);
        newMessagesPageFrame.setLocationRelativeTo(null);
        newMessagesPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newMessagesPageFrame.getContentPane().setLayout(null);
        newMessagesPageFrame.setVisible(true);

        newMessagesPageFrame.setResizable(true);
        newMessagesPageFrame.setLayout(new GridLayout(5, 1));
        uiNavigator.initBgColour(newMessagesPageFrame);

        JLabel headerLabel = new JLabel("New Messages ", JLabel.CENTER);

        newMessagesPageFrame.add(headerLabel);

        // add JPanel
        JPanel panel = new JPanel();
        newMessagesPageFrame.add(panel);

        newMessagesPageOption(user, panel);

    }

    /**
     * sets the options shown on the window for new messages interface
     * @param user the user who sees this page
     * @param panel the JPanel for the buttons and labels
     */
    private void newMessagesPageOption(IUserAccount user, JPanel panel) throws Exception {

        List<String> notifications = userOpGUINavigator.getNotificationsNavigator(user);
        Map<Integer, String> inviteTransInvitations = userOpGUINavigator.getInviteTransNewMessagesNavigator(user);

        for (String notification : notifications) {
            JButton notificationButton = new JButton(notification);
            panel.add(notificationButton);

            notificationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        userMessagesOpGUINavigator.removeNotificationNavigates(
                                notification, userAccountGUINavigator.getUserIdNavigator(user));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }
        for (Map.Entry<Integer, String> tradeInvitation : inviteTransInvitations.entrySet()) {
            JButton tradeInvitationButton = new JButton(tradeInvitation.getValue());

            tradeInvitationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NewMessagesTradeInvitation.tradeInvitationScreen(
                            user, tradeInvitation.getKey(), tradeInvitation.getValue(), languageRB);
                    newMessagesPageFrame.setVisible(false);
                }
            });

            panel.add(tradeInvitationButton);
        }

        uiNavigator.backToMainMenu(user, newMessagesPageFrame, panel, languageRB);

    }

}
