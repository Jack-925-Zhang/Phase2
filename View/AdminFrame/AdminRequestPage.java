package View.AdminFrame;

import Controller.UINavigator;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * This class creates the frame for administrative users to select which request they want to respond to
 * (normal user's add item request or unfreeze request)
 * @author Linni Xie
 * @version 1.8
 */
public class AdminRequestPage extends JFrame{

    JFrame requestPageFrame;
    JPanel requestPagePanel;
    ResourceBundle languageRB;
    UINavigator uiNavigator = UINavigator.getInstance();

    /**
     * calls the menu frame for user requests
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new AdminRequestPage(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This constructs the frame and its characteristics for the request page
     * @param user the admin user opening this page
     * @param languageRB the user selected language
     */
    public AdminRequestPage(IUserAccount user, ResourceBundle languageRB) {

        this.languageRB = languageRB;
        String requestedNewItemsLabelText = languageRB.getString("requestedNewItemsLabelText");

        requestPageFrame = new JFrame();
        requestPagePanel = new JPanel();
        requestPageFrame.setTitle(requestedNewItemsLabelText);
        requestPageFrame.setSize(350,200);
        requestPageFrame.setLocationRelativeTo(null);
        requestPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        requestPageFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // button panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel headerLabel3 = new JLabel(requestedNewItemsLabelText, JLabel.CENTER);
        panel.add(headerLabel3);
        requestPageOption(user, panel);
        mainPanel.add(panel);
        requestPageFrame.add(mainPanel);
        requestPageFrame.setVisible(true);
    }

    /**
     * This method sets the buttons and other menu items for the request page
     * @param user the admin user opening this page
     */
    private void requestPageOption(IUserAccount user, JPanel panel){

        String requestAddingItemLabelText = languageRB.getString("requestAddingItemLabelText");
        String unfreezingRequestLabelText = languageRB.getString("unfreezingRequestLabelText");

        JButton addingItemRequestUsers = new JButton(requestAddingItemLabelText);
        JButton unfrozenRequestUsers = new JButton(unfreezingRequestLabelText);
        uiNavigator.formatButtonSize(addingItemRequestUsers, panel);
        uiNavigator.formatButtonSize(unfrozenRequestUsers, panel);
        try {
            uiNavigator.backToMainMenu(user, requestPageFrame, panel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addingItemRequestUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRequestPage.this.setVisible(false);
                AdminAddingItemRequestUsersPage.MenuScreen(user, languageRB);
                requestPageFrame.setVisible(false);
            }
        });
        unfrozenRequestUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRequestPage.this.setVisible(false);
                AdminUnfrozenRequestUsersPage.MenuScreen(user, languageRB);
                requestPageFrame.setVisible(false);
            }
        });

    }

}
