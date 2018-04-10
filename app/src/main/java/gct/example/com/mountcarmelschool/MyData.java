package gct.example.com.mountcarmelschool;

/**
 * Created by GCT on 2/10/2018.
 */

public class MyData {
    private int id;
    private String firstname;
    private String adm_no;
    private String Standard;
    private String Section_abc ;


    public MyData(int id, String firstname, String adm_no) {
        this.id = id;
        this.firstname = firstname;
        this.adm_no = adm_no;
        this.Standard=Standard;
        this.Section_abc= Section_abc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String standard) {
        Standard = standard;
    }

    public String getSection_abc() {
        return Section_abc;
    }

    public void setSection_abc(String section_abc) {
        Section_abc = section_abc;
    }

    public String getadm_no() {
        return adm_no;
    }

    public void setadm_no(String adm_no) {
        this.adm_no = adm_no;
    }
}
