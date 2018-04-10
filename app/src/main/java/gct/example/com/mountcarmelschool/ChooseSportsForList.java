package gct.example.com.mountcarmelschool;

/**
 * Created by GCT on 2/10/2018.
 */

public class
ChooseSportsForList {
    private String img;
    private String sp_name;
    private int sp_id;

    public ChooseSportsForList(String img, int sp_id, String sp_name) {
        this.img = img;
        this.sp_name = sp_name;
        this.sp_id = sp_id;
    }

    public int getSp_id() {
        return sp_id;
    }

    public void setSp_id(int sp_id) {
        this.sp_id = sp_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSp_name() {
        return sp_name;
    }

    public void setSp_name(String sp_name) {
        this.sp_name = sp_name;
    }
}
