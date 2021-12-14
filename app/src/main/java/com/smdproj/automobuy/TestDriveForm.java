package com.smdproj.automobuy;

public class TestDriveForm {

    String Vehicle, FirstName, LastName, Email, PhoneNo, Cnic, date, time, Outlet;

    public TestDriveForm(String vehicle, String firstName, String lastName, String email, String phoneNo, String cnic, String date, String time, String outlet) {
        Vehicle = vehicle;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhoneNo = phoneNo;
        Cnic = cnic;
        this.date = date;
        this.time = time;
        Outlet = outlet;
    }

    public String getVehicle() {
        return Vehicle;
    }

    public void setVehicle(String vehicle) {
        Vehicle = vehicle;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getCnic() {
        return Cnic;
    }

    public void setCnic(String cnic) {
        Cnic = cnic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOutlet() {
        return Outlet;
    }

    public void setOutlet(String outlet) {
        Outlet = outlet;
    }
}
