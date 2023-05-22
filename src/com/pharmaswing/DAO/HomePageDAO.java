package com.pharmaswing.DAO;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.pharmaswing.model.DBConnection;

public class HomePageDAO {
	private Connection connection;

	public HomePageDAO() throws SQLException {
		connection = DBConnection.getConnection();
	}

	public void loadDataTableMenu(JTable jTable) {
		jTable.setRowHeight(35);
		try {
			String query = "SELECT name, amount, price,iddrugs FROM menu";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			DefaultTableModel model = (DefaultTableModel) jTable.getModel();
			model.setColumnIdentifiers(new Object[] { "Mã hàng","Tên hàng","Số lượng","Thành tiền" });
			model.setRowCount(0);

			while (resultSet.next()) {
				Object[] row = new Object[4];
				String name = resultSet.getString("name");
				row[0] = resultSet.getInt("iddrugs");
				row[1] = resultSet.getString("name");
				row[2] = resultSet.getInt("amount");
				row[3] = resultSet.getInt("price");
				model.addRow(row);
			}
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadDataTableDrugsList(JTable jTable,String query,String selectedDrugs) {
		jTable.setRowHeight(35);
		JTableHeader header = jTable.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 18));
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			if(selectedDrugs!=null) {
			    statement.setString(1, selectedDrugs);
			}
		    ResultSet resultSet= statement.executeQuery();
			DefaultTableModel model = (DefaultTableModel) jTable.getModel();
			model.setColumnIdentifiers(new Object[] { "Mã hàng","Tên hàng","Giá tiền"});
			model.setRowCount(0);

			while (resultSet.next()) {
				Object[] row = new Object[3];
				row[0] = resultSet.getInt("code");
				row[1] = resultSet.getString("name");
				row[2] = resultSet.getInt("price");
				model.addRow(row);
			}
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addToMenu(JTable jTable, int idDrugs, String name, String amount, String price) throws SQLException {
		boolean existed = false;
		String nameDrugs = name;
		int priceDrugs = Integer.parseInt(price);
		int amountDrusg = Integer.parseInt(amount);

		for (int i = 0; i < jTable.getRowCount(); i++) {
			// Nếu sản phẩm đã có trong Menu
			if (jTable.getValueAt(i, 0).equals(idDrugs)) {
				existed = true;
				int oldAmount = Integer.parseInt(jTable.getValueAt(i, 2).toString());
				int newAmount = oldAmount + amountDrusg;
				int oldPrice = Integer.parseInt(jTable.getValueAt(i, 3).toString());
				int newPrice = priceDrugs + oldPrice;
				// Cập nhật lại giá trị price và amount của database
				String sql = "UPDATE menu SET price = ?, amount = ? WHERE iddrugs = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, newPrice);
				statement.setInt(2, newAmount);
				statement.setInt(3, idDrugs);
				statement.executeUpdate();

				break;
			}
		}
		// Sản phẩm chưa có trong Menu => Thêm sản phẩm mới
		if (!existed) {

			String sql = ("INSERT INTO menu(name,price,amount,iddrugs) VALUES(?,?,?,?)");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nameDrugs);
			statement.setInt(2, priceDrugs);
			statement.setInt(3, amountDrusg);
			statement.setInt(4, idDrugs);
			statement.executeUpdate();

		}
	}
	public void addToCombobox(JComboBox jComboBox, String query,String selectedPharrma){
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			if(selectedPharrma!=null) {
				statement.setString(1, selectedPharrma);
			}
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				jComboBox.addItem(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
