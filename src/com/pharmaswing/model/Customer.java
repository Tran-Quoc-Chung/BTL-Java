package com.pharmaswing.model;

import java.sql.Date;
import java.time.LocalDate;

public class Customer {
    private String phoneNumber;
    private String customerName;
    private Date dateCustomer;
    private int points;
    private String historyBought;
    
    public Customer(String phoneNumber, String customerName, Date dateCustomer, int points, String historyBought) {
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.dateCustomer = dateCustomer;
        this.points = points;
    }
    public Customer() {
    	
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public Date getDateCustomer() {
        return dateCustomer;
    }
    
    public void setDateCustomer(Date dateCustomer) {
        this.dateCustomer = dateCustomer;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
}

