package model;

public class MaGiamGia {
    private String ma;
    private String tenMa;
    private String loaiDoanhMuc;
    private String loaiThuongHieu;
    private String tienGiam;
    private String ngayBatDau;
    private String ngayKetThuc;
    private SanPham sanPhamDaDung = null;

    public MaGiamGia(String ma, String tenMa, String loaiDoanhMuc, String loaiThuongHieu, String tienGiam,
            String ngayBatDau, String ngayKetThuc) {
        this.ma = ma;
        this.tenMa = tenMa;
        this.loaiDoanhMuc = loaiDoanhMuc;
        this.loaiThuongHieu = loaiThuongHieu;
        this.tienGiam = tienGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public MaGiamGia(MaGiamGia maGiamGia) {
        this.ma = maGiamGia.getMa();
        this.tenMa = maGiamGia.getTenMa();
        this.loaiDoanhMuc = maGiamGia.getLoaiDoanhMuc();
        this.loaiThuongHieu = maGiamGia.getLoaiThuongHieu();
        this.tienGiam = maGiamGia.getTienGiam();
        this.ngayBatDau = maGiamGia.getNgayBatDau();
        this.ngayKetThuc = maGiamGia.getNgayKetThuc();
        this.sanPhamDaDung = maGiamGia.getSanPhamDaDung();
    }



    public String getLoaiDoanhMuc() {
        return loaiDoanhMuc;
    }

    public String getLoaiThuongHieu() {
        return loaiThuongHieu;
    }

    public String getMa() {
        return ma;
    }

    public SanPham getSanPhamDaDung() {
        return sanPhamDaDung;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public String getTienGiam() {
        return tienGiam;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public String getTenMa() {
        return tenMa;
    }

    public void setTienGiam(String tienGiam) {
        this.tienGiam = tienGiam;
    }

    public void setLoaiDoanhMuc(String loaiDoanhMuc) {
        this.loaiDoanhMuc = loaiDoanhMuc;
    }

    public void setLoaiThuongHieu(String loaiThuongHieu) {
        this.loaiThuongHieu = loaiThuongHieu;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTenMa(String tenMa) {
        this.tenMa = tenMa;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void setSanPhamDaDung(SanPham sanPhamDaDung) {
        this.sanPhamDaDung = sanPhamDaDung;
    }

    @Override
    public String toString() {
        return "Ma : " + ma + "\n" +
                "TenMa : " + tenMa + "\n" +
                "LoaiDoanhMuc : " + loaiDoanhMuc + "\n" +
                "LoaiThuongHieu : " + loaiThuongHieu + "\n" +
                "TienGiam : " + tienGiam + "\n" +
                "NgayBatDau : " + ngayBatDau + "\n" +
                "NgayKetThuc : " + ngayKetThuc + "\n" +
                (sanPhamDaDung != null ? "(da ap dung cho san pham " + sanPhamDaDung.getSerial() + ")" + "\n" : "");
    }

}
