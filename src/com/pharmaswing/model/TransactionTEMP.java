package com.pharmaswing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionTEMP {
private Date datetimeDate;
private int idBill;
private int totalBill;
private List<TransactionTEMP> transactionTEMPs= new ArrayList<>();
public TransactionTEMP(Date datetimeDate,int idBill,int totalBill) {
	this.datetimeDate=datetimeDate;
	this.idBill=idBill;
	this.totalBill=totalBill;
}
public Date getDatetimeDate() {
	return datetimeDate;
}
public void setDatetimeDate(Date datetimeDate) {
	this.datetimeDate = datetimeDate;
}
public int getIdBill() {
	return idBill;
}
public void setIdBill(int idBill) {
	this.idBill = idBill;
}
public int getTotalBill() {
	return totalBill;
}
public void setTotalBill(int totalBill) {
	this.totalBill = totalBill;
}
public void addTransactionList(TransactionTEMP transactionTEMP) {
	transactionTEMPs.add(transactionTEMP);
}
}
