	package com.pharmaswing.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuDAO {
	private Connection connection;

	public MenuDAO(Connection connection) {
		this.connection = connection;
	}

	public void deleteAllMenu() throws SQLException {
		String sql = "DELETE FROM menu";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
	}
}
