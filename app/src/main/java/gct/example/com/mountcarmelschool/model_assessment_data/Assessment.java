package gct.example.com.mountcarmelschool.model_assessment_data;

/**
 * Created by GCT on 10/17/2017.
 */

public class Assessment {
    private String Assessment_Record;

    public String getAssessment_Record ()
    {
        return Assessment_Record;
    }

    public void setAssessment_Record (String Assessment_Record)
    {
        this.Assessment_Record = Assessment_Record;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Assessment_Record = "+Assessment_Record+"]";
    }
}
