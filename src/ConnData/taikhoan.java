package ConnData;

public class taikhoan {
	private String TenTK;
	private String MatKhau;
	
	public taikhoan(String TenTK,String MatKhau) {
		this.TenTK=TenTK;
		this.MatKhau=MatKhau;
		
	}

	public String getTenTK() {
		return TenTK;
	}

	public void setTenTK(String tenTK) {
		TenTK = tenTK;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	public taikhoan() {
		// TODO Auto-generated constructor stub
	}

}
