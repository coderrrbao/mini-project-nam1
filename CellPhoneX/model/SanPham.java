package model;

public class SanPham {
    private String serial; // mã duy nhất của sản phẩm phân biệt với các sản phẩm khác
    private ThongTinSanPham thongTinSanPham; // thông tin của sản phẩm (lưu trong database)
    private boolean traHang = false; // theo giỏi tình trạng trả hàng
    private BaoHanh baoHanh = null; // Bảo hành của riêng sản phẩm (đối tượng giống thông tin được tách ra từ đôi
                                    // tượng trong database)
    private boolean daBan = false;// theo giỏi xem còn trong kho không

    public SanPham() {
    }

    public SanPham(String serial, ThongTinSanPham thongTinSanPham) {
        this.serial = serial;
        this.thongTinSanPham = thongTinSanPham;
    }

    public SanPham(String serial, ThongTinSanPham thongTinSanPham, boolean traHang, BaoHanh baoHanh, boolean daBan) {
        this.serial = serial;
        this.thongTinSanPham = thongTinSanPham;
        this.traHang = traHang;
        this.baoHanh = baoHanh;
        this.daBan = daBan;
    }

    public ThongTinSanPham getThongTinSanPham() {
        return thongTinSanPham;
    }

    public String getSerial() {
        return serial;
    }

    public boolean getDaBan() {
        return daBan;
    }

    public boolean getTraHang() {
        return traHang;
    }

    public void setThongTinSanPham(ThongTinSanPham thongTinSanPham) {
        this.thongTinSanPham = thongTinSanPham;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setBaoHanh(BaoHanh baoHanh) {
        this.baoHanh = baoHanh;
    }

    public void setDaBan(boolean daBan) {
        this.daBan = daBan;
    }

    public void setTraHang(boolean traHang) {
        this.traHang = traHang;
    }

    public void doiDaBan() {
        this.daBan = this.daBan == false ? true : false;
    }

    public String getMa() {
        return thongTinSanPham.getMa();
    }

    public String getTen() {
        return thongTinSanPham.getTen();
    }

    public String getDanhMuc() {
        return thongTinSanPham.getDanhMuc();
    }

    public String getThuongHieu() {
        return thongTinSanPham.getThuongHieu();
    }

    public long getGia() {
        return thongTinSanPham.getGia();
    }

    public BaoHanh getBaoHanh() {
        return baoHanh;
    }

    public int getTonKho() {
        return thongTinSanPham.getTonKho();
    }

    public String getMoTa() {
        return thongTinSanPham.getMoTa();
    }

    public String getTrangThai() {
        return thongTinSanPham.getTrangThai();
    }

    @Override
    public String toString() {
        return "Serial : " + serial + "\n"
                + "ma : " + thongTinSanPham.getMa() + "\n" +
                "ten : " + thongTinSanPham.getTen() + "\n" +
                "danhMuc : " + thongTinSanPham.getDanhMuc() + "\n" +
                "thuongHieu : " + thongTinSanPham.getThuongHieu() + "\n" +
                "gia : " + thongTinSanPham.getGia() + "\n" +
                "moTa : " + thongTinSanPham.getMoTa() + "\n" + "Da Ban : " + getDaBan() + "\n" + "Tra hang : " + traHang
                + "\n"
                + "BaoHanh : " + ((baoHanh == null) ? "null" : (baoHanh.getMaBh() + " " + baoHanh.getLoaiBaoHanh()))
                + "\n";
    }
}
