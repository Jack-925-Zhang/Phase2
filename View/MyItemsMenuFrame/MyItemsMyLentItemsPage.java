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
 * the class for user's lent items page
 * @author Hongyu Chen
 * @version 1.8
 */
public class MyItemsMyLentItemsPage extends JFrame {

    private final ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    private final UserOpGUINavigator userOpGUINavigator = UserOpGUINavigator.getInstance();
    private final UINavigator uiNavigator = UINavigator.getInstance();
    MyItemsMenu myItemsMenu;
    JFrame myLentItemsPageFrame;
    ResourceBundle languageRB;

    /**
     * calls the page frame for the user's lent items page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    static void myLentItemsPageScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyItemsMyLentItemsPage window = new MyItemsMyLentItemsPage(user, languageRB);
                    window.myLentItemsPageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of the class for user's lent items page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    MyItemsMyLentItemsPage(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.myItemsMenu = new MyItemsMenu(user, languageRB);
        this.languageRB = languageRB;
        initLentItemsPage(user);
    }

    /**
     * initializer for user's lent items page
     * @param user the user who sees this page
     */
    private void initLentItemsPage(IUserAccount user) throws Exception {

        String myLentItemsLabelText = languageRB.getString("myLentItemsLabelText");
        List<ItemInterface> lentItem = userOpGUINavigator.getCurrentLendingNavigator(user);
        // setting the frame of the page for user's lent items
        myLentItemsPageFrame = new JFrame();
        myLentItemsPageFrame.setTitle(myLentItemsLabelText);
        myLentItemsPageFrame.setSize(300, (lentItem.size()+3)*50);
        myLentItemsPageFrame.setLocationRelativeTo(null);
        myLentItemsPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myLentItemsPageFrame.setResizable(true);
        uiNavigator.initBgColour(myLentItemsPageFrame);

        // adding JPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout((lentItem.size()+3), 1));

        JLabel headerLabel2 = new JLabel(myLentItemsLabelText, JLabel.CENTER);
        panel.add(headerLabel2);

        myLentItemsPageOption(user, panel);
        mainPanel.add(panel);
        myLentItemsPageFrame.add(mainPanel);
    }

    /**
     * sets the options shown on the window for user's lent items menu
     * @param user the user who sees this menu
     * @param panel2 the JPanel for the buttons
     */
    private void myLentItemsPageOption(IUserAccount user, JPanel panel2) throws Exception {

        String noItemLentLabelText = languageRB.getString("noItemLentLabelText");

        if (userOpGUINavigator.getCurrentLendingNavigator(user).size() == 0) {
            JLabel noItemLent = new JLabel(noItemLentLabelText, JLabel.CENTER);
            panel2.add(noItemLent);
        }else {
            for (ItemInterface lentItem : userOpGUINavigator.getCurrentLendingNavigator(user)) {
                JLabel item = new JLabel(itemGUINavigator.getItemNameNavigator(lentItem), JLabel.CENTER);
                panel2.add(item);
            }
        }

        myItemsMenu.backToParentMenu(user, myLentItemsPageFrame, panel2, languageRB);

    }

}
