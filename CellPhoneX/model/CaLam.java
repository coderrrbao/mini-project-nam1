package model;

public class CaLam {
    private String ma;
    private int so; // số ca (1,2,3)
    private String gioBatDau;
    private String gioKetThuc;
    private int soLuongCan;
    private NhanVienDiemDanh listNhanVien; // Nhân viên trong ca

    public CaLam(String ma, int so, String gioBatBau, String gioKetThuc, int soLuongCan) {
        this.ma = ma;
        this.so = so;
        this.gioBatDau = gioBatBau;
        this.gioKetThuc = gioKetThuc;
        listNhanVien = new NhanVienDiemDanh();
        this.soLuongCan = soLuongCan;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public String getGioKetThuc() {
        return gioKetThuc;
    }

    public int getSo() {
        return so;
    }

    public String getMa() {
        return ma;
    }

    public int getSoLuongCan() {
        return soLuongCan;
    }

    public NhanVienDiemDanh getListNhanVien() {
        return listNhanVien;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public void setGioKetThuc(String gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public void setSo(int so) {
        this.so = so;
    }

    public void setSoLuongCan(int soLuongCan) {
        this.soLuongCan = soLuongCan;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setListNhanVien(NhanVienDiemDanh listNhanVien) {
        this.listNhanVien = listNhanVien;
    }

    public boolean diemDanh(NhanVien nhanVien) {
        return listNhanVien.diemDanhNhanVien(nhanVien);
    }

    public int soNguoiTrongCa() {
        return this.listNhanVien.soNhanVien();
    }

    public boolean tonTaiNhanVien(NhanVien nhanVien) {
        return listNhanVien.tonTaiNhanVien(nhanVien);
    }

    public boolean caLamChuaCoNguoi() {
        if (soNguoiTrongCa() == 0) {
            return true;
        }
        return false;
    }

    public boolean caLamDuNguoi() {
        if (soLuongCan == listNhanVien.soNhanVien()) {
            return true;
        }
        return false;
    }

    public boolean themNhanVienVaoCa(NhanVien nhanVien) {
        return listNhanVien.themNhanVien(nhanVien);
    }

    public boolean xoaNhanVienKhoiCa(NhanVien nhanVien) {
        return listNhanVien.xoaNhanVien(nhanVien);
    }

    public int soNguoiThieu() {
        return soLuongCan - soNguoiTrongCa();
    }

    @Override
    public String toString() {
        return "Gio bat dau: " + getGioBatDau() + "\n" +
                "Gio ket thuc: " + getGioKetThuc() + "\n" +
                "So: " + getSo() + "\n" +
                "So luong can: " + getSoLuongCan() + "\n" +
                "Nhan vien: " + getListNhanVien() + "\n";
    }
}
