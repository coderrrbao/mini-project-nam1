package quanli;

import java.util.ArrayList;

import danhsach.DanhSachNhanVien;
import database.Database;
import model.NhanVien;
import util.Nhap;
import util.TaoDoiTuong;
import util.XoaManHinh;

public class QuanLyNhanVien {
    private Database db; // Đối tượng cơ sở dữ liệu chung

    public QuanLyNhanVien(Database db) {
        this.db = db; // Gán database vào quản lý nhân viên
    }

    private void taoNhanVien() { // Tạo và thêm nhân viên mới
        DanhSachNhanVien danhSachNhanVien = db.getDanhSachNhanVien();
        NhanVien nv = TaoDoiTuong.taoNhanVien(db);
        danhSachNhanVien.them(nv);
        System.out.println("Da them nhan vien thanh cong.");
    }

    private void xuatSuaNhanVien() { // Menu lựa chọn khi sửa nhân viên
        System.out.println("1. Sua ten");
        System.out.println("2. Sua sdt");
        System.out.println("3. Sua gioi tinh");
        System.out.println("4. Sua ngay sinh");
        System.out.println("5. Sua cccd");
        System.out.println("6. Sua luong");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    private void suaThanhPhan(NhanVien nv, int luaChon) { // Sửa thuộc tính cụ thể theo lựa chọn
        switch (luaChon) {
            case 1 -> nv.setTen(Nhap.nhapStr("Nhap ten moi: "));
            case 2 -> nv.setSdt(Nhap.nhapStr("Nhap sdt moi: "));
            case 3 -> nv.setGioiTinh(Nhap.nhapStr("Nhap gioi tinh moi: "));
            case 4 -> nv.setNgaySinh(Nhap.nhapNgay("Nhap ngay sinh moi: "));
            case 5 -> nv.setCccd(Nhap.nhapStr("Nhap cccd moi: "));
            case 6 -> nv.setLuong(Nhap.nhapLong("Nhap luong moi: "));
            case 0 -> System.out.println("Thoat sua");
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    private void suaNhanVien() { // Sửa thông tin của nhân viên theo mã
        DanhSachNhanVien danhSachNhanVien = db.getDanhSachNhanVien();
        String ma = Nhap.nhapStr("Nhap ma nhan vien can sua: ");
        NhanVien nv = danhSachNhanVien.tim(ma);
        if (nv == null) {
            System.out.println("Khong tim thay nhan vien.");
            return;
        }
        while (true) {
            XoaManHinh.xoa(); // Xóa màn hình trước khi hiển thị
            System.out.println();
            System.out.println("------------------------");
            System.out.println(nv); // Hiển thị thông tin nhân viên hiện tại
            System.out.println("------------------------");
            xuatSuaNhanVien(); // Gọi menu sửa
            int chon = Nhap.nhapInt("Chon muc: ");
            if (chon == 0) {
                return; // Thoát nếu chọn 0
            }
            suaThanhPhan(nv, chon); // Gọi hàm sửa thuộc tính tương ứng
            Nhap.pause(); // Dừng màn hình chờ người dùng
        }
    }

    private void xoaNhanVien() { // Xóa nhân viên theo mã
        DanhSachNhanVien danhSachNhanVien = db.getDanhSachNhanVien();
        String ma = Nhap.nhapStr("Nhap ma nhan vien can xoa: ");
        NhanVien nhanVien = danhSachNhanVien.tim(ma);
        if (nhanVien != null) {
            danhSachNhanVien.xoa(ma);
            System.out.println("Da xoa nhan vien");
        } else {
            System.out.println("Khong tim thay nhan vien de xoa");
        }
    }

    private void traCuuNhanVien() { // Tìm kiếm và hiển thị nhân viên theo mã
        DanhSachNhanVien danhSachNhanVien = db.getDanhSachNhanVien();
        String ma = Nhap.nhapStr("Nhap ma nhan vien: ");
        NhanVien nv = danhSachNhanVien.tim(ma);
        if (nv == null)
            System.out.println("Khong tim thay.");
        else {
            System.out.println("---------------------------");
            System.out.println(nv);
            System.out.println("---------------------------");
        }
    }

    private void xemTatCaNhanVien() { // In danh sách tất cả nhân viên
        ArrayList<NhanVien> list = db.getListNhanVien();
        if (list == null || list.isEmpty()) {
            System.out.println("Chua co nhan vien nao.");
            return;
        }
        System.out.println("Danh sach nhan vien:");
        for (NhanVien nv : list) {
            System.out.println("---------------------------");
            System.out.println(nv);
        }
    }

    private void xuatMenu() { // In menu chính của quản lý nhân viên
        System.out.println("======= Quan Ly Nhan Vien =======");
        System.out.println("1. Them nhan vien");
        System.out.println("2. Sua thong tin");
        System.out.println("3. Xoa nhan vien");
        System.out.println("4. Tra cuu nhan vien");
        System.out.println("5. Xem tat ca nhan vien");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    public void thucHienChucNang(int chon) { // Thực hiện chức năng tương ứng với lựa chọn
        switch (chon) {
            case 0 -> {
                System.out.println("Thoat menu quan ly nhan vien");
                return;
            }
            case 1 -> taoNhanVien();
            case 2 -> suaNhanVien();
            case 3 -> xoaNhanVien();
            case 4 -> traCuuNhanVien();
            case 5 -> xemTatCaNhanVien();
            default -> System.out.println("Lua chon khong hop le!");
        }
    }

    public void menu() { // Menu lặp chính cho quản lý nhân viên
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
