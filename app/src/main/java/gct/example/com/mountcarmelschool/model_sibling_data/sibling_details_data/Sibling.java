package gct.example.com.mountcarmelschool.model_sibling_data.sibling_details_data;

/**
 * Created by GCT on 9/27/2017.
 */

public class Sibling {

    private String timestamp;
    private String id;
    private String sbranch2;
    private String sadm_no1;
    private String sadm_no2;
    private String sbranch1;
    private String sbranch;
    private String adm_no;
    private String sname1;
    private String sadm_no;
    private String sname;
    private String sname2;

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getSbranch2 ()
    {
        return sbranch2;
    }

    public void setSbranch2 (String sbranch2)
    {
        this.sbranch2 = sbranch2;
    }

    public String getSadm_no1 ()
    {
        return sadm_no1;
    }

    public void setSadm_no1 (String sadm_no1)
    {
        this.sadm_no1 = sadm_no1;
    }

    public String getSadm_no2 ()
    {
        return sadm_no2;
    }

    public void setSadm_no2 (String sadm_no2)
    {
        this.sadm_no2 = sadm_no2;
    }

    public String getSbranch1 ()
    {
        return sbranch1;
    }

    public void setSbranch1 (String sbranch1)
    {
        this.sbranch1 = sbranch1;
    }

    public String getSbranch ()
    {
        return sbranch;
    }

    public void setSbranch (String sbranch)
    {
        this.sbranch = sbranch;
    }

    public String getAdm_no ()
    {
        return adm_no;
    }

    public void setAdm_no (String adm_no)
    {
        this.adm_no = adm_no;
    }

    public String getSname1 ()
    {
        return sname1;
    }

    public void setSname1 (String sname1)
    {
        this.sname1 = sname1;
    }

    public String getSadm_no ()
    {
        return sadm_no;
    }

    public void setSadm_no (String sadm_no)
    {
        this.sadm_no = sadm_no;
    }

    public String getSname ()
    {
        return sname;
    }

    public void setSname (String sname)
    {
        this.sname = sname;
    }

    public String getSname2 ()
    {
        return sname2;
    }

    public void setSname2 (String sname2)
    {
        this.sname2 = sname2;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [timestamp = "+timestamp+", id = "+id+", sbranch2 = "+sbranch2+", sadm_no1 = "+sadm_no1+", sadm_no2 = "+sadm_no2+", sbranch1 = "+sbranch1+", sbranch = "+sbranch+", adm_no = "+adm_no+", sname1 = "+sname1+", sadm_no = "+sadm_no+", sname = "+sname+", sname2 = "+sname2+"]";
    }
}
