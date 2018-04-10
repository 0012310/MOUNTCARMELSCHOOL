package gct.example.com.mountcarmelschool.model_attendance_data;

/**
 * Created by GCT on 12/5/2017.
 */

public class Dec {
    private String Status;

    private String att_date;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getAtt_date ()
    {
        return att_date;
    }

    public void setAtt_date (String att_date)
    {
        this.att_date = att_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", att_date = "+att_date+"]";
    }
}
