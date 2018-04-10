package gct.example.com.mountcarmelschool.Model_EventListData;

/**
 * Created by GCT on 2/13/2018.
 */

public class EventDetails {
    private Response response;

    private String success;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", success = "+success+"]";
    }
}
