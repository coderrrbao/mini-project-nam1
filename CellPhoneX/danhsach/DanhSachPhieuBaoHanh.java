package danhsach;

import java.util.ArrayList;
import interfaces.QuanLyDanhSach;
import model.PhieuBaoHanh;
import model.SanPham;

public class DanhSachPhieuBaoHanh implements QuanLyDanhSach<PhieuBaoHanh> {
    private ArrayList<PhieuBaoHanh> listPhieuBaoHanh; // danh sách phiếu bảo hành
    private int soLuong = 0; // số lượng phiếu bảo hành

    // constructor khởi tạo danh sách
    public DanhSachPhieuBaoHanh(ArrayList<PhieuBaoHanh> listPhieuBaoHanh) {
        this.listPhieuBaoHanh = listPhieuBaoHanh;
    }

    public DanhSachPhieuBaoHanh() {
    }

    // getter & setter
    public ArrayList<PhieuBaoHanh> getListPhieuBaoHanh() {
        return listPhieuBaoHanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setListPhieuBaoHanh(ArrayList<PhieuBaoHanh> listPhieuBaoHanh) {
        this.listPhieuBaoHanh = listPhieuBaoHanh;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // thêm phiếu bảo hành mới
    public boolean them(PhieuBaoHanh phieuBaoHanh) {
        if (phieuBaoHanh == null) {
            return false;
        }
        soLuong++;
        return listPhieuBaoHanh.add(phieuBaoHanh);
    }

    // tìm phiếu bảo hành theo mã
    public PhieuBaoHanh tim(String ma) {
        if (listPhieuBaoHanh == null) {
            return null;
        }
        for (int i = 0; i < listPhieuBaoHanh.size(); i++) {
            if (listPhieuBaoHanh.get(i).getMaBaoHanh().equals(ma)) {
                return listPhieuBaoHanh.get(i); // trả về phiếu bảo hành tìm thấy
            }
        }
        return null; // không tìm thấy
    }

    // xóa phiếu bảo hành theo mã
    public boolean xoa(String ma) {
        PhieuBaoHanh phieuBaoHanh = tim(ma);
        if (phieuBaoHanh != null) {
            soLuong--;
            return listPhieuBaoHanh.remove(phieuBaoHanh);
        }
        return false;
    }

    // xóa phiếu bảo hành theo đối tượng
    public boolean xoa(PhieuBaoHanh phieuBaoHanh) {
        if (phieuBaoHanh != null) {
            soLuong--;
            return listPhieuBaoHanh.remove(phieuBaoHanh);
        }
        return false;
    }

    // tìm bảo hành của một sản phẩm cụ thể
    public PhieuBaoHanh tim(SanPham sanPham) {
        for (PhieuBaoHanh phieuBaoHanh : listPhieuBaoHanh) {
            if (phieuBaoHanh.getSanPham().equals(sanPham)) {
                return phieuBaoHanh;
            }
        }
        return null;
    }
}
