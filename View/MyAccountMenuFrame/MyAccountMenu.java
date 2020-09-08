package View.MyAccountMenuFrame;

import UseCases.TradingSystem;
import UseCases.TradingSystemInterface;
import View.Menu;
import Model.IUserAccount;
import View.RegisterMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * sets my account menu
 * @author BINGQING WAN
 * @version 1.8
 */
public class MyAccountMenu {

    JFrame myAccountMenuFrame;
    ResourceBundle languageRB;

    /**
     * calls the menu frame for the my account menu
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyAccountMenu window = new MyAccountMenu(user, languageRB);
                    window.myAccountMenuFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor of class MyAccountPage
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public MyAccountMenu(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        init(user);
    }


    /**
     * this is the initializer for class MyAccountMenu
     */
    private void init(IUserAccount user) {

        String myAccountButtonText = languageRB.getString("myAccountButtonText");
        String userLabelText = languageRB.getString("userLabelText");
        String homeCityLabelText = languageRB.getString("homeCityLabelText");
        String accountStatusLabelText = languageRB.getString("accountStatusLabelText");

        myAccountMenuFrame = new JFrame();
        myAccountMenuFrame.setTitle(myAccountButtonText);
        myAccountMenuFrame.setSize(300,350);
        myAccountMenuFrame.setLocationRelativeTo(null);
        myAccountMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myAccountMenuFrame.setResizable(true);
        myAccountMenuFrame.setVisible(true);
        myAccountMenuFrame.setLayout(new GridLayout(7, 1));

        String userStatus;
        String initialAdminLabelText = languageRB.getString("initialAdminLabelText");
        String adminLabelText = languageRB.getString("adminLabelText");
        String normalLabelText = languageRB.getString("normalLabelText");
        String guestLabelText = languageRB.getString("guestLabelText");

        switch (user.getStatus()) {
            case "Admin":
                if (user.getIsInitialAdmin()) {
                    userStatus = initialAdminLabelText;
                } else {
                    userStatus = adminLabelText;
                }
                break;
            case "Normal":
                userStatus = normalLabelText;
                break;
            case "Guest":
                userStatus = guestLabelText;
                break;
            default:
                throw new IllegalStateException("Unexpected user status: " + user.getStatus());
        }

        JLabel headerLabel = new JLabel(" " + myAccountButtonText, JLabel.CENTER);
        JLabel userId = new JLabel(" " + userLabelText + " " + user.getUserId());
        JLabel userCity = new JLabel(" " + homeCityLabelText + " " + user.getHomeCity());
        JLabel accountStatus = new JLabel(" " +accountStatusLabelText + " " + userStatus);

        myAccountMenuFrame.add(headerLabel);
        myAccountMenuFrame.add(userId);
        myAccountMenuFrame.add(accountStatus);
        if (!user.getStatus().equals("Guest")) {
            myAccountMenuFrame.add(userCity);
        }
        myAccountMenuOption(user);

    }

    /**
     * sets the options shown on the window for my account menu
     * @param user the user who sees this menu
     */
    private void myAccountMenuOption(IUserAccount user) {

        String changePasswordLabelText = languageRB.getString("changePasswordLabelText");
        String mainMenuButtonText = languageRB.getString("mainMenuButtonText");
        String userVocationText = languageRB.getString("userVacationText");
        String registerButtonText = languageRB.getString("registerButtonText");

        if (! user.getStatus().equals("Guest")){

            JButton changePassword = new JButton(changePasswordLabelText);
            JPanel changePasswordSize = new JPanel();
            changePasswordSize.add(changePassword);
            JButton setVocation = new JButton(userVocationText);
            JPanel setVocationSize = new JPanel();
            setVocationSize.add(setVocation);

            changePassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyAccountChangePasswordPage.MenuScreen(user, languageRB);
                    myAccountMenuFrame.setVisible(false);
                }
            });

            setVocation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyAccountVacationPage.MenuScreen(user, languageRB);
                    myAccountMenuFrame.setVisible(false);
                }
            });
            myAccountMenuFrame.add(changePasswordSize);
            myAccountMenuFrame.add(setVocationSize);
        }

        JButton mainMenu = new JButton(mainMenuButtonText);
        JPanel mainMenuSize = new JPanel();
        mainMenuSize.add(mainMenu);
        JButton register = new JButton(registerButtonText);
        JPanel registerSize = new JPanel();
        registerSize.add(register);

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.MenuScreen(user, languageRB);
                myAccountMenuFrame.setVisible(false);

            }
        });
        TradingSystemInterface tradingSystem = TradingSystem.getInstance();
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterMenu.MenuScreen(tradingSystem, languageRB);
                myAccountMenuFrame.setVisible(false);
            }
        });
        myAccountMenuFrame.add(mainMenuSize);
        String guestNotificationButtonText = languageRB.getString("guestNotificationButtonText");
        if (user.getStatus().equals("Guest")) {
            JLabel guestNotification = new JLabel(guestNotificationButtonText);
            myAccountMenuFrame.add(guestNotification);
            myAccountMenuFrame.add(registerSize);
        }

    }

}
