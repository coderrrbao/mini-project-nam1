package quanli;

import model.LichTrongTuan;
import model.NhanVien;
import util.Nhap;
import util.ThoiGian;
import util.XoaManHinh;
import database.Database;
import interfaces.GiaoTiep;

public class NhanVienQuanLy implements GiaoTiep {
    private Database db; // cơ sở dữ liệu tổng
    private NhanVien nv; // nhân viên đang đăng nhập

    public NhanVienQuanLy(Database db, NhanVien nv) {
        this.db = db;
        this.nv = nv;
    }

    // xem lịch làm việc của tuần hiện tại
    public void xemLichLamViecTrongTuanNay() {
        LichTrongTuan lichTrongTuan = db.getLichTrongTuanNay();
        if (lichTrongTuan == null || !ThoiGian.ngayTrongKhoan(ThoiGian.layNgayHienTaiStr(), lichTrongTuan.getNgayThu2(),
                lichTrongTuan.getNgayCn())) {
            System.out.println("Tuan nay chua xep lich");
            return;
        }
        QuanLyLichTrongTuan quanLyLichTrongTuan = new QuanLyLichTrongTuan(db, lichTrongTuan);
        quanLyLichTrongTuan.xemLichLamViec();
    }

    // tra cứu thông tin khách hàng
    public void traCuuThongTinKhachHang() {
        QuanLyKhachHang quanLyKhachHang = new QuanLyKhachHang(db);
        quanLyKhachHang.traCuuThongTinKhachHang();
    }

    // tạo hóa đơn bán hàng
    public void taoHoaDon() {
        QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon(db);
        quanLyHoaDon.taoHoaDon();
    }

    // tạo phiếu trả hàng
    public void taoPhieuTraHang() {
        QuanLyPhieuTraHang quanLyPhieuTraHang = new QuanLyPhieuTraHang(db);
        quanLyPhieuTraHang.taoPhieuTraHang();
    }

    // tìm kiếm sản phẩm
    public void traCuuSanPham() {
        QuanLyThongTinSanPham quanLySanPham = new QuanLyThongTinSanPham(db);
        quanLySanPham.traCuuThongTinSanPham();
    }

    // tra cứu bảo hành
    public void TraCuuBaoHanh() {
        QuanLyBaoHanh quanLyBaoHanh = new QuanLyBaoHanh(db);
        quanLyBaoHanh.traCuuBaoHanh();
    }

    // tạo phiếu bảo hành
    public void taoPhieuBaoHanh() {
        QuanLyPhieuBaoHanh quanLyPhieuBaoHanh = new QuanLyPhieuBaoHanh(db);
        quanLyPhieuBaoHanh.taoPhieuBaoHanh();
    }

    // xem toàn bộ mã giảm giá
    public void xemTatCaMaGiamGia() {
        QuanLyMaGiamGia quanLyMaGiamGia = new QuanLyMaGiamGia(db);
        quanLyMaGiamGia.xemTatCaMaGiamGia();
    }

    // đổi mật khẩu cho nhân viên
    public void doiMatKhau() {
        QuanLyTaiKhoan quanLyTaiKhoan = new QuanLyTaiKhoan(db);
        quanLyTaiKhoan.doiMatKhau(nv);
    }

    // xử lý đổi/trả hàng
    public void doiTraHang() {
        QuanLyPhieuTraHang quanLyPhieuTraHang = new QuanLyPhieuTraHang(db);
        quanLyPhieuTraHang.taoPhieuTraHang();
    }

    // hiển thị thông tin nhân viên hiện tại
    public void hienThiThongTin() {
        System.out.println(nv);
    }

    // gửi tin nhắn cho người khác
    public void guiTinNhan() {
        QuanLyTinNhan quanLyTinNhan = new QuanLyTinNhan(db);
        quanLyTinNhan.guiTinNhan(nv);
    }

    // xem tất cả tin nhắn
    public void xemTatCaTinNhan() {
        QuanLyTinNhan quanLyTinNhan = new QuanLyTinNhan(db);
        quanLyTinNhan.xemTatCaTinNhan(nv);
    }

    // xem tin nhắn gần đây
    public void xemTinNhanGanDay() {
        QuanLyTinNhan quanLyTinNhan = new QuanLyTinNhan(db);
        quanLyTinNhan.xemTatCaTinNhan(nv);
    }

    // menu hiển thị các chức năng
    public void xuatMenu() {
        System.out.println("======= Nhan Vien Quan Ly =======");
        System.out.println("1.  Hien thi thong tin");
        System.out.println("2.  Tra cuu thong tin khach hang");
        System.out.println("3.  Tao hoa don");
        System.out.println("4.  Tao Phieu tra hang");
        System.out.println("5.  Tim kiem san pham");
        System.out.println("6.  Xem tat ca ma giam gia");
        System.out.println("7.  Xem lich lam trong tuan");
        System.out.println("8.  Tra cuu thong tin bao hanh");
        System.out.println("9.  Tao phieu bao hanh");
        System.out.println("10. Gui Tin Nhan");
        System.out.println("11. Hop thu");
        System.out.println("12. Doi mat khau");
        System.out.println("0.  Thoat");
        System.out.println("---------------------------");
    }

    // thực hiện hành động dựa theo lựa chọn
    public void thucHienChucNang(int choice) {
        switch (choice) {
            case 1 -> hienThiThongTin();
            case 2 -> traCuuThongTinKhachHang();
            case 3 -> taoHoaDon();
            case 4 -> doiTraHang();
            case 5 -> traCuuSanPham();
            case 6 -> xemTatCaMaGiamGia();
            case 7 -> xemLichLamViecTrongTuanNay();
            case 8 -> TraCuuBaoHanh();
            case 9 -> taoPhieuBaoHanh();
            case 10 -> guiTinNhan();
            case 11 -> xemTatCaTinNhan();
            case 12 -> doiMatKhau();
            default -> System.out.println("Da thoat Menu!");
        }
    }

    // hiển thị menu và nhận lựa chọn từ người dùng
    public void menu() {
        int xacNhan = 1;
        while (xacNhan == 1) {
            XoaManHinh.xoa(); // dọn màn hình
            xuatMenu(); // hiển thị menu
            int luaChon = Nhap.nhapInt("Nhap lua Chon : ");
            if (luaChon == 0) {
                return;
            }
            thucHienChucNang(luaChon); // thực hiện chức năng tương ứng
            Nhap.pause(); // dừng chờ người dùng bấm phím
        }
    }
}
