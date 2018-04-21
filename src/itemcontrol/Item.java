package itemcontrol;

import exceptions.ItemConstructException;
import exceptions.TimeIllegalException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private Date startTime;
    private Date endTime;
    private String title;
    private String description;

    public Item(Date sTime, Date eTime, String t, String d){
        if (sTime.after(eTime) || sTime.equals(eTime)) throw new ItemConstructException("Start time must before end time.");
        this.startTime = sTime;
        this.endTime = eTime;
        setTitle(t);
        setDescription(d);
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.equals("")) throw new ItemConstructException("No title");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) description = "";
        this.description = description;
    }

    public static Date stringToDate(String timeString){
        Date result;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setLenient(false);
        try{
            result = simpleDateFormat.parse(timeString);
        } catch (Exception e){
            throw new TimeIllegalException("There is a wrong time expression.");
        }
        return result;
    }
}
