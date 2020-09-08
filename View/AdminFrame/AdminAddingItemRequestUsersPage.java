package View.AdminFrame;

import Controller.*;
import Model.IUserAccount;
import Model.ItemInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class creates the frame for administrative users to respond to any adding item requests from
 * the normal user
 * @author Linni Xie
 * @version 1.8
 */
public class AdminAddingItemRequestUsersPage extends JFrame{
    JFrame addingItemRequestUsersPageFrame;

    JList AddItemsRequests = new JList();

    private final ArrayList<String> addItemsRequests = new ArrayList<>();

    ResourceBundle languageRB;

    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    TradingSystemGUINavigator tradingSystemGUINavigator = TradingSystemGUINavigator.getInstance();
    UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    AdminOpGUINavigator adminOpGUINavigator = AdminOpGUINavigator.getInstance();

    /**
     * calls the menu frame for the adding item requests
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new AdminAddingItemRequestUsersPage(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class AdminAddingItemRequestUsersPage
     * @param user the user who sees this page
     * @param languageRB the language bundle chosen by user
     */
    public AdminAddingItemRequestUsersPage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        initAdminAddingItemRequestUsersPage(user);
    }

    /**
     * This constructs the frame and its characteristics for adding item request page
     * @param user the admin user opening the frame
     */
    public void initAdminAddingItemRequestUsersPage(IUserAccount user) {

        String requestAddingItemLabelText = languageRB.getString("requestAddingItemLabelText");
        addItemsRequests.addAll(adminOpGUINavigator.getAddingItemRequestUsersNavigator(user));

        addingItemRequestUsersPageFrame = new JFrame();
        addingItemRequestUsersPageFrame.setTitle(requestAddingItemLabelText);
        addingItemRequestUsersPageFrame.setSize(1200,(addItemsRequests.size())*20+150);
        addingItemRequestUsersPageFrame.setLocationRelativeTo(null);
        addingItemRequestUsersPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addingItemRequestUsersPageFrame.setResizable(true);

        JPanel content = new JPanel();
        addingItemRequestUsersPageFrame.getContentPane().add(content);
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
        panel2.setPreferredSize(new Dimension(1200,(addItemsRequests.size())*20));
        panel3.setPreferredSize(new Dimension(1200,100));

        boxes[0].add(panel);
        boxes[1].add(panel2);
        boxes[2].add(panel3);

        JLabel headerLabel4 = new JLabel(requestAddingItemLabelText, JLabel.CENTER);
        panel.add(headerLabel4);
        addingItemRequestUsersPageOption(panel2, panel3, user);
        addingItemRequestUsersPageFrame.setVisible(true);
    }

    /**
     * This method sets up the buttons and menu items on the adding item request page
     * @param user the admin user opening the frame
     */
    private void addingItemRequestUsersPageOption(JPanel infoPanel, JPanel btnPanel, IUserAccount user){

        String requestAddingItemLabelText = languageRB.getString("requestAddingItemLabelText");
        String requestMenuButtonText = languageRB.getString("requestMenuButtonText");
        String confirmedMenuItemText = languageRB.getString("confirmedMenuItemText");
        String rejectedMenuItemText = languageRB.getString("rejectedMenuItemText");

        JButton ViewAddingItemsRequestsButton = new JButton(requestAddingItemLabelText);
        JButton RequestsMenu = new JButton(requestMenuButtonText);
        JPopupMenu addingItemPopupMenu = new JPopupMenu();
        JMenuItem confirmed = new JMenuItem(confirmedMenuItemText);
        JMenuItem reject = new JMenuItem(rejectedMenuItemText);

        addingItemPopupMenu.add(confirmed);
        addingItemPopupMenu.add(reject);
        AddItemsRequests.add(addingItemPopupMenu);
        infoPanel.add(AddItemsRequests);

        String noRequestMessage = languageRB.getString("noRequestMessage");
        ViewAddingItemsRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddItemsRequests.setListData(addItemsRequests.toArray());
                if (addItemsRequests.isEmpty()){
                    JOptionPane.showMessageDialog(null,noRequestMessage);
                }
            }
        });

        AddItemsRequests.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String confirmRequestMessage1 = languageRB.getString("confirmRequestMessage1");
                String confirmRequestMessage2 = languageRB.getString("confirmRequestMessage2");
                if(e.getButton() == 3 && AddItemsRequests.getSelectedIndex()!=-1){
                    Object selected = AddItemsRequests.getModel().getElementAt(AddItemsRequests.getSelectedIndex());
                    String message = confirmRequestMessage1 + " " +
                            adminOpGUINavigator.findRequestedNewItemNavigator(
                                   user, userAccountGUINavigator.getUserIdNavigator(user)) +
                            " " + confirmRequestMessage2 + " " + selected;
                    JOptionPane.showMessageDialog(null,message);
                    addingItemPopupMenu.show(e.getComponent(),e.getX(), e.getY());
                }
            }
        });

        confirmed.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = AddItemsRequests.getModel().getElementAt(AddItemsRequests.getSelectedIndex());
                int itemId = adminOpGUINavigator.findRequestedNewItemNavigator(
                     user, userAccountGUINavigator.getUserIdNavigator(user));
                ItemInterface item = adminOpGUINavigator.getItemByIdNavigator(user,itemId);
                IUserAccount requestUser = userManagerGUINavigator.getUserByIdNavigator((String) selected);
                String message = adminOpGUINavigator.getAddingItemNewMessagesNavigator(user).get(itemId);
                try {
                    adminOpGUINavigator.confirmAddItemNavigator(user, requestUser, item, message);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        reject.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = AddItemsRequests.getModel().getElementAt(AddItemsRequests.getSelectedIndex());
                int itemId = adminOpGUINavigator.findRequestedNewItemNavigator(
                      user, userAccountGUINavigator.getUserIdNavigator(user));
                ItemInterface item = adminOpGUINavigator.getItemByIdNavigator(user,itemId);
                IUserAccount requestUser = userManagerGUINavigator.getUserByIdNavigator((String) selected);
                String message = adminOpGUINavigator.getAddingItemNewMessagesNavigator(user).get(itemId);
                try {
                    adminOpGUINavigator.rejectAddItemNavigator(user, requestUser, item, message);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        RequestsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRequestPage.MenuScreen(user, languageRB);
                addingItemRequestUsersPageFrame.setVisible(false);
            }
        });

        btnPanel.add(ViewAddingItemsRequestsButton);
        btnPanel.add(RequestsMenu);
    }

}
