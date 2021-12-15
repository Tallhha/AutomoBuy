package com.smdproj.automobuy;

public class TestDriveForm {

    String id, Vehicle, FirstName, LastName, Email, PhoneNo, Cnic, date, Outlet;

    public TestDriveForm(String id, String vehicle, String firstName, String lastName, String email, String phoneNo, String cnic, String date, String outlet) {
        Vehicle = vehicle;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhoneNo = phoneNo;
        Cnic = cnic;
        this.date = date;
        Outlet = outlet;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOutlet() {
        return Outlet;
    }

    public void setOutlet(String outlet) {
        Outlet = outlet;
    }
}
