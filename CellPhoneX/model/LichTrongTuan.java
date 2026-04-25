package model;

import java.util.ArrayList;

import database.Database;
import util.CapMa;
import util.ThoiGian;

public class LichTrongTuan {
    private String ma;
    private String ngayThu2;
    private String ngayCn;
    private ArrayList<LichTrongNgay> listLichTrongNgay;// 7 phan tu

    public LichTrongTuan() {
        listLichTrongNgay = new ArrayList<>();
    }

    public LichTrongTuan(String ma, String ngayThu2, String ngayCn) {
        this.ma = ma;
        this.ngayThu2 = ngayThu2;
        this.ngayCn = ngayCn;
        listLichTrongNgay = new ArrayList<>();
    }

    public ArrayList<LichTrongNgay> getLichTuan() {
        return listLichTrongNgay;
    }

    public ArrayList<LichTrongNgay> getListLichTrongNgay() {
        return listLichTrongNgay;
    }

    public String getMa() {
        return ma;
    }

    public String getNgayThu2() {
        return ngayThu2;
    }

    public String getNgayCn() {
        return ngayCn;
    }

    public void setListLichTrongNgay(ArrayList<LichTrongNgay> listLichTrongNgay) {
        this.listLichTrongNgay = listLichTrongNgay;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setNgayThu2(String ngayThu2) {/////
        this.ngayThu2 = ngayThu2;
        this.ngayCn = ThoiGian.nNgayTiepTheo(ngayThu2, 6);
    }

    public void setNgayCn(String ngayCn) {////
        this.ngayCn = ngayCn;
    }

    public boolean themLichTrongNgay(LichTrongNgay lichTrongNgay) {
        if (lichTrongNgay == null) {
            return false;
        }
        return listLichTrongNgay.add(lichTrongNgay);
    }

    public static LichTrongTuan taoLichTrongTuan(int soTuan, Database db) {
        LichTrongTuan lichTrongTuan = new LichTrongTuan();
        lichTrongTuan.setListLichTrongNgay(
                db.getDanhSachLichTrongNgay().taoListLichTrongNgay(db, ThoiGian.layNgayDauTuanStr(soTuan)));
        lichTrongTuan.setMa(CapMa.capMaLichTrongTuan(db));
        lichTrongTuan.setNgayThu2(ThoiGian.layNgayDauTuanStr(soTuan));
        db.setLichTrongTuanNay(lichTrongTuan);
        return lichTrongTuan;
    }
}
