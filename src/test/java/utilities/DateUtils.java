package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    public static String returnNextMonth(){
        //create date object
        Date dNow = new Date();

        //create calender object
        Calendar calendar = new GregorianCalendar();
        //set calender date to current date
        calendar.setTime(dNow);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
        calendar.add(Calendar.MONTH,1);

        return sdf.format(calendar.getTime());

        }

    }

