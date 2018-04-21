package itemcontrol;

import exceptions.ItemConstructException;
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
    public void testSetTitleNullOrEmpty(){
        Item testItem = new Item(Item.stringToDate("1998-11-5 22:12:15"),Item.stringToDate("1999-11-5 22:12:15"),"test","" );
        try{
            testItem.setTitle(null);
        } catch (ItemConstructException e){
            assertTrue(e.getMessage().equals("No title"));
        }

        try{
            testItem.setTitle("");
        } catch (ItemConstructException e){
            assertTrue(e.getMessage().equals("No title"));
        }
    }

    @Test
    public void testSetTitleNotNull(){
        Item testItem = new Item(Item.stringToDate("1998-11-5 22:12:15"),Item.stringToDate("1999-11-5 22:12:15"),"fdsaf","" );
        boolean error = false;
        try{
            testItem.setTitle("dfafs");
        } catch (ItemConstructException e){
            error = true;
        }
        assertFalse(error);
    }

    @Test
    public void testSetDescriptionNull(){
        Item testItem = new Item(Item.stringToDate("1998-11-5 22:12:15"),Item.stringToDate("1999-11-5 22:12:15"),"test","" );
        boolean error = false;
        try{
            testItem.setDescription(null);
        } catch (Exception e){
            error = true;
        }
        assertFalse(error);
    }

    @Test
    public void testSetDescriptionNotNull(){
        Item testItem = new Item(Item.stringToDate("1998-11-5 22:12:15"),Item.stringToDate("1999-11-5 22:12:15"),"test","" );
        boolean error = false;
        try{
            testItem.setDescription("");
        } catch (ItemConstructException e){
            error = true;
        }
        assertFalse(error);
    }

    @Test
    public void testStringToDateNotFormatted(){
        boolean error;
        String[] illegalFormats = {"fdafdsa","1999-12-32 12:12","1999-15-12 12:12","0-12-15 12:12",
                "1999-12-15 25:12", "1999-12-15 24:12", "1999-12-15 23:61", "1998-2-29 12:12"
        };
        for (String illegalFormat : illegalFormats){
            try{
                error = false;
                Item.stringToDate(illegalFormat);
            } catch (TimeIllegalException e){
                error = true;
            }
            assertTrue(error);
        }
    }

    @Test
    public void testStringToDateFormatted(){
        assertNotNull(Item.stringToDate("1999-12-12 12:12"));

        Date time = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm");
        try{
            assert(ft.parse(ft.format(time)).equals(Item.stringToDate(ft.format(time))));
        } catch (Exception e){
            throw new AssertionError();
        }
    }
}