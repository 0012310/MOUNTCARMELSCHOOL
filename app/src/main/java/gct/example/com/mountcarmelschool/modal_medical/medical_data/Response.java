package gct.example.com.mountcarmelschool.modal_medical.medical_data;

/**
 * Created by GCT on 9/26/2017.
 */

public class Response {
    private Medical[] Medical;

    public Medical[] getMedical ()
    {
        return Medical;
    }

    public void setMedical (Medical[] Medical)
    {
        this.Medical = Medical;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Medical = "+Medical+"]";
    }
}
