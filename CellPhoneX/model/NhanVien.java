package model;

public class NhanVien extends User {
    private long luong;

    public NhanVien() {
    }

    public NhanVien(String ma, String cccd, String ten,
            String ngaySinh, String sdt, String gioiTinh, long luong) {
        super(ma, cccd, ten, ngaySinh, sdt, gioiTinh);
        setLuong(luong);
        setQuyenHang("NhanVien"); // phân quyền đối tượng
    }

    public NhanVien(String ma, String cccd, String ten,
            String ngaySinh, String sdt, String gioiTinh) {
        super(ma, cccd, ten, ngaySinh, sdt, gioiTinh);
        setQuyenHang("NhanVien");// phân quyền đối tượng
    }

    public long getLuong() {
        return luong;
    }

    public String get_ten() { // lấy tên riêng (lấy từ cuối)
        return getTen().split("\\s+")[getTen().split("\\s+").length - 1];
    }

    public void setLuong(long luong) {
        this.luong = luong;
    }

    @Override
    public String toString() {
        return "ma : " + getMa() + "\n"
                + "ten : " + getTen() + "\n"
                + "cccd : " + getCccd() + "\n"
                + "ngaySinh : " + getNgaySinh() + "\n"
                + "sdt : " + getSdt() + "\n"
                + "gioiTinh : " + getGioiTinh() + "\n"
                + "quyenHang : " + getQuyenHang() + "\n"
                + "luong : " + luong + "\n";
    }
}
