package com.pharmaswing.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.pharmaswing.model.GetInfoUser;
import com.pharmaswing.model.HistoryLogin;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txfUsername;
	private JPasswordField txfPassword;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private Timer timer;
	private boolean showPass=false;
	
	

	/**
	 * Launch the application.
	 */
	static JButton btnLogin = new JButton("LOGIN");
	private JLabel lblNewLabel;
	private JLabel showhidepass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Login frame = new Login();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public Login() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\logoApp.png"));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 566);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		JLabel showhidepass = new JLabel("");
		showhidepass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showhidepass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPass=!showPass;
				if(showPass==false) {
					txfPassword.setEchoChar('\u2022');
					ImageIcon iconShow = new ImageIcon(
							"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\showpass.png");
					Image imageEye = iconShow.getImage();
					Image scaledImgEye = imageEye.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
					ImageIcon scaledImg = new ImageIcon(scaledImgEye);
					showhidepass.setIcon(scaledImg);
				}else {
					ImageIcon iconShow = new ImageIcon(
							"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\hiddenpass.png");
					Image imageEye = iconShow.getImage();
					Image scaledImgEye = imageEye.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
					ImageIcon scaledImg = new ImageIcon(scaledImgEye);
					showhidepass.setIcon(scaledImg);
					txfPassword.setEchoChar((char) 0);
				}
			}
		});
		showhidepass.setBounds(761, 361, 32, 24);
		contentPane.add(showhidepass);

		JLabel backgroundTiltle = new JLabel("");
		backgroundTiltle.setBounds(10, 0, 754, 132);

		ImageIcon imageIcon = new ImageIcon(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\logo4-removebg-preview.png");
		Image image = imageIcon.getImage();
		Image scaledImage = image.getScaledInstance(700, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		backgroundTiltle.setIcon(scaledImageIcon);
		contentPane.add(backgroundTiltle);
		
		if(showPass==false) {
			ImageIcon iconShow = new ImageIcon(
					"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\showpass.png");
			Image imageEye = iconShow.getImage();
			Image scaledImgEye = imageEye.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon scaledImg = new ImageIcon(scaledImgEye);
			showhidepass.setIcon(scaledImg);
		}


		
		txfUsername = new JTextField();
		txfUsername.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txfUsername.setDisabledTextColor(SystemColor.textText);
		txfUsername.setSelectedTextColor(SystemColor.controlHighlight);
		txfUsername.setSelectionColor(SystemColor.textText);
		txfUsername.setForeground(SystemColor.textText);
		txfUsername.setBackground(new Color(204, 204, 255));
		txfUsername.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txfUsername.setBorder(new EmptyBorder(0, 0, 3, 0));
		txfUsername.setBounds(535, 263, 216, 30);
		contentPane.add(txfUsername);
		txfUsername.setColumns(10);

		txfPassword = new JPasswordField();
		txfPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txfPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin.doClick();
			}
		});
		txfPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txfPassword.setBorder(null);
		txfPassword.setBackground(new Color(204, 204, 255));
		txfPassword.setBounds(535, 350, 216, 30);
		contentPane.add(txfPassword);
		btnLogin.setHideActionText(true);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		btnLogin.setBackground(new Color(0, 204, 255));

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String usernameString = txfUsername.getText();
				String passString = new String(txfPassword.getPassword());

				if (checkLogin(usernameString, passString)) {
					try {
						HomePage homePage = new HomePage();
						showDialog("Đăng nhập thành công, vui lòng chờ...", 2000);
						homePage.setLocation(90, 50);
						homePage.setVisible(true);
						
						btnLogin.removeActionListener(this);
						HistoryLogin historyLogin=  HistoryLogin.getInstance();
						GetInfoUser getInfoUser=GetInfoUser.getInstance();
						Date currentDateTime = new Date();
						historyLogin.setFromLogin(getInfoUser.getIdstaff(), getInfoUser.getId_position(), currentDateTime);
						dispose();

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Đăng nhập không thành công. Vui lòng nhập lại!!");
					System.out.println("User" + usernameString);
					return;
				}
				txfPassword.setText(null);
				txfUsername.setText(null);
			}

		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(483, 415, 197, 53);

		contentPane.add(btnLogin);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(204, 204, 255));
		separator.setBounds(535, 296, 216, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(204, 204, 255));
		separator_1.setBounds(535, 382, 216, 2);
		contentPane.add(separator_1);

		txtUsername = new JTextField();
		txtUsername.setFocusable(false);
		txtUsername.setOpaque(false);
		txtUsername.setEditable(false);
		txtUsername.setSelectedTextColor(Color.LIGHT_GRAY);
		txtUsername.setFont(new Font("VNI-Palatin", Font.PLAIN, 25));
		txtUsername.setText("Username:");
		txtUsername.setBackground(new Color(204, 204, 255));
		txtUsername.setBorder(null);
		txtUsername.setBounds(393, 265, 132, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setFocusable(false);
		txtPassword.setEditable(false);
		txtPassword.setText("Password:");
		txtPassword.setSelectedTextColor(Color.LIGHT_GRAY);
		txtPassword.setFont(new Font("VNI-Palatin", Font.PLAIN, 25));
		txtPassword.setColumns(10);
		txtPassword.setBorder(null);
		txtPassword.setBackground(new Color(204, 204, 255));
		txtPassword.setBounds(393, 348, 132, 39);
		contentPane.add(txtPassword);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(204, 102, 255));
		lblNewLabel_1.setFocusable(false);
		lblNewLabel_1.setIcon(
				new ImageIcon("C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\logo3-removebg-preview.png"));
		lblNewLabel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel_1.setBounds(0, 0, 830, 532);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("LOGIN HERE");
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 45));
		lblNewLabel.setBounds(423, 164, 353, 61);
		contentPane.add(lblNewLabel);
		
		
	}

	boolean checkLogin(String usernameString, String pasString) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
			java.sql.Statement stmStatement = con.createStatement();
			String sqlString = "select * from account where username='" + usernameString + "' and password ='"
					+ pasString + "' and active=true";
			ResultSet rsSet = stmStatement.executeQuery(sqlString);

			if (rsSet.next()) {
				// Lấy thông tin của nhân viên đăng nhập
				String positionUser = rsSet.getString("position");
				String staffNameString = rsSet.getString("namestaff");
				int idStaff = rsSet.getInt("idstaff");
				GetInfoUser getInfoUser = GetInfoUser.getInstance();
				getInfoUser.setPosition(positionUser);
				getInfoUser.setstaffname(staffNameString);
				getInfoUser.setIdstaff(idStaff);
				getInfoUser.setId_position(rsSet.getInt("id_position"));
				return true;
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		return false;
	}

	public static void showDialog(String message, int duration) {
		JFrame frame = new JFrame();
		JDialog dialog = new JDialog(frame, "Thông báo", true);

		JLabel label = new JLabel(message, SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 22));
		dialog.getContentPane().add(label);

		dialog.setUndecorated(true);
		dialog.pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		dialog.setLocation(centerX - dialog.getWidth() / 2, centerY - dialog.getHeight() / 2);

		Timer timer = new Timer(duration, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});

		timer.setRepeats(false);
		timer.start();
		dialog.setVisible(true);
	}
}
