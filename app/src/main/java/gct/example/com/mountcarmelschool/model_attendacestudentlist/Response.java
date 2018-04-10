package gct.example.com.mountcarmelschool.model_attendacestudentlist;

/**
 * Created by GCT on 12/29/2017.
 */

public class Response {
    private String Standard;

    private String Section_abc;

    private List[] List;

    public String getStandard ()
    {
        return Standard;
    }

    public void setStandard (String Standard)
    {
        this.Standard = Standard;
    }

    public String getSection_abc ()
    {
        return Section_abc;
    }

    public void setSection_abc (String Section_abc)
    {
        this.Section_abc = Section_abc;
    }

    public List[] getList ()
    {
        return List;
    }

    public void setList (List[] List)
    {
        this.List = List;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Standard = "+Standard+", Section_abc = "+Section_abc+", List = "+List+"]";
    }
}

