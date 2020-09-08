package View.MyTradesMenu;

import Controller.*;
import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

/**
 * sets the page to take user input to decline an invited transaction
 * @author BINGQING WAN
 * @version 1.8
 */
public class ChooseInvitedTransDeclinePage {
    ResourceBundle languageRB;
    MeetingSystemGUINavigator meetingSystemGUINavigator = MeetingSystemGUINavigator.getInstance();
    UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    TransGUINavigator transGUINavigator = TransGUINavigator.getInstance();
    UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    UserTransactionOpGUINavigator userTransactionOpGUINavigator = UserTransactionOpGUINavigator.getInstance();
    UserMessagesOpGUINavigator userMessagesOpGUINavigator = UserMessagesOpGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    /**
     * The constructor for ChooseInvitedTransDeclinePage and initialize the frame properties and buttons
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    public ChooseInvitedTransDeclinePage(IUserAccount user, List<Integer> invitedTrans, ResourceBundle languageRB){
        this.languageRB = languageRB;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initializeChooseDecline(user, invitedTrans, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method initializes the frame for user to decline an invited transaction
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    public void initializeChooseDecline(IUserAccount user, List<Integer> invitedTrans, ResourceBundle languageRB) {
        String invitedTransLabelText = languageRB.getString("invitedTransLabelText");
        String transIdLabelText = languageRB.getString("transIdLabelText");
        String rejectButtonText = languageRB.getString("rejectButtonText");
        String tradeText = languageRB.getString("trades");
        String closeButtonText = languageRB.getString("closeButtonText");

        JFrame declineFrame = new JFrame();
        declineFrame.setTitle(invitedTransLabelText);
        declineFrame.setSize(300, 3 * 50);
        declineFrame.setLocationRelativeTo(null);
        declineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        declineFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // sub-panels
        JPanel transDeclinePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        transDeclinePanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setLayout(new GridLayout(2, 1));

        JLabel transIdLabel = new JLabel(transIdLabelText, JLabel.CENTER);
        transDeclinePanel.add(transIdLabel);
        JTextField transIdInput = new JTextField();
        transDeclinePanel.add(transIdInput);

        JButton btnDecline = new JButton(rejectButtonText);
        uiNavigator.formatButtonSize(btnDecline, buttonPanel);

        JButton btnClose = new JButton(closeButtonText);
        uiNavigator.formatButtonSize(btnClose, buttonPanel);

        mainPanel.add(transDeclinePanel);
        mainPanel.add(buttonPanel);
        declineFrame.add(mainPanel);
        declineFrame.setVisible(true);

        String transNotFoundMessage = languageRB.getString("transNotFoundMessage");
        String noTextMessage = languageRB.getString("noTextMessage");
        String successMessage = languageRB.getString("successMessage");

        btnDecline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean emptyInput = true;
                boolean cannotFind = true;
                int transId = 0;
                ITransaction invitedT = null;
                try {
                    transId = Integer.parseInt(transIdInput.getText());
                    if (transIdInput.getText().isEmpty()) {
                        emptyInput = false;
                        JOptionPane.showMessageDialog(null, noTextMessage);
                    } else if (!invitedTrans.contains(transId)) {
                        cannotFind = false;
                        JOptionPane.showMessageDialog(null, transNotFoundMessage);
                    } else if (emptyInput && cannotFind) {
                        invitedT = meetingSystemGUINavigator.getTransByIdNavigator(transId);
                        List<ItemInterface> startUserWishToLend = userOpGUINavigator.getWishToLendNavigator(
                                transGUINavigator.getStartUserNavigator(invitedT));
                        IUserAccount startUser =
                                transGUINavigator.getStartUserNavigator(
                                        meetingSystemGUINavigator.getTransByIdNavigator(transId));
                        String rejectionMessage = user.getUserId() + " " + rejectButtonText + " " + tradeText
                                + " " + startUserWishToLend.get(0).getItemName();
                        try {
                            userTransactionOpGUINavigator.declineTransNavigator(invitedT);
                            userMessagesOpGUINavigator.addNotificationNavigates(
                                    rejectionMessage, userAccountGUINavigator.getUserIdNavigator(startUser));
                            JOptionPane.showMessageDialog(null, successMessage);
                            declineFrame.setVisible(false);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, transNotFoundMessage);
                }
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                declineFrame.setVisible(false);
            }
        });
    }
}
