package GUI;

import java.awt.EventQueue;
import ConnData.formhome;
import ConnData.hocsinh;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CapNhatHS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtdate;
	private JTextField txtphone;
	private JTextField txtdiachi;
	private JTextField txtmalop;
	private JTextField txtnote;
	public static  JTable bang1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapNhatHS frame = new CapNhatHS();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void HienThiTT(List<hocsinh>hocsinhlist) {
		List<hocsinh>listhocsinh=new ArrayList<>();
		listhocsinh=hocsinhlist;
		DefaultTableModel tableModel;
		bang1.getModel();
		tableModel=(DefaultTableModel)bang1.getModel();
		tableModel.setRowCount(0);
		listhocsinh.forEach((hocsinh)->{
			String GioiTinh;
			if(hocsinh.getGioiTinh()==0) {
				GioiTinh="Nam";
				
			}
			else {
				GioiTinh="Nữ";
			}
			
			
			tableModel.addRow(new Object[] {
					hocsinh.getMaHS(),hocsinh.getHoTen(),GioiTinh,
					hocsinh.getNgaySinh(),hocsinh.getSDT(),hocsinh.getDiaChi(),hocsinh.getMaLop(),hocsinh.getGhiChu()
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
	public CapNhatHS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1166, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Học Sinh");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 131, 126, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel txttx = new JLabel("Họ và Tên");
		txttx.setFont(new Font("SansSerif", Font.BOLD, 14));
		txttx.setBounds(10, 209, 126, 43);
		contentPane.add(txttx);
		
		JLabel txtxtx = new JLabel("Giới Tính");
		txtxtx.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtxtx.setBounds(10, 283, 126, 43);
		contentPane.add(txtxtx);
		
		JLabel lblNewLabel_1_3 = new JLabel("Ngày Sinh");
		lblNewLabel_1_3.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(10, 371, 126, 43);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Số điện thoại");
		lblNewLabel_1_4.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(272, 131, 126, 43);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Địa Chỉ");
		lblNewLabel_1_5.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(272, 209, 126, 43);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel cxcscs = new JLabel("Mã Lớp Học");
		cxcscs.setFont(new Font("SansSerif", Font.BOLD, 14));
		cxcscs.setBounds(272, 283, 126, 43);
		contentPane.add(cxcscs);
		
		JLabel lblNewLabel_1_7 = new JLabel("Ghi Chú");
		lblNewLabel_1_7.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1_7.setBounds(272, 371, 126, 43);
		contentPane.add(lblNewLabel_1_7);
		
		txtid = new JTextField();
		txtid.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtid.setBounds(113, 136, 126, 33);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtname = new JTextField();
		txtname.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtname.setColumns(10);
		txtname.setBounds(113, 214, 126, 33);
		contentPane.add(txtname);
		
		JComboBox cbbgender = new JComboBox();
		cbbgender.setFont(new Font("SansSerif", Font.BOLD, 14));
		cbbgender.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cbbgender.setBounds(113, 287, 96, 34);
		contentPane.add(cbbgender);
		
		txtdate = new JTextField();
		txtdate.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtdate.setColumns(10);
		txtdate.setBounds(113, 376, 126, 33);
		contentPane.add(txtdate);
		
		txtphone = new JTextField();
		txtphone.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtphone.setColumns(10);
		txtphone.setBounds(383, 136, 126, 33);
		contentPane.add(txtphone);
		
		txtdiachi = new JTextField();
		txtdiachi.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtdiachi.setColumns(10);
		txtdiachi.setBounds(383, 214, 126, 33);
		contentPane.add(txtdiachi);
		
		txtmalop = new JTextField();
		txtmalop.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtmalop.setColumns(10);
		txtmalop.setBounds(383, 288, 126, 33);
		contentPane.add(txtmalop);
		
		txtnote = new JTextField();
		txtnote.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtnote.setColumns(10);
		txtnote.setBounds(383, 376, 126, 33);
		contentPane.add(txtnote);
		
		JButton btnNewButton = new JButton("Thêm ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtid.getText().equals("")||txtname.getText().equals("")||
						txtphone.getText().equals("")||txtdiachi.getText().equals("")||
						txtmalop.getText().equals("")||txtnote.getText().equals("")
						) 
				{
					JOptionPane.showMessageDialog(contentPane,"Các mục không được để trống");
					
				}
				else {
					
					hocsinh hs=new hocsinh();
					hs.setMaHS(Integer.parseInt(txtid.getText()));
					hs.setHoTen(txtname.getText());
					hs.setGioiTinh(cbbgender.getSelectedIndex());
					hs.setNgaySinh(txtdate.getText());
					hs.setSDT(Integer.parseInt(txtphone.getText()));
					hs.setDiaChi(txtdiachi.getText());
					hs.setMaLop(Integer.parseInt(txtmalop.getText()));
					hs.setGhiChu(txtnote.getText());
					ConnJDBC.ThemHS(hs);
					HienThiTT(ConnJDBC.HienThi());
					
					
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton.setBounds(43, 448, 113, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sửa");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtid.getText().equals("")||txtname.getText().equals("")||
						txtphone.getText().equals("")||txtdiachi.getText().equals("")||
						txtmalop.getText().equals("")||txtnote.getText().equals("")
						) 
				{
					JOptionPane.showMessageDialog(contentPane,"Các mục không được để trống");
					
				}
				else {
					hocsinh hs=new hocsinh();
					hs.setMaHS(Integer.parseInt(txtid.getText()));
					hs.setHoTen(txtname.getText());
					hs.setGioiTinh(cbbgender.getSelectedIndex());
					hs.setNgaySinh(txtdate.getText());
					hs.setSDT(Integer.parseInt(txtphone.getText()));
					hs.setDiaChi(txtdiachi.getText());
					hs.setMaLop(Integer.parseInt(txtmalop.getText()));
					hs.setGhiChu(txtnote.getText());
					ConnJDBC.Update(hs);
					HienThiTT(ConnJDBC.HienThi());
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_1.setBounds(227, 448, 113, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hocsinh hs =new hocsinh();
				hs.setMaHS(Integer.parseInt(txtid.getText()));
				ConnJDBC.XoaHS(hs);
				HienThiTT(ConnJDBC.HienThi());
			
			}
		});
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_2.setBounds(400, 448, 113, 43);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Làm mới");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtid.setText("");
				txtname.setText("");
				txtdate.setText("");
				txtphone.setText("");
				txtdiachi.setText("");
				txtmalop.setText("");
				txtnote.setText("");
				
			}
		});
		btnNewButton_3.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_3.setBounds(595, 448, 113, 43);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Quay lại");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formhome fh =new formhome();
				fh.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_4.setBounds(791, 448, 113, 43);
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(516, 102, 668, 316);
		contentPane.add(scrollPane);
		
		bang1 = new JTable();
		bang1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 HS", "H\u1ECD v\u00E0 T\u00EAn", "Gi\u1EDBi T\u00EDnh", "Ng\u00E0y Sinh", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa Ch\u1EC9", "M\u00E3 L\u1EDBp ", "Ghi Ch\u00FA"
			}
		));
		bang1.getColumnModel().getColumn(0).setPreferredWidth(56);
		bang1.getColumnModel().getColumn(0).setMinWidth(30);
		bang1.getColumnModel().getColumn(1).setPreferredWidth(123);
		bang1.getColumnModel().getColumn(1).setMinWidth(100);
		bang1.getColumnModel().getColumn(2).setPreferredWidth(69);
		bang1.getColumnModel().getColumn(2).setMinWidth(30);
		bang1.getColumnModel().getColumn(3).setMinWidth(30);
		bang1.getColumnModel().getColumn(4).setPreferredWidth(92);
		bang1.getColumnModel().getColumn(4).setMinWidth(40);
		bang1.getColumnModel().getColumn(5).setPreferredWidth(85);
		bang1.getColumnModel().getColumn(5).setMinWidth(51);
		bang1.getColumnModel().getColumn(6).setPreferredWidth(60);
		bang1.getColumnModel().getColumn(6).setMinWidth(39);
		bang1.getColumnModel().getColumn(7).setPreferredWidth(98);
		bang1.getColumnModel().getColumn(7).setMinWidth(34);
		bang1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		scrollPane.setViewportView(bang1);
		
		JButton btnNewButton_3_1 = new JButton("Thoát");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExit();
				
			}
		});
		btnNewButton_3_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_3_1.setBounds(983, 448, 113, 43);
		contentPane.add(btnNewButton_3_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CapNhatHS.class.getResource("/img/Cập nhật danh sách học sinh.png")));
		lblNewLabel.setBounds(0, 0, 1158, 650);
		contentPane.add(lblNewLabel);
		HienThiTT(ConnJDBC.HienThi());
		
		
	}
}
