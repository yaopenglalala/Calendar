package displaypanes;

import exceptions.TimeIllegalException;
import init.CalendarDate;
import itemcontrol.Item;
import itemcontrol.ItemsController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Date;


public class ItemAddWindow {
    private TextField sTimeInput;
    private TextField eTimeInput;
    private TextField titleInput;
    private TextField desInput;
    private Button add;
    private Button cancel;


    public ItemAddWindow(CalendarDate date, ItemsController items){
        Stage window = new Stage();
        window.setTitle("Item add");
        window.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(init(1));
        add.setOnAction(event -> {
            String titleString = titleInput.getText();
            String desString = desInput.getText();
            if (!items.addItem(date, titleString, desString)) new ErrorAlert("Title and description can not be empty").showAndWait();
            else window.close();
        });
        cancel.setOnAction(event -> window.close());

        window.setScene(scene);
        window.showAndWait();
    }

    public ItemAddWindow(ItemsController items){
        Stage window = new Stage();
        window.setTitle("Item add");
        window.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(init(2));
        add.setOnAction(event -> {
            String sTimeString = sTimeInput.getText();
            String eTimeString = eTimeInput.getText();
            String titleString = titleInput.getText();
            String desString = desInput.getText();
            try {
                Date sTime = Item.stringToDate(sTimeString);
                Date eTime = Item.stringToDate(eTimeString);
                if (!items.addItem(sTime, eTime, titleString, desString)) new ErrorAlert("Title and description can not be empty").showAndWait();
                else window.close();
            } catch (TimeIllegalException e){
                new ErrorAlert(e.getMessage()).showAndWait();
            }
        });
        cancel.setOnAction(event -> window.close());

        window.setScene(scene);
        window.showAndWait();
    }


    private VBox init(int way){
        VBox frame = new VBox();

        //start time
        HBox sTimeBox = new HBox();
        Label sTimeLabel = new Label("Start time: ");
        sTimeInput = new TextField();
        sTimeBox.getChildren().addAll(sTimeLabel, sTimeInput);

        //end Time
        HBox eTimeBox = new HBox();
        Label eTimeLabel = new Label("End time: ");
        eTimeInput = new TextField();
        eTimeBox.getChildren().addAll(eTimeLabel, eTimeInput);

        //title
        HBox titleBox = new HBox();
        Label titleLabel = new Label("Title: ");
        titleInput = new TextField();
        titleBox.getChildren().addAll(titleLabel, titleInput);

        //description
        HBox desBox = new HBox();
        Label desLabel = new Label("Description: ");
        desInput = new TextField();
        desBox.getChildren().addAll(desLabel, desInput);

        //buttons
        HBox buttonsBox = new HBox();
        add = new Button("add");
        cancel = new Button("cancel");
        buttonsBox.getChildren().addAll(add, cancel);

        if (way == 1){
            frame.getChildren().addAll(titleBox, desBox);
        } else if (way == 2){
            frame.getChildren().addAll(sTimeBox, eTimeBox, titleBox, desBox, buttonsBox);
        }

        return frame;
    }
}
