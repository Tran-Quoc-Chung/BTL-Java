package com.pharmaswing.model;

public class Drug {
    private int code;
    private String name;
    private int price;


	public Drug(int code, String name, int price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return code + " - " + name + " - " + price;
    }
}