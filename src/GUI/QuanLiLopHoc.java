package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnData.formhome;
import ConnData.lophoc;
import ConnData.monhoc;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class QuanLiLopHoc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMalop;
	private JTextField txtTenlop;
	private JTextField txtLichhoc;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiLopHoc frame = new QuanLiLopHoc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void HienThiTT(List<lophoc>lophoclist) {
		List<lophoc>listlophoc=new ArrayList<>();
		listlophoc=lophoclist;
		DefaultTableModel tableModel;
		table.getModel();
		
		tableModel=(DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		listlophoc.forEach((monhoc)->{
			
			tableModel.addRow(new Object[] {
					monhoc.getMaLop(),monhoc.getTenLop(),
					monhoc.getLichHoc()
			});
			
		});
	}
	private void confirmExit() {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Bạn có thực sự muốn thoát không ?",
            "Exit ",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0); // Thoát chương trình nếu người dùng chọn "Yes"
        }
	}
	/**
	 * Create the frame.
	 */
	public QuanLiLopHoc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1141, 598);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Lớp Học");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel.setBounds(82, 103, 125, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnLpHc = new JLabel("Tên Lớp Học");
		lblTnLpHc.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTnLpHc.setBounds(82, 201, 125, 34);
		contentPane.add(lblTnLpHc);
		
		JLabel lblLchHc = new JLabel("Lịch Học");
		lblLchHc.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblLchHc.setBounds(82, 291, 125, 34);
		contentPane.add(lblLchHc);
		
		txtMalop = new JTextField();
		txtMalop.setBounds(199, 100, 152, 40);
		contentPane.add(txtMalop);
		txtMalop.setColumns(10);
		
		txtTenlop = new JTextField();
		txtTenlop.setColumns(10);
		txtTenlop.setBounds(199, 201, 152, 40);
		contentPane.add(txtTenlop);
		
		txtLichhoc = new JTextField();
		txtLichhoc.setColumns(10);
		txtLichhoc.setBounds(199, 291, 152, 40);
		contentPane.add(txtLichhoc);
		
		JButton btnNewButton = new JButton("Thêm ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMalop.getText().equals("")||txtTenlop.getText().equals("")||txtLichhoc.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane,"Các mục không được để trống !" );
				}
				else {
					lophoc mh=new lophoc();
					mh.setMaLop(Integer.parseInt(txtMalop.getText()));
					mh.setTenLop(txtTenlop.getText());
					mh.setLichHoc(txtLichhoc.getText());
					ConnLH.ThemLH(mh);
					HienThiTT(ConnLH.HienThi());
				}
				
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton.setBounds(10, 385, 119, 40);
		contentPane.add(btnNewButton);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMalop.getText().equals("")||txtTenlop.getText().equals("")||txtLichhoc.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane,"Các mục không được để trống !" );
				}
				else {
					lophoc mh=new lophoc();
					mh.setMaLop(Integer.parseInt(txtMalop.getText()));
					mh.setTenLop(txtTenlop.getText());
					mh.setLichHoc(txtLichhoc.getText());
					ConnLH.Update(mh);
					HienThiTT(ConnLH.HienThi());
					
				}
				
			}
		});
		btnSa.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSa.setBounds(170, 385, 119, 40);
		contentPane.add(btnSa);
		
		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formhome fh=new formhome();
				fh.setVisible(true);
				setVisible(false);
			}
		});
		btnQuayLi.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnQuayLi.setBounds(338, 385, 119, 40);
		contentPane.add(btnQuayLi);
		
		JButton btnNewButton_2_1 = new JButton("Xóa");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lophoc mh=new lophoc();
				mh.setMaLop(Integer.parseInt(txtMalop.getText()));
				ConnLH.XoaLH(mh);
				HienThiTT(ConnLH.HienThi());
			}
			
		});
		btnNewButton_2_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_2_1.setBounds(409, 100, 119, 40);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Làm Mới");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMalop.setText("");
				txtTenlop.setText("");
				txtLichhoc.setText("");
				HienThiTT(ConnLH.HienThi());
				
			}
		});
		btnNewButton_2_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_2_2.setBounds(409, 201, 119, 40);
		contentPane.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_3 = new JButton("Tìm Kiếm");
		btnNewButton_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lophoc lh=new lophoc();
				lh.setMaLop(Integer.parseInt(txtMalop.getText()));	
				HienThiTT(ConnLH.TimLH(lh));
			}
		});
		btnNewButton_2_3.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_2_3.setBounds(409, 291, 119, 40);
		contentPane.add(btnNewButton_2_3);
		
		JButton btnNewButton_2_4 = new JButton("Thoát");
		btnNewButton_2_4.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_2_4.setBounds(533, 385, 119, 40);
		contentPane.add(btnNewButton_2_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(582, 93, 545, 183);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"M\u00E3 L\u1EDBp H\u1ECDc", "T\u00EAn L\u1EDBp H\u1ECDc", "L\u1ECBch H\u1ECDc"
			}
		));
		table.getColumnModel().getColumn(1).setMinWidth(27);
		table.getColumnModel().getColumn(2).setMinWidth(31);
		table.setFont(new Font("SansSerif", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(QuanLiLopHoc.class.getResource("/img/QLHS.jpg")));
		lblNewLabel_1.setBounds(0, 0, 1161, 733);
		contentPane.add(lblNewLabel_1);
		HienThiTT(ConnLH.HienThi());
	}
}
