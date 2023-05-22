package com.pharmaswing.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.pharmaswing.model.DBConnection;

public class ManageDrugsDAO {
	private Connection connection;
    
	 
	 public ManageDrugsDAO() throws SQLException {
	        connection = DBConnection.getConnection();
	    }
	  public void loadTableData(JTable table) {
	    	table.setRowHeight(35);
	    	try {
	            String query = "SELECT * FROM account";
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID nhân viên", "Tên", "Chức vụ", "Hoạt động"});
		        model.setRowCount(0);

		        while (resultSet.next()) {
		            Object[] row = new Object[4];
		            row[0] = resultSet.getInt("idstaff");
		            row[1] = resultSet.getString("namestaff");
		            row[2] = resultSet.getString("position");
		            row[3] = resultSet.getBoolean("active");
		            model.addRow(row);
		           
		        }
		        resultSet.close();
		        statement.close();
			} catch (Exception e) {
				System.out.println(e);
			}
	
	    }
	 public void addNewPharma(JTable jTable, String query,boolean selected,String name,int id) throws SQLException{
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, name);
			stmt.setBoolean(2, selected);
			stmt.setBoolean(3, selected);
			stmt.setBoolean(4, selected);
			stmt.setInt(5, id);

	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	        	JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công!");
	        }else {
	        	
	        }JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thất bại!");
	    }
	 
}
