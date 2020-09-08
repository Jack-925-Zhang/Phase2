package View.MyAccountMenuFrame;

import Controller.UINavigator;
import Controller.UserManagerGUINavigator;
import Model.IUserAccount;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * sets the vacation page in my account menu
 * @author BINGQING WAN
 * @version 1.8
 */
public class MyAccountVacationPage {
    ResourceBundle languageRB;
    UserManagerGUINavigator userManagerGUINavigator = UserManagerGUINavigator.getInstance();
    UINavigator uiNavigator = UINavigator.getInstance();
    /**
     * calls the menu frame for the vacation page
     * @param user the user who sees this window
     * @param languageRB the language bundle chosen by user
     */
    public static void MenuScreen(IUserAccount user, ResourceBundle languageRB) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MyAccountVacationPage(user, languageRB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * the constructor of class MyAccountVacationPage
     * @param user the user who sees this menu
     * @param languageRB the language bundle chosen by user
     */
    public MyAccountVacationPage(IUserAccount user, ResourceBundle languageRB) {
        this.languageRB = languageRB;
        initializeVacation(user);
    }
    /**
     * initialize the frame properties and set up buttons and their actions
     * @param user the user who sees this menu
     */
    private void initializeVacation(IUserAccount user){
        String setVocationLabelText = languageRB.getString("setVocationLabelText");
        String startDateLabelText = languageRB.getString("startDateLabelText");
        String endDateLabelText = languageRB.getString("endDateLabelText");

        JFrame setVocationFrame = new JFrame(setVocationLabelText);
        setVocationFrame.setSize(300,5*50);
        setVocationFrame.setLocationRelativeTo(null);
        setVocationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVocationFrame.setResizable(true);
        // set up date picker for vacation start date
        UtilDateModel startDateModel = new UtilDateModel();
        Properties startP = new Properties();
        startP.put("text.today", "Today");
        startP.put("text.month", "Month");
        startP.put("text.year", "Year");
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startDateModel, startP);
        JDatePickerImpl startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
        // set up date picker for vacation end date
        UtilDateModel endDateModel = new UtilDateModel();
        Properties endP = new Properties();
        endP.put("text.today", "Today");
        endP.put("text.month", "Month");
        endP.put("text.year", "Year");
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateModel, startP);
        JDatePickerImpl endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // title panel
        JPanel setTitlePanel = new JPanel();
        setTitlePanel.setLayout(new GridLayout(1, 1));
        // title page label
        JLabel setVocationLabel = new JLabel(setVocationLabelText, JLabel.CENTER);
        setTitlePanel.add(setVocationLabel);
        // select date panel
        JPanel setVocationPanel = new JPanel();
        setVocationPanel.setLayout(new GridLayout(2, 2));

        JLabel startDateLabel = new JLabel(startDateLabelText, JLabel.CENTER);
        JLabel endDateLabel = new JLabel(endDateLabelText, JLabel.CENTER);

        setVocationPanel.add(startDateLabel);
        setVocationPanel.add(startDatePicker);
        setVocationPanel.add(endDateLabel);
        setVocationPanel.add(endDatePicker);
        // going back to other menu panel
        JPanel backMenuPanel = new JPanel();
        backMenuPanel.setLayout(new GridLayout(2, 1));
        String completeButtonText = languageRB.getString("completeButtonText");
        String myAccountButtonText = languageRB.getString("myAccountButtonText");
        JButton btnComplete = new JButton(completeButtonText);
        uiNavigator.formatButtonSize(btnComplete, backMenuPanel);
        JButton btnMyAccount = new JButton(myAccountButtonText);
        uiNavigator.formatButtonSize(btnMyAccount, backMenuPanel);

        btnComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean startCorrect = true;
                boolean compare = true;
                String startDateSelectFail = languageRB.getString("startDateSelectFail");
                String endDateSelectFail = languageRB.getString("endDateSelectFail");
                String successMessage = languageRB.getString("successMessage");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                String currDate = dtf.format(now);
                String startDateText = startDatePicker.getJFormattedTextField().getText();
                String endDateText = endDatePicker.getJFormattedTextField().getText();
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                Date curr = null;
                Date start = null;
                Date end = null;
                try {
                    curr = sdformat.parse(currDate);
                    start = sdformat.parse(startDateText);
                    end = sdformat.parse(endDateText);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                if (start.compareTo(curr)<0){
                    startCorrect = false;
                    JOptionPane.showMessageDialog(null, startDateSelectFail);
                }
                if (end.compareTo(start)<=0){
                    compare = false;
                    JOptionPane.showMessageDialog(null, endDateSelectFail);
                }
                else if (startCorrect) {
                    Date startDate;
                    Date endDate;
                    try {
                        startDate = new Date(String.valueOf(sdformat.parse(startDateText)));
                        endDate = new Date(String.valueOf(sdformat.parse(endDateText)));
                        userManagerGUINavigator.setUserVacationTimerNavigator(user, startDate, endDate);
                        JOptionPane.showMessageDialog(null, successMessage);
                        MyAccountMenu.MenuScreen(user, languageRB);
                        setVocationFrame.setVisible(false);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
            }
        });
        btnMyAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyAccountMenu.MenuScreen(user, languageRB);
                setVocationFrame.setVisible(false);
            }
        });
        mainPanel.add(setTitlePanel);
        mainPanel.add(setVocationPanel);
        mainPanel.add(backMenuPanel);
        setVocationFrame.add(mainPanel);
        setVocationFrame.setVisible(true);
    }
    /**
     * this method formats the date to wanted types
     */
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final String datePattern = "yyyy-MM-dd";
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }
        @Override
        public String valueToString(Object value) {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }

    }
}
