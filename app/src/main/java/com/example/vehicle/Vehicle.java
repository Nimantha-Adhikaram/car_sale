package com.example.vehicle;

public class Vehicle {
    private String adDate;
    private String city;
    private String contact;
    private String fuelType;
    private String make;
    private String model;
    private String modelYear;
    private String name;
    private String price;
    private String transmission;
    private int imageResource;

    // Default constructor
    public Vehicle() {}

    // Parameterized constructor
    public Vehicle(String adDate, String city, String contact, String fuelType, String make,
                   String model, String modelYear, String name, String price,
                   String transmission, int imageResource) {
        this.adDate = adDate;
        this.city = city;
        this.contact = contact;
        this.fuelType = fuelType;
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
        this.name = name;
        this.price = price;
        this.transmission = transmission;
        this.imageResource = imageResource;
    }

    // Getters and Setters
    public String getAdDate() {
        return adDate;
    }

    public void setAdDate(String adDate) {
        this.adDate = adDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
