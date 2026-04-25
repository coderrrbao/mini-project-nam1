import danhsach.DanhSachTaiKhoan;
import database.Database;
import file.GhiFile;
import model.NhanVien;
import model.QuanLy;
import model.User;
import quanli.NhanVienQuanLy;
import quanli.QuanLyQuanLyTatCa;
import util.Nhap;

public class Main {
    private User user;
    private Database db = new Database();
    /// điều hướng ra 2 menu quản lý chính dự trên phân quyền
    public void dieuHuongMenu() {
        if (user.getQuyenHang().equals("NhanVien")) {
            NhanVienQuanLy menuNhanVien = new NhanVienQuanLy(db, (NhanVien) user);
            menuNhanVien.menu();
        } else if (user.getQuyenHang().equals("QuanLy")) {
            QuanLyQuanLyTatCa menuQuanLy = new QuanLyQuanLyTatCa(db, (QuanLy) user);
            menuQuanLy.menu();
        } else {
            System.out.println("Tai Khoan chua duoc cap quyen");
        }
    }
    /// đăng nhập 
    public void dangNhap() {
        int xacNhan = 1;
        do {
            int dem = 0;
            DanhSachTaiKhoan danhSachTaiKhoan = db.getDanhSachTaiKhoan();
            String tenTaiKhoan;
            String matKhau;
            do {
                if (dem == 3) { // nếu 3 lần sài tk mk thì kết thức chương trình
                    break;
                }
                dem++;
                tenTaiKhoan = Nhap.nhapStr("Tai khoan : ");
                matKhau = Nhap.nhapStr("Mat khau : ");
                user = danhSachTaiKhoan.layUserBangTk(tenTaiKhoan, matKhau);
                if (user == null) {
                    System.out.println("Tai khoan hoac mat khau khong khong dung vui long nhap lai");
                }
                
            } while (user == null);
            if (user != null) {
                dieuHuongMenu();
            }
            dem = 0; 
            xacNhan = Nhap.nhapInt("(1)Tiep tuc dang nhap (khac)Thoat : ");
            GhiFile ghiFile = new GhiFile(db);
            ghiFile.ghi_DatasVaoDatabase(); // ghi dữ liệu thay đổi vào file txt
        } while (xacNhan == 1);
        System.out.println("Da thoat khoi chuong trinh");
    }
    /// hàm main để chạy cả chương trình
    public static void main(String[] args) {
        Main cellPhoneX = new Main();
        cellPhoneX.dangNhap();
    }
}
