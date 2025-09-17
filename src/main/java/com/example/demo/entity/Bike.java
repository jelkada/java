package com.example.demo.entity;

public class Bike extends Vehicle {

    boolean hasGear;

    public Bike(String brand, String model, int year, boolean hasGear) {
        super(brand, model, year);
        this.hasGear = hasGear;
    }

    @Override
    public void start() {
        System.out.println("Starting car: " + brand + " " + model);
    }

    @Override
    public void stop() {
        System.out.println("Stopping car: " + brand + " " + model);
    }

    @Override
    public double calculateServiceCost() {
        return 2000 + (hasGear ? 500 : 0);
    }
}
