package com.smdproj.automobuy;

public class IP {
    public String DB_IP;
    public String onli_ip;

    public IP() {
        this.DB_IP = "http://192.168.1.7/automobuy/";
        this.onli_ip = "192.168.1.7";
    }

    public String getOnli_ip() {
        return onli_ip;
    }

    public void setOnli_ip(String onli_ip) {
        this.onli_ip = onli_ip;
    }

    public String getDB_IP() {
        return DB_IP;
    }

    public void setDB_IP(String DB_IP) {
        this.DB_IP = DB_IP;
    }
}
