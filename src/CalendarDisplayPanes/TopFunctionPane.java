package CalendarDisplayPanes;

import CalendarDateCore.CalendarDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TopFunctionPane extends  HBox{
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
        setCurrentDateLabel(date);

        this.yearChoiceLabel = new Label("YEAR:");
        this.yearsChoiceInput = new TextField(date.getYear() + "");
        this.monthChoiceLabel = new Label("MONTH");
        this.monthsChoiceInput = new TextField(date.getMonth() + "");
        this.jumpButton = new Button("view");

        this.searchInput = new TextField();
        this.searchButton = new Button("search");

        this.getChildren().addAll(currentDateLabel, yearChoiceLabel, yearsChoiceInput, monthChoiceLabel, monthsChoiceInput, jumpButton, searchInput, searchButton);
    }

    public void setCurrentDateLabel(CalendarDate date) {
        this.currentDate = date;
        String currentDateString = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
        this.currentDateLabel.setText(currentDateString);
    }
}
