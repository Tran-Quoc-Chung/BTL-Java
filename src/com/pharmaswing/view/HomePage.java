package com.pharmaswing.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class HomePage extends JFrame {

	private JPanel contentPane;

	private JTextField txf_totalMenu;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txf_nameDrugs;

	private JScrollPane scrollPane;
	private JFrame jFrame;
	private JTextField txf_searchDrugs;
	private JTextField txf_priceDrugs;
	private String priceDrugs;
	private int selectedId;
	private int idDrugs;
	private int spinValue;
	private JComboBox cbx_pharma;

	/**
	 * Launch the application.
	 */
	PropertiesDesign propertiesDesign = new PropertiesDesign();

	public class ComboItem {
		private String name;
		private String value;

		public ComboItem(String name, String value) {
			this.name = name;
			this.value = value;
		}

		@Override
		public String toString() {
			return name;
		}

		public String getValue() {
			return value;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.pack();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void declaredAllDesign() {

		setTitle("HomePage-PharmaApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1251, 761);
		contentPane = new JPanel();
		contentPane.setName("");
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(propertiesDesign.getJLabel1());
		contentPane.add(propertiesDesign.getJLabel2());
		contentPane.add(propertiesDesign.getJLabel3());
		contentPane.add(propertiesDesign.getJLabel4());
		contentPane.add(propertiesDesign.getJLabel5());
		contentPane.add(propertiesDesign.getJLabel6());
		
		contentPane.add(propertiesDesign.getTableMenu());

		JButton btnNewButton_3 = new JButton("Thanh toán");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_3.setBackground(new Color(240, 240, 240));
		btnNewButton_3.setBounds(961, 600, 143, 83);
		contentPane.add(btnNewButton_3);

	

		txf_totalMenu = new JTextField();
		txf_totalMenu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txf_totalMenu.setBounds(656, 600, 142, 42);
		contentPane.add(txf_totalMenu);
		txf_totalMenu.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(656, 652, 142, 42);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setBounds(808, 636, 144, 56);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton_3_1 = new JButton("Voucher");
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnNewButton_3_1.setBounds(1114, 600, 113, 83);
		contentPane.add(btnNewButton_3_1);

		JButton btn_cancelMenu = new JButton("Hủy giao dịch");
		btn_cancelMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dialogCancle= JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy giao dịch này", "Hủy giao dịch", JOptionPane.YES_NO_CANCEL_OPTION);
				if(dialogCancle==JOptionPane.YES_OPTION) {
					try {
						Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root",
								"123456");
						String sql = "DELETE FROM menu";
						Statement statement = conn.createStatement();
						statement.executeUpdate(sql);
						Login.showDialog("Đang hủy giao dịch, vui lòng chờ...", 1500);
						refreshMenu();
						Login.showDialog("Hủy thành công", 1000);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btn_cancelMenu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_cancelMenu.setBounds(798, 10, 143, 59);
		contentPane.add(btn_cancelMenu);

		JButton btnNewButton_1_1_2 = new JButton("Refresh");
		btnNewButton_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1_1_2.setBounds(944, 10, 130, 58);
		contentPane.add(btnNewButton_1_1_2);

		JButton btnNewButton_1_1_3 = new JButton("Đăng xuất");
		btnNewButton_1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?",
						"Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {

					setVisible(false);
					Login login = new Login();
					login.setVisible(true);

				}

			}
		});
		btnNewButton_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1_1_3.setBounds(1084, 10, 143, 58);
		contentPane.add(btnNewButton_1_1_3);

		txf_nameDrugs = new JTextField();
		txf_nameDrugs.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txf_nameDrugs.setBounds(83, 614, 307, 42);
		contentPane.add(txf_nameDrugs);
		txf_nameDrugs.setColumns(10);

		txf_searchDrugs = new JTextField();
		txf_searchDrugs.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txf_searchDrugs.setText(" ");

			}

			public void focusLost(FocusEvent e) {
				txf_searchDrugs.setText("Tìm kiếm");
			}
		});
		txf_searchDrugs.setToolTipText("\r\n");
		txf_searchDrugs.setText("Tìm kiếm");
		txf_searchDrugs.setBorder(null);
		txf_searchDrugs.setFont(new Font("Tahoma", Font.ITALIC, 22));
		txf_searchDrugs.setBackground(SystemColor.control);
		txf_searchDrugs.setBounds(53, 84, 261, 26);
		contentPane.add(txf_searchDrugs);
		txf_searchDrugs.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(48, 111, 273, 2);
		contentPane.add(separator);

	}

	public HomePage() {
		declaredAllDesign();
		cbx_pharma = new JComboBox();
		cbx_pharma.setBounds(10, 33, 177, 41);
		contentPane.add(cbx_pharma);

		JComboBox cbx_drug = new JComboBox();
		cbx_drug.setBounds(197, 36, 216, 35);
		contentPane.add(cbx_drug);

		contentPane.add(propertiesDesign.getTableDrugsList());


		JScrollPane listDrugScrollPane = new JScrollPane();
		listDrugScrollPane.setBounds(15, 150, 570, 437);
		listDrugScrollPane.setViewportView(propertiesDesign.getTableDrugsList());
		contentPane.add(listDrugScrollPane);

		JScrollPane listMenuScrollPane = new JScrollPane();
		listMenuScrollPane.setBounds(613, 78, 614, 512);
		listMenuScrollPane.setViewportView(propertiesDesign.getTableMenu());
		propertiesDesign.getTableMenu().setFillsViewportHeight(true);
		contentPane.add(listMenuScrollPane);

		JButton btn_deleteDrugs = new JButton("Hủy hàng");
		btn_deleteDrugs.setEnabled(false);
		btn_deleteDrugs.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_deleteDrugs.setBounds(645, 10, 143, 58);
		contentPane.add(btn_deleteDrugs);



		 cbx_pharma.addItem("");

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM pharma");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				cbx_pharma.addItem(name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Đoạn mã bắt sự kiện khi chọn cbx_pharma
		cbx_pharma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cbx_drug.removeAllItems();
				try {

					Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root",
							"123456");
					String selectedPharma = cbx_pharma.getSelectedItem().toString();
					if (!selectedPharma.isEmpty()) {
						String query = "SELECT pharmalv2.name FROM pharmalv2 "
								+ "INNER JOIN pharma ON pharmalv2.idpk = pharma.id " + "WHERE pharma.name = ?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, selectedPharma);
						ResultSet rs = ps.executeQuery();

						while (rs.next()) {
							String name = rs.getString("name");
							cbx_drug.addItem(name);
						}
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				// ghi giá trị của tất cả trường drugs vào jtable
				getData();
			}
		});

		cbx_drug.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedDrug = cbx_drug.getSelectedItem().toString();

				try {
					if (!selectedDrug.isEmpty()) {
						Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing",
								"root", "123456");
						String query = "SELECT code, name, price FROM drugslist "
								+ "WHERE idpk = (SELECT id FROM pharmalv2 WHERE name = ?)";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, selectedDrug);
						ResultSet rs = ps.executeQuery();

						DefaultTableModel model = new DefaultTableModel() {
							public boolean isCellEditable(int row, int column) {
								return false; // không cho phép chỉnh sửa
							}
						};
						model.addColumn("Mã hàng");
						model.addColumn("Tên hàng");
						model.addColumn("Giá tiền");

						while (rs.next()) {
							Object[] row = new Object[3];
							row[0] = rs.getString("code");
							row[1] = rs.getString("name");
							row[2] = rs.getDouble("price");
							model.addRow(row);
						}

						propertiesDesign.getTableDrugsList().setModel(model);
						setWidthColumnTable(propertiesDesign.getTableDrugsList(), 50, 220, 60);

					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		// table list drugs

		DefaultTableModel model = new DefaultTableModel(new Object[] { "Mã hàng", "Tên hàng", "Giá tiền" }, 0) {
			public boolean isCellEditable(int row, int column) {
				return false; // không cho phép chỉnh sửa
			}
		};
		try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
				java.sql.Statement stmStatement = con.createStatement();
				ResultSet resultSet = stmStatement.executeQuery("SELECT code, name, price FROM drugslist");

		) {
			// lấy dữ liệu thành công
			while (resultSet.next()) {
				int id = resultSet.getInt("code");
				String name = resultSet.getString("name");
				int price = resultSet.getInt("price");
				model.addRow(new Object[] { id, name, price });
				// import data to table

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		propertiesDesign.getTableDrugsList().setModel(model);
		JTableHeader header = propertiesDesign.getTableDrugsList().getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 18));

//		

		JSpinner spin_count = new JSpinner();
		spin_count.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spin_count.setBounds(129, 666, 76, 41);
		spin_count.setModel(new SpinnerNumberModel(1, 1, null, 1));

		contentPane.add(spin_count);
		spin_count.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				spinValue = (int) spin_count.getValue();
				String text = txf_priceDrugs.getText();
				Integer price = Integer.parseInt(priceDrugs);
				txf_priceDrugs.setText(String.valueOf(spinValue * price));

			}
		});

		JButton btn_addToMenu = new JButton("Thêm");
		btn_addToMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				addMenu(txf_nameDrugs.getText(), spin_count.getValue().toString(), txf_priceDrugs.getText());
				refreshMenu();
				totalMenu();

			}
		});
		btn_addToMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_addToMenu.setBounds(400, 600, 113, 107);
		contentPane.add(btn_addToMenu);

		txf_priceDrugs = new JTextField();
		txf_priceDrugs.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txf_priceDrugs.setBounds(253, 666, 137, 41);
		contentPane.add(txf_priceDrugs);
		txf_priceDrugs.setColumns(10);

		propertiesDesign.getTableDrugsList().addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				int row = propertiesDesign.getTableDrugsList().getSelectedRow();
				String nameString = propertiesDesign.getTableDrugsList().getValueAt(row, 1).toString();
				String priceString = propertiesDesign.getTableDrugsList().getValueAt(row, 2).toString();
				idDrugs = (int) propertiesDesign.getTableDrugsList().getValueAt(row, 0);
				priceDrugs = propertiesDesign.getTableDrugsList().getValueAt(row, 2).toString();
				txf_nameDrugs.setText(nameString);
				txf_priceDrugs.setText(priceString);
				spin_count.setValue(1);

			}

		});
		setWidthColumnTable(propertiesDesign.getTableDrugsList(), 50, 220, 60);
		// table menu

		DefaultTableModel model_menu = new DefaultTableModel(
				new Object[] { "Mã hàng", "Tên hàng", "Số lượng", "Thành tiền" }, 0) {
			public boolean isCellEditable(int row, int column) {
				return false; // không cho phép chỉnh sửa
			}
		};

		try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
				java.sql.Statement stmStatement = con.createStatement();
				ResultSet resultSet = stmStatement.executeQuery("SELECT name, amount, price,iddrugs FROM menu");

		) {
			// lấy dữ liệu thành công
			while (resultSet.next()) {

				String name = resultSet.getString("name");
				int amount = resultSet.getInt("amount");
				int price = resultSet.getInt("price");
				int idDrugsMenu = resultSet.getInt("iddrugs");
				model_menu.addRow(new Object[] { idDrugsMenu, name, amount, price, });
				// import data to table

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		propertiesDesign.getTableMenu().setModel(model_menu);

		JTableHeader header_menu = propertiesDesign.getTableMenu().getTableHeader();
		header_menu.setFont(new Font("Arial", Font.BOLD, 18));
		totalMenu();

		// chọn hàng để bắt sự kiện button xóa
		propertiesDesign.getTableMenu().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// Lấy chỉ số hàng được chọn
				int selectedRow = propertiesDesign.getTableMenu().getSelectedRow();
				// Kiểm tra xem hàng đã được chọn hay chưa
				if (selectedRow != -1) {
					// Lấy ID của hàng được chọn
					int id = (int) propertiesDesign.getTableMenu().getValueAt(selectedRow, 0);
					// Lưu ID vào biến toàn cục để sử dụng ở phần khác
					selectedId = id;
					btn_deleteDrugs.setEnabled(true);
				}
			}
		});
		setWidthColumnTable(propertiesDesign.getTableMenu(), 30, 220, 30);

		btn_deleteDrugs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (selectedId != -1) {
					String deleteQuery = "DELETE FROM menu WHERE iddrugs = ?";
					try {
						Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing",
								"root", "123456");
						PreparedStatement ps = conn.prepareStatement(deleteQuery);
						ps.setInt(1, selectedId);
						int rowsDeleted = ps.executeUpdate();
						if (rowsDeleted > 0) {
							// Nếu có hàng bị xóa, hiển thị thông báo thành công
							JOptionPane.showMessageDialog(null, "Hủy thành công!");
							btn_deleteDrugs.setEnabled(false);
							// Refresh lại dữ liệu trong JTable
							refreshMenu();
						} else {
							// Nếu không có hàng nào bị xóa, hiển thị thông báo lỗi
							JOptionPane.showMessageDialog(null, "Không thể hủy hàng này!");
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				totalMenu();

			}
		});

	}

	void setWidthColumnTable(JTable table, int x1, int x2, int x3) {
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(x1);
		columnModel.getColumn(1).setPreferredWidth(x2);
		columnModel.getColumn(2).setPreferredWidth(x3);
	}

	void addMenu(String name, String amount, String price) {
		String nameDrugs = name;
		int priceDrugs = Integer.parseInt(price);
		int amountDrusg = Integer.parseInt(amount);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
			boolean existed = false;
			for (int i = 0; i < propertiesDesign.getTableMenu().getRowCount(); i++) {
				// Nếu sản phẩm đã có trong Menu
				if (propertiesDesign.getTableMenu().getValueAt(i, 0).equals(idDrugs)) {
					existed = true;
					int oldAmount = Integer.parseInt(propertiesDesign.getTableMenu().getValueAt(i, 2).toString());
					int newAmount = oldAmount + amountDrusg;
					int oldPrice = Integer.parseInt(propertiesDesign.getTableMenu().getValueAt(i, 3).toString());
					int newPrice = priceDrugs + oldPrice;
				

					// Cập nhật lại giá trị price và amount của database
					String sql = "UPDATE menu SET price = ?, amount = ? WHERE iddrugs = ?";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setInt(1, newPrice);
					statement.setInt(2, newAmount);
					statement.setInt(3, idDrugs);
					statement.executeUpdate();
					System.out.println(idDrugs);

					break;
				}
			}
			// Sản phẩm chưa có trong Menu => Thêm sản phẩm mới
			if (!existed) {

				stmt = conn.prepareStatement("INSERT INTO menu(name,price,amount,iddrugs) VALUES(?,?,?,?)");
				stmt.setString(1, nameDrugs);
				stmt.setInt(2, priceDrugs);
				stmt.setInt(3, amountDrusg);
				stmt.setInt(4, idDrugs);
				stmt.executeUpdate();

			}
			refreshMenu();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	void refreshMenu() {

		DefaultTableModel model_menu = new DefaultTableModel(
				new Object[] { "Mã hàng", "Tên hàng", "Số lượng", "Thành tiền", }, 0) {
				public boolean isCellEditable(int row, int column) {
				return false; // không cho phép chỉnh sửa
			}
		};

		try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
				java.sql.Statement stmStatement = con.createStatement();
				ResultSet resultSet = stmStatement.executeQuery("SELECT name, amount, price,iddrugs FROM menu");

		) {
			// lấy dữ liệu thành công
			while (resultSet.next()) {
			
				String name = resultSet.getString("name");
				int amount = resultSet.getInt("amount");
				int price = resultSet.getInt("price");
				int idDrugsMenu = resultSet.getInt("iddrugs");
				model_menu.addRow(new Object[] { idDrugsMenu, name, amount, price });
				// import data to table

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		propertiesDesign.getTableMenu().setModel(model_menu);
	}

	public void totalMenu() {
		int sum = 0;
		int col = 3;

		for (int row = 0; row < propertiesDesign.getTableMenu().getRowCount(); row++) {
			Object value = propertiesDesign.getTableMenu().getValueAt(row, col);
			if (value != null && value instanceof Number) {
				sum += ((Number) value).intValue();
			}
		}

		txf_totalMenu.setText(String.valueOf(sum));
	}

	public void getData() {
		String selectedPharma = cbx_pharma.getSelectedItem().toString();
		if (selectedPharma != null && !selectedPharma.isEmpty()) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root",
						"123456");
				String sql = "SELECT drugslist.code, drugslist.name, drugslist.price FROM drugslist "
						+ "INNER JOIN pharmalv2 ON drugslist.idpk = pharmalv2.id "
						+ "INNER JOIN pharma ON pharmalv2.idpk = pharma.id " + "WHERE pharma.name = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, selectedPharma);
				ResultSet rs = ps.executeQuery();
				DefaultTableModel model = (DefaultTableModel) propertiesDesign.getTableDrugsList().getModel();
				model.setRowCount(0);
				while (rs.next()) {
					int id = rs.getInt("code");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					model.addRow(new Object[] { id, name, price });
					
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

}