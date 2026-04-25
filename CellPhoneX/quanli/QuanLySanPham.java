package quanli;

import java.util.ArrayList;

import danhsach.DanhSachBaoHanh;
import danhsach.DanhSachSanPham;
import danhsach.DanhSachThongTinSanPham;
import database.Database;
import model.BaoHanh;
import model.SanPham;
import model.ThongTinSanPham;
import util.Nhap;
import util.ThoiGian;
import util.XoaManHinh;

public class QuanLySanPham {
    private Database db; // CSDL lưu toàn bộ thông tin

    public QuanLySanPham(Database db) {
        this.db = db; // Gán đối tượng database
    }

    /// hiển thị tất cả sản phẩm có trong kho
    public void hienThiSanPhamTrongKho() {
        int dem = 0; // đếm sản phẩm còn trong kho
        System.out.println("Danh sach san pham dang co trong kho");
        for (SanPham sanPham : db.getListSanPham()) {
            System.out.println(sanPham);
            if (!sanPham.getDaBan()) { // nếu chưa bán thì tăng đếm
                dem++;
            }
        }
        System.out.println();
        System.out.println("Co tong cong : " + dem + " san pham ton tai kha dung trong kho");
    }

    /// Nhập kho (thêm sản phẩm mới)
    public void nhapKho() {
        System.out.println("==== Nhap kho ====");
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        // tìm thông tin sản phẩm cần nhập
        ThongTinSanPham thongTinSanPham = danhSachThongTinSanPham.tim(Nhap.nhapStr("Nhap ma thong tin san pham : "));
        if (thongTinSanPham == null) {
            System.out.println("Khong tim thay thong tin san pham can nhap kho");
            return;
        }
        int soLuong = Nhap.nhapInt("Nhap so luong san pham can them : ");
        while (soLuong > 0) {
            String serial = Nhap.nhapStr("Nhap so serial cua " + thongTinSanPham.getMa() + " : ");
            // kiểm tra trùng serial
            if (db.getKhoSerial().contains(serial)) {
                System.out.println("Ma serial da ton trong database");
                continue;
            } else {
                db.getKhoSerial().add(serial);
                soLuong--;
            }
            SanPham sanPham = new SanPham(serial, thongTinSanPham);
            danhSachSanPham.them(sanPham);
        }
    }

    // xóa sản phẩm khỏi kho
    public void xoaSanPham() {
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        // tìm sản phẩm theo serial
        SanPham sanPham = danhSachSanPham.tim(Nhap.nhapStr("Nhap ma serial san pham can xoa: "));
        if (sanPham == null) {
            System.out.println("Khong tim thay san pham can xoa");
            return;
        }
        danhSachSanPham.xoa(sanPham);
        System.out.println("Xoa san pham thanh cong");
    }

    // hiển thị tất cả sản phẩm của một thông tin sản phẩm
    public void timSanPhamCuaTTSanPham() {
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
        ThongTinSanPham thongTinSanPham = danhSachThongTinSanPham.tim(Nhap.nhapStr("Nhap ma thong tin san pham : "));
        if (thongTinSanPham == null) {
            System.out.println("Khong tim thay thong tin san pham");
            return;
        }
        System.out.println("Danh sach san pham cua thong tin san pham " + thongTinSanPham.getMa());
        for (SanPham sanPham : db.getListSanPham()) {
            if (sanPham.getThongTinSanPham().equals(thongTinSanPham)) {
                System.out.println(sanPham);
            }
        }
    }

    // hiển thị tất cả sản phẩm đã bị trả hàng
    public void hienThiSanPhamTraHang() {
        System.out.println("Danh sach san pham bi tra hang :");
        for (SanPham sanPham : db.getListSanPham()) {
            if (sanPham.getTraHang()) {
                System.out.println(sanPham);
            }
        }
    }

    // hiển thị sản phẩm đã bán
    public void hienThiSanPhamDaBan() {
        System.out.println("Danh sach san pham da ban :");
        for (SanPham sanPham : db.getListSanPham()) {
            if (sanPham.getDaBan()) {
                System.out.println(sanPham);
            }
        }
    }

    // Menu chỉnh sửa sản phẩm
    private void xuatSuaSanPham() {
        System.out.println("1. Sua serial");
        System.out.println("2. Sua thong tin san pham");
        System.out.println("3. Sua bao hanh");
        System.out.println("4. Sua trang thai da ban");
        System.out.println("0. Thoat");
    }

    // Xử lý logic sửa từng phần của sản phẩm
    private void suaThanhPhanSanPham(int luaChon, SanPham sanPham) {
        switch (luaChon) {
            case 1: // sửa serial
                String serial = Nhap.nhapStr("Nhap ma serial moi : ");
                if (db.getKhoSerial().contains(serial)) {
                    System.out.println("Serial da ton tai trong database");
                    return;
                }
                sanPham.setSerial(serial);
                System.out.println("Da thay doi");
                break;

            case 2: // sửa thông tin sản phẩm
                DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
                ThongTinSanPham thongTinSanPham = danhSachThongTinSanPham
                        .tim(Nhap.nhapStr("Nhap ma thong thong tin san pham moi :"));
                if (thongTinSanPham == null) {
                    System.out.println("Khong tim thay thong tin san pham");
                    return;
                }
                sanPham.setThongTinSanPham(thongTinSanPham);
                System.out.println("Da thay doi");
                break;

            case 3: // sửa bảo hành
                DanhSachBaoHanh danhSachBaoHanh = db.getDanhSachBaoHanh();
                ArrayList<BaoHanh> listBaoHanh = danhSachBaoHanh.timBaoHanh(sanPham);
                for (int i = 0; i < listBaoHanh.size(); i++) {
                    System.out.println(i + ". " + listBaoHanh.get(i).getLoaiBaoHanh());
                }
                System.out.println("-1. Xoa bao hanh");
                luaChon = Nhap.nhapInt(
                        "Nhap lua chon bao hanh san pham cho " + sanPham.getMa() + " " + sanPham.getSerial() + " : ");
                BaoHanh baoHanhGoc = null;
                if (luaChon >= 0 && luaChon < listBaoHanh.size()) {
                    baoHanhGoc = listBaoHanh.get(luaChon);
                    BaoHanh baoHanh = new BaoHanh(baoHanhGoc);
                    baoHanh.setNgayBatDau(ThoiGian.layNgayHienTaiStr());
                    baoHanh.setNgayKetThuc();
                    sanPham.setBaoHanh(baoHanh);
                } else if (luaChon == -1) {
                    sanPham.setBaoHanh(null);
                } else {
                    System.out.println("Lua chon khong hop le");
                    return;
                }
                System.out.println("Da thay doi");
                break;

            case 4: // sửa trạng thái đã bán
                System.out.println("Trang thai hien tai la : " + sanPham.getDaBan());
                int xacNhan = Nhap.nhapInt("(1)Thay doi  (Khac)giu nguyen : ");
                if (xacNhan == 1) {
                    sanPham.doiDaBan();
                    System.out.println("Da thay doi");
                }
                break;

            default:
                System.out.println("Lua chon khong hop le");
                break;
        }
    }

    // menu sửa sản phẩm tổng hợp
    public void suaSanPham() {
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        SanPham sanPham = danhSachSanPham.tim(Nhap.nhapStr("Nhap ma serial san pham can sua : "));
        if (sanPham == null) {
            System.out.println("Khong tim duoc san pham de sua");
            return;
        }
        while (true) {
            XoaManHinh.xoa();
            System.out.println("------------------------");
            System.out.println(sanPham);
            System.out.println("------------------------");
            xuatSuaSanPham();
            int luaChon = Nhap.nhapInt("Nhap lua chon : ");
            if (luaChon == 0) {
                return;
            }
            suaThanhPhanSanPham(luaChon, sanPham);
        }
    }

    // menu chính của quản lý sản phẩm
    private void xuatMenu() {
        System.out.println("======= Quan Ly San Pham =======");
        System.out.println("1. Hien thi tat ca san pham trong kho");
        System.out.println("2. Nhap kho (them san pham moi)");
        System.out.println("3. Xoa san pham");
        System.out.println("4. Tim san pham cua thong tin san pham");
        System.out.println("5. Hien thi san pham bi tra hang");
        System.out.println("6. Hien thi san pham da ban");
        System.out.println("7. Sua san pham");
        System.out.println("0. Thoat");
    }

    // vòng lặp menu điều khiển
    public void menu() {
        while (true) {
            XoaManHinh.xoa();
            xuatMenu();
            int luaChon = Nhap.nhapInt("Nhap lua chon: ");
            switch (luaChon) {
                case 1:
                    hienThiSanPhamTrongKho();
                    break;
                case 2:
                    nhapKho();
                    break;
                case 3:
                    xoaSanPham();
                    break;
                case 4:
                    timSanPhamCuaTTSanPham();
                    break;
                case 5:
                    hienThiSanPhamTraHang();
                    break;
                case 6:
                    hienThiSanPhamDaBan();
                    break;
                case 7:
                    suaSanPham();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
            Nhap.pause(); // dừng chờ người dùng nhấn Enter
        }
    }
}
