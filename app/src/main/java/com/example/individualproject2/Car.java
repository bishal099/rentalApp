package com.example.individualproject2;

import java.io.Serializable;

public class Car implements Serializable {
    private int imageResource;
    private String name;
    private double price;
    private String bodyType;
    private String engineType;
    private int engineCC;
    private double kmRun;

    public Car(int imageResource, String name, double price, String bodyType, String engineType, int engineCC, double kmRun) {
        this.imageResource = imageResource;
        this.name = name;
        this.price = price;
        this.bodyType = bodyType;
        this.engineType = engineType;
        this.engineCC = engineCC;
        this.kmRun = kmRun;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getEngineType() {
        return engineType;
    }

    public int getEngineCC() {
        return engineCC;
    }

    public double getKmRun() {
        return kmRun;
    }
}
