package pflb.entity;

public class Lesson {

    private int     ID;
    private String  Name;
    private String  LessonTask;
    private String  HomeTask;

    private int ReturnCode;
    private String ReqMessage;

    public Lesson() {
    }

    public Lesson(int ID, String name, String lessonTask, String homeTask, int returnCode, String reqMessage) {
        this.ID = ID;
        Name = name;
        LessonTask = lessonTask;
        HomeTask = homeTask;
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

    public String getLessonTask() {
        return LessonTask;
    }

    public void setLessonTask(String lessonTask) {
        LessonTask = lessonTask;
    }

    public String getHomeTask() {
        return HomeTask;
    }

    public void setHomeTask(String homeTask) {
        HomeTask = homeTask;
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
