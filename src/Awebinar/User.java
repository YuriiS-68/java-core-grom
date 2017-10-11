package Awebinar;

public class User {
    private String sessionId;
    private String ipAddress;

    public User(String sessionId, String ipAddress) {
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
