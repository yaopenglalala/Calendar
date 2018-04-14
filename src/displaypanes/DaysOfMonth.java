package displaypanes;

import init.CalendarDate;
import init.DateUtil;
import exceptions.DateIllegalException;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.control.Button;
import java.util.List;
import javafx.scene.control.Label;

public class DaysOfMonth extends Pane{
    CalendarDate currentDate;
    Label[] weekDays;
    Button[][] daysOfMonthButtons;

    public DaysOfMonth(CalendarDate date){
        currentDate = date;
        weekDays = new Label[7];
        daysOfMonthButtons = new Button[6][7];

        this.getChildren().add(newDaysOfMonthPane());
        this.getStyleClass().add("daysPane");
        this.getStylesheets().add(getClass().getResource("/DaysCSS.css").toString());
        updateDays(date);
    }

    public void updateDays(CalendarDate date){
        List<CalendarDate> dates =  DateUtil.getDaysInMonth(date);
        if (dates == null) throw new DateIllegalException("It's not legal");
        //Set the month's days and week
        int start = dates.get(0).getDayOfWeek() % 7;
        int index;
        for (int i = 0; i < daysOfMonthButtons.length ; i ++){
            for (int j = 0; j < daysOfMonthButtons[i].length; j++){
                index = (j - start) + i * 7;
                daysOfMonthButtons[i][j].getStyleClass().remove("choosedDay");
                if (index < 0 || index >= dates.size()) {
                    daysOfMonthButtons[i][j].setText("");
                    daysOfMonthButtons[i][j].getStyleClass().remove("dayButton");
                } else {
                    daysOfMonthButtons[i][j].setText(dates.get(index).getDay() + "");
                    daysOfMonthButtons[i][j].getStyleClass().add("dayButton");
                    if (dates.get(index).getDay() == date.getDay()) daysOfMonthButtons[i][j].getStyleClass().add("choosedDay");
                }
            }
        }
    }

    public GridPane newDaysOfMonthPane(){
        GridPane daysOfMonthPane = new GridPane();
        daysOfMonthPane.getStyleClass().add("daysPane");

        daysOfMonthPane.setHgap(10);
        daysOfMonthPane.setVgap(10);
        daysOfMonthPane.setAlignment(Pos.CENTER);

        //Add week names
        String[] weekDaysName = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0 ; i < weekDaysName.length ; i ++){
            weekDays[i] = new Label(weekDaysName[i]);
            weekDays[i].getStyleClass().add("weekName");
            daysOfMonthPane.add(weekDays[i] , i, 0);
        }

        //Add days of month
        for (int i = 0; i < daysOfMonthButtons.length; i ++){
            for (int j = 0; j < daysOfMonthButtons[i].length ; j++){
                daysOfMonthButtons[i][j] = new Button();
                daysOfMonthButtons[i][j].getStyleClass().add("nullButton");
                daysOfMonthPane.add(daysOfMonthButtons[i][j], j ,i + 1);
            }
        }
        return daysOfMonthPane;
    }

    public Button[][] getDaysOfMonthButtons() {
        return daysOfMonthButtons;
    }
}
