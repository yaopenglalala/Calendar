package displaypanes;

import init.CalendarDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class TopFunctionPane extends HBox{
    private Label currentDateLabel;

    private FlowPane userInputPane;

    private Label yearChoiceLabel;
    private TextField yearsChoiceInput;
    private Label monthChoiceLabel;
    private TextField monthsChoiceInput;
    private Button jumpButton;

    private TextField searchInput;
    private Button searchButton;

    private Button resetButton;
    public TopFunctionPane(CalendarDate date){
        super();

        this.currentDateLabel = new Label();
        this.currentDateLabel.getStyleClass().add("currentDateLabel");
        setCurrentDateLabel(date);

        //user input pane
        this.userInputPane = new FlowPane();
        this.userInputPane.getStyleClass().add("userInputPane");
        this.userInputPane.setHgap(15);
        this.userInputPane.setVgap(10);

        this.yearChoiceLabel = new Label("YEAR:");
        this.yearChoiceLabel.getStyleClass().add("yearLabel");
        this.yearsChoiceInput = new TextField(date.getYear() + "");
        this.yearsChoiceInput.getStyleClass().add("yearInput");

        this.monthChoiceLabel = new Label("MONTH");
        this.monthChoiceLabel.getStyleClass().add("monthLabel");
        this.monthsChoiceInput = new TextField(date.getMonth() + "");
        this.monthsChoiceInput.getStyleClass().add("monthInput");
        this.jumpButton = new Button("view");
        this.jumpButton.getStyleClass().add("jumpButton");

        this.searchInput = new TextField();
        this.searchInput.getStyleClass().add("searchInput");
        this.searchButton = new Button("search");
        this.searchButton.getStyleClass().add("searchButton");

        this.resetButton = new Button("reset");
        this.resetButton.getStyleClass().add("resetButton");

        this.userInputPane.getChildren().addAll(yearChoiceLabel, yearsChoiceInput, monthChoiceLabel, monthsChoiceInput, jumpButton, searchInput, searchButton, resetButton);

        //form the whole top pane
        this.getChildren().addAll(currentDateLabel, userInputPane);

        this.getStylesheets().add(getClass().getResource("/TopPaneCSS.css").toString());
        this.setSpacing(70);
    }

    public void setCurrentDateLabel(CalendarDate date) {
        String currentDateString = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
        this.currentDateLabel.setText(currentDateString);
    }

    public Button getJumpButton() {
        return jumpButton;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public TextField getYearsChoiceInput() {
        return yearsChoiceInput;
    }

    public TextField getMonthsChoiceInput() {
        return monthsChoiceInput;
    }

    public TextField getSearchInput() {
        return searchInput;
    }

    public Button getResetButton() {
        return resetButton;
    }
}
