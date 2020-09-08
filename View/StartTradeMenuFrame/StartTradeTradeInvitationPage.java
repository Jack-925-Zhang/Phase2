package View.StartTradeMenuFrame;

import Controller.*;
import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.Recommendation;
import UseCases.RecommendationInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

/**
 * the class for trade invitation page
 * @author Hongyu Chen
 * @version 1.8
 */
public class StartTradeTradeInvitationPage extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final MeetingSystemGUINavigator meetingSystemGUINavigator = MeetingSystemGUINavigator.getInstance();
    private final UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    private final ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    private final TransGUINavigator transGUINavigator = TransGUINavigator.getInstance();
    private final UserMessagesOpGUINavigator userMessagesOpGUINavigator = UserMessagesOpGUINavigator.getInstance();
    UserTransactionOpGUINavigator userTransactionOpGUINavigator = UserTransactionOpGUINavigator.getInstance();
    StartTradeMenu startTradeMenu;

    String tradeType;
    ItemInterface tradeItem;
    IUserAccount partner;
    JFrame tradeInvitationPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the trade invitation page
     * @param user the user who sees this window
     * @param tradeType type of the trade user wants to start
     * @param tradeItem item of the trade user wants to start
     * @param partner the user who is invited
     * @param languageRB the language bundle chosen by user
     */
    static void tradeInvitationScreen(IUserAccount user, String tradeType, ItemInterface tradeItem,
                                      IUserAccount partner, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    StartTradeTradeInvitationPage window =
                            new StartTradeTradeInvitationPage(user, tradeType, tradeItem, partner, languageRB);
                    window.tradeInvitationPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of the class for trade invitation page
     * @param user the user who sees this window
     * @param tradeType type of the trade user wants to start
     * @param tradeItem item of the trade user wants to start
     * @param partner the user who is invited
     * @param languageRB the language bundle chosen by user
     */
    StartTradeTradeInvitationPage(IUserAccount user, String tradeType, ItemInterface tradeItem,
                                  IUserAccount partner, ResourceBundle languageRB) throws Exception {
        this.startTradeMenu = new StartTradeMenu(user, tradeType, languageRB);
        this.tradeType = tradeType;
        this.tradeItem = tradeItem;
        this.partner = partner;
        this.languageRB = languageRB;
        initTradeInvitationPage(user);
    }

    /**
     * the initializer of trade invitation page
     * @param user the user who sees this window
     */
    private void initTradeInvitationPage(IUserAccount user) throws Exception {

        // setting the frame of the page for inviting lender
        tradeInvitationPageFrame = new JFrame();

        String inviteLenderButtonText = languageRB.getString("inviteLenderButtonText");
        String inviteBorrowerButtonText = languageRB.getString("inviteBorrowerButtonText");

        String title;
        if (tradeType.equals("borrow")) {
            title = inviteLenderButtonText;
        }else {
            title = inviteBorrowerButtonText;
        }
        tradeInvitationPageFrame.setTitle(title);

        tradeInvitationPageFrame.setBounds(0,0,1025,600);
        tradeInvitationPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tradeInvitationPageFrame.getContentPane().setLayout(null);
        tradeInvitationPageFrame.setVisible(true);

        tradeInvitationPageFrame.setResizable(true);
        tradeInvitationPageFrame.setLayout(new GridLayout(5, 1));
        uiNavigator.initBgColour(tradeInvitationPageFrame);

        JLabel headerLabel4 = new JLabel(title, JLabel.CENTER);

        tradeInvitationPageFrame.add(headerLabel4);

        // adding JPanel
        JPanel panel4 = new JPanel();
        tradeInvitationPageFrame.add(panel4);

        tradeInvitationPageOption(user, partner, panel4);

    }

    /**
     * sets the options shown on the window for trade invitation page
     * @param user the user who sees this page
     * @param partner the user who is invited
     * @param panel4 the JPanel for the buttons
     */
    private void tradeInvitationPageOption(IUserAccount user, IUserAccount partner, JPanel panel4) throws Exception {

        String isPermanentLabelText = languageRB.getString("isPermanentLabelText");

        JLabel isPermanent  = new JLabel(isPermanentLabelText);

        isPermanent.setBounds(100,80,50,25);

        String permanentButtonText = languageRB.getString("permanentButtonText");
        String temporaryButtonText = languageRB.getString("temporaryButtonText");

        JButton permanent = new JButton(permanentButtonText);
        JButton temporary = new JButton(temporaryButtonText);

        permanent.setBounds(200,80,30,25);
        temporary.setBounds(250,80,30,25);

        permanent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oneOrTwo(user, partner, true, panel4);
            }
        });
        temporary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oneOrTwo(user, partner, false, panel4);
            }
        });

        panel4.add(isPermanent);
        panel4.add(permanent);
        panel4.add(temporary);

        startTradeMenu.backToParentMenu(user, tradeInvitationPageFrame, panel4);

    }

    /**
     * lets user choose whether to start a one-way trade or a two-way trade
     * @param user the user who sees this page
     * @param partner the user who is invited
     * @param isPermanent the choice from user of whether to start a permanent trade or not
     * @param panel4 the JPanel for the buttons
     */
    private void oneOrTwo(IUserAccount user, IUserAccount partner,
                          boolean isPermanent, JPanel panel4) {

        RecommendationInterface recommendation = new Recommendation();
        List<ItemInterface> recommendedItems;
        if (tradeType.equals("borrow")) {
            recommendedItems = recommendation.SuggestLend(user, partner);
        }else {
            recommendedItems = recommendation.SuggestLend(partner, user);
        }

        int y = 115;


        if(recommendedItems.size() == 0){
            JLabel noItemFound = new JLabel(
                    "Sorry, There is no item system can suggest to you due to nothing in your" +
                            "WishToLend list is in your partner's WishToBorrow list.");
            tradeInvitationPageFrame.add(noItemFound);
        }else {
            for (ItemInterface recommendedItem : recommendedItems) {

                JLabel itemLabel = new JLabel(itemGUINavigator.getItemNameNavigator(recommendedItem));
                itemLabel.setBounds(100, y, 50, 25);

                panel4.add(itemLabel);
                y += 35;

            }
        }

        String oneOrTwoLabelText = languageRB.getString("oneOrTwoLabelText");

        JLabel oneOrTwo  = new JLabel(oneOrTwoLabelText);

        oneOrTwo.setBounds(100, y + 35, 50, 25);

        String oneWayButtonText = languageRB.getString("oneWayButtonText");
        String twoWayButtonText = languageRB.getString("twoWayButtonText");

        JButton oneWay = new JButton(oneWayButtonText);
        JButton twoWay = new JButton(twoWayButtonText);

        oneWay.setBounds(200, 115, 30, 25);
        twoWay.setBounds(250, 115, 30, 25);

        oneWay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ITransaction newTrans = null; // TODO: tradeType
                try {
                    newTrans = meetingSystemGUINavigator.createOneWayTransNavigator(
                            user, partner, tradeItem, isPermanent, tradeType);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                int transId = transGUINavigator.getTransIdNavigator(newTrans);
                String invitorId = userAccountGUINavigator.getUserIdNavigator(user);

                String tradeInvitation = languageRB.getString("tradeInvitation");
                String oneWayInvitationMessage = languageRB.getString("oneWayInvitationMessage");

                String invitation = tradeInvitation + " " + invitorId + " " + oneWayInvitationMessage + " " +
                        itemGUINavigator.getItemNameNavigator(tradeItem) + ". ";

                try {
                    userMessagesOpGUINavigator.addInviteTransNewMessageNavigates(
                            transId, invitation, userAccountGUINavigator.getUserIdNavigator(partner));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    userTransactionOpGUINavigator.addInvitedTransNavigator(
                            newTrans, userAccountGUINavigator.getUserIdNavigator(user));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });
        twoWay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ITransaction newTrans = null; // TODO: tradeType
                try {
                    newTrans = meetingSystemGUINavigator.createTwoWayTransNavigator(
                            user, partner, tradeItem, isPermanent, tradeType);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                int transId = transGUINavigator.getTransIdNavigator(newTrans);
                String invitorId = userAccountGUINavigator.getUserIdNavigator(user);

                String tradeInvitation = languageRB.getString("tradeInvitation");
                String twoWayInvitationMessage = languageRB.getString("twoWayInvitationMessage");

                String invitation = tradeInvitation + " " + invitorId + " " + twoWayInvitationMessage + " " +
                        itemGUINavigator.getItemNameNavigator(tradeItem) + ". ";

                try {
                    userMessagesOpGUINavigator.addInviteTransNewMessageNavigates(
                            transId, invitation, userAccountGUINavigator.getUserIdNavigator(partner));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    userTransactionOpGUINavigator.addInvitedTransNavigator(
                            newTrans, userAccountGUINavigator.getUserIdNavigator(user));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });

        panel4.add(oneOrTwo);
        panel4.add(oneWay);
        panel4.add(twoWay);

    }

}
