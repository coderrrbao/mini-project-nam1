package model;

public class PhieuTraHang {
    // chi dc tra truoc 3 ngay sau khi mua sann phan, Tra hang can hoa don
    private String maTraHang;
    private KhachHang khachHang;
    private SanPham sanPham;
    private String ngayTra;
    private String lyDoTra;

    public PhieuTraHang() {
    }

    public PhieuTraHang(String maTraHang, KhachHang khachHang, SanPham sanPham, String ngayTra,
            String lyDoTra) {
        this.maTraHang = maTraHang;
        this.khachHang = khachHang;
        this.sanPham = sanPham;
        this.ngayTra = ngayTra;
        this.lyDoTra = lyDoTra;
    }

    public PhieuTraHang(String maTraHang, SanPham sanPham, String ngayTra,
            String lyDoTra) {
        this.maTraHang = maTraHang;
        this.sanPham = sanPham;
        this.ngayTra = ngayTra;
        this.lyDoTra = lyDoTra;
    }

    public String getMaTraHang() {
        return maTraHang;
    }

    public void setMaTraHang(String maTraHang) {
        this.maTraHang = maTraHang;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getLyDoTra() {
        return lyDoTra;
    }

    public void setLyDoTra(String lyDoTra) {
        this.lyDoTra = lyDoTra;
    }

    @Override
    public String toString() {
        return "maTraHang : " + maTraHang + "\n" +
                "khachHangMa : " + (khachHang != null ? khachHang.getMaKh() : "null") + "\n" +
                "sanPhamMa : " + (sanPham != null ? sanPham.getSerial() : "null") + "\n" +
                "ngayTra : " + ngayTra + "\n" +
                "lyDoTra : " + lyDoTra + "\n";
    }
}
