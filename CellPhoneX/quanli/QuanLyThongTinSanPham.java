package quanli;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import danhsach.DanhSachThongTinSanPham;
import database.Database;
import model.ThongTinSanPham;
import util.Nhap;
import util.TaoDoiTuong;
import util.XoaManHinh;

public class QuanLyThongTinSanPham {
    private Database db; // Tham chiếu cơ sở dữ liệu

    public QuanLyThongTinSanPham(Database db) {
        this.db = db;
    }

    // Hiển thị tất cả thông tin sản phẩm
    public void hienThiTatCaThongTinSanPham() {
        System.out.println("Danh sach thong tin san san pham:");
        for (ThongTinSanPham ThongTinSp : db.getListThongTinSanPham()) {
            System.out.println(ThongTinSp);
        }
    }

    // Thêm thông tin sản phẩm mới
    public void themThongTinSanPham() {
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
        ThongTinSanPham sp = TaoDoiTuong.taoSanPham(db);
        if (danhSachThongTinSanPham.them(sp)) {
            System.out.println("Them san pham thanh cong!");
        } else {
            System.out.println("Them san pham khong thanh cong!");
        }
    }

    // Xóa thông tin sản phẩm theo mã
    public void xoaThongTinSanPham() {
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
        String ma = Nhap.nhapStr("Nhap ma san pham can xoa: ");
        if (danhSachThongTinSanPham.xoa(ma)) {
            System.out.println("Xoa thong tin san pham thanh cong!");
        } else {
            System.out.println("Xoa thong tin san pham khong thanh cong!");
        }
    }

    // Hiển thị menu sửa sản phẩm
    public void xuatSuaSanPham() {
        System.out.println("===== Menu Sua San Pham =====");
        System.out.println("1. Sua ten san pham");
        System.out.println("2. Sua danh muc");
        System.out.println("3. Sua thuong hieu");
        System.out.println("4. Sua gia");
        System.out.println("5. Sua so luong ton kho");
        System.out.println("6. Sua mo ta");
        System.out.println("7. Sua trang thai");
        System.out.println("0. Thoat");
    }

    // Sửa từng thành phần thông tin sản phẩm
    private void suaThanhPhanThongTinSanPham(ThongTinSanPham sp, int chon) {
        switch (chon) {
            case 1:
                sp.setTen(Nhap.nhapStr("Nhap ten san pham moi: "));
                System.out.println("Da thay doi");
                break;
            case 2:
                sp.setDanhMuc(Nhap.nhapStr("Nhap danh muc moi: "));
                System.out.println("Da thay doi");
                break;
            case 3:
                sp.setThuongHieu(Nhap.nhapStr("Nhap thuong hieu moi: "));
                System.out.println("Da thay doi");
                break;
            case 4:
                sp.setGia(Nhap.nhapLong("Nhap gia moi: "));
                System.out.println("Da thay doi");
                break;
            case 5:
                sp.setTonKho(Nhap.nhapInt("Nhap so luong ton kho moi: "));
                System.out.println("Da thay doi");
                break;
            case 6:
                sp.setMoTa(Nhap.nhapStr("Nhap mo ta moi: "));
                System.out.println("Da thay doi");
                break;
            case 7:
                sp.setTrangThai(Nhap.nhapStr("Nhap trang thai moi: "));
                System.out.println("Da thay doi");
                break;
            case 0:
                System.out.println("Thoat sua san pham");
                break;
            default:
                System.out.println("Lua chon khong hop le");
        }
    }

    // Chức năng sửa thông tin sản phẩm
    public void suaThongTinSanPham() {
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
        ThongTinSanPham sp = danhSachThongTinSanPham.tim(Nhap.nhapStr("Nhap ma san pham can sua : "));
        if (sp == null) {
            System.out.println("khong tim thay ma san pham");
            return;
        }
        int xacNhan = 1;
        while (xacNhan == 1) {
            XoaManHinh.xoa();
            System.out.println();
            System.out.println("------------------------");
            System.out.println(sp);
            System.out.println("------------------------");
            xuatSuaSanPham();
            int chon = Nhap.nhapInt("Chon muc can sua: ");
            if (chon == 0) {
                return;
            }
            suaThanhPhanThongTinSanPham(sp, chon);
            Nhap.pause();
        }
    }

    // Tra cứu thông tin sản phẩm theo tên hoặc mã
    public void traCuuThongTinSanPham() {
        String tuKhoa = Nhap.nhapStr("Nhap tu khoa de tim : ");
        boolean timThay = false;
        ArrayList<ThongTinSanPham> listSanPham = db.getListThongTinSanPham();
        if (listSanPham == null || listSanPham.size() == 0) {
            System.out.println("khong tim thay san pham");
            return;
        }
        // Tìm theo tên
        Set<Integer> setLuaChon = new HashSet<>();
        for (int i = 0; i < listSanPham.size(); i++) {
            if (listSanPham.get(i).getTen().contains(tuKhoa)) {
                timThay = true;
                System.out.println(i + ". " + listSanPham.get(i).getMa() + " " + listSanPham.get(i).getTen() + " "
                        + listSanPham.get(i).getGia() + " " + listSanPham.get(i).getThuongHieu());
                        setLuaChon.add(i);
            }
        }

        if (timThay) {
            int luaChon = Nhap.nhapInt("lua chon san pham de hien thi : ");
             if (!setLuaChon.contains(luaChon)) {
                System.out.println("Lua chon khong nam trong pham vi");
                return;
            }
            if (listSanPham.size() > luaChon && 0 <= luaChon) {
                System.out.println("---------------------------");
                System.out.println(listSanPham.get(luaChon));
                System.out.println("---------------------------");
            } else {
                System.out.println("lua chon khong hop le");
            }
        } else {
            // Nếu tìm theo tên không thấy thì tìm theo mã
            for (int i = 0; i < listSanPham.size(); i++) {
                if (listSanPham.get(i).getMa().equals(tuKhoa)) {
                    System.out.println("---------------------------");
                    System.out.println(listSanPham.get(i));
                    System.out.println("---------------------------");
                    timThay = true;
                    break;
                }
            }
        }

        if (!timThay) {
            System.out.println("khong tim thay san pham");
        }
    }

    // Chuyển sang menu quản lý sản phẩm (có serial)
    public void QuanLySanPham() {
        QuanLySanPham quanLySanPham = new QuanLySanPham(db);
        quanLySanPham.menu();
    }

    // Hiển thị menu chính
    private void xuatMenu() {
        System.out.println("======= Quan Ly Thong Tin San Pham =======");
        System.out.println("1. Hien thi tat ca thong tin san pham");
        System.out.println("2. Them thong tin san pham");
        System.out.println("3. Xoa thong tin san pham");
        System.out.println("4. Sua thong tin san pham");
        System.out.println("5. Tra cuu thong tin san pham");
        System.out.println("6. Quan Ly San Pham trong kho");
        System.out.println("0. Thoat");
    }

    // Thực hiện chức năng theo lựa chọn
    private void thucHienChucNang(int choice) {
        switch (choice) {
            case 0 -> System.out.println("Da thoat Menu!");
            case 1 -> hienThiTatCaThongTinSanPham();
            case 2 -> themThongTinSanPham();
            case 3 -> xoaThongTinSanPham();
            case 4 -> suaThongTinSanPham();
            case 5 -> traCuuThongTinSanPham();
            case 6 -> QuanLySanPham();
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    // Menu chính lặp vô hạn cho đến khi thoát
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