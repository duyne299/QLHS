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
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnData.formhome;
import ConnData.hocsinh;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.event.AncestorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.AncestorEvent;

public class ThongKeHS extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtTimKiem;
	private JTable bang2;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private static int rowCount;

	/**
	 * Launch the application.
	 */
	public void HienThiTT(List<hocsinh> hocsinhlist) {
		List<hocsinh> listhocsinh = new ArrayList<>();
		listhocsinh = hocsinhlist;
		DefaultTableModel tableModel;
		bang2.getModel();
		tableModel = (DefaultTableModel) bang2.getModel();
		tableModel.setRowCount(0);
		listhocsinh.forEach((hocsinh) -> {
			String GioiTinh;
			if (hocsinh.getGioiTinh() == 0) {
				GioiTinh = "Nam";

			} else {
				GioiTinh = "Nữ";
			}

			tableModel.addRow(new Object[] { hocsinh.getMaHS(), hocsinh.getHoTen(), GioiTinh, hocsinh.getNgaySinh(),
					hocsinh.getSDT(), hocsinh.getDiaChi(), hocsinh.getMaLop(), hocsinh.getGhiChu() });

		});

	}

	private void confirmExit() {
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn thoát không ?", "Exit ",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			System.exit(0); // Thoát chương trình nếu người dùng chọn "Yes"
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeHS frame = new ThongKeHS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void CapNhatSL() {
		String query = "SELECT COUNT(*) AS row_count FROM hocsinh";
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

	/**
	 * Create the frame.
	 */
	public ThongKeHS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1156, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm kiếm yêu cầu :");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(182, 87, 159, 28);
		contentPane.add(lblNewLabel);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(192, 125, 194, 35);
		contentPane.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 353, 1011, 189);
		contentPane.add(scrollPane);

		bang2 = new JTable();

		bang2.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 H\u1ECDc Sinh", "H\u1ECD v\u00E0 T\u00EAn", "Gi\u1EDBi T\u00EDnh",
						"Ng\u00E0y Sinh", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "\u0110\u1ECBa Ch\u1EC9",
						"M\u00E3 L\u1EDBp H\u1ECDc", "Ghi Ch\u00FA" }));
		bang2.getColumnModel().getColumn(0).setMinWidth(37);
		bang2.getColumnModel().getColumn(1).setPreferredWidth(109);
		bang2.getColumnModel().getColumn(1).setMinWidth(47);
		bang2.getColumnModel().getColumn(2).setPreferredWidth(59);
		bang2.getColumnModel().getColumn(3).setPreferredWidth(90);
		bang2.getColumnModel().getColumn(4).setPreferredWidth(84);
		bang2.getColumnModel().getColumn(4).setMinWidth(27);
		bang2.getColumnModel().getColumn(5).setPreferredWidth(82);
		bang2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		scrollPane.setViewportView(bang2);
		HienThiTT(ConnJDBC.HienThi());

		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formhome fh = new formhome();
				fh.setVisible(true);
				setVisible(false);
			}
		});
		btnQuayLi.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnQuayLi.setBounds(691, 201, 136, 41);
		contentPane.add(btnQuayLi);

		JButton btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});
		btnThot.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnThot.setBounds(881, 201, 136, 41);
		contentPane.add(btnThot);

		JRadioButton rdbMa = new JRadioButton("Mã Học Sinh");
		buttonGroup.add(rdbMa);
		rdbMa.setFont(new Font("SansSerif", Font.BOLD, 16));
		rdbMa.setBounds(685, 125, 167, 28);
		contentPane.add(rdbMa);

		JRadioButton rdbTen = new JRadioButton("Tên Học Sinh");
		buttonGroup.add(rdbTen);
		rdbTen.setFont(new Font("SansSerif", Font.BOLD, 16));
		rdbTen.setBounds(881, 125, 167, 28);
		contentPane.add(rdbTen);

		JRadioButton rdbAll = new JRadioButton("Tất cả học sinh");
		buttonGroup.add(rdbAll);
		rdbAll.setSelected(true);
		rdbAll.setFont(new Font("SansSerif", Font.BOLD, 16));
		rdbAll.setBounds(452, 125, 167, 28);
		contentPane.add(rdbAll);

		JButton btnTmKim = new JButton("Tìm Kiếm");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hocsinh hs = new hocsinh();

				if (rdbAll.isSelected()) {
					HienThiTT(ConnJDBC.HienThi());
				} else if (rdbMa.isSelected()) {
					if (txtTimKiem.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Không được để trống ");
					} else {
						hs.setMaHS(Integer.parseInt(txtTimKiem.getText()));
						HienThiTT(ConnJDBC.TimHS(hs));
					}

				} else {
					if (txtTimKiem.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Không được để trống ");
					} else {
						hs.setHoTen(txtTimKiem.getText());
						HienThiTT(ConnJDBC.TimTenHS(hs));

					}

				}
			}
		});
		btnTmKim.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTmKim.setBounds(466, 201, 136, 41);
		contentPane.add(btnTmKim);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(30, 230, 45, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 218, 45, 13);
		contentPane.add(lblNewLabel_3);

		CapNhatSL();
		JLabel lblNewLabel_2 = new JLabel("Tổng số học sinh "+rowCount);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(30, 315, 159, 28);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ThongKeHS.class.getResource("/img/TKDS.jpg")));
		lblNewLabel_4.setBounds(-11, -13, 1163, 731);
		contentPane.add(lblNewLabel_4);

	}
}
