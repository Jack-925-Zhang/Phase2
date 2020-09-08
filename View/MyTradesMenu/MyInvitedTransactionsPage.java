package View.MyTradesMenu;

import Controller.*;
import Model.IUserAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

/**
 * sets the page to view all invited transactions
 * and choose to edit, confirm, or decline any of them
 * @author BINGQING WAN
 * @version 1.8
 */
public class MyInvitedTransactionsPage {

    ResourceBundle languageRB;
    MeetingSystemGUINavigator meetingSystemGUINavigator = MeetingSystemGUINavigator.getInstance();
    UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    TransGUINavigator transGUINavigator = TransGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    MyTradesMenu myTradesMenu;
    /**
     * The constructor for MyInvitedTransactionsPage and initialize the frame properties and buttons
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    public MyInvitedTransactionsPage(IUserAccount user, List<Integer> invitedTrans, ResourceBundle languageRB){
        this.languageRB = languageRB;
        this.myTradesMenu = new MyTradesMenu(user, languageRB);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initializeInvited(user, invitedTrans, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * This method initializes the frame for user to view all invited transactions and make changes to them
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    private void initializeInvited(IUserAccount user, List<Integer> invitedTrans, ResourceBundle languageRB) {

        String invitedTransLabelText = languageRB.getString("invitedTransLabelText");
        String transIdLabelText = languageRB.getString("transIdLabelText");

        JFrame invitedFrame = new JFrame();
        invitedFrame.setTitle(invitedTransLabelText);
        invitedFrame.setSize(400,(invitedTrans.size()+4)*50);
        invitedFrame.setLocationRelativeTo(null);
        invitedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        invitedFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // sub-panels
        JPanel transInfoPanel = new JPanel();
        JPanel transButtonPanel = new JPanel();
        JPanel backMenuPanel = new JPanel();

        transButtonPanel.setLayout(new GridLayout(1, 3));
        backMenuPanel.setLayout(new GridLayout(2, 1));

        if (invitedTrans.size() == 0) {
            String noTransLabelText = languageRB.getString("noTransLabelText");
            transInfoPanel.setLayout(new GridLayout(1, 1));
            JLabel transLabel = new JLabel(noTransLabelText, JLabel.CENTER);
            transInfoPanel.add(transLabel);
        } else {
            transInfoPanel.setLayout(new GridLayout(invitedTrans.size()+1, 1));
            JLabel titleLabel = new JLabel(invitedTransLabelText, JLabel.CENTER);
            transInfoPanel.add(titleLabel);
            for (int transId : invitedTrans) {
                String isUpdated = null;
                try {
                    if (meetingSystemGUINavigator.isUpdatedNavigator(
                            meetingSystemGUINavigator.getMeetingByTransIdNavigator(transId))) {
                        String updatedMessage = languageRB.getString("updatedMessage");
                        isUpdated = updatedMessage + " ";
                    } else {
                        String unchangedMessage = languageRB.getString("unchangedMessage");
                        isUpdated = unchangedMessage + " ";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                IUserAccount startUser =
                        transGUINavigator.getStartUserNavigator(
                                meetingSystemGUINavigator.getTransByIdNavigator(transId));
                IUserAccount targetUser =
                        transGUINavigator.getTargetUserNavigator(
                                meetingSystemGUINavigator.getTransByIdNavigator(transId));
                String partnerId;

                if (userAccountGUINavigator.getUserIdNavigator(user).equals(
                        userAccountGUINavigator.getUserIdNavigator(startUser))) {
                    partnerId = userAccountGUINavigator.getUserIdNavigator(targetUser);
                } else {
                    partnerId = userAccountGUINavigator.getUserIdNavigator(startUser);
                }

                String partnerLabelText = languageRB.getString("partnerLabelText");

                JLabel transLabel = new JLabel(isUpdated + transIdLabelText + " " + transId +
                        ", " + partnerLabelText + " " + partnerId);
                transInfoPanel.add(transLabel);
            }
        }

        String editButtonText = languageRB.getString("editButtonText");
        String confirmButtonText = languageRB.getString("confirmButtonText");
        String declineButtonText = languageRB.getString("rejectButtonText");

        JButton btnEdit = new JButton(editButtonText);
        uiNavigator.formatButtonSize(btnEdit, transButtonPanel);

        JButton btnConfirm = new JButton(confirmButtonText);
        uiNavigator.formatButtonSize(btnConfirm, transButtonPanel);

        JButton btnDecline = new JButton(declineButtonText);
        uiNavigator.formatButtonSize(btnDecline, transButtonPanel);

        try {
            myTradesMenu.backToTradesMenu(user, invitedFrame, backMenuPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            uiNavigator.backToMainMenu(user, invitedFrame, backMenuPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChooseInvitedTransEditPage(user, invitedTrans, languageRB);
                invitedFrame.setVisible(false);
            }
        });

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChooseInvitedTransConfirmPage(user, invitedTrans, languageRB);
            }
        });

        btnDecline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChooseInvitedTransDeclinePage(user, invitedTrans, languageRB);
            }
        });

        mainPanel.add(transInfoPanel);
        mainPanel.add(transButtonPanel);
        mainPanel.add(backMenuPanel);
        invitedFrame.add(mainPanel);
        invitedFrame.setVisible(true);
    }

}
