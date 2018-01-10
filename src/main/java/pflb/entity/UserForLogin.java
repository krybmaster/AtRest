package pflb.entity;

public class UserForLogin {

    private String LOGIN;
    private String PASSMD5;

    public UserForLogin(String LOGIN, String PASSMD5) {
        this.LOGIN = LOGIN;
        this.PASSMD5 = PASSMD5;
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
}
