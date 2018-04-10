package gct.example.com.mountcarmelschool.model_sibling_data.sibling_details_data;

/**
 * Created by GCT on 9/27/2017.
 */

public class Response {
    private Sibling[] Sibling;

    public Sibling[] getSibling ()
    {
        return Sibling;
    }

    public void setSibling (Sibling[] Sibling)
    {
        this.Sibling = Sibling;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Sibling = "+Sibling+"]";
    }
}
