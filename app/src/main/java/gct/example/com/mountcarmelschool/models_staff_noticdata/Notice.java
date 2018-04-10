package gct.example.com.mountcarmelschool.models_staff_noticdata;

/**
 * Created by GCT on 11/10/2017.
 */

public class Notice {
    private String title;

    private String notice_content;

    private String attachment_name;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getNotice_content ()
    {
        return notice_content;
    }

    public void setNotice_content (String notice_content)
    {
        this.notice_content = notice_content;
    }

    public String getAttachment_name ()
    {
        return attachment_name;
    }

    public void setAttachment_name (String attachment_name)
    {
        this.attachment_name = attachment_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", notice_content = "+notice_content+", attachment_name = "+attachment_name+"]";
    }



}
