package View.MyTradesMenu;

import Controller.UINavigator;
import Controller.UserManagerGUINavigator;
import Controller.UserOpGUINavigator;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * sets the page to display the user's three most frequent trading partners
 * @author BINGQING WAN
 * @version 1.8
 */
public class MyTradePartnersPage {

    ResourceBundle languageRB;
    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    UINavigator uiNavigator =  UINavigator.getInstance();
    MyTradesMenu myTradesMenu;

    /**
     * This constructs the trade partners page
     * @param user the user opening this page
     * @param languageRB the user selected language
     */
    public MyTradePartnersPage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        this.myTradesMenu = new MyTradesMenu(user, languageRB);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initializePartners(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * this method initializes the frame for the partner page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    private void initializePartners(IUserAccount user, ResourceBundle languageRB) {

        String myPartnersButtonText = languageRB.getString("myPartnersButtonText");
        userManagerGUINavigator.findFrequentTradingPartnersNavigator(user);
        Map<String, Integer> partners = userOpGUINavigator.getThreePartnersNavigator(user);

        JFrame partnersMenuFrame = new JFrame();
        partnersMenuFrame.setTitle(myPartnersButtonText);
        partnersMenuFrame.setSize(400,(partners.size()+4)*50);
        partnersMenuFrame.setLocationRelativeTo(null);
        partnersMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        partnersMenuFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // text panel
        JPanel txtPanel = new JPanel();

        // button panel
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2,1));

        if (partners.size()==0) {
            String noPartnerLabelText = languageRB.getString("noPartnerLabelText");
            txtPanel.setLayout(new GridLayout(1,1));
            JLabel partnerLabel = new JLabel(noPartnerLabelText, JLabel.CENTER);
            txtPanel.add(partnerLabel);
        } else {
            String trades = languageRB.getString("trades");
            txtPanel.setLayout(new GridLayout(partners.size()+1,1));
            JLabel headerLabel = new JLabel(myPartnersButtonText, JLabel.CENTER);
            txtPanel.add(headerLabel);
            for (Map.Entry<String, Integer> elements : partners.entrySet()) {
                String userName = elements.getKey();
                String tranTimes = Integer.toString(elements.getValue());
                JLabel partnerLabel = new JLabel(" " + userName + ": " + tranTimes + trades);
                txtPanel.add(partnerLabel);
            }
        }

        try {
            myTradesMenu.backToTradesMenu(user, partnersMenuFrame, btnPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            uiNavigator.backToMainMenu(user, partnersMenuFrame, btnPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainPanel.add(txtPanel);
        mainPanel.add(btnPanel);
        partnersMenuFrame.add(mainPanel);
        partnersMenuFrame.setVisible(true);
    }

}
