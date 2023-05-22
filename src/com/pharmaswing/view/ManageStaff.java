package com.pharmaswing.view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pharmaswing.DAO.ManageStaffDAO;
import com.pharmaswing.model.DBConnection;
import com.pharmaswing.model.Staff;
public class ManageStaff extends JFrame   {

	private JPanel contentPane;
	private JTextField txf_username;
	private JTextField txf_password;
	private JTextField txf_namestaff;
	private JTextField txf_idstaff;
	 JTable tableStaff= new JTable();
	 JComboBox<String> cbx_position;
	 boolean createNewStaff=false;

	/**
	 * Launch the application.
	 */
	Connection connection = DBConnection.getConnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageStaff frame = new ManageStaff();
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
	public ManageStaff() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\nguoidung.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 634);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chx_active = new JCheckBox("Hoạt động");
		chx_active.setBackground(new Color(255, 240, 245));
		chx_active.setFont(new Font("Tahoma", Font.PLAIN, 21));
		chx_active.setBounds(570, 545, 159, 33);
		contentPane.add(chx_active);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.LIGHT_GRAY);
		scrollPane.setBounds(10, 42, 992, 324);
		tableStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableStaff.rowAtPoint(e.getPoint());
		        int id = Integer.parseInt(tableStaff.getValueAt(row, 0).toString());

		        try {
		            ManageStaffDAO manageStaffDAO = new ManageStaffDAO();
		            Staff staff = manageStaffDAO.getStaffById(id);

		            if (staff != null) {
		                txf_idstaff.setText(Integer.toString(staff.getId()));
		                txf_namestaff.setText(staff.getStaffname());
		                txf_username.setText(staff.getUsername());
		                txf_password.setText(staff.getPassword());
		                
		                if (staff.getPosition().equals("Quản lí")) {
		                    cbx_position.setSelectedItem("Quản lí");
		                } else if (staff.getPosition().equals("Nhân viên")) {
		                    cbx_position.setSelectedItem("Nhân viên");
		                }
		                if(staff.isActive()==true) {
		                	chx_active.setSelected(true);
		                }else {
		                	chx_active.setSelected(false);
		                }
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
			}
		});
		tableStaff.setFont(new Font("Tahoma", Font.PLAIN, 19));
		scrollPane.add(tableStaff);
		scrollPane.setViewportView(tableStaff);
		tableStaff.setFillsViewportHeight(true);
		tableStaff.setSelectionBackground(new Color(240, 240, 240));
		contentPane.add(scrollPane);
		
		txf_username = new JTextField();
		txf_username.setBackground(new Color(255, 240, 245));
		txf_username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txf_username.setBounds(164, 416, 176, 35);
		contentPane.add(txf_username);
		txf_username.setColumns(10);
		
		txf_password = new JTextField();
		txf_password.setBackground(new Color(255, 240, 245));
		txf_password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txf_password.setColumns(10);
		txf_password.setBounds(164, 483, 176, 35);
		contentPane.add(txf_password);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập:");
		lblNewLabel.setBackground(new Color(255, 240, 245));
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblNewLabel.setBounds(10, 422, 159, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setBackground(new Color(255, 240, 245));
		lblMtKhu.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblMtKhu.setBounds(10, 490, 159, 35);
		contentPane.add(lblMtKhu);
		
		JLabel lblChcV = new JLabel("Chức vụ:");
		lblChcV.setBackground(new Color(255, 240, 245));
		lblChcV.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblChcV.setBounds(10, 548, 159, 35);
		contentPane.add(lblChcV);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên:");
		lblTnNhnVin.setBackground(new Color(255, 240, 245));
		lblTnNhnVin.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblTnNhnVin.setBounds(410, 420, 159, 35);
		contentPane.add(lblTnNhnVin);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên:");
		lblMNhnVin.setBackground(new Color(255, 240, 245));
		lblMNhnVin.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblMNhnVin.setBounds(410, 487, 159, 35);
		contentPane.add(lblMNhnVin);
		
		txf_namestaff = new JTextField();
		txf_namestaff.setBackground(new Color(255, 240, 245));
		txf_namestaff.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txf_namestaff.setColumns(10);
		txf_namestaff.setBounds(570, 416, 159, 35);
		contentPane.add(txf_namestaff);
		
		txf_idstaff = new JTextField();
		txf_idstaff.setEnabled(false);
		txf_idstaff.setEditable(false);
		txf_idstaff.setBackground(new Color(255, 240, 245));
		txf_idstaff.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txf_idstaff.setColumns(10);
		txf_idstaff.setBounds(570, 485, 159, 35);
		contentPane.add(txf_idstaff);
		
		JLabel lblTrngThi = new JLabel("Trạng thái:");
		lblTrngThi.setBackground(new Color(255, 240, 245));
		lblTrngThi.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblTrngThi.setBounds(410, 547, 159, 35);
		contentPane.add(lblTrngThi);
		
		cbx_position = new JComboBox<String>();
		cbx_position.setBackground(new Color(255, 240, 245));
		cbx_position.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbx_position.addItem("Quản lí");
		cbx_position.addItem("Nhân viên");
		cbx_position.setBounds(164, 541, 176, 35);
		contentPane.add(cbx_position);
		
		JButton btnUpdate = new JButton("Lưu thay đổi");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setBackground(new Color(255, 240, 245));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				if(createNewStaff==false) {
					 try {
				            int row = tableStaff.getSelectedRow();
				            int id = Integer.parseInt(tableStaff.getValueAt(row, 0).toString());
				            String name = txf_namestaff.getText();
				            String position = cbx_position.getSelectedItem().toString();
				            String username = txf_username.getText();
				            String password = txf_password.getText();
				            boolean active = chx_active.isSelected();

				            ManageStaffDAO manageStaffDAO = new ManageStaffDAO();
				            manageStaffDAO.updateStaff(id, name, position, username, password, active);
				            manageStaffDAO.loadTableData(tableStaff);
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
				}else {
					 	try {
					 		String name = txf_namestaff.getText();
				            String position = cbx_position.getSelectedItem().toString();
				            String username = txf_username.getText();
				            String password = txf_password.getText();
				            ManageStaffDAO manageStaffDAO = new ManageStaffDAO();
				            manageStaffDAO.addNewUser(name, position, username, password);
				            manageStaffDAO.loadTableData(tableStaff);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
			            
			            
				}
		
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(815, 463, 187, 62);
		btnUpdate.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		contentPane.add(btnUpdate);
		
		JLabel lbl_updateUser = new JLabel("+ Thêm người dùng ");
		lbl_updateUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(createNewStaff==false) {
					createNewStaff=!createNewStaff;
					btnUpdate.setText("+ Tạo người dùng");
					lbl_updateUser.setText("Chỉnh sửa người dùng");
					txf_idstaff.setEditable(false);
					chx_active.setEnabled(false);
					clearAllJtextField();
					
				}else {
					createNewStaff=!createNewStaff;
					btnUpdate.setText("Lưu thay đổi");
					lbl_updateUser.setText("Thêm người dùng mới");
					txf_idstaff.setEditable(true);
					chx_active.setEnabled(true);
					clearAllJtextField();
				}
			}
		});
		lbl_updateUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_updateUser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lbl_updateUser.setBounds(815, 376, 187, 23);
		contentPane.add(lbl_updateUser);
		
		JButton btnNewButton = new JButton("<<Quay lại");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(0, 0, 139, 32);
		contentPane.add(btnNewButton);
		
	
		showDataTable();
		
		
		
	}
	public void showDataTable() {
		try {
			ManageStaffDAO manageStaffDAO = new ManageStaffDAO();
			manageStaffDAO.loadTableData(tableStaff);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	void clearAllJtextField() {
		JTextField[] jTextField = {txf_idstaff,txf_namestaff,txf_password,txf_username};
		for (JTextField textField : jTextField) {
		    textField.setText("");
		}
	}
}
