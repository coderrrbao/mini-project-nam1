package quanli;

import java.util.ArrayList;

import danhsach.DanhSachChiTietHoaDon;
import danhsach.DanhSachKhachHang;
import danhsach.DanhSachPhieuBaoHanh;
import danhsach.DanhSachSanPham;
import database.Database;
import model.HoaDon;
import model.KhachHang;
import model.PhieuBaoHanh;
import model.SanPham;
import util.Nhap;
import util.TaoDoiTuong;
import util.XoaManHinh;

public class QuanLyPhieuBaoHanh {
    private Database db; // Tham chiếu đến database chính

    public QuanLyPhieuBaoHanh(Database db) {
        this.db = db; // Gán database vào quản lý phiếu bảo hành
    }

    public void taoPhieuBaoHanh() {
        // Lấy danh sách các đối tượng liên quan
        DanhSachPhieuBaoHanh danhSachPhieuBaoHanh = db.getDanhSachPhieuBaoHanh();
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();

        // Tìm khách hàng theo số điện thoại
        KhachHang khachHang = danhSachKhachHang.timKhachHangTheoSdt(Nhap.nhapStr("Nhap sdt khach hang de bao hanh : "));
        if (khachHang == null) {
            System.out.println("Khong tim thay khach hang");
            return;
        }

        // Tìm sản phẩm theo serial
        SanPham sanPham = danhSachSanPham.tim(Nhap.nhapStr("Nhap ma serial san pham can bao hanh : "));
        if (sanPham == null) {
            System.out.println("Khong tim thay san pham");
            return;
        }
        DanhSachPhieuBaoHanh danhSachPhieuBaoHanhKh = new DanhSachPhieuBaoHanh(khachHang.getListPhieuBaoHanh());
        if (danhSachPhieuBaoHanhKh.tim(sanPham) != null) {
            System.out.println("San pham dang duoc bao hanh");
            return;
        }
        if (sanPham.getTraHang()) {
            System.out.println("San pham da duoc tra hang");
            return;
        }

        // Kiểm tra sản phẩm có trong hóa đơn của khách hàng không
        boolean tonTai = false;
        for (HoaDon hoaDon : khachHang.getListHoaDon()) {
            DanhSachChiTietHoaDon danhSachChiTietHoaDon = new DanhSachChiTietHoaDon(hoaDon.getListChiTietHoaDon());
            if (danhSachChiTietHoaDon.tim(sanPham) != null) {
                tonTai = true;
            }
        }
        if (!tonTai) {
            System.out.println("Khach hang chua mua san pham nay");
            return;
        }

        // Kiểm tra sản phẩm có bảo hành không
        if (sanPham.getBaoHanh() == null) {
            System.out.println("San pham nay khong co bao hanh");
            return;
        }

        // Tạo phiếu bảo hành và thêm vào danh sách
        PhieuBaoHanh phieuBaoHanh = TaoDoiTuong.taoPhieuBaoHanh(sanPham.getBaoHanh(), khachHang, db);
        danhSachPhieuBaoHanh.them(phieuBaoHanh);
        System.out.println("Da tao phieu bao hanh thanh cong.");
    }

    public void xuatSuaPhieuBaoHanh() {
        // Menu sửa phiếu bảo hành
        System.out.println("1. Sua khach hang");
        System.out.println("2. Sua san pham");
        System.out.println("3. Sua ngay bao hanh");
        System.out.println("4. Sua chi tiet loi");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    private void suaThanhPhanPhieu(PhieuBaoHanh phieuBaoHanh, int luaChon) {
        // Thực hiện sửa từng phần trong phiếu theo lựa chọn
        switch (luaChon) {
            case 0:
                System.out.println("Thoat sua khach hang");
                break;
            case 1:
                // Sửa thông tin khách hàng trong phiếu
                DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
                KhachHang khachHang = danhSachKhachHang
                        .tim(Nhap.nhapStr("Nhap ma khach hang can thay doi vao trong phieu : "));
                if (khachHang == null) {
                    System.out.println("Ma khach hang khong hop le");
                } else {
                    phieuBaoHanh.setKhachHang(khachHang);
                    System.out.println("Da sua khach hang");
                }
                break;
            case 2:
                // Sửa sản phẩm trong phiếu
                DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
                SanPham sanPham = danhSachSanPham.tim(Nhap.nhapStr("Nhap ma serial san pham moi trong phieu : "));
                if (sanPham == null) {
                    System.out.println("Ma san pham khong hop le");
                } else {
                    phieuBaoHanh.setSanPham(sanPham);
                    System.out.println("Da sua san pham");
                }
                break;
            case 3:
                // Sửa ngày bảo hành
                phieuBaoHanh.setNgayBaoHanh(Nhap.nhapNgay("Nhap ngay bao hanh moi : "));
                break;
            case 4:
                // Sửa chi tiết lỗi
                phieuBaoHanh.setChiTietLoi(Nhap.nhapStr("Nhap chi tiet loi moi : "));
                break;
            default:
                System.out.println("lua chon khong hop le");
                break;
        }
    }

    public void suaPhieuBaoHanh() {
        // Sửa thông tin phiếu bảo hành theo mã
        DanhSachPhieuBaoHanh danhSachPhieuBaoHanh = db.getDanhSachPhieuBaoHanh();
        String maPhieu = Nhap.nhapStr("Nhap ma phieu bao hanh can sua: ");
        PhieuBaoHanh phieuBaoHanh = danhSachPhieuBaoHanh.tim(maPhieu);
        if (phieuBaoHanh == null) {
            System.out.println("Khong tim thay phieu bao hanh");
            return;
        }
        int xacNhan = 1;
        while (xacNhan == 1) {
            // Hiển thị thông tin phiếu trước khi sửa
            XoaManHinh.xoa();
            System.out.println("------------------------");
            System.out.println(phieuBaoHanh);
            System.out.println("------------------------");
            xuatSuaPhieuBaoHanh(); // in menu sửa
            int luaChon = Nhap.nhapInt("Nhap lua chon : ");
            if (luaChon == 0) {
                return;
            }
            suaThanhPhanPhieu(phieuBaoHanh, luaChon); // thực hiện sửa
            Nhap.pause(); // dừng màn hình chờ người dùng
        }
        System.out.println("Da cap nhat phieu bao hanh.");
    }

    public void xoaPhieuBaoHanh() {
        // Xóa phiếu bảo hành khỏi danh sách
        DanhSachPhieuBaoHanh danhSachPhieuBaoHanh = db.getDanhSachPhieuBaoHanh();
        String ma = Nhap.nhapStr("Nhap ma phieu bao hanh can xoa: ");
        PhieuBaoHanh phieuBaoHanh = danhSachPhieuBaoHanh.tim(ma);
        if (phieuBaoHanh == null) {
            System.out.println("Khong tim thay phieu bao hanh");
            return;
        }
        // Xóa khỏi danh sách phiếu của khách hàng
        KhachHang khachHang = phieuBaoHanh.getKhachHang();
        khachHang.getListPhieuBaoHanh().remove(phieuBaoHanh);
        // Xóa khỏi danh sách tổng
        danhSachPhieuBaoHanh.xoa(ma);
        System.out.println("Da xoa phieu bao hanh");
    }

    public void xemTatCaPhieu() {
        // In danh sách toàn bộ phiếu bảo hành
        ArrayList<PhieuBaoHanh> listPhieuBaoHanh = db.getListPhieuBaoHanh();
        if (listPhieuBaoHanh == null || listPhieuBaoHanh.size() == 0) {
            System.out.println("Chua co phieu bao hanh nao.");
            return;
        }
        System.out.println("Danh sach tat ca phieu bao hanh:");
        for (int i = 0; i < listPhieuBaoHanh.size(); i++) {
            System.out.println("---------------------------");
            System.out.println(listPhieuBaoHanh.get(i));
        }
    }

    public void traCuuPhieuBaoHanh() {
        // Tra cứu phiếu bảo hành theo mã
        DanhSachPhieuBaoHanh danhSachPhieuBaoHanh = db.getDanhSachPhieuBaoHanh();
        PhieuBaoHanh phieuBaoHanh = danhSachPhieuBaoHanh
                .tim(Nhap.nhapStr("Nhap ma phieu bao hanh can tim : "));
        if (phieuBaoHanh == null) {
            System.out.println("khong tim thay phieu bao hanh");
            return;
        }
        System.out.println("---------------------------");
        System.out.println(phieuBaoHanh);
        System.out.println("---------------------------");
    }

    private void xuatMenu() {
        // Menu chính quản lý phiếu bảo hành
        System.out.println("======= Quan Ly Phieu Bao Hanh =======");
        System.out.println("1. Tao phieu bao hanh");
        System.out.println("2. Sua phieu bao hanh");
        System.out.println("3. Xoa phieu bao hanh");
        System.out.println("4. Tra cuu phieu bao hanh");
        System.out.println("5. Xem phieu bao hanh");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    public void thucHienChucNang(int luaChon) {
        // Xử lý hành động người dùng chọn
        switch (luaChon) {
            case 0 -> {
                System.out.println("Thoat menu phieu bao hanh");
                return;
            }
            case 1 -> taoPhieuBaoHanh();
            case 2 -> suaPhieuBaoHanh();
            case 3 -> xoaPhieuBaoHanh();
            case 4 -> traCuuPhieuBaoHanh();
            case 5 -> xemTatCaPhieu();
            default -> System.out.println("Lua chon khong hop le!");
        }
    }

    public void menu() {
        // Vòng lặp menu chính cho quản lý phiếu bảo hành
        int xacNhan = 1;
        while (xacNhan == 1) {
            XoaManHinh.xoa();
            xuatMenu();
            int luaChon = Nhap.nhapInt("Nhap lua Chon : ");
            if (luaChon == 0) {
                return;
            }
            thucHienChucNang(luaChon);
            Nhap.pause();
        }
    }
}
