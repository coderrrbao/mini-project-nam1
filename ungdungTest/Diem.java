public class Diem {
    private String ma;
    private String ten;
    private double diem;
    private String tenMon;
    private int hocKi;
    private int namHoc;

    public Diem(String ma, String ten, double diem, String tenMon, int hocKi, int namHoc) {
        this.ma = ma;
        this.ten = ten;
        this.diem = diem;
        this.tenMon = tenMon;
        this.hocKi = hocKi;
        this.namHoc = namHoc;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    // Getter và Setter cho diem
    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    // Getter và Setter cho tenMon
    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    // Getter và Setter cho hocKi
    public int getHocKi() {
        return hocKi;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    // Getter và Setter cho namHoc
    public int getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(int namHoc) {
        this.namHoc = namHoc;
    }

    public void hienThiThongTin() {
        System.out.println(ma + " " + ten + " " + tenMon + " " + diem + " " + hocKi + " " + namHoc);
    }

    public void hienThiKetQua() {
        System.out.println(ma + " " + ten + " : " + diem + "/10");
    }
}