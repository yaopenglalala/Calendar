package itemcontrol;

import java.util.ArrayList;

public class ItemList {
    ArrayList<Item> items;

    public ItemList(){
        items = new ArrayList<Item>();
    }

    public boolean add(Item item) {
        return item != null && this.items.add(item);
    }

    public boolean remove(Item item){
        return this.items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }


}
