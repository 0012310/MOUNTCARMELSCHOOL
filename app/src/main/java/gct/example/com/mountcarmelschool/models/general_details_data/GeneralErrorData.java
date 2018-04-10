package gct.example.com.mountcarmelschool.models.general_details_data;

/**
 * Created by GCT on 9/26/2017.
 */

public class GeneralErrorData {

    private String error;
    private String status;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [error = " + error + ", status = " + status + "]";
    }
}
