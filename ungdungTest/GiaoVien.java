import java.util.ArrayList;

public class GiaoVien extends User implements GiangDay, GiaoTiep {
    private ArrayList<Lop> listLop = new ArrayList<>();
    private ArrayList<TinNhan> listTinNhan = new ArrayList<>();
    private MonHoc chuyenMon;

    public GiaoVien(String maGv, String ten, String namSinh, String sdt, String gioiTinh, MonHoc chuyenMon) {
        super(ten, namSinh, sdt, gioiTinh);
        setMa(maGv);
        this.chuyenMon = chuyenMon;
    }

    public GiaoVien() {

    }

    public String loai() {
        return "giao vien";
    }

    public ArrayList<TinNhan> getListTinNhan() {
        return listTinNhan;
    }

    public void setListLop(ArrayList<Lop> listLop) {
        this.listLop = listLop;
    }

    public void setListTinNhan(ArrayList<TinNhan> listTinNhan) {
        this.listTinNhan = listTinNhan;
    }

    public void themMotHocSinhVaoLop(Lop lop, HocSinh newHs) {
        lop.themMotHocSinh(newHs);
    }

    public void nhanTinNhan(TinNhan tn) {
        listTinNhan.add(tn);
    }

    public MonHoc getChuyenMon() {
        return chuyenMon;
    }

    public void setChuyenMon(MonHoc chuyenMon) {
        this.chuyenMon = chuyenMon;
    }

    public ArrayList<Lop> getListLop() {
        return listLop;
    }

    public void guiTinNhan(Database db) {
        HocSinh hs = db.timHocSinh(Nhap.nhapLuaChonStr("nhap ma hoc sinh can gui"));
        if (hs == null) {
            System.out.println("khong tim thay hoc sinh");
            return;
        }
        String noiDung = Nhap.nhapLuaChonStr("nhap noi dung can gui : ");
        hs.nhanTinNhan(new TinNhan(getMa(), this.getTen().replaceAll(" ", "_"), noiDung));
        System.out.println("da gui");
    }

    private Lop timLop(String tenLop) {
        for (int i = 0; i < listLop.size(); i++) {
            if (listLop.get(i).getTenLop().equals(tenLop)) {
                return listLop.get(i);
            }
        }
        return null;
    }

    public void quanLyLop(Database db) {
        hienThiTatCaLop();
        Lop lop = timLop(listLop.get(Nhap.nhapLuaChonInt("chon ten lop")).getTenLop());
        if (lop == null) {
            System.out.println("lop khong ton tai");
        }
        QuanLyLop quanLyLop = new QuanLyLop(this, lop, db);
        quanLyLop.quanLy();
    }

    public void xemtatCaTinNhan() {
        if (listTinNhan == null) {
            System.out.println("khong co tin nhan");
        }
        for (int i = 0; i < listTinNhan.size(); i++) {
            listTinNhan.get(i).hienThiThongTin();
        }
    }

    public void xemTinNhanMoiNhat() {
        if (listTinNhan == null) {
            System.out.println("khong co tin nhan");
        }
        int i = listTinNhan.size() - 1;
        while (i >= 0 && i >= listTinNhan.size() - 3) {
            listTinNhan.get(i).hienThiThongTin();
            i--;
        }
    }

    public void hienThiTatCaLop() {
        if (listLop == null) {
            System.out.println("khong co lop de hien thi");
        }
        for (int i = 0; i < listLop.size(); i++) {
            System.out.print(i + ". ");
            listLop.get(i).hienThiThongTin();
        }
    }

    public void themCauHoi(int thuTu, Database db) {
        CauHoi ch = TaoDoiTuong.taoCauHoi(thuTu);
        chuyenMon.themCauHoi(ch);
        db.themCauHoi(ch);
        System.out.println("da them vao kho cau hoi cua mon hoc");
    }

    public void themLop(Lop lop) {
        if (lop == null) {
            return;
        }
        listLop.add(lop);
    }

    public void GiaoBaiKiemTra(Database db) {
        GiaoDien.Printf_menuGiaoBaiKiemTra();
        int loaiChucNang = Nhap.nhapLuaChonInt("nhap loai chuc nang : ");
        int soCau = Nhap.nhapLuaChonInt("nhap so cau cho bai kiem tra : ");
        BaiKiemTra kt = null;
        if (loaiChucNang == 1) {
            kt = TaoDoiTuong.taoBaiKiemTra(this.getChuyenMon().getTenMon(), this.getTen(), soCau, db);
        } else if (loaiChucNang == 2) {
            kt = chuyenMon.taoBaiKiemTra(soCau, this.getTen(), db.getListBaiKiemTra().size());
        } else {
            return;
        }
        hienThiTatCaLop();
        int luaChon = Nhap.nhapLuaChonInt("chon lop de giao bai tap (-1 de thoat) : ");
        if (luaChon == -1) {
            return;
        }
        listLop.get(luaChon).giaoBaiKiemTra(kt);
        db.themBaiKiemTra(kt);
    }

    public void hienThiThongTin() {
        System.out.println("Ma giang vien: " + getMa());
        System.out.println("Ho va ten: " + getTen());
        System.out.println("Tuoi: " + getTuoi());
        System.out.println("Nam sinh: " + getNamSinh());
        System.out.println("So dien thoai: " + getSdt());
        System.out.println("Gioi tinh: " + getGioiTinh());
        System.out.println("Chuyen mon: " + chuyenMon.getTenMon());
        System.out.println("========================");
    }

}