import java.util.ArrayList;

public class BaiKiemTra {
    private String maBaiKt;
    //////////////////////
    private String mon;
    private int namHoc;
    private int hocKi;
    private int tongSoCau;

    private String giaoVien;

    private String ngayBd;
    private String ngayKt;

    private ArrayList<CauHoi> listCauHoi;
    private ArrayList<Diem> listDiem; // luu ket qua

    public BaiKiemTra() {

    }

    public BaiKiemTra(String mon, String tenGv, String ngayBd, String ngayKt, int namHoc, int hocKi) {
        this.mon = mon;
        this.giaoVien = tenGv;
        this.ngayBd = ngayBd;
        this.ngayKt = ngayKt;
        this.namHoc = namHoc;
        this.hocKi = hocKi;
        tongSoCau = 0;
    }

    public BaiKiemTra(int soCau, String mon, String tenGv, String ngayBd, String ngayKt, int namHoc, int hocKi) {
        this.tongSoCau = soCau;
        this.mon = mon;
        this.giaoVien = tenGv;
        this.ngayBd = ngayBd;
        this.ngayKt = ngayKt;
        this.namHoc = namHoc;
        this.hocKi = hocKi;
        tongSoCau = 0;
    }

    public BaiKiemTra(String ma, int soCau, String mon, String tenGv, String ngayBd, String ngayKt, int namHoc,
            int hocKi) {
        this.tongSoCau = soCau;
        this.mon = mon;
        this.giaoVien = tenGv;
        this.ngayBd = ngayBd;
        this.ngayKt = ngayKt;
        this.namHoc = namHoc;
        this.hocKi = hocKi;
        tongSoCau = 0;
        this.maBaiKt = ma;
    }

    //////////////  get , set 
    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    public void setListCauHoi(ArrayList<CauHoi> listCauHoi) {
        this.listCauHoi = listCauHoi;
    }

    public void setListDiem(ArrayList<Diem> listDiem) {
        this.listDiem = listDiem;
    }

    public void setMaBaiKt(String maBaiKt) {
        this.maBaiKt = maBaiKt;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public void setNamHoc(int namHoc) {
        this.namHoc = namHoc;
    }

    public void setNgayBd(String ngayBd) {
        this.ngayBd = ngayBd;
    }

    public void setNgayKt(String ngayKt) {
        this.ngayKt = ngayKt;
    }

    public void setTongSoCau(int tongSoCau) {
        this.tongSoCau = tongSoCau;
    }

    public String getMaBaiKt() {
        return maBaiKt;
    }

    public int getHocKi() {
        return hocKi;
    }

    public int getNamHoc() {
        return namHoc;
    }

    public String getMon() {
        return mon;
    }

    public ArrayList<CauHoi> getListCauHoi() {
        return listCauHoi;
    }

    public int getSoCau() {
        return ((listCauHoi == null) ? 0 : listCauHoi.size());
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public ArrayList<Diem> getListDiem() {
        return listDiem;
    }

    public String getNgayBd() {
        return ngayBd;
    }

    public String getNgayKt() {
        return ngayKt;
    }

    public int getTongSoCau() {
        return tongSoCau;
    }

    //////////////////////////////////    phuong thuoc
    public void hienThiTatCaCauHoi() {
        for (CauHoi cauHoi : listCauHoi) {
            cauHoi.hienThiCauHoi();
        }
    }

    public void hienThiThongTin() {
        System.out.println(maBaiKt + " " + ngayBd + "_" + ngayKt + " " + mon + " " + giaoVien + " so cau : "
                + getTongSoCau());
    }

    public double tinhDiem(int cauDung) {
        return cauDung * 1.0 / tongSoCau * 10;
    }

    public void luuDiem(Diem diem) {
        if (listDiem == null) {
            listDiem = new ArrayList<>();
        }
        listDiem.add(diem);
    }

    public void themCauHoi(CauHoi newCh) {
        if (listCauHoi == null) {
            listCauHoi = new ArrayList<>();
        }
        listCauHoi.add(newCh);
        tongSoCau++;
    }

    public void hienThiKetQua() {
        if (listDiem.size() == 0) {
            System.out.println("chua co hoc sinh nao nop bai");
            return;
        }
        System.out.println("ket qua cua bai kiem tra mon" + mon + "-" + giaoVien + "-" + namHoc + "-" + hocKi + "   "
                + ngayBd + "-" + ngayKt);
        for (int i = 0; i < listDiem.size(); i++) {
            listDiem.get(i).hienThiThongTin();
        }
        System.out.println("===================");
    }
}
