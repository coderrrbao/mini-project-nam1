package quanli;

import java.util.ArrayList;

import danhsach.DanhSachQuanLy;
import database.Database;
import model.QuanLy;
import util.Nhap;
import util.TaoDoiTuong;
import util.XoaManHinh;

public class QuanLyDanhSachQuanLy {
    Database db; // cơ sở dữ liệu chính

    public QuanLyDanhSachQuanLy() {
    };

    public QuanLyDanhSachQuanLy(Database db) {
        this.db = db;
    }

    // hiển thị tất cả quản lý
    public void hienThiTatCaQuanLy() {
        ArrayList<QuanLy> listQuanLy = db.getListQuanLy(); // lấy danh sách quản lý
        if (listQuanLy == null || listQuanLy.isEmpty()) {
            System.out.println("chua co quang ly nao");
            return;
        }
        for (int i = 0; i < listQuanLy.size(); i++) {
            System.out.println("---------------------------");
            System.out.println(listQuanLy.get(i)); // in từng quản lý
        }
    }

    // thêm quản lý mới
    public void themQuanLy() {
        QuanLy ql = TaoDoiTuong.TaoDoiTuongQuanLy(db); // tạo đối tượng quản lý mới
        DanhSachQuanLy danhSachQuanLy = db.getDanhSachQuanLy(); // lấy danh sách quản lý
        if (danhSachQuanLy.them(ql)) {
            System.out.println("them quan ly thanh cong!");
        } else {
            System.out.println("them quang ly that bai!");
        }
    }

    // xóa quản lý theo mã
    public void xoaQuanLy() {
        DanhSachQuanLy danhSachQuanLy = db.getDanhSachQuanLy();
        String ma = Nhap.nhapStr("nhap ma quang ly can xoa: "); // nhập mã cần xóa
        QuanLy ql = danhSachQuanLy.tim(ma);
        if (ql == null) {
            System.out.println("khong tim thay quang ly can xoa!");
        }
        if (danhSachQuanLy.xoa(ma)) {
            System.out.println("xoa quan ly thanh cong");
        } else {
            System.out.println("xoa quan ly that bai!");
        }
    }

    // tra cứu quản lý theo mã
    public void traCuuQuanLy() {
        String ma = Nhap.nhapStr("nhap ma quang ly can tra cuu: ");
        DanhSachQuanLy danhSachQuanLy = db.getDanhSachQuanLy();
        QuanLy ql = danhSachQuanLy.tim(ma);
        if (ql == null) {
            System.out.println("khong tim thay quang ly can tra cuu!");
        }
        System.out.println(ql); // hiển thị thông tin quản lý
    }

    // xuất menu sửa quản lý
    public void xuatSuaQuanLy() {
        System.out.println("1. Sua ten");
        System.out.println("2. Sua sdt");
        System.out.println("3. Sua gioi tinh");
        System.out.println("4. Sua ngay sinh");
        System.out.println("5. Sua cccd");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    // thực hiện chức năng sửa theo lựa chọn
    public void thucHienChucNangSuaQuanLy(QuanLy ql, int luaChon) {
        switch (luaChon) {
            case 1 -> ql.setTen(Nhap.nhapStr("Nhap ten moi: "));
            case 2 -> ql.setSdt(Nhap.nhapStr("Nhap sdt moi: "));
            case 3 -> ql.setGioiTinh(Nhap.nhapStr("Nhap gioi tinh moi: "));
            case 4 -> ql.setNgaySinh(Nhap.nhapNgay("Nhap ngay sinh moi: "));
            case 5 -> ql.setCccd(Nhap.nhapStr("Nhap cccd moi: "));
            case 0 -> System.out.println("Thoat sua");
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    // sửa thông tin quản lý theo mã
    public void SuaQuanLy() {
        DanhSachQuanLy danhSachQuanLy = db.getDanhSachQuanLy();
        String ma = Nhap.nhapStr("Nhap ma quan ly can sua: "); // nhập mã quản lý cần sửa
        QuanLy ql = danhSachQuanLy.tim(ma);
        if (ql == null) {
            System.out.println("Khong tim thay quan ly can sua.");
            return;
        }
        while (true) { // cho phép sửa liên tục cho đến khi người dùng thoát
            XoaManHinh.xoa();
            System.out.println();
            System.out.println("------------------------");
            System.out.println(ql);
            System.out.println("------------------------");
            xuatSuaQuanLy(); // in menu sửa
            int chon = Nhap.nhapInt("Chon muc: ");
            if (chon==0){
                return;
            }
            thucHienChucNangSuaQuanLy(ql, chon); // sửa thuộc tính tương ứng
           Nhap.pause();
        }
    }

    public void xuatMenu() {
        System.out.println("======= QUAN LY DANH SACH QUAN LY =======");
        System.out.println("1. Hien thi tat ca quan ly");
        System.out.println("2. Them quan ly moi");
        System.out.println("3. Sua thong tin quan ly");
        System.out.println("4. Xoa quan ly");
        System.out.println("5. Tra cuu quan ly theo ma");
        System.out.println("0. Thoat");
        System.out.println("----------------------------------------");
    }

    public void menu() {
        int chon;
        do {
            XoaManHinh.xoa();
            xuatMenu();
            chon = Nhap.nhapInt("Nhap lua chon: ");
            switch (chon) {
                case 1 -> hienThiTatCaQuanLy();
                case 2 -> themQuanLy();
                case 3 -> SuaQuanLy();
                case 4 -> xoaQuanLy();
                case 5 -> traCuuQuanLy();
                case 0 -> System.out.println("Thoat chuong trinh quan ly.");
                default -> System.out.println("Lua chon khong hop le, vui long chon lai.");
            }
            Nhap.pause();
        } while (chon != 0);
    }

    public static void main(String[] args) {
        Database db = new Database();
        QuanLyDanhSachQuanLy quanLy = new QuanLyDanhSachQuanLy(db);
        quanLy.menu();
    }
}
