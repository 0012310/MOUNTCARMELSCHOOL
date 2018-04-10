package gct.example.com.mountcarmelschool.Model_EventListData;

/**
 * Created by GCT on 2/13/2018.
 */

public class Response {
    private Calender_List[] Calender_List;

    public Calender_List[] getCalender_List ()
    {
        return Calender_List;
    }

    public void setCalender_List (Calender_List[] Calender_List)
    {
        this.Calender_List = Calender_List;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Calender_List = "+Calender_List+"]";
    }
}
