package View.MyTradesMenu;

import Controller.*;
import Model.ITransaction;
import Model.IUserAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

/**
 * sets the page to take user input to check confirmed transactions
 * and change any to completed transactions if real-world trading is complete
 * @author BINGQING WAN
 * @version 1.8
 */
public class MyConfirmedTransactionsPage {
    ResourceBundle languageRB;
    MeetingSystemGUINavigator meetingSystemGUINavigator = MeetingSystemGUINavigator.getInstance();
    TransGUINavigator transGUINavigator = TransGUINavigator.getInstance();
    UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    UserTransactionOpGUINavigator userTransactionOpGUINavigator = UserTransactionOpGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    MyTradesMenu myTradesMenu;
    /**
     * The constructor for MyConfirmedTransactionsPage and initialize the frame properties and buttons
     * @param user the user opening this window
     * @param confirmedTrans a list of confirmed transactions
     * @param languageRB user selected language
     */
    public MyConfirmedTransactionsPage(IUserAccount user, List<Integer> confirmedTrans, ResourceBundle languageRB){
        this.languageRB = languageRB;
        this.myTradesMenu = new MyTradesMenu(user, languageRB);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initializeConfirm(user, confirmedTrans, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * This method initializes the frame for user to view all confirmed transactions and choose to complete
     * @param user the user opening this window
     * @param confirmedTrans a list of confirmed transactions
     * @param languageRB user selected language
     */
    public void initializeConfirm(IUserAccount user, List<Integer> confirmedTrans, ResourceBundle languageRB) {
        String confirmedTransLabelText = languageRB.getString("confirmedTransLabelText");
        String transIdLabelText = languageRB.getString("transIdLabelText");
        String successMessage = languageRB.getString("successMessage");
        String tradeText = languageRB.getString("trades");

        JFrame confirmedFrame = new JFrame();
        confirmedFrame.setTitle(confirmedTransLabelText);
        confirmedFrame.setSize(400,(confirmedTrans.size()+4)*50);
        confirmedFrame.setLocationRelativeTo(null);
        confirmedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmedFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // sub-panels
        JPanel transInfoPanel = new JPanel();
        JPanel transButtonPanel = new JPanel();
        JPanel backMenuPanel = new JPanel();

        transButtonPanel.setLayout(new GridLayout(1, 3));
        backMenuPanel.setLayout(new GridLayout(2, 1));

        if (confirmedTrans.size() == 0) {
            String noTransLabelText = languageRB.getString("noTransLabelText");
            transInfoPanel.setLayout(new GridLayout(1, 1));
            JLabel transLabel = new JLabel(noTransLabelText, JLabel.CENTER);
            transInfoPanel.add(transLabel);
        } else {
            transInfoPanel.setLayout(new GridLayout(confirmedTrans.size() + 1, 1));
            JLabel titleLabel = new JLabel(confirmedTransLabelText, JLabel.CENTER);
            transInfoPanel.add(titleLabel);
            for (int transId : confirmedTrans) {
                String partnerLabelText = languageRB.getString("partnerLabelText");
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
                JLabel transLabel = new JLabel(transIdLabelText + " " + transId +
                        ", " + partnerLabelText + " " + partnerId);
                transInfoPanel.add(transLabel);
            }
        }

        String completeButtonText = languageRB.getString("completeButtonText");

        JLabel completeTransLabel = new JLabel(transIdLabelText);
        transButtonPanel.add(completeTransLabel);

        JTextField completeTransInput = new JTextField();
        transButtonPanel.add(completeTransInput);

        JButton btnComplete = new JButton(completeButtonText);
        uiNavigator.formatButtonSize(btnComplete, transButtonPanel);

        try {
            myTradesMenu.backToTradesMenu(user, confirmedFrame, backMenuPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            uiNavigator.backToMainMenu(user, confirmedFrame, backMenuPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String transNotFoundMessage = languageRB.getString("transNotFoundMessage");
        String noTextMessage = languageRB.getString("noTextMessage");

        mainPanel.add(transInfoPanel);
        mainPanel.add(transButtonPanel);
        mainPanel.add(backMenuPanel);
        confirmedFrame.add(mainPanel);
        confirmedFrame.setVisible(true);

        btnComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean emptyInput = true;
                boolean cannotFind = true;
                int transId = 0;
                ITransaction confirmedT = null;
                try
                {
                    transId = Integer.parseInt(completeTransInput.getText());
                    if (completeTransInput.getText().isEmpty()) {
                        emptyInput = false;
                        JOptionPane.showMessageDialog(null, noTextMessage);
                    }
                    else if (!confirmedTrans.contains(transId)){
                        cannotFind = false;
                        JOptionPane.showMessageDialog(null, transNotFoundMessage);
                    }
                    else if (emptyInput&&cannotFind){
                        confirmedT = meetingSystemGUINavigator.getTransByIdNavigator(transId);
                        userTransactionOpGUINavigator.confirmRealWorldTradeNavigator(user, confirmedT);
                        JOptionPane.showMessageDialog(null, successMessage);
                    }
                }
                catch (NumberFormatException numberFormatException)
                {
                    JOptionPane.showMessageDialog(null, transNotFoundMessage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
