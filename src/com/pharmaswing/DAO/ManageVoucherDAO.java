package com.pharmaswing.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.pharmaswing.model.DBConnection;
import com.pharmaswing.model.Voucher;

public class ManageVoucherDAO {
	private Connection connection;
	java.sql.Date date ;

	public ManageVoucherDAO() throws SQLException {
		connection = DBConnection.getConnection();
	}

	public void loadDataTableVoucher(JTable jTable) {
		jTable.setRowHeight(35);
		try {
			String query = "SELECT * FROM voucher";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			DefaultTableModel model = (DefaultTableModel) jTable.getModel();
			model.setColumnIdentifiers(
					new Object[] { "ID voucher", "Mã voucher", "Ngày bắt đầu", "Ngày kết thúc", "Giá trị voucher","Trạng thái" });
			model.setRowCount(0);

			while (resultSet.next()) {
				Object[] row = new Object[6];
				float discount = resultSet.getFloat("discount") * 100;
				DecimalFormat decimalFormat = new DecimalFormat("#.##");
				String formattedDiscount = decimalFormat.format(discount) + "%";
				row[0] = resultSet.getInt("idvoucher");
				row[1] = resultSet.getString("codevoucher");
				row[2] = resultSet.getDate("startdate");
				row[3] = resultSet.getDate("enddate");
				row[4] = formattedDiscount;
				if(resultSet.getBoolean("active")) {
					row[5]="Hoạt động";
				}else {
					row[5]="Ngưng hoạt động";
				}
				model.addRow(row);
			}
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Voucher getVoucherById(int idvoucher) throws SQLException {
		String sql = "SELECT * FROM voucher WHERE idvoucher = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idvoucher);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			float discount = resultSet.getFloat("discount") * 100;
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			String formattedDiscount = decimalFormat.format(discount) + "%";

			String codevoucher = resultSet.getString("codevoucher");
			Date startDate = resultSet.getDate("startdate");
			Date endDate = resultSet.getDate("enddate");
			boolean active = resultSet.getBoolean("active");
			return new Voucher(idvoucher, codevoucher, startDate, endDate, discount,active);
		} else {
			return null;
		}
	}
public void addNewVoucher(String codevoucher, java.util.Date startDate, java.util.Date endDate, int discount) throws SQLException {
    java.util.Date currentDate = new java.util.Date();
    boolean isActive = (currentDate.after(startDate) && currentDate.before(endDate));
    java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
    java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
    
    double discountvalue=discount/100.0;
    String sql = "INSERT INTO voucher(codevoucher, startDate, endDate, discount, active) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, codevoucher);
    statement.setDate(2, sqlStartDate);
    statement.setDate(3, sqlEndDate);
    statement.setDouble(4, discountvalue);
    statement.setBoolean(5, isActive);

    int rowsInserted = statement.executeUpdate();
    if (rowsInserted > 0) {
        JOptionPane.showMessageDialog(null, "Thêm voucher thành công!");
    } else {
        JOptionPane.showMessageDialog(null, "Thêm voucher thất bại!");
    }
}
public void updateVoucher(int idvoucher, String codeVoucher, boolean active, java.util.Date endDate) throws SQLException {
	 boolean canUseVoucher = new java.util.Date().after(endDate);
	    if (canUseVoucher && active) {
	        JOptionPane.showMessageDialog(null, "Không thể kích hoạt voucher đã hết hạn sử dụng!");
	        return;
	    }
	String sql = "UPDATE voucher SET codevoucher = ?, active = ? WHERE idvoucher = ?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, codeVoucher);
    statement.setBoolean(2, active);
    statement.setInt(3, idvoucher);

    int rowsUpdated = statement.executeUpdate();
    if (rowsUpdated > 0) {
        JOptionPane.showMessageDialog(null, "Cập nhật voucher thành công!");
    } else {
        JOptionPane.showMessageDialog(null, "Không tìm thấy voucher để cập nhật!");
    }
}
}


