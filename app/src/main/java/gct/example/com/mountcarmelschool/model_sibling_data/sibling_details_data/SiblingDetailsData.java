package gct.example.com.mountcarmelschool.model_sibling_data.sibling_details_data;

/**
 * Created by GCT on 9/27/2017.
 */

public class SiblingDetailsData {
    private Response response;

    private String status;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", status = "+status+"]";
    }
}
