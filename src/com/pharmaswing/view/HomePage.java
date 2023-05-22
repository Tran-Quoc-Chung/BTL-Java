package com.pharmaswing.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import com.pharmaswing.DAO.BillDAO;
import com.pharmaswing.DAO.CustomerDAO;
import com.pharmaswing.DAO.HomePageDAO;
import com.pharmaswing.DAO.MenuDAO;
import com.pharmaswing.DAO.historyloginDAO;
import com.pharmaswing.model.Bill;
import com.pharmaswing.model.Database;
import com.pharmaswing.model.DetailBill;
import com.pharmaswing.model.Drug;
import com.pharmaswing.model.GetInfoUser;
import com.pharmaswing.model.HistoryLogin;
import com.pharmaswing.model.TransactionTEMP;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JTextField txf_totalMenu;
	private JTextField txf_voucher;
	private JTextField txf_totalBill;
	private JTextField txf_nameDrugs;
	private JTextField txf_searchDrugs;
	private JTextField txf_priceDrugs;
	private JButton btn_voucher;
	private String priceDrugs;
	private int selectedId;
	private int idDrugs;
	private int spinValue;
	private JLabel lbl_user;
	private JComboBox<String> cbx_pharma;
	private Boolean toogleVoucher = true;
	private JLabel lbl_dateTime;
	private JToolBar toolBar;
	private JButton btn_toolbar_manage_Drugs, btn_toolbar_manage_User, btn_toolbar_Support, btn_toolbar_exit,
			btn_toolbar_LogOut, btn_toolbar_history, btn_toolbar_manage_Voucher;
	private Boolean checkUseJToolBar = true;
	private JTextField txf_points;
	private String customerPhonenumber;
	private String customerName;
	private String idBill;
	private List<TransactionTEMP> TransactionTEMPs = new ArrayList<>();

	Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
	BillDAO billDAO = new BillDAO(con);
	MenuDAO menuDAO = new MenuDAO(con);
	PropertiesDesign propertiesDesign = new PropertiesDesign();
	GetInfoUser getInfoUser = GetInfoUser.getInstance();
	HistoryLogin historyLogin = HistoryLogin.getInstance();
	DecimalFormat df = new DecimalFormat("#.##");
	HomePageDAO homePageDAO = new HomePageDAO();
	CustomerDAO customerDAO = new CustomerDAO();
	Bill BillModel = new Bill();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
					frame.setAlwaysOnTop(true);

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
		setBounds(100, 100, 1342, 761);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));

		ImageIcon logo = new ImageIcon("path/to/PharmaSwing/src/image/logo.jpg");

		contentPane.setName("");
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(propertiesDesign.getTableMenu());

		lbl_user = new JLabel();
		lbl_user.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_user.setBounds(790, 698, 127, 26);
		contentPane.add(lbl_user);

		JButton btnNewButton_3 = new JButton("Thanh toán");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (propertiesDesign.getTableMenu().getRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "Không thể thanh toán giao dịch rỗng!!");
					return;
				} else {
					int dialog = JOptionPane.showConfirmDialog(null, "Thanh toán giao dịch này?", "Thông báo",
							JOptionPane.YES_NO_OPTION);
					if (dialog == JOptionPane.YES_OPTION) {
						Double totalBill = Double.valueOf(txf_totalBill.getText());
						int voucher = Integer.valueOf(txf_voucher.getText());
						int points = Integer.valueOf(txf_points.getText());
						String cashier = lbl_user.getText();
						String customer;
						if (customerName == null) {
							customer = "Khách lẻ";
						} else {
							customer = customerName;
						}
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Date dateTime = null;
						try {
							dateTime = format.parse(lbl_dateTime.getText());
						} catch (ParseException ex) {
							ex.printStackTrace();
						}

						Bill bill = new Bill(0, totalBill, cashier, customer, dateTime, voucher, points,customerPhonenumber);
						List<DetailBill> detailBills = new ArrayList<>();
						DefaultTableModel model = (DefaultTableModel) propertiesDesign.getTableMenu().getModel();
						for (int i = 0; i < model.getRowCount(); i++) {
							int idDrugs = Integer.parseInt(model.getValueAt(i, 0).toString());
							int quantity = Integer.parseInt(model.getValueAt(i, 2).toString());
							String name = model.getValueAt(i, 1).toString();
							double total = Double.parseDouble(model.getValueAt(i, 3).toString());
							DetailBill detailBill = new DetailBill(0, 0, idDrugs, quantity, name, total);
							detailBills.add(detailBill);
						}

						JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
						int outputBill = JOptionPane.showConfirmDialog(null, "Xuất hóa đơn giao dịch?", "Thông báo",
								JOptionPane.YES_NO_OPTION);
						if (outputBill == JOptionPane.YES_OPTION) {
							exportBill();
						}
						
						try {
							
							billDAO.addBill(bill, detailBills);
							customerPhonenumber=null;
							customerName=null;
							TransactionTEMP transactionTEMP= new TransactionTEMP(dateTime,historyLogin.getIdBill(), (int)bill.getTotal());
							TransactionTEMPs.add(transactionTEMP);
							menuDAO.deleteAllMenu();
							model.setRowCount(0);
							txf_points.setText("0");
							txf_voucher.setText("0");
							toogleVoucher = true;
							btn_voucher.setText("Thêm voucher");
							totalMenu();
							totalBill();
							
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Thanh toán thất bại, lỗi: " + e2.getMessage());
						}

					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Dialog", Font.BOLD, 17));
		btnNewButton_3.setBackground(new Color(245, 245, 245));
		btnNewButton_3.setBounds(1176, 602, 142, 86);
		btnNewButton_3.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));

		contentPane.add(btnNewButton_3);

		txf_totalMenu = new JTextField();
		txf_totalMenu.setSelectedTextColor(UIManager.getColor("Button.disabledShadow"));
		txf_totalMenu.setCaretColor(UIManager.getColor("Button.disabledShadow"));
		txf_totalMenu.setForeground(UIManager.getColor("Button.foreground"));
		txf_totalMenu.setDisabledTextColor(UIManager.getColor("Button.disabledShadow"));
		txf_totalMenu.setBackground(new Color(224, 255, 255));
		txf_totalMenu.setBorder(null);
		txf_totalMenu.setEditable(false);
		txf_totalMenu.setFont(new Font("Cambria", Font.PLAIN, 24));
		txf_totalMenu.setBounds(943, 441, 161, 37);
		contentPane.add(txf_totalMenu);
		txf_totalMenu.setColumns(10);

		txf_voucher = new JTextField();
		txf_voucher.setCaretColor(UIManager.getColor("Button.disabledShadow"));
		txf_voucher.setForeground(UIManager.getColor("Button.foreground"));
		txf_voucher.setDisabledTextColor(UIManager.getColor("Button.disabledShadow"));
		txf_voucher.setBackground(new Color(224, 255, 255));
		txf_voucher.setBorder(null);
		txf_voucher.setText("0");
		txf_voucher.setFont(new Font("Cambria", Font.PLAIN, 24));
		txf_voucher.setEditable(false);
		txf_voucher.setColumns(10);
		txf_voucher.setBounds(943, 496, 161, 37);
		contentPane.add(txf_voucher);

		txf_totalBill = new JTextField();
		txf_totalBill.setCaretColor(UIManager.getColor("Button.disabledShadow"));
		txf_totalBill.setForeground(UIManager.getColor("Button.foreground"));
		txf_totalBill.setDisabledTextColor(UIManager.getColor("Button.disabledShadow"));
		txf_totalBill.setBackground(new Color(224, 255, 255));
		txf_totalBill.setBorder(null);
		txf_totalBill.setFont(new Font("Calibri", Font.PLAIN, 24));
		txf_totalBill.setText("0");
		txf_totalBill.setEditable(false);
		txf_totalBill.setBounds(943, 601, 161, 37);
		contentPane.add(txf_totalBill);
		txf_totalBill.setColumns(10);

		btn_voucher = new JButton("Voucher");
		btn_voucher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_voucher.setBackground(new Color(255, 240, 245));
		btn_voucher.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				toogleVoucher = !toogleVoucher;
				int total = Integer.parseInt(txf_totalMenu.getText());
				if (!toogleVoucher) {
					String vouchercode = JOptionPane.showInputDialog("Nhập voucher giảm giá");
					try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing",
							"root", "123456");
							java.sql.Statement stmStatement = con.createStatement();
							ResultSet resultSet = stmStatement
									.executeQuery("SELECT * FROM voucher WHERE codevoucher = '" + vouchercode
											+ "' AND startdate <= NOW() AND enddate >= NOW()")) {
						if (resultSet.next()) {
							float discount = resultSet.getFloat("discount");
							int totalMenu = Integer.parseInt(txf_totalMenu.getText());
							propertiesDesign.setVoucher(discount);
							checkVoucherValue(txf_totalMenu, discount);
							btn_voucher.setText("Hủy voucher");
							txf_totalBill.setText(String.valueOf((total - Integer.parseInt(txf_voucher.getText()))));

						} else {
							JOptionPane.showMessageDialog(null,
									"Voucher không tồn tại hoặc không trong thời gian sử dụng. Vui lòng thử lại!!");
							toogleVoucher = true;
						}
						totalBill();

					} catch (Exception e2) {
						e2.printStackTrace();
					}

				} else {
					checkVoucherValue(txf_totalMenu, 0);
					btn_voucher.setText("Thêm voucher");
					JOptionPane.showMessageDialog(null, "Hủy voucher thành công");
					txf_totalBill.setText(String.valueOf((total - Integer.parseInt(txf_voucher.getText()))));
					totalBill();

				}
			}
		});
		btn_voucher.setFont(new Font("Dialog", Font.BOLD, 17));
		btn_voucher.setBounds(1176, 490, 142, 47);
		btn_voucher.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		contentPane.add(btn_voucher);

		JButton btn_cancelMenu = new JButton("Hủy giao dịch");
		btn_cancelMenu.setBackground(new Color(240, 255, 255));
		btn_cancelMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_cancelMenu.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		btn_cancelMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (propertiesDesign.getTableMenu().getRowCount() > 0) {
					int dialogCancle = JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy giao dịch này",
							"Hủy giao dịch", JOptionPane.YES_NO_CANCEL_OPTION);
					if (dialogCancle == JOptionPane.YES_OPTION) {
						int points = Integer.parseInt(txf_points.getText());
						try {
							menuDAO.deleteAllMenu();
							Login.showDialog("Đang hủy giao dịch, vui lòng chờ...", 1500);
							homePageDAO.loadDataTableMenu(propertiesDesign.getTableMenu());
							totalMenu();
							customerDAO.updatePointsCustomer(points, customerPhonenumber);
							txf_voucher.setText("0");
							txf_points.setText("0");
							Login.showDialog("Hủy thành công", 1000);
							toogleVoucher = true;
							btn_voucher.setText("Thêm voucher");
							totalBill();

						} catch (Exception e2) {
							e2.printStackTrace();
						}

					}

				} else {
					JOptionPane.showConfirmDialog(null, "Không thể hủy giao dịch", "Lỗi", JOptionPane.CANCEL_OPTION);
				}

			}
		});
		btn_cancelMenu.setFont(new Font("Dialog", Font.BOLD, 17));
		btn_cancelMenu.setBounds(687, 646, 479, 42);
		contentPane.add(btn_cancelMenu);

		txf_nameDrugs = new JTextField();
		txf_nameDrugs.setEditable(false);
		txf_nameDrugs.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txf_nameDrugs.setBounds(270, 551, 348, 42);
		txf_nameDrugs.setColumns(10);
		contentPane.add(txf_nameDrugs);

		// Tạo DefaultTableModel để lưu trữ dữ liệu cho JTable
		String[] columnNames = { "Code", "Name", "Price" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		propertiesDesign.getTableDrugsList().setModel(model);

		txf_searchDrugs = new JTextField();
		txf_searchDrugs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String keyword = txf_searchDrugs.getText();
				model.setRowCount(0);
				List<Drug> drugs = Database.searchDrugs(keyword);
				updateTable(drugs);

			}
		});
		txf_searchDrugs.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txf_searchDrugs.setText("");
			}

			public void focusLost(FocusEvent e) {
				txf_searchDrugs.setText("Tìm kiếm");
			}
		});
		txf_searchDrugs.setToolTipText("\r\n");
		txf_searchDrugs.setText("Tìm kiếm");
		txf_searchDrugs.setBorder(null);
		txf_searchDrugs.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txf_searchDrugs.setBackground(new Color(224, 255, 255));
		txf_searchDrugs.setBounds(386, 79, 216, 26);
		contentPane.add(txf_searchDrugs);
		txf_searchDrugs.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(373, 115, 227, 2);
		contentPane.add(separator);

	}

	private void updateTable(List<Drug> drugs) {
		DefaultTableModel model = (DefaultTableModel) propertiesDesign.getTableDrugsList().getModel();
		model.setRowCount(0);
		for (Drug drug : drugs) {
			model.addRow(new Object[] { drug.getCode(), drug.getName(), drug.getPrice() });
		}
	}

	public HomePage() throws SQLException {
		setResizable(false);
		setBackground(UIManager.getColor("Button.highlight"));

		setFont(new Font("Dialog", Font.PLAIN, 17));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\logoApp.png"));
		declaredAllDesign();
		lbl_user.setText(getInfoUser.getstaffname());

		cbx_pharma = new JComboBox();
		cbx_pharma.setBackground(new Color(224, 255, 255));
		cbx_pharma.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbx_pharma.setBounds(99, 32, 236, 35);
		contentPane.add(cbx_pharma);

		JComboBox cbx_drug = new JComboBox();
		cbx_drug.setBackground(new Color(224, 255, 255));
		cbx_drug.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbx_drug.setBounds(99, 79, 236, 35);
		contentPane.add(cbx_drug);
		contentPane.add(propertiesDesign.getTableDrugsList());

		JScrollPane listDrugScrollPane = new JScrollPane();
		listDrugScrollPane.setBackground(new Color(224, 255, 255));
		listDrugScrollPane.setBounds(99, 126, 519, 396);
		listDrugScrollPane.setViewportView(propertiesDesign.getTableDrugsList());
		propertiesDesign.getTableDrugsList().setFillsViewportHeight(true);
		contentPane.add(listDrugScrollPane);

		JScrollPane listMenuScrollPane = new JScrollPane();
		listMenuScrollPane.setBackground(new Color(224, 255, 255));
		listMenuScrollPane.setBounds(687, 32, 631, 393);
		listMenuScrollPane.setViewportView(propertiesDesign.getTableMenu());
		propertiesDesign.getTableMenu().setFillsViewportHeight(true);
		contentPane.add(listMenuScrollPane);

		JButton btn_deleteDrugs = new JButton("Hủy hàng");
		btn_deleteDrugs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_deleteDrugs.setBackground(new Color(255, 240, 245));
		btn_deleteDrugs.setEnabled(false);
		btn_deleteDrugs.setFont(new Font("Dialog", Font.BOLD, 17));
		btn_deleteDrugs.setBounds(1176, 435, 142, 47);
		btn_deleteDrugs.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		contentPane.add(btn_deleteDrugs);
		cbx_pharma.addItem("");

		homePageDAO.addToCombobox(cbx_pharma, "SELECT * FROM pharma WHERE pharma.invisible=0", null);

		// Đoạn mã bắt sự kiện khi chọn cbx_pharma
		cbx_pharma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cbx_drug.removeAllItems();
				String selectedPharma = cbx_pharma.getSelectedItem().toString();

				if (!selectedPharma.isEmpty()) {
					String query = "SELECT pharmalv2.name FROM pharmalv2 "
							+ "INNER JOIN pharma ON pharmalv2.idpk = pharma.id "
							+ "WHERE pharma.name = ? AND pharmalv2.invisible=0";
					homePageDAO.addToCombobox(cbx_drug, query, selectedPharma);
					getData();
				}
			}

		});

		cbx_drug.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedDrug = cbx_drug.getSelectedItem().toString();
				if (!selectedDrug.isEmpty()) {
					String query = "SELECT code, name, price FROM drugslist "
							+ "WHERE idpk = (SELECT id FROM pharmalv2 WHERE name = ?) AND invisible=0";
					homePageDAO.loadDataTableDrugsList(propertiesDesign.getTableDrugsList(), query, selectedDrug);
					setWidthColumnTable(propertiesDesign.getTableDrugsList(), 50, 220, 60);
				}
			}
		});
		// table list drugs

		homePageDAO.loadDataTableDrugsList(propertiesDesign.getTableDrugsList(),
				"SELECT code, name, price FROM drugslist ", null);

		JSpinner spin_count = new JSpinner();
		spin_count.setForeground(new Color(224, 255, 255));
		spin_count.setBackground(new Color(224, 255, 255));
		spin_count.setBorder(null);
		spin_count.setFont(new Font("Tahoma", Font.PLAIN, 22));
		spin_count.setBounds(270, 606, 182, 41);
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
		btn_addToMenu.setBackground(new Color(255, 240, 245));
		btn_addToMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_addToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					homePageDAO.addToMenu(propertiesDesign.getTableMenu(), idDrugs, txf_nameDrugs.getText(),
							spin_count.getValue().toString(), txf_priceDrugs.getText());
					homePageDAO.loadDataTableMenu(propertiesDesign.getTableMenu());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				totalMenu();
				setWidthColumnTable(propertiesDesign.getTableMenu(), 30, 220, 30);
				int totalMenu = Integer.parseInt(txf_totalMenu.getText());
				if (!toogleVoucher) {
					checkVoucherValue(txf_totalMenu, propertiesDesign.getVoucher());
				}
				totalBill();

			}
		});

		btn_addToMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_addToMenu.setBounds(476, 600, 142, 101);
		btn_addToMenu.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		contentPane.add(btn_addToMenu);

		txf_priceDrugs = new JTextField();
		txf_priceDrugs.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txf_priceDrugs.setBackground(new Color(224, 255, 255));
		txf_priceDrugs.setEditable(false);
		txf_priceDrugs.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txf_priceDrugs.setBounds(270, 660, 182, 41);
		contentPane.add(txf_priceDrugs);
		txf_priceDrugs.setColumns(10);

		lbl_dateTime = new JLabel("New label");
		lbl_dateTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_dateTime.setBounds(1126, 697, 192, 23);
		contentPane.add(lbl_dateTime);

		JLabel lblNewLabel = new JLabel("Nhân viên sử dụng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(632, 700, 148, 20);
		contentPane.add(lblNewLabel);

		toolBar = new JToolBar(null, JToolBar.VERTICAL);
		toolBar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toolBar.setAlignmentX(Component.CENTER_ALIGNMENT);
		toolBar.setFloatable(false);
		toolBar.setBackground(new Color(64, 224, 208));
		toolBar.setBounds(0, 0, 80, 691);
		contentPane.add(toolBar);
		toolBar.add(Box.createVerticalStrut(30));

		btn_toolbar_manage_Drugs = new JButton();
		btn_toolbar_manage_Drugs = createToolbarButton(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\logoApp.png",
				"Quản lí thuốc", 50, 50);
		toolBar.add(btn_toolbar_manage_Drugs);
		toolBar.add(Box.createVerticalStrut(30));
		btn_toolbar_manage_Drugs.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				if (getInfoUser.getPosition().equals("Quản lí")) {
					ManageDrug manageDrug = new ManageDrug();
					manageDrug.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập.");
				}

			}
		});

		btn_toolbar_manage_User = new JButton();
		btn_toolbar_manage_User = createToolbarButton(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\nguoidung.png",
				"Quản lí người dùng", 50, 50);
		toolBar.add(btn_toolbar_manage_User);
		toolBar.add(Box.createVerticalStrut(30));

		btn_toolbar_manage_User.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (getInfoUser.getPosition().equals("Quản lí")) {
					ManageStaff manageStaff = new ManageStaff();
					manageStaff.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập.");
				}
			}
		});

		btn_toolbar_manage_Voucher = new JButton();
		btn_toolbar_manage_Voucher = createToolbarButton(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\voucher.png",
				"Quản lí voucher", 50, 50);
		toolBar.add(btn_toolbar_manage_Voucher);
		toolBar.add(Box.createVerticalStrut(30));

		btn_toolbar_manage_Voucher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (getInfoUser.getPosition().equals("Quản lí")) {
					ManageVoucher manageVoucher = new ManageVoucher();
					manageVoucher.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập.");
				}
			}
		});

		btn_toolbar_history = new JButton();
		btn_toolbar_history = createToolbarButton(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\history2.png",
				"Lịch sử giao dịch", 50, 50);
		toolBar.add(btn_toolbar_history);
		toolBar.add(Box.createVerticalStrut(30));
		btn_toolbar_history.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SalesStore salesStore = new SalesStore();
				salesStore.setVisible(true);
				dispose();

			}
		});

		btn_toolbar_LogOut = new JButton();
		btn_toolbar_LogOut = createToolbarButton(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\dangxuat.png",
				"Đăng xuất ứng dụng", 50, 50);
		toolBar.add(btn_toolbar_LogOut);
		toolBar.add(Box.createVerticalStrut(30));
		btn_toolbar_LogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					historyloginDAO historylogin = new historyloginDAO();
					historylogin.addData(TransactionTEMPs);
					TransactionTEMPs.clear();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Lỗi: "+e2.getMessage());
				}
				
				
			}
		});

		btn_toolbar_exit = new JButton();
		btn_toolbar_exit = createToolbarButton(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\Thoatchuongtrinh.png",
				"Thoát chương trình", 50, 50);
		toolBar.add(btn_toolbar_exit);
		toolBar.add(Box.createVerticalStrut(30));

		btn_toolbar_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogCancle = JOptionPane.showConfirmDialog(null, "Đóng ứng dụng", "Xác nhận",
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (dialogCancle == JOptionPane.YES_OPTION) {
					try {
						historyloginDAO historylogin = new historyloginDAO();
						historylogin.addData(TransactionTEMPs);
						TransactionTEMPs.clear();
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Lỗi: "+e2.getMessage());
					}
					dispose();
				}
				
			}
		});

		btn_toolbar_Support = new JButton();
		btn_toolbar_Support = createToolbarButton(
				"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\help.png", "Trợ giúp ", 50,
				50);
		toolBar.add(btn_toolbar_Support);
		toolBar.add(Box.createVerticalStrut(30));

		btn_toolbar_LogOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				Login login = new Login();
				login.setVisible(true);

			}
		});
		JButton btn_useJToolBar = new JButton("<<<");
		btn_useJToolBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkUseJToolBar) {
					toolBar.setBounds(0, 0, 0, 0);
					btn_useJToolBar.setText(">>>");

				} else {
					toolBar.setBounds(0, 0, 73, 691);
					btn_useJToolBar.setText("<<<");

				}
				checkUseJToolBar = !checkUseJToolBar;

			}
		});
		btn_useJToolBar.setBorder(null);
		btn_useJToolBar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_useJToolBar.setBounds(0, 688, 80, 26);
		contentPane.add(btn_useJToolBar);

		txf_points = new JTextField();
		txf_points.setCaretColor(UIManager.getColor("Button.disabledShadow"));
		txf_points.setForeground(UIManager.getColor("Button.foreground"));
		txf_points.setDisabledTextColor(UIManager.getColor("Button.disabledShadow"));
		txf_points.setBackground(new Color(224, 255, 255));
		txf_points.setBorder(null);
		txf_points.setText("0");
		txf_points.setFont(new Font("Cambria", Font.PLAIN, 24));
		txf_points.setEditable(false);
		txf_points.setColumns(10);
		txf_points.setBounds(943, 549, 161, 37);
		contentPane.add(txf_points);

		JLabel lblNewLabel_1 = new JLabel("Tên thuốc:");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(99, 557, 113, 35);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Số lượng:");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblNewLabel_1_1.setBounds(99, 618, 113, 35);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Thành tiền:");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblNewLabel_1_2.setBounds(99, 670, 113, 35);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Tổng thanh toán:");
		lblNewLabel_1_3.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblNewLabel_1_3.setBounds(690, 446, 168, 35);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Voucher:");
		lblNewLabel_1_4.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblNewLabel_1_4.setBounds(693, 505, 113, 35);
		contentPane.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Trừ tích điểm:");
		lblNewLabel_1_5.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblNewLabel_1_5.setBounds(690, 555, 155, 35);
		contentPane.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("Còn lại:");
		lblNewLabel_1_6.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblNewLabel_1_6.setBounds(693, 602, 113, 35);
		contentPane.add(lblNewLabel_1_6);

		JLabel lblIdnv = new JLabel("IDNV:");
		lblIdnv.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdnv.setBounds(917, 700, 64, 20);
		contentPane.add(lblIdnv);

		JLabel lbl_idstaff = new JLabel("ID");
		lbl_idstaff.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_idstaff.setBounds(991, 700, 64, 20);
		lbl_idstaff.setText(String.valueOf(getInfoUser.getIdstaff()));
		contentPane.add(lbl_idstaff);

		JButton btn_points = new JButton("Tích điểm");
		btn_points.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_points.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String totalString = txf_totalBill.getText();
				BigDecimal totalBillDecimal = new BigDecimal(totalString);
				int totalBill = totalBillDecimal.intValue();
				Customer customer = new Customer(HomePage.this);
				customer.setPoints(totalBill);
				customer.setVisible(true);
			}
		});
		btn_points.setFont(new Font("Dialog", Font.BOLD, 17));
		btn_points.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
		btn_points.setBackground(new Color(255, 240, 245));
		btn_points.setBounds(1176, 545, 142, 47);
		contentPane.add(btn_points);

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
							Login.showDialog("Hủy hàng thành công!!", 1000);
							btn_deleteDrugs.setEnabled(false);
							homePageDAO.loadDataTableMenu(propertiesDesign.getTableMenu());
						} else {
							JOptionPane.showMessageDialog(null, "Không thể hủy hàng này!");
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				totalMenu();
				totalBill();
				if (!toogleVoucher) {
					checkVoucherValue(txf_totalMenu, propertiesDesign.getVoucher());
				}
				setWidthColumnTable(propertiesDesign.getTableMenu(), 30, 220, 30);

			}
		});
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
				String dateTime = dateFormat.format(calendar.getTime());
				lbl_dateTime.setText(dateTime);
			}
		});
		timer.start();
		totalBill();

	}

	void setWidthColumnTable(JTable table, int x1, int x2, int x3) {
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(x1);
		columnModel.getColumn(1).setPreferredWidth(x2);
		columnModel.getColumn(2).setPreferredWidth(x3);
	}

	Double checkVoucherValue(JTextField jTextField, double voucher) {
		// Cập nhật JtextField Voucher
		String total = jTextField.getText();
		double a = voucher * Integer.parseInt(total);
		txf_voucher.setText(df.format(a));
		return voucher;
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

	public void totalBill() {
		double txfmenu = Double.valueOf(txf_totalMenu.getText());
		double txfvoucher = Double.valueOf(txf_voucher.getText());
		double txfpoints = Double.valueOf(txf_points.getText());
		double totalbill = txfmenu - txfvoucher - txfpoints;
		txf_totalBill.setText(String.valueOf(totalbill));
	}

	public void getData() {
		String selectedPharma = cbx_pharma.getSelectedItem().toString();
		if (selectedPharma != null && !selectedPharma.isEmpty()) {
			String sql = "SELECT drugslist.code, drugslist.name, drugslist.price FROM drugslist "
					+ "INNER JOIN pharmalv2 ON drugslist.idpk = pharmalv2.id "
					+ "INNER JOIN pharma ON pharmalv2.idpk = pharma.id " + "WHERE pharma.name = ?";
			homePageDAO.loadDataTableDrugsList(propertiesDesign.getTableDrugsList(), sql, selectedPharma);
		}
	}

	private JButton createToolbarButton(String iconPath, String tooltip, int sizeWidth, int sizeHeight) {
		JButton button = new JButton();

		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setAlignmentX(JButton.CENTER_ALIGNMENT);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBackground(new Color(64, 224, 208));
		button.setBorder(null);
		button.setBorderPainted(false);

		try {
			Image img = ImageIO.read(new File(iconPath));
			Image scaledImg = img.getScaledInstance(sizeWidth, sizeHeight, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaledImg);
			button.setIcon(icon);
			button.setToolTipText(tooltip);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return button;
	}

	public void updatePoints(int points, String phoneNumber) {
		this.customerPhonenumber = phoneNumber;
		txf_points.setText(String.valueOf(points));
		totalBill();
	}

	public void getCustomerName(String customerName,String phoneNumber) {
		this.customerName = customerName;
		this.customerPhonenumber = phoneNumber;
	}

	public void exportBill() {
		String customer;
		if (customerName == null) {
			customer = "Khách lẻ";
		} else {
			customer = customerName;
		}
		try {
			com.pharmaswing.view.Bill bill2 = new com.pharmaswing.view.Bill();

			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
			String sql = "SELECT idBill FROM bill ORDER BY idbill DESC LIMIT 1 ";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("idBill");
				idBill = String.valueOf(id);
			}
			bill2.setLocation(1071, 0);
			bill2.loadDataBill(idBill);
			bill2.fillBill(idBill, customer, lbl_user.getText(), lbl_dateTime.getText(), txf_totalMenu.getText(),
					txf_voucher.getText(), txf_points.getText(), txf_totalBill.getText());
			bill2.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Thanh toán thất bại, lỗi: " + e.getMessage());
		}
	}
	public void setValueToTransactionDetail(int id_bill,int total_Bill, Date datetime) {
		TransactionTEMP transactionTEMP= new TransactionTEMP(datetime, id_bill, total_Bill);
		transactionTEMP.addTransactionList(transactionTEMP);
		
	}
}	
