package View.MyTradesMenu;

import Controller.UINavigator;
import Model.IUserAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * sets the main menu page for trades and transactions
 * @author BINGQING WAN
 * @version 1.8
 */
public class MyTradesMenu {

    JFrame myTradesMenuFrame;
    ResourceBundle languageRB;
    UINavigator uiNavigator = UINavigator.getInstance();

    /**
     * calls the menu frame for the my trades menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyTradesMenu window = new MyTradesMenu(user, languageRB);
                    window.myTradesMenuFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * constructor for the my trades menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public MyTradesMenu(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        init(user);
    }

    /**
     * this method initializes characteristics for the trades menu frame
     * @param user the user who sees this window
     */
    private void init(IUserAccount user) {

        String myTradesButtonText = languageRB.getString("myTradesButtonText");

        myTradesMenuFrame = new JFrame();
        myTradesMenuFrame.setTitle(myTradesButtonText);
        myTradesMenuFrame.setSize(350,250);
        myTradesMenuFrame.setLocationRelativeTo(null);
        myTradesMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myTradesMenuFrame.setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        myTradesMenuOption(user, buttonPanel);

        mainPanel.add(buttonPanel);
        myTradesMenuFrame.add(mainPanel);

    }

    /**
     * this method sets up the buttons for the trade menu frame
     * @param user the user who sees this window
     * @param buttonPanel the panel for the buttons to display on
     */
    private void myTradesMenuOption(IUserAccount user, JPanel buttonPanel) {

        String myTradesButtonText = languageRB.getString("myTradesButtonText");
        String myTransButtonText = languageRB.getString("myTransButtonText");
        String myTradingItemsButtonText = languageRB.getString("myTradingItemsButtonText");
        String myPartnersButtonText = languageRB.getString("myPartnersButtonText");
        String mainMenuButtonText = languageRB.getString("mainMenuButtonText");

        JLabel headerLabel = new JLabel(myTradesButtonText, JLabel.CENTER);
        JButton myTrans = new JButton(myTransButtonText);
        JButton myTradingItems = new JButton(myTradingItemsButtonText);
        JButton myPartners = new JButton(myPartnersButtonText);
        JButton mainMenu = new JButton(mainMenuButtonText);

        buttonPanel.add(headerLabel);

        JPanel transSizePanel = new JPanel();
        transSizePanel.add(myTrans);
        buttonPanel.add(transSizePanel);

        JPanel itemSizePanel = new JPanel();
        itemSizePanel.add(myTradingItems);
        buttonPanel.add(itemSizePanel);

        JPanel partnerSizePanel = new JPanel();
        partnerSizePanel.add(myPartners);
        buttonPanel.add(partnerSizePanel);

        JPanel mainSizePanel = new JPanel();
        mainSizePanel.add(mainMenu);
        buttonPanel.add(mainSizePanel);

        myTrans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyTradesTransactionsPage(user, languageRB);
                myTradesMenuFrame.setVisible(false);
            }
        });
        myTradingItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyTradeItemsPage(user, languageRB);
                myTradesMenuFrame.setVisible(false);
            }
        });
        myPartners.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyTradePartnersPage(user, languageRB);
                myTradesMenuFrame.setVisible(false);
            }
        });
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.Menu.MenuScreen(user, languageRB);
                myTradesMenuFrame.setVisible(false);
            }
        });
    }
    /**
     * goes back to my trades menu
     * @param user the user who sees this
     * @param currentFrame the JFrame which will be disposed
     * @param panel the JPanel where the buttons are added
     * @param languageRB user selected language
     */
    public void backToTradesMenu(IUserAccount user, JFrame currentFrame, JPanel panel, ResourceBundle languageRB) throws Exception {

        String myTradesButtonText = languageRB.getString("myTradesButtonText");
        JButton myTradesMenu = new JButton(myTradesButtonText);
        uiNavigator.formatButtonSize(myTradesMenu, panel);

        myTradesMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyTradesMenu.MenuScreen(user, languageRB);
                currentFrame.setVisible(false);
            }
        });
    }

}
