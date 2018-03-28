import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/*
* You need to implement Calendar GUI here!
* show the calendar of month of today.
* jump to last/next month's calendar
* jump to last/next year's calendar
*
* jump to one specific day's calendar
* */
public class Display {
    Button[][] days = new Button[6][7];
    CalendarDate today = DateUtil.getToday();
    CalendarDate inputDay;

    public Display(){}

    /**
     * Init the UI Windows here. For example, the frame, some panels and buttons.
     */
    private void init(){
        VBox frame = new VBox();

        /*
        The top nodes
         */
        HBox top = getTopNodes(today);

        /*
        Days of month and the arrow to next or last month
         */
        //Arrows
        HBox daysOfMonth = new HBox();
        Button leftArrow = new Button("<-");
        Button rightArrow = new Button("->");

        //Days and week names
        GridPane daysAndWeekName = getDaysAndWeekName(today);

        daysOfMonth.getChildren().addAll(leftArrow , daysAndWeekName , rightArrow);

        /*
        The frame of Calendar
         */
        frame.getChildren().addAll(top , daysOfMonth);
    }

    /**
     * paint the days of whole current month on the frame with the given CalendarDate
     * @param date a valid CalendarDate param.
     */
    private boolean paintDays(CalendarDate date) throws DateIllegalException{
        List<CalendarDate> dates =  DateUtil.getDaysInMonth(date);
        if (dates == null) throw new DateIllegalException("It's not legal");
        else {
            //Set the month's days and week
            int start = dates.get(0).getDayOfWeek() % 7;
            int index;
            for (int i = 0; i < days.length ; i ++){
                for (int j = 0; j < days[i].length; j++){
                    index = (j - start) + i * 7;
                    if (index < 0 || index > dates.size()) days[i][j].setText("");
                    else days[i][j].setText(dates.get(index).getDay() + "");
                }
            }
        }
        return true;
    }

    private HBox getTopNodes(CalendarDate date){
        HBox top = new HBox();
        //Show the current date
        Label currentDate = new Label();

        /*
        Show the choice of yeas and months
         */
        //years
        Label year = new Label("Year:");
        ComboBox<Integer> yearsChoice = new ComboBox<>();
        for (int i = date.getYear() - 3; i < date.getYear() + 3 ; i++ ){
            yearsChoice.getItems().add(i);
        }
        //months
        Label month = new Label("Month:");
        ComboBox<Integer> monthsChoice = new ComboBox<>();
        for (int i =1 ; i < 13 ; i ++){
            monthsChoice.getItems().add(i);
        }
        Button show = new Button("Show");

        //Show the search of date
        TextField inputSearch = new TextField();
        Button search = new Button("Search");

        top.getChildren().addAll(currentDate, year, yearsChoice, month, monthsChoice, show, inputSearch, search);

        return top;
    }

    private GridPane getDaysAndWeekName(CalendarDate date){
        GridPane daysAndWeekName = new GridPane();

        //Add week names
        Button[] weekNames = getWeekNames();
        for (int i = 0 ; i < weekNames.length ; i ++){
            daysAndWeekName.add(weekNames[i] , i, 0);
        }

        //Add days of month
        for (int i = 0 ;i < days.length; i ++){
            for (int j = 0; j < days[i].length ; j++){
                daysAndWeekName.add(days[i][j], j ,i + 1);
            }
        }

        return daysAndWeekName;
    }

    private Button[] getWeekNames(){
        Button[] weekNames = {new Button("Sunday") , new Button("Monday"),
                new Button("Tuesday"), new Button("Wednesday"), new Button("Thursday"),
                new Button("Friday"), new Button("Saturday")};
        return weekNames;
    }

}
