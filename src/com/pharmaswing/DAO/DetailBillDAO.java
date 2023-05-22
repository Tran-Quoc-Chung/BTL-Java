package com.pharmaswing.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pharmaswing.model.DetailBill;

public class DetailBillDAO {
   private Connection connection;

   public DetailBillDAO(Connection connection) {
      this.connection = connection;
   }

   public void addDetailBill(DetailBill detailBill) throws SQLException {
      String sql = "INSERT INTO detailBill (idfkDetailBill, idDrugs, quantityDrugs, nameDrugs, totalDrugs) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, detailBill.getIdBill());
      statement.setInt(2, detailBill.getIdDrugs());
      statement.setInt(3, detailBill.getQuantity());
      statement.setString(4, detailBill.getName());
      statement.setDouble(5, detailBill.getTotal());
      statement.executeUpdate();
   }

   public List<DetailBill> getDetailBillsByBillId(int id) throws SQLException {
      String sql = "SELECT * FROM detailBill WHERE idfkDetailBill = ?";
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      List<DetailBill> detailBills = new ArrayList<>();
      while (resultSet.next()) {
         int detailBillId = resultSet.getInt("iddetalbill");
         int idfkDetailBill = resultSet.getInt("idfkDetailBill");
         int idDrugs = resultSet.getInt("idDrugs");
         int quantity = resultSet.getInt("quantityDrugs");
         String name = resultSet.getString("nameDrugs");
         double total = resultSet.getDouble("totalDrugs");
         DetailBill detailBill = new DetailBill(detailBillId, idfkDetailBill, idDrugs, quantity, name, total);
         detailBills.add(detailBill);
      }

      return detailBills;
   }
}
