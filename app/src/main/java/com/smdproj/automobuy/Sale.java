package com.smdproj.automobuy;

public class Sale {
    //sales
    String id, dlr_email, cust_email, dlr_name, cust_name, vehicle, model, cost, buying_option;

    public Sale(String id, String dlr_email, String cust_email, String dlr_name, String cust_name, String vehicle, String model, String cost, String buying_option) {
        this.dlr_email = dlr_email;
        this.cust_email = cust_email;
        this.dlr_name = dlr_name;
        this.cust_name = cust_name;
        this.vehicle = vehicle;
        this.model = model;
        this.cost = cost;
        this.buying_option = buying_option;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDlr_email() {
        return dlr_email;
    }

    public void setDlr_email(String dlr_email) {
        this.dlr_email = dlr_email;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getDlr_name() {
        return dlr_name;
    }

    public void setDlr_name(String dlr_name) {
        this.dlr_name = dlr_name;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
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

    public String getBuying_option() {
        return buying_option;
    }

    public void setBuying_option(String buying_option) {
        this.buying_option = buying_option;
    }
}
