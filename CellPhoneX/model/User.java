package model;

import java.util.ArrayList;

public abstract class User {
    private String ma;
    private String quyenHang;
    private String cccd;
    private String ten;
    private String ngaySinh;
    private String sdt;
    private String gioiTinh;
    private TaiKhoan taiKhoan = null;
    private ArrayList<TinNhan> listTinNhan; // kho lưu trử tin nhắn riêng cho một user

    public User() {
    }

    public User(String ma, String cccd, String ten,
            String ngaySinh, String sdt, String gioiTinh) {
        this.ma = ma;
        this.cccd = cccd;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        listTinNhan = new ArrayList<>();
    }

    public String getQuyenHang() {
        return quyenHang;
    }

    public void setQuyenHang(String quyenHang) {
        this.quyenHang = quyenHang;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getMa() {
        return ma;
    }

    public String getCccd() {
        return cccd;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getngaySinh() {
        return ngaySinh;
    }

    public ArrayList<TinNhan> getListTinNhan() {
        return listTinNhan;
    }

    public String getSdt() {
        return sdt;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public String getTen() {
        return ten;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setngaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setListTinNhan(ArrayList<TinNhan> listTinNhan) {
        this.listTinNhan = listTinNhan;
    }

    public boolean nhanTinNhan(TinNhan tinNhan) {
        if (tinNhan == null) {
            return false;
        }
        return listTinNhan.add(tinNhan);
    }

    public boolean capTaiKhoan(TaiKhoan tk) {
        if (tk == null) {
            return false;
        }
        this.taiKhoan = tk;
        tk.setUser(this);
        return true;
    }

    public void goTaiKhoan() {
        taiKhoan = null;
    }

    public abstract String toString();
}
