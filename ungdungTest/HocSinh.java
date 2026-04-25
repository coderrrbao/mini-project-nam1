import java.util.ArrayList;

public class HocSinh extends User implements HocTap, GiaoTiep {
    private String nienKhoa;
    private ArrayList<BaiKiemTra> listBaiKiemTra;
    private ArrayList<TinNhan> listTinNhan;
    private ArrayList<Diem> listDiem = new ArrayList<>();

    public HocSinh(String maSv, String ten, String namSinh, String sdt, String gioiTinh,
            String nienKhoa) {
        super(ten, namSinh, sdt, gioiTinh);
        setMa(maSv);
        this.nienKhoa = nienKhoa;
        listDiem = new ArrayList<>();
        listBaiKiemTra = new ArrayList<>();
        listTinNhan = new ArrayList<>();
    }

    public HocSinh() {

    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public ArrayList<TinNhan> getListTinNhan() {
        return listTinNhan;
    }

    public ArrayList<Diem> getListDiem() {
        return listDiem;
    }

    public ArrayList<BaiKiemTra> getListBaiKiemTra() {
        return listBaiKiemTra;
    }

    public void setListBaiKiemTra(ArrayList<BaiKiemTra> listBaiKiemTra) {
        this.listBaiKiemTra = listBaiKiemTra;
    }

    public void setListDiem(ArrayList<Diem> listDiem) {
        this.listDiem = listDiem;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public void setListTinNhan(ArrayList<TinNhan> listTinNhan) {
        this.listTinNhan = listTinNhan;
    }

    public String loai() {
        return "hoc sinh";
    }

    public void luuDiem(Diem diem) {
        if (listDiem == null) {
            listDiem = new ArrayList<>();
        }
        if (diem == null) {
            return;
        }
        listDiem.add(diem);
    }

    public void hienThiThongTin() {
        System.out.println("Ma sinh vien: " + getMa());
        System.out.println("Ho va ten: " + getTen());
        System.out.println("Nien khoa: " + nienKhoa);
        System.out.println("Tuoi: " + getTuoi());
        System.out.println("Nam sinh: " + getNamSinh());
        System.out.println("So dien thoai: " + getSdt());
        System.out.println("Gioi tinh: " + getGioiTinh());
        System.out.println("========================");
    }

    public void nhanBaiKiemTra(BaiKiemTra newBaiKT) {
        if (listBaiKiemTra == null) {
            listBaiKiemTra = new ArrayList<>();
        }
        if (newBaiKT == null) {
            System.out.println("khong nhan duoc bai kiem tra");
            return;
        }
        listBaiKiemTra.add(newBaiKT);
    }

    public void lamBaiKiemTra() {
        if (listBaiKiemTra == null || listBaiKiemTra.size() == 0) {
            System.out.println("khong co bai kiem tra");
            return;
        }
        for (int i = 0; i < listBaiKiemTra.size(); i++) {
            System.out.print(i + ". ");
            listBaiKiemTra.get(i).hienThiThongTin();
        }

        int luaChon = Nhap.nhapLuaChonInt("Chon bai kiem tra : ");

        if (luaChon < 0 || luaChon >= listBaiKiemTra.size()) {
            System.out.println("lua chon khong hop le");
        } else {
            BaiKiemTra kt = listBaiKiemTra.get(luaChon);
            int soCauDung = TienIch.lamBaiKiemTra(kt);
            Diem diem = new Diem(getMa(), getTen(), kt.tinhDiem(soCauDung), kt.getMon(), kt.getHocKi(), kt.getNamHoc());
            diem.hienThiKetQua();
            luuDiem(diem);
            kt.luuDiem(diem);
            listBaiKiemTra.remove(kt);
        }
    }

    public void thongKeDiem() {
        if (listBaiKiemTra == null) {
            System.out.println("khong co danh sach diem da luu");
        }
        for (int i = 0; i < listDiem.size(); i++) {
            listDiem.get(i).hienThiThongTin();
        }
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

    public void guiTinNhan(Database db) {
        GiaoVien gv = db.timGiaoVien(Nhap.nhapLuaChonStr("nhap ma giao vien can gui"));
        if (gv == null) {
            System.out.println("khong tim thay giao vien");
            return;
        }
        String noiDung = Nhap.nhapLuaChonStr("nhap noi dung can gui : ");
        gv.nhanTinNhan(new TinNhan(getMa(), this.getTen().replace(" ", "_"), noiDung));
        System.out.println("da gui");
    }

    public void nhanTinNhan(TinNhan tn) {
        if (tn == null) {
            return;
        }
        if (listTinNhan == null) {
            listTinNhan = new ArrayList<>();
        }
        listTinNhan.add(tn);
    }
}
