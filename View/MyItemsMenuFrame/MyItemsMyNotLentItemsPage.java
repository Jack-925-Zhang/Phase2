package View.MyItemsMenuFrame;

import Controller.ItemGUINavigator;
import Controller.UINavigator;
import Controller.UserOpGUINavigator;
import Model.IUserAccount;
import Model.ItemInterface;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ResourceBundle;

/**
 * the class for user's not lent items page
 * @author Hongyu Chen
 * @version 1.8
 */
public class MyItemsMyNotLentItemsPage extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    private final UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    MyItemsMenu myItemsMenu;

    JFrame myNotLentItemsPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the user's not lent items page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    static void myNotLentItemsPageScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyItemsMyNotLentItemsPage window = new MyItemsMyNotLentItemsPage(user, languageRB);
                    window.myNotLentItemsPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of the class for user's not lent items page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    MyItemsMyNotLentItemsPage(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.myItemsMenu = new MyItemsMenu(user, languageRB);
        this.languageRB = languageRB;
        initNotLentItemsPage(user);
    }

    /**
     * initializer for user's not lent items page
     * @param user the user who sees this page
     */
    private void initNotLentItemsPage(IUserAccount user) throws Exception {

        String myNotLentItemsLabelText = languageRB.getString("myNotLentItemsLabelText");
        List<ItemInterface> notLentItem = userOpGUINavigator.getWishToLendNavigator(user);

        // setting the frame of the page for user's not lent items
        myNotLentItemsPageFrame = new JFrame();
        myNotLentItemsPageFrame.setTitle(myNotLentItemsLabelText);
        myNotLentItemsPageFrame.setSize(300, (notLentItem.size()+3)*50);
        myNotLentItemsPageFrame.setLocationRelativeTo(null);
        myNotLentItemsPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myNotLentItemsPageFrame.setResizable(true);

        // adding JPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout((notLentItem.size()+3), 1));

        JLabel headerLabel3 = new JLabel(myNotLentItemsLabelText, JLabel.CENTER);
        panel.add(headerLabel3);

        myNotLentItemsPageOption(user, panel);
        mainPanel.add(panel);
        myNotLentItemsPageFrame.add(mainPanel);

    }

    /**
     * sets the options shown on the window for user's not lent items menu
     * @param user the user who sees this menu
     * @param panel3 the JPanel for the buttons
     */
    private void myNotLentItemsPageOption(IUserAccount user, JPanel panel3) throws Exception {

        String noNotItemLentLabelText = languageRB.getString("noNotItemLentLabelText");

        if (userOpGUINavigator.getWishToLendNavigator(user).size() == 0) {
            JLabel noItemNotLent = new JLabel(noNotItemLentLabelText, JLabel.CENTER);
            panel3.add(noItemNotLent);
        }else {
            for (ItemInterface notLentItem : userOpGUINavigator.getWishToLendNavigator(user)) {
                JLabel item = new JLabel(itemGUINavigator.getItemNameNavigator(notLentItem), JLabel.CENTER);
                panel3.add(item);
            }
        }

        myItemsMenu.backToParentMenu(user, myNotLentItemsPageFrame, panel3, languageRB);

    }

}
