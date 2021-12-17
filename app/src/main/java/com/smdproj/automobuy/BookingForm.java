package com.smdproj.automobuy;

public class BookingForm {
    String id, Vehicle, FirstName, LastName, Email, PhoneNo, Cnic, BuyingOption;
    String type, model, cost, color;

    public BookingForm(String id, String vehicle, String firstName, String lastName, String email, String phoneNo, String cnic, String buyingOption, String type, String model, String cost, String color) {
        this.id = id;
        Vehicle = vehicle;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhoneNo = phoneNo;
        Cnic = cnic;
        BuyingOption = buyingOption;
        this.type = type;
        this.model = model;
        this.cost = cost;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getBuyingOption() {
        return BuyingOption;
    }

    public void setBuyingOption(String buyingOption) {
        BuyingOption = buyingOption;
    }
}
