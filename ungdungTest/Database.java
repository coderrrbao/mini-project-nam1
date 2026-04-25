import java.util.ArrayList;

public class Database { // them doi tuong moi nao phai thong qua day
    private ArrayList<GiaoVien> listGiaoVien;
    private ArrayList<TaiKhoan> listTaiKhoan;
    private ArrayList<HocSinh> listHocSinh;
    private ArrayList<MonHoc> listMonHoc;
    private ArrayList<Lop> listLop;
    private ArrayList<BaiKiemTra> listBaiKiemTra = new ArrayList<>();
    private ArrayList<CauHoi> listCauHoi;

    // Constructor
    public Database() {

        DocFile.doc_Data_CauHoitxt(this, "Data\\CauHoi.txt");
        DocFile.doc_Data_MonHoc(this, "Data\\MonHoc");
        DocFile.doc_Data_BaiKiemTra(this, "Data\\BaiKiemTra");
        DocFile.doc_Data_HocSinh(this, "Data\\HocSinh");
        DocFile.doc_Data_Lop(this, "Data\\Lop");
        DocFile.doc_Data_GiaoVien(this, "Data\\GiaoVien");
    }

    public ArrayList<BaiKiemTra> getListBaiKiemTra() {
        return listBaiKiemTra;
    }

    public void themBaiKiemTra(BaiKiemTra baiKiemTra) {
        if (baiKiemTra == null) {
            listBaiKiemTra = new ArrayList<>();
        }
        listBaiKiemTra.add(baiKiemTra);
    }

    public void setListBaiKiemTra(ArrayList<BaiKiemTra> listBaiKiemTra) {
        this.listBaiKiemTra = listBaiKiemTra;
    }

    public ArrayList<MonHoc> getListMonHoc() {
        return listMonHoc;
    }

    public void setListHocSinh(ArrayList<HocSinh> listHocSinh) {
        this.listHocSinh = listHocSinh;
    }

    public void setListMonHoc(ArrayList<MonHoc> listMonHoc) {
        this.listMonHoc = listMonHoc;
    }

    public ArrayList<GiaoVien> getListGiaoVien() {
        return listGiaoVien;
    }

    public void setListGiaoVien(ArrayList<GiaoVien> listGiaoVien) {
        this.listGiaoVien = listGiaoVien;
    }

    public ArrayList<TaiKhoan> getListTaiKhoan() {
        return listTaiKhoan;
    }

    public void setListTaiKhoan(ArrayList<TaiKhoan> listTaiKhoan) {
        this.listTaiKhoan = listTaiKhoan;
    }

    public ArrayList<HocSinh> getListHocSinh() {
        return listHocSinh;
    }

    public void themGiaoVien(GiaoVien gv) {
        if (listGiaoVien == null) {
            listGiaoVien = new ArrayList<>();
        }
        listGiaoVien.add(gv);
    }

    public void themTaiKhoan(TaiKhoan tk) {
        if (listTaiKhoan == null) {
            listTaiKhoan = new ArrayList<>();
        }
        listTaiKhoan.add(tk);
    }

    public void themHocSinh(HocSinh newHs) {
        if (listHocSinh == null) {
            listHocSinh = new ArrayList<>();
        }
        listHocSinh.add(newHs);
    }

    public void setListLop(ArrayList<Lop> listLop) {
        this.listLop = listLop;
    }

    public ArrayList<Lop> getListLop() {
        return listLop;
    }

    public ArrayList<CauHoi> getListCauHoi() {
        return listCauHoi;
    }

    public void setListCauHoi(ArrayList<CauHoi> listCauHoi) {
        this.listCauHoi = listCauHoi;
    }

    public int laySoCauHoi() {
        return (listCauHoi == null) ? 0 : listCauHoi.size();
    }

    /////////////////////////////////////

    public TaiKhoan dangNhap(TaiKhoan tk) { // tai khoan chu mk tk do nguoi dung nhap de tim user
        for (int i = 0; i < listTaiKhoan.size(); i++) {
            if (listTaiKhoan.get(i).kiemTra(tk)) {
                return listTaiKhoan.get(i);
            }
        }
        return null;
    }

    //////////   nhap ma de tra ve doi tuong phu hop
    public GiaoVien timGiaoVien(String ma) {
        for (int i = 0; i < listGiaoVien.size(); i++) {
            if (listGiaoVien.get(i).getMa().equals(ma)) {
                return listGiaoVien.get(i);
            }
        }
        return null;
    }

    public HocSinh timHocSinh(String ma) {
        for (int i = 0; i < listHocSinh.size(); i++) {
            if (listHocSinh.get(i).getMa().equals(ma)) {
                return listHocSinh.get(i);
            }
        }
        return null;
    }

    public Lop timLop(String tenLop) {
        for (int i = 0; i < listLop.size(); i++) {
            if (listLop.get(i).getTenLop().equals(tenLop)) {
                return listLop.get(i);
            }
        }
        return null;
    }

    public MonHoc timMonHoc(String tenMon) {
        for (int i = 0; i < listMonHoc.size(); i++) {
            if (listMonHoc.get(i).getTenMon().equals(tenMon)) {
                return listMonHoc.get(i);
            }
        }
        System.out.println("khong tim dc mon hoc " + tenMon);
        return null;
    }

    public User timUser(String ma) {
        HocSinh hs;
        GiaoVien gv;
        if ((hs = timHocSinh(ma)) != null) {
            return hs;
        }
        if ((gv = timGiaoVien(ma)) != null) {
            return gv;
        }
        return null;
    }

    public BaiKiemTra timBaiKiemTra(String ma) {
        for (int i = 0; i < listBaiKiemTra.size(); i++) {
            if (listBaiKiemTra.get(i).getMaBaiKt().equals(ma)) {
                return listBaiKiemTra.get(i);
            }
        }
        return null;
    }

    public CauHoi timCauHoi(String ma) {
        for (int i = 0; i < listCauHoi.size(); i++) {
            if (listCauHoi.get(i).getMa().equals(ma)) {
                return listCauHoi.get(i);
            }
        }
        return null;
    }

    ///////////////////////////////////////   

    public void lietKeTatCaHocSinh() {
        if (listHocSinh == null) {
            System.out.println("database khong co hoc sinh nao");
        }
        for (int i = 0; i < listHocSinh.size(); i++) {
            listHocSinh.get(i).hienThiThongTin();
            listHocSinh.get(i).getTaiKhoan().tryCapTaiKhoan().hienThiThongTin();
        }
    }

    public void lietKeTatCaGiaoVien() {
        for (int i = 0; i < listGiaoVien.size(); i++) {
            listGiaoVien.get(i).hienThiThongTin();
            listGiaoVien.get(i).hienThiThongTin();
        }
    }

    public void lietKeTatCaMonHoc() {
        for (int i = 0; i < listMonHoc.size(); i++) {
            listMonHoc.get(i).hienThiThongTin();
            listMonHoc.get(i).hienThiTatCaCauHoi();
            System.out.println("--------------------------------------------");
        }
    }

    public void lietKeTatCaLop() {
        for (int i = 0; i < listLop.size(); i++) {
            listLop.get(i).hienThiThongTin();
            System.out.println(listLop.get(i).getListHocSinh().size());
        }
    }

    public void lietKeTatCaCauHoi() {
        for (CauHoi cauHoi : listCauHoi) {
            cauHoi.hienThiCauHoi();
        }
    }

    public void lietKeTatCaBaiKiemTra() {
        for (BaiKiemTra baiKiemTra : listBaiKiemTra) {
            System.out.println(baiKiemTra.getMaBaiKt());
            baiKiemTra.hienThiThongTin();
            baiKiemTra.hienThiTatCaCauHoi();
        }
    }

    //////////////////
    public void themCauHoi(CauHoi cauHoi) {
        if (listCauHoi == null) {
            listCauHoi = new ArrayList<>();
        }
        listCauHoi.add(cauHoi);
    }

    public void themMonHoc(MonHoc monHoc) {
        if (listMonHoc == null) {
            listMonHoc = new ArrayList<>();
        }
        listMonHoc.add(monHoc);
    }

    public void docVaGhiFileMau() {
        DocFile.docFile_DanhSachLoptxt("DuLieuMau\\DanhSachLop.txt", this);
        DocFile.docFile_MonHoctxt("DuLieuMau\\MonHoc.txt", this);
        DocFile.docFile_GiaoVientxt("DuLieuMau\\GiaoVien.txt", this);
        DocFile.docFile_HocSinhtxt("DuLieuMau\\HocSinh.txt", this);
        DocFile.docFile_TaiKhoantxt("DuLieuMau\\TaiKhoan.txt", this);
        DocFile.docFile_CauHoitxt("DuLieuMau\\CauHoi.txt", this);
        DocFile.docFile_HocSinhCuaLoptxt("DuLieuMau\\HocSinhCuaLop.txt", this);
        DocFile.docFile_GiaoVienCuaLoptxt("DuLieuMau\\GiaoVienCuaLop.txt", this);
        GhiFile.saoLuaDuLieu(this);
    }
}