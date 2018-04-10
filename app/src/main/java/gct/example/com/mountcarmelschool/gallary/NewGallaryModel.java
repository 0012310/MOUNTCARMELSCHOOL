package gct.example.com.mountcarmelschool.gallary;

/**
 * Created by GCT on 1/31/2018.
 */

public class NewGallaryModel {
    public String Month;
    public String ImageURl;
    public String Month_no;

    public NewGallaryModel(String month, String imageURl, String month_no) {
        Month = month;
        ImageURl = imageURl;
        Month_no = month_no;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getImageURl() {
        return ImageURl;
    }

    public void setImageURl(String imageURl) {
        ImageURl = imageURl;
    }

    public String getMonth_no() {
        return Month_no;
    }

    public void setMonth_no(String month_no) {
        Month_no = month_no;
    }

    public NewGallaryModel(String month, String imageURl) {
        Month = month;
        ImageURl = imageURl;


    }
}
