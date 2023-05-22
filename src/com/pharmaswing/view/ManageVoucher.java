package com.pharmaswing.view;

import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import com.pharmaswing.DAO.ManageVoucherDAO;
import com.pharmaswing.model.Voucher;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

public class ManageVoucher extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txf_codevoucher;
	private JFormattedTextField txf_discount;
	private JDateChooser date_startdate;
	private JDateChooser date_enddate;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageVoucher frame = new ManageVoucher();
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
	public ManageVoucher() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\voucher.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 982, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 626, 396);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBackground(new Color(255, 250, 250));
		scrollPane.setViewportView(table);

		JButton btn_createNewVoucher = new JButton("Tạo voucher");
		btn_createNewVoucher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_createNewVoucher.setBackground(new Color(255, 250, 240));

		btn_createNewVoucher.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_createNewVoucher.setBounds(810, 416, 148, 48);
		btn_createNewVoucher.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		contentPane.add(btn_createNewVoucher);

		txf_codevoucher = new JTextField();
		txf_codevoucher.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txf_codevoucher.setBounds(810, 100, 148, 34);
		contentPane.add(txf_codevoucher);
		txf_codevoucher.setColumns(10);

		JLabel lblNewLabel = new JLabel("Mã voucher:");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNewLabel.setBounds(659, 108, 141, 26);
		contentPane.add(lblNewLabel);

		JLabel lblNgyHiuLc = new JLabel("Ngày bắt đầu:");
		lblNgyHiuLc.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNgyHiuLc.setBounds(659, 187, 141, 26);
		contentPane.add(lblNgyHiuLc);

		JLabel lblNgyKtThc = new JLabel("Ngày kết thúc:");
		lblNgyKtThc.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNgyKtThc.setBounds(659, 254, 141, 26);
		contentPane.add(lblNgyKtThc);

		JLabel lblGimGi = new JLabel("Giảm giá:");
		lblGimGi.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblGimGi.setBounds(659, 321, 141, 26);
		contentPane.add(lblGimGi);

		 date_startdate = new JDateChooser();
		date_startdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		date_startdate.setBounds(810, 179, 148, 34);
		contentPane.add(date_startdate);

		 date_enddate = new JDateChooser();
		date_enddate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		date_enddate.setBounds(810, 246, 148, 34);
		contentPane.add(date_enddate);

		NumberFormatter formatter = new NumberFormatter(new DecimalFormat("#0")) {
			@Override
			public Object stringToValue(String text) throws ParseException {
				if (text == null || text.isEmpty()) {
					return null;
				}

				try {
					int value = Integer.parseInt(text);
					if (value >= 0 && value <= 100) {
						return value;
					}
				} catch (NumberFormatException e) {
				}

				throw new ParseException("Exception value", 0);
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				if (value == null) {
					return "";
				}
				return super.valueToString(value);
			}
		};

		formatter.setMinimum(0);
		formatter.setMaximum(100);
		formatter.setAllowsInvalid(false);

		txf_discount = new JFormattedTextField(formatter);
		txf_discount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txf_discount.setColumns(10);
		txf_discount.setBounds(810, 314, 90, 34);
		contentPane.add(txf_discount);

		Checkbox chb_active = new Checkbox("Hoạt động");
		chb_active.setFont(new Font("Dialog", Font.PLAIN, 20));
		chb_active.setBounds(822, 362, 173, 34);
		contentPane.add(chb_active);

		JButton btn_updateVoucher = new JButton("Cập nhật");
		btn_updateVoucher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_updateVoucher.setBackground(new Color(255, 250, 240));
		btn_updateVoucher.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_updateVoucher.setBounds(659, 416, 127, 48);
		btn_updateVoucher.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		contentPane.add(btn_updateVoucher);
		
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
		btnNewButton_2.setBounds(0, 0, 132, 32);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("(%)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(910, 314, 45, 33);
		contentPane.add(lblNewLabel_1);
		LoadDataTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int id = Integer.parseInt(table.getValueAt(row, 0).toString());

				try {
					ManageVoucherDAO manageVoucherDAO = new ManageVoucherDAO();
					Voucher voucher = manageVoucherDAO.getVoucherById(id);

					if (voucher != null) {
						txf_codevoucher.setText(voucher.getCodevoucher());
						date_startdate.setDate(voucher.getStartDate());
						date_enddate.setDate(voucher.getEndDate());

						DecimalFormat decimalFormat = new DecimalFormat("##");
						String formattedDiscount = decimalFormat.format(voucher.getDiscount());
						txf_discount.setText(formattedDiscount);
						if (voucher.isActive() == true) {
							chb_active.setState(true);
						} else {
							chb_active.setState(false);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		btn_createNewVoucher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codevoucherString = txf_codevoucher.getText();
				java.util.Date startDate = date_startdate.getDate();
				java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());

				java.util.Date endDate = date_enddate.getDate();
				java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

				int discount = Integer.parseInt(txf_discount.getText());
				System.out.println("from home" + discount);
				try {
					ManageVoucherDAO manageVoucherDAO = new ManageVoucherDAO();
					manageVoucherDAO.addNewVoucher(codevoucherString, sqlStartDate, sqlEndDate, discount);
					manageVoucherDAO.loadDataTableVoucher(table);
					RefreshJframe();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Thêm voucher thất bại!");
					System.out.println(e2.getMessage());
				}
			}
		});
		btn_updateVoucher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
					String codevoucher = txf_codevoucher.getText();
					boolean active = chb_active.getState();
					java.util.Date endDate = date_enddate.getDate();
					java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

					try {
						ManageVoucherDAO manageVoucherDAO = new ManageVoucherDAO();
						manageVoucherDAO.updateVoucher(id, codevoucher, active, sqlEndDate);
						manageVoucherDAO.loadDataTableVoucher(table);
						RefreshJframe();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Hãy chọn dòng muốn sửa đổi");
				}
			}
		});
	}

	void LoadDataTable() {
		try {
			ManageVoucherDAO manageVoucherDAO = new ManageVoucherDAO();
			manageVoucherDAO.loadDataTableVoucher(table);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public void RefreshJframe() {
		txf_codevoucher.setText("");
		txf_discount.setText("");
		date_enddate.setDate(null);
		date_startdate.setDate(null);
	}

}
