package com.pharmaswing.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.pharmaswing.model.DBConnection;

public class SalesStoreDAO {
	private Connection connection;
    
	 
	 public SalesStoreDAO() throws SQLException {
	        // Kết nối đến CSDL MySQL sử dụng DBConnection
	        connection = DBConnection.getConnection();
	    }
	 public void loadTableData(java.util.Date startDate, java.util.Date endDate, JTable table) throws SQLException {
		    table.setRowHeight(35);
		    String query;
		    PreparedStatement statement = null;
		    ResultSet resultSet = null;
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    model.setColumnIdentifiers(new Object[]{"ID hóa đơn", "Thời gian thanh toán", "Khách hàng","Trừ voucher","Trừ tích điểm", "Tổng hóa đơn","Thu ngân"});
		    model.setRowCount(0);
		    try {
		        if (startDate == null || endDate == null) {
		            query = "SELECT * FROM bill";
		            statement = connection.prepareStatement(query);
		        } else {
		            query = "SELECT * FROM bill WHERE datebill BETWEEN ? AND ?";
		            statement = connection.prepareStatement(query);
		            statement.setDate(1, new java.sql.Date(startDate.getTime()));
		            statement.setDate(2, new java.sql.Date(endDate.getTime()));
		        }
		        resultSet = statement.executeQuery();

		        while (resultSet.next()) {
		            Object[] row = new Object[7];
		            row[0] = resultSet.getInt("idbill");
		            row[1] = resultSet.getTimestamp("dateBill");
		            row[2] = resultSet.getString("customer");
		            row[3] = resultSet.getInt("voucher");
		            row[4] = resultSet.getInt("points");
		            row[5] = resultSet.getInt("totalbill");
		            row[6] = resultSet.getString("cashier");
		            model.addRow(row);
		        }
		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage());
		    } }

   public void close() throws SQLException {
       connection.close();
   }
   public int totalSales(java.util.Date startDate, java.util.Date endDate) throws SQLException {
	    int total = 0;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    if (startDate.after(endDate) || startDate.equals(endDate)) {
	        JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải trước ngày kết thúc!");
	        return total;
	    }
	    try {
	        String query = "SELECT SUM(totalbill) AS total_revenue FROM bill WHERE datebill BETWEEN ? AND ?";
	        statement = connection.prepareStatement(query);
	        statement.setDate(1, new java.sql.Date(startDate.getTime()));
	        statement.setDate(2, new java.sql.Date(endDate.getTime()));
	        resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            total = resultSet.getInt("total_revenue");
	        }else {
	        	JOptionPane.showMessageDialog(null, "Không có hóa đơn nào trong khoảng thời gian này! ");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage());
	    }
	    return total;
	}

}
