package com.smdproj.automobuy;

public class LogInfo {

    String pw, email;
    int isManager, isDealer;

    public LogInfo(String pw, String email, int isManager, int isDealer) {
        this.pw = pw;
        this.email = email;
        this.isManager = isManager;
        this.isDealer = isDealer;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public int getIsDealer() {
        return isDealer;
    }

    public void setIsDealer(int isDealer) {
        this.isDealer = isDealer;
    }

}
