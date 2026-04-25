package danhsach;

import java.util.ArrayList;
import interfaces.QuanLyDanhSach;
import model.ThongTinSanPham;

public class DanhSachThongTinSanPham implements QuanLyDanhSach<ThongTinSanPham> {
    private ArrayList<ThongTinSanPham> listThongTinSanPham; // danh sách thông tin sản phẩm
    private int soLuong = 0; // số lượng sản phẩm

    // constructor
    public DanhSachThongTinSanPham(ArrayList<ThongTinSanPham> listSanPham) {
        this.listThongTinSanPham = listSanPham;
    }

    public DanhSachThongTinSanPham() {
    }

    // getter & setter
    public ArrayList<ThongTinSanPham> getListThongTinSanPham() {
        return listThongTinSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setListThongTinSanPham(ArrayList<ThongTinSanPham> listSanPham) {
        this.listThongTinSanPham = listSanPham;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // thêm sản phẩm mới
    public boolean them(ThongTinSanPham sanPham) {
        if (sanPham == null) {
            return false;
        }
        soLuong++;
        return listThongTinSanPham.add(sanPham);
    }

    // xóa sản phẩm theo mã
    public boolean xoa(String ma) {
        if (listThongTinSanPham == null) {
            return false;
        }
        ThongTinSanPham sanPham = tim(ma);
        if (sanPham == null) {
            return false;
        }
        soLuong--;
        return listThongTinSanPham.remove(sanPham);
    }

    // xóa sản phẩm theo object
    public boolean xoa(ThongTinSanPham sanPham) {
        if (listThongTinSanPham == null || sanPham == null) {
            return false;
        }
        soLuong--;
        return listThongTinSanPham.remove(sanPham);
    }

    // tìm sản phẩm theo tên
    public ThongTinSanPham timSanPhamTheoTen(String ten) {
        if (listThongTinSanPham == null) {
            return null;
        }
        for (int i = 0; i < listThongTinSanPham.size(); i++) {
            if (listThongTinSanPham.get(i).getTen().equals(ten)) {
                return listThongTinSanPham.get(i);
            }
        }
        return null;
    }

    // tìm sản phẩm theo mã
    public ThongTinSanPham tim(String ma) {
        if (listThongTinSanPham == null) {
            return null;
        }
        for (int i = 0; i < listThongTinSanPham.size(); i++) {
            if (listThongTinSanPham.get(i).getMa().equals(ma)) {
                return listThongTinSanPham.get(i);
            }
        }
        return null;
    }
}
