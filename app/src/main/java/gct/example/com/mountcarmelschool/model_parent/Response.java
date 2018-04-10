package gct.example.com.mountcarmelschool.model_parent;

/**
 * Created by GCT on 9/27/2017.
 */

public class Response {
    private Mother[] Mother;

    private Father[] Father;

    public Mother[] getMother ()
    {
        return Mother;
    }

    public void setMother (Mother[] Mother)
    {
        this.Mother = Mother;
    }

    public Father[] getFather ()
    {
        return Father;
    }

    public void setFather (Father[] Father)
    {
        this.Father = Father;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Mother = "+Mother+", Father = "+Father+"]";
    }
}
