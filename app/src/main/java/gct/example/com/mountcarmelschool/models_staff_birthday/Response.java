package gct.example.com.mountcarmelschool.models_staff_birthday;

/**
 * Created by GCT on 11/10/2017.
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
