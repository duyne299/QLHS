package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnData.monhoc;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import ConnData.formhome;
import javax.swing.ImageIcon;
public class ThongKeMH extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTim;
	private JTable table;
	private static int rowCount;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeMH frame = new ThongKeMH();
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
	public static void CapNhatSLMH() {
		String query="SELECT COUNT(*) AS row_count FROM monhoc";
		try {
			Connection conn=ConnJDBC.getConnection();
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			 if (rs.next()) {
	                rowCount = rs.getInt("row_count");
	               
	            }
			
		}
		catch(Exception ex) {
			
		}
	}
	public ThongKeMH() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 974, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTim = new JTextField();
		txtTim.setBounds(62, 174, 177, 39);
		contentPane.add(txtTim);
		txtTim.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm theo yêu cầu :");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(62, 134, 177, 18);
		contentPane.add(lblNewLabel);
		
		JRadioButton rdbAllMH = new JRadioButton("Tất cả môn học");
		rdbAllMH.setSelected(true);
		buttonGroup.add(rdbAllMH);
		rdbAllMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbAllMH.setBounds(298, 134, 157, 21);
		contentPane.add(rdbAllMH);
		
		JRadioButton rdbMaMH = new JRadioButton("Mã Môn Học");
		buttonGroup.add(rdbMaMH);
		rdbMaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbMaMH.setBounds(298, 183, 157, 21);
		contentPane.add(rdbMaMH);
		
		JRadioButton rdbTenMH = new JRadioButton("Tên Môn Học");
		buttonGroup.add(rdbTenMH);
		rdbTenMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbTenMH.setBounds(298, 236, 157, 21);
		contentPane.add(rdbTenMH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(499, 73, 465, 189);
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
			},
			new String[] {
				"M\u00E3 M\u00F4n H\u1ECDc", "T\u00EAn M\u00F4n H\u1ECDc", "S\u1ED1 Ti\u1EBFt H\u1ECDc"
			}
		));
		scrollPane.setViewportView(table);
		HienThiTT(ConnMH.HienThi());
		
		JButton btnNewButton = new JButton("Tìm Kiếm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monhoc mh=new monhoc();
				if(rdbAllMH.isSelected()) {
					HienThiTT(ConnMH.HienThi());		
				}
				else if(rdbMaMH.isSelected()) {
					if(txtTim.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Không được bỏ trống ô tìm kiếm");
						
					}
					else {
						mh.setMaMH(Integer.parseInt(txtTim.getText()));	
						HienThiTT(ConnMH.TimMH(mh));												
					}					
				}
				else {
					if(txtTim.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Không được bỏ trống ô tìm kiếm");
						
					}
					else {
						mh.setTenMH(txtTim.getText());	
						HienThiTT(ConnMH.TimTenMH(mh));												
					}	
					
				}
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton.setBounds(22, 356, 135, 49);
		contentPane.add(btnNewButton);
		
		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formhome fh=new formhome();
				fh.setVisible(true);
				setVisible(false);
			}
		});
		btnQuayLi.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnQuayLi.setBounds(407, 356, 135, 49);
		contentPane.add(btnQuayLi);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});
		btnThot.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnThot.setBounds(574, 356, 135, 49);
		contentPane.add(btnThot);
		CapNhatSLMH();
		JLabel lblTngSMn = new JLabel("Tổng số môn học :"+ rowCount);
		lblTngSMn.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 13));
		lblTngSMn.setBounds(497, 45, 177, 18);
		contentPane.add(lblTngSMn);
		
		JButton btnLmMi = new JButton("Làm Mới");
		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTim.setText("");
				HienThiTT(ConnMH.HienThi());
			}
		});
		btnLmMi.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnLmMi.setBounds(216, 356, 135, 49);
		contentPane.add(btnLmMi);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(55, 500, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ThongKeMH.class.getResource("/img/CNMH.jpg")));
		lblNewLabel_2.setBounds(10, -19, 1019, 735);
		contentPane.add(lblNewLabel_2);
		
	}
}
