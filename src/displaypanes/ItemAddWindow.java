package displaypanes;

import exceptions.TimeIllegalException;
import init.CalendarDate;
import init.Display;
import itemcontrol.Item;
import itemcontrol.ItemsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.ws.Dispatch;
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
            try {
                items.addItem(date, titleString, desString);
                Display.getDisplay().fresh();
                window.close();
            }catch (Exception e){
                new ErrorAlert(e.getMessage()).showAndWait();
            }
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
                items.addItem(sTime, eTime, titleString, desString);
                Display.getDisplay().fresh();
                window.close();
            } catch (Exception e){
                new ErrorAlert(e.getMessage()).showAndWait();
            }
        });
        cancel.setOnAction(event -> window.close());

        window.setScene(scene);
        window.showAndWait();
    }

    private GridPane init(int way){
        GridPane frame = new GridPane();
        frame.setAlignment(Pos.CENTER);
        frame.setPadding(new Insets(5, 10, 10 , 10));
        frame.setVgap(10);
        frame.setHgap(8);

        //start time
        Label sTimeLabel = new Label("Start time: ");
        sTimeInput = new TextField();

        //end Time
        Label eTimeLabel = new Label("End time: ");
        eTimeInput = new TextField();

        //title
        Label titleLabel = new Label("Title: ");
        titleInput = new TextField();

        //description
        Label desLabel = new Label("Description: ");
        desInput = new TextField();

        //buttons
        add = new Button("add");
        cancel = new Button("cancel");

        if (way == 2){
            frame.add(sTimeLabel, 0, 0);
            frame.add(sTimeInput, 1, 0);
            frame.add(eTimeLabel, 0, 1);
            frame.add(eTimeInput, 1,1);
        }

        frame.add(titleLabel, 0, 2);
        frame.add(titleInput, 1, 2);
        frame.add(desLabel, 0, 3);
        frame.add(desInput, 1,3);
        frame.add(add, 0, 4);
        frame.add(cancel, 1, 4);

        return frame;
    }
}
