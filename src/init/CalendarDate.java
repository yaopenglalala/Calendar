package init;

import exceptions.CalendarConstructException;

/**
 * We have finished part of this class yet, you should finish the rest.
 * 1. A constructor that can return a init.CalendarDate object through the given string.
 * 2. A method named getDayOfWeek() that can get the index of a day in a week.
 */
public class CalendarDate {
    private int year;
    private int month;
    private int day;

    public CalendarDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * a constructor that can return a init.CalendarDate object through the given string.
     *
     * @param dateString format: 2018-3-18
     */
    public CalendarDate(String dateString) throws CalendarConstructException {
        if (DateUtil.isFormatted(dateString)){
            String[] calendarArray = dateString.split("-");
            this.year = Integer.parseInt(calendarArray[0]);
            this.month = Integer.parseInt(calendarArray[1]);
            this.day = Integer.parseInt(calendarArray[2]);
        }else throw new CalendarConstructException("It's not a legal string");
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Get index of the day in a week for one date.
     * <p>
     * Don't use the existing implement like Calendar.setTime(),
     * try to implement your own algorithm.
     *
     * @return 1-7, 1 stands for Monday and 7 stands for Sunday
     */
    public int getDayOfWeek() {
        if (!DateUtil.isValid(this)) return -1;
        int yearZeller = year;
        int monthZeller = month;
        if (month < 3) {
            yearZeller --;
            monthZeller += 12;
        }
        int c = yearZeller / 100;
        int y = yearZeller - c * 100;

        int w = (c / 4) - (2 * c) + y + (y / 4) + ((13 * (monthZeller + 1)) / 5) + day - 1;
        return (w % 7 - 7) % 7 + 7;
    }
}
