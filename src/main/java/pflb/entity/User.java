package pflb.entity;

public class User {

    private String login;
    private String passMD5;
    private int role;
    private String sessionID;
    private int ReturnCode;
    private String ReqMessage;

    public User() {
    }

    public User(String login, String passMD5, int role, String sessionID, int returnCode, String reqMessage) {
        this.login = login;
        this.passMD5 = passMD5;
        this.role = role;
        this.sessionID = sessionID;
        ReturnCode = returnCode;
        ReqMessage = reqMessage;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassMD5() {
        return passMD5;
    }

    public void setPassMD5(String passMD5) {
        this.passMD5 = passMD5;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
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
