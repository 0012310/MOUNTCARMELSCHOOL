package gct.example.com.mountcarmelschool.model.login_data;

/**
 * Created by GCT on 9/19/2017.
 */

public class Response {

    private String timestamp;

    private String tokengoogle;

    private String id;

    private String email;

    private String name;

    private String img;

    private String device_id;

    private String type;

    private String section;

    private String schoolcode;

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getTokengoogle ()
    {
        return tokengoogle;
    }

    public void setTokengoogle (String tokengoogle)
    {
        this.tokengoogle = tokengoogle;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getImg ()
    {
        return img;
    }

    public void setImg (String img)
    {
        this.img = img;
    }

    public String getDevice_id ()
    {
        return device_id;
    }

    public void setDevice_id (String device_id)
    {
        this.device_id = device_id;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getSection ()
    {
        return section;
    }

    public void setSection (String section)
    {
        this.section = section;
    }

    public String getSchoolcode ()
    {
        return schoolcode;
    }

    public void setSchoolcode (String schoolcode)
    {
        this.schoolcode = schoolcode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [timestamp = "+timestamp+", tokengoogle = "+tokengoogle+", id = "+id+", email = "+email+", name = "+name+", img = "+img+", device_id = "+device_id+", type = "+type+", section = "+section+", schoolcode = "+schoolcode+"]";
    }
}
