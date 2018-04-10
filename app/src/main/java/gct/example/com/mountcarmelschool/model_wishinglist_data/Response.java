package gct.example.com.mountcarmelschool.model_wishinglist_data;

/**
 * Created by GCT on 1/6/2018.
 */

public class Response {
    private General[] General;

    public General[] getGeneral ()
    {
        return General;
    }

    public void setGeneral (General[] General)
    {
        this.General = General;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [General = "+General+"]";
    }
}
