package com.pharmaswing.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import com.pharmaswing.DAO.CustomerDAO;
import com.toedter.calendar.JDateChooser;

public class Customer extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txf_phonenumber;
	private JTextField txf_customername;
	private JTextField txf_points;
	private boolean existCustomer;
	private int points;
	private HomePage homePage;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Customer(HomePage homePage) {
		this();
		this.homePage = homePage;

	}

	public void setPoints(int points) {
		this.points = points;

	}

	/**
	 * Create the frame.
	 */
	public Customer() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\inputPoints.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 619, 604);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Số điện thoại:");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNewLabel.setBounds(36, 69, 158, 42);
		contentPane.add(lblNewLabel);

		JLabel lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblTnKhchHng.setBounds(20, 225, 174, 42);
		contentPane.add(lblTnKhchHng);

		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNgySinh.setBounds(20, 312, 174, 42);
		contentPane.add(lblNgySinh);

		JLabel lblSimHin = new JLabel("Số điểm hiện có:");
		lblSimHin.setVisible(false);
		lblSimHin.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblSimHin.setBounds(20, 375, 174, 42);
		contentPane.add(lblSimHin);
		NumberFormatter formatter = new NumberFormatter(new DecimalFormat("#0")) {
			@Override
			public Object stringToValue(String text) throws ParseException {
				if (text == null || text.isEmpty()) {
					return null;
				}

				try {
					int value = Integer.parseInt(text);
					if (value >= 0 && value <= 1000000000) {
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
		formatter.setMaximum(1000000000);
		formatter.setAllowsInvalid(false);
		

		txf_phonenumber = new JFormattedTextField(formatter);
		txf_phonenumber.setBorder(null);
		txf_phonenumber.setBackground(new Color(0, 255, 255));

		txf_phonenumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txf_phonenumber.setBounds(206, 62, 282, 49);
		contentPane.add(txf_phonenumber);
		txf_phonenumber.setColumns(10);

		txf_customername = new JTextField();
		txf_customername.setBackground(new Color(0, 255, 255));
		txf_customername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txf_customername.setColumns(10);
		txf_customername.setBounds(300, 221, 207, 42);
		contentPane.add(txf_customername);

		txf_points = new JTextField();
		txf_points.setBackground(new Color(0, 255, 255));
		txf_points.setVisible(false);
		txf_points.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txf_points.setColumns(10);
		txf_points.setBounds(300, 371, 210, 42);
		contentPane.add(txf_points);

		JDateChooser date_datecustomer = new JDateChooser();
		date_datecustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		date_datecustomer.setBackground(new Color(0, 255, 255));
		date_datecustomer.setBounds(300, 299, 207, 42);
		LocalDate today = LocalDate.now();

		// Tạo một Calendar từ ngày hiện tại
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Date.from(today.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		date_datecustomer.setMaxSelectableDate(calendar.getTime());		
		contentPane.add(date_datecustomer);

		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(206, 475, 180, 58);
		btnNewButton.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Dùng");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setBackground(new Color(64, 224, 208));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pointString = JOptionPane.showInputDialog("Nhập số điểm muốn sử dụng");
				int points = Integer.parseInt(pointString);
				int pointsTemp = Integer.valueOf(txf_points.getText());
				if (points > pointsTemp) {
					JOptionPane.showMessageDialog(null, "Nhập điểm không hợp lệ!");
				} else {
					try {
						CustomerDAO dao = new CustomerDAO();
						String phoneNumber = txf_phonenumber.getText();
						dao.usingPointsCustomer(points, phoneNumber);
						txf_points.setText(String.valueOf(pointsTemp - points));
						JOptionPane.showMessageDialog(null, "Trừ điểm thành công!");
						homePage.updatePoints(points, phoneNumber);
						dispose();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(517, 381, 81, 32);
		contentPane.add(btnNewButton_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 10, 578, 8);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 543, 578, 8);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(193, 111, 340, 8);
		contentPane.add(separator_2);

		JButton btnNewButton_2 = new JButton("<<Quay lại");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HomePage homePage = new HomePage();
					homePage.setVisible(true);
					dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBackground(new Color(0, 255, 255));
		btnNewButton_2.setBounds(0, 0, 130, 32);
		contentPane.add(btnNewButton_2);

		txf_phonenumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String phoneNumber = txf_phonenumber.getText();
					com.pharmaswing.model.Customer customer = null;
					CustomerDAO customerDAO = new CustomerDAO();
					if (!phoneNumber.isEmpty()) {
						customer = customerDAO.getCustomerByPhoneNumber(phoneNumber);
					}

					if (customer != null) {
						txf_customername.setText(customer.getCustomerName());
						date_datecustomer.setDate(customer.getDateCustomer());
						txf_points.setText(String.valueOf(customer.getPoints()));
						txf_points.setText(String.valueOf(customer.getPoints()));
						existCustomer = true;
						lblSimHin.setVisible(true);
						txf_points.setVisible(true);
						btnNewButton_1.setVisible(true);
						txf_customername.setEditable(false);
						customerDAO.checkBirthdayCustomer(customer.getDateCustomer(), phoneNumber);

					} else {
						existCustomer = false;
						txf_customername.setEditable(true);
						txf_customername.setText("");
						date_datecustomer.setDate(null);
						txf_points.setText("");
						txf_points.setVisible(false);
						btnNewButton_1.setVisible(false);
						lblSimHin.setVisible(false);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double pointsDouble = points * 0.01;
				BigDecimal pointsDecimal = new BigDecimal(pointsDouble);
				int pointsCustomer = pointsDecimal.intValue();
				try {
					if (!existCustomer) {
						String phoneNumber = txf_phonenumber.getText();
						String customerName = txf_customername.getText();
						java.util.Date utilDate = date_datecustomer.getDate();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						CustomerDAO customerDAO = new CustomerDAO();
						customerDAO.createNewCustomer(phoneNumber, customerName, sqlDate, pointsCustomer);
						homePage.getCustomerName(customerName,phoneNumber);
						System.out.println("from customer :"+customerName);
						dispose();
					} else {

						String phoneNumber = txf_phonenumber.getText();
						CustomerDAO customerDAO = new CustomerDAO();
						customerDAO.updatePointsCustomer(pointsCustomer, phoneNumber);
						customerDAO.getCustomerByPhoneNumber(phoneNumber);
						com.pharmaswing.model.Customer customer = new com.pharmaswing.model.Customer();
						String customerName = txf_customername.getText();
						System.out.println("from customer"+customerName);
						homePage.getCustomerName(customerName,phoneNumber);
						dispose();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

	}

	public void clearAllJtextField() {
		JTextField[] jTextField = { txf_customername, txf_phonenumber, txf_points };
		for (JTextField textField : jTextField) {
			textField.setText("");
		}
	}
}
