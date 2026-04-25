package danhsach;

import java.util.ArrayList;
import interfaces.QuanLyDanhSach;
import model.QuanLy;

public class DanhSachQuanLy implements QuanLyDanhSach<QuanLy> {
    private ArrayList<QuanLy> listQuanLy; // danh sách quản lý
    private int soLuong = 0; // số lượng quản lý

    // constructor khởi tạo danh sách
    public DanhSachQuanLy(ArrayList<QuanLy> listQuanLy) {
        this.listQuanLy = listQuanLy;
    }

    public DanhSachQuanLy() {
    }

    // getter & setter
    public ArrayList<QuanLy> getListQuanLy() {
        return listQuanLy;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setListQuanLy(ArrayList<QuanLy> listQuanLy) {
        this.listQuanLy = listQuanLy;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // tìm quản lý theo mã
    public QuanLy tim(String ma) {
        if (listQuanLy == null) {
            return null;
        }
        for (int i = 0; i < listQuanLy.size(); i++) {
            if (listQuanLy.get(i).getMa().equals(ma)) {
                return listQuanLy.get(i); // trả về quản lý tìm thấy
            }
        }
        return null; // không tìm thấy
    }

    // thêm quản lý mới
    public boolean them(QuanLy quanLy) {
        if (quanLy == null) {
            return false;
        }
        soLuong++;
        return listQuanLy.add(quanLy);
    }

    // xóa quản lý theo mã
    public boolean xoa(String ma) {
        QuanLy quanLy = tim(ma);
        if (quanLy == null) {
            return false;
        }
        soLuong--;
        return listQuanLy.remove(quanLy);
    }

    // xóa quản lý theo đối tượng
    public boolean xoa(QuanLy quanLy) {
        if (quanLy == null) {
            return false;
        }
        soLuong--;
        return listQuanLy.remove(quanLy);
    }
}
