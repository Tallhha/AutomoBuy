package com.smdproj.automobuy;

public class Store_Feedback {

    String id, fname, lname, description;

    public Store_Feedback(String id, String fname, String lname, String description) {
        this.fname = fname;
        this.lname = lname;
        this.description = description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
