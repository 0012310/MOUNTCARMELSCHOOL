package gct.example.com.mountcarmelschool.main_notice_data;

/**
 * Created by GCT on 10/6/2017.
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
