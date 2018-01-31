package pflb.entity.master;

public class masterCourse {

    private int     ID;
    private String  Name;
    private String  StartDate;
    private String  EndDate;
    private int     LessonNumber;

    public masterCourse() {
    }

    public masterCourse(int ID, String name, String startDate, String endDate, int lessonNumber) {
        this.ID = ID;
        Name = name;
        StartDate = startDate;
        EndDate = endDate;
        LessonNumber = lessonNumber;
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

    public int getLessonNumber() {
        return LessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        LessonNumber = lessonNumber;
    }
}
