package model;

public class QuanLy extends User {
    public QuanLy(String ma, String cccd, String ten,
            String ngaySinh, String sdt, String gioiTinh) {
        super(ma, cccd, ten, ngaySinh, sdt, gioiTinh);
        setQuyenHang("QuanLy"); // phân quyền
    }

    @Override
    public String toString() {
        return "ma : " + getMa() + "\n"
                + "ten : " + getTen() + "\n"
                + "cccd : " + getCccd() + "\n"
                + "ngaySinh : " + getNgaySinh() + "\n"
                + "sdt : " + getSdt() + "\n"
                + "gioiTinh : " + getGioiTinh() + "\n"
                + "quyenHang : " + getQuyenHang() + "\n";
    }
}
