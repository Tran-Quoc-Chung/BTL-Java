package com.pharmaswing.view;

import java.sql.*;
import java.util.*;

public class Database {
    private String url;
    private String user;
    private String password;

    public Database(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
    }

    public List<Drug> getDrugs() {
        List<Drug> drugs = new ArrayList<>();

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM drugslist";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int code = rs.getInt("code");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                Drug drug = new Drug(code, name, price);
                drugs.add(drug);
            }

            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return drugs;
    }

    public static List<Drug> searchDrugs(String keyword) {
        List<Drug> drugs = new ArrayList<>();

        try {
            Connection conn = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM drugslist WHERE code LIKE ? OR name LIKE ? OR price LIKE ?");
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int code = rs.getInt("code");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                Drug drug = new Drug(code, name, price);
                drugs.add(drug);
            }

            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return drugs;
    }
}