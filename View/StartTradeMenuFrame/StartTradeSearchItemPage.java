package View.StartTradeMenuFrame;

import Controller.*;
import Model.IUserAccount;
import Model.ItemInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * the class for search item page
 * @author Hongyu Chen
 * @version 1.8
 */
public class StartTradeSearchItemPage extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    private final UserAccountGUINavigator userAccountGUINavigator = UserAccountGUINavigator.getInstance();
    private final ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    private final UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();

    StartTradeMenu startTradeMenu;

    String tradeType;
    JFrame searchItemsPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the search item page
     * @param user the user who sees this window
     * @param tradeType type of the trade user wants to start
     * @param languageRB the language bundle chosen by user
     */
    static void searchItemScreen(IUserAccount user, String tradeType, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    StartTradeSearchItemPage window = new StartTradeSearchItemPage(user, tradeType, languageRB);
                    window.searchItemsPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of the class for search item page
     * @param user the user who sees this window
     * @param tradeType type of the trade user wants to start
     * @param languageRB the language bundle chosen by user
     */
    StartTradeSearchItemPage(IUserAccount user, String tradeType, ResourceBundle languageRB) throws Exception {
        this.startTradeMenu = new StartTradeMenu(user, tradeType, languageRB);
        this.tradeType = tradeType;
        this.languageRB = languageRB;
        initSearchItemPage(user);
    }

    /**
     * the initializer of search item page
     * @param user the user who sees this page
     */
    void initSearchItemPage(IUserAccount user) throws Exception {

        // setting the frame of the page for searching items
        searchItemsPageFrame = new JFrame();

        String searchItemsButtonText = languageRB.getString("searchItemsButtonText");
        String myAvailableItemsButtonText = languageRB.getString("myAvailableItemsButtonText");

        String title;
        if (tradeType.equals("borrow")) {
            title = searchItemsButtonText;
        }else {
            title = myAvailableItemsButtonText;
        }
        searchItemsPageFrame.setTitle(title);

        searchItemsPageFrame.setSize(600,4*50);
        searchItemsPageFrame.setLocationRelativeTo(null);
        searchItemsPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchItemsPageFrame.setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1,1));
        JLabel headerLabel2 = new JLabel(title, JLabel.CENTER);
        titlePanel.add(headerLabel2);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1,2));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));

        searchItemsPageOption(user, inputPanel, buttonPanel);
        mainPanel.add(titlePanel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        searchItemsPageFrame.add(mainPanel);
    }

    private ItemInterface tradeItem;

    /**
     * sets the options shown on the window for search item page
     * @param user the user who sees this menu
     * @param inputPanel the JPanel for user input
     * @param buttonPanel the JPanel for buttons
     */
    private void searchItemsPageOption(IUserAccount user, JPanel inputPanel, JPanel buttonPanel) throws Exception {

        String borrowWantLabelText = languageRB.getString("borrowWantLabelText");
        String lendWantLabelText = languageRB.getString("lendWantLabelText");

        String labelText;
        if (tradeType.equals("borrow")) {
            labelText = borrowWantLabelText;
        }else {
            labelText = lendWantLabelText;
        }

        JLabel searchLabel  = new JLabel(labelText, JLabel.CENTER);
        inputPanel.add(searchLabel);

        JTextField searchInput = new JTextField(20);
        inputPanel.add(searchInput);

        String searchButtonText = languageRB.getString("searchButtonText");
        JButton searchButton = new JButton(searchButtonText);
        uiNavigator.formatButtonSize(searchButton, buttonPanel);
        String noInputMessage = languageRB.getString("noInputMessage");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchItemName = searchInput.getText();
                if (searchItemName.isEmpty()){
                    JOptionPane.showMessageDialog(null, noInputMessage);
                } else {
                    List<String> searchItemNameInList = new ArrayList<>();
                    int i;
                    for (i = 0; i < searchItemName.length(); i++) {
                        searchItemNameInList.add(searchItemName.substring(i, i + 1));
                        searchItemNameInList.add(", ");
                    }
                    if (searchItemNameInList.size() != 0) {
                        List<IUserAccount> partners = getPartners(user, searchItemName);
                        if (partners.size() != 0) {
                            StartTradeChoosePartnerPage.choosePartnerScreen(
                                    user, tradeType, tradeItem, partners, languageRB);
                            searchItemsPageFrame.setVisible(false);
                        } else {
                            String failMessage = "fail: cannot find this item";
                            JLabel failMessageLabel = new JLabel(failMessage);
                            JOptionPane.showMessageDialog(null, failMessageLabel);
                        }
                    }
                }
            }
        });
        startTradeMenu.backToParentMenu(user, searchItemsPageFrame, buttonPanel);

    }

    /**
     * gets the partner(s)
     * @param user the user who may want to trade with user
     * @param tradeItemName the name of tradeItem
     * @return the user(s) who wants to start a trade
     */
    private List<IUserAccount> getPartners(IUserAccount user, String tradeItemName) {

        List<IUserAccount> partners = new ArrayList<>();

        Map<String, IUserAccount> normalUsers = userManagerGUINavigator.getNormalUsersNavigator();
        Map<String, IUserAccount> adminUsers = userManagerGUINavigator.getAdminUsersNavigator();

        for (Map.Entry<String, IUserAccount> normalUser : normalUsers.entrySet()) {

            if (!(normalUser.getKey().equals(userAccountGUINavigator.getUserIdNavigator(user)))) {

                List<ItemInterface> tradingItems;
                if (tradeType.equals("borrow")) {
                    tradingItems = userOpGUINavigator.getCurrentLendingNavigator(user);
                }else {
                    tradingItems = userOpGUINavigator.getWishToBorrowNavigator(user);
                }

                for (ItemInterface item : tradingItems) {
                    if (itemGUINavigator.getItemNameNavigator(item).equals(tradeItemName)) {
                        tradeItem = item;
                        partners.add(normalUser.getValue());
                    }
                }
            }

        }
        for (Map.Entry<String, IUserAccount> adminUser : adminUsers.entrySet()) {

            if (!(adminUser.getKey().equals(userAccountGUINavigator.getUserIdNavigator(user)))) {

                List<ItemInterface> tradingItems;
                if (tradeType.equals("borrow")) {
                    tradingItems = userOpGUINavigator.getCurrentLendingNavigator(user);
                }else {
                    tradingItems = userOpGUINavigator.getWishToBorrowNavigator(user);
                }

                for (ItemInterface item : tradingItems) {
                    if (itemGUINavigator.getItemNameNavigator(item).equals(tradeItemName)) {
                        tradeItem = item;
                        partners.add(adminUser.getValue());
                    }
                }
            }

        }

        return partners;

    }

}
