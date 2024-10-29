package ConnData;

public class hocsinh {
	private int MaHS;
	private String HoTen;
	private int GioiTinh;
	private String NgaySinh;
	private int SDT;
	private String DiaChi;
	private int MaLop;
	private String GhiChu;

	public hocsinh(int MaHS, String HoTen,int GioiTinh, String NgaySinh, int SDT, String DiaChi, int MaLop,
			String GhiChu) {
		this.MaHS = MaHS;
		this.HoTen = HoTen;
		this.GioiTinh = GioiTinh;
		this.NgaySinh = NgaySinh;
		this.SDT = SDT;
		this.DiaChi = DiaChi;
		this.MaLop = MaLop;
		this.GhiChu = GhiChu;

	}

	
	public int getGioiTinh() {
		return GioiTinh;
	}


	public void setGioiTinh(int gioiTinh) {
		GioiTinh = gioiTinh;
	}


	public int getMaHS() {
		return MaHS;
	}

	public void setMaHS(int maHS) {
		MaHS = maHS;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	
	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public int getSDT() {
		return SDT;
	}

	public void setSDT(int sDT) {
		SDT = sDT;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public int getMaLop() {
		return MaLop;
	}

	public void setMaLop(int maLop) {
		MaLop = maLop;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}

	public hocsinh() {
		// TODO Auto-generated constructor stub
	}

}
