package quanli;

import java.util.ArrayList;

import danhsach.DanhSachBaoHanh;
import danhsach.DanhSachChiTietHoaDon;
import danhsach.DanhSachHoaDon;
import danhsach.DanhSachKhachHang;
import danhsach.DanhSachMaGiamGia;
import danhsach.DanhSachSanPham;
import database.Database;
import model.BaoHanh;
import model.ChiTietHoaDon;
import model.HoaDon;
import model.KhachHang;
import model.MaGiamGia;
import model.SanPham;
import util.Nhap;
import util.TaoDoiTuong;
import util.ThoiGian;
import util.XoaManHinh;

public class QuanLyHoaDon {
    private Database db;

    public QuanLyHoaDon(Database db) {
        this.db = db;
    }

    // tạo hóa đơn
    public void taoHoaDon() {
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        HoaDon hoaDon = TaoDoiTuong.taoHoaDon(db);
        if (danhSachHoaDon.them(hoaDon)) {
            System.out.println();
            System.out.println("====Hoa don vua tao====");
            System.out.println();
            System.out.println(hoaDon);
            System.out.println("Tao hoa don thanh cong");
        } else {
            System.out.println("Tao hoa don that bai");
        }
    }

    // 2. xóa hóa đơn
    public void xoaHoaDon() {
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        String ma = Nhap.nhapStr("nhap ma hoa don can xoa: ");
        if (danhSachHoaDon.xoa(ma)) {
            System.out.println("xoa hoa don thanh cong!");
        } else {
            System.out.println("xoa hoa don that bai!");
        }
    }

    /// ================== sửa hóa đơn ============================= ///

    /// xuất các lựa chọn sửa của đói tượng sanPham trong hóa đơn
    private void xuatSuaHoaDon_SanPham() {
        System.out.println("1. Sua san pham");
        System.out.println("2. Xoa san pham");
        System.out.println("3. Sua bao hanh");
        System.out.println("4. Xoa bao hanh");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    // hàm thực thi sửa đối tượng sản phẩm dựa vào lựa chọn
    private boolean suaThanhPhanSanPham(SanPham sanPham, HoaDon hoaDon, int chon) {
        DanhSachChiTietHoaDon danhSachChiTietHoaDon = new DanhSachChiTietHoaDon(hoaDon.getListChiTietHoaDon());
        switch (chon) {
            case 1:
                DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
                ChiTietHoaDon chiTietHoaDon = danhSachChiTietHoaDon.tim(sanPham);///// tìm chi tiết hóa đơn thuộc về
                                                                                 /// sản phẩm
                // sản phẩm mới muốn thêm vào
                SanPham newSanPham = danhSachSanPham.tim(Nhap.nhapStr("Nhap ma serial san pham can them vao : "));

                if (newSanPham == null) {
                    System.out.println("Khong tim thay san pham de them vao");
                } else {
                    DanhSachMaGiamGia danhSachMaGiamGia = new DanhSachMaGiamGia(
                            hoaDon.getKhachHang().getListMaGiamGia()); ////////// tạo đối tượng danh sách mã giảm giá là
                                                                       /// danh sách mã GG của khách hàng

                    /// xóa 1 sản phẩm cụ thể trong một hóa đơn , và thu hồi mã giảm giá tương ứng
                    /// cho khách hàng (nếu còn hạng sử dung)
                    ArrayList<MaGiamGia> listMaGiamGia = danhSachMaGiamGia.xoaSanPhamThuHoiMa(sanPham, hoaDon);
                    if (listMaGiamGia != null) {
                        for (MaGiamGia maGiamGia : listMaGiamGia) { // liệt kê các mã đã thu hồi
                            System.out.println("Da thu hoi ma giam gia " + maGiamGia.getMa() + " cho khach hang "
                                    + hoaDon.getKhachHang().getSdt());
                        }
                    }
                    //// cập nhập giá hóa đơn sau khi thêm sản phẩm mới vào
                    chiTietHoaDon.setThanhTien(chiTietHoaDon.getThanhTien() + newSanPham.getGia());
                    chiTietHoaDon.themSanPham(newSanPham);// thêm sản phẩm
                    System.out.println("Da thay doi san pham");
                    return true;
                }
                break;
            case 2:
                /// xóa 1 sản phẩm cụ thể trong một hóa đơn , và thu hồi mã giảm giá tương ứng
                /// cho khách hàng (nếu còn hạng sử dung)
                DanhSachMaGiamGia danhSachMaGiamGia = new DanhSachMaGiamGia(hoaDon.getKhachHang().getListMaGiamGia());
                ArrayList<MaGiamGia> listMaGiamGia = danhSachMaGiamGia.xoaSanPhamThuHoiMa(sanPham, hoaDon);
                if (listMaGiamGia != null) {
                    for (MaGiamGia maGiamGia : listMaGiamGia) {
                        System.out.println("Da thu hoi ma giam gia " + maGiamGia.getMa() + " cho khach hang "
                                + hoaDon.getKhachHang().getSdt());
                    }
                }

                System.out.println("Da xoa san pham");
                return true;
            case 3:
                DanhSachBaoHanh danhSachBaoHanh = db.getDanhSachBaoHanh(); // lấy bảo hành gốc trong database
                ArrayList<BaoHanh> listBaoHanh = danhSachBaoHanh.timBaoHanh(sanPham); /// trả về danh sách bảo hành của
                                                                                      /// riêng sản phẩm
                if (listBaoHanh.size() == 0) {
                    System.out.println("San pham nay khong co bao hanh nao");
                    return false; // không có bảo hành không thay đôi
                }
                for (int i = 0; i < listBaoHanh.size(); i++) {
                    System.out.println(
                            i + " " + listBaoHanh.get(i).getMaBh() + " " + listBaoHanh.get(i).getLoaiBaoHanh());
                }
                int luaChon = Nhap.nhapInt("Chon bao hanh de thay doi : ");
                BaoHanh baoHanh = null;
                if (luaChon >= 0 && luaChon < listBaoHanh.size()) {
                    baoHanh = listBaoHanh.get(luaChon); /// lựa chọn bảo hành cần thêm
                }
                chiTietHoaDon = danhSachChiTietHoaDon.tim(sanPham);
                if (sanPham.getBaoHanh() != null) { // nếu sản phẩm đã có bảo hành thì chitiethoadon giảm = với giá bảo
                                                    // hành cũ
                    chiTietHoaDon.setThanhTien(chiTietHoaDon.getThanhTien() - sanPham.getBaoHanh().getGia());
                }
                BaoHanh baoHanhMoi = new BaoHanh(baoHanh); // do baoHanh là tham chiếu từ dữ liệu gốc (chỉ dùng để lưu
                                                           // trử) nên tạo cái mới cho khách hàng
                baoHanhMoi.setSanPham(sanPham); // set không ảnh hưởng tới dữ liệu gốc
                sanPham.setBaoHanh(baoHanhMoi);
                chiTietHoaDon.setThanhTien(chiTietHoaDon.getThanhTien() + baoHanh.getGia());
                System.out.println("Da thay doi bao hanh");

                break;
            case 4:
                // gở bảo hành nếu có
                if (sanPham.getBaoHanh() == null) {
                    System.out.println("Khong the xoa vi khong co bao hanh");
                } else {
                    chiTietHoaDon = danhSachChiTietHoaDon.tim(sanPham);
                    chiTietHoaDon.setThanhTien(chiTietHoaDon.getThanhTien() - sanPham.getBaoHanh().getGia());
                    sanPham.setBaoHanh(null);
                    System.out.println("Da xoa bao hanh");
                }
                break;
            default:
                break;
        }
        return false;
    }

    private void suaSanPham(SanPham sanPham, HoaDon hoaDon) {
        while (true) {
            XoaManHinh.xoa();
            System.out.println("------------------------");
            System.out.println(sanPham);
            System.out.println("------------------------");
            xuatSuaHoaDon_SanPham();
            int chon = Nhap.nhapInt("Nhap lua chon : ");
            if (chon == 0) {
                return;
            }
            // nếu đánh dấu là true nghĩa là cần thoát khỏi hàm này (vì sản phẩm đã bị xóa)
            if (suaThanhPhanSanPham(sanPham, hoaDon, chon)) {
                hoaDon.tinhThanhTien();
                return;
            }
            hoaDon.tinhThanhTien();
            Nhap.pause();
        }
    }

    private void suaChiTietHoaDon(HoaDon hoaDon) {
        for (int i = 0; i < hoaDon.getListChiTietHoaDon().size(); i++) { // đuyệt từng chitiethoadom trong hóa đơn
            ChiTietHoaDon chiTietHoaDon = hoaDon.getListChiTietHoaDon().get(i);
            // liệt kê để lựa chọn
            System.out.print(i + ". " + chiTietHoaDon.getMa());
            String maThongTin = chiTietHoaDon.getListSanPham().size() > 0
                    ? hoaDon.getListChiTietHoaDon().get(i).getListSanPham().get(0).getMa()
                    : "null";
            System.out.print("   " + maThongTin + " soSanPham : " + chiTietHoaDon.getSoSp()
                    + " |    soBaoHanh : " + chiTietHoaDon.getSoBh());
            System.out.println();
        }
        int chon = Nhap.nhapInt("Chon ChiTietHoaDon can quan li : ");
        ChiTietHoaDon chiTietHoaDon = null;
        if (chon >= 0 && chon < hoaDon.getListChiTietHoaDon().size()) {
            chiTietHoaDon = hoaDon.getListChiTietHoaDon().get(chon); // đã chọn chitiethoadon này quản lí
        } else {
            System.out.println("Lua chon khong lop le");
            return;
        }

        while (true) {
            if (chiTietHoaDon.getListSanPham().size() == 0) {
                System.out.println("danh sach san pham trong");
                return;
            }
            for (int i = 0; i < chiTietHoaDon.getListSanPham().size(); i++) {
                System.out.println(i + ". " + chiTietHoaDon.getListSanPham().get(i).getTen()
                        + " voi ma serial :  "
                        + chiTietHoaDon.getListSanPham().get(i).getSerial()
                        + (chiTietHoaDon.getListSanPham().get(i).getTraHang() ? "(Da tra hang)" : "")); // nếu sản phẩm
                                                                                                        // bị trả hàng
                                                                                                        // thì thông báo
            }

            chon = Nhap.nhapInt("Chon san pham de quan li hoac nhan -1 de thoat : ");
            if (chon == -1) {
                return;
            }

            SanPham sanPham = null;
            if (chon >= 0 && chon < chiTietHoaDon.getListSanPham().size()) {
                sanPham = chiTietHoaDon.getListSanPham().get(chon);
            } else {
                System.out.println("Lua chon khong hop le");
                return;
            }
            suaSanPham(sanPham, hoaDon); // gọi menu chức năng sửa sản phẩm
            Nhap.pause();
        }

    }

    private void xuatSuaHoaDon() {
        System.out.println("1. Sua Khach hang");
        System.out.println("2. Sua ngay tao hoa don");
        System.out.println("3. Sua ghi chu");
        System.out.println("4. Sua ChiTietHoaDon");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    private void suaThanhPhanHoaDon(HoaDon hoaDon, int luaChon) {
        switch (luaChon) {
            case 1:
                DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
                KhachHang khachHang = danhSachKhachHang.tim(Nhap.nhapStr("Nhap ma khach hang moi : "));
                if (khachHang == null) {
                    System.out.println("Khong tim thay khach hang");
                    return;
                }
                hoaDon.setKhachHang(khachHang);
                System.out.println("Da thay doi khach hang");
                break;
            case 2:
                hoaDon.setNgayTaoHoaDon(Nhap.nhapNgay("Nhap ngay tao hoa don moi : "));
                break;
            case 3:
                hoaDon.setGhiChu(Nhap.nhapStr("Nhap ghi chu moi : "));
                break;
            case 4:
                suaChiTietHoaDon(hoaDon);
                break;
            default:
                break;
        }
    }

    public void suaHoaDon() {
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        HoaDon hoaDon = danhSachHoaDon.tim(Nhap.nhapStr("Nhap hoa don can sua : ")); /// hóa đơn cần sửa (tìm
                                                                                     /// trong database)
        if (hoaDon == null) {
            System.out.println("khong tim thay hoa don");
            return;
        }
        int xacNhan = 1;
        while (xacNhan == 1) {
            XoaManHinh.xoa();
            System.out.println();
            System.out.println("------------------------");
            System.out.println(hoaDon);
            System.out.println("------------------------");
            xuatSuaHoaDon();
            int luaChon = Nhap.nhapInt("Nhap lua chon : ");
            if (luaChon == 0) {
                return;
            }
            suaThanhPhanHoaDon(hoaDon, luaChon); // gọi hàm thực thi sửa hóa đơn
            Nhap.pause();
        }
    }

    // lựa chon xem doanh thu
    private void xuatXemDoanhThu() {
        System.out.println("======= Quan Ly Doanh Thu =======");
        System.out.println("1. xem doanh thu hom nay");
        System.out.println("2. xem doanh thu tuan");
        System.out.println("3. xem doanh thu thang");
        System.out.println("0. Thoat");
        System.out.println("---------------------------");
    }

    // xem doanh thu hôm nay
    private void xemDoanhthuHomNay() {
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        String ngayHomNay = ThoiGian.layNgayHienTaiStr();
        long tong = danhSachHoaDon.tinhDoanhThuTrongNgay(ngayHomNay);
        System.out.println("Doanh thu ngay hom nay: " + tong);
        System.out.println("So hoa don ban duoc : " + danhSachHoaDon.soHoaDonTrongNgay());
    }

    // xem doanh thu của tuần
    private void xemDoanhThuTuan() {
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        String ngayDauTuan = ThoiGian.layNgayDauTuanStr();
        String ngayCuoiTuan = ThoiGian.layNgayCuoiTuanStr();
        long tongTien = danhSachHoaDon.tinhDoanhThuTrongKhoan(ngayDauTuan, ngayCuoiTuan);
        System.out.println("Doanh thu trong tuan: " + tongTien);
        System.out.println("So hoa don ban duoc : " + danhSachHoaDon.soHoaDonTrongKhoan(ngayDauTuan, ngayCuoiTuan));
    }

    // xem doanh thu của tháng
    public void xemDoanhThuThang() {
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        String ngayDauThang = ThoiGian.layNgayDauThangStr();
        String ngayCuoiThang = ThoiGian.layNgayCuoiThangStr();
        long tongTien = danhSachHoaDon.tinhDoanhThuTrongKhoan(ngayDauThang, ngayCuoiThang);
        System.out.println("Doanh thu trong thang : " + tongTien);
        System.out.println("So hoa don ban duoc : " + danhSachHoaDon.soHoaDonTrongKhoan(ngayDauThang, ngayCuoiThang));
    }

    // hàm thực thi chức năng xem doanh thu
    private void thucHienChucNangDoanhThu(int choice) {
        switch (choice) {
            case 0 -> System.out.println("Da thoat xem doanh thu");
            case 1 -> xemDoanhthuHomNay();
            case 2 -> xemDoanhThuTuan();
            case 3 -> xemDoanhThuThang();
            default -> System.out.println("Lua chon khong hop le");
        }
    }

    // nhập lựa chọn chức năng xem doanh thu
    public void xemDoanhThu() {
        int xacNhan = 1;
        while (xacNhan == 1) {
            XoaManHinh.xoa();
            xuatXemDoanhThu();
            int choice = Nhap.nhapInt("nhap lua chon: ");
            if (choice == 0) {
                return;
            }
            thucHienChucNangDoanhThu(choice);
            Nhap.pause();
        }
    }

    // 6.tra cứu hóa đơn mua hàng
    public void traCuuHoaDonMuaHang() {
        String ma = Nhap.nhapStr("nhap ma hoa don muon tra cuu: ");
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        HoaDon hoaDon = danhSachHoaDon.tim(ma);
        if (hoaDon == null) {
            System.out.println("khong tim thay hoa don muon tra cuu!");
            return;
        }
        System.out.println("---------------------------");
        System.out.println(hoaDon);
        System.out.println("Danh sach san pham va bao hanh");
        for (int i = 0; i < hoaDon.getListChiTietHoaDon().size(); i++) {
            ChiTietHoaDon chiTietHoaDon = hoaDon.getListChiTietHoaDon().get(i);
            System.out.println("======================================");
            System.out.println("Chi tiet hoa don : " + chiTietHoaDon.getMa());
            System.out.println("-------------------------------------------");
            for (SanPham sanPham : chiTietHoaDon.getListSanPham()) {
                System.out.println(sanPham);
                if (sanPham.getBaoHanh() != null) {
                    System.out.println(sanPham.getBaoHanh());
                }
                System.out.println("--------------------------------");
            }
            System.out.println("=======================================");
        }
        // xuất mã giảm giá đã dùng
        System.out.println();
        System.out.println("Danh sach ma giam gia da ap dung :");
        boolean trong = true;
        for (ChiTietHoaDon chiTietHoaDon : hoaDon.getListChiTietHoaDon()) {
            for (MaGiamGia maGiamGia : chiTietHoaDon.getListMaGiamGia()) {
                System.out.println(maGiamGia);
                trong = false;
            }
        }
        if (trong) {
            System.out.println("Khong co ma giam gia da ap dung");
        }

    }

    // xem thông tin cơ bản của tất cả hóa đơn
    public void xemTatCaHoaDon() {
        ArrayList<HoaDon> listHoaDon = db.getListHoaDon();
        if (listHoaDon == null) {
            System.out.println("khong co hoa don de in!");
        }
        for (int i = 0; i < listHoaDon.size(); i++) {
            System.out.println("---------------------------");
            System.out.println(listHoaDon.get(i));
        }
    }

    // lựa chon quản lí hóa đơn
    private void xuatMenu() {
        System.out.println("======= Quan Ly Hoa Don =======");
        System.out.println("1. Tao hoa don mua hang cho khach");
        System.out.println("2. Xoa hoa don mua Hang");
        System.out.println("3. Sua hoa don mua hang theo ma");
        System.out.println("4. Xem doanh thu");
        System.out.println("5. Tra cuu hoa don mua hang");
        System.out.println("6. Xem tat ca hoa don mua hang");
        System.out.println("0. Thoat");
        System.out.println("-----------------------------------");
    }

    // hàm thực thi chức năng
    private void thucHienChucNang(int luaChon) {
        switch (luaChon) {
            case 0:
                System.out.println("da thoat");
                break;
            case 1:
                taoHoaDon();
                break;
            case 2:
                xoaHoaDon();
                break;
            case 3:
                suaHoaDon();
                break;
            case 4:
                xemDoanhThu();
                break;
            case 5:
                traCuuHoaDonMuaHang();
                break;
            case 6:
                xemTatCaHoaDon();
                break;
            default:
                System.out.println("Lua chon khong hop le");
                break;
        }
    }

    /// menu quản lí
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