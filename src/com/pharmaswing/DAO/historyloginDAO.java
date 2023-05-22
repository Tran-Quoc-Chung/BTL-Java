package com.pharmaswing.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.pharmaswing.model.DBConnection;
import com.pharmaswing.model.DetailBill;
import com.pharmaswing.model.HistoryLogin;
import com.pharmaswing.model.TransactionTEMP;

public class historyloginDAO {
	private Connection connection;
	
	public historyloginDAO() throws SQLException {
		connection = DBConnection.getConnection();
	}
	HistoryLogin historyLogin=  HistoryLogin.getInstance();
   public historyloginDAO(Connection connection) {
      this.connection = connection;
   }
   	public void addData( List<TransactionTEMP> transactionTEMPs) throws SQLException {
   		String sql = "INSERT INTO loginhistory(id_user, position, login_time,logout_time ) VALUES (?, ?, ?,?)";
		PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		Date  logout = new Date ();
		Timestamp logoutTime= new Timestamp(logout.getTime());
		Timestamp  loginTime= new Timestamp (historyLogin.getLogin_time().getTime());
		statement.setInt(1,historyLogin.getId_user());
		statement.setInt(2,historyLogin.getId_position());
		statement.setTimestamp(3,loginTime );
		statement.setTimestamp(4,logoutTime );
		
		 statement.executeUpdate();
		 ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			int idBill=generatedKeys.getInt(1);
			for (TransactionTEMP transactionTEMP : transactionTEMPs) {
	            
				addTransactionDetail(transactionTEMP, idBill);
				System.out.println(transactionTEMP);
	         }
			JOptionPane.showMessageDialog(null, "Đăng xuất thành công!!");
		} else {
			JOptionPane.showMessageDialog(null, "Đăng xuất bất bại!!");
		}
   	}
   	public void addTransactionDetail(TransactionTEMP transactionTEMP, int transactionid ) throws SQLException{
   	 String sql = "INSERT INTO transaction_detail ( transaction_idfk, transaction_time, id_bill, total_bill) VALUES ( ?, ?, ?, ?)";
	   PreparedStatement statement = connection.prepareStatement(sql);
	   Timestamp  time= new Timestamp (transactionTEMP.getDatetimeDate().getTime());
	   statement.setInt(1,transactionid );
	   statement.setTimestamp(2,time);
	   statement.setInt(3, transactionTEMP.getIdBill());
	   statement.setInt(4, transactionTEMP.getTotalBill());
	   statement.executeUpdate();
   	}
}
