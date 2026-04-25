public class UngDungQuanLy {
    private Database db;
    User userQl;

    public UngDungQuanLy() {
        db = new Database();
        userQl = null;
    }

    public void menuHocsinh(HocSinh hs) {
        int xacNhan = 1;
        while (xacNhan == 1) {
            GiaoDien.Printf_menuHocsinh();
            int luaChon = Nhap.nhapLuaChonInt("lua chon chuc nang : ");
            System.out.println("==============================");
            chucNangHs(luaChon, hs);
            if (luaChon == 0) {
                return;
            }
            xacNhan = Nhap.nhapLuaChonInt("(1) tiep tuc  (khac) thoat : ");
        }
    }

    public void chucNangHs(int luaChon, HocSinh hs) {
        switch (luaChon) {
            case 0 -> System.out.println("da thoat");
            case 1 -> hs.hienThiThongTin();
            case 2 -> hs.lamBaiKiemTra();
            case 3 -> hs.thongKeDiem();
            case 4 -> hs.guiTinNhan(db);
            case 5 -> hs.xemtatCaTinNhan();
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    public void menuGiaoVien(GiaoVien gv) {
        int xacNhan = 1;
        while (xacNhan == 1) {
            GiaoDien.Printf_menuGiaoVien();
            int luaChon = Nhap.nhapLuaChonInt("lua chon chuc nang : ");
            System.out.println("==============================");
            chucNangGv(luaChon, gv);
            if (luaChon == 0) {
                return;
            }
            xacNhan = Nhap.nhapLuaChonInt("(1) tiep tuc  (khac) thoat : ");
        }
    }

    public void chucNangGv(int luaChon, GiaoVien gv) {
        switch (luaChon) {
            case 0 -> System.out.println("da thoat");
            case 1 -> gv.hienThiThongTin();
            case 2 -> gv.GiaoBaiKiemTra(db);
            case 3 -> gv.themCauHoi(db.laySoCauHoi(), db);
            case 4 -> gv.guiTinNhan(db);
            case 5 -> gv.xemtatCaTinNhan();
            case 6 -> gv.quanLyLop(db);
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    public void dieuHuongTaiKhoan(User user) {
        if (user.loai() == "giao vien") {
            menuGiaoVien((GiaoVien) user);
        } else if (user.loai() == "hoc sinh") {
            menuHocsinh((HocSinh) user);
        }
    }

    public void dangNhap() {
        int xacNhan = 1;
        while (xacNhan == 1) {
            GhiFile.saoLuaDuLieu(db);
            TaiKhoan tk = TienIch.dangNhap(db);
            if (tk == null) {
                return;
            }
            userQl = tk.tryCapTaiKhoan();
            dieuHuongTaiKhoan(userQl);
            xacNhan = Nhap.nhapLuaChonInt("(1)Quay lai dang nhap   (khac)Thoat");
        }

    }

    public static void main(String[] args) {
        UngDungQuanLy app = new UngDungQuanLy();
        app.dangNhap();
    }
}