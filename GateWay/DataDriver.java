package GateWay;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;

/**
 * This class DataDriver will connect to an existing database.
 * If the database does not exist, it will be created.
 * It implements IDataController
 * @author LINNI XIE
 * @version 1.8
 */
public class DataDriver {

    private final static String USER_ACCOUNT_URL = "jdbc:sqlite:userAccount.db";
    private final static String ITEM_URL = "jdbc:sqlite:item.db";
    private final static String TRANSACTION_URL = "jdbc:sqlite:transaction.db";


    private static ConnectionSource userConnectionSource = null;
    private static ConnectionSource itemConnectionSource = null;
    private static ConnectionSource transactionConnectionSource = null;

    private static DataDriver dataDriver = null;

    static {
        try {
            dataDriver = new DataDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * gets instance for dataDriver
     * @return dataDriver
     */
    public static DataDriver getInstance(){
        return dataDriver;
    }


    /**
     * the constructor for class DataDriver
     * @throws Exception exception thrown when connection to database is not found
     */
    public DataDriver() throws Exception {
        UserConnectionSource();
        ItemConnectionSource();
        TransactionConnectionSource();
    }

    /**
     * Connect the UserConnectionSource if it doesn't exist then it will create new ConnectionSource.
     * @throws Exception Exception delete error
     * @see Exception
     */
    private static void UserConnectionSource() throws Exception {
        try {
            // create our data-source for the database
            Class.forName("org.sqlite.JDBC");
            //userConnectionSource = (ConnectionSource) DriverManager.getConnection(USER_ACCOUNT_URL);
            userConnectionSource = new JdbcConnectionSource(USER_ACCOUNT_URL);
            System.out.println("\n\nIt seems to have worked\n\n");
        } finally {
            if (userConnectionSource != null) {
                userConnectionSource.close();
            }
        }
    }

    /**
     * Connect the ItemConnectionSource if it doesn't exist then it will create new ConnectionSource.
     * @throws Exception Exception delete error
     * @see Exception
     */
    private static void ItemConnectionSource() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            //itemConnectionSource = (ConnectionSource) DriverManager.getConnection(ITEM_URL);
            itemConnectionSource = new JdbcConnectionSource(ITEM_URL);
            System.out.println("\n\nIt seems to have worked\n\n");
        } finally {
            if (itemConnectionSource != null) {
                itemConnectionSource.close();
            }
        }
    }

    /**
     * Connect the TransactionConnectionSource if it doesn't exist then it will create new ConnectionSource.
     * @throws Exception Exception delete error
     * @see Exception
     */
    private static void TransactionConnectionSource() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            //transactionConnectionSource = (ConnectionSource) DriverManager.getConnection(TRANSACTION_URL);
            transactionConnectionSource = new JdbcConnectionSource(TRANSACTION_URL);
            System.out.println("\n\nIt seems to have worked\n\n");
        } finally {
            if (transactionConnectionSource != null) {
                transactionConnectionSource.close();
            }
        }
    }

    /**
     * gets user's connection source
     * @return user's connection source
     */
    public ConnectionSource getUserConnectionSource() {
        return userConnectionSource;
    }

    /**
     * gets item's connection source
     * @return item's connection source
     */
    public ConnectionSource getItemConnectionSource() {
        return itemConnectionSource;
    }

    /**
     * gets transaction's connection source
     * @return transaction's connection source
     */
    public ConnectionSource getTransactionConnectionSource() {
        return transactionConnectionSource;
    }

    public void closeConnectionSource() throws IOException {
        userConnectionSource.close();
        itemConnectionSource.close();
        transactionConnectionSource.close();
    }
}
