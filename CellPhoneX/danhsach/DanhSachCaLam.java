package danhsach;

import java.util.ArrayList;

import database.Database;
import interfaces.QuanLyDanhSach;
import model.CaLam;
import model.LichTrongNgay;
import util.CapMa;
import util.ThoiGian;

public class DanhSachCaLam implements QuanLyDanhSach<CaLam> {
    private ArrayList<CaLam> listCaLam;
    private int soLuong = 0;

    public DanhSachCaLam(ArrayList<CaLam> listCaLam) {
        this.listCaLam = listCaLam;
    }

    public DanhSachCaLam() {
    }

    public ArrayList<CaLam> getListCaLam() {
        return listCaLam;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setListCaLam(ArrayList<CaLam> listCaLam) {
        this.listCaLam = listCaLam;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // tìm ca làm theo mã
    public CaLam tim(String ma) {
        if (listCaLam == null) {
            return null;
        }
        for (int i = 0; i < listCaLam.size(); i++) {
            if (listCaLam.get(i).getMa().equals(ma)) {
                return listCaLam.get(i);
            }
        }
        return null;
    }

    // thêm ca làm vào danh sách
    public boolean them(CaLam caLam) {
        if (caLam == null) {
            return false;
        }
        soLuong++;
        return listCaLam.add(caLam);
    }

    // xóa ca làm khỏi danh sách
    public boolean xoa(CaLam caLam) {
        if (caLam == null) {
            return false;
        }
        soLuong--;
        return listCaLam.remove(caLam);
    }

    // xóa ca làm theo mã
    public boolean xoaCaLam(String ma) {
        CaLam caLam = tim(ma);
        if (caLam == null) {
            return false;
        }
        soLuong--;
        return listCaLam.remove(caLam);
    }

    // tìm ca làm của 1 ngày cụ thể dự trên số ca , và lichtrongngay truyền vào
    public CaLam tim(int soCa, LichTrongNgay lichTrongNgay) {
        if (lichTrongNgay == null) {
            return null;
        }
        ArrayList<CaLam> listCaLam = lichTrongNgay.getListCaLam();
        for (CaLam caLam : listCaLam) {
            if (caLam.getSo() == soCa) {
                return caLam;
            }
        }
        return null;
    }

    // trả về ca làm hiện tại (dự theo giờ thực tế) của 1 lịch trong ngày truyền vào
    public CaLam caLamHienTai(LichTrongNgay lichTrongNgay) {
        if (lichTrongNgay == null) {
            return null;
        }
        String gio = ThoiGian.layGioHienTai();
        ArrayList<CaLam> listCaLam = lichTrongNgay.getListCaLam();
        if (listCaLam == null) {
            return null;
        }
        for (int i = 0; i < listCaLam.size(); i++) {
            if (ThoiGian.gioTrongKhoan(gio, listCaLam.get(i).getGioBatDau(), listCaLam.get(i).getGioKetThuc())) {
                return listCaLam.get(i);
            }
        }
        return null;
    }

    // tạo ra 1 list 3 caLam để giúp khởi tạo thuộc tính listCaLam trong lịch trong
    // ngày
    public ArrayList<CaLam> taoListCaLamTrongTrongNgay(Database db) {
        ArrayList<CaLam> listCaLamMau = new ArrayList<>();
        DanhSachCaLam danhSachCaLam = db.getDanhSachCaLam();
        String gioBatDau1 = "06:00";
        String gioKetThuc1 = "12:00";
        CaLam ca1 = new CaLam(CapMa.capMaCaLam(db), 1, gioBatDau1, gioKetThuc1, 2);
        danhSachCaLam.them(ca1);
        listCaLamMau.add(ca1);
        String gioBatDau2 = "12:00";
        String gioKetThuc2 = "18:00";
        CaLam ca2 = new CaLam(CapMa.capMaCaLam(db), 2, gioBatDau2, gioKetThuc2, 2);
        listCaLamMau.add(ca2);
        danhSachCaLam.them(ca2);
        String gioBatDau3 = "18:00";
        String gioKetThuc3 = "23:00";
        CaLam ca3 = new CaLam(CapMa.capMaCaLam(db), 3, gioBatDau3, gioKetThuc3, 2);
        danhSachCaLam.them(ca3);
        listCaLamMau.add(ca3);
        return listCaLamMau;
    }
}
