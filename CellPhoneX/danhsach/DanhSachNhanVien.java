package danhsach;

import java.util.ArrayList;

import interfaces.QuanLyDanhSach;
import model.NhanVien;

public class DanhSachNhanVien implements QuanLyDanhSach<NhanVien> {
    private ArrayList<NhanVien> listNhanVien; // danh sách lưu toàn bộ nhân viên
    private int soLuong = 0; // số lượng nhân viên hiện có trong danh sách

    // ================== CONSTRUCTOR ==================
    // constructor nhận sẵn danh sách nhân viên (thường dùng khi đọc file hoặc load
    // từ database)
    public DanhSachNhanVien(ArrayList<NhanVien> listNhanVien) {
        this.listNhanVien = listNhanVien;
    }

    // constructor mặc định (nên khởi tạo listNhanVien = new ArrayList<>(); để tránh
    // null)
    public DanhSachNhanVien() {
    };

    // ================== GETTER / SETTER ==================
    public ArrayList<NhanVien> getListNhanVien() {
        return listNhanVien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setListNhanVien(ArrayList<NhanVien> listNhanVien) {
        this.listNhanVien = listNhanVien;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // ================== CÁC HÀM XỬ LÝ CHÍNH ==================

    // thêm 1 nhân viên mới vào danh sách
    // trả về true nếu thêm thành công, false nếu nhân viên null
    public boolean them(NhanVien nhanVien) {
        if (nhanVien == null) {
            return false;
        }
        soLuong++; // tăng biến đếm số lượng
        return listNhanVien.add(nhanVien); // thêm nhân viên vào danh sách
    }

    // tìm nhân viên theo mã, trả về đối tượng NhanVien nếu tìm thấy
    public NhanVien tim(String ma) {
        if (listNhanVien == null) {
            return null;
        }
        for (int i = 0; i < listNhanVien.size(); i++) {
            NhanVien nv = listNhanVien.get(i);
            if (nv.getMa().equals(ma)) // so sánh mã nhân viên
                return nv; // trả về nhân viên tìm thấy
        }
        return null; // không tìm thấy
    }

    // xóa nhân viên theo mã
    public boolean xoa(String ma) {
        NhanVien nv = tim(ma); // tìm nhân viên cần xóa
        if (nv == null) {
            return false; // không tồn tại nhân viên có mã này
        }
        soLuong--; // giảm biến đếm
        return listNhanVien.remove(nv); // xóa nhân viên ra khỏi danh sách
    }

    // xóa nhân viên theo đối tượng NhanVien (nếu đã có sẵn)
    public boolean xoa(NhanVien nv) {
        if (nv == null) {
            return false;
        }
        soLuong--;
        return listNhanVien.remove(nv);
    }
}
