package danhsach;

import java.util.ArrayList;

import interfaces.QuanLyDanhSach;
import model.BaoHanh;
import model.SanPham;

public class DanhSachBaoHanh implements QuanLyDanhSach<BaoHanh> {
    private ArrayList<BaoHanh> listBaoHanh;
    private int soLuong = 0;

    public DanhSachBaoHanh() {
    }

    public DanhSachBaoHanh(ArrayList<BaoHanh> listBaoHanh) {
        this.listBaoHanh = listBaoHanh;
    }

    //// get set
    public ArrayList<BaoHanh> getListBaoHanh() {
        return listBaoHanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setListBaoHanh(ArrayList<BaoHanh> listBaoHanh) {
        this.listBaoHanh = listBaoHanh;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }

    ///////

    // thêm bảo hành vào danh sách
    public boolean them(BaoHanh baoHanh) {
        if (baoHanh == null) {
            return false;
        }
        soLuong++;
        return listBaoHanh.add(baoHanh);
    }

    /// tìm bảo hành theo mã
    public BaoHanh tim(String ma) {
        if (listBaoHanh == null) {
            return null;
        }
        for (int i = 0; i < listBaoHanh.size(); i++) {
            if (listBaoHanh.get(i).getMaBh().equals(ma)) {
                return listBaoHanh.get(i);
            }
        }
        return null;
    }

    // trả về list bảo hành khả dụng cho sản phẩm dự trên danh sách bảo hành hiện
    // tại
    public ArrayList<BaoHanh> timBaoHanh(SanPham sanPham) {
        ArrayList<BaoHanh> listBaoHanhs = new ArrayList<>();
        for (int i = 0; i < listBaoHanh.size(); i++) {
            if (listBaoHanh.get(i).getSanPham().getMa().equals(sanPham.getMa())) {
                listBaoHanhs.add(listBaoHanh.get(i));
            }
        }
        return listBaoHanhs;
    }

    // xóa bảo hành khỏi danh sách theo mã
    public boolean xoa(String ma) {
        BaoHanh baoHanh = tim(ma);
        if (baoHanh != null) {
            return false;
        }
        soLuong--;
        return listBaoHanh.remove(baoHanh);
    }

    // xóa bảo hành khỏi danh sách
    public boolean xoa(BaoHanh baoHanh) {
        if (baoHanh != null) {
            return false;
        }
        soLuong--;
        return listBaoHanh.remove(baoHanh);
    }
}
