package model;

import java.util.ArrayList;

public class HoaDon {
    private String ma;
    private KhachHang khachHang; // 1 hoadon thuộc về 1 khách hàng
    private ArrayList<ChiTietHoaDon> listChiTietHoaDon; // 1 hoadon có nhiều chi tiết hóa đơn
    private String ngayTaoHoaDon;
    private String ghiChu;
    private long thanhTien;

    public HoaDon() {
        listChiTietHoaDon = new ArrayList<>();
    }

    public HoaDon(String ma, KhachHang khachHang, String ngayTaoHoaDon,
            String ghiChu, long thanhTien) {
        this.ma = ma;
        this.khachHang = khachHang;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.ghiChu = ghiChu;
        this.listChiTietHoaDon = new ArrayList<>();
        this.thanhTien = thanhTien;
    }

    public HoaDon(String ma, KhachHang khachHang, String ngayTaoHoaDon,
            String ghiChu) {
        this.ma = ma;
        this.khachHang = khachHang;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.ghiChu = ghiChu;
        listChiTietHoaDon = new ArrayList<>();
    }

    public ArrayList<ChiTietHoaDon> getListChiTietHoaDon() {
        return listChiTietHoaDon;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public String getMa() {
        return ma;
    }

    public String getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setListChiTietHoaDon(ArrayList<ChiTietHoaDon> listChiTietHoaDon) {
        this.listChiTietHoaDon = listChiTietHoaDon;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setNgayTaoHoaDon(String ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        if (chiTietHoaDon == null) {
            return false;
        }
        return listChiTietHoaDon.add(chiTietHoaDon);
    }

    public void tangThanhTien(long value) {
        this.thanhTien += value;
    }

    public void giamThanhTien(Long value) {
        this.thanhTien -= value;
    }

    public void tinhThanhTien() { // tính thanhtien dự trên giá của các chi tiết hóa đơn (đảm bảo chitiethoadon
                                  // được tính xong thì dùng)
        thanhTien = 0;
        for (ChiTietHoaDon chiTietHoaDon : listChiTietHoaDon) {
            thanhTien += chiTietHoaDon.getThanhTien();
        }
        setThanhTien(thanhTien);
    }

    @Override
    public String toString() {
        return "HoaDonMa : " + getMa() + "\n" +
                "KhachHangMa : " + (khachHang != null ? khachHang.getMaKh() : "null") + "\n" +
                "GhiChu : " + getGhiChu() + "\n" +
                "NgayTaoHoaDon : " + getNgayTaoHoaDon() + "\n" +
                "ThanhTien : " + getThanhTien() + "\n";
    }
}