public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private User user;

    // Constructor
    public TaiKhoan(String tenDangNhap, String matKhau, User user) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.user = user;
    }

    public TaiKhoan(String tenDangNhap, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public TaiKhoan() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter cho tên đăng nhập
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    // Setter cho tên đăng nhập
    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    // Getter cho mật khẩu
    public String getMatKhau() {
        return matKhau;
    }

    // Setter cho mật khẩu
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    // Phương thức hiển thị thông tin tài khoản (tùy chọn)
    public void hienThiThongTin() {
        System.out.println("Tên đăng nhập: " + tenDangNhap);
        System.out.println("Mật khẩu: " + matKhau);
        System.out.println();
    }

    public boolean kiemTra(TaiKhoan tk) {
        if (tk.getTenDangNhap().equals(this.tenDangNhap) && tk.getMatKhau().equals(this.matKhau)) {
            return true;
        }
        return false;
    }

    public User tryCapTaiKhoan() {
        return user;
    }

}