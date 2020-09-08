package View.MyTradesMenu;

import Controller.*;
import Model.IMeeting;
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
 * sets the page to take user input to edit an invited transaction
 * @author BINGQING WAN
 * @version 1.8
 */
public class EditInvitedTransactionsPage {
    ResourceBundle languageRB;
    MeetingSystemGUINavigator meetingSystemGUINavigator = MeetingSystemGUINavigator.getInstance();
    TransGUINavigator transGUINavigator = TransGUINavigator.getInstance();
    UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    UserTransactionOpGUINavigator userTransactionOpGUINavigator = UserTransactionOpGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    MyTradesMenu myTradesMenu;
    /**
     * The constructor for EditInvitedTransactionsPage and initialize the frame properties and buttons
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    public EditInvitedTransactionsPage(IUserAccount user, ITransaction invitedTrans, ResourceBundle languageRB){
        this.languageRB = languageRB;
        this.myTradesMenu = new MyTradesMenu(user, languageRB);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initializeEdit(user, invitedTrans, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * This method initializes the frame for user to edit an invited transaction
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    public void initializeEdit(IUserAccount user, ITransaction invitedTrans, ResourceBundle languageRB) throws Exception {
        String invitedTransLabelText = languageRB.getString("invitedTransLabelText");
        IMeeting meeting = meetingSystemGUINavigator.getMeetingByTransIdNavigator(invitedTrans.getTranId());
        String partnerLabelText = languageRB.getString("partnerLabelText");
        String partnerBorrowText = languageRB.getString("partnerBorrowText");
        String meetingLocationText = languageRB.getString("meetingLocationText");
        String meetingTimeText = languageRB.getString("meetingTimeText");
        JLabel partnerLabel = new JLabel(partnerLabelText+invitedTrans.getStartUserId());
        JLabel partnerBorrowLabel = new JLabel(partnerBorrowText);
        JLabel partnerBorrowInfoLabel = new JLabel(invitedTrans.getStartUserWantToTradeItem().getItemName());
        JLabel partnerBorrowInputLabel = new JLabel("");
        JTextField borrowInputTextField = new JTextField();
        JFrame editFrame = new JFrame();
        editFrame.setTitle(invitedTransLabelText);
        editFrame.setSize(600,9*50);
        editFrame.setLocationRelativeTo(null);
        editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editFrame.setResizable(true);
        JPanel mainPanel = new JPanel(); // main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel transInfoPanel = new JPanel(); // sub-panel
        JPanel transEditPanel = new JPanel();
        JPanel backMenuPanel = new JPanel();
        backMenuPanel.setLayout(new GridLayout(3, 1));
        if (invitedTrans.getOneOrTwo()==1){
            transInfoPanel.setLayout(new GridLayout(2, 1));
            transEditPanel.setLayout(new GridLayout(3, 3));
            String oneWayTradeTypeTextLabel = languageRB.getString("oneWayTradeTypeTextLabel");
            JLabel tradeTypeLabel = new JLabel(oneWayTradeTypeTextLabel);
            transInfoPanel.add(tradeTypeLabel);
            transInfoPanel.add(partnerLabel);
            transEditPanel.add(partnerBorrowLabel); // partner borrow info
            transEditPanel.add(partnerBorrowInfoLabel);
            transEditPanel.add(partnerBorrowInputLabel);
        } else {
            transInfoPanel.setLayout(new GridLayout(2, 1));
            transEditPanel.setLayout(new GridLayout(4, 3));
            String twoWayTradeTypeTextLabel = languageRB.getString("twoWayTradeTypeTextLabel");
            String borrowText = languageRB.getString("borrowText");
            JLabel borrowLabel = new JLabel(borrowText);
            JLabel borrowInfoLabel = new JLabel(invitedTrans.getTargetUserWantToTradItem().getItemName());
            JLabel tradeTypeLabel = new JLabel(twoWayTradeTypeTextLabel);
            transInfoPanel.add(tradeTypeLabel);
            transInfoPanel.add(partnerLabel);
            transEditPanel.add(partnerBorrowLabel); // partner borrow info
            transEditPanel.add(partnerBorrowInfoLabel);
            transEditPanel.add(partnerBorrowInputLabel);
            transEditPanel.add(borrowLabel); // your borrow info and change
            transEditPanel.add(borrowInfoLabel);
            transEditPanel.add(borrowInputTextField);
        }
        JLabel meetingLocationLabel = new JLabel(meetingLocationText); // meeting location
        JLabel meetingLocationInfoLabel = new JLabel(meeting.getPlace());
        JTextField meetingLocationInput = new JTextField();
        transEditPanel.add(meetingLocationLabel);
        transEditPanel.add(meetingLocationInfoLabel);
        transEditPanel.add(meetingLocationInput);
        JLabel meetingTimeLabel = new JLabel(meetingTimeText); // meeting time
        JLabel meetingTimeInfoLabel = new JLabel(meeting.getTime());
        JTextField meetingTimeInput = new JTextField();
        transEditPanel.add(meetingTimeLabel);
        transEditPanel.add(meetingTimeInfoLabel);
        transEditPanel.add(meetingTimeInput);
        String completeButtonText = languageRB.getString("completeButtonText"); // buttons
        String myTradesButtonText = languageRB.getString("myTradesButtonText");
        String mainMenuButtonText = languageRB.getString("mainMenuButtonText");

        JButton btnComplete = new JButton(completeButtonText);
        uiNavigator.formatButtonSize(btnComplete, backMenuPanel);

        try {
            myTradesMenu.backToTradesMenu(user, editFrame, backMenuPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            uiNavigator.backToMainMenu(user, editFrame, backMenuPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainPanel.add(transInfoPanel);
        mainPanel.add(transEditPanel);
        mainPanel.add(backMenuPanel);
        editFrame.add(mainPanel);
        editFrame.setVisible(true);

        btnComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String noInputMessage = languageRB.getString("noInputMessage");
                String noItemMessage = languageRB.getString("noItemMessage");
                String successMessage = languageRB.getString("successMessage");
                List<ItemInterface> startUserWishToLend = userOpGUINavigator.getWishToLendNavigator(
                        transGUINavigator.getStartUserNavigator(invitedTrans));
                List<ItemInterface> newItem = userManagerGUINavigator.getItemNavigator(
                        borrowInputTextField.getText(), startUserWishToLend);
                if (invitedTrans.getOneOrTwo()==1){
                    if (meetingLocationInput.getText().isEmpty()&&meetingTimeInput.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, noInputMessage);
                    } else {
                        try {
                            userTransactionOpGUINavigator.editTransactionNavigator(
                                    invitedTrans, null, meetingTimeInput.getText(),
                                    meetingLocationInput.getText(), invitedTrans.getTradeType(), user);
                            JOptionPane.showMessageDialog(null, successMessage);
                            editFrame.setVisible(false);
                            MyTradesMenu.MenuScreen(user, languageRB);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                } else {
                    if (meetingLocationInput.getText().isEmpty() && meetingTimeInput.getText().isEmpty() &&
                            borrowInputTextField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, noInputMessage);
                    }
                    else if (newItem==null){
                        JOptionPane.showMessageDialog(null, noItemMessage);
                    }
                    else {
                        try {
                            userTransactionOpGUINavigator.editTransactionNavigator(
                                    invitedTrans, newItem.get(0), meetingTimeInput.getText(),
                                    meetingLocationInput.getText(), invitedTrans.getTradeType(), user);
                            JOptionPane.showMessageDialog(null, successMessage);
                            editFrame.setVisible(false);
                            MyTradesMenu.MenuScreen(user, languageRB);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
