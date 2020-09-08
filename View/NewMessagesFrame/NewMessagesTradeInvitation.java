package View.NewMessagesFrame;

import Controller.*;
import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * the class for trade invitation messages page
 * @author Hongyu Chen
 * @version 1.8
 */
public class NewMessagesTradeInvitation extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final MeetingSystemGUINavigator meetingSystemGUINavigator = MeetingSystemGUINavigator.getInstance();
    private final TransGUINavigator transGUINavigator = TransGUINavigator.getInstance();
    private final UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    private final ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    private final UserMessagesOpGUINavigator userMessagesOpGUINavigator = UserMessagesOpGUINavigator.getInstance();
    JFrame tradeInvitationFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the trade invitation message page
     * @param user the user who sees this page
     * @param invitedTransId the id of the invited transaction
     * @param invitationMessage the message of this invitation
     * @param languageRB the language bundle chosen by user
     */
    static void tradeInvitationScreen(IUserAccount user,
                                      int invitedTransId, String invitationMessage,
                                      ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    NewMessagesTradeInvitation window =
                            new NewMessagesTradeInvitation(user, invitedTransId, invitationMessage, languageRB);
                    window.tradeInvitationFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor for NewMessagesTradeInvitation
     * @param user the user who sees this page
     * @param invitedTransId the id of the invited transaction
     * @param invitationMessage the message of this invitation
     * @param languageRB the language bundle chosen by user
     */
    NewMessagesTradeInvitation(IUserAccount user,
                               int invitedTransId, String invitationMessage,
                               ResourceBundle languageRB) {
        this.languageRB = languageRB;
        initTradeInvitation(user, invitedTransId, invitationMessage);
    }

    /**
     * the initializer for NewMessagesTradeInvitation
     * @param user the user who sees this page
     * @param invitedTransId the id of the invited transaction
     * @param invitationMessage the message of this invitation
     */
    private void initTradeInvitation(IUserAccount user, int invitedTransId, String invitationMessage) {

        tradeInvitationFrame = new JFrame();
        tradeInvitationFrame.setTitle(invitationMessage);
        tradeInvitationFrame.setSize(1025,600);
        tradeInvitationFrame.setLocationRelativeTo(null);
        tradeInvitationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tradeInvitationFrame.getContentPane().setLayout(null);
        tradeInvitationFrame.setVisible(true);

        tradeInvitationFrame.setResizable(true);
        tradeInvitationFrame.setLayout(new GridLayout(5, 1));
        uiNavigator.initBgColour(tradeInvitationFrame);

        JLabel headerLabel = new JLabel(invitationMessage, JLabel.CENTER);

        tradeInvitationFrame.add(headerLabel);

        // add JPanel
        JPanel panel = new JPanel();
        tradeInvitationFrame.add(panel);

        tradeInvitationOption(user, invitedTransId, invitationMessage, panel);

    }

    /**
     * sets the options shown on the window for trade invitation message page
     * @param user the user who sees this page
     * @param invitedTransId the id of the invited transaction
     * @param panel the JPanel for the buttons and labels
     */
    private void tradeInvitationOption(IUserAccount user, int invitedTransId,
                                       String invitationMessage, JPanel panel) {

        ITransaction invitedTrans = meetingSystemGUINavigator.getTransByIdNavigator(invitedTransId);
        ItemInterface wantedItem = transGUINavigator.getStartUserWantToTradeItemNavigator(invitedTrans);
        JLabel transInfo = new JLabel(transGUINavigator.transToStringNavigator(invitedTrans));
        tradeInvitationFrame.add(transInfo);

        String confirmButtonText = languageRB.getString("confirmButtonText");
        String rejectButtonText = languageRB.getString("rejectButtonText");
        String tradeText = languageRB.getString("trades");

        JButton confirmation = new JButton(confirmButtonText);
        JButton rejection = new JButton(rejectButtonText);

        confirmation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String confirmationMessage = userAccountGUINavigator.getUserIdNavigator(user) + " " +
                        confirmButtonText + " " + tradeText + " " +
                        itemGUINavigator.getItemNameNavigator(wantedItem) + ". ";
                try {
                    userMessagesOpGUINavigator.addNotificationNavigates(
                            confirmationMessage, userAccountGUINavigator.getUserIdNavigator(user));
                    userMessagesOpGUINavigator.removeInviteTransNewMessageNavigates(
                            invitedTransId, invitationMessage, userAccountGUINavigator.getUserIdNavigator(user));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                NewMessagesInterface.MenuScreen(user, languageRB);
                tradeInvitationFrame.setVisible(false);
            }
        });
        rejection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rejectionMessage = userAccountGUINavigator.getUserIdNavigator(user) + " " +
                        rejectButtonText + " " + tradeText + " " +
                        itemGUINavigator.getItemNameNavigator(wantedItem) + ". ";
                try {
                    userMessagesOpGUINavigator.addNotificationNavigates(
                            rejectionMessage, userAccountGUINavigator.getUserIdNavigator(user));
                    userMessagesOpGUINavigator.removeInviteTransNewMessageNavigates(
                            invitedTransId, invitationMessage, userAccountGUINavigator.getUserIdNavigator(user));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                NewMessagesInterface.MenuScreen(user, languageRB);
                tradeInvitationFrame.setVisible(false);
            }
        });

        panel.add(confirmation);
        panel.add(rejection);

    }

}
