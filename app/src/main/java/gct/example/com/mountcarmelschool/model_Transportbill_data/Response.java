package gct.example.com.mountcarmelschool.model_Transportbill_data;

/**
 * Created by GCT on 4/3/2018.
 */

public class Response {
    private String Transport_Bill;

    public String getTransport_Bill ()
    {
        return Transport_Bill;
    }

    public void setTransport_Bill (String Transport_Bill)
    {
        this.Transport_Bill = Transport_Bill;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Transport_Bill = "+Transport_Bill+"]";
    }
}
