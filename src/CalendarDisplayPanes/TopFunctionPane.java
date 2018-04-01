package CalendarDisplayPanes;

import CalendarDateCore.CalendarDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class TopFunctionPane extends FlowPane{
    private CalendarDate currentDate;
    private Label currentDateLabel;
    private Label yearChoiceLabel;
    private TextField yearsChoiceInput;
    private Label monthChoiceLabel;
    private TextField monthsChoiceInput;
    private Button jumpButton;

    private TextField searchInput;
    private Button searchButton;

    public TopFunctionPane(CalendarDate date){
        super();

        this.currentDateLabel = new Label();
        this.currentDateLabel.getStyleClass().add("currentDateLabel");
        setCurrentDateLabel(date);

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

        this.getChildren().addAll(currentDateLabel, yearChoiceLabel, yearsChoiceInput, monthChoiceLabel, monthsChoiceInput, jumpButton, searchInput, searchButton);

        this.getStylesheets().add(getClass().getResource("/TopPaneCSS.css").toExternalForm());
        this.setHgap(15);
        this.setVgap(20);
    }

    public void setCurrentDateLabel(CalendarDate date) {
        this.currentDate = date;
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
}
