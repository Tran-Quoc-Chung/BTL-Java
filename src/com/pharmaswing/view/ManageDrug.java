package com.pharmaswing.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;

public class ManageDrug extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table_list;
	private JTextField txf_pharma_id;
	private JTextField txf_pharma_name;
	Boolean createPharmaBoolean = false;
	Boolean createPharmaLv2Boolean = false;
	Boolean createDrugsBoolean = false;
	private JTextField txf_pharma_id_lv2;
	private JTextField txf_pharma_name_lv2;
	private JTextField txf_pharma_idpk;
	private JTextField txf_name_drugs;
	private JTextField txf_price_drugs;
	private JTextField txf_code_drugs;
	private JTextField txf_id_pharmalv2_drugs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageDrug frame = new ManageDrug();
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
	public ManageDrug() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\logoApp.png"));
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 724);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 240, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 372, 1002, 315);
		contentPane.add(scrollPane);

		table_list = new JTable();

		table_list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(table_list);
		table_list.setFillsViewportHeight(true);
		table_list.setRowHeight(45);

		JPanel parentPanel = new JPanel();
		parentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		parentPanel.setBackground(new Color(250, 240, 230));
		parentPanel.setBounds(255, 10, 771, 301);
		contentPane.add(parentPanel);
		parentPanel.setLayout(new CardLayout(0, 0));

		JPanel panel_pharma = new JPanel();
		panel_pharma.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_pharma.setBackground(new Color(250, 240, 230));
		parentPanel.add(panel_pharma, "name_7681531788900");
		panel_pharma.setLayout(null);

		txf_pharma_id = new JTextField();
		txf_pharma_id.setBackground(new Color(250, 240, 230));
		txf_pharma_id.setEditable(false);
		txf_pharma_id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txf_pharma_id.setBounds(119, 76, 228, 41);
		panel_pharma.add(txf_pharma_id);
		txf_pharma_id.setColumns(10);

		txf_pharma_name = new JTextField();
		txf_pharma_name.setBackground(new Color(250, 240, 230));
		txf_pharma_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txf_pharma_name.setColumns(10);
		txf_pharma_name.setBounds(120, 152, 227, 41);
		panel_pharma.add(txf_pharma_name);

		JCheckBox chb_checkVisible = new JCheckBox("Ẩn dữ liệu");
		chb_checkVisible.setBackground(new Color(250, 240, 230));
		chb_checkVisible.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chb_checkVisible.setBounds(508, 84, 161, 33);
		panel_pharma.add(chb_checkVisible);

		JButton btn_pharma = new JButton("Cập nhật ");
		btn_pharma.setBackground(new Color(250, 240, 230));
		btn_pharma.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		btn_pharma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = chb_checkVisible.isSelected();

				if (createPharmaBoolean == false) {
					try {
						Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing",
								"root", "123456");

						String sql = "UPDATE pharma " + "INNER JOIN pharmalv2 ON pharma.id=pharmalv2.idpk "
								+ "INNER JOIN drugslist ON pharmalv2.id=drugslist.idpk"
								+ " SET pharma.name=?,pharma.invisible=?, pharmalv2.invisible=?,drugslist.invisible=? WHERE pharma.id=?";
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setString(1, txf_pharma_name.getText());
						stmt.setBoolean(2, selected);
						stmt.setBoolean(3, selected);
						stmt.setBoolean(4, selected);
						stmt.setInt(5, Integer.parseInt(txf_pharma_id.getText()));
						int rowUpdate = stmt.executeUpdate();
						if (rowUpdate > 0) {
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
							String[] columnNames = new String[] { "id", "name", "invisible" };
							String[] headers = new String[] { "Mã loại", "Tên loại", "Trạng thái" };
							refreshTable("id,Name,invisible", "pharma", columnNames, headers);
						} else {
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại", "Thông báo",
									JOptionPane.ERROR_MESSAGE);
							System.out.println(rowUpdate);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại", "Thông báo",
								JOptionPane.ERROR_MESSAGE);
						System.out.println(e2);
					}
				} else {
					try {
						Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing",
								"root", "123456");
						String sql = "INSERT INTO pharma (id, name) VALUES (?, ?)";
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setInt(1, Integer.parseInt(txf_pharma_id.getText()));
						stmt.setString(2, txf_pharma_name.getText());
						int rowInserted = stmt.executeUpdate();
						if (rowInserted > 0) {
							JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
							String[] columnNames = new String[] { "id", "name", "invisible" };
							String[] headers = new String[] { "Mã loại", "Tên loại", "Trạng thái" };
							refreshTable("id,Name,invisible", "pharma", columnNames, headers);
							txf_pharma_id.setText("");
							txf_pharma_name.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Thông báo",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Thông báo",
								JOptionPane.ERROR_MESSAGE);
						System.out.println(e2);
					}

				}

			}
		});
		btn_pharma.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_pharma.setBounds(508, 152, 134, 41);
		panel_pharma.add(btn_pharma);

		ImageIcon icon_back = new ImageIcon(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\back.png");
		Image img_back = icon_back.getImage();
		Image scaledImg_back = img_back.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon_back = new ImageIcon(scaledImg_back);
		JLabel lbl_back = new JLabel(scaledIcon_back);
		lbl_back.setBackground(new Color(250, 240, 230));

		lbl_back.setVisible(false);
		lbl_back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_back.setBounds(27, 232, 66, 55);
		panel_pharma.add(lbl_back);

		ImageIcon icon = new ImageIcon(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\plus.png");
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);
		JButton btn_plusPharma = new JButton();
		btn_plusPharma.setBorder(null);
		btn_plusPharma.setBackground(new Color(250, 240, 230));
		btn_plusPharma.setText("+ Tạo loại thuốc mới.");
		btn_plusPharma.setFont(new Font("Calibri", Font.BOLD, 22));
		btn_plusPharma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txf_pharma_id.setEditable(true);
				txf_pharma_id.setText("");
				txf_pharma_name.setText("");
				createPharmaBoolean = true;
				btn_pharma.setText("Thêm mới");

				lbl_back.setVisible(true);
				btn_plusPharma.setVisible(false);

			}
		});
		btn_plusPharma.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_plusPharma.setBounds(514, 242, 253, 34);

		panel_pharma.add(btn_plusPharma);

		JLabel lblNewLabel_1 = new JLabel("Mã loại:");
		lblNewLabel_1.setBackground(new Color(250, 240, 230));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(27, 83, 82, 26);
		panel_pharma.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tên loại:");
		lblNewLabel_1_1.setBackground(new Color(250, 240, 230));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(27, 159, 82, 26);
		panel_pharma.add(lblNewLabel_1_1);

		lbl_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createPharmaBoolean = false;
				btn_plusPharma.setVisible(true);
				lbl_back.setVisible(false);
				btn_pharma.setText("Cập nhật");
				txf_pharma_id.setEditable(false);
				clearAllJtextField();
			}
		});

		JPanel panel_pharmaLv2 = new JPanel();
		panel_pharmaLv2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_pharmaLv2.setBackground(new Color(250, 240, 230));
		parentPanel.add(panel_pharmaLv2, "name_7696933528300");
		panel_pharmaLv2.setLayout(null);

		JComboBox cbx_pharma = new JComboBox();
		cbx_pharma.setBackground(new Color(250, 240, 230));
		cbx_pharma.setFont(new Font("Dialog", Font.PLAIN, 21));
		cbx_pharma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root",
							"123456");
					String selectedPharma = cbx_pharma.getSelectedItem().toString();
					if (selectedPharma != null && !selectedPharma.isEmpty()) {
						String query = "SELECT pharmalv2.idpk,pharmalv2.name,pharmalv2.id,pharmalv2.invisible FROM pharmalv2 "
								+ "INNER JOIN pharma ON pharmalv2.idpk = pharma.id " + "WHERE pharma.name = ?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, selectedPharma);
						ResultSet rs = ps.executeQuery();

						DefaultTableModel model = new DefaultTableModel() {
							public boolean isCellEditable(int row, int column) {
								// không cho phép chỉnh sửa
								return false;
							}
						};
						model.addColumn("Mã nhóm thuốc");
						model.addColumn("Mã loại thuốc");
						model.addColumn("Tên nhóm thuốc");
						model.addColumn("Trạng thái");

						while (rs.next()) {
							Object[] row = new Object[4];
							row[0] = rs.getString("id");
							row[1] = rs.getInt("idpk");
							row[2] = rs.getString("name");
							if(rs.getBoolean("invisible")==true) {
								row[3] ="Ngưng hoạt động";
							}else {
								row[3] ="Hoạt động";
							}
							model.addRow(row);
						}
						table_list.setModel(model);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		cbx_pharma.setBounds(101, 39, 205, 41);
		panel_pharmaLv2.add(cbx_pharma);

		txf_pharma_id_lv2 = new JTextField();
		txf_pharma_id_lv2.setBackground(new Color(250, 240, 230));
		txf_pharma_id_lv2.setEditable(false);
		txf_pharma_id_lv2.setFont(new Font("Dialog", Font.PLAIN, 21));
		txf_pharma_id_lv2.setColumns(10);
		txf_pharma_id_lv2.setBounds(484, 90, 85, 41);
		panel_pharmaLv2.add(txf_pharma_id_lv2);

		txf_pharma_name_lv2 = new JTextField();
		txf_pharma_name_lv2.setBackground(new Color(250, 240, 230));
		txf_pharma_name_lv2.setFont(new Font("Dialog", Font.PLAIN, 21));
		txf_pharma_name_lv2.setColumns(10);
		txf_pharma_name_lv2.setBounds(484, 39, 227, 41);
		panel_pharmaLv2.add(txf_pharma_name_lv2);

		JCheckBox chb_checkVisible_pharmalv2 = new JCheckBox("Ẩn dữ liệu");
		chb_checkVisible_pharmalv2.setBackground(new Color(250, 240, 230));
		chb_checkVisible_pharmalv2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chb_checkVisible_pharmalv2.setBounds(560, 154, 161, 33);

		JButton btn_pharma_lv2 = new JButton("Cập nhật ");
		btn_pharma_lv2.setBackground(new Color(250, 240, 230));
		btn_pharma_lv2.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		btn_pharma_lv2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean checkInvisible = chb_checkVisible_pharmalv2.isSelected();
				String id = txf_pharma_id_lv2.getText();
				String ippk = txf_pharma_idpk.getText();
				String name = txf_pharma_name_lv2.getText();
				if (createPharmaLv2Boolean == true) {
					String sql = "INSERT INTO pharmalv2 (id, idpk, name) VALUES (?, ?, ?) ";
					String[] values = { id, ippk, name };
					boolean isInserted = addDataToTable(sql, values);
					if (isInserted) {
						JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						String[] columnNames = new String[] { "id", "idpk", "name", "invisible" };
						String[] header = new String[] { "Mã nhóm", "Mã loại", "Tên nhóm", "Trạng thái" };
						refreshTable("id,idpk,name,invisible", "pharmalv2 WHERE pharmalv2.idpk='" + ippk + "' ",
								columnNames, header);
					} else {
						JOptionPane.showMessageDialog(null, "Lỗi thêm dữ liệu", "Thông báo", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					try {
						Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing",
								"root", "123456");

						String sql = "UPDATE pharmalv2 " + " INNER JOIN drugslist ON pharmalv2.id=drugslist.idpk "
								+ " SET pharmalv2.name=?, pharmalv2.invisible =?, drugslist.invisible=? "
								+ " WHERE pharmalv2.id=?";
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setString(1, txf_pharma_name_lv2.getText());
						stmt.setBoolean(2, checkInvisible);
						stmt.setBoolean(3, checkInvisible);
						stmt.setInt(4, Integer.parseInt(txf_pharma_id_lv2.getText()));

						int rowUpdate = stmt.executeUpdate();
						if (rowUpdate > 0) {
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
							String[] columnNames = new String[] { "id", "idpk", "name", "invisible" };
							String[] header = new String[] { "Mã nhóm", "Mã loại", "Tên nhóm", "Trạng thái" };
							refreshTable("id,idpk,name,invisible", "pharmalv2 WHERE pharmalv2.idpk='" + ippk + "' ",
									columnNames, header);
						} else {
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại", "Thông báo",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại", "Thông báo",
								JOptionPane.ERROR_MESSAGE);
						System.out.println(e2);
					}
				}

			}
		});
		btn_pharma_lv2.setFont(new Font("Dialog", Font.PLAIN, 21));
		btn_pharma_lv2.setBounds(262, 198, 151, 41);
		panel_pharmaLv2.add(btn_pharma_lv2);

		txf_pharma_idpk = new JTextField();
		txf_pharma_idpk.setBackground(new Color(250, 240, 230));
		txf_pharma_idpk.setEditable(false);
		txf_pharma_idpk.setFont(new Font("Dialog", Font.PLAIN, 21));
		txf_pharma_idpk.setColumns(10);
		txf_pharma_idpk.setBounds(101, 90, 85, 41);
		panel_pharmaLv2.add(txf_pharma_idpk);

		JLabel lbl_back_lv2 = new JLabel(scaledIcon_back);
		lbl_back_lv2.setBackground(new Color(250, 240, 230));
		lbl_back_lv2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_back_lv2.setVisible(false);

		lbl_back_lv2.setBounds(21, 249, 67, 57);
		panel_pharmaLv2.add(lbl_back_lv2);

		JButton btn_plus_pharma_lv2 = new JButton();
		btn_plus_pharma_lv2.setBorder(null);
		btn_plus_pharma_lv2.setBackground(new Color(250, 240, 230));
		btn_plus_pharma_lv2.setFont(new Font("Calibri", Font.BOLD, 22));
		btn_plus_pharma_lv2.setText("+ Tạo nhóm thuốc mới.");
		btn_plus_pharma_lv2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_plus_pharma_lv2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllJtextField();
				createPharmaLv2Boolean = true;
				txf_pharma_id_lv2.setEditable(true);
				txf_pharma_idpk.setEditable(true);
				btn_pharma_lv2.setText("Thêm nhóm");
				lbl_back_lv2.setVisible(true);
				btn_plus_pharma_lv2.setVisible(false);

			}
		});
		btn_plus_pharma_lv2.setBounds(512, 258, 249, 33);
		panel_pharmaLv2.add(btn_plus_pharma_lv2);

		JLabel lblNewLabel_2 = new JLabel("Loại:");
		lblNewLabel_2.setBackground(new Color(250, 240, 230));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 43, 67, 25);
		panel_pharmaLv2.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Mã nhóm:");
		lblNewLabel_2_1.setBackground(new Color(250, 240, 230));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(367, 106, 92, 25);
		panel_pharmaLv2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Mã loại:");
		lblNewLabel_2_1_1.setBackground(new Color(250, 240, 230));
		lblNewLabel_2_1_1.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblNewLabel_2_1_1.setBounds(10, 101, 78, 25);
		panel_pharmaLv2.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_2 = new JLabel("Tên nhóm:");
		lblNewLabel_2_1_2.setBackground(new Color(250, 240, 230));
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_2.setBounds(367, 47, 107, 25);
		panel_pharmaLv2.add(lblNewLabel_2_1_2);

		panel_pharmaLv2.add(chb_checkVisible_pharmalv2);

		lbl_back_lv2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearAllJtextField();
				createPharmaLv2Boolean = false;
				txf_pharma_id_lv2.setEditable(false);
				txf_pharma_idpk.setEditable(false);
				lbl_back_lv2.setVisible(false);
				btn_pharma_lv2.setText("Cập nhật");
				btn_plus_pharma_lv2.setVisible(true);

			}
		});

		JPanel panel_Drugs = new JPanel();
		panel_Drugs.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Drugs.setBackground(new Color(250, 240, 230));
		parentPanel.add(panel_Drugs, "name_7699483769100");
		panel_Drugs.setLayout(null);

		JComboBox cbx_drugs = new JComboBox();
		cbx_drugs.setBackground(new Color(250, 240, 230));
		cbx_drugs.setFont(new Font("Dialog", Font.PLAIN, 21));
		cbx_drugs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String selectedDrug = cbx_drugs.getSelectedItem().toString();
				try {
					if (!selectedDrug.isEmpty()) {
						Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing",
								"root", "123456");
						String query = "SELECT code, name, price,invisible FROM drugslist "
								+ "WHERE idpk = (SELECT id FROM pharmalv2 WHERE name = ?)";

						String query2 = "SELECT id FROM pharmalv2 WHERE name = '" + selectedDrug + "'";
						PreparedStatement ps2 = conn.prepareStatement(query2);
						ResultSet rs2 = ps2.executeQuery();

						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, selectedDrug);
						ResultSet rs = ps.executeQuery();
						if (rs2.next()) {
							int idpk_drugs = rs2.getInt("id");
							txf_id_pharmalv2_drugs.setText(String.valueOf(idpk_drugs));

						}
						DefaultTableModel model = new DefaultTableModel() {
							public boolean isCellEditable(int row, int column) {
								return false; // không cho phép chỉnh sửa
							}
						};
						model.addColumn("Mã hàng");
						model.addColumn("Tên hàng");
						model.addColumn("Giá tiền");
						model.addColumn("Trạng thái");

						while (rs.next()) {
							Object[] row = new Object[4];
							row[0] = rs.getInt("code");
							row[1] = rs.getString("name");
							row[2] = rs.getInt("price");
							if(rs.getBoolean("invisible")==true) {
								row[3] = "Ngưng hoạt động";
							}else {
								row[3]="Hoạt động";
							}
							
							model.addRow(row);

						}

						table_list.setModel(model);

					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		cbx_drugs.setBounds(493, 23, 190, 39);
		panel_Drugs.add(cbx_drugs);

		JComboBox cbx_pharma2_drug = new JComboBox();
		cbx_pharma2_drug.setBackground(new Color(250, 240, 230));
		cbx_pharma2_drug.setFont(new Font("Dialog", Font.PLAIN, 21));
		cbx_pharma2_drug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cbx_drugs.removeAllItems();
					Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root",
							"123456");
					String selectedPharma = cbx_pharma2_drug.getSelectedItem().toString();
					if (!selectedPharma.isEmpty()&& selectedPharma!="") {
						String query = "SELECT pharmalv2.name, pharmalv2.id FROM pharmalv2 "
								+ "INNER JOIN pharma ON pharmalv2.idpk = pharma.id " + "WHERE pharma.name = ?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, selectedPharma);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							String name = rs.getString("name");
							cbx_drugs.addItem(name);
							int id_pharma = rs.getInt("id");
						}

					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		});
		cbx_pharma2_drug.setBounds(113, 20, 201, 39);
		panel_Drugs.add(cbx_pharma2_drug);

		txf_name_drugs = new JTextField();
		txf_name_drugs.setBackground(new Color(250, 240, 230));
		txf_name_drugs.setFont(new Font("Dialog", Font.PLAIN, 21));
		txf_name_drugs.setBounds(113, 72, 201, 39);
		panel_Drugs.add(txf_name_drugs);
		txf_name_drugs.setColumns(10);

		txf_price_drugs = new JTextField();
		txf_price_drugs.setBackground(new Color(250, 240, 230));
		txf_price_drugs.setFont(new Font("Dialog", Font.PLAIN, 21));
		txf_price_drugs.setBounds(113, 130, 201, 39);
		panel_Drugs.add(txf_price_drugs);
		txf_price_drugs.setColumns(10);

		txf_code_drugs = new JTextField();
		txf_code_drugs.setBackground(new Color(250, 240, 230));
		txf_code_drugs.setFont(new Font("Dialog", Font.PLAIN, 21));
		txf_code_drugs.setEditable(false);
		txf_code_drugs.setBounds(496, 130, 106, 39);
		panel_Drugs.add(txf_code_drugs);
		txf_code_drugs.setColumns(10);

		JLabel lbl_back_drug = new JLabel(scaledIcon_back);
		lbl_back_drug.setBackground(new Color(250, 240, 230));

		lbl_back_drug.setVisible(false);
		lbl_back_drug.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_back_drug.setBounds(10, 251, 67, 45);
		panel_Drugs.add(lbl_back_drug);

		JCheckBox chb_checkVisible_drugs = new JCheckBox("Ẩn dữ liệu");
		chb_checkVisible_drugs.setBackground(new Color(250, 240, 230));
		chb_checkVisible_drugs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chb_checkVisible_drugs.setBounds(554, 175, 161, 33);
		panel_Drugs.add(chb_checkVisible_drugs);

		JButton btn_drugs = new JButton("Cập nhật ");
		btn_drugs.setBackground(new Color(250, 240, 230));
		btn_drugs.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		btn_drugs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = txf_code_drugs.getText();
				String name = txf_name_drugs.getText();
				String price = txf_price_drugs.getText();
				String idpk = txf_id_pharmalv2_drugs.getText();
				Boolean checkVisible = chb_checkVisible_drugs.isSelected();
				int temp = checkVisible ? 1 : 0;

				if (createDrugsBoolean == true) {
					String sql = "INSERT INTO drugslist (code,name,price,idpk)" + "VALUES (?,?,?,?)";
					String[] values = { code, name, price, idpk };
					boolean isInserted = addDataToTable(sql, values);
					if (isInserted) {
						JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						String[] columnNames = new String[] { "code", "name", "price", "invisible" };
						String[] headers = new String[] { "Mã thuốc", "Tên thuốc", "Giá thuốc", "Trạng thái" };
						refreshTable("code,name,price,invisible", "drugslist WHERE idpk='" + idpk + "'  ", columnNames,
								headers);
						clearAllJtextField();
					} else {
						JOptionPane.showMessageDialog(null, "Lỗi thêm dữ liệu", "Thông báo", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					String sql = "UPDATE drugslist SET name=?,price=?,idpk=?, invisible=?" + "WHERE code = ?";
					String[] values = { name, price, idpk, String.valueOf(temp), code };
					boolean isUpdate = updateDataInTable(sql, values);
					if (isUpdate) {
						JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						String[] columnNames = new String[] { "code", "name", "price", "invisible" };
						String[] headers = new String[] { "Mã thuốc", "Tên thuốc", "Giá thuốc", "Trạng thái" };
						refreshTable("code,name,price,invisible", "drugslist WHERE idpk='" + idpk + "'  ", columnNames,
								headers);
						clearAllJtextField();
					} else {
						JOptionPane.showMessageDialog(null, "Lỗi cập nhật dữ liệu", "Thông báo",
								JOptionPane.ERROR_MESSAGE);
						clearAllJtextField();
					}
				}
			}
		});

		JButton btn_plus_drug = new JButton();
		btn_plus_drug.setBorder(null);
		btn_plus_drug.setBackground(new Color(250, 240, 230));
		btn_plus_drug.setFont(new Font("Calibri", Font.BOLD, 20));
		btn_plus_drug.setText("+ Thêm thuốc.");
		btn_plus_drug.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_plus_drug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllJtextField();
				createDrugsBoolean = true;
				txf_code_drugs.setEditable(true);
				lbl_back_drug.setVisible(true);
				btn_plus_drug.setVisible(false);
				btn_drugs.setText("Thêm thuốc mới");

			}
		});
		btn_plus_drug.setBounds(588, 249, 161, 33);
		panel_Drugs.add(btn_plus_drug);

		lbl_back_drug.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearAllJtextField();
				createDrugsBoolean = false;
				txf_code_drugs.setEditable(false);
				btn_drugs.setText("Cập nhật");
				btn_plus_drug.setVisible(true);
				lbl_back_drug.setVisible(false);

			}
		});

		btn_drugs.setFont(new Font("Dialog", Font.PLAIN, 21));
		btn_drugs.setBounds(263, 212, 150, 41);
		panel_Drugs.add(btn_drugs);

		txf_id_pharmalv2_drugs = new JTextField();
		txf_id_pharmalv2_drugs.setBackground(new Color(250, 240, 230));
		txf_id_pharmalv2_drugs.setFont(new Font("Dialog", Font.PLAIN, 21));
		txf_id_pharmalv2_drugs.setColumns(10);
		txf_id_pharmalv2_drugs.setBounds(496, 72, 106, 39);
		panel_Drugs.add(txf_id_pharmalv2_drugs);

		JLabel lblNewLabel_3 = new JLabel("Loại:");
		lblNewLabel_3.setBackground(new Color(250, 240, 230));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 23, 57, 25);
		panel_Drugs.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Nhóm:");
		lblNewLabel_3_1.setBackground(new Color(250, 240, 230));
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(391, 30, 67, 25);
		panel_Drugs.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_1_1 = new JLabel("Mã nhóm:");
		lblNewLabel_3_1_1.setBackground(new Color(250, 240, 230));
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1_1.setBounds(391, 79, 95, 25);
		panel_Drugs.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_1_1_1 = new JLabel("Mã thuốc:");
		lblNewLabel_3_1_1_1.setBackground(new Color(250, 240, 230));
		lblNewLabel_3_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_3_1_1_1.setBounds(391, 137, 95, 25);
		panel_Drugs.add(lblNewLabel_3_1_1_1);

		JLabel lblNewLabel_3_1_1_2 = new JLabel("Tên thuốc:");
		lblNewLabel_3_1_1_2.setBackground(new Color(250, 240, 230));
		lblNewLabel_3_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1_1_2.setBounds(10, 79, 98, 25);
		panel_Drugs.add(lblNewLabel_3_1_1_2);

		JLabel lblNewLabel_3_1_1_2_1 = new JLabel("Giá tiền:");
		lblNewLabel_3_1_1_2_1.setBackground(new Color(250, 240, 230));
		lblNewLabel_3_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1_1_2_1.setBounds(10, 137, 98, 25);
		panel_Drugs.add(lblNewLabel_3_1_1_2_1);

		JRadioButton rd_pharma = new JRadioButton("Chỉnh sửa loại thuốc");
		rd_pharma.setBackground(new Color(250, 240, 230));
		rd_pharma.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rd_pharma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.removeAll();
				parentPanel.add(panel_pharma);
				parentPanel.repaint();
				parentPanel.revalidate();
				cbx_pharma.removeAllItems();

				String[] columnNames = new String[] { "id", "name", "invisible" };
				String[] headers = new String[] { "Mã loại", "Tên loại", "Trạng thái" };
				refreshTable("*", "pharma", columnNames, headers);

			}
		});
		rd_pharma.setFont(new Font("Tahoma", Font.BOLD, 17));
		rd_pharma.setBounds(6, 50, 209, 50);
		contentPane.add(rd_pharma);

		JRadioButton rd_pharmaLv2 = new JRadioButton("Chỉnh sửa nhóm thuốc");
		rd_pharmaLv2.setBackground(new Color(250, 240, 230));
		rd_pharmaLv2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rd_pharmaLv2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.removeAll();
				parentPanel.add(panel_pharmaLv2);
				parentPanel.repaint();
				parentPanel.revalidate();
				addItemToComboBox(cbx_pharma, "SELECT name FROM pharma");

				String[] columnNames = new String[] { "id", "idpk", "name", "invisible" };
				String[] header = new String[] { "Mã nhóm", "Mã loại", "Tên nhóm", "Trạng thái" };
				refreshTable("id,idpk,name,invisible", "pharmalv2 ", columnNames, header);
			}
		});
		rd_pharmaLv2.setFont(new Font("Tahoma", Font.BOLD, 17));
		rd_pharmaLv2.setBounds(6, 146, 228, 38);
		contentPane.add(rd_pharmaLv2);

		JRadioButton rd_drugs = new JRadioButton("Chỉnh sửa tên thuốc");
		rd_drugs.setBackground(new Color(250, 240, 230));
		rd_drugs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rd_drugs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.removeAll();
				parentPanel.add(panel_Drugs);
				parentPanel.repaint();
				parentPanel.revalidate();
				addItemToComboBox(cbx_pharma2_drug, "SELECT name FROM pharma");
				addItemToComboBox(cbx_drugs, "SELECT name FROM pharmalv2");

				String[] columnNames = new String[] { "code", "name", "price", "invisible" };
				String[] headers = new String[] { "Mã thuốc", "Tên thuốc", "Giá thuốc", "Trạng thái" };
				refreshTable("code,name,price,invisible", "drugslist ", columnNames, headers);

			}
		});
		rd_drugs.setFont(new Font("Tahoma", Font.BOLD, 17));
		rd_drugs.setBounds(6, 229, 193, 38);
		contentPane.add(rd_drugs);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rd_pharma);
		buttonGroup.add(rd_pharmaLv2);
		buttonGroup.add(rd_drugs);
		rd_pharma.setSelected(true);

		JButton btnNewButton_2 = new JButton("<<Quay lại");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HomePage homePage = new HomePage();
					homePage.setLocation(90, 50);
					homePage.setVisible(true);
					dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBackground(new Color(255, 182, 193));
		btnNewButton_2.setBounds(0, 0, 130, 32);
		contentPane.add(btnNewButton_2);

		table_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rd_pharma.isSelected()) {
					int row = table_list.getSelectedRow();
					String nameString = table_list.getValueAt(row, 1).toString();
					String idDrugs = table_list.getValueAt(row, 0).toString();
					txf_pharma_name.setText(nameString);
					txf_pharma_id.setText(idDrugs);
					if (table_list.getValueAt(row, 2).toString() == "Ngưng hoạt động") {
						chb_checkVisible.setSelected(true);
					} else {
						chb_checkVisible.setSelected(false);
					}
				} else if (rd_pharmaLv2.isSelected()) {
					int row = table_list.getSelectedRow();
					String nameString = table_list.getValueAt(row, 2).toString();
					String idDrugs = table_list.getValueAt(row, 0).toString();
					String idpkDrugs = table_list.getValueAt(row, 1).toString();
					txf_pharma_name_lv2.setText(nameString);
					txf_pharma_id_lv2.setText(idDrugs);
					txf_pharma_idpk.setText(idpkDrugs);
					if (table_list.getValueAt(row, 3).toString() == "Ngưng hoạt động") {
						chb_checkVisible_pharmalv2.setSelected(true);
					} else {
						chb_checkVisible_pharmalv2.setSelected(false);
					}
				} else if (rd_drugs.isSelected()) {
					int row = table_list.getSelectedRow();
					String nameString = table_list.getValueAt(row, 1).toString();
					String idDrugs = table_list.getValueAt(row, 0).toString();
					String priceDrugs = table_list.getValueAt(row, 2).toString();
					txf_name_drugs.setText(nameString);
					txf_code_drugs.setText(idDrugs);
					txf_price_drugs.setText(priceDrugs);
					if (table_list.getValueAt(row, 3).toString() == "Ngưng hoạt động") {
						chb_checkVisible_drugs.setSelected(true);
					} else {
						chb_checkVisible_drugs.setSelected(false);
					}
				}

			}
		});

	}

	void clearAllJtextField() {
		JTextField[] jTextField = { txf_code_drugs, txf_id_pharmalv2_drugs, txf_name_drugs, txf_pharma_id,
				txf_pharma_id_lv2, txf_pharma_idpk, txf_pharma_idpk, txf_pharma_name, txf_pharma_name_lv2,
				txf_price_drugs };
		for (JTextField textField : jTextField) {
			textField.setText("");
		}
	}

	void refreshTable(String selectFromTable, String tableName, String[] columnNames, String[] headers) {
		DefaultTableModel model = new DefaultTableModel(headers, 0);
		String sqlString = String.format("SELECT %s FROM %s", selectFromTable, tableName);
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root",
				"123456");
				PreparedStatement pstmt = conn.prepareStatement(sqlString);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				String[] row = new String[columnNames.length];
				for (int i = 0; i < columnNames.length; i++) {
					String columnName = columnNames[i];
					if (columnName.equals("invisible")) {
						int value = rs.getInt(columnName);
						row[i] = value == 0 ? "Hoạt động" : "Ngưng hoạt động";
					} else {
						row[i] = rs.getString(columnName);
					}
				}
				model.addRow(row);
			}

			table_list.setModel(model);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean addDataToTable(String sql, String[] values) {
		try {
//		    	String sql="INSERT INTO pharmalv2 (id, idpk, name) VALUES (?, ?, ?) ";
//				String[] values = {id, ippk, name};
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < values.length; i++) {
				pstmt.setString(i + 1, values[i]);

			}
			int rowInserted = pstmt.executeUpdate();
			if (rowInserted > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean updateDataInTable(String sql, String[] values) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < values.length; i++) {
				pstmt.setString(i + 1, values[i]);
			}
			int rowUpdated = pstmt.executeUpdate();
			if (rowUpdated > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void addItemToComboBox(JComboBox comboBox, String query) {
	    comboBox.removeAllItems();
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            ResultSet rs = pstmt.executeQuery()) {
	        while (rs.next()) {
	            comboBox.addItem(rs.getString(1));
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
}
