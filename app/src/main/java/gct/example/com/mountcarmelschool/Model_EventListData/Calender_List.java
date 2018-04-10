package gct.example.com.mountcarmelschool.Model_EventListData;

/**
 * Created by GCT on 2/13/2018.
 */

public class Calender_List {
    private String message;

    private String id;

    private String status;

    private String date;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", id = "+id+", status = "+status+", date = "+date+"]";
    }
}
