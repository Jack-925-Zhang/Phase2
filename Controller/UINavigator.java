package Controller;

import GateWay.DataUpdater;
import GateWay.IDataUpdater;
import View.Menu;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * This class, UINavigator, navigates every method needed by the classes Menu,
 * BorrowItemsMenu, LendItemsMenu, MyItemsMenu, MyTradesMenu, MyAccountMenu,
 * or ManageUserMenu from the interfaces and the use case classes.
 *
 * @author Hongyu Chen
 * @version 1.8
 */
public class UINavigator {

    private static final UINavigator uiNavigator = new UINavigator();
    private final IDataUpdater dataUpdater;


    /**
     * the constructor for class UINavigator
     */
    public UINavigator() {
        dataUpdater = new DataUpdater();
    }

    /**
     * gets the attribute uiNavigator
     * @return uiNavigator
     */
    public static UINavigator getInstance(){
        return uiNavigator;
    }

    /**
     * goes back to main menu
     * @param user the user who sees this
     * @param currentFrame the JFrame which will be disposed
     * @param panel the JPanel where the buttons are added
     * @param languageRB the language bundle chosen by user
     */
    public void backToMainMenu(
            IUserAccount user, JFrame currentFrame, JPanel panel, ResourceBundle languageRB) throws Exception {

        String mainMenuButtonText = languageRB.getString("mainMenuButtonText");

        JButton mainMenu = new JButton(mainMenuButtonText);
        formatButtonSize(mainMenu, panel);

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.MenuScreen(user, languageRB);
                currentFrame.setVisible(false);
            }
        });
    }

    /**
     * set colour for menu's frame
     * @param frame menu's frame
     */
    public void initBgColour(JFrame frame) {
        Container pane = frame.getContentPane();
        pane.setBackground(Color.lightGray);
    }

    public void formatButtonSize(JButton button, JPanel panel){
        JPanel sizePanel = new JPanel();
        sizePanel.add(button);
        panel.add(sizePanel);
    }
}
