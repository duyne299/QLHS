package ConnData;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class formlogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txttaikhoan;
	private JPasswordField txtmatkhau;
	
	static String url = "jdbc:mySQL://localhost:3306/qlhsthpt";
	static String user = "root";
	static String password = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formlogin frame = new formlogin();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static Connection getConnection() {
	    Connection connection = null;
	    try {
	        connection = DriverManager.getConnection(url, user, password);
	      
	    } catch (Exception ex) {
	        System.out.println("Kết nối thất bại");
	        ex.printStackTrace();
	    }
	    return connection;
	}


	/**
	 * Create the frame.
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
	public formlogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 981, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên Tài Khoản");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel.setBounds(63, 346, 99, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("Mật Khẩu");
		lblMtKhu.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblMtKhu.setBounds(66, 404, 84, 22);
		contentPane.add(lblMtKhu);
		
		txttaikhoan = new JTextField();
		txttaikhoan.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txttaikhoan.setBounds(172, 339, 179, 40);
		contentPane.add(txttaikhoan);
		txttaikhoan.setColumns(10);
		
		txtmatkhau = new JPasswordField();
		txtmatkhau.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtmatkhau.setBounds(169, 403, 182, 40);
		contentPane.add(txtmatkhau);
		
		JButton btnlogin = new JButton("Đăng Nhập");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try {
				Connection connection=getConnection();
				String query ="select * from TaiKhoan where TenTK=? and MatKhau=? ";
				PreparedStatement ps = connection.prepareCall(query);
				ps.setString(1,txttaikhoan.getText());
				ps.setString(2,txtmatkhau.getText());
				
				 ResultSet rs = ps.executeQuery();
				 if(txttaikhoan.getText().equals("")||txtmatkhau.getText().equals("")) {
					 JOptionPane.showMessageDialog(contentPane,"Tài khoản hoặc mật khẩu không được để trống ");
				 }
				 else if(rs.next()) {
					 formhome fc=new formhome();
					 fc.setVisible(true);
					 setVisible(false);
					 

					 JOptionPane.showMessageDialog(contentPane, "Đăng nhập thành công ");
					 
				 }
				 else {
					 JOptionPane.showMessageDialog(contentPane, "Tài khoản hoặc mật khẩu bị sai ");
					 
				 }
				 
				 
			
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
				
			}
				
				
			}
		});
		btnlogin.setSelectedIcon(null);
		btnlogin.setIcon(null);
		btnlogin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnlogin.setBounds(67, 460, 142, 46);
		contentPane.add(btnlogin);
		
		JButton btnExit = new JButton("Thoát");
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});
		btnExit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnExit.setBounds(241, 460, 132, 46);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(formlogin.class.getResource("/img/nenlogin2.png")));
		lblNewLabel_2.setBounds(-12, -53, 993, 580);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(formlogin.class.getResource("/img/nenlogin2(1)(1).png")));
		lblNewLabel_1.setBounds(508, 472, 459, 88);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setBounds(21, 57, 711, 88);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setIcon(new ImageIcon(formlogin.class.getResource("/img/nenlogin2(1)(1).png")));
		lblNewLabel_1_2.setBounds(62, 472, 512, 88);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("");
		lblNewLabel_1_2_1.setIcon(new ImageIcon(formlogin.class.getResource("/img/nenlogin2(1)(1).png")));
		lblNewLabel_1_2_1.setBounds(-75, 472, 512, 88);
		contentPane.add(lblNewLabel_1_2_1);
	}
}
