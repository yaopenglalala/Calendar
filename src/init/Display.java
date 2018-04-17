package init;

import displaypanes.DaysOfMonth;
import displaypanes.ErrorAlert;
import displaypanes.ItemControlPane;
import displaypanes.TopFunctionPane;
import itemcontrol.ItemsController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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
    private CalendarDate currentDate;
    private ItemsController itemsController;
    private TopFunctionPane topPane;
    private DaysOfMonth days;
    private ItemControlPane itemControlPane;

    public Display(Stage stage){
        init();
        stage.setResizable(false);

        VBox calendar = new VBox();
        calendar.getChildren().addAll(topPane, days);

        HBox frame = new HBox();
        frame.getChildren().addAll(calendar, itemControlPane);
        Scene scene = new Scene(frame);
        stage.setTitle("Calendar");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Init the UI Windows here. For example, the frame, some panels and buttons.
     */
    private void init(){
        currentDate = DateUtil.getToday();
        itemsController = new ItemsController();

        topPane = new TopFunctionPane(currentDate);
        days = new DaysOfMonth(currentDate);
        itemControlPane = new ItemControlPane(itemsController);

        initJumpButton();
        initSearchButton();

        topPane.getResetButton().setOnAction(e -> paintDays(DateUtil.getToday()));

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
     * paint the days of whole current month on the frame with the given init.CalendarDate
     * @param date a valid init.CalendarDate param.
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

    private void initJumpButton(){
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
    }

    private void initSearchButton(){
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
    }
}
