package com.pharmaswing.model;

import java.sql.Date;

public class SalesStore {
	private int idBill;
    private String customer;
    private String cashier;
    private Date dateBill;
	private int totalBill;
	public SalesStore(int idBill, String customer, String cashier,Date dateBill,int totalBill) {
        this.idBill = idBill;
        this.customer = customer;
        this.cashier = cashier;
        this.dateBill=dateBill;
        this.totalBill=totalBill;
        
    }
	public int getIdBill() {
		return idBill;
	}
	public void setIdBill(int idBill) {
		this.idBill = idBill;
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
	public Date getDateBillDate() {
		return dateBill;
	}
	public void setDateBillDate(Date dateBillDate) {
		this.dateBill = dateBill;
	}
	public int getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(int totalBill) {
		this.totalBill = totalBill;
	}

}
