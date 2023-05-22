package com.pharmaswing.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.pharmaswing.DAO.SalesStoreDAO;
import com.pharmaswing.model.Database;
import com.toedter.calendar.JDateChooser;

public class SalesStore extends JFrame {

	private JPanel contentPane;
	private JTextField txf_totalSales;
	private JTable table;
	private JTextField txf_search;
	private static Bill currentBill = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesStore frame = new SalesStore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalesStore() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\history2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 568);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(40, 68, 859, 322);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = table.rowAtPoint(e.getPoint());
		        String id = table.getValueAt(row, 0).toString();
		        String dateTimeString =table.getValueAt(row, 1).toString();
		        String customer =table.getValueAt(row, 2).toString();
		        String voucher =table.getValueAt(row, 3).toString();
		        String points =table.getValueAt(row, 4).toString();
		        String totalBill =table.getValueAt(row, 5).toString();
		        String cashier =table.getValueAt(row, 6).toString();
		        String totalMenu=String.valueOf(Integer.parseInt(totalBill)+Integer.parseInt(voucher)+Integer.parseInt(points));
		        
		        if (row >= 0) {
		            // Nếu currentBill không null, đóng currentBill trước khi tạo và hiển thị bill mới
		            if (currentBill != null) {
		                currentBill.dispose();
		            }
		            // Tạo và hiển thị bill mới
		            Bill bill = new Bill();
		            bill.setLocation(1071, 0);
		            bill.loadDataBill(id);
		            bill.fillBill(id, customer, cashier, dateTimeString, totalMenu, voucher, points, totalBill);
		            bill.setVisible(true);
		            currentBill = bill;
		        }
		       
		        
			}
		});
		table.setBackground(new Color(255, 255, 255));
		table.setEnabled(false);
		table.setEditingRow(0);
		table.setEditingColumn(0);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
		JDateChooser date_startDate = new JDateChooser();
		date_startDate.setBounds(199, 420, 197, 36);
		contentPane.add(date_startDate);
		
		JDateChooser date_endDate = new JDateChooser();
		date_endDate.setBounds(199, 466, 197, 36);
		contentPane.add(date_endDate);
		
		Label label = new Label("Từ ngày:");
		label.setFont(new Font("Dialog", Font.PLAIN, 17));
		label.setBounds(93, 420, 100, 28);
		contentPane.add(label);
		
		Label label_1 = new Label("Đến:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 17));
		label_1.setBounds(93, 474, 85, 28);
		contentPane.add(label_1);
		
		Label label_2 = new Label("Tổng doanh thu:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 17));
		label_2.setBounds(536, 447, 148, 28);
		contentPane.add(label_2);
		
		txf_totalSales = new JTextField();
		txf_totalSales.setEditable(false);
		txf_totalSales.setBackground(new Color(255, 250, 250));
		txf_totalSales.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txf_totalSales.setBounds(690, 440, 140, 36);
		contentPane.add(txf_totalSales);
		txf_totalSales.setColumns(10);
		
		JButton btn_salesStore = new JButton("Duyệt");
		btn_salesStore.setBackground(new Color(255, 250, 240));
		btn_salesStore.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_salesStore.setBounds(414, 434, 116, 49);
		btn_salesStore.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		contentPane.add(btn_salesStore);
		
		txf_search = new JTextField();
		txf_search.setBorder(null);
		txf_search.setBackground(new Color(255, 250, 250));
		txf_search.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txf_search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String keyword=txf_search.getText();
				List<com.pharmaswing.model.SalesStore> salesStores=Database.searchSalesStores(keyword);
				updateTable(salesStores);
			}
		});
		txf_search.setBounds(668, 27, 231, 25);
		contentPane.add(txf_search);
		txf_search.setColumns(10);
		
		Label label_3 = new Label("Tìm kiếm giao dịch:");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 19));
		label_3.setBounds(479, 27, 218, 28);
		contentPane.add(label_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(671, 58, 218, 4);
		contentPane.add(separator);
		
		JButton btnNewButton_2 = new JButton("<<Quay lại");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HomePage homePage= new HomePage();
					homePage.setLocation(90, 50);
					homePage.setVisible(true);
					dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBackground(new Color(255, 250, 240));
		btnNewButton_2.setBounds(0, 0, 140, 32);
		contentPane.add(btnNewButton_2);
		showDataTable();
		
		btn_salesStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date startDate = date_startDate.getDate();
				java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
				java.util.Date endDate = date_endDate.getDate();
				java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
				try {
					SalesStoreDAO salesStoreDAO = new SalesStoreDAO();
					int total = salesStoreDAO.totalSales(sqlStartDate, sqlEndDate);
					txf_totalSales.setText(String.valueOf(total));
					salesStoreDAO.loadTableData(sqlStartDate,sqlEndDate,table);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
				
			}
		});
	}
	public void showDataTable() {
		try {
			SalesStoreDAO salesStoreDAO = new SalesStoreDAO();
			salesStoreDAO.loadTableData(null,null,table);
		} catch (Exception e) { 
			System.out.println(e);
		}
	}
	private void updateTable(List<com.pharmaswing.model.SalesStore> salesStores) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (com.pharmaswing.model.SalesStore salesStore : salesStores) {
			model.addRow(new Object[] {salesStore.getIdBill(),salesStore.getDateBillDate(),salesStore.getTotalBill(),salesStore.getCustomer(),salesStore.getCashier() });
		}
	}
}
