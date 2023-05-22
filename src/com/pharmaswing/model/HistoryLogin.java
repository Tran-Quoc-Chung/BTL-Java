package com.pharmaswing.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class HistoryLogin {
	private static HistoryLogin instance =null;


	private int id_user;
	private int id_position;
	private java.util.Date login_time;
	private java.util.Date logout_time;
	private int transactions;
	private int idBill;

	private List<Integer> listIdBill = new ArrayList<>();
	
	private   HistoryLogin() {}
	public static HistoryLogin getInstance() {
		if(instance==null) {
			instance= new HistoryLogin();
		}
		return instance;
	}
	
	public void setTransaction(int idbill) {
		listIdBill.add(idbill);
	}

	public void setFromLogin(int id_user, int id_position, java.util.Date login_time) {
		this.id_user=id_user;
		this.id_position=id_position;
		this.login_time=login_time;
	}

	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_position() {
		return id_position;
	}
	public void setId_position(int id_position) {
		this.id_position = id_position;
	}
	public java.util.Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public java.util.Date getLogout_time() {
		return logout_time;
	}
	public void setLogout_time(Date logout_time) {
		this.logout_time = logout_time;
	}
	
	public int getIdBill() {
		return idBill;
	}

	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}




	
	
}
