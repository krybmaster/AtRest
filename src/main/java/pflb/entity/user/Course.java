package pflb.entity.user;

import java.util.ArrayList;

public class Course {

    private String  ID;
    private String  Name;
    private String  StartDate;
    private String  EndDate;
    private int CurrCourceLesson;

    private int ReturnCode;
    private String ReqMessage;

    public Course() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public int getCurrCourceLesson() {
        return CurrCourceLesson;
    }

    public void setCurrCourceLesson(int currCourceLesson) {
        CurrCourceLesson = currCourceLesson;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public int getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(int returnCode) {
        ReturnCode = returnCode;
    }

    public String getReqMessage() {
        return ReqMessage;
    }

    public void setReqMessage(String reqMessage) {
        ReqMessage = reqMessage;
    }
}
