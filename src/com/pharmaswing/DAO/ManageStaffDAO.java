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
import com.pharmaswing.model.Staff;

public class ManageStaffDAO {
		private Connection connection;
	    
		 public ManageStaffDAO() throws SQLException {
		        connection = DBConnection.getConnection();
		    }
	    public void loadTableData(JTable table) {
	    	table.setRowHeight(35);
	    	try {
	            String query = "SELECT * FROM account";
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID nhân viên", "Tên", "Chức vụ", "Trạng thái"});
		        model.setRowCount(0);

		        while (resultSet.next()) {
		            Object[] row = new Object[4];
		            row[0] = resultSet.getInt("idstaff");
		            row[1] = resultSet.getString("namestaff");
		            row[2] = resultSet.getString("position");
		            if(resultSet.getBoolean("active")==true) {
		            	row[3] = "Hoạt động";
		            }else {
		            	row[3]="Ngưng hoạt động";
		            }
		            model.addRow(row);
		           
		        }
		        resultSet.close();
		        statement.close();
			} catch (Exception e) {
				System.out.println(e);
			}
	
	    }
	    
	    public void close() throws SQLException {
	        connection.close();
	    }
	    public Staff getStaffById(int id) throws SQLException {
	    	
	        String sql = "SELECT * FROM account WHERE idstaff = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            String name = resultSet.getString("namestaff");
	            String position = resultSet.getString("position");
	            String username = resultSet.getString("username");
	            String password = resultSet.getString("password");
	            boolean active = resultSet.getBoolean("active");
	            return new Staff( id,  name,  position,  username,  password, active);
	        } else {
	            return null;
	        }
	    }

	    public void updateStaff(int id, String name, String position, String username, String password, boolean active) throws SQLException {
	        String sql = "UPDATE account SET namestaff=?, position=?, username=?, password=?,active=? WHERE idstaff=?";

	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, name);
	        statement.setString(2, position);
	        statement.setString(3, username);
	        statement.setString(4, password);
	        statement.setBoolean(5, active);
	        statement.setInt(6, id);

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	        	JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công!");
	        }else {
	        	JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thất bại!");
	        }
	    }
	    public void addNewUser(String name, String position, String username, String password) throws SQLException {
	        String sql = "INSERT INTO account(namestaff, position, username, password) VALUES (?, ?, ?, ?)";

	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, name);
	        statement.setString(2, position);
	        statement.setString(3, username);
	        statement.setString(4, password);

	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	        	JOptionPane.showMessageDialog(null, "Tạo mới người dùng thành công!");
	        }else {
	        	JOptionPane.showMessageDialog(null, "Tạo mới người dùng thất bại!");
	        }
	    }


}


