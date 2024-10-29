package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class LienHe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LienHe frame = new LienHe();
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
	public LienHe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1304, 723);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LienHe.class.getResource("/img/duy2.jpg")));
		lblNewLabel.setBounds(43, 28, 303, 338);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LienHe.class.getResource("/img/dung2.jpg")));
		lblNewLabel_1.setBounds(898, 24, 366, 344);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LienHe.class.getResource("/img/thuyn2.jpg")));
		lblNewLabel_2.setBounds(489, 28, 303, 338);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nguyễn Đức Duy\r\n");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_3.setBounds(91, 346, 230, 109);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("ig:duy_ne299");
		lblNewLabel_3_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_3_1.setBounds(101, 376, 230, 109);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Hoàng Thị Thuyên");
		lblNewLabel_3_2.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_3_2.setBounds(523, 364, 230, 109);
		contentPane.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Trần Minh Dũng");
		lblNewLabel_3_3.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_3_3.setBounds(1020, 346, 230, 109);
		contentPane.add(lblNewLabel_3_3);
	}

}
