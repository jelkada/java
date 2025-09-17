package com.example.demo.entity;

public class Truck extends Vehicle {
    int capacity;

    public Truck(String brand, String model, int year, int capacity) {
        super(brand, model, year);
        this.capacity = capacity;
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
        return 8000 + (capacity * 200);
    }
}
