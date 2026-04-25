package quanli;

import java.util.ArrayList;

import danhsach.DanhSachMaGiamGia;
import danhsach.DanhSachSanPham;
import database.Database;
import model.KhachHang;
import model.MaGiamGia;
import model.SanPham;
import util.Nhap;
import util.TaoDoiTuong;
import util.XoaManHinh;

public class QuanLyMaGiamGia {
    private Database db;

    // constructor - nhận database để thao tác
    public QuanLyMaGiamGia(Database db) {
        this.db = db;
    }

    // tìm kiếm mã giảm giá theo mã nhập từ người dùng
    public void timKiemMaGiamGia() {
        DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGia();
        String ma = Nhap.nhapStr("nhap ma cua ma giam gia can tim: ");
        MaGiamGia mGG = danhSachMaGiamGia.tim(ma);
        if (mGG == null) {
            System.out.println("khong tim thay ma giam gia!");
        } else {
            System.out.println(mGG);
        }
    }

    // hiển thị toàn bộ mã giảm giá thường và độc quyền
    public void xemTatCaMaGiamGia() {
        System.out.println("====Tat ca ma giam gia====");
        ArrayList<MaGiamGia> listMaGiamGia = db.getListMaGiamGia();
        // in danh sách mã giảm giá thường
        if (!(listMaGiamGia == null || listMaGiamGia.size() == 0)) {
            System.out.println("Danh sach ma giam gia thuong : ");
            System.out.println();
            for (int i = 0; i < listMaGiamGia.size(); i++) {
                System.out.println(listMaGiamGia.get(i));
            }
        }
        // in danh sách mã giảm giá độc quyền
        ArrayList<MaGiamGia> listMaGiamGiaDq = db.getListMaGiamGiaDq();
        if (!(listMaGiamGiaDq == null || listMaGiamGiaDq.size() == 0)) {
            System.out.println("Danh sach ma giam gia doc quyen : ");
            System.out.println();
            for (int i = 0; i < listMaGiamGiaDq.size(); i++) {
                System.out.println(listMaGiamGiaDq.get(i));
            }
        }
    }

    // thêm một mã giảm giá mới và gán cho tất cả khách hàng hiện có
    public void themMaGiamGia() {
        DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGia();
        MaGiamGia mGG = TaoDoiTuong.taoMaGiamGia(db);
        if (danhSachMaGiamGia.them(mGG)) {
            System.out.println("them ma giam gia thanh cong!");
            // thêm bản sao mã giảm giá cho tất cả khách hàng
            for (KhachHang khachHang : db.getListKhachHang()) {
                khachHang.themMaGiamGia(new MaGiamGia(mGG));
            }
        } else {
            System.out.println("them ma giam gia that bai!");
        }
    }

    // xóa mã giảm giá khỏi hệ thống và khỏi danh sách của từng khách hàng
    public void xoaMaGiamGia() {
        DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGia();
        String ma = Nhap.nhapStr("nhap ma cua ma giam gia can xoa: ");
        MaGiamGia mGG = danhSachMaGiamGia.tim(ma);
        if (mGG == null) {
            System.out.println("khong tim thay ma giam gia can xoa!");
        } else {
            if (danhSachMaGiamGia.xoa(mGG)) {
                System.out.println("xoa ma giam gia thanh cong!");
                // xóa mã giảm giá khỏi từng khách hàng
                for (KhachHang khachHang : db.getListKhachHang()) {
                    khachHang.xoaMaGiamGia(mGG);
                }
            } else {
                System.out.println("xoa ma giam gia that bai!");
            }
        }
    }

    // menu hiển thị các mục chỉnh sửa thông tin mã giảm giá
    public void xuatMenuSuaMaGiamGia() {
        System.out.println("====Sua Ma Giam Gia====");
        System.out.println("1. sua ten ma giam gia");
        System.out.println("2. sua loai doanh muc");
        System.out.println("3. sua loai thuong hieu");
        System.out.println("4. sua so tien giam");
        System.out.println("5. sua ngay bat dau");
        System.out.println("6. sua ngay ket thuc");
        System.out.println("0. thoat");
        System.out.println("---------------------------");
    }

    // thực hiện chức năng sửa tương ứng theo lựa chọn người dùng
    private void thucHienChucNangSua(MaGiamGia mGG, int choice) {
        switch (choice) {
            case 1:
                mGG.setTenMa(Nhap.nhapStr("nhap ten moi: "));
                System.out.println("thay doi ten ma giam gia thanh cong!");
                break;
            case 2:
                mGG.setLoaiDoanhMuc(Nhap.nhapStr("nhap loai danh muc moi: "));
                System.out.println("thay doi danh muc moi thanh cong!");
                break;
            case 3:
                mGG.setLoaiThuongHieu(Nhap.nhapStr("nhap loai thuong hieu moi: "));
                System.out.println("thay doi loai thuong hieu thanh cong!");
                break;
            case 4:
                mGG.setTienGiam(Nhap.nhapStr("nhap so tien giam moi: "));
                System.out.println("da thay doi so tien giam thanh cong!");
                break;
            case 5:
                mGG.setNgayBatDau(Nhap.nhapNgay("nhap ngay bat dau moi (yyyy-mm-dd): "));
                System.out.println("thay doi ngay batg dau moi thanh cong!");
                break;
            case 6:
                mGG.setNgayKetThuc(Nhap.nhapNgay("nhap ngay ket thuc moi (yyyy-mm-dd): "));
                System.out.println("thay doi ngay ket thuc thanh cong!");
                break;
            case 0:
                System.out.println("da thoat!");
            default:
                System.out.println("lua chon khong hop le!");
        }
    }

    // menu chính để sửa thông tin của 1 mã giảm giá cụ thể
    public void suaMaGiamGia() {
        DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGia();
        String ma = Nhap.nhapStr("nhap ma cua ma giam gia can sua: ");
        MaGiamGia mGG = danhSachMaGiamGia.tim(ma);
        if (mGG == null) {
            System.out.println("Khong tim thay ma");
            return;
        }
        while (true) {
            System.out.println();
            System.out.println("------------------------");
            System.out.println(mGG);
            System.out.println("------------------------");
            xuatMenuSuaMaGiamGia();
            int choice = Nhap.nhapInt("nhap lua chon: ");
            if (choice == 0) {
                return;
            }
            thucHienChucNangSua(mGG, choice);
            Nhap.pause();
        }
    }

    // liệt kê các mã giảm giá có thể áp dụng cho 1 sản phẩm cụ thể
    public void maGiamGiaApDungChoSp() {
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        SanPham sanPham = danhSachSanPham.tim(Nhap.nhapStr("Nhap ma serial san pham de xem : "));
        if (sanPham == null) {
            System.out.println("Khong tim thay san pham");
            return;
        }
        DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGia();
        ArrayList<MaGiamGia> listMaGiamGia = danhSachMaGiamGia.listMaGiamGiaChoSp(sanPham);
        if (listMaGiamGia.size() == 0) {
            System.out.println("Khong co ma giam gia thoa man");
            return;
        }

        for (int i = 0; i < listMaGiamGia.size(); i++) {
            System.out.println(listMaGiamGia.get(i));
        }
    }

    // xuất menu quản lý mã giảm giá tổng thể
    private void xuatMenu() {
        System.out.println("======= Quan Ly Lich Trong Tuan =======");
        System.out.println("1. Them ma giam gia");
        System.out.println("2. Xoa ma giam gia");
        System.out.println("3. Tra cuu ma giam gia");
        System.out.println("4. Xem ma giam gia ap dung cho san pham");
        System.out.println("5. Sua ma giam gia");
        System.out.println("6. Xem tat ca ma giam gia");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    // xử lý hành động tương ứng với lựa chọn người dùng
    private void thucHienChucNang(int luaChon) {
        switch (luaChon) {
            case 1 -> themMaGiamGia();
            case 2 -> xoaMaGiamGia();
            case 3 -> timKiemMaGiamGia();
            case 4 -> maGiamGiaApDungChoSp();
            case 5 -> suaMaGiamGia();
            case 6 -> xemTatCaMaGiamGia();
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    // menu chính cho quản lý mã giảm giá (vòng lặp liên tục đến khi thoát)
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
