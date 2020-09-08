package View.StartTradeMenuFrame;

import Controller.*;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ResourceBundle;

/**
 * the class for start trade menu
 * @author Hongyu Chen
 * @version 1.8
 */
public class StartTradeMenu extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();

    String tradeType;
    JFrame startTradeMenuFrame;

    ResourceBundle languageRB;

    /**
     * calls the menu frame for the borrow items menu or lend item menu
     * @param user the user who sees this window
     * @param borrowOrLend type of trade started ("borrow" or "lend")
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, String borrowOrLend, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    StartTradeMenu window = new StartTradeMenu(user, borrowOrLend, languageRB);
                    window.startTradeMenuFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class StartTradeMenu
     * @param user the user who sees this menu
     * @param tradeType type of trade started ("borrow" or "lend")
     * @param languageRB the language bundle chosen by user
     */
    StartTradeMenu(IUserAccount user, String tradeType, ResourceBundle languageRB) throws Exception {
        this.tradeType = tradeType;
        this.languageRB = languageRB;
        init(user);
    }

    /**
     * the initializer for class BorrowItemsMenu
     * @param user the user who sees this menu
     */
    private void init(IUserAccount user) throws Exception {

        startTradeMenuFrame = new JFrame();

        String borrowItemsButtonText = languageRB.getString("borrowItemsButtonText");
        String lendItemsButtonText = languageRB.getString("lendItemsButtonText");

        String title;
        if (tradeType.equals("borrow")) {
            title = borrowItemsButtonText;
        }else {
            title = lendItemsButtonText;
        }

        startTradeMenuFrame.setTitle(title);

        startTradeMenuFrame.setSize(350,4*50);
        startTradeMenuFrame.setLocationRelativeTo(null);
        startTradeMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startTradeMenuFrame.setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(3,1));

        JLabel headerLabel1 = new JLabel(title, JLabel.CENTER);
        optionPanel.add(headerLabel1);
        startTradeMenuOption(user, optionPanel);
        mainPanel.add(optionPanel);
        startTradeMenuFrame.add(mainPanel);
    }

    /**
     * sets the options shown on the window for borrow items menu or lend items menu
     * @param user the user who sees this menu
     * @param panel1 the JPanel for the buttons
     */
    private void startTradeMenuOption(IUserAccount user, JPanel panel1) throws Exception {

        String searchItemsButtonText = languageRB.getString("searchItemsButtonText");
        String myAvailableItemsButtonText = languageRB.getString("myAvailableItemsButtonText");

        String buttonText;
        if (tradeType.equals("borrow")) {
            buttonText = searchItemsButtonText;
        }else {
            buttonText = myAvailableItemsButtonText;
        }

        JButton searchItem = new JButton(buttonText);
        uiNavigator.formatButtonSize(searchItem, panel1);

        searchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartTradeSearchItemPage.searchItemScreen(user, tradeType, languageRB);
                startTradeMenuFrame.setVisible(false);
            }
        });

        uiNavigator.backToMainMenu(user, startTradeMenuFrame, panel1, languageRB);
    }

    /**
     * goes back to borrow items menu or lend items menu
     * @param user the user who sees this menu
     * @param currentFrame the JFrame which will be disposed
     * @param panel the JPanel where the buttons are added
     */
    void backToParentMenu(IUserAccount user, JFrame currentFrame, JPanel panel) throws Exception {

        uiNavigator.backToMainMenu(user, currentFrame, panel, languageRB);

    }

}
