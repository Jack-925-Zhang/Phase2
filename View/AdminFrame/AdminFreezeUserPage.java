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
 * This class creates the frame for administrative users to freeze normal users
 * @author Linni Xie
 * @version 1.8
 */
public class AdminFreezeUserPage extends JFrame {

    TradingSystemGUINavigator tradingSystemGUINavigator = TradingSystemGUINavigator.getInstance();
    UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    AdminOpGUINavigator adminOpGUINavigator = AdminOpGUINavigator.getInstance();
    JFrame freezeUserPageFrame;
    JPanel freezeUserPagePanel;
    JList FreezeUsers = new JList();

    private final ArrayList<IUserAccount> freezeUsers = new ArrayList<>();

    ResourceBundle languageRB;

    /**
     * calls the menu frame for freeze user
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AdminFreezeUserPage window = new AdminFreezeUserPage(user, languageRB);
                    window.freezeUserPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This constructs the frame and its characteristics for the freeze user page
     * @param user the admin user opening this page
     * @param languageRB the user selected language
     */
    public AdminFreezeUserPage(IUserAccount user, ResourceBundle languageRB) {

        this.languageRB = languageRB;
        String freezeUserLabelText =languageRB.getString("freezeUserLabelText");
        freezeUsers.addAll(tradingSystemGUINavigator.getFlaggedNavigator());

        freezeUserPageFrame = new JFrame();
        freezeUserPageFrame.setTitle(freezeUserLabelText);
        freezeUserPageFrame.setSize(1200,(freezeUsers.size())*20+150);
        freezeUserPageFrame.setLocationRelativeTo(null);
        freezeUserPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        freezeUserPageFrame.setResizable(true);

        JPanel content = new JPanel();
        freezeUserPageFrame.getContentPane().add(content);
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
        panel2.setPreferredSize(new Dimension(1200,(freezeUsers.size())*20));
        panel3.setPreferredSize(new Dimension(1200,100));

        boxes[0].add(panel);
        boxes[1].add(panel2);
        boxes[2].add(panel3);

        JLabel headerLabel2 = new JLabel(freezeUserLabelText, JLabel.CENTER);
        panel.add(headerLabel2);
        freezeUserPageOption(user, panel2, panel3);
    }

    /**
     * This method sets up the buttons and other menu items to the freeze user page
     * @param user the admin user opening the page
     */
    private void freezeUserPageOption(IUserAccount user, JPanel infoPanel, JPanel btnPanel){

        String viewFreezeButtonText = languageRB.getString("viewFreezeButtonText");
        String requestMenuButtonText = languageRB.getString("requestMenuButtonText");
        String freezeMenuItemText = languageRB.getString("freezeMenuItemText");
        String removeMenuItemText = languageRB.getString("removeMenuItemText");

        JButton ViewFreezeButton = new JButton(viewFreezeButtonText);
        JButton RequestsMenu = new JButton(requestMenuButtonText);
        JPopupMenu freezePopupMenu = new JPopupMenu();
        JMenuItem freeze = new JMenuItem(freezeMenuItemText);
        JMenuItem remove = new JMenuItem(removeMenuItemText);

        btnPanel.add(ViewFreezeButton);
        btnPanel.add(RequestsMenu);

        freezePopupMenu.add(freeze);
        freezePopupMenu.add(remove);
        FreezeUsers.add(freezePopupMenu);
        infoPanel.add(FreezeUsers);
        String noFrozenUserMessage = languageRB.getString("noFrozenUserMessage");
        ViewFreezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FreezeUsers.setListData(freezeUsers.toArray());
                if (freezeUsers.isEmpty()){
                    JOptionPane.showMessageDialog(null, noFrozenUserMessage);
                }
            }
        });

        String successMessage = languageRB.getString("successMessage");

        FreezeUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == 3 && FreezeUsers.getSelectedIndex()!=-1){
                    String freezeConfirmationMessage = languageRB.getString("freezeConfirmationMessage");

                    Object selected = FreezeUsers.getModel().getElementAt(FreezeUsers.getSelectedIndex());
                    String id = userAccountGUINavigator.getUserIdNavigator((IUserAccount) selected);
                    JOptionPane.showMessageDialog(null,freezeConfirmationMessage + " "+ id);
                    freezePopupMenu.show(e.getComponent(),e.getX(), e.getY());
                }
            }
        });

        freeze.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = FreezeUsers.getModel().getElementAt(FreezeUsers.getSelectedIndex());
                String userId = userAccountGUINavigator.getUserIdNavigator((IUserAccount) selected);
                String message = adminOpGUINavigator.getFreezeNewMessagesNavigator(user).get(userId);
                int id = 1;
                try {
                    adminOpGUINavigator.freezeUserNavigator(user, (IUserAccount) selected, message);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, successMessage);
            }
        });

        remove.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Object selected = FreezeUsers.getModel().getElementAt(FreezeUsers.getSelectedIndex());
                tradingSystemGUINavigator.removeFlagged((IUserAccount) selected);
                JOptionPane.showMessageDialog(null, successMessage);
            }
        });
        RequestsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUserMainMenu.MenuScreen(user, languageRB);
                freezeUserPageFrame.setVisible(false);
            }
        });

    }
}
