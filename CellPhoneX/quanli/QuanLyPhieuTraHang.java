package quanli;

import java.util.ArrayList;

import danhsach.*;
import database.Database;
import model.*;
import util.*;

// Quản lý các phiếu trả hàng
public class QuanLyPhieuTraHang {
    private Database db; // CSDL tổng chứa các danh sách

    public QuanLyPhieuTraHang(Database db) {
        this.db = db;
    }

    // Hiển thị toàn bộ phiếu trả hàng
    public void hienThiTatCaPhieuTraHang() {
        System.out.println("===== DANH SACH PHIEU TRA HANG =====");
        ArrayList<PhieuTraHang> listPhieuTraHang = db.getListPhieuTraHang();

        if (listPhieuTraHang.size() == 0) { // Nếu danh sách trống
            System.out.println("khong tim thay phieu tra hang nao");
            return;
        }

        // In từng phiếu trả hàng
        for (int i = 0; i < listPhieuTraHang.size(); i++) {
            System.out.println("---------------------------");
            System.out.println(listPhieuTraHang.get(i));
        }
    }

    // Tạo mới phiếu trả hàng
    public void taoPhieuTraHang() {
        DanhSachPhieuTraHang danhSachPhieuTraHang = db.getDanhSachPhieuTraHang();
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();

        // Tìm khách hàng bằng số điện thoại
        KhachHang khachHang = danhSachKhachHang.timKhachHangTheoSdt(Nhap.nhapStr("Nhap sdt khach hang de tra hang : "));
        if (khachHang == null) {
            System.out.println("Khong tim thay khach hang");
            return;
        }

        // Tìm sản phẩm cần trả bằng serial
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        SanPham sanPham = danhSachSanPham.tim(Nhap.nhapStr("Hay hay nhap ma serial san pham can tra hang : "));
        if (sanPham == null) {
            System.out.println("Khong tim thay san pham");
            return;
        }

        // Kiểm tra nếu sản phẩm đã được trả
        if (sanPham.getTraHang()) {
            System.out.println("Khach hang da tra san pham nay roi");
            return;
        }
        DanhSachPhieuBaoHanh danhSachPhieuBaoHanh = new DanhSachPhieuBaoHanh(khachHang.getListPhieuBaoHanh());
        if (danhSachPhieuBaoHanh.tim(sanPham) != null) {
            System.out.println("San pham dang duoc bao hanh");
            return;
        }
        // Tìm chi tiết hóa đơn chứa sản phẩm này
        DanhSachChiTietHoaDon danhSachChiTietHoaDon = db.getDanhSachChiTietHoaDon();
        ChiTietHoaDon chiTietHoaDon = danhSachChiTietHoaDon.tim(sanPham);

        // Tìm hóa đơn tương ứng
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        HoaDon hoaDon = danhSachHoaDon.tim(chiTietHoaDon);
        if (hoaDon == null) {
            System.out.println("Khach hang chua tung mua san pham " + sanPham.getSerial());
            return;
        }

        // Kiểm tra thời gian trả hàng <= 3 ngày kể từ ngày mua
        if (ThoiGian.khoangCachNgay(hoaDon.getNgayTaoHoaDon(), ThoiGian.layNgayHienTaiStr()) > 3) {
            System.out.println("Chi duoc tra hang neu loi trong vong 3 ngay");
            return;
        }

        // Đánh dấu sản phẩm là đã trả
        sanPham.setTraHang(true);
        sanPham.setDaBan(false);

        // Tính tiền hoàn lại của sản phẩm (bảo gồm cả mã giảm giá sản phẩm đó)
        DanhSachMaGiamGia danhSachMaGiamGia = new DanhSachMaGiamGia(chiTietHoaDon.getListMaGiamGia());
        long tienTraLai = danhSachMaGiamGia.giaSanPhamSauKhiApDungTatCa(sanPham);
        System.out.println("So tien hoan lai cho khach hang : " + tienTraLai);

        // Cập nhật thông tin khách hàng và hóa đơn
        khachHang.giamTienDaChi(tienTraLai);
        hoaDon.giamThanhTien(tienTraLai);

        // Cập nhật lại hạng thành viên của khách
        DanhSachHangThanhVien danhSachHangThanhVien = db.getDanhSachHangThanhVien();
        danhSachHangThanhVien.setHangThanhVienChoKhachHang(khachHang);

        // Tạo phiếu trả hàng mới
        PhieuTraHang pth = TaoDoiTuong.taoPhieuTraHang(khachHang, sanPham, db);

        // Thêm phiếu trả hàng vào danh sách
        if (danhSachPhieuTraHang.them(pth)) {
            System.out.println("Tao phieu tra hang thanh cong!");
            khachHang.themPhieuTraHang(pth);
        } else {
            System.out.println("Tao phieu tra hang that bai!");
        }
    }

    // Xóa phiếu trả hàng
    public void xoaPhieuTraHang() {
        DanhSachPhieuTraHang danhSachPhieuTraHang = db.getDanhSachPhieuTraHang();
        String ma = Nhap.nhapStr("Nhap ma phieu can xoa: ");
        if (danhSachPhieuTraHang.xoa(ma)) {
            System.out.println("Xoa phieu tra hang thanh cong!");
        } else {
            System.out.println("Khong tim thay ma phieu hoac xoa that bai!");
        }
    }

    // Hiển thị menu sửa phiếu trả hàng
    private void xuatSuaTraHang() {
        System.out.println("===== SUA PHIEU TRA HANG =====");
        System.out.println("1. Sua khach hang");
        System.out.println("2. Sua san pham");
        System.out.println("3. Sua ngay tra");
        System.out.println("4. Sua ly do tra");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    // Sửa thông tin cụ thể trong phiếu trả hàng
    private void suaThanhPhanPhieuTraHang(PhieuTraHang pth, int chon) {
        switch (chon) {
            case 1: // Sửa khách hàng
                DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
                KhachHang khachHang = danhSachKhachHang.tim(Nhap.nhapStr("Nhap ma khach hang de them vao : "));
                if (khachHang == null) {
                    System.out.println("Khong tim thay khach hang de them vao");
                    return;
                }
                pth.setKhachHang(khachHang);
                System.out.println("Da thay doi khach hang");
                break;

            case 2: // Sửa sản phẩm
                DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
                SanPham sanPham = danhSachSanPham.tim(Nhap.nhapStr("Nhap ma serial san pham de them vao : "));
                if (sanPham == null) {
                    System.out.println("Khong tim thay san pham de them vao");
                    return;
                }
                pth.setSanPham(sanPham);
                break;

            case 3: // Sửa ngày trả
                pth.setNgayTra(Nhap.nhapNgay("Nhap ngay tra moi (yyyy-MM-dd): "));
                System.out.println("Da thay doi ngay tra");
                break;

            case 4: // Sửa lý do trả
                pth.setLyDoTra(Nhap.nhapStr("Nhap ly do moi: "));
                System.out.println("Da thay doi ly do tra");
                break;

            case 0:
                System.out.println("Thoat sua phieu tra hang.");
                break;

            default:
                System.out.println("Lua chon khong hop le!");
                break;
        }
    }

    // Sửa phiếu trả hàng
    public void suaPhieuTraHang() {
        DanhSachPhieuTraHang danhSachPhieuTraHang = db.getDanhSachPhieuTraHang();
        String ma = Nhap.nhapStr("Nhap ma phieu can sua: ");
        PhieuTraHang pth = danhSachPhieuTraHang.tim(ma);

        if (pth == null) {
            System.out.println("Khong tim thay phieu tra hang!");
            return;
        }

        int tiep = 1;
        while (tiep == 1) {
            XoaManHinh.xoa();
            System.out.println();
            System.out.println("------------------------");
            System.out.println(pth);
            System.out.println("------------------------");
            xuatSuaTraHang();
            int chon = Nhap.nhapInt("Chon muc can sua: ");
            if (chon == 0) {
                return;
            }
            suaThanhPhanPhieuTraHang(pth, chon);
            Nhap.pause();
        }
    }

    // Tra cứu phiếu trả hàng theo mã
    public void traCuuPhieuTraHang() {
        DanhSachPhieuTraHang danhSachPhieuTraHang = db.getDanhSachPhieuTraHang();
        String ma = Nhap.nhapStr("Nhap ma phieu bao hanh can tim : ");
        PhieuTraHang phieuTraHang = danhSachPhieuTraHang.tim(ma);
        if (phieuTraHang == null) {
            System.out.println("khong tim thay");
        } else {
            System.out.println("---------------------------");
            System.out.println(phieuTraHang);
            System.out.println("---------------------------");
        }
    }

    // Xuất menu chính
    private void xuatMenu() {
        System.out.println("======= Quan Ly Phieu Tra Hang =======");
        System.out.println("1. Tao phieu tra hang");
        System.out.println("2. Sua phieu tra hang");
        System.out.println("3. Tra cuu phieu tra hang");
        System.out.println("4. Xoa phieu tra hang");
        System.out.println("5. Hien thi tat ca phieu tra hang");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    // Xử lý lựa chọn menu
    private void thucHienChucNang(int chon) {
        switch (chon) {
            case 1 -> taoPhieuTraHang();
            case 2 -> suaPhieuTraHang();
            case 3 -> traCuuPhieuTraHang();
            case 4 -> xoaPhieuTraHang();
            case 5 -> hienThiTatCaPhieuTraHang();
            case 0 -> System.out.println("Da thoat menu phieu tra hang!");
            default -> System.out.println("Lua chon khong hop le!");
        }
    }

    // Vòng lặp menu chính
    public void menu() {
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
