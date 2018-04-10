package gct.example.com.mountcarmelschool.models_staff_birthday;

/**
 * Created by GCT on 11/10/2017.
 */

public class General {

    private String staff_fname;

    private String status;

    private String dob;

    private String img;

    private String staff_mob;

    private String e_mail;

    private String staff_id;

    private String staff_lname;

    private String msg;

    private String reply;

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getStaff_fname ()
    {
        return staff_fname;
    }

    public void setStaff_fname (String staff_fname)
    {
        this.staff_fname = staff_fname;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDob ()
    {
        return dob;
    }

    public void setDob (String dob)
    {
        this.dob = dob;
    }

    public String getImg ()
    {
        return img;
    }

    public void setImg (String img)
    {
        this.img = img;
    }

    public String getStaff_mob ()
    {
        return staff_mob;
    }

    public void setStaff_mob (String staff_mob)
    {
        this.staff_mob = staff_mob;
    }

    public String getE_mail ()
    {
        return e_mail;
    }

    public void setE_mail (String e_mail)
    {
        this.e_mail = e_mail;
    }

    public String getStaff_id ()
    {
        return staff_id;
    }

    public void setStaff_id (String staff_id)
    {
        this.staff_id = staff_id;
    }

    public String getStaff_lname ()
    {
        return staff_lname;
    }

    public void setStaff_lname (String staff_lname)
    {
        this.staff_lname = staff_lname;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [staff_fname = "+staff_fname+", status = "+status+", dob = "+dob+", img = "+img+", staff_mob = "+staff_mob+", e_mail = "+e_mail+", staff_id = "+staff_id+", staff_lname = "+staff_lname+", msg = "+msg+"]";
    }
}
