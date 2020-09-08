package View.AdminFrame;

import Controller.*;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class creates the frame for administrative users to respond to unfrozen requests from normal users
 * @author Linni Xie
 * @version 1.8
 */
public class AdminUnfrozenRequestUsersPage extends JFrame {

    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    AdminOpGUINavigator adminOpGUINavigator = AdminOpGUINavigator.getInstance();

    JFrame unfrozenRequestUsersPageFrame;
    JPanel unfrozenRequestUsersPagePanel;

    JList UnfreezeRequests = new JList();
    private final ArrayList<String> unfreezeRequests = new ArrayList<>();

    ResourceBundle languageRB;

    /**
     * calls the menu frame for unfrozen request
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new AdminUnfrozenRequestUsersPage(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * set colour for unfrozen request frame
     * @param frame unfrozen request frame
     */
    private void initBgColour(JFrame frame) {
        Container pane = frame.getContentPane();
        pane.setBackground(Color.lightGray);
    }

    /**
     * This constructs the frame and its characteristics for the unfrozen request page
     * @param user the admin user opening this page
     * @param languageRB the user selected language
     */
    public AdminUnfrozenRequestUsersPage(IUserAccount user, ResourceBundle languageRB) {

        this.languageRB = languageRB;
        String unfreezingRequestLabelText = languageRB.getString("unfreezingRequestLabelText");
        unfreezeRequests.addAll(adminOpGUINavigator.getUnfrozenRequestUsersNavigator(user));

        unfrozenRequestUsersPageFrame = new JFrame();
        unfrozenRequestUsersPageFrame.setTitle(unfreezingRequestLabelText);
        unfrozenRequestUsersPageFrame.setSize(1200,(unfreezeRequests.size())*20+150);
        unfrozenRequestUsersPageFrame.setLocationRelativeTo(null);
        unfrozenRequestUsersPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        unfrozenRequestUsersPageFrame.setResizable(true);

        JPanel content = new JPanel();
        unfrozenRequestUsersPageFrame.getContentPane().add(content);
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
        panel2.setPreferredSize(new Dimension(1200,(unfreezeRequests.size())*20));
        panel3.setPreferredSize(new Dimension(1200,100));

        boxes[0].add(panel);
        boxes[1].add(panel2);
        boxes[2].add(panel3);

        JLabel headerLabel4 = new JLabel(unfreezingRequestLabelText, JLabel.CENTER);
        panel.add(headerLabel4);
        unfrozenRequestUsersPageOption(panel2, panel3, user);
        unfrozenRequestUsersPageFrame.setVisible(true);
    }

    /**
     * This method sets up the buttons and other menu items for the unfrozen request page
     * @param user the admin user opening this page
     */
    private void unfrozenRequestUsersPageOption(JPanel infoPanel, JPanel btnPanel, IUserAccount user){

        String unfreezingRequestLabelText = languageRB.getString("unfreezingRequestLabelText");
        String requestMenuButtonText = languageRB.getString("requestMenuButtonText");
        String confirmedMenuItemText = languageRB.getString("confirmedMenuItemText");
        String rejectedMenuItemText = languageRB.getString("rejectedMenuItemText");

        JButton ViewUnfrozenRequestsButton = new JButton(unfreezingRequestLabelText);
        JButton RequestsMenu = new JButton(requestMenuButtonText);
        JPopupMenu unfrozenRequestsPopupMenu = new JPopupMenu();
        JMenuItem confirmed = new JMenuItem(confirmedMenuItemText);
        JMenuItem reject = new JMenuItem(rejectedMenuItemText);

        unfrozenRequestsPopupMenu.add(confirmed);
        unfrozenRequestsPopupMenu.add(reject);
        UnfreezeRequests.add(unfrozenRequestsPopupMenu);
        infoPanel.add(UnfreezeRequests);

        btnPanel.add(ViewUnfrozenRequestsButton);
        btnPanel.add(RequestsMenu);
        String noRequestMessage = languageRB.getString("noRequestMessage");

        ViewUnfrozenRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnfreezeRequests.setListData(unfreezeRequests.toArray());
                if (unfreezeRequests.isEmpty()){
                    JOptionPane.showMessageDialog(null,noRequestMessage);
                }
            }
        });

        UnfreezeRequests.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == 3 && UnfreezeRequests.getSelectedIndex()!=-1){
                    String confirmRequestMessage3 = languageRB.getString("confirmRequestMessage3");

                    Object selected = UnfreezeRequests.getModel().getElementAt(UnfreezeRequests.getSelectedIndex());
                    String message = confirmRequestMessage3 + " " + selected;
                    JOptionPane.showMessageDialog(null,message);
                    unfrozenRequestsPopupMenu.show(e.getComponent(),e.getX(), e.getY());
                }
            }
        });

        confirmed.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = UnfreezeRequests.getModel().getElementAt(UnfreezeRequests.getSelectedIndex());
                IUserAccount requestUser = userManagerGUINavigator.getUserByIdNavigator((String) selected);
                String message = adminOpGUINavigator.getUnfreezeNewMessagesNavigator(user).get(selected);
                try {
                    adminOpGUINavigator.confirmedUnfreezeUserNavigator(user, requestUser, message );
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        reject.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = UnfreezeRequests.getModel().getElementAt(UnfreezeRequests.getSelectedIndex());
                IUserAccount requestUser = userManagerGUINavigator.getUserByIdNavigator((String) selected);
                String message = adminOpGUINavigator.getUnfreezeNewMessagesNavigator(user).get(selected);
                try {
                    adminOpGUINavigator.rejectedUnFreezeUserNavigator(user, requestUser, message);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        RequestsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUnfrozenRequestUsersPage.this.setVisible(false);
                AdminRequestPage.MenuScreen(user, languageRB);
                unfrozenRequestUsersPageFrame.dispose();
            }
        });
    }










}
