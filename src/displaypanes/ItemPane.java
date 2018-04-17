package displaypanes;

import itemcontrol.Item;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemPane extends VBox{
    private Item item;

    private Label time;
    private Label title;
    private Label description;

    private Button deleteButton;

    public ItemPane(){
        item = null;
        time = new Label();
        title = new Label();
        description = new Label();
        deleteButton = new Button("delete");
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
            this.description.setText("Description: "+ item.getDescription());
        }
    }

    public Item getItem(){
        return item;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    private static String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.format(date);
    }

    private void init(){
        this.time.setText("Time: NULL");
        this.title.setText("Title: NULL");
        this.description.setText("Description: NULL");
    }
}
