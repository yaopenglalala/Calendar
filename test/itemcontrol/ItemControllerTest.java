package itemcontrol;

import init.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemControllerTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("Class ItemController tests begin! Good luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Class ItemController tests end! Are you satisfied?");
    }

    @Test
    public void testAddItem() throws Exception {
        ItemController itemController = new ItemController();
        assertFalse(itemController.addItem(null, null, null));
        assertFalse(itemController.addItem(DateUtil.getToday(), null, null));
        assertFalse(itemController.addItem(DateUtil.getToday(), "", null));
        assertTrue(itemController.addItem(DateUtil.getToday(), "", ""));

    }

    @Test
    public void testRemove() throws Exception {
        ItemController itemController = new ItemController();

        assertFalse(itemController.removeItem(null));

        Item testItem = new Item(Item.stringToDate("1998-11-5 22:12:15"),Item.stringToDate("1999-11-5 22:12:15"),"","" );
        assertFalse(itemController.removeItem(testItem));

        itemController.addItem(testItem);
        assertTrue(itemController.removeItem(testItem));
        assertFalse(itemController.getItems().contains(testItem));
    }
}