package pflb.entity.master;

public class masterLessonWithoutTasks {

    private int     ID;
    private String  Name;
    private int     Number;

    public masterLessonWithoutTasks() {
    }

    public masterLessonWithoutTasks(int ID, String name, int number) {
        this.ID = ID;
        Name = name;
        Number = number;
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

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }
}
