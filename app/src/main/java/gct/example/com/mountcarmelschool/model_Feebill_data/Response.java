package gct.example.com.mountcarmelschool.model_Feebill_data;

/**
 * Created by GCT on 12/19/2017.
 */

public class Response {
    private String Fee_Bill;

    public String getFee_Bill ()
    {
        return Fee_Bill;
    }

    public void setFee_Bill (String Fee_Bill)
    {
        this.Fee_Bill = Fee_Bill;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Fee_Bill = "+Fee_Bill+"]";
    }
}
