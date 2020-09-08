package View;

import GateWay.DataBuilder;
import GateWay.DataDriver;
import GateWay.DataLoading;
import UseCases.TradingSystem;
import UseCases.TradingSystemInterface;

import java.awt.EventQueue;

/**
 * this class provides access to ChooseLanguageMenu then login
 * @author BINGQING WAN
 * @version 1.8
 */
public class Main {
    static TradingSystemInterface tradingSystem = TradingSystem.getInstance();

    /**
     * calls the menu frame for the choose language menu
     */
    public static void chooseLanguageScreen() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ChooseLanguagePage.chooseLanguageScreen();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * this method access to choosing language menu
     * @param args the argument the user/people send out to the system.
     */
    public static void main(String[] args) throws Exception {
        chooseLanguageScreen();

        // connecting to database
        DataDriver driver = DataDriver.getInstance();

        // building all tables
        DataBuilder builder = DataBuilder.getInstance();
        builder.setupUserAccount(driver.getUserConnectionSource());
        builder.setupTransactionDataBase(driver.getTransactionConnectionSource());
        builder.setupItemDataBase(driver.getItemConnectionSource());

        // loading all information
        DataLoading loading = new DataLoading();
        loading.accountLoading();
        loading.adminsOperationsLoading();
        loading.itemLoading();
        loading.meetingSystemLoading();
        loading.usersListsLoading();
        tradingSystem.setUserManager(loading.accountLoading());
        tradingSystem.setGeneralInventory(loading.itemLoading());
    }

}
