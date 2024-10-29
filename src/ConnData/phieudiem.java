package ConnData;

public class phieudiem {
	private int MaPhieu;
	private int MaMH;
	private int MaHS;
	private int MaLop;
	private double DiemHK1;
	private double DiemHK2;
	private double DiemTB;
	private String XepLoai;

	public phieudiem(int MaPhieu, int MaMH, int MaHS, int MaLop, double DiemHK1, double DiemHK2, double DiemTB, String Xeploai) {
		this.MaPhieu = MaPhieu;
		this.MaMH = MaMH;
		this.MaHS = MaHS;
		this.MaLop = MaLop;
		this.DiemHK1 = DiemHK1;
		this.DiemHK2 = DiemHK2;
		this.DiemTB = DiemTB;
		this.XepLoai = Xeploai;

	}

	public phieudiem() {
		// TODO Auto-generated constructor stub
	}

	public int getMaPhieu() {
		return MaPhieu;
	}

	public void setMaPhieu(int maPhieu) {
		MaPhieu = maPhieu;
	}

	public int getMaMH() {
		return MaMH;
	}

	public void setMaMH(int maMH) {
		MaMH = maMH;
	}

	public int getMaHS() {
		return MaHS;
	}

	public void setMaHS(int maHS) {
		MaHS = maHS;
	}

	public int getMaLop() {
		return MaLop;
	}

	public void setMaLop(int maLop) {
		MaLop = maLop;
	}

	

	public double getDiemHK1() {
		return DiemHK1;
	}

	public void setDiemHK1(double diemHK1) {
		DiemHK1 = diemHK1;
	}

	public double getDiemHK2() {
		return DiemHK2;
	}

	public void setDiemHK2(double diemHK2) {
		DiemHK2 = diemHK2;
	}

	public double TinhDiemTB() {
		return (DiemHK1+DiemHK2)/2;
	}

	

	public String XepLoai() {
		 double dtb = TinhDiemTB();
	        if (dtb >= 8.5) {
	            return "Giỏi";
	        } else if (dtb >= 6.5) {
	            return "Khá";
	        } else if (dtb >= 5) {
	            return "Trung bình";
	        } else {
	            return "Yếu";
	        }
	}

	

}
