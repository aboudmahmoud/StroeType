package com.example.stroetype.Moudle;

public class MouldeCar {
    private  int id ;
    private  String Name;
    private String Color;
    private double dpl;

    public MouldeCar(String name, String color, double dpl) {
        Name = name;
        Color = color;
        this.dpl = dpl;
    }

    public MouldeCar(int id, String name, String color, double dpl) {
        this.id = id;
        Name = name;
        Color = color;
        this.dpl = dpl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public double getDpl() {
        return dpl;
    }

    public void setDpl(double dpl) {
        this.dpl = dpl;
    }
}
