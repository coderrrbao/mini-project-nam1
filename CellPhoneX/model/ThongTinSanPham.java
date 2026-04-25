package model;

public class ThongTinSanPham {
    private String ma;
    private String ten;
    private String danhMuc;
    private String thuongHieu;
    private long gia;
    private int tonKho = 0;
    private String moTa;
    private String trangThai;

    public ThongTinSanPham(String ma, String ten, String danhMuc, String thuongHieu, long gia, int tonKho, String moTa,
            String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.danhMuc = danhMuc;
        this.thuongHieu = thuongHieu;
        this.gia = gia;
        this.tonKho = tonKho;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public ThongTinSanPham(String ma, String ten, String danhMuc, String thuongHieu, long gia, String moTa,
            String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.danhMuc = danhMuc;
        this.thuongHieu = thuongHieu;
        this.gia = gia;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public ThongTinSanPham(ThongTinSanPham sanPham) {
        this.ma = sanPham.ma;
        this.ten = sanPham.ten;
        this.danhMuc = sanPham.danhMuc;
        this.thuongHieu = sanPham.thuongHieu;
        this.gia = sanPham.gia;
        this.tonKho = sanPham.tonKho;
        this.moTa = sanPham.moTa;
        this.trangThai = sanPham.trangThai;
    }

    public ThongTinSanPham() {
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public String getTen() {
        return ten;
    }

    public long getGia() {
        return gia;
    }

    public String getMa() {
        return ma;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public int getTonKho() {
        return tonKho;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void giamTonKho() {
        if (tonKho == 0) {
            return;
        }
        this.tonKho--;
        if (this.tonKho == 0) {
            this.trangThai = "Het Hang";
        }
    }

    public void tangTonKho() {
        this.tonKho++;
        if (this.tonKho > 0) {
            this.trangThai = "Con Hang";
        }
    }

    @Override
    public String toString() {
        return "ma : " + ma + "\n" +
                "ten : " + ten + "\n" +
                "danhMuc : " + danhMuc + "\n" +
                "thuongHieu : " + thuongHieu + "\n" +
                "gia : " + gia + "\n" +
                "tonKho : " + tonKho + "\n" +
                "moTa : " + moTa + "\n" +
                "trangThai : " + trangThai + "\n";
    }
}
