package displaypanes;

import init.Display;
import itemcontrol.Item;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemPane extends VBox{
    private Item item;

    private Label time;
    private Label title;
    private TextArea descriptionText;

    private Button deleteButton;

    public ItemPane(){
        Display display = Display.getDisplay();

        item = null;
        time = new Label();
        title = new Label();
        deleteButton = new Button();
        title.setMaxWidth(400);
        this.deleteButton.setOnAction(event -> {
            display.getItemsController().removeItem(this.getItem());
            display.fresh();
        });

        VBox description = new VBox();
        Label desLabel = new Label();
        descriptionText = new TextArea();
        desLabel.setText("Description: ");
        description.setMaxWidth(400);
        description.setMaxHeight(120);
        description.getChildren().addAll(desLabel, descriptionText);

        init();
        this.getChildren().addAll(time, title, description, deleteButton);
    }

    public void setItem(Item item) {
        this.item = item;
        if (item == null){
            this.init();
        }else {
            this.time.setText("Time: "+ dateToString(item.getStartTime()) + "～" + dateToString(item.getEndTime()));
            this.title.setText("Title: "+ item.getTitle());
            this.descriptionText.setText(item.getDescription());
        }
    }

    public Item getItem(){
        return item;
    }

    private static String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.format(date);
    }

    private void init(){
        this.time.setText("Time: NULL");
        this.title.setText("Title: NULL");
        this.descriptionText.setText("NULL");
        this.deleteButton.setText("delete");
    }
}
