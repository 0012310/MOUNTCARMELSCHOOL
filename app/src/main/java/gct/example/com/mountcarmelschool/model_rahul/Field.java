package gct.example.com.mountcarmelschool.model_rahul;

/**
 * Created by GCT on 12/21/2017.
 */

public class Field {
    public String imgid;
    public String image;
    public String description;

    public Field(String imgid, String image, String description) {
        this.imgid = imgid;
        this.image = image;
        this.description = description;
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
