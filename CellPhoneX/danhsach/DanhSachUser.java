package danhsach;

import java.util.ArrayList;
import model.TaiKhoan;
import model.User;

public class DanhSachUser {
    private ArrayList<User> listUser; // danh sách user

    // constructor
    public DanhSachUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }
    public DanhSachUser(){}
    // getter
    public ArrayList<User> getListUser() {
        return listUser;
    }
    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }
    public int getSoLuong(){
        return listUser.size();
    }
    // tìm user theo mã
    public User timUser(String ma) {
        if (listUser == null) {
            return null;
        }
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getMa().equals(ma)) {
                return listUser.get(i);
            }
        }
        return null;
    }

    // gán tài khoản cho user theo tên đăng nhập
    public boolean ganTaiKhoanChoUser(TaiKhoan taiKhoan) {
        if (taiKhoan == null) {
            return false;
        }
        String ma = taiKhoan.getTenDangNhap(); // dùng tên đăng nhập làm mã
        User user = timUser(ma); // tìm user tương ứng
        if (user == null) {
            return false;
        }
        return user.capTaiKhoan(taiKhoan); // gán tài khoản cho user
    }
}
