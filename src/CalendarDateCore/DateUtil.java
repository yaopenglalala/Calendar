package CalendarDateCore;/*
* This class provides some utils that may help you to finish this lab.
* getToday() is finished, you can use this method to get the current date.
* The other four methods getDaysInMonth(), isValid(), isFormatted() and isLeapYear() are not finished,
* you should implement them before you use.
*
* */
import CalendarDateCore.CalendarDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtil {
    /**
     * get a CalendarDateCore.CalendarDate instance point to today
     * @return a CalendarDateCore.CalendarDate object
     */
    public static CalendarDate getToday(){
        Calendar calendar = Calendar.getInstance();
        return new CalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * get all dates in the same month with given date
     * @param date the given date
     * @return a list of days in a whole month
     */
    public static List<CalendarDate> getDaysInMonth(CalendarDate date){
        if (date == null || !isValid(date)) return null;
        ArrayList<CalendarDate> daysInMonth = new ArrayList<>();
        CalendarDate tmpDate;
        for (int i = 1 ; i <= 31 ; i ++){
            tmpDate = new CalendarDate(date.getYear() , date.getMonth() , i);
            if (!isValid(tmpDate)) break;
            else daysInMonth.add(tmpDate);
        }
        return daysInMonth;
    }

    /**
     * Judge whether the input date is valid. For example, 2018-2-31 is not valid
     * @param date the input date
     * @return true if the date is valid, false if the date is not valid.
     */
    public static boolean isValid(CalendarDate date){
        if (date == null) return false;
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        return year >= 1800 && year <= 2300 &&
                month <= 12 && month >= 1 &&
                day >= 1 && day <= 31 &&
                ((day <= 30) || (month != 2 && month != 4 && month != 6 && month != 9 && month != 11)) &&
                ((day <= 29) || (month != 2)) &&
                ((day <= 28) || (month != 2) || isLeapYear(year));
//        else if (month > 12 || month < 1) return false;
//        else if (day < 1 || day > 31) return false;
//        else if ((day > 30) && (month == 2 || month == 4 || month == 6 || month == 9
//                || month == 11)) return false;
//        else if ((day > 29) && (month == 2)) return false;
//        else if ((day > 28) && (month == 2) && !isLeapYear(year)) return false;
//        return true;
    }

    /**
     * Judge whether the input is formatted.
     * For example, 2018/2/1 is not valid and 2018-2-1 is valid.
     * @param dateString
     * @return true if the input is formatted, false if the input is not formatted.
     */
    public static boolean isFormatted(String dateString){
        try {
            String[] calendarArray = dateString.split("-");
            if (calendarArray.length != 3) return false;
            else {
                for (String s : calendarArray){
                    Integer.parseInt(s);
                }
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Judge whether the input year is a leap year or not.
     * For example, year 2000 is a leap year, and 1900 is not.
     * @param year
     * @return true if the input year is a leap year, false if the input is not.
     */
    public static boolean isLeapYear(int year){
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

}

