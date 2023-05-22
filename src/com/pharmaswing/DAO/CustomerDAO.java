package com.pharmaswing.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.pharmaswing.model.Customer;
import com.pharmaswing.model.DBConnection;

public class CustomerDAO {
	private Connection connection;
	
	public CustomerDAO() throws SQLException {
		connection = DBConnection.getConnection();
	}

	public Customer getCustomerByPhoneNumber(String phoneNumber) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer customer = null;

		try {
			String sql = "SELECT * FROM customer WHERE phonenumber = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, phoneNumber);
			rs = stmt.executeQuery();

			if (rs.next()) {
				customer = new Customer();
				customer.setPhoneNumber(rs.getString("phonenumber"));
				customer.setCustomerName(rs.getString("customername"));
				customer.setDateCustomer(rs.getDate("datecustomer"));
				customer.setPoints(rs.getInt("points"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return customer;
	}

	public void createNewCustomer(String phoneNumber, String customerName, Date dateCustomer, int points)
			throws SQLException {

		String sql = "INSERT INTO customer(phonenumber, customername, datecustomer,points ) VALUES (?, ?, ?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, phoneNumber);
		statement.setString(2, customerName);
		statement.setDate(3, dateCustomer);
		statement.setInt(4, points);

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			JOptionPane.showMessageDialog(null, "Tạo khách hàng thành công!");
		} else {
			JOptionPane.showMessageDialog(null, "Tạo khách hàng thất bại!");
		}
		Customer customer = new Customer(phoneNumber, customerName, dateCustomer, points, null);
	}

	public void updatePointsCustomer(int points, String phoneNumber) {
		try {
			String sql = "UPDATE customer SET points = points + ? WHERE phonenumber = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, points);
			statement.setString(2, phoneNumber);

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(null, "Tích điểm thành công!");
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void usingPointsCustomer(int points, String phoneNumber) throws SQLException {
		String sql = "UPDATE customer SET points = points - ? WHERE phonenumber = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, points);
		statement.setString(2, phoneNumber);
		statement.executeUpdate();
	}

	public void checkBirthdayCustomer(Date dateCustomer,String phonenumber) {
		Calendar calendarToday = Calendar.getInstance();
		Calendar calendarCustomer = Calendar.getInstance();
		calendarCustomer.setTime(dateCustomer);

		int dayToday = calendarToday.get(Calendar.DAY_OF_MONTH);
		int monthToday = calendarToday.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0 nên cộng thêm 1

		int dayCustomer = calendarCustomer.get(Calendar.DAY_OF_MONTH);
		int monthCustomer = calendarCustomer.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0 nên cộng thêm 1

		if (dayToday == dayCustomer && monthToday == monthCustomer) {
			JOptionPane.showMessageDialog(null, "Voucher tặng khách hàng nhân ngày sinh nhật: hpbd_"+phonenumber);
		}
	}
}
