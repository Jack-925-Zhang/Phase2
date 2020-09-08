package View.MyTradesMenu;

import Controller.UINavigator;
import Controller.UserOpGUINavigator;
import Model.IUserAccount;
import Model.ItemInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

/**
 * sets the page to display the three most recent trading items
 * @author BINGQING WAN
 * @version 1.8
 */
public class MyTradeItemsPage {

    ResourceBundle languageRB;
    UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    MyTradesMenu myTradesMenu;

    /**
     * constructor for the my recent trading items menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public MyTradeItemsPage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        this.myTradesMenu = new MyTradesMenu(user, languageRB);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initializeTradeItems(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * this method initializes the frame for item page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    private void initializeTradeItems(IUserAccount user, ResourceBundle languageRB) {

        String myTradingItemsButtonText = languageRB.getString("myTradingItemsButtonText");
        List<ItemInterface> items = userOpGUINavigator.getThreeItemsNavigator(user);

        JFrame itemsMenuFrame = new JFrame();
        itemsMenuFrame.setTitle(myTradingItemsButtonText);
        itemsMenuFrame.setSize(400,(items.size()+4)*50);
        itemsMenuFrame.setLocationRelativeTo(null);
        itemsMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itemsMenuFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // text panel
        JPanel txtPanel = new JPanel();

        // button panel
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2,1));

        if (items.isEmpty()) {
            String noTradeItemLabelText = languageRB.getString("noTradeItemLabelText");
            txtPanel.setLayout(new GridLayout(1,1));
            JLabel itemLabel = new JLabel(noTradeItemLabelText, JLabel.CENTER);
            txtPanel.add(itemLabel);
        } else {
            txtPanel.setLayout(new GridLayout(items.size()+1,1));
            JLabel headerLabel = new JLabel(myTradingItemsButtonText, JLabel.CENTER);
            txtPanel.add(headerLabel);
            int count = 1;
            for (ItemInterface item : items) {
                JLabel itemLabel = new JLabel(" " + count + ": " + item.getItemName());
                txtPanel.add(itemLabel);
                count = count + 1;
            }
        }
        try {
            myTradesMenu.backToTradesMenu(user, itemsMenuFrame, btnPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            uiNavigator.backToMainMenu(user, itemsMenuFrame, btnPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainPanel.add(txtPanel);
        mainPanel.add(btnPanel);
        itemsMenuFrame.add(mainPanel);
        itemsMenuFrame.setVisible(true);
    }
}
