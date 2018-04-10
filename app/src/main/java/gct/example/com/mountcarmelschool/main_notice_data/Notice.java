package gct.example.com.mountcarmelschool.main_notice_data;

/**
 * Created by GCT on 10/6/2017.
 */

public class Notice {

    private String title;

    private String startdate;

    private String notice_type;

    private String notice_content;

    private String attachment_name;

    private String enddate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getAttachment_name() {
        return attachment_name;
    }

    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "ClassPojo [title = " + title + ", startdate = " + startdate + ", notice_type = " + notice_type + ", notice_content = " + notice_content + ", attachment_name = " + attachment_name + ", enddate = " + enddate + "]";
    }
}
