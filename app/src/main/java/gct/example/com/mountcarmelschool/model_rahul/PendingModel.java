package gct.example.com.mountcarmelschool.model_rahul;

/**
 * Created by GCT on 12/17/2017.
 */

public class PendingModel {

    public String   id;
    public String  name;
    public String  date;
    public String  description;
    public String  entry_by;
    public String  timestamp;
    public String  classdetail;
    public String  section_id;
    public String  event_image;

    public PendingModel(String id, String name, String date, String description, String entry_by, String timestamp, String classdetail, String section_id, String event_image) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.entry_by = entry_by;
        this.timestamp = timestamp;
        this.classdetail = classdetail;
        this.section_id = section_id;
        this.event_image = event_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntry_by() {
        return entry_by;
    }

    public void setEntry_by(String entry_by) {
        this.entry_by = entry_by;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getClassdetail() {
        return classdetail;
    }

    public void setClassdetail(String classdetail) {
        this.classdetail = classdetail;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getEvent_image() {
        return event_image;
    }

    public void setEvent_image(String event_image) {
        this.event_image = event_image;
    }
}
