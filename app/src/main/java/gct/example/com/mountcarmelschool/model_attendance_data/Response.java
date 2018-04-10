package gct.example.com.mountcarmelschool.model_attendance_data;


/**
 * Created by GCT on 12/5/2017.
 */

public class Response {

    private String Total_Present;
    private String Total_Absent;
    private Attendence attendence;
    private String Total_Holiday;

    public String getTotal_Present() {
        return Total_Present;
    }

    public void setTotal_Present(String Total_Present) {
        this.Total_Present = Total_Present;
    }

    public String getTotal_Absent() {
        return Total_Absent;
    }

    public void setTotal_Absent(String Total_Absent) {
        this.Total_Absent = Total_Absent;
    }

    public Attendence getAttendence() {
        return attendence;
    }

    public void setAttendence(Attendence attendence) {
        this.attendence = attendence;
    }

    public String getTotal_Holiday() {
        return Total_Holiday;
    }

    public void setTotal_Holiday(String Total_Holiday) {
        this.Total_Holiday = Total_Holiday;
    }

    @Override
    public String toString() {
        return "ClassPojo [Total_Present = " + Total_Present + ", Total_Absent = " + Total_Absent + ", attendence = " + attendence + ", Total_Holiday = " + Total_Holiday + "]";
    }
}
