package quanli;

import database.Database;
import model.CaLam;
import model.LichTrongTuan;
import model.LichTrongNgay;
import model.NhanVien;
import util.Nhap;
import util.ThoiGian;
import util.XoaManHinh;

import java.util.ArrayList;

import danhsach.DanhSachCaLam;
import danhsach.DanhSachLichTrongNgay;
import danhsach.DanhSachNhanVien;

public class QuanLyLichTrongTuan {
    private Database db; // cơ sở dữ liệu chính
    private LichTrongTuan lichTrongTuan; // đối tượng lịch trong tuần cần quản lý

    public QuanLyLichTrongTuan(Database db, LichTrongTuan lichTrongTuan) {
        this.db = db;
        this.lichTrongTuan = lichTrongTuan;
    }

    // ====== ĐIỂM DANH ======
    public boolean diemDanh(NhanVien nhanVien) {
        if (nhanVien == null)
            return false; // nếu nhân viên null thì hủy
        DanhSachCaLam danhSachCaLam = db.getDanhSachCaLam(); // lấy danh sách ca làm
        DanhSachLichTrongNgay danhSachLichTrongNgay = db.getDanhSachLichTrongNgay(); // danh sách lịch ngày
        if (lichTrongTuan == null)
            return false; // chưa có lịch tuần

        LichTrongNgay lichTrongNgay = danhSachLichTrongNgay.lichTrongNgayHomNay(); // lấy lịch hôm nay
        if (lichTrongNgay == null)
            return false;

        CaLam caLamHienTai = danhSachCaLam.caLamHienTai(lichTrongNgay); // lấy ca làm hiện tại
        if (caLamHienTai == null)
            return false;

        return caLamHienTai.diemDanh(nhanVien); // thực hiện điểm danh trong ca
    }

    // điểm danh thủ công theo mã nhân viên
    public void diemDanh() {
        NhanVien nhanVien = db.getDanhSachNhanVien().tim(Nhap.nhapStr("Nhap ma nhan vien can diem danh : "));
        if (nhanVien == null) {
            System.out.println("Khong tim thay nhan vien");
            return;
        }
        if (diemDanh(nhanVien)) {
            System.out.println("Diem danh thanh cong");
        } else {
            System.out.println("Nhan vien " + nhanVien.getMa() + " khong co ca lam hien tai");
        }
    }

    // ====== XÓA NHÂN VIÊN KHỎI CA ======
    private void xoaNhanVienKhoiCa() {
        xemLichLamViec(); // hiển thị lịch để chọn
        int index = Nhap.nhapInt("Chon thu can chinh sua : ") - 2; // -2 vì thứ 2 ứng index 0
        if (!(index >= 0 && index <= 6)) { // kiểm tra hợp lệ thứ trong tuần
            System.out.println("Lua chon khong hop le");
            return;
        }
        LichTrongNgay lichTrongNgay = lichTrongTuan.getLichTuan().get(index);
        int soCa = Nhap.nhapInt("Nhap so CaLam can chinh sua : ");
        CaLam caLam = db.getDanhSachCaLam().tim(soCa, lichTrongNgay);
        if (caLam == null) {
            System.out.println("Khong tim thay ca lam");
            return;
        }
        if (caLam.soNguoiTrongCa() == 0) {
            System.out.println("Khong co nhan vien de xoa");
            return;
        }

        DanhSachNhanVien danhSachNhanVien = db.getDanhSachNhanVien();
        NhanVien nhanVien = danhSachNhanVien.tim(Nhap.nhapStr("Nhap ma nhan vien xoa khoi ca lam : "));
        if (nhanVien == null) {
            System.out.println("Khong tim thay nhan vien");
            return;
        }
        caLam.xoaNhanVienKhoiCa(nhanVien); // thực hiện xóa
        System.out.println("Da xoa nhan vien khoi ca lam");
    }

    // ====== THÊM NHÂN VIÊN VÀO CA ======
    private void themNhanVienVaoCa() {
        xemLichLamViec(); // in lịch tuần ra để chọn
        int index = Nhap.nhapInt("Chon thu can chinh sua ( thu2 - thu8 (cn) ) : ") - 2;
        if (!(index >= 0 && index <= 6)) { // kiểm tra hợp lệ thứ trong tuần
            System.out.println("Lua chon khong hop le");
            return;
        }
        LichTrongNgay lichTrongNgay = lichTrongTuan.getLichTuan().get(index);
        int soCa = Nhap.nhapInt("Nhap so CaLam can chinh sua : ");
        CaLam caLam = db.getDanhSachCaLam().tim(soCa, lichTrongNgay);
        if (caLam == null) {
            System.out.println("Khong tim thay ca lam");
            return;
        }
        if (caLam.caLamDuNguoi()) {
            System.out.println("Ca lam da du nguoi");
            return;
        }

        // nhập số lượng cần thêm
        DanhSachNhanVien danhSachNhanVien = db.getDanhSachNhanVien();
        int soCanThem;
        do {
            soCanThem = Nhap.nhapInt("Nhap so luong can them : ");
            if (soCanThem > caLam.soNguoiThieu()) {
                System.out.println("So luong them khong hop le");
            }
        } while (soCanThem > caLam.soNguoiThieu());

        // thêm từng nhân viên
        while (soCanThem-- > 0) {
            NhanVien nhanVien = null;
            do {
                nhanVien = danhSachNhanVien.tim(Nhap.nhapStr("Nhap ma nhan vien de them vao ca lam : "));
                if (nhanVien == null) {
                    System.out.println("Khong tim thay nhan vien, vui long nhap lai : ");
                }
            } while (nhanVien == null);

            caLam.themNhanVienVaoCa(nhanVien);
            System.out.println("Da them nhan vien vao ca");
        }
    }

    // chọn chức năng xếp lịch (thêm / xóa)
    private void thucHienChucNangXepLich(int luaChon) {
        switch (luaChon) {
            case 1 -> themNhanVienVaoCa();
            case 2 -> xoaNhanVienKhoiCa();
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    // in menu con cho xếp lịch
    public void xuatMenuXepLich() {
        System.out.println("1. Them nhan vien vao ca");
        System.out.println("2. Xoa nhan vien khoi ca");
        System.out.println("0. Thoat");
    }

    // menu thao tác xếp lịch
    public void xepLichLamViec() {
        while (true) {
            XoaManHinh.xoa();
            xuatMenuXepLich();
            int luaChon = Nhap.nhapInt("Nhap lua chon : ");
            if (luaChon == 0)
                return;
            thucHienChucNangXepLich(luaChon);
            Nhap.pause();
        }
    }

    // ====== XEM LỊCH LÀM VIỆC TRONG TUẦN ======
    public void xemLichLamViec() {
        ArrayList<LichTrongNgay> listLichTrongNgay = lichTrongTuan.getLichTuan();
        if (listLichTrongNgay == null || listLichTrongNgay.size() == 0) {
            System.out.println("Chua xep lich hom nao trong tuan");
            return;
        }

        // tạo bảng 7 cột (7 ngày) cho từng ca
        ArrayList<ArrayList<String>> ca1 = new ArrayList<>();
        for (int j = 0; j < 7; j++)
            ca1.add(new ArrayList<>());

        ArrayList<ArrayList<String>> ca2 = new ArrayList<>();
        for (int j = 0; j < 7; j++)
            ca2.add(new ArrayList<>());

        ArrayList<ArrayList<String>> ca3 = new ArrayList<>();
        for (int j = 0; j < 7; j++)
            ca3.add(new ArrayList<>());

        // duyệt từng ngày trong tuần để đổ dữ liệu ca
        for (int i = 0; i < listLichTrongNgay.size(); i++) {
            LichTrongNgay lichTrongNgay = listLichTrongNgay.get(i);
            ArrayList<CaLam> listCaLam = lichTrongNgay.getListCaLam();

            for (CaLam caLam : listCaLam) {
                ArrayList<String> listNhanVien = null;
                if (caLam.getSo() == 1)
                    listNhanVien = ca1.get(i);
                if (caLam.getSo() == 2)
                    listNhanVien = ca2.get(i);
                if (caLam.getSo() == 3)
                    listNhanVien = ca3.get(i);

                // thêm nhân viên vào danh sách hiển thị
                for (NhanVien nhanVien : caLam.getListNhanVien().getMapNhanVien().keySet()) {
                    listNhanVien.add(String.format("%-14s", nhanVien.getMa() + " " + nhanVien.get_ten()) + "|");
                }
                // thêm "Trống" nếu chưa đủ người
                for (int k = caLam.soNguoiTrongCa(); k < caLam.getSoLuongCan(); k++) {
                    listNhanVien.add(String.format("%-14s", "Trong") + "|");
                }
                // đảm bảo đủ 3 dòng hiển thị
                while (listNhanVien.size() < 3)
                    listNhanVien.add("              |");
            }
        }

        // in bảng lịch tuần
        System.out.println();
        System.out.println("                                        Lich lam viec cua nhan vien");
        System.out.println("                                        " + lichTrongTuan.getNgayThu2() + " - "
                + lichTrongTuan.getNgayCn());
        System.out.println();
        System.out.println(
                "      ----------------------------------------------------------------------------------------------------------");
        System.out.println(
                "      |_____Thu 2____|____Thu 3_____|_____Thu 4____|_____Thu 5____|____Thu 6_____|____Thu 7_____|_______CN_____|");

        // in ca 1
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                System.out.print("Ca 1  |");
            if (i == 1)
                System.out.print("06:00 |");
            if (i == 2)
                System.out.print("12:00 |");
            for (int j = 0; j < 7; j++)
                System.out.print(ca1.get(j).get(i));
            System.out.println();
        }
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------");

        // in ca 2
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                System.out.print("Ca 2  |");
            if (i == 1)
                System.out.print("12:00 |");
            if (i == 2)
                System.out.print("18:00 |");
            for (int j = 0; j < 7; j++)
                System.out.print(ca2.get(j).get(i));
            System.out.println();
        }
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------");

        // in ca 3
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                System.out.print("Ca 3  |");
            if (i == 1)
                System.out.print("18:00 |");
            if (i == 2)
                System.out.print("23:00 |");
            for (int j = 0; j < 7; j++)
                System.out.print(ca3.get(j).get(i));
            System.out.println();
        }
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    // ====== THỐNG KÊ ĐIỂM DANH ======
    public void thongKeDiemDanhTrongTuan() {
        System.out.println();
        System.out
                .println("Tinh trang diem danh trong tuan " + ThoiGian.laySoTuanTrongNam(lichTrongTuan.getNgayThu2()));
        System.out.println("----------------------------------");

        // duyệt từng ngày để in trạng thái điểm danh
        for (LichTrongNgay lichTrongNgay : lichTrongTuan.getListLichTrongNgay()) {
            System.out.println(lichTrongNgay.getNgay());
            System.out.println("Thu " + lichTrongNgay.getThu());
            for (CaLam caLam : lichTrongNgay.getListCaLam()) {
                System.out.println("ca " + caLam.getSo() + ": ");
                for (NhanVien nhanVien : caLam.getListNhanVien().getMapNhanVien().keySet()) {
                    System.out.println("Nhan Vien : " + nhanVien.getMa() + " " + nhanVien.getTen()
                            + (caLam.getListNhanVien().kiemTraDiemDanh(nhanVien) ? " Da diem danh "
                                    : " Chua diem danh"));
                }
            }
            System.out.println();
        }
    }

    // ====== MENU CHÍNH ======
    private void xuatMenu() {
        System.out.println("======= Quan Ly Lich Trong Tuan =======");
        System.out.println("1. Thong ke diem danh trong tuan");
        System.out.println("2. Xep lich lam viec");
        System.out.println("3. Xem lich lam viec");
        System.out.println("4. Diem danh cho nhan vien");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    private void thucHienChucNang(int luaChon) {
        switch (luaChon) {
            case 0 -> System.out.println("Thoat menu lich lam viec");
            case 1 -> thongKeDiemDanhTrongTuan();
            case 2 -> xepLichLamViec();
            case 3 -> xemLichLamViec();
            case 4 -> diemDanh();
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    public void menu() {
        int xacNhan = 1;
        while (xacNhan == 1) {
            XoaManHinh.xoa();
            xuatMenu();
            int luaChon = Nhap.nhapInt("Nhap lua Chon : ");
            if (luaChon == 0)
                return;
            thucHienChucNang(luaChon);
            Nhap.pause();
        }
    }
}
