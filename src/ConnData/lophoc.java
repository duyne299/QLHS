package ConnData;

public class lophoc {
	private int MaLop;
	private String TenLop;
	private String LichHoc;

	public lophoc(int MaLop, String TenLop, String LichHoc) {
		this.MaLop = MaLop;
		this.TenLop = TenLop;
		this.LichHoc = LichHoc;

	}

	public lophoc() {
		// TODO Auto-generated constructor stub
	}

	public int getMaLop() {
		return MaLop;
	}

	public void setMaLop(int maLop) {
		MaLop = maLop;
	}

	public String getTenLop() {
		return TenLop;
	}

	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}

	public String getLichHoc() {
		return LichHoc;
	}

	public void setLichHoc(String lichHoc) {
		LichHoc = lichHoc;
	}

}
