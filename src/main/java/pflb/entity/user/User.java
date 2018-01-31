package pflb.entity.user;

public class User {

    private String Login;
    private String PassMD5;
    private String Name;
    private String LastName;
    private String MiddleName;
    private int Role;
    private String SessionID;
    private String ImgURL;
    private int CurseID;

    private int ReturnCode;
    private String ReqMessage;

    public User() {
    }

    public User(String login, String passMD5, String name, String lastName, String middleName, int role, String sessionID, String imgURL, int curseID, int returnCode, String reqMessage) {
        Login = login;
        PassMD5 = passMD5;
        Name = name;
        LastName = lastName;
        MiddleName = middleName;
        Role = role;
        SessionID = sessionID;
        ImgURL = imgURL;
        CurseID = curseID;
        ReturnCode = returnCode;
        ReqMessage = reqMessage;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassMD5() {
        return PassMD5;
    }

    public void setPassMD5(String passMD5) {
        PassMD5 = passMD5;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }

    public int getCurseID() {
        return CurseID;
    }

    public void setCurseID(int curseID) {
        CurseID = curseID;
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
