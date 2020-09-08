package View.ManageUserFrame;

import Controller.UINavigator;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * sets the window to display all normal users in the system
 * @author BINGQING WAN
 * @version 1.8
 */
public class DisplayAllNormalUserIdPage {

    JFrame displayUserFrame;
    ResourceBundle languageRB;
    UINavigator uiNavigator = UINavigator.getInstance();

    /**
     * calls the menu frame for the display normal user window
     * @param normalUser the HashMap of all normal users in the system. The key is the user id in String
     *                   type, and the value is the IUserAccount type
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(HashMap<String, IUserAccount> normalUser, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DisplayAllNormalUserIdPage window = new DisplayAllNormalUserIdPage(normalUser, languageRB);
                    window.displayUserFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class DisplayAllNormalUserIdPage
     * @param normalUser the HashMap of all normal users in the system. The key is the user id in String
     *                   type, and the value is the IUserAccount type
     * @param languageRB the language bundle chosen by user
     */
    public DisplayAllNormalUserIdPage(HashMap<String, IUserAccount> normalUser, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        initUpgradeUserPage(normalUser, languageRB);
    }

    /**
     * this method initializes the window properties and buttons for the DisplayAllNormalUserIdPage
     * @param normalUser the HashMap of all normal users in the system. The key is the user id in String
     *                   type, and the value is the IUserAccount type
     * @param languageRB the language bundle chosen by user
     */
    private void initUpgradeUserPage(HashMap<String, IUserAccount> normalUser, ResourceBundle languageRB) {

        String titleText = languageRB.getString("getNormalUserButtonText");
        String closeButtonText = languageRB.getString("closeButtonText");
        String noNormalUserMessage = languageRB.getString("noNormalUserMessage");

        displayUserFrame = new JFrame();
        displayUserFrame.setTitle(titleText);
        displayUserFrame.setSize(350,4*50);
        displayUserFrame.setLocationRelativeTo(null);
        displayUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayUserFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(normalUser.size()+1,1));
        if (normalUser.size()==0){
            JLabel titleLabel = new JLabel(noNormalUserMessage, JLabel.CENTER);
            infoPanel.add(titleLabel);
        } else {
            JLabel titleLabel = new JLabel(titleText, JLabel.CENTER);
            infoPanel.add(titleLabel);
        }
        Iterator infoIterator = normalUser.entrySet().iterator();
        while (infoIterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)infoIterator.next();
            JLabel infoLabel = new JLabel((String) mapElement.getKey(), JLabel.CENTER);
            infoPanel.add(infoLabel);
        }
        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));

        JButton btnClose = new JButton(closeButtonText);
        uiNavigator.formatButtonSize(btnClose, buttonPanel);

        mainPanel.add(infoPanel);
        mainPanel.add(buttonPanel);
        displayUserFrame.add(mainPanel);

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUserFrame.setVisible(false);
            }
        });

    }
}
