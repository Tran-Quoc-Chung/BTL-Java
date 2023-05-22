package com.pharmaswing.view;
import javax.swing.*;
import javax.swing.table.TableColumnModel;

import com.mysql.cj.xdevapi.Table;

import java.awt.*;

public class PropertiesDesign {
	
	private JLabel lblNewLabel;
	private JLabel lblTrGimGi;
	private JLabel lblNewLabel_1;
	private JLabel lblTn;
	private JLabel lblSLng;
	private JLabel lblNewLabel_2;
	private JTable table_menu;
	private JTable table_drugList;
    private double voucher;

    
	public double getVoucher() {
		return voucher;
	}

	public void setVoucher(double voucher) {
		this.voucher = voucher;
	}
	
	public PropertiesDesign() {

		table_menu = new JTable();
		table_menu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table_menu.setForeground(Color.black);
		table_menu.setSelectionBackground(new Color(240, 240, 240));
		
		table_menu.setSelectionForeground(Color.black);
		table_menu.setRowHeight(35);
		table_menu.setAutoCreateRowSorter(true);
		
		table_drugList = new JTable();
		table_drugList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table_drugList.setForeground(Color.black);
		table_drugList.setSelectionBackground(new Color(240, 240, 240));
		
		table_drugList.setSelectionForeground(Color.black);
		table_drugList.setRowHeight(35);
		table_drugList.setAutoCreateRowSorter(true);

	}
	
	public JLabel getJLabel1() {
	return lblNewLabel;
	}
	
	public JLabel getJLabel2() {
		return lblTrGimGi;
		}
	
	public JLabel getJLabel3() {
		return lblNewLabel_1;
		}
	
	public JLabel getJLabel4() {
		return lblTn;
		}
	
	public JLabel getJLabel5() {
		return lblSLng;
		}
	
	public JLabel getJLabel6() {
		return lblNewLabel_2;
		}
	public JTable getTableMenu() {
		return table_menu;
	}
	
	public JTable getTableDrugsList() {
		return table_drugList;
	}
	
}
