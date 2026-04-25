package quanli;

import database.Database;
import interfaces.GiaoTiep;
import model.LichTrongTuan;
import model.QuanLy;
import util.Nhap;
import util.ThoiGian;
import util.XoaManHinh;

public class QuanLyQuanLyTatCa implements GiaoTiep {
    private Database db; // Cơ sở dữ liệu chính
    private QuanLy ql;   // Đối tượng quản lý hiện tại

    public QuanLyQuanLyTatCa(Database db, QuanLy ql) {
        this.ql = ql; // Gán người quản lý
        this.db = db; // Gán cơ sở dữ liệu
    }

    // Quản lý sản phẩm
    public void quanLySanPham() {
        QuanLyThongTinSanPham quanLySanPham = new QuanLyThongTinSanPham(db);
        quanLySanPham.menu();
    }

    // Quản lý nhân viên
    public void quanLyNhanVien() {
        QuanLyNhanVien quanLyQuanLyNhanVien = new QuanLyNhanVien(db);
        quanLyQuanLyNhanVien.menu();
    }

    // Quản lý hóa đơn
    public void quanLyHoaDon() {
        QuanLyHoaDon menuHoaDon = new QuanLyHoaDon(db);
        menuHoaDon.menu();
    }

    // Quản lý bảo hành
    public void quanLyBaoHanh() {
        QuanLyBaoHanh quanLyBaoHanh = new QuanLyBaoHanh(db);
        quanLyBaoHanh.menu();
    }

    // Quản lý khách hàng
    public void quanLyKhachHang() {
        QuanLyKhachHang quanLyKhachHang = new QuanLyKhachHang(db);
        quanLyKhachHang.menu();
    }

    // Gửi tin nhắn cho user khác
    public void guiTinNhan() {
        QuanLyTinNhan quanLyTinNhan = new QuanLyTinNhan(db);
        quanLyTinNhan.guiTinNhan(ql);
    }

    // Xem tất cả tin nhắn (hộp thư)
    public void xemTatCaTinNhan() {
        QuanLyTinNhan quanLyTinNhan = new QuanLyTinNhan(db);
        quanLyTinNhan.xemTatCaTinNhan(ql);
    }

    // Xem doanh thu tổng
    public void xemDoanhThu() {
        QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon(db);
        quanLyHoaDon.xemDoanhThu();
    }

    // Xem lịch làm việc trong tuần hiện tại
    public void xemLichLamViecTrongTuan() {
        LichTrongTuan lichTrongTuan = db.getLichTrongTuanNay();
        if (lichTrongTuan == null) {
            System.out.println("Tuan nay chua co lich");
            return;
        }
        QuanLyLichTrongTuan quanLyLichTrongTuan = new QuanLyLichTrongTuan(db, lichTrongTuan);
        quanLyLichTrongTuan.xemLichLamViec();
    }

    // Quản lý lịch tuần này (tạo mới nếu chưa có)
    public void quanLyLichTuanNay() {
        LichTrongTuan lichTrongTuan = db.getLichTrongTuanNay();
        // Kiểm tra tuần hiện tại có lịch chưa hoặc lịch hết hạn
        if (lichTrongTuan == null || !ThoiGian.ngayTrongKhoan(ThoiGian.layNgayHienTaiStr(),
                lichTrongTuan.getNgayThu2(), lichTrongTuan.getNgayCn())) {
            System.out.println("Tuan nay chua co lich");
            System.out.println("Ban co muon tao moi khong (1)Co (khac)Khong");
            if (Nhap.nhapInt("Nhap lua chon : ") != 1) {
                return;
            }
            // Tạo lịch mới cho tuần hiện tại
            db.setLichTrongTuanNay(LichTrongTuan.taoLichTrongTuan(ThoiGian.soTuanHienTai(), db));
        }
        QuanLyLichTrongTuan quanLyLichTrongTuan = new QuanLyLichTrongTuan(db, db.getLichTrongTuanNay());
        quanLyLichTrongTuan.menu();
    }

    // Quản lý phiếu bảo hành
    public void quanLyPhieuBaoHanh() {
        QuanLyPhieuBaoHanh quanLyPhieuBaoHanh = new QuanLyPhieuBaoHanh(db);
        quanLyPhieuBaoHanh.menu();
    }

    // Quản lý phiếu trả hàng
    public void quanLyPhieuTraHang() {
        QuanLyPhieuTraHang quanLyPhieuTraHang = new QuanLyPhieuTraHang(db);
        quanLyPhieuTraHang.menu();
    }

    // Quản lý mã giảm giá
    public void quanLyMaGiamGia() {
        QuanLyMaGiamGia quanLyMaGiamGia = new QuanLyMaGiamGia(db);
        quanLyMaGiamGia.menu();
    }

    // Quản lý hạng thành viên
    public void QuanLyHangThanhVien() {
        QuanLyHangThanhVien quanLyHangThanhVien = new QuanLyHangThanhVien(db);
        quanLyHangThanhVien.menu();
    }

    // Đổi mật khẩu tài khoản quản lý
    public void doiMatKhau() {
        QuanLyTaiKhoan quanLyTaiKhoan = new QuanLyTaiKhoan(db);
        quanLyTaiKhoan.doiMatKhau(ql);
    }

    // Hiển thị thông tin của quản lý hiện tại
    public void hienThiThongTin() {
        System.out.println();
        System.out.println(ql);
    }

    // Cấp tài khoản cho user mới
    public void capTaiKhoanChoUser() {
        QuanLyTaiKhoan quanLyTaiKhoan = new QuanLyTaiKhoan(db);
        quanLyTaiKhoan.capTaiKhoanChoUser();
    }

    // Xuất menu chính
    public void xuatMenu() {
        System.out.println("======= Quan Ly Tat Ca =======");
        System.out.println("1.  Hien thong tin");
        System.out.println("2.  Quan ly nhan vien");
        System.out.println("3.  Quan ly san pham");
        System.out.println("4.  Quan ly don hang");
        System.out.println("5.  Quan ly bao hanh san pham");
        System.out.println("6.  Quan ly phieu bao hanh san pham");
        System.out.println("7.  Quan ly tra hang");
        System.out.println("8.  Quan ly khach hang");
        System.out.println("9.  Quan ly ma giam gia");
        System.out.println("10. Quan ly hang thanh vien");
        System.out.println("11. Quan ly lich trong tuan nay");
        System.out.println("12. Xem doanh thu");
        System.out.println("13. Gui tin nhan");
        System.out.println("14. Hop thu");
        System.out.println("15. Xem lich trong tuan nay");
        System.out.println("16. Doi mat khau");
        System.out.println("17. Cap tai khoan cho user");
        System.out.println("0.  Thoat");
        System.out.println("-------------------------------------------------");
    }

    // Thực thi chức năng theo lựa chọn người dùng
    public void thucHienChucNang(int luaChon) {
        switch (luaChon) {
            case 1 -> hienThiThongTin();
            case 2 -> quanLyNhanVien();
            case 3 -> quanLySanPham();
            case 4 -> quanLyHoaDon();
            case 5 -> quanLyBaoHanh();
            case 6 -> quanLyPhieuBaoHanh();
            case 7 -> quanLyPhieuTraHang();
            case 8 -> quanLyKhachHang();
            case 9 -> quanLyMaGiamGia();
            case 10 -> QuanLyHangThanhVien();
            case 11 -> quanLyLichTuanNay();
            case 12 -> xemDoanhThu();
            case 13 -> guiTinNhan();
            case 14 -> xemTatCaTinNhan(); // Hộp thư
            case 15 -> xemLichLamViecTrongTuan(); // Xem lịch
            case 16 -> doiMatKhau();
            case 17 -> capTaiKhoanChoUser();
            case 0 -> System.out.println("Da thoat!");
            default -> System.out.println("Lua chon khong hop le!");
        }
    }

    // Vòng lặp menu chính
    public void menu() {
        int xacNhan = 1; // Biến điều khiển vòng lặp
        while (xacNhan == 1) {
            XoaManHinh.xoa(); // Xóa màn hình console
            xuatMenu(); // In menu
            int luaChon = Nhap.nhapInt("Nhap lua Chon : "); // Nhập lựa chọn
            if (luaChon == 0) { // Nếu chọn thoát
                return;
            }
            thucHienChucNang(luaChon); // Thực hiện chức năng tương ứng
            Nhap.pause(); // Tạm dừng chờ người dùng
        }
    }
}
