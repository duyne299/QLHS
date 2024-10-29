package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnData.formhome;
import ConnData.hocsinh;
import ConnData.phieudiem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Danhsachdiem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTim;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static int rowCount;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Danhsachdiem frame = new Danhsachdiem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void HienThiTT(List<phieudiem> pdlist) {
		
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel(); // Get the table model
		tableModel.setRowCount(0); // Clear the existing rows

		pdlist.forEach(pd -> {
			tableModel.addRow(new Object[] { pd.getMaPhieu(), pd.getMaMH(), pd.getMaHS(), pd.getMaLop(),
					pd.getDiemHK1(), pd.getDiemHK2(), pd.TinhDiemTB(), pd.XepLoai() });
		});
	}

	public static void CapNhatSL() {
		String query = "SELECT COUNT(*) AS row_count FROM phieudiem";
		try {
			Connection conn = ConnJDBC.getConnection();
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				rowCount = rs.getInt("row_count");

			}

		} catch (Exception ex) {

		}
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
	public Danhsachdiem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1107, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm kiếm theo yêu cầu :");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(136, 197, 198, 42);
		contentPane.add(lblNewLabel);

		txtTim = new JTextField();
		txtTim.setBounds(165, 246, 198, 42);
		contentPane.add(txtTim);
		txtTim.setColumns(10);

		JRadioButton rdbAll = new JRadioButton("Tất cả phiếu điểm");
		rdbAll.setSelected(true);
		buttonGroup.add(rdbAll);
		rdbAll.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbAll.setBounds(414, 256, 149, 18);
		contentPane.add(rdbAll);

		JRadioButton rdbMa = new JRadioButton("Mã Phiếu Điểm");
		buttonGroup.add(rdbMa);
		rdbMa.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbMa.setBounds(607, 256, 149, 18);
		contentPane.add(rdbMa);

		JRadioButton rdbDiem = new JRadioButton("Điểm Trung Bình > 5");
		buttonGroup.add(rdbDiem);
		rdbDiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbDiem.setBounds(758, 231, 173, 18);
		contentPane.add(rdbDiem);
		
		JRadioButton rdbTBK = new JRadioButton("Điểm Trung Bình < 5");
		buttonGroup.add(rdbTBK);
		rdbTBK.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbTBK.setBounds(758, 274, 173, 18);
		contentPane.add(rdbTBK);

		JButton btnNewButton = new JButton("Quay Lại");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formhome fh=new formhome();
				fh.setVisible(true);
				setVisible(false);
			
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnNewButton.setBounds(457, 322, 139, 42);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Tìm Kiếm");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phieudiem pd = new phieudiem();

				if (rdbAll.isSelected()) {
					HienThiTT(ConnDiem.HienThi());
				} else if (rdbMa.isSelected()) {
					if (txtTim.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Không được để trống ");
					}
					else {
						pd.setMaPhieu(Integer.parseInt(txtTim.getText()));
						HienThiTT(ConnDiem.TraMa(pd));
					}

				}
				else if(rdbTBK.isSelected()) {
					HienThiTT(ConnDiem.TBK(pd));
				}
				
				
				else  {
					
						
						HienThiTT(ConnDiem.TraDiem(pd));

					
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnNewButton_1.setBounds(269, 322, 139, 42);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Thoát");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnNewButton_2.setBounds(641, 322, 139, 42);
		contentPane.add(btnNewButton_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 417, 841, 198);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, "", null, null, null },
				{ null, null, null, null, "", null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 Phi\u1EBFu", "M\u00E3 M\u00F4n H\u1ECDc", "M\u00E3 H\u1ECDc Sinh",
						"M\u00E3 L\u1EDBp", "\u0110i\u1EC3m HK1", "\u0110i\u1EC3m HK2", "\u0110i\u1EC3m TB",
						"X\u1EBFp Lo\u1EA1i" }));
		scrollPane.setViewportView(table);
		CapNhatSL();
		JLabel lblTngSPhiu = new JLabel("Tổng số phiếu điểm hiện có : "+rowCount);
		lblTngSPhiu.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lblTngSPhiu.setBounds(10, 379, 236, 42);
		contentPane.add(lblTngSPhiu);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Danhsachdiem.class.getResource("/img/DSD.jpg")));
		lblNewLabel_1.setBounds(0, 0, 1102, 670);
		contentPane.add(lblNewLabel_1);
		
		
		HienThiTT(ConnDiem.HienThi());
		
	}
}
