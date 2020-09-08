package View;

import Controller.UINavigator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * the class for choose language page
 * @author Hongyu Chen
 * @version 1.8
 */
public class ChooseLanguagePage extends JFrame {

    private final UINavigator uiNavigator = UINavigator.getInstance();

    JFrame chooseLanguagePageFrame;

    Locale languageLoc;
    ResourceBundle languageRB;

    /**
     * calls the page frame for the choose language page
     */
    static void chooseLanguageScreen() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ChooseLanguagePage window = new ChooseLanguagePage();
                    window.chooseLanguagePageFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * the constructor for ChooseLanguagePage
     */
    ChooseLanguagePage() {
        initChooseLanguagePage();
    }

    /**
     * the initializer of choose partner page
     */
    private void initChooseLanguagePage() {
        chooseLanguagePageOption();
    }

    /**
     * sets the options shown on the window for choose language page
     */
    private void chooseLanguagePageOption() {

        // setting the frame of the page for choosing lender
        String languageMenuTitle = "choose a language";
        chooseLanguagePageFrame = new JFrame(languageMenuTitle);
        chooseLanguagePageFrame.setSize(300, 300);
        chooseLanguagePageFrame.setLocationRelativeTo(null);
        chooseLanguagePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseLanguagePageFrame.setResizable(true);
        uiNavigator.initBgColour(chooseLanguagePageFrame);

        // adding JPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton en = new JButton("English");
        uiNavigator.formatButtonSize(en, panel);

        JButton zh = new JButton("中文");
        uiNavigator.formatButtonSize(zh, panel);

        JButton ca = new JButton("粵語");
        uiNavigator.formatButtonSize(ca, panel);

        JButton fr = new JButton("Français");
        uiNavigator.formatButtonSize(fr, panel);

        JButton sp = new JButton("Español");
        uiNavigator.formatButtonSize(sp, panel);

        JButton ja = new JButton("日本語");
        uiNavigator.formatButtonSize(ja, panel);

        mainPanel.add(panel);
        chooseLanguagePageFrame.add(mainPanel);

        en.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageLoc = new Locale("en", "CA");
                languageRB = ResourceBundle.getBundle("LanguageResources", languageLoc);
                new LoginMenu(languageRB);
                chooseLanguagePageFrame.setVisible(false);
            }
        });
        zh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageLoc = new Locale("zh", "CN");
                languageRB = ResourceBundle.getBundle("LanguageResources", languageLoc);
                new LoginMenu(languageRB);
                chooseLanguagePageFrame.setVisible(false);
            }
        });
        ca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageLoc = new Locale("zh", "HK");
                languageRB = ResourceBundle.getBundle("LanguageResources", languageLoc);
                new LoginMenu(languageRB);
                chooseLanguagePageFrame.setVisible(false);
            }
        });
        fr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageLoc = new Locale("fr", "CA");
                languageRB = ResourceBundle.getBundle("LanguageResources", languageLoc);
                new LoginMenu(languageRB);
                chooseLanguagePageFrame.setVisible(false);
            }
        });
        sp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageLoc = new Locale("es", "ES");
                languageRB = ResourceBundle.getBundle("LanguageResources", languageLoc);
                new LoginMenu(languageRB);
                chooseLanguagePageFrame.setVisible(false);
            }
        });
        ja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageLoc = new Locale("ja", "JA");
                languageRB = ResourceBundle.getBundle("LanguageResources", languageLoc);
                new LoginMenu(languageRB);
                chooseLanguagePageFrame.setVisible(false);
            }
        });
    }
}
