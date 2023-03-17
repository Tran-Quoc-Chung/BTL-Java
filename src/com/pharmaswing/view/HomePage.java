package com.pharmaswing.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JTable table_menu;
	private JTextField txf_totalMenu;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txf_nameDrugs;
	private JTable table_drugList;
	private JScrollPane scrollPane;
	private JFrame jFrame;
	private JTextField txf_searchDrugs;
	private JTextField txf_priceDrugs;
	private String priceDrugs;

	/**
	 * Launch the application.
	 */
	PropertiesDesign propertiesDesign = new PropertiesDesign();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

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

		JButton btnNewButton_3 = new JButton("Thanh toán");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_3.setBackground(new Color(240, 240, 240));
		btnNewButton_3.setBounds(961, 600, 143, 83);
		contentPane.add(btnNewButton_3);

		table_menu = new JTable();
		table_menu.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		Object[] coluObjects = { "a", "b", "c" };
		DefaultTableModel model = new DefaultTableModel();

		table_menu.setBackground(SystemColor.menu);
		table_menu.setForeground(Color.black);
		table_menu.setSelectionBackground(Color.red);
		table_menu.setGridColor(Color.red);
		table_menu.setSelectionForeground(Color.white);
		table_menu.setRowHeight(30);
		table_menu.setAutoCreateRowSorter(true);

		table_menu.setBounds(613, 78, 614, 512);

		contentPane.add(table_menu);

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

		JButton btnNewButton_1_1 = new JButton("Hủy hàng");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1_1.setBounds(645, 10, 143, 58);
		contentPane.add(btnNewButton_1_1);

		JButton btnNewButton_1_1_1 = new JButton("Hủy giao dịch");
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_1_1.setBounds(798, 10, 143, 59);
		contentPane.add(btnNewButton_1_1_1);

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
		JComboBox cbx_pharma = new JComboBox();
		cbx_pharma.setBounds(10, 33, 177, 41);
		contentPane.add(cbx_pharma);

		JComboBox cbx_drug = new JComboBox();
		cbx_drug.setBounds(197, 36, 216, 35);
		contentPane.add(cbx_drug);

		table_drugList = new JTable();
		table_drugList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table_drugList.setSelectionForeground(Color.WHITE);
		table_drugList.setSelectionBackground(new Color(192, 192, 192));
		table_drugList.setRowHeight(30);
		table_drugList.setGridColor(Color.LIGHT_GRAY);
		table_drugList.setForeground(Color.BLACK);
		table_drugList.setBackground(new Color(128, 255, 255));
		table_drugList.setBounds(10, 146, 560, 427);
		contentPane.add(table_drugList);

		// combobox pharma
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing?useSSL=false",
					"root", "123456");
			java.sql.Statement stmStatement = con.createStatement();
			ResultSet resultPharma = stmStatement.executeQuery("SELECT * FROM pharma");

			while (resultPharma.next()) {
				cbx_pharma.addItem(resultPharma.getString("name"));

			}
			resultPharma.close();
			stmStatement.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		cbx_pharma.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selectedPharma = cbx_pharma.getSelectedItem().toString();

					cbx_drug.removeAllItems();

					try {
						Connection conn = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1:3306/pharma_swing?useSSL=false", "root", "123456");
						// String query = "SELECT name FROM drugs WHERE pharma = ?";
						Statement stmt = conn.createStatement();

						// stmt.setString(1, selectedQuocGia);
						ResultSet rs = stmt.executeQuery(
								"SELECT * FROM drugs WHERE id_pharma IN (SELECT id FROM pharma WHERE name='"
										+ selectedPharma + "')");

						while (rs.next()) {

							cbx_drug.addItem(rs.getString("name"));
						}

						rs.close();
						stmt.close();
						conn.close();
					} catch (SQLException ex) {
						System.out.println(ex);
					}

				}
			}
		});

		// table list drugs

		DefaultTableModel model = new DefaultTableModel(new Object[] { "ID", "Name", "Price" }, 1);

		try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing?useSSL=false",
				"root", "123456");
				java.sql.Statement stmStatement = con.createStatement();
				ResultSet resultSet = stmStatement.executeQuery("SELECT id, name, price FROM drugs");

		) {
			// lấy dữ liệu thành công
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int price = resultSet.getInt("price");
				model.addRow(new Object[] { id, name, price });
				// import data to table

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table_drugList.setModel(model);
		JTableHeader header = table_drugList.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 14));
		table_drugList.getColumnModel().getColumn(0).setHeaderValue("ID");
		table_drugList.getColumnModel().getColumn(1).setHeaderValue("Name");
		table_drugList.getColumnModel().getColumn(2).setHeaderValue("Price");
		table_drugList.setTableHeader(header);

		JSpinner spin_count = new JSpinner();
		spin_count.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spin_count.setBounds(129, 666, 76, 41);
		spin_count.setModel(new SpinnerNumberModel(1, 1, null, 1));

		contentPane.add(spin_count);
		spin_count.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int count = (int) spin_count.getValue();
				String text = txf_priceDrugs.getText();
				Integer price = Integer.parseInt(priceDrugs);
				txf_priceDrugs.setText(String.valueOf(count * price));

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

		table_drugList.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int row = table_drugList.getSelectedRow();
				String nameString = table_drugList.getValueAt(row, 1).toString();
				String priceString = table_drugList.getValueAt(row, 2).toString();
				priceDrugs = table_drugList.getValueAt(row, 2).toString();
				txf_nameDrugs.setText(nameString);
				txf_priceDrugs.setText(priceString);
				spin_count.setValue(1);

			}

		});

		// table menu

		DefaultTableModel model_menu = new DefaultTableModel(new Object[] { "Name", "Amount", "Price" }, 1);

		try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing?useSSL=false",
				"root", "123456");
				java.sql.Statement stmStatement = con.createStatement();
				ResultSet resultSet = stmStatement.executeQuery("SELECT name, amount, price FROM menu");

		) {
			// lấy dữ liệu thành công
			while (resultSet.next()) {

				String name = resultSet.getString("name");
				int amount = resultSet.getInt("amount");
				int price = resultSet.getInt("price");
				model_menu.addRow(new Object[] { name, amount, price });
				// import data to table

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table_menu.setModel(model_menu);

		JTableHeader header_menu = table_menu.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 14));
		table_menu.getColumnModel().getColumn(0).setHeaderValue("ID");
		table_menu.getColumnModel().getColumn(1).setHeaderValue("Name");
		table_menu.getColumnModel().getColumn(2).setHeaderValue("Price");
		table_menu.setTableHeader(header_menu);
		totalMenu();

	}

	void addMenu(String name, String amount, String price) {
		String nameDrugs = name;
		int priceDrugs = Integer.parseInt(price);
		int amountDrusg = Integer.parseInt(amount);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing?useSSL=false", "root",
					"123456");
			// PreparedStatement stmt = conn.createStatement();
			stmt = conn.prepareStatement("INSERT INTO menu(name,price,amount) VALUES(?,?,?)");
			stmt.setString(1, nameDrugs);
			stmt.setInt(2, priceDrugs);
			stmt.setInt(3, amountDrusg);
			stmt.executeUpdate();

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

		DefaultTableModel model_menu = new DefaultTableModel(new Object[] { "Name", "Amount", "Price" }, 1);

		try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing?useSSL=false",
				"root", "123456");
				java.sql.Statement stmStatement = con.createStatement();
				ResultSet resultSet = stmStatement.executeQuery("SELECT name, amount, price FROM menu");

		) {
			// lấy dữ liệu thành công
			while (resultSet.next()) {

				String name = resultSet.getString("name");
				int amount = resultSet.getInt("amount");
				int price = resultSet.getInt("price");
				model_menu.addRow(new Object[] { name, amount, price });
				// import data to table

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table_menu.setModel(model_menu);
	}

	public void totalMenu() {
		double sum = 0.0;
		int col = 2;

		for (int row = 0; row < table_menu.getRowCount(); row++) {
			Object value = table_menu.getValueAt(row, col);
			if (value != null && value instanceof Number) {
				sum += ((Number) value).doubleValue();
			}
		}

		txf_totalMenu.setText(String.valueOf(sum));
	}
}