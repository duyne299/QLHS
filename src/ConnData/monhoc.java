package ConnData;

public class monhoc {
	private int MaMH;
	private String TenMH;
	private int SoTiet;

	public monhoc(int MaMH, String TenMH, int SoTiet) {
		this.MaMH = MaMH;
		this.TenMH = TenMH;
		this.SoTiet = SoTiet;

	}

	public monhoc() {
		// TODO Auto-generated constructor stub
	}

	public int getMaMH() {
		return MaMH;
	}

	public void setMaMH(int maMH) {
		MaMH = maMH;
	}

	public String getTenMH() {
		return TenMH;
	}

	public void setTenMH(String tenMH) {
		TenMH = tenMH;
	}

	public int getSoTiet() {
		return SoTiet;
	}

	public void setSoTiet(int soTiet) {
		SoTiet = soTiet;
	}

}
