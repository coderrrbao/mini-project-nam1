package quanli;

import java.util.ArrayList;

import danhsach.DanhSachTaiKhoan;
import danhsach.DanhSachUser;
import database.Database;
import model.TaiKhoan;
import model.User;
import util.Nhap;
import util.TaoDoiTuong;
import util.ThoiGian;

public class QuanLyTaiKhoan {
    private ArrayList<TaiKhoan> listTaiKhoan; // danh sách tất cả tài khoản
    private Database db; // tham chiếu cơ sở dữ liệu

    public QuanLyTaiKhoan(Database db) {
        this.db = db;
        this.listTaiKhoan = db.getListTaiKhoan();
    }

    // Đổi mật khẩu cho user và cập nhật ngày đổi
    public void doiMatKhau(User user) {
        String matKhau = Nhap.nhapStr("Nhap mat khau moi : ");
        user.getTaiKhoan().setMatKhau(matKhau);
        user.getTaiKhoan().setNgayTao(ThoiGian.layNgayHienTaiStr());
    }

    // Tạo tài khoản mới và gán cho user
    public void taoTaiKhoan() {
        TaiKhoan taiKhoan = TaoDoiTuong.taoTaiKhoan();
        DanhSachUser danhSachUser = db.getDanhSachUser();
        danhSachUser.ganTaiKhoanChoUser(taiKhoan);
        listTaiKhoan.add(taiKhoan);
    }

    // Cấp tài khoản mặc định cho các user chưa có
    public void capTaiKhoanChoUser() {
        // Tìm danh sách user chưa có tài khoản
        ArrayList<User> listUser = db.getListUser();
        ArrayList<User> listUserChuaCoTk = new ArrayList<>();
        boolean timThay = false;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getTaiKhoan() == null) {
                listUserChuaCoTk.add(listUser.get(i));
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Tat ca user da duoc cap tai khoan");
            return;
        }

        // Hiển thị user chưa có tài khoản
        if (listUserChuaCoTk != null) {
            System.out.println("Danh sach user chua co tai khoan");
            System.out.println("=================================");
        }
        for (User user : listUserChuaCoTk) {
            System.out.println(user);
            System.out.println("--------------------------");
        }

        // Nhập user cần cấp tài khoản
        String ma = Nhap.nhapStr("Nhap ma user can cap tai khoan : ");
        User user = db.getDanhSachUser().timUser(ma);
        if (user == null) {
            System.out.println("Khong tim thay user");
            return;
        }

        // Tạo tài khoản mặc định và gán cho user
        TaiKhoan taiKhoan = new TaiKhoan(ma, "123");
        DanhSachTaiKhoan danhSachTaiKhoan = db.getDanhSachTaiKhoan();
        danhSachTaiKhoan.them(taiKhoan);
        user.capTaiKhoan(taiKhoan);
        System.out.println("Da cap tai khoan cho user");
        System.out.println(taiKhoan);
    }
}
