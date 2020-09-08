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
 * This class sets the page to display the main page to check user's transactions
 * @author BINGQING WAN
 * @version 1.8
 */
public class MyTradesTransactionsPage {

    ResourceBundle languageRB;
    UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    MyTradesMenu myTradesMenu;

    /**
     * constructor for the transaction page
     * @param user the user opening this page
     * @param languageRB ths user selected language
     */
    public MyTradesTransactionsPage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        this.myTradesMenu = new MyTradesMenu(user, languageRB);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initializeTrans(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method initializes the frame and also buttons for the transaction page
     * @param user the user opening this page
     * @param languageRB ths user selected language
     */
    private void initializeTrans(IUserAccount user, ResourceBundle languageRB) {

        String myTransButtonText = languageRB.getString("myTransButtonText");
        JFrame transMenuFrame = new JFrame();
        transMenuFrame.setTitle(myTransButtonText);
        transMenuFrame.setSize(400,250);
        transMenuFrame.setLocationRelativeTo(null);
        transMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        transMenuFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // page title panel
        JPanel transInfoPanel = new JPanel();

        // page button panel
        JPanel transButtonPanel = new JPanel();

        // button to go back to other menus
        JPanel backMenuPanel = new JPanel();

        List<Integer> invitedTrans = userOpGUINavigator.getInvitedTransNavigator(user);
        List<Integer> confirmedTrans = userOpGUINavigator.getConfirmedTransactionNavigator(user);

        transInfoPanel.setLayout(new GridLayout(1, 1));
        transButtonPanel.setLayout(new GridLayout(2, 2));
        backMenuPanel.setLayout(new GridLayout(2, 1));

        String checkButtonText = languageRB.getString("checkButtonText");
        String invitedTransLabelText = languageRB.getString("invitedTransLabelText");
        String confirmedTransLabelText = languageRB.getString("confirmedTransLabelText");

        JLabel headerLabel = new JLabel(myTransButtonText, JLabel.CENTER);
        transInfoPanel.add(headerLabel);

        JLabel confirmedLabel = new JLabel(confirmedTrans.size() + " " + confirmedTransLabelText + ".", JLabel.CENTER);
        transButtonPanel.add(confirmedLabel);

        JButton btnCheckConfirmed = new JButton(confirmedTransLabelText);
        uiNavigator.formatButtonSize(btnCheckConfirmed, transButtonPanel);

        JLabel invitedLabel = new JLabel(invitedTrans.size() + " " + invitedTransLabelText + ".", JLabel.CENTER);
        transButtonPanel.add(invitedLabel);

        JButton btnCheckInvited = new JButton(checkButtonText);
        uiNavigator.formatButtonSize(btnCheckInvited, transButtonPanel);

        try {
            myTradesMenu.backToTradesMenu(user, transMenuFrame, backMenuPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            uiNavigator.backToMainMenu(user, transMenuFrame, backMenuPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnCheckInvited.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyInvitedTransactionsPage(user, invitedTrans, languageRB);
                transMenuFrame.dispose();
            }
        });

        btnCheckConfirmed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyConfirmedTransactionsPage(user, confirmedTrans, languageRB);
                transMenuFrame.dispose();
            }
        });

        mainPanel.add(transInfoPanel);
        mainPanel.add(transButtonPanel);
        mainPanel.add(backMenuPanel);
        transMenuFrame.add(mainPanel);
        transMenuFrame.setVisible(true);
    }

}
