import CalendarDateCore.CalendarDate;
import CalendarDateCore.DateUtil;
import CalendarDisplayPanes.DaysOfMonth;
import CalendarDisplayPanes.TopFunctionPane;
import CalendarExceptions.DateIllegalException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/*
* You need to implement Calendar GUI here!
* show the calendar of month of today.
* jump to last/next month's calendar
* jump to last/next year's calendar
*
* jump to one specific day's calendar
* */
public class Display{
    private CalendarDate today;
    private CalendarDate currentDate;
    private TopFunctionPane topPane;
    private DaysOfMonth days;

    public Display(Stage stage){
        init();

        VBox frame = new VBox();
        frame.getChildren().addAll(topPane, days);

        Scene scene = new Scene(frame, 1000, 800);
        stage.setTitle("Calendar");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Init the UI Windows here. For example, the frame, some panels and buttons.
     */
    private void init(){
        today = DateUtil.getToday();
        topPane = new TopFunctionPane(today);
        days = new DaysOfMonth(today);
    }

    /**
     * paint the days of whole current month on the frame with the given CalendarDateCore.CalendarDate
     * @param date a valid CalendarDateCore.CalendarDate param.
     */
    private boolean paintDays(CalendarDate date) throws DateIllegalException {
        currentDate = date;
        try{
            days.updateDays(currentDate);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
