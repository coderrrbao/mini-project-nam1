package danhsach;

import java.util.ArrayList;
import interfaces.QuanLyDanhSach;
import model.PhieuTraHang;

public class DanhSachPhieuTraHang implements QuanLyDanhSach<PhieuTraHang> {
    private ArrayList<PhieuTraHang> listPhieuTrahang; // danh sách phiếu trả hàng
    private int soLuong = 0; // số lượng phiếu trả hàng

    // constructor khởi tạo danh sách
    public DanhSachPhieuTraHang(ArrayList<PhieuTraHang> listPhieuTraHang) {
        this.listPhieuTrahang = listPhieuTraHang;
    }

    public DanhSachPhieuTraHang() {
    }

    // getter & setter
    public ArrayList<PhieuTraHang> getListPhieuTraHang() {
        return listPhieuTrahang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            return;
        }
        this.soLuong = soLuong;
    }

    public ArrayList<PhieuTraHang> getListPhieuTrahang() {
        return listPhieuTrahang;
    }

    public void setListPhieuTraHang(ArrayList<PhieuTraHang> listPhieuTrahang) {
        this.listPhieuTrahang = listPhieuTrahang;
    }

    // thêm phiếu trả hàng mới
    public boolean them(PhieuTraHang phieuTraHang) {
        if (phieuTraHang == null) {
            return false;
        }
        soLuong++;
        return listPhieuTrahang.add(phieuTraHang);
    }

    // tìm phiếu trả hàng theo mã
    public PhieuTraHang tim(String ma) {
        if (listPhieuTrahang == null) {
            return null;
        }
        for (int i = 0; i < listPhieuTrahang.size(); i++) {
            if (listPhieuTrahang.get(i).getMaTraHang().equals(ma)) {
                return listPhieuTrahang.get(i); // trả về phiếu tìm thấy
            }
        }
        return null; // không tìm thấy
    }

    // xóa phiếu trả hàng theo mã
    public boolean xoa(String ma) {
        PhieuTraHang phieuTraHang = tim(ma);
        if (phieuTraHang == null) {
            return false;
        }
        soLuong--;
        return listPhieuTrahang.remove(phieuTraHang);
    }

    // xóa phiếu trả hàng theo đối tượng
    public boolean xoa(PhieuTraHang phieuTraHang) {
        if (phieuTraHang == null) {
            return false;
        }
        soLuong--;
        return listPhieuTrahang.remove(phieuTraHang);
    }
}
