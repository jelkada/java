package com.example.demo.entity;

public class Car extends Vehicle {

    int numDoors;

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public Car(String brand, String model, int year, int numDoors) {
        super(brand, model, year);
        this.numDoors = numDoors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "numDoors=" + numDoors +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
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
        return 5000 + (numDoors * 100);
    }
}
