package model;

import java.util.ArrayList;

public class LichTrongNgay {
    private String ma;
    private int thu; // thứ (2-8)
    private String ngay; // ngày
    private ArrayList<CaLam> listCaLam; // 1 ngay gom nhieu ca lam

    public LichTrongNgay(String ma, int thu, String ngay) {
        this.ma = ma;
        this.thu = thu;
        this.ngay = ngay;
        listCaLam = new ArrayList<>();
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setListCaLam(ArrayList<CaLam> listCaLam) {
        this.listCaLam = listCaLam;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public ArrayList<CaLam> getListCaLam() {
        return listCaLam;
    }

    public String getNgay() {
        return ngay;
    }

    public String getMa() {
        return ma;
    }

    public int getThu() {
        return thu;
    }

    public boolean themCaLam(CaLam caLam) {
        if (caLam == null) {
            return false;
        }
        return listCaLam.add(caLam);
    }
}
