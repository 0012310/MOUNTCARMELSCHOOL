package gct.example.com.mountcarmelschool.wise;

/**
 * Created by GCT on 12/27/2017.
 */

public class WiseModel {
    public String remarks;
    public  String teacher_name;
    public  String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public WiseModel(String remarks, String teacher_name,String date) {
        this.remarks = remarks;
        this.teacher_name = teacher_name;
        this.date=date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}
