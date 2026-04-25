package model;

public class PhieuBaoHanh {
    private String maBaoHanh;
    private KhachHang khachHang;
    private SanPham sanPham;
    private String ngayBaoHanh;
    private String ChiTietLoi;

    public PhieuBaoHanh(String ma, KhachHang khachHang, SanPham sanPham, String ngayBaoHanh, String chiTietLoi) {
        this.maBaoHanh = ma;
        this.khachHang = khachHang;
        this.sanPham = sanPham;
        this.ngayBaoHanh = ngayBaoHanh;
        this.ChiTietLoi = chiTietLoi;
    }

    public String getChiTietLoi() {
        return ChiTietLoi;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public String getMaBaoHanh() {
        return maBaoHanh;
    }

    public String getNgayBaoHanh() {
        return ngayBaoHanh;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setChiTietLoi(String chiTietLoi) {
        ChiTietLoi = chiTietLoi;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public void setMaBaoHanh(String maBaoHanh) {
        this.maBaoHanh = maBaoHanh;
    }

    public void setNgayBaoHanh(String ngayBaoHanh) {
        this.ngayBaoHanh = ngayBaoHanh;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public String toString() {
        return "maBaoHanh : " + maBaoHanh + "\n" +
                "khachHangMa : " + (khachHang != null ? khachHang.getMaKh() : "null") + "\n" +
                "sanPhamMa : " + (sanPham != null ? sanPham.getSerial() : "null") + "\n" +
                "ngayBaoHanh : " + ngayBaoHanh + "\n" +
                "ChiTietLoi : " + ChiTietLoi + "\n";
    }
}
