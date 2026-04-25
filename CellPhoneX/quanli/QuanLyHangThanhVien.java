package quanli;

import java.util.ArrayList;

import danhsach.DanhSachHangThanhVien;
import danhsach.DanhSachMaGiamGia;
import database.Database;
import model.HangThanhVien;
import model.MaGiamGia;
import util.Nhap;
import util.TaoDoiTuong;
import util.XoaManHinh;

public class QuanLyHangThanhVien {
    private Database db; // lưu tham chiếu đến cơ sở dữ liệu chính

    public QuanLyHangThanhVien(Database db) {
        this.db = db; // khởi tạo với database
    }

    public void xemTatCaHangThanhVien() {
        ArrayList<HangThanhVien> listHangThanhVien = db.getListHangThanhVien(); // lấy danh sách hạng TV
        if (listHangThanhVien == null || listHangThanhVien.size() == 0) { // nếu rỗng thì báo không có
            System.out.println("Khong tim thay hang thanh vien nao");
            return;
        }
        for (int i = 0; i < listHangThanhVien.size(); i++) { // duyệt và in từng hạng
            System.out.println("---------------------------");
            System.out.println(listHangThanhVien.get(i));
        }
    }

    /// menu sửa hạng thành viên
    private void xuatSuaHangThanhVien() {
        System.out.println("1. Sua ten hang thanh vien");
        System.out.println("2. Sua mo ta hang thanh vien");
        System.out.println("3. Tao them ma giam gia cho hang thanh vien");
        System.out.println("4. Xoa ma giam gia cho hang thanh vien");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    // xử lý từng chức năng sửa
    private void suaThanhPhanHangThanhVien(HangThanhVien hangThanhVien, int luachon) {
        switch (luachon) {
            case 1:
                hangThanhVien.setTenHang(Nhap.nhapStr("Nhap ten hang thanh vien moi : ")); // đổi tên
                System.out.println("Da sua ten cua hang thanh vien");
                break;
            case 2:
                hangThanhVien.setMoTa(Nhap.nhapStr("Nhap mo ta cua hang thanh vien : ")); // đổi mô tả
                System.out.println("Da sua mo ta cua hang thanh vien");
                break;
            case 3:
                DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGiaDq(); // lấy danh sách mã độc quyền
                MaGiamGia maGiamGia = TaoDoiTuong.taoMaGiamGiaDocQuyen(db, hangThanhVien); // tạo mã mới
                danhSachMaGiamGia.them(maGiamGia); // thêm vào danh sách chung
                hangThanhVien.themMaGiamGia(maGiamGia); // gán vào hạng
                System.out.println("Them ma giam gia thanh cong");
                break;
            case 4:
                DanhSachMaGiamGia maGiamGiaService1 = db.getDanhSachMaGiamGiaDq(); // lấy DS mã
                MaGiamGia maGiamGia1 = maGiamGiaService1
                        .tim(Nhap.nhapStr("Nhap ma giam gia de xoa khoi hang thanh vien : ")); // tìm mã cần xóa
                if (maGiamGia1 == null) {
                    System.out.println("Khong tim thay ma giam gia");
                    return;
                }
                hangThanhVien.xoaMaGiamGia(maGiamGia1); // xóa mã khỏi hạng
                System.out.println("Da xoa ma giam gia thanh cong");
                break;
            default:
                System.out.println("Lua chon khong hop le");
                break;
        }
    }

    public void suaHangThanhVien() {
        DanhSachHangThanhVien hangThanhVienService = db.getDanhSachHangThanhVien(); // lấy DS hạng
        HangThanhVien hangThanhVien = hangThanhVienService.tim(Nhap.nhapStr("Nhap ten hang thanh vien can sua : ")); // tìm
                                                                                                                     // theo
                                                                                                                     // tên
        if (hangThanhVien == null) {
            System.out.println("Khong tim thay hang thanh vien");
            return;
        }
        while (true) { // vòng lặp menu sửa
            XoaManHinh.xoa();
            System.out.println();
            System.out.println("------------------------");
            System.out.println(hangThanhVien); // hiển thị thông tin hiện tại
            System.out.println("------------------------");
            xuatSuaHangThanhVien(); // in menu
            int luaChon = Nhap.nhapInt("Nhap lua chon : ");
            if (luaChon == 0) {
                return;
            }
            suaThanhPhanHangThanhVien(hangThanhVien, luaChon); // xử lý chức năng
            Nhap.pause();
        }
    }

    ///// tra cứu hạng theo tên
    public void traCuuHangThanhVien() {
        String ten = Nhap.nhapStr("Nhap ten hang thanh vien can tra cuu: ");
        DanhSachHangThanhVien service = db.getDanhSachHangThanhVien();
        HangThanhVien htv = service.tim(ten); // tìm hạng theo tên
        if (htv == null) {
            System.out.println("Khong tim thay hang thanh vien!");
            return;
        }
        System.out.println(htv); // in thông tin hạng
        ArrayList<MaGiamGia> listMaGiamGia = htv.getListMaGiamGiaDQ(); // lấy danh sách mã giảm giá
        if (listMaGiamGia != null && listMaGiamGia.size() > 0) {
            System.out.println("Danh sach ma giam gia co trong hang thanh vien : ");
            for (MaGiamGia maGiamGia : listMaGiamGia) {
                System.out.println(maGiamGia); // in từng mã
            }
        }
    }

    private void xuatMenu() { // menu chính
        System.out.println("======= Quan Ly Hang Thanh Vien =======");
        System.out.println("1. Tra cuu hang thanh vien");
        System.out.println("2. Sua hang thanh vien");
        System.out.println("3. Hien thi thong tin tat ca hang thanh vien");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    private void thucHienChucNang(int chon) { // xử lý chọn menu
        switch (chon) {
            case 1 -> traCuuHangThanhVien();
            case 2 -> suaHangThanhVien();
            case 3 -> xemTatCaHangThanhVien();
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    public void menu() { // vòng lặp menu chính
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
