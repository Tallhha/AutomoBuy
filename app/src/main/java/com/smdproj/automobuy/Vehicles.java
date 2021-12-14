package com.smdproj.automobuy;

import android.net.Uri;

public class Vehicles {

    String Name, Company, Type, Model, Color, Cost, Total;
    Uri Img;

    public Vehicles(String name, String company, String type, String model, String color, String cost, String total, Uri img) {
        Name = name;
        Company = company;
        Type = type;
        Model = model;
        Color = color;
        Cost = cost;
        Total = total;
        Img = img;
    }

    public Uri getImg() {
        return Img;
    }

    public void setImg(Uri img) {
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
