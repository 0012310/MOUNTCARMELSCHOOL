package gct.example.com.mountcarmelschool.model_assessment_data;

/**
 * Created by GCT on 10/14/2017.
 */

public class Response {

    private Assessment[] Assessment;

    public Assessment[] getAssessment ()
    {
        return Assessment;
    }

    public void setAssessment (Assessment[] Assessment)
    {
        this.Assessment = Assessment;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Assessment = "+Assessment+"]";
    }
}
