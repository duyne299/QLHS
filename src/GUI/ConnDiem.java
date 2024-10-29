package GUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ConnData.hocsinh;
import ConnData.monhoc;
import ConnData.phieudiem;
public class ConnDiem {
	
	//Hàm Hiển thị môn học
		public static List<phieudiem> HienThi(){
			List<phieudiem>pdlist=new ArrayList<>();
			String query ="SELECT * FROM phieudiem";
			try {
				//Kết nối tới với CSDL
				Connection conn=ConnJDBC.getConnection();
				if(conn !=null) {
					java.sql.Statement stmt = conn.createStatement();
		            ResultSet rs = stmt.executeQuery(query);
		            
		            // Kiểm tra nếu có dữ liệu
		            if (!rs.isBeforeFirst()) {
		                System.out.println("Không có dữ liệu trong bảng monhoc");
		            }
		            // Lặp qua kết quả và thêm vào danh sách phiếu điểm
		            while (rs.next()) {
		            	phieudiem mh = new phieudiem(rs.getInt("MaPhieu"),
		                		rs.getInt("MaMH"),
		                		rs.getInt("MaHS"),
		                		rs.getInt("MaLop"),
		                		rs.getDouble("DiemHK1"),
		                		rs.getDouble("DiemHK2"),
		                		rs.getDouble("DiemTB"),
		                		rs.getString("XepLoai")
		                		
		                        );
		                pdlist.add(mh);
		            }
				}
	            
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			return pdlist;			
		}
		
		//Thêm môn học 
		public static void ThemDiem(phieudiem pd) {
			String query ="Insert into phieudiem(MaPhieu,MaMH,MaHS,MaLop,DiemHK1,DiemHK2,DiemTB,XepLoai) values(?,?,?,?,?,?,?,?)";
			try {
				Connection conn=ConnJDBC.getConnection();
				PreparedStatement ps=conn.prepareStatement(query);
				ps.setInt(1, pd.getMaPhieu());
				ps.setInt(2, pd.getMaMH());
				ps.setInt(3, pd.getMaHS());
				ps.setInt(4, pd.getMaLop());
				ps.setDouble(5, pd.getDiemHK1());
				ps.setDouble(6, pd.getDiemHK2());
				ps.setDouble(7, pd.TinhDiemTB());
				ps.setString(8, pd.XepLoai());
				ps.executeUpdate();
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}	
		//Xóa phiếu điểm 
		public static void XoaPD(phieudiem pd) {
			String query = "delete from phieudiem where MaPhieu='" + pd.getMaPhieu() + "'";
			try {
				Connection connection =ConnJDBC. getConnection();
				PreparedStatement psmt = connection.prepareStatement(query);
				psmt.executeUpdate();

			} catch (Exception ex) {

			}
		}
		
		//Sửa danh sách
				public static void Update(phieudiem pd) {
					String query ="Update phieudiem set MaPhieu=?, MaMH=?,MaHS=?,MaLop=?,DiemHK1=?,DiemHK2=? where MaPhieu='"+pd.getMaPhieu()+"'";
					try {
						Connection conn=ConnJDBC.getConnection();
						PreparedStatement ps=conn.prepareStatement(query);
						ps.setInt(1, pd.getMaPhieu());
						ps.setInt(2,pd.getMaMH());
						ps.setInt(3,pd.getMaHS());
						ps.setInt(4,pd.getMaLop());
						ps.setDouble(5,pd.getDiemHK1());
						ps.setDouble(6,pd.getDiemHK2());
						ps.executeUpdate();		
					}
					catch(Exception ex) {
						ex.printStackTrace();
					}
				}
				
				//tra cứu điểm
				public static List<phieudiem>TraDiem(phieudiem pd){
					List<phieudiem>listphieudiem=new ArrayList<>();
					String query="SELECT * FROM phieudiem WHERE DiemTB > 5";
					try {
						Connection conn=ConnJDBC.getConnection();
						java.sql.Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						while(rs.next()) {
							phieudiem st=new phieudiem(rs.getInt("MaPhieu"),rs.getInt("MaMH"),rs.getInt("MaHS"),rs.getInt("MaLop"),rs.getDouble("DiemHK1"),rs.getDouble("DiemHK2"),rs.getDouble("DiemTB"),rs.getString("XepLoai"));
							listphieudiem.add(st);
						}
						
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					return listphieudiem;
				}
				
				public static List<phieudiem>TBK(phieudiem pd){
					List<phieudiem>listphieudiem=new ArrayList<>();
					String query="SELECT * FROM phieudiem WHERE DiemTB < 5";
					try {
						Connection conn=ConnJDBC.getConnection();
						java.sql.Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						while(rs.next()) {
							phieudiem st=new phieudiem(rs.getInt("MaPhieu"),rs.getInt("MaMH"),rs.getInt("MaHS"),rs.getInt("MaLop"),rs.getDouble("DiemHK1"),rs.getDouble("DiemHK2"),rs.getDouble("DiemTB"),rs.getString("XepLoai"));
							listphieudiem.add(st);
						}
						
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					return listphieudiem;
				}
				
				//tra cứu theo mã phiếu
				public static List<phieudiem>TraMa(phieudiem pd){
					List<phieudiem>listphieudiem=new ArrayList<>();
					String query="Select * from phieudiem where phieudiem.MaPhieu='"+pd.getMaPhieu()+"'";
					try {
						Connection conn=ConnJDBC.getConnection();
						java.sql.Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						while(rs.next()) {
							phieudiem st=new phieudiem(rs.getInt("MaPhieu"),rs.getInt("MaMH"),rs.getInt("MaHS"),rs.getInt("MaLop"),rs.getDouble("DiemHK1"),rs.getDouble("DiemHK2"),rs.getDouble("DiemTB"),rs.getString("XepLoai"));
							listphieudiem.add(st);
						}
						
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					return listphieudiem;
				}
	public ConnDiem() {
		// TODO Auto-generated constructor stub
	}

}
