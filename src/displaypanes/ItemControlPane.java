package displaypanes;

import exceptions.TimeIllegalException;
import init.CalendarDate;
import init.DateUtil;
import init.Display;
import itemcontrol.Item;
import itemcontrol.ItemsController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Date;


public class ItemControlPane extends VBox{
    private final int itemPanesNumber = 3;
    private int currentPageNumber;

    private ItemsController itemsController;
    private ArrayList<Item> searchResult;

    //search part
    private TextField startTime;
    private TextField endTime;
    private Button timeSearchButton;

    private TextField date;
    private Button dateSearchButton;


    //Item info part
    private ItemPane[] itemPanes;

    //Add item part
    private Label pageLabel;
    private Button lastPage;
    private Button nextPage;
    private Button addByDate;
    private Button addByTime;

    public ItemControlPane(ItemsController itemsControl){
        this.itemsController = itemsControl;
        this.searchResult = itemsController.getItems();
        /*
        search part
         */
        //start time
        HBox startTimeBox = new HBox();
        Label startTimeLabel = new Label("Start time: ");
        startTime = new TextField();
        startTimeBox.getChildren().addAll(startTimeLabel, startTime);
        //end time
        HBox endTimeBox = new HBox();
        Label endTimeLabel = new Label("End   time: ");
        endTime = new TextField();
        endTimeBox.getChildren().addAll(endTimeLabel, endTime);

        timeSearchButton = new Button("search by time");

        //date
        date = new TextField();
        date.setMaxWidth(400);
        dateSearchButton = new Button("search by date");

        this.getChildren().addAll(startTimeBox,endTimeBox, timeSearchButton, date, dateSearchButton);
        //item info part
        itemPanes = new ItemPane[itemPanesNumber];
        for (int i = 0; i < itemPanesNumber; i ++){
            itemPanes[i] = new ItemPane();
            this.getChildren().add(itemPanes[i]);
        }

        //add item part
        pageLabel = new Label();
        lastPage = new Button("<-");
        nextPage = new Button("->");
        addByDate = new Button("add item by date");
        addByTime = new Button("add item by time");

        HBox pageInfo = new HBox();
        pageInfo.setSpacing(150);
        pageInfo.getChildren().addAll(lastPage , pageLabel, nextPage);

        HBox addItem = new HBox();
        addItem.setSpacing(120);
        addItem.getChildren().addAll(addByDate, addByTime);

        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.getChildren().addAll(pageInfo, addItem);
        this.initSearch();
        this.initAddItem();
    }

    public void changePage(int expectPageNumber){
        int maxPage = ((searchResult.size() / itemPanesNumber) + (searchResult.size() % itemPanesNumber == 0? 0:1));
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
                if ((expectPageNumber * itemPanesNumber + i) >= searchResult.size()){
                    itemPanes[i].setItem(null);
                } else itemPanes[i].setItem(searchResult.get(expectPageNumber * itemPanesNumber + i));
            }
        }
    }

    private void initSearch(){
        timeSearchButton.setOnAction(event -> {
            String sTimeString = startTime.getText();
            String eTimeString = endTime.getText();
            try {
                Date sTime = Item.stringToDate(sTimeString);
                Date eTime = Item.stringToDate(eTimeString);
                searchResult = itemsController.searchItem(sTime, eTime);
                changePage(0);
            } catch (TimeIllegalException e){
                new ErrorAlert(e.getMessage()).showAndWait();
            }
        });

        dateSearchButton.setOnAction(event -> {
            String dateString = date.getText();
            if (!DateUtil.isFormatted(dateString)){
                new ErrorAlert(1).showAndWait();
            } else {
                CalendarDate tmpDate = new CalendarDate(dateString);
                if (!DateUtil.isValid(tmpDate)) new ErrorAlert(2).showAndWait();
                else {
                    searchItemByDate(tmpDate);
                }
            }
        });
    }

    public void searchItemByDate(CalendarDate date) {
        searchResult = itemsController.searchItem(date);
        changePage(0);
    }

    private void initAddItem(){
        lastPage.setOnAction(event -> {
            int expectPageNumber = currentPageNumber - 1;
            changePage(expectPageNumber);
        });

        nextPage.setOnAction(event -> {
            int expectPageNumber = currentPageNumber + 1;
            changePage(expectPageNumber);
        });

        addByDate.setOnAction(event -> {
            new ItemAddWindow(Display.getDisplay().getCurrentDate(), itemsController);
            searchResult = itemsController.searchItem(Display.getDisplay().getCurrentDate());
            changePage(0);
        });

        addByTime.setOnAction(event -> {
            new ItemAddWindow(itemsController);
            searchResult = itemsController.getItems();
            changePage(0);
        });

        changePage(0);
    }
}
