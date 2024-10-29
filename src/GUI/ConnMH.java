package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ConnData.hocsinh;
import ConnData.lophoc;
import ConnData.monhoc;
import ConnData.phieudiem;


public class ConnMH {
	

	

//Hàm Hiển thị môn học
	public static List<monhoc> HienThi(){
		List<monhoc>mhlist=new ArrayList<>();
		String query ="SELECT * FROM monhoc";
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
	            // Lặp qua kết quả và thêm vào danh sách sinh viên
	            while (rs.next()) {
	                monhoc mh = new monhoc(rs.getInt("MaMH"),
	                		rs.getString("TenMH"),
	                		rs.getInt("SoTiet")
	                        );
	                mhlist.add(mh);
	            }
			}
            
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return mhlist;
		

	}
	
	//Thêm môn học 
	public static void ThemMH(monhoc mh) {
		String query ="Insert into monhoc(MaMH,TenMH,SoTiet) values(?,?,?)";
		try {
			Connection conn=ConnJDBC.getConnection();
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, mh.getMaMH());
			ps.setString(2, mh.getTenMH());
			ps.setInt(3, mh.getSoTiet());
			ps.execute();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	//Xóa môn học 
	public static void XoaHS(monhoc mh) {
		String query = "delete from monhoc where MaMH='" + mh.getMaMH() + "'";
		try {
			Connection connection =ConnJDBC. getConnection();
			PreparedStatement psmt = connection.prepareStatement(query);
			psmt.executeUpdate();

		} catch (Exception ex) {

		}
	}
	//Sửa danh sách
		public static void Update(monhoc mh) {
			String query ="Update monhoc set TenMH=?,SoTiet=? where MaMH='"+mh.getMaMH()+"'";
			try {
				Connection conn= ConnJDBC.getConnection();
				PreparedStatement ps=conn.prepareStatement(query);
				ps.setString(1, mh.getTenMH());
				ps.setInt(2,mh.getSoTiet());	
				ps.executeUpdate();		
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		//Tìm môn học theo mã môn học
		public static List<monhoc>TimMH(monhoc mh){
			List<monhoc>listmh=new ArrayList<>();
			String query="Select * from monhoc where monhoc.MaMH='"+mh.getMaMH()+"'";
			try {
				Connection conn=ConnJDBC.getConnection();
				java.sql.Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					monhoc st=new monhoc(rs.getInt("MaMH"),rs.getString("TenMH"),rs.getInt("SoTiet"));
					listmh.add(st);
				}
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			return listmh;
		}
		
		//Tìm môn học dựa theo tên môn học
		public static List<monhoc>TimTenMH(monhoc mh){
			List<monhoc>listmh=new ArrayList<>();
			String query="Select * from monhoc where monhoc.TenMH='"+mh.getTenMH()+"'";
			try {
				Connection conn=ConnJDBC.getConnection();
				java.sql.Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					monhoc st=new monhoc(rs.getInt("MaMH"),rs.getString("TenMH"),rs.getInt("SoTiet"));
					listmh.add(st);
				}
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			return listmh;
		}
		
	public ConnMH() {
		// TODO Auto-generated constructor stub
	}

}
