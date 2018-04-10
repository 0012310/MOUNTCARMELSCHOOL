package gct.example.com.mountcarmelschool.models.general_details_data;

/**
 * Created by GCT on 9/26/2017.
 */

public class Response {

    private General[] General;

    public General[] getGeneral() {
        return General;
    }

    public void setGeneral(General[] General) {
        this.General = General;
    }

    @Override
    public String toString() {
        return "ClassPojo [General = " + General + "]";
    }
}
