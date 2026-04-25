package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import danhsach.DanhSachBaoHanh;
import danhsach.DanhSachHangThanhVien;
import danhsach.DanhSachKhachHang;
import danhsach.DanhSachMaGiamGia;
import danhsach.DanhSachSanPham;
import danhsach.DanhSachThongTinSanPham;
import database.Database;
import model.BaoHanh;
import model.ChiTietHoaDon;
import model.HangThanhVien;
import model.HoaDon;
import model.KhachHang;
import model.MaGiamGia;
import model.NhanVien;
import model.PhieuBaoHanh;
import model.PhieuTraHang;
import model.QuanLy;
import model.SanPham;
import model.ThongTinSanPham;
import model.TaiKhoan;
import model.TinNhan;

public class TaoDoiTuong {

    // Tạo TinNhan mới với người gửi và nội dung nhập từ bàn phím
    public static TinNhan taoTinNhan(String tenNG, Database db) {
        String maNG = CapMa.capMaTinNhan(db);
        String noiDung = Nhap.nhapStr("Nhap noi dung : ");
        return new TinNhan(maNG, tenNG, noiDung, ThoiGian.layNgayHienTaiStr());
    }

    // Tạo NhanVien mới với dữ liệu nhập từ bàn phím
    public static NhanVien taoNhanVien(Database db) {
        String ma = CapMa.capMaNhanVien(db);
        String cccd = Nhap.nhapStr("Nhap cccd: ");
        String ten = Nhap.nhapStr("Nhap ten: ");
        String ngaySinh = Nhap.nhapNgay("Nhap ngay sinh (dd/MM/yyyy): ");
        String sdt = Nhap.nhapStr("Nhap so dien thoai: ");
        String gioiTinh = Nhap.nhapStr("Nhap gioi tinh: ");
        return new NhanVien(ma, cccd, ten, ngaySinh, sdt, gioiTinh);
    }

    // Tạo ThongTinSanPham mới với dữ liệu nhập từ bàn phím
    public static ThongTinSanPham taoSanPham(Database db) {
        String ma = CapMa.capMaThongTinSanPham(db);
        String ten = Nhap.nhapStr("Nhap ten san pham: ");
        String danhMuc = Nhap.nhapStr("Nhap danh muc: ");
        String thuongHieu = Nhap.nhapStr("Nhap thuong hieu: ");
        long gia = Nhap.nhapLong("Nhap gia: ");
        String moTa = Nhap.nhapStr("Nhap mo ta: ");
        int trangThai = Nhap.nhapInt("Nhap trang thai (1)Con Hang (2)Het Hang (3)Ngung Kinh Doanh): ");
        String trangThaiStr = "";
        switch (trangThai) {
            case 1:
                trangThaiStr = "Con Hang";
                break;
            case 2:
                trangThaiStr = "Het Hang";
                break;
            case 3:
                trangThaiStr = "Ngung Kinh Doanh";
                break;
            default:
                break;
        }
        return new ThongTinSanPham(ma, ten, danhMuc, thuongHieu, gia, moTa, trangThaiStr);
    }

    // Tạo PhieuTraHang mới với dữ liệu từ KhachHang, SanPham và thời gian hiện tại
    public static PhieuTraHang taoPhieuTraHang(KhachHang khachHang, SanPham sanPham, Database db) {
        String maTraHang = CapMa.capMaPhieuTraHang(db);
        String ngayTra = ThoiGian.layNgayHienTaiStr();
        String lyDoTra = Nhap.nhapStr("Nhap ly do tra : ");
        if (lyDoTra.length() == 0) {
            lyDoTra = "trong";
        }
        PhieuTraHang phieuTraHang = new PhieuTraHang(maTraHang, khachHang, sanPham, ngayTra, lyDoTra);
        return phieuTraHang;
    }

    // Tạo HangThanhVien mới với tên và mô tả nhập từ bàn phím
    public static HangThanhVien taoHangThanhVien(Database db) {
        String ten = Nhap.nhapStr("Nhap ten hang thanh vien: ");
        String moTa = Nhap.nhapStr("Nhap mo ta: ");
        return new HangThanhVien(ten, moTa);
    }

    // Tạo MaGiamGia mới, dữ liệu nhập từ bàn phím
    public static MaGiamGia taoMaGiamGia(Database db) {
        String ma = CapMa.capMaMaGiamGia(db);
        String tenMa = Nhap.nhapStr("Nhap ten ma: ");
        String loaiDoanhMuc = Nhap.nhapStr("Nhap loai danh muc (hoac nhap Tat_Ca) : ");
        String loaiThuongHieu = Nhap.nhapStr("Nhap loai thuong hieu (hoac nhap Tat_Ca) : ");
        String soTienGiam = Nhap.nhapStr("Nhap so tien giam(x) hoac % giam(x%) : ");
        String ngayBatDau = Nhap.nhapNgay("Nhap ngay bat dau (yyyy-MM-dd): ");
        String ngayKetThuc = Nhap.nhapNgay("Nhap ngay ket thuc (yyyy-MM-dd): ");

        return new MaGiamGia(ma, tenMa, loaiDoanhMuc, loaiThuongHieu, soTienGiam, ngayBatDau, ngayKetThuc);
    }

    // Tạo MaGiamGia doc quyen cho HangThanhVien cụ thể
    public static MaGiamGia taoMaGiamGiaDocQuyen(Database db, HangThanhVien hangThanhVien) {
        String ma = CapMa.capMaMaGiamGiaDocQuyen(db, hangThanhVien);
        String tenMa = Nhap.nhapStr("Nhap ten ma: ");
        String loaiDoanhMuc = Nhap.nhapStr("Nhap loai danh muc (hoac nhap Tat_Ca) : ");
        String loaiThuongHieu = Nhap.nhapStr("Nhap loai thuong hieu (hoac nhap Tat_Ca) : ");
        String soTienGiam = Nhap.nhapStr("Nhap so tien giam(x) hoac % giam(x%) : ");
        String ngayBatDau = Nhap.nhapNgay("Nhap ngay bat dau (yyyy-MM-dd): ");
        String ngayKetThuc = Nhap.nhapNgay("Nhap ngay ket thuc (yyyy-MM-dd): ");
        return new MaGiamGia(ma, tenMa, loaiDoanhMuc, loaiThuongHieu, soTienGiam, ngayBatDau, ngayKetThuc);
    }

    // Tạo KhachHang mới với dữ liệu nhập từ bàn phím, gán danh sách mã giảm giá từ
    // DB
    public static KhachHang taoKhachHang(Database db) {
        String maKh = CapMa.capMaKhachHang(db);
        String tenKh = Nhap.nhapStr("Nhap ten khach hang: ");///
        String sdt = Nhap.nhapStr("Nhap so dien thoai: ");
        KhachHang khachHang = new KhachHang(maKh, tenKh, sdt);
        for (MaGiamGia maGiamGia : db.getListMaGiamGia()) {
            khachHang.themMaGiamGia(new MaGiamGia(maGiamGia));
        }
        return khachHang;
    }

    // Tạo KhachHang mới với số điện thoại đã cho, thêm các mã giảm giá từ DB
    public static KhachHang taoKhachHang(Database db, String sdt) {
        String maKh = CapMa.capMaKhachHang(db);
        String tenKh = Nhap.nhapStr("Nhap ten khach hang: ");////
        KhachHang khachHang = new KhachHang(maKh, tenKh, sdt);
        for (MaGiamGia maGiamGia : db.getListMaGiamGia()) {
            khachHang.themMaGiamGia(new MaGiamGia(maGiamGia));
        }
        return khachHang;
    }

    // Tạo PhieuBaoHanh mới cho KhachHang và SanPham cụ thể
    public static PhieuBaoHanh taoPhieuBaoHanh(BaoHanh baoHanh, KhachHang khachHang, Database db) {
        System.out.println("Tao phieu bao hanh");
        String maPhieuBaoHanh = CapMa.capMaPhieuBaoHanh(db);
        SanPham sanPham = baoHanh.getSanPham();
        String ngayBaoHanh = ThoiGian.layNgayHienTaiStr();
        String chiTietLoi = Nhap.nhapStr("Nhap chi tiet loi : ");
        if (chiTietLoi.length() == 0) {
            chiTietLoi = "trong";
        }
        PhieuBaoHanh phieuBaoHanh = new PhieuBaoHanh(maPhieuBaoHanh, khachHang, sanPham, ngayBaoHanh, chiTietLoi);
        khachHang.themPhieuBaoHanh(phieuBaoHanh);
        return phieuBaoHanh;
    }

    // Tạo BaoHanh mới dựa trên ThongTinSanPham và dữ liệu nhập từ bàn phím
    public static BaoHanh taoBaoHanh(Database db) {
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
        String maBaoHanh = CapMa.capMaBaoHanh(db);
        int soThangBaoHanh = Nhap.nhapInt("Nhap so thang bao hanh : ");

        String maSanPham = Nhap.nhapStr("Nhap ma thong tin san pham : ");
        ThongTinSanPham thongTinSanPham = danhSachThongTinSanPham.tim(maSanPham);
        if (thongTinSanPham == null) {
            System.out.println("Khong tim thay thong tin san pham de tao bao hanh");
            return null;
        }
        SanPham sanPham = new SanPham();
        sanPham.setThongTinSanPham(thongTinSanPham);
        long gia = Nhap.nhapLong("Nhap gia cua bao hanh : ");
        String loaiBaoHanh = "BaoHanh" + soThangBaoHanh + "T";
        return new BaoHanh(maBaoHanh, loaiBaoHanh, sanPham, gia);
    }

    // Tạo danh sách ChiTietHoaDon cho 1 KhachHang
    public static ArrayList<ChiTietHoaDon> taoListChiTietHoaDon(Database db, KhachHang khachHang) {
        ArrayList<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<>();// list chứa chi tiết hóa đơn của 1 hóa đơn

        DanhSachBaoHanh danhSachBaoHanh = db.getDanhSachBaoHanh();
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
        do {
            // chon san pham
            ThongTinSanPham thongTinSanPham = null;
            int soLuong = 0;
            do {
                String ma = Nhap.nhapStr("Nhap ma thong tin san pham de tao ChiTietHoaDon hoac nhan 0 de thoat: ");
                if (ma.replaceAll("\\s+", "").equals("0")) {
                    return listChiTietHoaDon;
                }
                thongTinSanPham = danhSachThongTinSanPham.tim(ma);
                if (thongTinSanPham == null) {
                    System.out.println("Khong ton tai thong tin cua san pham nay, vui long nhap lai");
                    continue;
                }
                soLuong = Nhap.nhapInt("Nhap so luong san pham nay can them vao: ");
                if (thongTinSanPham.getTonKho() == 0) {
                    System.out.println("So luong san pham trong kho khong du");
                    continue;//////////
                }
            } while (thongTinSanPham == null);

            // Tạo ChiTietHoaDon và thêm từng sản phẩm với bảo hành nếu có
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(CapMa.capMaChiTietHoaDon(db));
            Set<String> set = new HashSet<>();
            db.getDanhSachChiTietHoaDon().them(chiTietHoaDon);
            do { /////////////// nhập sao cho đủ số lượng thì thôi

                String maSr = Nhap.nhapStr("Nhap ma serial san pham cua " +
                        thongTinSanPham.getMa() + " : ");
                SanPham sanPham = danhSachSanPham.timSanPhamChuaBan(maSr);

                if (sanPham == null) {
                    System.out.println("Khong co san pham voi serial " + maSr + " trong kho san pham kha dung");
                    continue;
                }

                if (!sanPham.getMa().equals(thongTinSanPham.getMa())) {
                    System.out.println("Da khong phai la san pham cua " +
                            thongTinSanPham.getMa());
                    continue;
                }
                if (set.contains(maSr)) {
                    System.out.println("Ban da them san pham nay roi");
                    continue;
                }

                if (sanPham.getTrangThai().equals("Ngung Kinh Doanh")) {
                    System.out.println("San pham da ngung kinh doanh");
                    continue;
                }
                if (sanPham.getTrangThai().equals("Het Hang")) {
                    System.out.println("San pham da het hang");
                    continue;
                }

                // Giảm số lượng tồn kho, đánh dấu sản phẩm đã bán, thêm bảo hành nếu có
                thongTinSanPham.giamTonKho();
                soLuong--;
                System.out
                        .println("Da them san pham : " + sanPham.getTen() + " voi serial : " +
                                sanPham.getSerial()
                                + " vao hoa don");
                sanPham.setDaBan(true);
                chiTietHoaDon.themSanPham(sanPham);
                set.add(maSr);
                ArrayList<BaoHanh> listBaoHanh = danhSachBaoHanh.timBaoHanh(sanPham); // check danh sách bảo hành sản
                                                                                      // phẩm
                if (listBaoHanh.size() == 0) {// ko có bảo hành
                    continue;
                }

                int luaChon = Nhap.nhapInt("Them bao hanh cho san pham nay khong? (1)Co (soKhac)Khong: ");
                if (luaChon != 1) {// ko them
                    continue;
                }

                for (int j = 0; j < listBaoHanh.size(); j++) {
                    BaoHanh baoHanh = listBaoHanh.get(j);
                    System.out.println(j + ". " + baoHanh.getLoaiBaoHanh());
                }

                luaChon = Nhap.nhapInt("Chon bao hanh : ");

                if (luaChon < 0 || luaChon >= listBaoHanh.size()) {
                    System.out.println("Lua chon khong hop le");
                } else {
                    BaoHanh baoHanh = new BaoHanh(listBaoHanh.get(luaChon));
                    baoHanh.setNgayBatDau(ThoiGian.layNgayHienTaiStr());
                    baoHanh.setNgayKetThuc();
                    baoHanh.setSanPham(sanPham);

                    sanPham.setBaoHanh(baoHanh);
                    khachHang.themBaoHanh(baoHanh);
                    chiTietHoaDon.setSoBh(chiTietHoaDon.getSoBh() + 1);
                }
            } while (soLuong > 0);

            listChiTietHoaDon.add(chiTietHoaDon);
        } while (true);

    }

    // Tạo HoaDon mới cho KhachHang, bao gồm các ChiTietHoaDon, tính thành tiền và
    // áp dụng mã giảm giá
    public static HoaDon taoHoaDon(Database db) {
        String ma = CapMa.capMaHoaDon(db);
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        String sdt = Nhap.nhapStr("Nhap so dien thoai khach hang : ");
        KhachHang khachHang = danhSachKhachHang.timKhachHangTheoSdt(sdt);
        if (khachHang == null) {
            System.out.println("Khach hang khong ton tai");
            int chon = Nhap.nhapInt("Ban co muon tao moi khach hang khong? (1)Co (khac)khong : ");
            if (chon != 1) {
                return null;
            }
            System.out.println("Tao moi khach hang");
            khachHang = taoKhachHang(db, sdt);
            danhSachKhachHang.them(khachHang);
        }

        ArrayList<ChiTietHoaDon> listChiTietHoaDon = taoListChiTietHoaDon(db, khachHang);
        if (listChiTietHoaDon.size() == 0) {
            return null;
        }

        String ngayTaoHoaDon = ThoiGian.layNgayHienTaiStr();
        String ghiChu = Nhap.nhapStr("Nhap ghi chu neu co : ");
        if (ghiChu.length() == 0) {
            ghiChu = "trong";
        }
        HoaDon hoaDon = new HoaDon(ma, khachHang, ngayTaoHoaDon, ghiChu);
        hoaDon.setListChiTietHoaDon(listChiTietHoaDon);

        if (hoaDon != null) {
            // đưa danh sách mã giảm giá của khách hàng để xử lí
            DanhSachMaGiamGia danhSachMaGiamGia = new DanhSachMaGiamGia(khachHang.getListMaGiamGia());
            danhSachMaGiamGia.setThanhTienDaApMaGG(hoaDon); // tính thành tiền tổng cho hóa đơn, và áp dụng mã giảm giá
                                                            // nếu có
        }

        // xử lí đưa hoadon cho khách hàng
        khachHang.themHoaDon(hoaDon);
        khachHang.tangTienDaChi(hoaDon.getThanhTien());
        ////// thêm hạng thành viên mới (nếu có) cho khách hàng
        DanhSachHangThanhVien danhSachHangThanhVien = db.getDanhSachHangThanhVien();
        danhSachHangThanhVien.setHangThanhVienChoKhachHang(khachHang);

        // hiển thị các mã giảm giá đã áp dụng
        System.out.println("Danh sach ma giam gia da dung :");
        boolean timThay = false;

        for (ChiTietHoaDon chiTietHoaDon : hoaDon.getListChiTietHoaDon()) {
            for (MaGiamGia maGiamGia : chiTietHoaDon.getListMaGiamGia()) {
                System.out.println(maGiamGia);
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong co ma giam gia duoc ap dung trong hoa don nay");
        }
        return hoaDon;
    }

    // Tạo TaiKhoan mới với tên đăng nhập và mật khẩu nhập từ bàn phím
    public static TaiKhoan taoTaiKhoan() {
        String tenDangNhap = Nhap.nhapStr("Nhap ten dang nhap : ");
        String matKhau = Nhap.nhapStr("Nhap mat khau : ");
        return new TaiKhoan(tenDangNhap, matKhau);
    }

    // Tạo QuanLy mới với dữ liệu nhập từ bàn phím
    public static QuanLy TaoDoiTuongQuanLy(Database db) {
        String ma = CapMa.capMaQuanLy(db);
        String cccd = Nhap.nhapStr("Nhap cccd: ");
        String ten = Nhap.nhapStr("Nhap ten: ");
        String ngaySinh = Nhap.nhapNgay("Nhap ngay sinh (dd/MM/yyyy): ");
        String sdt = Nhap.nhapStr("Nhap so dien thoai: ");
        String gioiTinh = Nhap.nhapStr("Nhap gioi tinh: ");
        return new QuanLy(ma, cccd, ten, ngaySinh, sdt, gioiTinh);
    }
}
