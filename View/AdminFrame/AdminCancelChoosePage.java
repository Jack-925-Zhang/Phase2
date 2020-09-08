package View.AdminFrame;

import Controller.*;
import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;
import Model.NormalUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCancelChoosePage {
    JFrame directToCancelFrame;
    UINavigator uiNavigator = UINavigator.getInstance();
    ResourceBundle languageRB;
    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    MeetingSystemGUINavigator meetingSystemGUINavigator = MeetingSystemGUINavigator.getInstance();
    TradingSystemGUINavigator tradingSystemGUINavigator = TradingSystemGUINavigator.getInstance();
    UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    TransGUINavigator transGUINavigator = TransGUINavigator.getInstance();
    AdminOpGUINavigator adminOpGUINavigator = AdminOpGUINavigator.getInstance();
    UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    JList AllUsers = new JList();
    JList ConfirmedTransaction= new JList();
    JList InvitedTrans= new JList();
    private IUserAccount selectedUser;
    private final ArrayList<IUserAccount> allUsers = new ArrayList<>();
    private final ArrayList<ITransaction> confirmedTransactionList = new ArrayList<>();
    private final ArrayList<ITransaction> invitedTransList = new ArrayList<>();

    /**
     * calls the menu frame for the upgrade user menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AdminCancelChoosePage window = new AdminCancelChoosePage(user, languageRB);
                    window.directToCancelFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class UpgradeUserPage
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public AdminCancelChoosePage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        selectedUser = new NormalUser(null,null,null,null);
        allUsers.addAll(userManagerGUINavigator.getNormalUsersNavigator().values());
        if(userAccountGUINavigator.getIsInitialAdminNavigator(user)){
            allUsers.addAll(userManagerGUINavigator.getAdminUsersNavigator().values());
        }
        initDirectToCancelPage(user);
    }

    /**
     * this method initializes the window properties and buttons for the UpgradeUserPage
     */
    private void initDirectToCancelPage(IUserAccount user) {

        String cancelLabelText = languageRB.getString("cancelLabelText");
        directToCancelFrame = new JFrame();
        directToCancelFrame.setTitle(cancelLabelText);
        directToCancelFrame.setSize(1200,(allUsers.size())*20+150);
        directToCancelFrame.setLocationRelativeTo(null);
        directToCancelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        directToCancelFrame.setResizable(true);

        JPanel content = new JPanel();
        directToCancelFrame.getContentPane().add(content);
        LayoutManager layout = new BoxLayout(content, BoxLayout.Y_AXIS);
        Box boxes[] = new Box[3];
        boxes[0] = Box.createHorizontalBox();
        boxes[1] = Box.createHorizontalBox();
        boxes[2] = Box.createHorizontalBox();

        boxes[0].createGlue();
        boxes[1].createGlue();
        boxes[2].createGlue();

        content.add(boxes[0]);
        content.add(boxes[1]);
        content.add(boxes[2]);

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel.setPreferredSize(new Dimension(1200,50));
        panel2.setPreferredSize(new Dimension(1200,(allUsers.size())*20));
        panel3.setPreferredSize(new Dimension(1200,100));

        boxes[0].add(panel);
        boxes[1].add(panel2);
        boxes[2].add(panel3);

        JLabel titleLabel = new JLabel(cancelLabelText);
        panel.add(titleLabel);
        initializeUsers(panel2, panel3, user);
    }

    private void initializeUsers(JPanel panel, JPanel btnPanel, IUserAccount user){
        String cancelAddItemRequestButtonText = languageRB.getString("cancelAddItemRequestButtonText");
        String cancelUnfreezeRequestButtonText = languageRB.getString("cancelUnfreezeRequestButtonText");
        String cancelOnVacationButtonText = languageRB.getString("cancelOnVacationButtonText");

        JPopupMenu cancelOptionPopupMenu = new JPopupMenu();
        JMenuItem cancelAddItemRequest = new JMenuItem(cancelAddItemRequestButtonText);
        JMenuItem cancelUnfreezeRequest = new JMenuItem(cancelUnfreezeRequestButtonText);
        JMenuItem cancelOnVacationForUser = new JMenuItem(cancelOnVacationButtonText);

        String confirmedTransMenuItemText = languageRB.getString("confirmedTransMenuItemText");
        String cancelConfirmedTransMenuItemText = languageRB.getString("cancelConfirmedTransMenuItemText");
        String invitedTransMenuItemText = languageRB.getString("invitedTransMenuItemText");
        String cancelInvitedTransMenuItemText = languageRB.getString("cancelInvitedTransMenuItemText");

        JMenuItem viewConfirmedTransaction = new JMenuItem(confirmedTransMenuItemText);
        JPopupMenu cancelConfirmedTransactionPopupMenu = new JPopupMenu();
        JMenuItem cancelConfirmedTransaction = new JMenuItem(cancelConfirmedTransMenuItemText);

        JMenuItem viewInvitedTransaction = new JMenuItem(invitedTransMenuItemText);
        JPopupMenu cancelInvitedTransactionPopupMenu = new JPopupMenu();
        JMenuItem cancelInvitedTransaction = new JMenuItem(cancelInvitedTransMenuItemText);

        panel.add(AllUsers);

        cancelOptionPopupMenu.add(cancelAddItemRequest);
        cancelOptionPopupMenu.add(cancelUnfreezeRequest);
        cancelOptionPopupMenu.add(viewConfirmedTransaction);
        cancelOptionPopupMenu.add(viewInvitedTransaction);
        cancelOptionPopupMenu.add(cancelOnVacationForUser);
        cancelOptionPopupMenu.add(ConfirmedTransaction);
        cancelOptionPopupMenu.add(InvitedTrans);
        AllUsers.add(cancelOptionPopupMenu);

        cancelConfirmedTransactionPopupMenu.add(cancelConfirmedTransaction);
        ConfirmedTransaction.add(cancelConfirmedTransactionPopupMenu);

        cancelInvitedTransactionPopupMenu.add(cancelInvitedTransaction);
        InvitedTrans.add(cancelInvitedTransactionPopupMenu);

        String viewAllUsersButtonText = languageRB.getString("viewAllUsersButtonText");

        JButton viewAllUsersButton = new JButton(viewAllUsersButtonText);
        btnPanel.add(viewAllUsersButton);

        String successMessage = languageRB.getString("successMessage");

        viewAllUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllUsers.setListData(allUsers.toArray());
            }
        });

        AllUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == 3 && AllUsers.getSelectedIndex()!=-1){
                    Object selected = AllUsers.getModel().getElementAt(AllUsers.getSelectedIndex());
                    selectedUser = (IUserAccount) selected;
                    List<Integer> confirmedTransaction = userOpGUINavigator.getConfirmedTransactionNavigator(user);
                    for(int id: confirmedTransaction){
                        confirmedTransactionList.add(meetingSystemGUINavigator.getTransByIdNavigator(id));
                    }
                    List<Integer> invitedTrans = userOpGUINavigator.getInvitedTransNavigator(user);
                    for(int id: invitedTrans){
                        invitedTransList.add(meetingSystemGUINavigator.getTransByIdNavigator(id));
                    }

                    String confirmUndoMessage1 = languageRB.getString("confirmUndoMessage1");

                    String message = confirmUndoMessage1 + " " +
                            userAccountGUINavigator.getUserIdNavigator((IUserAccount) selected) + "? ";
                    JOptionPane.showMessageDialog(null,message);
                    cancelOptionPopupMenu.show(e.getComponent(),e.getX(), e.getY());
                }
            }
        });
        cancelAddItemRequest.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = AllUsers.getModel().getElementAt(AllUsers.getSelectedIndex());
                String userId = userAccountGUINavigator.getUserIdNavigator((IUserAccount)selected);
                int itemId = adminOpGUINavigator.findRequestedNewItemNavigator(user, userId);
                ItemInterface item = adminOpGUINavigator.getItemByIdNavigator(user,itemId);
                String message = adminOpGUINavigator.getAddingItemNewMessagesNavigator(user).get(itemId);
                try {
                    adminOpGUINavigator.cancelAddItemRequestNavigator(user, (IUserAccount) selected, item, message);
                    JOptionPane.showMessageDialog(null,successMessage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        cancelUnfreezeRequest.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = AllUsers.getModel().getElementAt(AllUsers.getSelectedIndex());
                String userId = userAccountGUINavigator.getUserIdNavigator((IUserAccount)selected);
                String message = adminOpGUINavigator.getUnfreezeNewMessagesNavigator(user).get(userId);
                try {
                    adminOpGUINavigator.cancelUnfreezeRequestNavigator(user, (IUserAccount) selected, message);
                    JOptionPane.showMessageDialog(null,successMessage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        cancelOnVacationForUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = AllUsers.getModel().getElementAt(AllUsers.getSelectedIndex());
                try {
                    adminOpGUINavigator.cancelOnVacationForUserNavigator(user, (IUserAccount)selected);
                    JOptionPane.showMessageDialog(null,successMessage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        viewConfirmedTransaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ConfirmedTransaction.setListData(confirmedTransactionList.toArray());
            }
        });

        ConfirmedTransaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == 3 && ConfirmedTransaction.getSelectedIndex()!=-1){
                    String confirmUndoMessage2 = languageRB.getString("confirmUndoMessage2");

                    Object selected = ConfirmedTransaction.getModel().
                            getElementAt(ConfirmedTransaction.getSelectedIndex());
                    String message = confirmUndoMessage2 + " " +
                            transGUINavigator.getTransIdNavigator((ITransaction) selected) + "? ";
                    JOptionPane.showMessageDialog(null,message);
                    cancelConfirmedTransactionPopupMenu.show(e.getComponent(),e.getX(), e.getY());
                }
            }
        });

        cancelConfirmedTransaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = ConfirmedTransaction.getModel().getElementAt(ConfirmedTransaction.getSelectedIndex());
                try {
                    adminOpGUINavigator.cancelConfirmedTransactionNavigator(user, selectedUser, (ITransaction) selected);
                    JOptionPane.showMessageDialog(null,successMessage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        viewInvitedTransaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                InvitedTrans.setListData(invitedTransList.toArray());
            }
        });

        InvitedTrans.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String confirmUndoMessage2 = languageRB.getString("confirmUndoMessage2");

                if(e.getButton() == 3 && InvitedTrans.getSelectedIndex()!=-1){
                    Object selected = InvitedTrans.getModel().getElementAt(InvitedTrans.getSelectedIndex());
                    String message = confirmUndoMessage2 + " " +
                            transGUINavigator.getTransIdNavigator((ITransaction) selected) + "? ";
                    JOptionPane.showMessageDialog(null,message);
                    cancelInvitedTransactionPopupMenu.show(e.getComponent(),e.getX(), e.getY());
                }
            }
        });

        cancelInvitedTransaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = InvitedTrans.getModel().getElementAt(InvitedTrans.getSelectedIndex());
                try {
                    adminOpGUINavigator.cancelInvitedTransactionNavigator(user, selectedUser, (ITransaction) selected);
                    JOptionPane.showMessageDialog(null,successMessage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        try {
            uiNavigator.backToMainMenu(user, directToCancelFrame, btnPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
