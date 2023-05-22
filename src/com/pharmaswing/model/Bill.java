package com.pharmaswing.model;

import java.util.Date;

import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;

public class Bill {
	private int id;
	private Date date;
	private double total;
	private String customer;
	private String cashier;
	private int voucher;
	private int points;
	private String phoneNumberCustomer;


	public Bill(int id, double total, String cashier, String customer, Date date,int voucher, int points,String phoneNumberCustomer) {
		this.id = id;
		this.date = date;
		this.total = total;
		this.customer = customer;
		this.cashier = cashier;
		this.voucher=voucher;
		this.points=points;
		this.phoneNumberCustomer=phoneNumberCustomer;
	}
	public Bill() {
		
	}
	

	public int getVoucher() {
		return voucher;
	}

	public void setVoucher(int voucher) {
		this.voucher = voucher;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
		
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	public String getPhoneNumberCustomer() {
		return phoneNumberCustomer;
	}
	public void setPhoneNumberCustomer(String phoneNumberCustomer) {
		this.phoneNumberCustomer = phoneNumberCustomer;
	}
}
