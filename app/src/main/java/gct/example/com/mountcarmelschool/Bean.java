package gct.example.com.mountcarmelschool;

/**
 * Created by GCT on 11/17/2017.
 */

public class Bean {
    public String att_date;
    public String status;

    public Bean(String att_date, String status) {
        this.att_date = att_date;
        this.status=status;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bean(String att_date) {
        this.att_date = att_date;
    }



    public String getAtt_date() {
        return att_date;
    }

    public void setAtt_date(String att_date) {
        this.att_date = att_date;
    }

}
