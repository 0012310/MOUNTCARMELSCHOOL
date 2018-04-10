package gct.example.com.mountcarmelschool.model_wishinglist_data;

/**
 * Created by GCT on 1/6/2018.
 */

public class General {
    private String message;

    private String status;

    private String teacher_name;

    private String staff_id;




    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getTeacher_name ()
    {
        return teacher_name;
    }

    public void setTeacher_name (String teacher_name)
    {
        this.teacher_name = teacher_name;
    }

    public String getStaff_id ()
    {
        return staff_id;
    }

    public void setStaff_id (String staff_id)
    {
        this.staff_id = staff_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", status = "+status+", teacher_name = "+teacher_name+", staff_id = "+staff_id+"]";
    }
}
