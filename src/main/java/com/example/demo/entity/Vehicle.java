package com.example.demo.entity;

public abstract class Vehicle {

    protected String brand;
    protected String model;
    protected int year;

    public abstract void start();
    public abstract void stop();
    public abstract double calculateServiceCost();

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }

    public void getDetails() {
        System.out.println("Details:" + brand + " " + model + " (" + year + ")");
    }
}
