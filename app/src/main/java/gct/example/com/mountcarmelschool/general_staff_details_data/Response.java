package gct.example.com.mountcarmelschool.general_staff_details_data;

/**
 * Created by GCT on 11/8/2017.
 */

public class Response {

    private General[] General;

    private Other[] other;

    public General[] getGeneral ()
    {
        return General;
    }

    public void setGeneral (General[] General)
    {
        this.General = General;
    }

    public Other[] getOther ()
    {
        return other;
    }

    public void setOther (Other[] other)
    {
        this.other = other;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [General = "+General+", other = "+other+"]";
    }
}
