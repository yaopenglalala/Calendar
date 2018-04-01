import CalendarDateCore.CalendarDate;
import CalendarDateCore.DateUtil;
import CalendarDisplayPanes.DaysOfMonth;
import CalendarDisplayPanes.ErrorAlert;
import CalendarDisplayPanes.TopFunctionPane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        Scene scene = new Scene(frame);
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

        topPane.getJumpButton().setOnAction(e -> {
            String yearString = topPane.getYearsChoiceInput().getText();
            String monthString = topPane.getMonthsChoiceInput().getText();
            CalendarDate tmpDate = getJumpDate(yearString , monthString);
            if (DateUtil.isValid(tmpDate) && (tmpDate.getYear() < 2300 || tmpDate.getYear() > 1800)){
                currentDate = tmpDate;
                paintDays(currentDate);
            } else {
                if (tmpDate == null) {
                    new ErrorAlert(1).showAndWait();
                } else {
                    new ErrorAlert(2).showAndWait();
                }
            }
        });

        topPane.getSearchButton().setOnAction(e ->{
            String searchInput = topPane.getSearchInput().getText();
            if (!DateUtil.isFormatted(searchInput)){
                new ErrorAlert(1).showAndWait();
            }else {
                CalendarDate tmpDate = new CalendarDate(searchInput);
                if (!DateUtil.isValid(tmpDate)) new ErrorAlert(2).showAndWait();
                else {
                    currentDate = tmpDate;
                    paintDays(currentDate);
                    updateTopPane();
                }
            }
        });
    }

    /**
     * paint the days of whole current month on the frame with the given CalendarDateCore.CalendarDate
     * @param date a valid CalendarDateCore.CalendarDate param.
     */
    private boolean paintDays(CalendarDate date){
        currentDate = date;
        try{
            days.updateDays(currentDate);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public CalendarDate getJumpDate(String yearString, String monthString){
        try {
            int year = Integer.parseInt(yearString);
            int month = Integer.parseInt(monthString);
            return new CalendarDate(year , month , 1);
        } catch (Exception e){
            return null;
        }
    }

    public void updateTopPane(){
        topPane.getYearsChoiceInput().setText(currentDate.getYear() + "");
        topPane.getMonthsChoiceInput().setText(currentDate.getMonth() + "");
    }
}
