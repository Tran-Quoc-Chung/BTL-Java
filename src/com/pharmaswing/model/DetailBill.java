package com.pharmaswing.model;

public class DetailBill {
	   private int id;
	   private int idBill;
	   private int idDrugs;
	   private int quantity;
	   private String name;
	   private double total;

	   public DetailBill(int id, int idBill, int idDrugs, int quantity, String name, double total) {
	      this.id = id;
	      this.idBill = idBill;
	      this.idDrugs = idDrugs;
	      this.quantity = quantity;
	      this.name = name;
	      this.total = total;
	   }

	   public int getId() {
	      return id;
	   }

	   public void setId(int id) {
	      this.id = id;
	   }

	   public int getIdBill() {
	      return idBill;
	   }

	   public void setIdBill(int idBill) {
	      this.idBill = idBill;
	   }

	   public int getIdDrugs() {
	      return idDrugs;
	   }

	   public void setIdDrugs(int idDrugs) {
	      this.idDrugs = idDrugs;
	   }

	   public int getQuantity() {
	      return quantity;
	   }

	   public void setQuantity(int quantity) {
	      this.quantity = quantity;
	   }

	   public String getName() {
	      return name;
	   }

	   public void setName(String name) {
	      this.name = name;
	   }

	   public double getTotal() {
	      return total;
	   }

	   public void setTotal(double total) {
	      this.total = total;
	   }
	}
