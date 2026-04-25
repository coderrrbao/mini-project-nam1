public abstract class User {
    private String ma;
    private String ten;
    private int tuoi;
    private String namSinh;
    private String sdt;
    private String gioiTinh;
    private TaiKhoan taiKhoan;

    public User(String ten, String namSinh, String sdt, String gioiTinh) {
        this.ten = ten;
        this.tuoi = tinhTuoi(namSinh);
        this.namSinh = namSinh;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
    }

    public User(String ma, String ten, String namSinh, String sdt, String gioiTinh) {
        this.ten = ten;
        this.tuoi = tinhTuoi(namSinh);
        this.namSinh = namSinh;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.ma = ma;
    }

    User() {

    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    private int tinhTuoi(String namSinh) {
        if (namSinh == null || namSinh.length() < 4)
            return -1;
        String t = namSinh.substring(namSinh.length() - 4);
        return 2025 - Integer.parseInt(t);
    }

    public String getMa() {
        return ma;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
        taiKhoan.setUser(this);
    }

    // Getter và Setter cho 'ten'
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    // Getter và Setter cho 'tuoi'
    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    // Getter và Setter cho 'namSinh'
    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    // Getter và Setter cho 'sdt'
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public abstract void hienThiThongTin();

    public abstract String loai();
}
