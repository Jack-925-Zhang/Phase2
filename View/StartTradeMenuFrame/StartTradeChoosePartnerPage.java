package View.StartTradeMenuFrame;

import Controller.*;
import Model.IUserAccount;
import Model.ItemInterface;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.List;

/**
 * the class for choose partner page
 * @author Hongyu Chen
 * @version 1.8
 */
public class StartTradeChoosePartnerPage extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    StartTradeMenu startTradeMenu;

    ItemInterface tradeItem;
    String tradeType;
    JFrame choosePartnerPageFrame;
    JFrame tradeInvitationPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the choose partner page
     * @param user the user who sees this window
     * @param tradeType type of the trade user wants to start
     * @param tradeItem item of the trade user wants to start
     * @param partners users who may trade with user
     * @param languageRB the language bundle chosen by user
     */
    static void choosePartnerScreen(IUserAccount user, String tradeType, ItemInterface tradeItem,
                                    List<IUserAccount> partners, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    StartTradeChoosePartnerPage window =
                            new StartTradeChoosePartnerPage(user, tradeType, tradeItem, partners, languageRB);
                    window.choosePartnerPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of the class for choose partner page
     * @param user the user who sees this window
     * @param tradeType type of the trade user wants to start
     * @param tradeItem item of the trade user wants to start
     * @param partners users who may trade with user
     * @param languageRB the language bundle chosen by user
     */
    StartTradeChoosePartnerPage(IUserAccount user, String tradeType, ItemInterface tradeItem,
                                List<IUserAccount> partners, ResourceBundle languageRB) throws Exception {
        this.startTradeMenu = new StartTradeMenu(user, tradeType, languageRB);
        this.tradeItem = tradeItem;
        this.tradeType = tradeType;
        this.languageRB = languageRB;
        initChoosePartnerPage(user, partners);
    }

    /**
     * the initializer of choose partner page
     * @param user the user who sees this window
     * @param partners users who may trade with user
     */
    private void initChoosePartnerPage(IUserAccount user, List<IUserAccount> partners) throws Exception {

        // setting the frame of the page for choosing lender
        choosePartnerPageFrame = new JFrame();

        String chooseLenderButtonText = languageRB.getString("chooseLenderButtonText");
        String chooseBorrowerButtonText = languageRB.getString("chooseBorrowerButtonText");

        String title;
        if (tradeType.equals("borrow")) {
            title = chooseLenderButtonText;
        }else {
            title = chooseBorrowerButtonText;
        }
        choosePartnerPageFrame.setTitle(title);
        choosePartnerPageFrame.setSize(1025,600);
        choosePartnerPageFrame.setLocationRelativeTo(null);
        choosePartnerPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        choosePartnerPageFrame.getContentPane().setLayout(null);
        choosePartnerPageFrame.setVisible(true);

        choosePartnerPageFrame.setResizable(true);
        choosePartnerPageFrame.setLayout(new GridLayout(10, 1));
        uiNavigator.initBgColour(choosePartnerPageFrame);

        JLabel headerLabel3 = new JLabel(title, JLabel.CENTER);

        choosePartnerPageFrame.add(headerLabel3);

        // adding JPanel
        JPanel panel3 = new JPanel();
        choosePartnerPageFrame.add(panel3);

        choosePartnerPageOption(user, partners, panel3);

    }

    /**
     * sets the options shown on the window for choose partner menu
     * @param user the user who sees this menu
     * @param partners users who may trade with user
     * @param panel3 the JPanel for the buttons
     */
    // TODO: lender多于10个的情况也要设定
    private void choosePartnerPageOption(IUserAccount user, List<IUserAccount> partners, JPanel panel3) throws Exception {

        int y = 80;

        for (IUserAccount partner : partners) {

            JButton partnerButton = new JButton(userAccountGUINavigator.getUserIdNavigator(partner));
            partnerButton.setBounds(100,y,50,25);

            partnerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

//                    uiNavigator.tradeInvitationPageOption(
//                            user, partner, tradeItem, panel4, tradeType);
                    try {
                        startTradeMenu.backToParentMenu(user, tradeInvitationPageFrame, panel3);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    StartTradeTradeInvitationPage.tradeInvitationScreen(
                            user, tradeType, tradeItem, partner, languageRB);
//                    choosePartnerPageFrame.dispose();
                    choosePartnerPageFrame.setVisible(false);

                }
            });

            panel3.add(partnerButton);
            y += 35;

        }

        startTradeMenu.backToParentMenu(user, choosePartnerPageFrame, panel3);

    }

}
