package itemcontrol;

import exceptions.ItemConstructException;
import init.CalendarDate;
import init.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemsControllerTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("Class ItemsController tests begin! Good luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Class ItemsController tests end! Are you satisfied?");
    }

    @Test
    public void testRemoveFalse(){
        ItemsController itemController = new ItemsController();
        assertFalse(itemController.removeItem(null));
        Item testItem = new Item(Item.stringToDate("1998-11-5 22:12:15"),Item.stringToDate("1999-11-5 22:12:15"),"test","" );
        assertFalse(itemController.removeItem(testItem));
    }

    @Test
    public void testRemoveTrue(){
        ItemsController itemController = new ItemsController();
        Item testItem = new Item(Item.stringToDate("1998-11-5 22:12:15"),Item.stringToDate("1999-11-5 22:12:15"),"test","" );
        itemController.addItem(testItem);
        assertTrue(itemController.removeItem(testItem));
        assertFalse(itemController.getItems().contains(testItem));
    }

    @Test
    public void testAddItemNoDateException() throws Exception{
        ItemsController itemController = new ItemsController();
        try {
            itemController.addItem(null, null, null);
        }catch (ItemConstructException e){
            assertTrue(e.getMessage().equals("No date"));
        }
    }

    @Test
    public void testAddItemNoTitleException() throws Exception{
        ItemsController itemController = new ItemsController();
        try{
            assertFalse(itemController.addItem(DateUtil.getToday(), null, null));
            assertTrue(false);
        }catch (ItemConstructException e){
            assertTrue(e.getMessage().equals("No title"));
        }

        try{
            assertFalse(itemController.addItem(DateUtil.getToday(), "", null));
            assertTrue(false);
        }catch (ItemConstructException e){
            assertTrue(e.getMessage().equals("No title"));
        }
    }

    @Test
    public void testAddItemTrue() throws Exception{
        ItemsController itemController = new ItemsController();
        assertTrue(itemController.addItem(Item.stringToDate("2018-12-12 12:12"), Item.stringToDate("2018-12-13 12:12"), "test" , null));
        assertTrue(itemController.addItem(DateUtil.getToday(), "test", null));
        assertTrue(itemController.addItem(DateUtil.getToday(), "test", ""));
    }

    @Test
    public void testSearchItemNotIn() throws Exception{
        ItemsController itemController = new ItemsController();
        itemController.addItem(DateUtil.getToday(), "test", "");

        assertTrue(itemController.searchItem(new CalendarDate(1998, 12, 12)).isEmpty());
    }

    @Test
    public void testSearchItemIn() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ItemsController itemController = new ItemsController();
        itemController.addItem(new CalendarDate(1998, 12, 13), "title0", "description0");
        itemController.addItem(Item.stringToDate("1998-12-11 11:12") ,Item.stringToDate("1998-12-13 11:12") , "title1", "description1");
        itemController.addItem(Item.stringToDate("1998-12-13 11:12") ,Item.stringToDate("1998-12-14 11:12") , "title2", "description2");
        ArrayList<Item> items = itemController.searchItem(new CalendarDate(1998, 12, 13));
        assertFalse(items.isEmpty());

        Item testItem0 = items.get(0);
        assertEquals("title0",  testItem0.getTitle());
        assertEquals("description0",  testItem0.getDescription());

        Item testItem1 = items.get(1);
        assertEquals("title1",  testItem1.getTitle());
        assertEquals("description1",  testItem1.getDescription());

        Item testItem2 = items.get(2);
        assertEquals("title2",  testItem2.getTitle());
        assertEquals("description2",  testItem2.getDescription());
    }
}