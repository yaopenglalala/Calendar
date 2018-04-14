package itemcontrol;

import init.CalendarDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemController {
    private ArrayList<Item> items;

    public ItemController(){
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

    public boolean addItem(Date startDate, Date endDate, String title, String description) {
        try {
            Item item = new Item(startDate, endDate, title, description);
            return addItem(item);
        }catch (Exception e){
            return false;
        }
    }

    public boolean addItem(CalendarDate date, String title, String description){
        try {
            String dateString = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
            return addItem(Item.stringToDate(dateString + " 00:00:00"),
                    Item.stringToDate(dateString + " 23:59:59"),
                    title, description);
        } catch (Exception e){
            return  false;
        }
    }

    public List<Item> searchItem(Date startDate, Date endDate){
        ArrayList<Item> results = new ArrayList<>();
        for (Item item : items) {
            if (! (item.getStartTime().after(endDate) || item.getEndTime().before(startDate))){
                results.add(item);
            }
        }
        return results;
    }

    public List<Item> searchItem(CalendarDate date){
        String dateString = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
        Date startDate = Item.stringToDate( dateString + " 00:00:00");
        Date endDate = Item.stringToDate(dateString + " 23:59:59");

        return searchItem(startDate, endDate);
    }
}
