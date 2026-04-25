package danhsach;

import java.util.ArrayList;

import interfaces.QuanLyDanhSach;
import model.HangThanhVien;
import model.KhachHang;
import model.MaGiamGia;

public class DanhSachHangThanhVien implements QuanLyDanhSach<HangThanhVien> {
    private ArrayList<HangThanhVien> listHangThanhVien;
    private int soLuong = 0;

    public DanhSachHangThanhVien(ArrayList<HangThanhVien> listHangThanhVien) {
        this.listHangThanhVien = listHangThanhVien;
    }

    public DanhSachHangThanhVien() {
    }

    public ArrayList<HangThanhVien> getListHangThanhVien() {
        return listHangThanhVien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setListHangThanhVien(ArrayList<HangThanhVien> listHangThanhVien) {
        this.listHangThanhVien = listHangThanhVien;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            return;
        }
        this.soLuong = soLuong;
    }

    // tìm hạng thành viên
    public HangThanhVien tim(String ten) {
        if (listHangThanhVien == null) {
            return null;
        }
        for (int i = 0; i < listHangThanhVien.size(); i++) {
            if (listHangThanhVien.get(i).getTenHang().equals(ten)) {
                return listHangThanhVien.get(i);
            }
        }
        return null;
    }

    // thêm một hạng thành viên vào danh sách
    public boolean them(HangThanhVien hangThanhVien) {
        if (hangThanhVien == null || tim(hangThanhVien.getTenHang()) != null) {
            return false; // đã tồn tại hoac truyen null vao
        }
        soLuong++;
        return listHangThanhVien.add(hangThanhVien);
    }

    // xóa một hạng thành viên vào danh sách
    public boolean xoa(String ten) {
        HangThanhVien htv = tim(ten);
        if (htv != null) {
            return listHangThanhVien.remove(htv);
        }
        soLuong--;
        return false;
    }

    public boolean xoa(HangThanhVien htv) {
        if (htv != null) {
            return listHangThanhVien.remove(htv);
        }
        soLuong--;
        return false;
    }

    /// kiểm tra tiền đã chi của khachHang và gán hạng thành viên dựa trên nó
    public void setHangThanhVienChoKhachHang(KhachHang khachHang) {
        HangThanhVien hangThanhVien = null;
        long tienDaChi = khachHang.getTienDaChi();
        if (tienDaChi >= HangThanhVien.mucDong && tienDaChi < HangThanhVien.mucBac
                && tienDaChi < HangThanhVien.mucVang) {
            if (khachHang.getHangThanhVien() != null && khachHang.getHangThanhVien().getTenHang().equals("Dong")) { // không
                                                                                                                    // thay
                                                                                                                    // dổi
                return;
            }
            hangThanhVien = tim("Dong");
            khachHang.setHangThanhVien(hangThanhVien);
        } else if (tienDaChi >= HangThanhVien.mucBac && tienDaChi < HangThanhVien.mucVang) {
            if (khachHang.getHangThanhVien() != null && khachHang.getHangThanhVien().getTenHang().equals("Bac")) { // không
                                                                                                                   // thay
                                                                                                                   // dổi
                return;
            }
            hangThanhVien = tim("Bac");
            khachHang.setHangThanhVien(hangThanhVien);
        } else {
            if (khachHang.getHangThanhVien() != null && khachHang.getHangThanhVien().getTenHang().equals("Vang")) { // không
                                                                                                                    // thay
                                                                                                                    // dổi
                return;
            }
            hangThanhVien = tim("Vang");
            khachHang.setHangThanhVien(hangThanhVien);

        }
        if (hangThanhVien == null) {
            return;
        }
        DanhSachMaGiamGia danhSachMaGiamGia = new DanhSachMaGiamGia(khachHang.getListMaGiamGia());
        /// xóa mã giảm giá độc quyền của hạng thành viên cũ
        for (int i = khachHang.getListMaGiamGia().size() - 1; i >= 0; i--) {
            MaGiamGia maGiamGia = khachHang.getListMaGiamGia().get(i);
            if (danhSachMaGiamGia.laMaGiamGiaDocQuyen(maGiamGia)) {
                danhSachMaGiamGia.xoa(maGiamGia);
            }
        }
        // thêm mã giảm mới
        danhSachMaGiamGia.getListMaGiamGia().addAll(hangThanhVien.getListMaGiamGiaDQ());
    }
}