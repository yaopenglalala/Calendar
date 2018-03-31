package CalendarDisplayPanes;

import CalendarDateCore.CalendarDate;
import CalendarDateCore.DateUtil;
import CalendarExceptions.DateIllegalException;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.control.Button;
import java.util.List;
import javafx.scene.control.Label;

public class DaysOfMonth extends Pane{
    Label[] weekDays;
    Button[][] daysOfMonth;

    public DaysOfMonth(CalendarDate date){
        weekDays = new Label[7];
        daysOfMonth = new Button[6][7];
        for (int i = 0; i < daysOfMonth.length ; i ++){
            for (int j = 0; j < daysOfMonth[i].length; j++){
                daysOfMonth[i][j] = new Button();
            }
        }

        updateDays(date);

        this.getChildren().add(getDaysOfMonthPane());
    }

    public void updateDays(CalendarDate date){
        List<CalendarDate> dates =  DateUtil.getDaysInMonth(date);
        if (dates == null) throw new DateIllegalException("It's not legal");
        else {
            //Set the month's days and week
            int start = dates.get(0).getDayOfWeek() % 7;
            int index;
            for (int i = 0; i < daysOfMonth.length ; i ++){
                for (int j = 0; j < daysOfMonth[i].length; j++){
                    index = (j - start) + i * 7;
                    if (index < 0 || index >= dates.size()) daysOfMonth[i][j].setText("");
                    else daysOfMonth[i][j].setText(dates.get(index).getDay() + "");
                }
            }
        }
    }

    public GridPane getDaysOfMonthPane(){
        GridPane daysOfMonthPane = new GridPane();

        //Add week names
        String[] weekDaysName = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0 ; i < weekDaysName.length ; i ++){
            weekDays[i] = new Label(weekDaysName[i]);
            daysOfMonthPane.add(weekDays[i] , i, 0);
        }

        //Add days of month
        for (int i = 0 ;i < daysOfMonth.length; i ++){
            for (int j = 0; j < daysOfMonth[i].length ; j++){
                daysOfMonthPane.add(daysOfMonth[i][j], j ,i + 1);
            }
        }
        return daysOfMonthPane;
    }
}
