package ConnData;

import java.awt.EventQueue;
import GUI.CapNhatHS;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import GUI.ThongKeHS;
import GUI.ThongKeMH;
import GUI.CapNhatMH;
import GUI.QuanLiLopHoc;
import GUI.NhapDiem;
import GUI.Danhsachdiem;
import GUI.LienHe;

public class formhome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formhome frame = new formhome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
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
	public formhome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBounds(0, 0, 764, 22);
		
		JMenu mnNewMenu = new JMenu("Trang chủ");
		menuBar.add(mnNewMenu);
		
		JMenuItem DangXuat = new JMenuItem("Đăng Xuất");
		DangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formlogin flg=new formlogin();
				flg.setVisible(true);
				setVisible(false);
			}
		});
		mnNewMenu.add(DangXuat);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Thoát");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Học Sinh");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem CapNhatHS = new JMenuItem("Cập Nhật Học Sinh");
		CapNhatHS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CapNhatHS cnhs=new CapNhatHS();
				cnhs.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(CapNhatHS);
		
		JMenuItem ThongKeHS = new JMenuItem("Thống Kê Học Sinh");
		ThongKeHS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeHS TKHS=new ThongKeHS();
				TKHS.setVisible(true);
				setVisible(false);
			}
		});
		mnNewMenu_1.add(ThongKeHS);
		
		JMenu mnNewMenu_2 = new JMenu("Môn Học");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem CapNhatMH = new JMenuItem("Cập Nhật Môn Học");
		CapNhatMH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CapNhatMH fcn=new CapNhatMH();
				fcn.setVisible(true);
				setVisible(false);
			}
		});
		mnNewMenu_2.add(CapNhatMH);
		
		JMenuItem TSMH = new JMenuItem("Tổng Số Môn Học\r\n");
		TSMH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeMH tk=new ThongKeMH();
				tk.setVisible(true);
				setVisible(false);
			}
		});
		mnNewMenu_2.add(TSMH);
		
		JMenu mnNewMenu_5 = new JMenu("Lớp Học");
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmQunLLp = new JMenuItem("Quản Lí Lớp Học");
		mntmQunLLp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLiLopHoc ql=new QuanLiLopHoc();
				ql.setVisible(true);
				setVisible(false);
				
			}
		});
		mnNewMenu_5.add(mntmQunLLp);
		
		JMenu mnNewMenu_3 = new JMenu("Phiếu Điểm");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem NhapDiem = new JMenuItem("Nhập Điểm ");
		NhapDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhapDiem nd=new NhapDiem();
				nd.setVisible(true);
				setVisible(false);
			}
		});
		mnNewMenu_3.add(NhapDiem);
		
		JMenuItem DSDiem = new JMenuItem("Danh Sách Điểm ");
		DSDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Danhsachdiem ds=new Danhsachdiem();
				ds.setVisible(true);
				setVisible(false);
				
			}
		});
		mnNewMenu_3.add(DSDiem);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu_4 = new JMenu("Liên Hệ");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem infor = new JMenuItem("Thông Tin Liên Hệ");
		infor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LienHe lh=new LienHe();
				lh.setVisible(true);
				setVisible(false);
				
			}
		});
		mnNewMenu_4.add(infor);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(formhome.class.getResource("/img/quan-ly-hoc-sinh.png")));
		lblNewLabel.setBounds(0, 32, 764, 423);
		contentPane.add(lblNewLabel);
	}
}
