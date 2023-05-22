package com.pharmaswing.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class Bill extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lbl_billnumber;
	private JLabel lbl_customer;
	private JLabel lbl_cashier;
	private JLabel lbl_datetime;
	private JLabel lbl_totalmenu;
	private JLabel lbl_voucher;
	private JLabel lbl_points;
	private JLabel lbl_totalbill;
	private String billId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bill frame = new Bill();
					frame.setAlwaysOnTop(true);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					int x = screenSize.width - frame.getWidth();
					int y = 0;
					frame.setLocation(x, y);
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
	public Bill() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 465, 810);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cửa hàng thuốc tây");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 33));
		lblNewLabel.setBounds(168, 10, 290, 38);
		contentPane.add(lblNewLabel);

		JLabel lblPharmaSwing = new JLabel("PHARMA SWING");
		lblPharmaSwing.setFont(new Font("Calibri", Font.BOLD, 27));
		lblPharmaSwing.setBounds(214, 38, 190, 38);
		contentPane.add(lblPharmaSwing);

		JLabel lblNewLabel_1 = new JLabel("UTC2, Lê Văn Việt, Quận 9, TP HCM");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(204, 68, 254, 23);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Hotline: 034577888");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(312, 86, 136, 23);
		contentPane.add(lblNewLabel_2);

		JLabel lbl_icon = new JLabel();
		lbl_icon.setBounds(10, -13, 161, 141); // Set vị trí và kích thước ban đầu cho JLabel

		String imagePath = "C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\image\\logojava2.png";
		ImageIcon imageIcon = new ImageIcon(imagePath);

		java.awt.Image image = imageIcon.getImage(); // Lấy đối tượng Image từ ImageIcon
		int labelWidth = lbl_icon.getWidth(); // Lấy chiều rộng của JLabel
		int labelHeight = lbl_icon.getHeight(); // Lấy chiều cao của JLabel
		java.awt.Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, java.awt.Image.SCALE_SMOOTH); 

		ImageIcon scaledIcon = new ImageIcon(scaledImage); // Tạo đối tượng ImageIcon từ bản sao thu nhỏ của hình ảnh
		lbl_icon.setIcon(scaledIcon);

		// Thêm JLabel vào container
		contentPane.add(lbl_icon);

		JLabel lblHanThanh = new JLabel("HÓA ĐƠN THANH TOÁN");
		lblHanThanh.setFont(new Font("Calibri", Font.BOLD, 28));
		lblHanThanh.setBounds(90, 119, 288, 38);
		contentPane.add(lblHanThanh);

		JLabel lblNewLabel_2_1 = new JLabel("Mã HĐ:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(142, 151, 74, 23);
		contentPane.add(lblNewLabel_2_1);

		lbl_billnumber = new JLabel("Mã HĐ:");
		lbl_billnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_billnumber.setBounds(214, 151, 74, 23);
		contentPane.add(lbl_billnumber);

		JLabel lblNewLabel_2_1_2 = new JLabel("Khách hàng:");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_2.setBounds(10, 184, 97, 29);
		contentPane.add(lblNewLabel_2_1_2);

		lbl_customer = new JLabel("Khách hàng:");
		lbl_customer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_customer.setBounds(106, 184, 125, 29);
		contentPane.add(lbl_customer);

		JLabel lblNewLabel_2_1_2_2 = new JLabel("Thu ngân:");
		lblNewLabel_2_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_2_2.setBounds(263, 184, 81, 29);
		contentPane.add(lblNewLabel_2_1_2_2);

		lbl_cashier = new JLabel("Thu ngân:");
		lbl_cashier.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_cashier.setBounds(342, 184, 116, 29);
		contentPane.add(lbl_cashier);

		JLabel lblNewLabel_2_1_2_3 = new JLabel("Thời gian:");
		lblNewLabel_2_1_2_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_2_3.setBounds(10, 223, 86, 29);
		contentPane.add(lblNewLabel_2_1_2_3);

		lbl_datetime = new JLabel("Thời gian:");
		lbl_datetime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_datetime.setBounds(106, 223, 314, 29);
		contentPane.add(lbl_datetime);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(10, 253, 428, 328);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setRowHeight(40);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2_1_2_3_2 = new JLabel("Thành tiền:");
		lblNewLabel_2_1_2_3_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_2_3_2.setBounds(10, 591, 86, 29);
		contentPane.add(lblNewLabel_2_1_2_3_2);

		lbl_totalmenu = new JLabel("Thời gian:");
		lbl_totalmenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_totalmenu.setBounds(342, 591, 86, 29);
		contentPane.add(lbl_totalmenu);

		JLabel lblNewLabel_2_1_2_3_2_1 = new JLabel("Giảm giá voucher:");
		lblNewLabel_2_1_2_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_2_3_2_1.setBounds(10, 630, 147, 29);
		contentPane.add(lblNewLabel_2_1_2_3_2_1);

		JLabel lblNewLabel_2_1_2_3_2_1_1 = new JLabel("Trừ tích điểm:");
		lblNewLabel_2_1_2_3_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_2_3_2_1_1.setBounds(10, 669, 147, 29);
		contentPane.add(lblNewLabel_2_1_2_3_2_1_1);

		lbl_voucher = new JLabel("Giảm giá voucher:");
		lbl_voucher.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_voucher.setBounds(342, 630, 109, 29);
		contentPane.add(lbl_voucher);

		lbl_points = new JLabel("Trừ tích điểm:");
		lbl_points.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_points.setBounds(342, 669, 109, 29);
		contentPane.add(lbl_points);

		JLabel lblNewLabel_2_1_2_3_2_1_1_2 = new JLabel("Tổng cộng:");
		lblNewLabel_2_1_2_3_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_2_3_2_1_1_2.setBounds(10, 708, 147, 29);
		contentPane.add(lblNewLabel_2_1_2_3_2_1_1_2);

		lbl_totalbill = new JLabel("Tổng cộng:");
		lbl_totalbill.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_totalbill.setBounds(342, 708, 116, 29);
		contentPane.add(lbl_totalbill);

		JLabel lblNewLabel_2_1_2_3_2_1_1_2_1_1 = new JLabel("---Thank you so much---");
		lblNewLabel_2_1_2_3_2_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_2_3_2_1_1_2_1_1.setBounds(124, 745, 196, 29);
		contentPane.add(lblNewLabel_2_1_2_3_2_1_1_2_1_1);

		JButton btnNewButton = new JButton("Xuất");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Document document = new Document();
				JButton btn = (JButton) e.getSource();
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(btn);
				PdfWriter writer = null;
				try {
					// Lấy id của bill để tạo tên file pdf
					String id = lbl_billnumber.getText().trim();

					// Tạo một PdfWriter để ghi nội dung vào tài liệu PDF
					writer = PdfWriter.getInstance(document,
							new FileOutputStream(
									"C:\\Users\\Admin\\Desktop\\Code\\Java\\JavaSwing\\PharmaSwing\\src\\BillList\\bill"
											+ id + ".pdf"));

					// Mở tài liệu để bắt đầu viết nội dung
					document.open();

					// Lấy kích thước của JFrame
					int width = frame.getWidth();
					int height = frame.getHeight();

					// Tạo một PdfContentByte để vẽ nội dung của JFrame lên tài liệu PDF
					PdfContentByte canvas = writer.getDirectContent();

					// Tạo một đối tượng BufferedImage với kích thước của JFrame
					BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

					// Vẽ nội dung của JFrame lên đối tượng BufferedImage
					frame.paint(img.getGraphics());

					// Tạo một đối tượng Image từ đối tượng BufferedImage
					Image image = Image.getInstance(canvas, img, 1);

					// Thêm đối tượng Image vào tài liệu PDF
					document.add(image);

				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công");
					dispose();
					document.close();
				}
			}

		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(367, 747, 81, 27);
		contentPane.add(btnNewButton);

	}

	public void fillBill(String billNumber, String customer, String cashier, String Datetime, String totalMenu,
			String voucher, String points, String totalBill) {
		lbl_billnumber.setText(billNumber);
		lbl_customer.setText(customer);
		lbl_cashier.setText(cashier);
		lbl_datetime.setText(Datetime);
		lbl_totalmenu.setText(totalMenu);
		lbl_voucher.setText(voucher);
		lbl_points.setText(points);
		lbl_totalbill.setText(totalBill);

	}

	public void loadDataBill(String id) {
		int idBill = Integer.parseInt(String.valueOf(id));
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_swing", "root", "123456");
			String queryString = "SELECT namedrugs,quantityDrugs,totalDrugs FROM detailBill WHERE idfkDetailBill="
					+ idBill + "";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(queryString);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setColumnIdentifiers(new Object[] { "Tên thuốc", "Số lượng", "Tổng " });
			model.setRowCount(0);

			while (resultSet.next()) {
				Object[] row = new Object[3];
				row[0] = resultSet.getString("nameDrugs");
				row[1] = resultSet.getInt("quantityDrugs");
				row[2] = resultSet.getInt("totalDrugs");
				model.addRow(row);
			}
			resultSet.close();
			statement.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Không lấy được dữ liệu hóa đơn" + e.getMessage());
		}

	}
}
