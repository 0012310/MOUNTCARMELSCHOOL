package gct.example.com.mountcarmelschool.classes;

/**
 * Created by GCT on 2/1/2018.
 */

public class AttentdanceFebrauryForList {

    private String att_date;
    private String present_status;
    private String holiday_status;
    private String absent_status;



    public String getAtt_date() {
        return att_date;
    }

    public String getHoliday_status() {
        return holiday_status;
    }

    public String getAbsent_status() {
        return absent_status;
    }

    public void setAbsent_status(String absent_status) {
        this.absent_status = absent_status;
    }

    public void setHoliday_status(String holiday_status) {

        this.holiday_status = holiday_status;
    }

    public void setAtt_date(String att_date) {

        this.att_date = att_date;
    }

    public String getPresent_status() {
        return present_status;
    }

    public void setPresent_status(String present_status) {
        this.present_status = present_status;
    }
}

