package gct.example.com.mountcarmelschool.main_wise_data;

/**
 * Created by GCT on 10/4/2017.
 */

public class Response {
    private INTELLECT[] INTELLECT;

    private STATURE[] STATURE;

    private EMOTION[] EMOTION;

    private WORTHY[] WORTHY;

    public INTELLECT[] getINTELLECT ()
    {
        return INTELLECT;
    }

    public void setINTELLECT (INTELLECT[] INTELLECT)
    {
        this.INTELLECT = INTELLECT;
    }

    public STATURE[] getSTATURE ()
    {
        return STATURE;
    }

    public void setSTATURE (STATURE[] STATURE)
    {
        this.STATURE = STATURE;
    }

    public EMOTION[] getEMOTION ()
    {
        return EMOTION;
    }

    public void setEMOTION (EMOTION[] EMOTION)
    {
        this.EMOTION = EMOTION;
    }

    public WORTHY[] getWORTHY ()
    {
        return WORTHY;
    }

    public void setWORTHY (WORTHY[] WORTHY)
    {
        this.WORTHY = WORTHY;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [INTELLECT = "+INTELLECT+", STATURE = "+STATURE+", EMOTION = "+EMOTION+", WORTHY = "+WORTHY+"]";
    }
}
