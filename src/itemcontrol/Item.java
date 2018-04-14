package itemcontrol;

import java.util.Date;

public class Item {
    private Date startTime;
    private Date endTime;
    private String title;
    private String description;

    public Item(Date sTime, Date eTime, String t, String d){
        setStartTime(sTime);
        setEndTime(eTime);
        setTitle(t);
        setDescription(d);
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
