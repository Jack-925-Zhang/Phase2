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
 * sets the page to take user input to choose an invited transaction to edit
 * @author BINGQING WAN
 * @version 1.8
 */
public class ChooseInvitedTransEditPage {
    ResourceBundle languageRB;
    MeetingSystemGUINavigator meetingSystemGUINavigator = MeetingSystemGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    /**
     * The constructor for ChooseInvitedTransEditPage and initialize the frame properties and buttons
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    public ChooseInvitedTransEditPage(IUserAccount user, List<Integer> invitedTrans, ResourceBundle languageRB){
        this.languageRB = languageRB;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initializeChooseEdit(user, invitedTrans, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method initializes the frame for user to choose an invited transaction to edit
     * @param user the user opening this window
     * @param invitedTrans a list of invited transactions
     * @param languageRB user selected language
     */
    public void initializeChooseEdit(IUserAccount user, List<Integer> invitedTrans, ResourceBundle languageRB) {
        String invitedTransLabelText = languageRB.getString("invitedTransLabelText");
        String transIdLabelText = languageRB.getString("transIdLabelText");
        String editButtonText = languageRB.getString("editButtonText");

        JFrame editFrame = new JFrame();
        editFrame.setTitle(invitedTransLabelText);
        editFrame.setSize(300, 3 * 50);
        editFrame.setLocationRelativeTo(null);
        editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // sub-panels
        JPanel transEditPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        transEditPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setLayout(new GridLayout(2, 1));

        JLabel transIdLabel = new JLabel(transIdLabelText, JLabel.CENTER);
        transEditPanel.add(transIdLabel);
        JTextField transIdInput = new JTextField();
        transEditPanel.add(transIdInput);

        JButton btnEdit = new JButton(editButtonText);
        uiNavigator.formatButtonSize(btnEdit,buttonPanel);

        JButton btnClose = new JButton(invitedTransLabelText);
        uiNavigator.formatButtonSize(btnClose,buttonPanel);

        mainPanel.add(transEditPanel);
        mainPanel.add(buttonPanel);
        editFrame.add(mainPanel);
        editFrame.setVisible(true);

        String transNotFoundMessage = languageRB.getString("transNotFoundMessage");
        String noTextMessage = languageRB.getString("noTextMessage");

        btnEdit.addActionListener(new ActionListener() {
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
                        new EditInvitedTransactionsPage(user, invitedT, languageRB);
                        editFrame.setVisible(false);
                    }
                }
                catch (NumberFormatException numberFormatException)
                {
                    JOptionPane.showMessageDialog(null, transNotFoundMessage);
                }
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyInvitedTransactionsPage(user, invitedTrans, languageRB);
                editFrame.setVisible(false);
            }
        });
    }
}
