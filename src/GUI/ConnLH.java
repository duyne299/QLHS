package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ConnData.hocsinh;
import ConnData.lophoc;
import ConnData.monhoc;
import ConnData.phieudiem;

import ConnData.monhoc;

public class ConnLH {
	//Hàm Hiển thị môn học
		public static List<lophoc> HienThi(){
			List<lophoc>lhlist=new ArrayList<>();
			String query ="SELECT * FROM lophoc";
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
		            // Lặp qua kết quả và thêm vào danh sách lớp học
		            while (rs.next()) {
		            lophoc lh = new lophoc(rs.getInt("MaLop"),
		                		rs.getString("TenLop"),
		                		rs.getString("LichHoc")
		                        );
		                lhlist.add(lh);
		            }
				}
	            
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			return lhlist;
		}
//Thêm lớp học
		public static void ThemLH(lophoc lh) {
			String query ="Insert into lophoc(MaLop,TenLop,LichHoc) values(?,?,?)";
			try {
				Connection conn=ConnJDBC.getConnection();
				PreparedStatement ps=conn.prepareStatement(query);
				ps.setInt(1, lh.getMaLop());
				ps.setString(2, lh.getTenLop());
				ps.setString(3, lh.getLichHoc());
				ps.execute();
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}	
		//Xóa lớp học 
		public static void XoaLH(lophoc lh) {
			String query = "delete from lophoc where MaLop='" + lh.getMaLop() + "'";
			try {
				Connection connection =ConnJDBC. getConnection();
				PreparedStatement psmt = connection.prepareStatement(query);
				psmt.executeUpdate();

			} catch (Exception ex) {

			}
		}	
		
		//Sửa danh sách
				public static void Update(lophoc lh) {
					String query ="Update lophoc set TenLop=?,LichHoc=? where TenLop='"+lh.getTenLop()+"'";
					try {
						Connection conn=ConnJDBC.getConnection();
						PreparedStatement ps=conn.prepareStatement(query);
						ps.setString(1, lh.getTenLop());
						ps.setString(2,lh.getLichHoc());												
						ps.executeUpdate();											
					}
					catch(Exception ex) {
						ex.printStackTrace();
					}
				}
				
				//Tìm lớp học theo mã lớp học
				public static List<lophoc>TimLH(lophoc lh){
					List<lophoc>listlh=new ArrayList<>();
					String query="Select * from lophoc where lophoc.MaLop='"+lh.getMaLop()+"'";
					try {
						Connection conn=ConnJDBC.getConnection();
						java.sql.Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						while(rs.next()) {
							lophoc st=new lophoc(rs.getInt("MaLop"),rs.getString("TenLop"),rs.getString("LichHoc"));
							listlh.add(st);
						}
						
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					return listlh;
				}
							
	public ConnLH() {
		// TODO Auto-generated constructor stub
	}

}
