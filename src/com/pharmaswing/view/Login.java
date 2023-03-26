package com.pharmaswing.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txfUsername;
	private JPasswordField txfPassword;
	private JTextField txtUsername;
	private JTextField txtPassword;
	public int id;
	public String namestaff;
	private JLabel lbl_user;
	/**
	 * Launch the application.
	 */
	static JButton btnLogin = new JButton("LOGIN");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

					try {
						frame.getRootPane().setDefaultButton(btnLogin);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public Login() {
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 439);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("LOGIN TO SYSTEM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(191, 10, 287, 52);
		contentPane.add(lblNewLabel);

		txfUsername = new JTextField();
		txfUsername.setDisabledTextColor(SystemColor.textText);
		txfUsername.setSelectedTextColor(SystemColor.controlHighlight);
		txfUsername.setSelectionColor(SystemColor.textText);
		txfUsername.setForeground(SystemColor.textText);
		txfUsername.setBackground(SystemColor.controlHighlight);
		txfUsername.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txfUsername.setBorder(null);
		txfUsername.setBounds(224, 160, 216, 30);
		contentPane.add(txfUsername);
		txfUsername.setColumns(10);

		txfPassword = new JPasswordField();
		txfPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin.doClick();
			}
		});
		txfPassword.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txfPassword.setBorder(null);
		txfPassword.setBackground(SystemColor.controlHighlight);
		txfPassword.setBounds(224, 260, 216, 30);
		contentPane.add(txfPassword);
		
		lbl_user = new JLabel("test");
		lbl_user.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_user.setBounds(808, 702, 177, 26);


		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameString = txfUsername.getText();
				String passString = new String(txfPassword.getPassword());

				// conect db
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager
							.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
					java.sql.Statement stmStatement = con.createStatement();
					String sqlString = "select * from account where username='" + usernameString + "' and password ='"
							+ passString + "'";
					ResultSet rsSet = stmStatement.executeQuery(sqlString);
					
					if (rsSet.next()) {
						namestaff = rsSet.getString("namestaff");
						 id = rsSet.getInt("idstaff");
						 lbl_user.setText(String(id));
						 System.out.println("Login: " +id);
						//login success
						showDialog("Đăng nhập thành công, vui lòng chờ...", 2000);
						setVisible(false);
						new HomePage().setVisible(true);
						
						
						
					} else {
						//login false
						 JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không hợp lệ! Vui lòng nhập lại.");
					}
				} catch (Exception e1) {
					// TODO: handlsyse exception
					System.out.println(e1.getMessage());
				}
			}

			private String String(int id) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(224, 329, 155, 33);

		contentPane.add(btnLogin);

		JSeparator separator = new JSeparator();
		separator.setBounds(224, 198, 216, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(224, 300, 216, 2);
		contentPane.add(separator_1);

		txtUsername = new JTextField();
		txtUsername.setEnabled(false);
		txtUsername.setEditable(false);
		txtUsername.setSelectedTextColor(Color.LIGHT_GRAY);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtUsername.setText("Username:");
		txtUsername.setBackground(SystemColor.controlHighlight);
		txtUsername.setBorder(null);
		txtUsername.setBounds(88, 160, 126, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setEnabled(false);
		txtPassword.setEditable(false);
		txtPassword.setText("Password:");
		txtPassword.setSelectedTextColor(Color.LIGHT_GRAY);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtPassword.setColumns(10);
		txtPassword.setBorder(null);
		txtPassword.setBackground(SystemColor.controlHighlight);
		txtPassword.setBounds(88, 262, 126, 30);
		contentPane.add(txtPassword);

	}
	public JLabel getJLabel() {
		return lbl_user ;
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
