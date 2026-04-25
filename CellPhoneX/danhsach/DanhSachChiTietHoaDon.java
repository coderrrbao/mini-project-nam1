package danhsach;

import java.util.ArrayList;

import interfaces.QuanLyDanhSach;
import model.ChiTietHoaDon;
import model.SanPham;

public class DanhSachChiTietHoaDon implements QuanLyDanhSach<ChiTietHoaDon> {
    private ArrayList<ChiTietHoaDon> listChiTietHoaDon;
    private int soLuong = 0;

    public DanhSachChiTietHoaDon(ArrayList<ChiTietHoaDon> listChiTietHoaDon) {
        this.listChiTietHoaDon = listChiTietHoaDon;
    }

    public DanhSachChiTietHoaDon() {
    };

    public void setListChiTietHoaDon(ArrayList<ChiTietHoaDon> listChiTietHoaDon) {
        this.listChiTietHoaDon = listChiTietHoaDon;
    }

   public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    public ArrayList<ChiTietHoaDon> getListChiTietHoaDon() {
        return listChiTietHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    // thêm chiTietHoaDon vào danh sạch hiện tại
    public boolean them(ChiTietHoaDon chiTietHoaDon) {
        if (chiTietHoaDon == null) {
            return false;
        }
        soLuong++;
        return listChiTietHoaDon.add(chiTietHoaDon);
    }

    // xóa chitiethoadon khỏi danh sách
    public boolean xoa(ChiTietHoaDon chiTietHoaDon) {
        if (chiTietHoaDon == null) {
            return false;
        }
        soLuong--;
        return listChiTietHoaDon.remove(chiTietHoaDon);
    }

    // tìm chitiethoadon theo mã
    public ChiTietHoaDon tim(String ma) {
        if (listChiTietHoaDon == null) {
            return null;
        }
        for (int i = 0; i < listChiTietHoaDon.size(); i++) {
            if (listChiTietHoaDon.get(i).getMa().equals(ma)) {
                return listChiTietHoaDon.get(i);
            }
        }
        return null;
    }

    // tìm chi tiết hóa đơn chứ sản phẩm (duy nhất) , nếu không tìm thấy trả về null
    public ChiTietHoaDon tim(SanPham sanPham) {
        for (ChiTietHoaDon chiTietHoaDon : listChiTietHoaDon) {
            for (SanPham sp : chiTietHoaDon.getListSanPham()) {
                if (sp.equals(sanPham)) {
                    return chiTietHoaDon;
                }
            }
        }
        return null;
    }

}
