package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerOfIsOnVacation is a class that serves as a timer for users who set their isOnVacation status.
 * Once the users entry the inputs of startDate and endDate of their vacation plan, the timer will be created.
 * Timer1 would start the isOnVacation mode since the startDate while timer2 would end it since the endDate.
 *
 * @author Tianyan Zhou
 * @version 1.8
 */

public class TimerOfIsOnVacation {

    private Date startDate;
    private Date endDate;

    /**
     * the default constructor of class TimerOfIsOnVacation
     */
    public TimerOfIsOnVacation(){}

    /**
     *
     * @param user either the adminUser or normalUser who set its isOnVacation status.
     * @param startDate the start date of the vacation
     * @param endDate the end date of the vacation
     * @throws ParseException it throws exception of parse
     */
    public TimerOfIsOnVacation(IUserAccount user, Date startDate, Date endDate) throws ParseException {
        this.endDate = endDate;
        this.startDate = startDate;

        Timer t1 = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                user.setOnVacation(true);
                System.out.println("Vacation mode is on");
            }
        };
        t1.schedule(task1, startDate);

        Timer t2 = new Timer();
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                user.setOnVacation(false);
                System.out.println("Vacation mode is off");
            }
        };
        t2.schedule(task2, endDate);
    }


}


