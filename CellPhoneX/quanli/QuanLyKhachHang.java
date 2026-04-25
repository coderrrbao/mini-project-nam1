package quanli;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import danhsach.DanhSachHangThanhVien;
import danhsach.DanhSachKhachHang;
import database.Database;
import model.BaoHanh;
import model.HangThanhVien;
import model.HoaDon;
import model.KhachHang;
import model.MaGiamGia;
import model.PhieuBaoHanh;
import model.PhieuTraHang;
import util.Nhap;
import util.TaoDoiTuong;
import util.XoaManHinh;

// Lớp quản lý khách hàng (thêm, xóa, sửa, tìm kiếm, hiển thị, v.v.)
public class QuanLyKhachHang {
    private Database db; // truy cập dữ liệu chung
    ArrayList<KhachHang> listKhachHang; // danh sách khách hàng trong hệ thống

    public QuanLyKhachHang(Database db) {
        this.db = db;
        this.listKhachHang = db.getListKhachHang(); // lấy danh sách khách hàng từ database
    }

    // tạo khách hàng mới
    public void taoKhachHang() {
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        KhachHang khachHang = TaoDoiTuong.taoKhachHang(db); // gọi lớp tiện ích để tạo khách hàng
        if (danhSachKhachHang.timKhachHangTheoSdt(khachHang.getSdt()) != null) {
            System.out.println("Them that bai! \nDa ton tai khach hang voi so dien thoai " + khachHang.getSdt());
            return;
        }
        danhSachKhachHang.them(khachHang); // thêm vào danh sách
        System.out.println("Da tao khach hang thanh cong");
    }

    // in ra toàn bộ các danh sách con trong một khách hàng (bảo hành, hóa đơn, mã
    // giảm giá, ...)
    private void xuatListTrongKhachHang(KhachHang khachHang) {
        ArrayList<BaoHanh> listBaoHanh = khachHang.getListBaoHanh();
        ArrayList<PhieuBaoHanh> listPhieuBaoHanh = khachHang.getListPhieuBaoHanh();
        ArrayList<MaGiamGia> listMaGiamGia = khachHang.getListMaGiamGia();
        ArrayList<HoaDon> listHoaDon = khachHang.getListHoaDon();
        ArrayList<PhieuTraHang> listPhieuTraHang = khachHang.getListPhieuTraHang();

        // in từng danh sách nếu có dữ liệu
        if (!listBaoHanh.isEmpty()) {
            System.out.println("Danh sach Bao Hanh:");
            for (BaoHanh bh : listBaoHanh) {
                System.out.println(bh);
            }
        }

        if (!listPhieuBaoHanh.isEmpty()) {
            System.out.println("Danh sach Phieu Bao Hanh:");
            for (PhieuBaoHanh pbh : listPhieuBaoHanh) {
                System.out.println(pbh);
            }
        }

        if (!listMaGiamGia.isEmpty()) {
            System.out.println("Danh sach Ma Giam Gia:");
            for (MaGiamGia mg : listMaGiamGia) {
                System.out.println(mg);
            }
        }

        if (!listHoaDon.isEmpty()) {
            System.out.println("Danh sach Hoa Don:");
            for (HoaDon hd : listHoaDon) {
                System.out.println(hd);
            }
        }

        if (!listPhieuTraHang.isEmpty()) {
            System.out.println("Danh sach Phieu Tra Hang:");
            for (PhieuTraHang pth : listPhieuTraHang) {
                System.out.println(pth);
            }
        }
    }

    // tra cứu thông tin khách hàng bằng từ khóa (sdt, mã, hoặc tên)
    public void traCuuThongTinKhachHang() {
        String tuKhoa = Nhap.nhapStr("Nhap tu khoa de tim : ");
        boolean timThay = false;
        ArrayList<KhachHang> listKhachHang = db.getListKhachHang();

        // kiểm tra rỗng
        if (listKhachHang == null || listKhachHang.size() == 0) {
            System.out.println("khong tim thay khach hang");
            return;
        }
        Set<Integer> setLuaChon = new HashSet<>();
        // tìm theo số điện thoại
        for (int i = 0; i < listKhachHang.size(); i++) {
            if (listKhachHang.get(i).getSdt().contains(tuKhoa)) {
                timThay = true;
                System.out.println(i + ". " + listKhachHang.get(i).getMaKh() + " " + listKhachHang.get(i).getTenKh()
                        + " " + listKhachHang.get(i).getSdt());
                setLuaChon.add(i);
            }
        }

        // nếu tìm thấy theo sdt
        if (timThay) {
            int luaChon = Nhap.nhapInt("lua chon khach hang de hien thi : ");
            if (!setLuaChon.contains(luaChon)) {
                System.out.println("Lua chon khong nam trong pham vi");
                return;
            }
            if (listKhachHang.size() > luaChon || 0 < luaChon) {
                System.out.println("---------------------------");
                System.out.println(listKhachHang.get(luaChon));
                xuatListTrongKhachHang(listKhachHang.get(luaChon));
                System.out.println("---------------------------");
            } else {
                System.out.println("lua chon khong hop le");
            }
        } else {
            // tìm theo mã khách hàng
            for (int i = 0; i < listKhachHang.size(); i++) {
                if (listKhachHang.get(i).getMaKh().equals(tuKhoa)) {
                    System.out.println("---------------------------");
                    System.out.println(listKhachHang.get(i));
                    xuatListTrongKhachHang(listKhachHang.get(i));
                    System.out.println("---------------------------");
                    timThay = true;
                    break;
                }
            }
            // nếu vẫn chưa thấy thì tìm theo tên
            if (!timThay) {
                setLuaChon.clear();
                for (int i = 0; i < listKhachHang.size(); i++) {
                    if (listKhachHang.get(i).getTenKh().contains(tuKhoa)) {
                        timThay = true;
                        System.out.println(
                                i + ". " + listKhachHang.get(i).getMaKh() + " " + listKhachHang.get(i).getTenKh()
                                        + " " + listKhachHang.get(i).getSdt());
                        setLuaChon.add(i);
                    }
                }
                if (timThay) {
                    int luaChon = Nhap.nhapInt("lua chon khach hang de hien thi : ");
                    if (!setLuaChon.contains(luaChon)) {
                        System.out.println("Lua chon khong nam trong pham vi");
                        return;
                    }
                    if (luaChon >= 0 && luaChon < listKhachHang.size()) {
                        System.out.println("---------------------------");
                        System.out.println(listKhachHang.get(luaChon));
                        xuatListTrongKhachHang(listKhachHang.get(luaChon));
                        System.out.println("---------------------------");
                    } else {
                        System.out.println("lua chon khong hop le");
                    }
                }
            }

        }

        if (!timThay) {
            System.out.println("khong tim thay khach hang");
        }
    }

    // xóa khách hàng theo mã
    public void xoaKhachHang() {
        ArrayList<KhachHang> listKhachHang = db.getListKhachHang();
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        String ma = Nhap.nhapStr("Nhap ma khach hang can xoa : ");
        if (listKhachHang == null || listKhachHang.size() == 0) {
            System.out.println("Khong co khach hang de xoa");
            return;
        }
        if (danhSachKhachHang.xoa(ma)) {
            System.out.println("Xoa khach hang thanh cong");
        } else {
            System.out.println("Xoa khong thanh cong");
        }
    }

    // sửa từng thành phần thông tin khách hàng
    private void suaThanhPhanKhachHang(KhachHang khachHang, int luaChon) {
        switch (luaChon) {
            case 0:
                System.out.println("Thoat sua khach hang");
                break;
            case 1:
                khachHang.setTenKh(Nhap.nhapStr("Nhap ten khach hang de thay doi : "));
                System.out.println("Da thay doi");
                break;
            case 2:
                khachHang.setSdt(Nhap.nhapStr("Nhap so dien thoai de thay doi : "));
                System.out.println("Da thay doi");
                break;
            case 3:
                // đổi hạng thành viên
                DanhSachHangThanhVien danhSachHangThanhVien = db.getDanhSachHangThanhVien();
                HangThanhVien hangThanhVienMoi = danhSachHangThanhVien
                        .tim(Nhap.nhapStr("Nhap hang thanh vien moi : "));
                if (hangThanhVienMoi == null) {
                    System.out.println("Khong tim thay hang thanh vien");
                    return;
                }
                khachHang.setHangThanhVien(hangThanhVienMoi);
                System.out.println("Da thay doi");
                break;
            default:
                break;
        }
    }

    // hiển thị toàn bộ khách hàng trong danh sách
    public void xemTatCaKhachHang() {
        for (int i = 0; i < listKhachHang.size(); i++) {
            System.out.println(listKhachHang.get(i));
        }
    }

    // menu chỉnh sửa thông tin khách hàng
    private void xuatSuaKhachHang() {
        System.out.println("1. Sua ten khach hang");
        System.out.println("2. Sua so dien thoai");
        System.out.println("3. Sua hang thanh vien");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    // xử lý quy trình sửa thông tin khách hàng
    public void suaThongTinKhachHang() {
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        KhachHang khachHang = danhSachKhachHang.tim(Nhap.nhapStr("Nhap ma khach hang can sua : "));
        if (khachHang == null) {
            System.out.println("Khong tim thay khach hang");
            return;
        }
        int xacNhan = 1;
        while (xacNhan == 1) {
            XoaManHinh.xoa();
            System.out.println();
            System.out.println("------------------------");
            System.out.println(khachHang);
            System.out.println("------------------------");
            xuatSuaKhachHang();
            int luaChon = Nhap.nhapInt("Nhap lua chon : ");
            if (luaChon == 0) {
                return;
            }
            suaThanhPhanKhachHang(khachHang, luaChon);
            Nhap.pause();
        }
    }

    // hiển thị menu chính
    private void xuatMenu() {
        System.out.println("======= Quan Ly Khach Hang =======");
        System.out.println("1. Tao khach hang");
        System.out.println("2. Tra cuu thong tin khach hang");
        System.out.println("3. Xoa khach hang");
        System.out.println("4. Sua thong tin khach hang");
        System.out.println("5. Xem tat ca khach hang");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    // xử lý từng chức năng người dùng chọn
    private void thucHienChucNang(int luaChon) {
        switch (luaChon) {
            case 0:
                System.out.println("Da thoat");
                break;
            case 1:
                taoKhachHang();
                break;
            case 2:
                traCuuThongTinKhachHang();
                break;
            case 3:
                xoaKhachHang();
                break;
            case 4:
                suaThongTinKhachHang();
                break;
            case 5:
                xemTatCaKhachHang();
                break;
            default:
                break;
        }
    }

    // vòng lặp menu chính
    public void menu() {
        int xacNhan = 1;
        while (xacNhan == 1) {
            XoaManHinh.xoa();
            xuatMenu();
            int luaChon = Nhap.nhapInt("Nhap lua Chon : ");
            if (luaChon == 0) {
                return;
            }
            thucHienChucNang(luaChon);
            Nhap.pause();
        }
    }
}
