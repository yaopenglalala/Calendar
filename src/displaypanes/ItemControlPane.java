package displaypanes;

import itemcontrol.Item;
import itemcontrol.ItemsController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ItemControlPane extends VBox{
    private final int itemPanesNumber = 3;
    private int currentPageNumber;

    private ItemsController itemsController;

    private ItemPane[] itemPanes;

    private Label pageLabel;
    private Button lastPage;
    private Button nextPage;
    private Button addByDate;
    private Button addByTime;

    public ItemControlPane(ItemsController itemsControl){
        this.itemsController = itemsControl;

        itemPanes = new ItemPane[itemPanesNumber];
        for (int i = 0; i < itemPanesNumber; i ++){
            itemPanes[i] = new ItemPane();
            this.getChildren().add(itemPanes[i]);
        }

        pageLabel = new Label();
        lastPage = new Button("<-");
        nextPage = new Button("->");
        addByDate = new Button("add by date");
        addByTime = new Button("add by time");

        HBox pageInfo = new HBox();
        pageInfo.getChildren().addAll(lastPage , pageLabel, nextPage);

        HBox addItem = new HBox();
        addItem.getChildren().addAll(addByDate, addByTime);

        this.getChildren().addAll(pageInfo, addItem);
        this.init();
    }

    public void changePage(int expectPageNumber){
        int maxPage = ((itemsController.getItems().size() / itemPanesNumber) + (itemsController.getItems().size() % itemPanesNumber == 0? 0:1));
        if (expectPageNumber < 0){
            changePage(0);
        } else if (expectPageNumber >= maxPage && expectPageNumber != 0){
            changePage(maxPage - 1);
        } else {
            if (maxPage == 0){
                pageLabel.setText(0 + "/" + 0);
            } else pageLabel.setText((expectPageNumber + 1) + "/" + maxPage);
            currentPageNumber = expectPageNumber;
            for (int i = 0; i < itemPanesNumber ; i++){
                if ((expectPageNumber * itemPanesNumber + i) >= itemsController.getItems().size()){
                    itemPanes[i].setItem(null);
                } else itemPanes[i].setItem(itemsController.getItems().get(expectPageNumber * itemPanesNumber + i));
            }
        }
    }

    private void init(){
        for (ItemPane itemPane : itemPanes)
            itemPane.getDeleteButton().setOnAction(event -> {
                itemsController.removeItem(itemPane.getItem());
                changePage(0);
            });

        lastPage.setOnAction(event -> {
            int expectPageNumber = currentPageNumber - 1;
            changePage(expectPageNumber);
        });

        nextPage.setOnAction(event -> {
            int expectPageNumber = currentPageNumber + 1;
            changePage(expectPageNumber);
        });

        addByTime.setOnAction(event -> {
            new ItemAddWindow(itemsController);
            changePage(0);
        });

        changePage(0);
    }
}
