package gct.example.com.mountcarmelschool.models_staff_noticdata;

/**
 * Created by GCT on 11/10/2017.
 */

public class Response {
    private Notice[] Notice;

    public Notice[] getNotice ()
    {
        return Notice;
    }

    public void setNotice (Notice[] Notice)
    {
        this.Notice = Notice;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Notice = "+Notice+"]";
    }
}
