package model;

import java.util.ArrayList;

public class HangThanhVien {
    // tiền đã chi của khách hàng đã mức thì được cấp hạng thành viên tương ứng
    public static long mucDong = 1000000;
    public static long mucBac = 5000000;
    public static long mucVang = 10000000;

    private String tenHang;
    private ArrayList<MaGiamGia> listMaGiamGiaDQ;
    private String moTa;

    public HangThanhVien(String tenHang, String moTa) {
        this.tenHang = tenHang;
        this.moTa = moTa;
        this.listMaGiamGiaDQ = new ArrayList<>();
    }

    public void setListMaGiamGiaDQ(ArrayList<MaGiamGia> listMaGiamGiaDQ) {
        this.listMaGiamGiaDQ = listMaGiamGiaDQ;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public ArrayList<MaGiamGia> getListMaGiamGiaDQ() {
        return listMaGiamGiaDQ;
    }

    public String getTenHang() {
        return tenHang;
    }

    public String getMoTa() {
        return moTa;
    }

    public boolean themMaGiamGia(MaGiamGia maGiamGia) {
        if (maGiamGia == null) {
            return false;
        }
        return listMaGiamGiaDQ.add(maGiamGia);
    }

    public boolean xoaMaGiamGia(MaGiamGia maGiamGia) {
        if (maGiamGia == null) {
            return false;
        }
        return listMaGiamGiaDQ.remove(maGiamGia);
    }

    @Override
    public String toString() {
        return "Ten hang : " + tenHang + "\n" +
                "Mo ta : " + moTa + "\n";
    }
}