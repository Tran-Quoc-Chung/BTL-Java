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
		lblNewLabel = new JLabel("Tổng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(586, 603, 69, 26);
		
		lblTrGimGi = new JLabel("Trừ giảm giá:");
		lblTrGimGi.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTrGimGi.setBounds(516, 657, 139, 26);
		
		lblNewLabel_1 = new JLabel("Tổng tiền:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(822, 600, 119, 26);
		
		lblTn = new JLabel("Tên:");
		lblTn.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTn.setBounds(120, 627, 51, 26);
		
		lblSLng = new JLabel("Số lượng:");
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblSLng.setBounds(120, 671, 107, 26);
		
		lblNewLabel_2 = new JLabel("=>");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(300, 682, 35, 13);
		
		table_menu = new JTable();
		table_menu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table_menu.setBackground(SystemColor.menu);
		table_menu.setForeground(Color.black);
		table_menu.setSelectionBackground(SystemColor.activeCaptionBorder);
		table_menu.setGridColor(SystemColor.textInactiveText);
		table_menu.setSelectionForeground(Color.white);
		table_menu.setRowHeight(35);
		table_menu.setAutoCreateRowSorter(true);
		
		table_drugList = new JTable();
		table_drugList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table_drugList.setBackground(SystemColor.menu);
		table_drugList.setForeground(Color.black);
		table_drugList.setSelectionBackground(SystemColor.activeCaptionBorder);
		table_drugList.setGridColor(SystemColor.textInactiveText);
		table_drugList.setSelectionForeground(Color.white);
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
