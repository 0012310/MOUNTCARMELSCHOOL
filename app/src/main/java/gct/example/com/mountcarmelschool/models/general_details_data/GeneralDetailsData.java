package gct.example.com.mountcarmelschool.models.general_details_data;

/**
 * Created by GCT on 9/26/2017.
 */

public class GeneralDetailsData {

    private Response response;

    private String status;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [response = " + response + ", status = " + status + "]";
    }
}
