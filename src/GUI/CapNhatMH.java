package GUI;

import java.awt.EventQueue;
import ConnData.monhoc;

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
import ConnData.hocsinh;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CapNhatMH extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtmaMH;
	private JTextField txtTenMH;
	private JTextField txtSotiet;
	private JButton btnQuayLi;
	private JButton btnLmMi;
	private JButton btnXa;
	private JButton btnSa;
	private JButton btnThot;
	private JTable table;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapNhatMH frame = new CapNhatMH();
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
	public void HienThiTT(List<monhoc>monhoclist) {
		List<monhoc>listmonhoc=new ArrayList<>();
		listmonhoc=monhoclist;
		DefaultTableModel tableModel;
		table.getModel();
		
		tableModel=(DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		listmonhoc.forEach((monhoc)->{
			
			tableModel.addRow(new Object[] {
					monhoc.getMaMH(),monhoc.getTenMH(),
					monhoc.getSoTiet()
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
	public CapNhatMH() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Môn Học ");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel.setBounds(69, 164, 102, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnMnHc = new JLabel("Tên Môn Học");
		lblTnMnHc.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTnMnHc.setBounds(69, 241, 102, 25);
		contentPane.add(lblTnMnHc);
		
		JLabel lblSTitHc = new JLabel("Số Tiết Học");
		lblSTitHc.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSTitHc.setBounds(69, 327, 102, 25);
		contentPane.add(lblSTitHc);
		
		txtmaMH = new JTextField();
		txtmaMH.setBounds(194, 160, 148, 37);
		contentPane.add(txtmaMH);
		txtmaMH.setColumns(10);
		
		txtTenMH = new JTextField();
		txtTenMH.setColumns(10);
		txtTenMH.setBounds(194, 237, 148, 37);
		contentPane.add(txtTenMH);
		
		txtSotiet = new JTextField();
		txtSotiet.setColumns(10);
		txtSotiet.setBounds(194, 323, 148, 37);
		contentPane.add(txtSotiet);
		
		JButton btnNewButton = new JButton("Thêm ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtmaMH.getText().equals("")||txtTenMH.getText().equals("")||txtSotiet.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane,"Các mục không được để trống !" );
				}
				else {
					monhoc mh=new monhoc();
					mh.setMaMH(Integer.parseInt(txtmaMH.getText()));
					mh.setTenMH(txtTenMH.getText());
					mh.setSoTiet(Integer.parseInt(txtSotiet.getText()));
					ConnMH.ThemMH(mh);
					HienThiTT(ConnMH.HienThi());
				}
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton.setBounds(98, 422, 109, 43);
		contentPane.add(btnNewButton);
		
		btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formhome fh=new formhome();
				fh.setVisible(true);
				setVisible(false);
			}
		});
		btnQuayLi.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnQuayLi.setBounds(248, 422, 109, 43);
		contentPane.add(btnQuayLi);
		
		btnLmMi = new JButton("Làm Mới");
		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtmaMH.setText("");
				txtTenMH.setText("");
				txtSotiet.setText("");
			}
		});
		btnLmMi.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnLmMi.setBounds(408, 318, 109, 43);
		contentPane.add(btnLmMi);
		
		btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monhoc mh=new monhoc();
				mh.setMaMH(Integer.parseInt(txtmaMH.getText()));
				ConnMH.XoaHS(mh);
				HienThiTT(ConnMH.HienThi());
			}
		});
		btnXa.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnXa.setBounds(408, 232, 109, 43);
		contentPane.add(btnXa);
		
		btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monhoc mh=new monhoc();
				mh.setMaMH(Integer.parseInt(txtmaMH.getText()));
				mh.setTenMH(txtTenMH.getText());
				mh.setSoTiet(Integer.parseInt(txtSotiet.getText()));
				ConnMH.Update(mh);
				HienThiTT(ConnMH.HienThi());
			}
		});
		btnSa.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSa.setBounds(408, 155, 109, 43);
		contentPane.add(btnSa);
		
		btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});
		btnThot.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnThot.setBounds(408, 422, 109, 43);
		contentPane.add(btnThot);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(527, 161, 484, 199);
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
			},
			new String[] {
				"M\u00E3 M\u00F4n H\u1ECDc", "T\u00EAn M\u00F4n H\u1ECDc", "S\u1ED1 Ti\u1EBFt H\u1ECDc"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(71);
		table.getColumnModel().getColumn(0).setMinWidth(37);
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(CapNhatMH.class.getResource("/img/CNMH.jpg")));
		lblNewLabel_1.setBounds(0, 0, 1011, 706);
		contentPane.add(lblNewLabel_1);
		HienThiTT(ConnMH.HienThi());
	}
}
