package danhsach;

import java.util.ArrayList;
import interfaces.QuanLyDanhSach;
import model.TaiKhoan;
import model.User;

public class DanhSachTaiKhoan implements QuanLyDanhSach<TaiKhoan> {
    private ArrayList<TaiKhoan> listTaiKhoan; // danh sách tài khoản
    private int soLuong = 0; // số lượng tài khoản

    // constructor
    public DanhSachTaiKhoan(ArrayList<TaiKhoan> listTaiKhoan) {
        this.listTaiKhoan = listTaiKhoan;
    }

    public DanhSachTaiKhoan() {
    }

    // getter & setter
    public ArrayList<TaiKhoan> getListTaiKhoan() {
        return listTaiKhoan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setListTaiKhoan(ArrayList<TaiKhoan> listTaiKhoan) {
        this.listTaiKhoan = listTaiKhoan;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // lấy User dựa trên tên đăng nhập & mật khẩu
    public User layUserBangTk(String tenDangNhap, String matKhau) {
        if (listTaiKhoan == null) {
            return null;
        }
        for (int i = 0; i < listTaiKhoan.size(); i++) {
            if (listTaiKhoan.get(i).kiemTraTaiKhoan(tenDangNhap, matKhau)) {
                return listTaiKhoan.get(i).getUser(); // trả về User nếu khớp
            }
        }
        return null;
    }

    // thêm tài khoản mới
    public boolean them(TaiKhoan tk) {
        if (tk == null) {
            return false;
        }
        soLuong++;
        return listTaiKhoan.add(tk);
    }

    // xóa tài khoản
    public boolean xoa(TaiKhoan tk) {
        if (tk == null) {
            return false;
        }
        soLuong--;
        tk.getUser().goTaiKhoan(); // hủy liên kết user với tài khoản
        return listTaiKhoan.remove(tk);
    }

    // tìm tài khoản theo tên đăng nhập
    public TaiKhoan tim(String tenDn) {
        for (TaiKhoan taiKhoan : listTaiKhoan) {
            if (taiKhoan.getTenDangNhap().equals(tenDn)) {
                return taiKhoan;
            }
        }
        return null;
    }
}
