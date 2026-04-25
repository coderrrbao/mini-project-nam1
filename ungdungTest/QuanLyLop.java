public class QuanLyLop {
    private GiaoVien gv;
    private Lop lop;
    private Database db;

    public QuanLyLop(GiaoVien gv, Lop lop, Database db) {
        this.gv = gv;
        this.lop = lop;
        this.db = db;
    }

    public GiaoVien getGv() {
        return gv;
    }

    public Lop getLop() {
        return lop;
    }

    public void setGv(GiaoVien gv) {
        this.gv = gv;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public void menuQuanLyLop() {
        int xacNhan = 1;
        while (xacNhan == 1) {
            GiaoDien.Printf_menuQuanLyLop();
            int luaChon = Nhap.nhapLuaChonInt("nhap chuc nang can dung : ");
            System.out.println("==============================");
            chucNangQlyLop(luaChon);
            xacNhan = Nhap.nhapLuaChonInt("(1)tiep tuc  (khac)thoat");
            System.out.println("==============================");
        }
    }

    public void chucNangQlyLop(int luaChon) {
        switch (luaChon) {
            case 1 -> {
                lop.themMotHocSinh(db.timHocSinh(Nhap.nhapLuaChonStr("nhap ma hoc sinh can them : ")));
                System.out.println("da them");
            }
            case 2 -> lop.xoaMotHocSinh(Nhap.nhapLuaChonStr("nhap ma hoc sinh can xoa : "));
            case 3 ->
                lop.giaoBaiKiemTra(gv.getChuyenMon().taoBaiKiemTra(Nhap.nhapLuaChonInt("nhap so cau : "), gv.getTen(),
                        db.getListBaiKiemTra().size()));
            case 4 -> lop.thongKeKetQuaBaikiemTra();
            case 5 -> lop.timHocSinhTrongLopBangMa((Nhap.nhapLuaChonStr("nhap ma hoc sinh can tim : ")));
            case 6 -> lop.hienThiTatCaHs();
            default -> System.out.println("lua chon khong lop le");
        }
    }

    public void quanLy() {
        int xacNhan = 1;
        while (xacNhan == 1) {
            menuQuanLyLop();
            xacNhan = Nhap.nhapLuaChonInt("nhap 0 de thoat");
        }
    }
}
