package org.kvlt.core.models;

public class AuthModel implements Model {

    private int id;
    private String password;
    private String registerIp;
    private String email;
    private boolean isEmailConfirmed;
    private String emailConfirmationCode;
    private long emailCodeTimestamp;
    private long lastAuth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        isEmailConfirmed = emailConfirmed;
    }

    public String getEmailConfirmationCode() {
        return emailConfirmationCode;
    }

    public void setEmailConfirmationCode(String emailConfirmationCode) {
        this.emailConfirmationCode = emailConfirmationCode;
    }

    public long getEmailCodeTimestamp() {
        return emailCodeTimestamp;
    }

    public void setEmailCodeTimestamp(long emailCodeTimestamp) {
        this.emailCodeTimestamp = emailCodeTimestamp;
    }

    public long getLastAuth() {
        return lastAuth;
    }

    public void setLastAuth(long lastAuth) {
        this.lastAuth = lastAuth;
    }
}
