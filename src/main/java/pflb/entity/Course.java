package pflb.entity;

public class Course {

    private int     ID;
    private String  Name;
    private String  StartDate;
    private String  EndDate;

    private int ReturnCode;
    private String ReqMessage;

    public Course() {
    }

    public Course(int ID, String name, String startDate, String endDate, int returnCode, String reqMessage) {
        this.ID = ID;
        Name = name;
        StartDate = startDate;
        EndDate = endDate;
        ReturnCode = returnCode;
        ReqMessage = reqMessage;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
