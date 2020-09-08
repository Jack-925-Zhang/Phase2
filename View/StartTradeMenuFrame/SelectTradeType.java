package View.StartTradeMenuFrame;

import Controller.UINavigator;
import Model.IUserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * the class for select trade type page
 * @author Bingqing Wan
 * @version 1.8
 */
public class SelectTradeType {
    private final UINavigator uiNavigator = UINavigator.getInstance();
    JFrame menuFrame;
    ResourceBundle languageRB;

    /**
     * calls the menu frame for the main menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SelectTradeType window = new SelectTradeType(user, languageRB);
                    window.menuFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class Menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public SelectTradeType(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        init(user);
    }

    /**
     * the initializer for class Menu
     * @param user the user who sees this window
     */
    private void init(IUserAccount user) {

        String startNewTransactionButtonText = languageRB.getString("startNewTransactionButtonText");

        menuFrame = new JFrame();
        menuFrame.setTitle(startNewTransactionButtonText);
        menuFrame.setSize(350,4*50);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setResizable(true);

        JLabel headerLabel = new JLabel(startNewTransactionButtonText, JLabel.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(4,1));

        optionPanel.add(headerLabel);
        initMenuOptions(user, optionPanel);
        mainPanel.add(optionPanel);
        menuFrame.add(mainPanel);
    }

    /**
     * sets the options shown on the window for the main menu
     * @param user the user who sees this main menu
     * @param optionPanel the JPanel for the buttons
     */
    private void initMenuOptions(IUserAccount user, JPanel optionPanel) {

        String borrowItemsButtonText = languageRB.getString("borrowItemsButtonText");
        String lendItemsButtonText = languageRB.getString("lendItemsButtonText");

        JButton borrowItems = new JButton(borrowItemsButtonText);
        JButton lendItems = new JButton(lendItemsButtonText);
        uiNavigator.formatButtonSize(borrowItems, optionPanel);
        uiNavigator.formatButtonSize(lendItems, optionPanel);
        try {
            uiNavigator.backToMainMenu(user, menuFrame, optionPanel, languageRB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        borrowItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartTradeMenu.MenuScreen(user, "borrow", languageRB);
                menuFrame.setVisible(false);
            }
        });
        lendItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartTradeMenu.MenuScreen(user, "lend", languageRB);
                menuFrame.setVisible(false);
            }
        });

    }

}
