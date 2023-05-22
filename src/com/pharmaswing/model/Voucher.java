package com.pharmaswing.model;

import java.util.Date;

public class Voucher {
	private int idvoucher;
	private String codevoucher;
	private Date startDate;
	private Date endDate;
	private float discount;
	private boolean active;
	
	
	public Voucher(	int idvoucher,String codevoucher,Date startDate,Date endDate, float discount, boolean active) {
		this.idvoucher= idvoucher;
		this.codevoucher=codevoucher;
		this.startDate=startDate;
		this.endDate=endDate;
		this.discount=discount;
		this.active=active;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getIdvoucher() {
		return idvoucher;
	}
	public void setIdvoucher(int idvoucher) {
		this.idvoucher = idvoucher;
	}
	public String getCodevoucher() {
		return codevoucher;
	}
	public void setCodevoucher(String codevoucher) {
		this.codevoucher = codevoucher;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}

}
