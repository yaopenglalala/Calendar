package itemcontrol;

import exceptions.TimeIllegalException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ItemTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("Class Item tests begin! Good luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Class Item tests end! Are you satisfied?");
    }

    @Test
    public void testChangeStartTimeAndEndTime(){
        Item testItem = new Item(Item.stringToDate("1998-11-5 15:12:23"),
                Item.stringToDate("1998-11-6 15:12:23"),"","");
        boolean error = false;
        try {
            testItem.changeStartTimeAndEndTime(Item.stringToDate(""), Item.stringToDate(""));
        } catch (TimeIllegalException e){
            error = true;
        }
        assertTrue(error);

        assertFalse(testItem.changeStartTimeAndEndTime(Item.stringToDate("1998-11-6 15:12:23"),
                Item.stringToDate("1998-11-5 15:12:23")));

        assertTrue(testItem.changeStartTimeAndEndTime(Item.stringToDate("1998-11-17 15:12:23"),
                Item.stringToDate("1998-11-19 15:12:23")));

        assertTrue(Item.stringToDate("1998-11-17 15:12:23").equals(testItem.getStartTime()));
        assertTrue(Item.stringToDate("1998-11-19 15:12:23").equals(testItem.getEndTime()));
    }

    @Test
    public void testStringToDate() throws Exception {
//        String[] illegalFormat = {"fdafdsa","1999-12-32 12:12:12","1999-15-12 12:12:12"
//
//        };
        assertNull(Item.stringToDate("fdafdsa"));
        assertNull(Item.stringToDate("1999-12-32 12:12:12"));
        assertNull(Item.stringToDate("1999-15-12 12:12:12"));
        assertNull(Item.stringToDate("0-12-15 12:12:12"));
        assertNull(Item.stringToDate("1999-12-15 25:12:12"));
        assertNull(Item.stringToDate("1999-12-15 24:12:12"));
        assertNull(Item.stringToDate("1999-12-15 23:61:12"));
        assertNull(Item.stringToDate("1999-12-15 23:59:61"));


        assertNotNull(Item.stringToDate("1999-12-12 12:12:12"));

        Date time = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        assert(ft.parse(ft.format(time)).equals(Item.stringToDate(ft.format(time))));
    }


}