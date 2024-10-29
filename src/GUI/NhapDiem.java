package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnData.formhome;
import ConnData.phieudiem;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class NhapDiem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaphieu;
	private JTextField txtMaMH;
	private JTextField txtMaHS;
	private JTextField txtMaLH;
	private JTextField txtDiemHK1;
	private JTextField txtDiemHK2;
	private JTable table;

	/**
	 * Launch the application.
	 */
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhapDiem frame = new NhapDiem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void HienThiTT(List<phieudiem> pdlist) {
	    if (pdlist == null) return; // Check for null

	    DefaultTableModel tableModel = (DefaultTableModel) table.getModel(); // Get the table model
	    tableModel.setRowCount(0); // Clear the existing rows

	    pdlist.forEach(pd -> {
	        tableModel.addRow(new Object[] {
	            pd.getMaPhieu(), pd.getMaMH(), pd.getMaHS(),
	            pd.getMaLop(), pd.getDiemHK1(), pd.getDiemHK2(), pd.TinhDiemTB(), pd.XepLoai()
	        });
	    });
	}

	
	
	
	/**
	 * Create the frame.
	 */
	public NhapDiem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1143, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Phiếu Điểm");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel.setBounds(44, 118, 128, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblMMnHc = new JLabel("Mã Môn Học");
		lblMMnHc.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblMMnHc.setBounds(44, 192, 128, 43);
		contentPane.add(lblMMnHc);
		
		JLabel lblMHcSinh = new JLabel("Mã Học Sinh");
		lblMHcSinh.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblMHcSinh.setBounds(44, 272, 128, 43);
		contentPane.add(lblMHcSinh);
		
		JLabel lblMLpHc = new JLabel("Mã Lớp Học");
		lblMLpHc.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblMLpHc.setBounds(360, 118, 128, 43);
		contentPane.add(lblMLpHc);
		
		JLabel lblimHcK = new JLabel("Điểm Học Kỳ 1");
		lblimHcK.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblimHcK.setBounds(360, 192, 128, 43);
		contentPane.add(lblimHcK);
		
		JLabel lblimHcK_1 = new JLabel("Điểm Học Kỳ 2");
		lblimHcK_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblimHcK_1.setBounds(360, 272, 128, 43);
		contentPane.add(lblimHcK_1);
		
		txtMaphieu = new JTextField();
		txtMaphieu.setBounds(173, 125, 141, 33);
		contentPane.add(txtMaphieu);
		txtMaphieu.setColumns(10);
		
		txtMaMH = new JTextField();
		txtMaMH.setColumns(10);
		txtMaMH.setBounds(173, 199, 141, 33);
		contentPane.add(txtMaMH);
		
		txtMaHS = new JTextField();
		txtMaHS.setColumns(10);
		txtMaHS.setBounds(173, 279, 141, 33);
		contentPane.add(txtMaHS);
		
		txtMaLH = new JTextField();
		txtMaLH.setColumns(10);
		txtMaLH.setBounds(498, 125, 141, 33);
		contentPane.add(txtMaLH);
		
		txtDiemHK1 = new JTextField();
		txtDiemHK1.setColumns(10);
		txtDiemHK1.setBounds(498, 199, 141, 33);
		contentPane.add(txtDiemHK1);
		
		txtDiemHK2 = new JTextField();
		txtDiemHK2.setColumns(10);
		txtDiemHK2.setBounds(498, 279, 141, 33);
		contentPane.add(txtDiemHK2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 400, 895, 170);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"M\u00E3 Phi\u1EBFu", "M\u00E3 M\u00F4n", "M\u00E3 H\u1ECDc Sinh", "M\u00E3 L\u1EDBp", "\u0110i\u1EC3m HK1", "\u0110i\u1EC3m HK2", "\u0110i\u1EC3m TB", "X\u1EBFp Lo\u1EA1i"
			}
		));
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		HienThiTT(ConnDiem.HienThi());
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMaphieu.getText().equals("")||txtMaHS.getText().equals("")||
						txtMaMH.getText().equals("")||txtMaLH.getText().equals("")||
						txtDiemHK1.getText().equals("")||txtDiemHK2.getText().equals("")
						) 
				{
					JOptionPane.showMessageDialog(contentPane,"Các mục không được để trống");
					
				}
				else {
					phieudiem pd=new phieudiem();
					pd.setMaPhieu(Integer.parseInt(txtMaphieu.getText()));
					pd.setMaMH(Integer.parseInt(txtMaMH.getText()));
					pd.setMaHS(Integer.parseInt(txtMaHS.getText()));
					pd.setMaLop(Integer.parseInt(txtMaLH.getText()));
					pd.setDiemHK1(Double.parseDouble(txtDiemHK1.getText()));
					pd.setDiemHK2(Double.parseDouble(txtDiemHK2.getText()));
					pd.TinhDiemTB();
					pd.XepLoai();
					ConnDiem.ThemDiem(pd);
					HienThiTT(ConnDiem.HienThi());
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton.setBounds(729, 124, 103, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sửa");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMaphieu.getText().equals("")||txtMaHS.getText().equals("")||
						txtMaMH.getText().equals("")||txtMaLH.getText().equals("")||
						txtDiemHK1.getText().equals("")||txtDiemHK2.getText().equals("")
						) 
				{
					JOptionPane.showMessageDialog(contentPane,"Các mục không được để trống");
					
				}
				else {
					phieudiem pd=new phieudiem();
					pd.setMaPhieu(Integer.parseInt(txtMaphieu.getText()));
					pd.setMaMH(Integer.parseInt(txtMaphieu.getText()));
					pd.setMaHS(Integer.parseInt(txtMaphieu.getText()));
					pd.setMaLop(Integer.parseInt(txtMaphieu.getText()));
					pd.setDiemHK1(Double.parseDouble(txtDiemHK1.getText()));
					pd.setDiemHK2(Double.parseDouble(txtDiemHK2.getText()));
					ConnDiem.Update(pd);
					HienThiTT(ConnDiem.HienThi());
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_1.setBounds(729, 202, 103, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phieudiem pd=new phieudiem();
				pd.setMaPhieu(Integer.parseInt(txtMaphieu.getText()));
				ConnDiem.XoaPD(pd);
				HienThiTT(ConnDiem.HienThi());
			}
		});
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_2.setBounds(729, 278, 103, 30);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Quay Lại");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formhome fh =new formhome();
				fh.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_3.setBounds(914, 202, 103, 30);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Làm mới");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaphieu.setText("");
				txtMaMH.setText("");
				txtMaHS.setText("");
				txtMaLH.setText("");
				txtDiemHK1.setText("");
				txtDiemHK2.setText("");
				
			}
		});
		btnNewButton_4.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_4.setBounds(914, 124, 103, 30);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_3_1 = new JButton("Thoát");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});
		btnNewButton_3_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_3_1.setBounds(914, 278, 103, 30);
		contentPane.add(btnNewButton_3_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(NhapDiem.class.getResource("/img/NhapDiem.jpg")));
		lblNewLabel_1.setBounds(0, 0, 1129, 665);
		contentPane.add(lblNewLabel_1);
	}
}
