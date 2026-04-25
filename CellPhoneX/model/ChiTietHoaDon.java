package model;

import java.util.ArrayList;

public class ChiTietHoaDon {
    private String ma;
    private ArrayList<SanPham> listSanPham; // chứa sản phẩm cùng thông tin sản phẩm, SanPham{serial,BaoHanh...}
    private int soSp = 0;
    private int soBh = 0;
    private long thanhTien;
    private ArrayList<MaGiamGia> listMaGiamGia; // chứa danh sách mã đã dùng để khi xóa thì tính lại thành tiền, tiền đã chi cho khách hàng

    public ChiTietHoaDon(String ma, Long thanhTien) {
        this.ma = ma;
        this.thanhTien = thanhTien;
        listSanPham = new ArrayList<>();
        listMaGiamGia = new ArrayList<>();
    }

    public ChiTietHoaDon(String ma) {
        this.ma = ma;
        listSanPham = new ArrayList<>();
        listMaGiamGia = new ArrayList<>();
    }

    public ChiTietHoaDon(String ma, long thanhTien) {
        this.ma = ma;
        this.thanhTien = thanhTien;
        listSanPham = new ArrayList<>();
        listMaGiamGia = new ArrayList<>();
    }

    public ChiTietHoaDon() {
        listSanPham = new ArrayList<>();
        listMaGiamGia = new ArrayList<>();
    };

    public String getMa() {
        return ma;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public ArrayList<SanPham> getListSanPham() {
        return listSanPham;
    }

    public int getSoBh() {
        return soBh;
    }

    public int getSoSp() {
        return soSp;
    }

    public ArrayList<MaGiamGia> getListMaGiamGia() {
        return listMaGiamGia;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setThanhTien(long thanhTien) {
        if (thanhTien < 0)
            thanhTien = 0;
        this.thanhTien = thanhTien;
    }

    public void setListSanPham(ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
    }

    public void setSoBh(int soBh) {
        this.soBh = soBh;
    }

    public void setSoSp(int soSp) {
        this.soSp = soSp;
    }

    public void setListMaGiamGia(ArrayList<MaGiamGia> listMaGiamGia) {
        this.listMaGiamGia = listMaGiamGia;
    }

    public boolean themSanPham(SanPham sanPham) {
        if (sanPham == null)
            return false;
        soSp++;
        if (sanPham.getBaoHanh() != null) {
            soBh++;
        }
        return listSanPham.add(sanPham);
    }

    public boolean xoaSanPham(SanPham sanPham) {
        if (sanPham == null) {
            return false;
        }
        soSp--;
        if (sanPham.getBaoHanh() != null) {
            soBh--;
        }
        return listSanPham.remove(sanPham);
    }

    public boolean themMaGiamGia(MaGiamGia maGiamGia) {
        if (maGiamGia == null) {
            return false;
        }
        return listMaGiamGia.add(maGiamGia);
    }

    public boolean xoaMaGiamGia(MaGiamGia maGiamGia) {
        if (maGiamGia == null) {
            return false;
        }
        return listMaGiamGia.remove(maGiamGia);
    }

    @Override
    public String toString() {
        return "Ma: " + getMa() + "\n"
                + "Thanh tien: " + getThanhTien() + "\n";
    }
}
