package View.MyItemsMenuFrame;

import Controller.*;
import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.UserUseCase.IUserWishListOperation;
import UseCases.UserUseCase.UserWishListOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * the class for user's add item to wishlist page
 * @author Hongyu Chen
 * @version 1.8
 */
public class MyItemsAddToWishlistPage extends JFrame {
    private final UserWishListOpGUINavigator userWishListOpGUINavigator = UserWishListOpGUINavigator.getInstance();
    private final UINavigator uiNavigator = UINavigator.getInstance();
    private final TradingSystemGUINavigator tradingSystemGUINavigator = TradingSystemGUINavigator.getInstance();
    private final ItemGUINavigator itemGUINavigator = ItemGUINavigator.getInstance();
    MyItemsMenu myItemsMenu;

    JFrame addToWishlistPageFrame;

    ResourceBundle languageRB;

    /**
     * calls the page frame for the add to wishlist page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    static void addToWishlistPageScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyItemsAddToWishlistPage window = null;
                try {
                    window = new MyItemsAddToWishlistPage(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assert window != null;
                window.addToWishlistPageFrame.setVisible(true);
            }
        });
    }

    /**
     * the constructor of class for add to wishlist page
     * @param user the user who sees this page
     * @param languageRB the language bundle chosen by user
     */
    MyItemsAddToWishlistPage(IUserAccount user, ResourceBundle languageRB) throws Exception {
        this.myItemsMenu = new MyItemsMenu(user, languageRB);
        this.languageRB = languageRB;
        initAddToWishlistPage(user);
    }

    /**
     * initializer for add to wishlist page
     * @param user the user who sees this page
     */
    private void initAddToWishlistPage(IUserAccount user) throws Exception {

        String addToWishlistButtonText = languageRB.getString("addToWishlistButtonText");
        int height = tradingSystemGUINavigator.getInventoryNavigator().size();

        // setting the frame of the page for adding item
        addToWishlistPageFrame = new JFrame();
        addToWishlistPageFrame.setTitle(addToWishlistButtonText);
        addToWishlistPageFrame.setSize(400,(height+3)*50);
        addToWishlistPageFrame.setLocationRelativeTo(null);
        addToWishlistPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addToWishlistPageFrame.setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // adding JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height+3, 1));

        JLabel headerLabel5 = new JLabel(addToWishlistButtonText, JLabel.CENTER);
        panel.add(headerLabel5);
        addToWishlistPageOption(user, panel);
        mainPanel.add(panel);
        addToWishlistPageFrame.add(mainPanel);
        addToWishlistPageFrame.setVisible(true);
    }

    /**
     * sets the options shown on the window for add to wishlist menu
     * @param user the user who sees this menu
     * @param panel5 the JPanel for the buttons
     */
    private void addToWishlistPageOption(IUserAccount user, JPanel panel5) throws Exception {

        Map<Integer, ItemInterface> generalInventory = tradingSystemGUINavigator.getInventoryNavigator();
        String noItemInventoryMessage = languageRB.getString("noItemInventoryMessage");

        if (generalInventory.isEmpty()) {
            JLabel noItemInventoryMessageLabel = new JLabel(noItemInventoryMessage, JLabel.CENTER);
            panel5.add(noItemInventoryMessageLabel);
        }
        else {
            for (Map.Entry<Integer, ItemInterface> item : generalInventory.entrySet()) {

                JButton itemName = new JButton(itemGUINavigator.getItemNameNavigator(item.getValue()));

                itemName.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            userWishListOpGUINavigator.addToWishToBorrowNavigator(
                                    user, item.getValue());
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
                panel5.add(itemName);
            }
        }
        myItemsMenu.backToParentMenu(user, addToWishlistPageFrame, panel5, languageRB);
    }

}
