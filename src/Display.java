import CalendarDateCore.CalendarDate;
import CalendarDateCore.DateUtil;
import CalendarDisplayPanes.DaysOfMonth;
import CalendarDisplayPanes.ErrorAlert;
import CalendarDisplayPanes.TopFunctionPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        stage.setResizable(false);

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
        currentDate = DateUtil.getToday();

        topPane = new TopFunctionPane(currentDate);
        days = new DaysOfMonth(currentDate);

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
                    updateTopPane(currentDate);
                }
            }
        });

        topPane.getResetButton().setOnAction(e ->{
            paintDays(today);
        });

        Button[][] daysButtons = days.getDaysOfMonthButtons();
        for (Button[] daysButton : daysButtons) {
            for (Button dayButton : daysButton) {
                dayButton.setOnAction(e -> {
                    if (!dayButton.getText().equals("")) {
                        currentDate.setDay(Integer.parseInt(dayButton.getText()));
                        paintDays(currentDate);
                    }
                });
            }
        }
    }

    /**
     * paint the days of whole current month on the frame with the given CalendarDateCore.CalendarDate
     * @param date a valid CalendarDateCore.CalendarDate param.
     */
    private boolean paintDays(CalendarDate date){
        currentDate.setYear(date.getYear());
        currentDate.setMonth(date.getMonth());
        currentDate.setDay(date.getDay());
        try{
            updateTopPane(date);
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

    public void updateTopPane(CalendarDate date){
        topPane.setCurrentDateLabel(date);
        topPane.getYearsChoiceInput().setText(date.getYear() + "");
        topPane.getMonthsChoiceInput().setText(date.getMonth() + "");
    }
}
