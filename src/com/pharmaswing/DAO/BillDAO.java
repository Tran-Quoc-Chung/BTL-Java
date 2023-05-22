package com.pharmaswing.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pharmaswing.model.Bill;
import com.pharmaswing.model.DetailBill;
import com.pharmaswing.model.HistoryLogin;

public class BillDAO {
   private Connection connection;
   
   public BillDAO(Connection connection) {
      this.connection = connection;
   }
   Bill BillModel= new Bill();
   public void addBill(Bill bill, List<DetailBill> detailBills) throws SQLException {
      String sql = "INSERT INTO bill (dateBill, totalBill, customer, cashier,voucher,points,phoneCustomer) VALUES (?, ?, ?, ?,?,?,?)";
      PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setTimestamp(1, new java.sql.Timestamp(bill.getDate().getTime()));
      statement.setDouble(2, bill.getTotal());
      statement.setString(3, bill.getCustomer());
      statement.setString(4, bill.getCashier());
      statement.setInt(5, bill.getVoucher());
      statement.setInt(6, bill.getPoints());
      statement.setString(7, bill.getPhoneNumberCustomer());
      statement.executeUpdate();

      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
         int idBill = generatedKeys.getInt(1);
         HistoryLogin historyLogin= HistoryLogin.getInstance();;
         historyLogin.setIdBill(idBill);
         for (DetailBill detailBill : detailBills) {
            detailBill.setIdBill(idBill);
            
            addDetailBill(detailBill);
         }
      }
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

   public void setIdBill() throws SQLException {
      String sql = "SELECT idBill FROM bill ORDER BY idbill DESC LIMIT 1 ";
      PreparedStatement statement = connection.prepareStatement(sql);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
         int id = resultSet.getInt("idBill");
         
         
      }
   }

   public List<DetailBill> getDetailBillsByBillId(int id) throws SQLException {
      String sql = "SELECT * FROM detailBill WHERE idfkDetailBill = ?";
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      List<DetailBill> detailBills = new ArrayList<>();
      while (resultSet.next()) {
         int iddetailBill = resultSet.getInt("iddetalbill");
         int idfkDetailBill = resultSet.getInt("idfkDetailBill");
         int idDrugs = resultSet.getInt("idDrugs");
         int quantity = resultSet.getInt("quantityDrugs");
         String name = resultSet.getString("nameDrugs");
         double total = resultSet.getDouble("totalDrugs");
         DetailBill detailBill = new DetailBill(iddetailBill, idfkDetailBill, idDrugs, quantity, name, total);
         detailBills.add(detailBill);
      }

      return detailBills;
   }
}
