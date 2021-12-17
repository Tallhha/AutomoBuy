package com.smdproj.automobuy;

import android.net.Uri;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Vehicles implements Serializable {

    String id, Name, Company, Type, Model, Color, Cost, Total;
    //Uri Img;
    String Img;

    public Vehicles(String id, String name, String company, String type, String model, String cost, String color, String total, String img) {
        Name = name;
        Company = company;
        Type = type;
        Model = model;
        Color = color;
        Cost = cost;
        Total = total;
        Img = img;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
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
