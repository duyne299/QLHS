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



public class ConnJDBC {
	static String url = "jdbc:mySQL://localhost:3306/qlhsthpt";
	static String user = "root";
	static String password = "";

	// Hàm tạo kết nối với sql
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
		
	public static List<hocsinh> HienThi(){
		List<hocsinh>hocsinhlist=new ArrayList<>();
		String query="SELECT * FROM hocsinh";
		try {
			//Kết nối tới với CSDL
			Connection conn=getConnection();
			if(conn !=null) {
				java.sql.Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            
	            // Kiểm tra nếu có dữ liệu
	            if (!rs.isBeforeFirst()) {
	                System.out.println("Không có dữ liệu trong bảng hocsinh");
	            }
	            // Lặp qua kết quả và thêm vào danh sách học sinh
	            while (rs.next()) {
	                hocsinh hs = new hocsinh(rs.getInt("MaHS"),
	                		rs.getString("HoTen"),
	                		rs.getInt("GioiTinh"),
	                        rs.getString("NgaySinh"),
	                        rs.getInt("SDT"),
	                        rs.getString("DiaChi"),
	                        rs.getInt("MaLop"),
	                        rs.getString("GhiChu"));
	                hocsinhlist.add(hs);
	            }
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			
		}
		return hocsinhlist;
		
	}
	public static void ThemHS(hocsinh hs) {
		String query ="Insert into hocsinh(MaHS,HoTen,GioiTinh,NgaySinh,SDT,DiaChi,MaLop,GhiChu) values (?,?,?,?,?,?,?,?);";
			try {
				Connection conn=getConnection();
				PreparedStatement ps=conn.prepareStatement(query);
				ps.setInt(1,hs.getMaHS());
				ps.setString(2, hs.getHoTen());
				ps.setInt(3,hs.getGioiTinh());
				ps.setString(4,hs.getNgaySinh());
				ps.setInt(5,hs.getSDT());
				ps.setString(6, hs.getDiaChi());
				ps.setInt(7, hs.getMaLop());
				ps.setString(8, hs.getGhiChu());
				ps.execute();
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
	}
		
	
	//Xóa học sinh
	public static void XoaHS(hocsinh hs) {
		String query = "delete from hocsinh where MaHS='" + hs.getMaHS() + "'";
		try {
			Connection connection = getConnection();
			PreparedStatement psmt = connection.prepareStatement(query);
			psmt.executeUpdate();

		} catch (Exception ex) {

		}
	}
	
	//Sửa danh sách
	public static void Update(hocsinh hs) {
		String query ="Update hocsinh set HoTen=?,GioiTinh=?,NgaySinh=?,SDT=?,DiaChi=?,MaLop=?,GhiChu=? where MaHS='"+hs.getMaHS()+"'";
		try {
			Connection conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setString(1, hs.getHoTen());
			ps.setInt(2,hs.getGioiTinh());
			ps.setString(3,hs.getNgaySinh());
			ps.setInt(4,hs.getSDT());
			ps.setString(5, hs.getDiaChi());
			ps.setInt(6, hs.getMaLop());
			ps.setString(7, hs.getGhiChu());
			ps.executeUpdate();
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static List<hocsinh>TimHS(hocsinh hs){
		List<hocsinh>listhocsinh=new ArrayList<>();
		String query="Select * from hocsinh where hocsinh.MaHS='"+hs.getMaHS()+"'";
		try {
			Connection conn=getConnection();
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				hocsinh st=new hocsinh(rs.getInt("MaHS"),rs.getString("HoTen"),rs.getInt("GioiTinh"),rs.getString("NgaySinh"),rs.getInt("SDT"),rs.getNString("DiaChi"),rs.getInt("MaLop"),rs.getString("GhiChu"));
				listhocsinh.add(st);
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return listhocsinh;
	}
	public static List<hocsinh>TimTenHS(hocsinh hs){
		List<hocsinh>listhocsinh=new ArrayList<>();
		String query="Select * from hocsinh where hocsinh.HoTen='"+hs.getHoTen()+"'";
		try {
			Connection conn=getConnection();
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				hocsinh st=new hocsinh(rs.getInt("MaHS"),rs.getString("HoTen"),rs.getInt("GioiTinh"),rs.getString("NgaySinh"),rs.getInt("SDT"),rs.getNString("DiaChi"),rs.getInt("MaLop"),rs.getString("GhiChu"));
				listhocsinh.add(st);
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return listhocsinh;
	}
	public static void CapNhatSL() {
		String query="SELECT COUNT(*) AS row_count FROM hocsinh";
		
		
	}
	
		
	public ConnJDBC() {
		// TODO Auto-generated constructor stub
	}

}
