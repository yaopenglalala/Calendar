package itemcontrol;

import exceptions.ItemConstructException;
import init.CalendarDate;

import java.util.ArrayList;
import java.util.Date;

public class ItemsController {
    private ArrayList<Item> items;

    public ItemsController(){
        items = new ArrayList<>();
    }

    public boolean removeItem(Item item){
        return this.items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean addItem(Item item) {
        return item != null && this.items.add(item);
    }

    public boolean addItem(Date startDate, Date endDate, String title, String description) throws Exception{
        Item item = new Item(startDate, endDate, title, description);
        return addItem(item);
    }

    public boolean addItem(CalendarDate date, String title, String description) throws Exception{
        if (date == null) throw new ItemConstructException("No date");
        String dateString = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
        return addItem(Item.stringToDate(dateString + " 00:00:00"),
                    Item.stringToDate(dateString + " 23:59:59"),
                    title, description);
    }

    public ArrayList<Item> searchItem(Date startDate, Date endDate){
        ArrayList<Item> results = new ArrayList<>();
        for (Item item : items) {
            if (! (item.getStartTime().after(endDate) || item.getEndTime().before(startDate))){
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<Item> searchItem(CalendarDate date){
        String dateString = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
        Date startDate = Item.stringToDate( dateString + " 00:00:00");
        Date endDate = Item.stringToDate(dateString + " 23:59:59");

        return searchItem(startDate, endDate);
    }
}
