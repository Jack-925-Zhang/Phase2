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
 * sets the page to take user input to confirm an invited transaction
 * @author BINGQING WAN
 * @version 1.8
 */
public class ChooseInvitedTransConfirmPage {
    ResourceBundle languageRB;
    MeetingSystemGUINavigator meetingSystemGUINavigator = MeetingSystemGUINavigator.getInstance();
    UserTransactionOpGUINavigator userTransactionOpGUINavigator = UserTransactionOpGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    /**
     * The constructor for ChooseInvitedTransConfirmPage and initialize the frame properties and buttons
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    public ChooseInvitedTransConfirmPage(IUserAccount user, List<Integer> invitedTrans, ResourceBundle languageRB){
        this.languageRB = languageRB;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initializeChooseConfirm(user, invitedTrans, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method initializes the frame for user to confirm an invited transaction
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    public void initializeChooseConfirm(IUserAccount user, List<Integer> invitedTrans, ResourceBundle languageRB) {
        String invitedTransLabelText = languageRB.getString("invitedTransLabelText");
        String transIdLabelText = languageRB.getString("transIdLabelText");
        String confirmButtonText = languageRB.getString("confirmButtonText");
        String closeButtonText = languageRB.getString("closeButtonText");

        JFrame confirmFrame = new JFrame();
        confirmFrame.setTitle(invitedTransLabelText);
        confirmFrame.setSize(300, 3 * 50);
        confirmFrame.setLocationRelativeTo(null);
        confirmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // sub-panels
        JPanel transConfirmPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        transConfirmPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setLayout(new GridLayout(2, 1));

        JLabel transIdLabel = new JLabel(transIdLabelText, JLabel.CENTER);
        transConfirmPanel.add(transIdLabel);
        JTextField transIdInput = new JTextField();
        transConfirmPanel.add(transIdInput);

        JButton btnConfirm = new JButton(confirmButtonText);
        uiNavigator.formatButtonSize(btnConfirm, buttonPanel);
        JButton btnClose = new JButton(closeButtonText);
        uiNavigator.formatButtonSize(btnClose, buttonPanel);

        mainPanel.add(transConfirmPanel);
        mainPanel.add(buttonPanel);
        confirmFrame.add(mainPanel);
        confirmFrame.setVisible(true);

        String transNotFoundMessage = languageRB.getString("transNotFoundMessage");
        String noTextMessage = languageRB.getString("noTextMessage");
        String successMessage = languageRB.getString("successMessage");

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean emptyInput = true;
                boolean cannotFind = true;
                int transId = 0;
                ITransaction invitedT = null;
                try
                {
                    transId = Integer.parseInt(transIdInput.getText());
                    if (transIdInput.getText().isEmpty()) {
                        emptyInput = false;
                        JOptionPane.showMessageDialog(null, noTextMessage);
                    }
                    else if (!invitedTrans.contains(transId)){
                        cannotFind = false;
                        JOptionPane.showMessageDialog(null, transNotFoundMessage);
                    }
                    else if (emptyInput&&cannotFind){
                        invitedT = meetingSystemGUINavigator.getTransByIdNavigator(transId);
                        userTransactionOpGUINavigator.confirmTransNavigator(invitedT);
                        JOptionPane.showMessageDialog(null, successMessage);
                        confirmFrame.setVisible(false);
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

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmFrame.setVisible(false);
            }
        });
    }
}
