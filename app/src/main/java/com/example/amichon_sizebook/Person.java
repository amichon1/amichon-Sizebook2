package com.example.amichon_sizebook;

import java.io.Serializable;

/**
 * Created by Alex on 2017-02-06.
 */

public class Person implements Serializable {
    // all relative fields of a person class
    private String name;
    private String date;
    private String neckSize;
    private String bustSize;
    private String chestSize;
    private String waistSize;
    private String hipSize;
    private String inseamSize;
    private String comment;
    private int id;

    // Persons getters & setters
    public Person(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getNeckSize() {
        return neckSize;
    }
    public void setNeckSize(String neckSize) {
        this.neckSize = neckSize;
    }
    public String getBustSize() {
        return bustSize;
    }
    public void setBustSize(String bustSize) {
        this.bustSize = bustSize;
    }
    public String getChestSize() {
        return chestSize;
    }
    public void setChestSize(String chestSize) {
        this.chestSize = chestSize;
    }
    public String getWaistSize() {
        return waistSize;
    }
    public void setWaistSize(String waistSize) {
        this.waistSize = waistSize;
    }
    public String getHipSize() {
        return hipSize;
    }
    public void setHipSize(String hipSize) {
        this.hipSize = hipSize;
    }
    public String getInseamSize() {
        return inseamSize;
    }
    public void setInseamSize(String inseamSize) {
        this.inseamSize = inseamSize;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }

    //returns a nice string in info
    @Override
    public String toString() {
        return "Name: " + name +  "\n" + "Date: " + this.getDate() + "\n" + "Neck: "
                + this.getNeckSize() + "\n"+ "Bust: " + this.getBustSize() + "\n" + "Chest: "
                + this.getChestSize() + "\n" + "Waist: " + this.getWaistSize() + "\n" + "Hip: "
                + this.getHipSize() + "\n" + "Inseam: " + this.getInseamSize();
    }
}
