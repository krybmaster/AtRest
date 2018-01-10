package pflb.entity;

public class User {

    private String ID;
    private String LOGIN;
    private String PASSMD5;
    private int ROLE;


    public User(String ID, String LOGIN, String PASSMD5, int ROLE) {
        this.ID = ID;
        this.LOGIN = LOGIN;
        this.PASSMD5 = PASSMD5;
        this.ROLE = ROLE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public String getPASSMD5() {
        return PASSMD5;
    }

    public void setPASSMD5(String PASSMD5) {
        this.PASSMD5 = PASSMD5;
    }

    public int getROLE() {
        return ROLE;
    }

    public void setROLE(int ROLE) {
        this.ROLE = ROLE;
    }
}
