package danhsach;

import java.util.ArrayList;

import interfaces.QuanLyDanhSach;
import model.BaoHanh;
import model.ChiTietHoaDon;
import model.HoaDon;
import model.KhachHang;
import model.MaGiamGia;
import model.SanPham;
import util.ThoiGian;

public class DanhSachMaGiamGia implements QuanLyDanhSach<MaGiamGia> {
    ArrayList<MaGiamGia> listMaGiamGia; // Danh sách các mã giảm giá
    private int soLuong = 0; // Số lượng mã giảm giá hiện có

    // Constructor có tham số
    public DanhSachMaGiamGia(ArrayList<MaGiamGia> listMaGiamGia) {
        this.listMaGiamGia = listMaGiamGia;
    }

    // Constructor mặc định
    public DanhSachMaGiamGia() {

    }

    // Getter - trả về danh sách mã giảm giá
    public ArrayList<MaGiamGia> getListMaGiamGia() {
        return listMaGiamGia;
    }

    // Getter - trả về số lượng mã
    public int getSoLuong() {
        return soLuong;
    }

    // Setter - gán danh sách mã giảm giá
    public void setListMaGiamGia(ArrayList<MaGiamGia> listMaGiamGia) {
        this.listMaGiamGia = listMaGiamGia;
    }

    // Setter - gán số lượng
    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // ==================== CÁC HÀM XỬ LÝ DANH SÁCH ====================

    // Thêm mã giảm giá mới
    public boolean them(MaGiamGia maGiamGia) {
        if (maGiamGia == null) {
            return false; // Không thêm nếu mã null
        }
        if (tim(maGiamGia.getMa()) != null) {
            return false; // Không thêm nếu mã đã tồn tại
        }
        soLuong++;
        return listMaGiamGia.add(maGiamGia);
    }

    // Xóa mã giảm giá theo đối tượng
    public boolean xoa(MaGiamGia ma) {
        if (ma == null) {
            return false;
        }
        soLuong--;
        return listMaGiamGia.remove(ma);
    }

    // Tìm mã giảm giá theo mã
    public MaGiamGia tim(String ma) {
        if (listMaGiamGia == null) {
            return null;
        }
        for (int i = 0; i < listMaGiamGia.size(); i++) {
            if (listMaGiamGia.get(i).getMa().equals(ma)) {
                return listMaGiamGia.get(i);
            }
        }
        return null;
    }

    // ==================== KIỂM TRA MÃ GIẢM GIÁ ====================

    // Kiểm tra xem mã giảm giá có cùng danh mục với sản phẩm không
    private boolean trungDanhMuc(MaGiamGia maGiamGia, SanPham sanPham) {
        return maGiamGia.getLoaiDoanhMuc().equals(sanPham.getDanhMuc());
    }

    // Kiểm tra xem mã giảm giá có cùng thương hiệu với sản phẩm không
    private boolean trungThuongHieu(MaGiamGia maGiamGia, SanPham sanPham) {
        return maGiamGia.getLoaiThuongHieu().equals(sanPham.getThuongHieu());
    }

    // Kiểm tra xem mã giảm giá có thể áp dụng cho sản phẩm không
    public boolean maGiamGiaThoaMan(SanPham sanPham, MaGiamGia maGiamGia) {
        if ((trungDanhMuc(maGiamGia, sanPham) || maGiamGia.getLoaiDoanhMuc().equals("Tat_Ca"))
                && (trungThuongHieu(maGiamGia, sanPham)
                        || maGiamGia.getLoaiThuongHieu().equals("Tat_Ca"))
                && ((maGiamGia.getNgayBatDau().equals("Vinh_Vien") || maGiamGia.getNgayKetThuc().equals("Vinh_Vien") ||
                        ThoiGian.ngayTrongKhoan(ThoiGian.layNgayHienTaiStr(), maGiamGia.getNgayBatDau(),
                                maGiamGia.getNgayKetThuc())))
                /// nếu đây là một giảm giá đã dùng (sanphamDadung!=null) thì check xem nó có
                /// phải của sản phẩm đó không
                && (maGiamGia.getSanPhamDaDung() == null || maGiamGia.getSanPhamDaDung().equals(sanPham))) {
            return true;
        }
        return false;
    }

    // ==================== ÁP DỤNG MÃ GIẢM GIÁ ====================

    // Tính số tiền được giảm cho sản phẩm nếu áp dụng mã
    private long appDungMaGiamGia(SanPham sanPham, MaGiamGia maGiamGia) {
        if (maGiamGiaThoaMan(sanPham, maGiamGia)) {
            String tienGiam = maGiamGia.getTienGiam();
            if (tienGiam != null && tienGiam.length() >= 2 && tienGiam.charAt(tienGiam.length() - 1) == '%') {
                // Nếu là giảm theo phần trăm
                Long phanTram = Long.parseLong(tienGiam.substring(0, tienGiam.length() - 1));
                return (sanPham.getGia() * phanTram) / 100;
            } else {
                // Nếu là giảm theo số tiền cụ thể
                return Long.parseLong(tienGiam);
            }
        }
        return 0;
    }

    // Tính và cập nhật thành tiền đã áp dụng mã giảm giá cho hóa đơn
    public void setThanhTienDaApMaGG(HoaDon hoaDon) {
        for (ChiTietHoaDon chiTietHoaDon : hoaDon.getListChiTietHoaDon()) {
            long thanhTien = 0;
            // Duyệt từng sản phẩm trong chi tiết hóa đơn
            for (int i = 0; i < chiTietHoaDon.getListSanPham().size(); i++) {
                SanPham sanPham = chiTietHoaDon.getListSanPham().get(i);
                long giaSanPham = sanPham.getGia();

                ArrayList<MaGiamGia> maGiamGiaSp = listMaGiamGiaChoSp(sanPham); // Lấy các mã hợp lệ cho sản phẩm
                for (int k = maGiamGiaSp.size() - 1; k >= 0; k--) {
                    long giaGiam = appDungMaGiamGia(sanPham, maGiamGiaSp.get(k));
                    if (giaGiam > 0) {
                        // Nếu có giảm giá -> áp dụng, đánh dấu mã đã dùng và trừ giá
                        maGiamGiaSp.get(k).setSanPhamDaDung(sanPham);
                        chiTietHoaDon.themMaGiamGia(maGiamGiaSp.get(k));
                        giaSanPham -= giaGiam;
                        xoa(maGiamGiaSp.get(k)); // Xóa mã khỏi kho
                    }
                    if (giaSanPham <= 0) {
                        giaSanPham = 0;
                        break;
                    }
                }
                thanhTien += giaSanPham;
                // Nếu sản phẩm có bảo hành thì cộng thêm giá bảo hành
                BaoHanh baoHanh = sanPham.getBaoHanh();
                if (baoHanh != null) {
                    thanhTien += baoHanh.getGia();
                }
            }
            chiTietHoaDon.setThanhTien(thanhTien); // Lưu tổng tiền vào chi tiết hóa đơn
        }
        hoaDon.tinhThanhTien(); // Cập nhật lại tổng hóa đơn
    }

    // Lấy danh sách các mã giảm giá có thể áp dụng cho sản phẩm
    public ArrayList<MaGiamGia> listMaGiamGiaChoSp(SanPham sanPham) {
        ArrayList<MaGiamGia> listMaGiamGiaTM = new ArrayList<>();
        for (int i = 0; i < listMaGiamGia.size(); i++) {
            if (maGiamGiaThoaMan(sanPham, listMaGiamGia.get(i))) {
                listMaGiamGiaTM.add(listMaGiamGia.get(i));
            }
        }
        return listMaGiamGiaTM;
    }

    // ==================== MÃ GIẢM GIÁ ĐỘC QUYỀN ====================

    // Kiểm tra xem mã có thuộc nhóm độc quyền không (đồng, bạc, vàng)
    private boolean maDocQuyen(String ma) {
        String maDong = "MGGDONG";
        String maBac = "MGGBAC";
        String maVang = "MGGVANG";
        return ma.startsWith(maDong) || ma.startsWith(maBac) || ma.startsWith(maVang);
    }

    // Xác định mã có phải mã độc quyền không
    public boolean laMaGiamGiaDocQuyen(MaGiamGia maGiamGia) {
        return maDocQuyen(maGiamGia.getMa());
    }

    // ==================== TÍNH TOÁN GIÁ SAU KHI ÁP DỤNG ====================

    // Tính giá sản phẩm sau khi áp dụng tất cả mã giảm giá có thể
    public long giaSanPhamSauKhiApDungTatCa(SanPham sanPham) {
        long gia = sanPham.getGia();
        for (MaGiamGia maGiamGia : listMaGiamGia) {
            long giaGiam = appDungMaGiamGia(sanPham, maGiamGia);
            if (giaGiam > 0) {
                gia -= giaGiam;
            }
        }
        if (gia < 0)
            gia = 0;
        return gia;
    }

    // ==================== THU HỒI MÃ KHI XÓA SẢN PHẨM ====================

    // Khi xóa sản phẩm khỏi hóa đơn, thu hồi lại các mã giảm giá đã áp dụng
    public ArrayList<MaGiamGia> xoaSanPhamThuHoiMa(SanPham sanPham, HoaDon hoaDon) {
        KhachHang khachHang = hoaDon.getKhachHang();
        if (sanPham == null) {
            return null;
        }

        // Tìm chi tiết hóa đơn chứa sản phẩm cần xóa
        DanhSachChiTietHoaDon danhSachChiTietHoaDon = new DanhSachChiTietHoaDon(hoaDon.getListChiTietHoaDon());
        ChiTietHoaDon chiTietHoaDon = danhSachChiTietHoaDon.tim(sanPham);
        if (sanPham.getTraHang()) {
            chiTietHoaDon.xoaSanPham(sanPham);
            return null;
        }
        ArrayList<MaGiamGia> listMaThuHoi = new ArrayList<>();
        ArrayList<MaGiamGia> listMaGiamGiaSp = chiTietHoaDon.getListMaGiamGia();

        // Tính lại thành tiền sau khi thu hồi mã
        DanhSachMaGiamGia danhSachMaGiamGia = new DanhSachMaGiamGia(listMaGiamGiaSp);
        long thanhTien = danhSachMaGiamGia.giaSanPhamSauKhiApDungTatCa(sanPham);
        if (sanPham.getBaoHanh() != null) {
            thanhTien += sanPham.getBaoHanh().getGia();
        }
        chiTietHoaDon.setThanhTien(chiTietHoaDon.getThanhTien() - thanhTien);
        hoaDon.tinhThanhTien();

        // Xóa sản phẩm khỏi chi tiết hóa đơn
        chiTietHoaDon.xoaSanPham(sanPham);

        if (listMaGiamGia == null) {
            return null;
        }

        // Thu hồi mã đã dùng về cho khách hàng
        for (int i = listMaGiamGiaSp.size() - 1; i >= 0; i--) {
            if (maGiamGiaThoaMan(sanPham, listMaGiamGiaSp.get(i))) {
                khachHang.themMaGiamGia(new MaGiamGia(listMaGiamGiaSp.get(i))); // Hoàn lại mã cho KH
                listMaThuHoi.add(listMaGiamGiaSp.get(i)); // Lưu danh sách mã thu hồi
                listMaGiamGiaSp.remove(i); // Xóa mã khỏi chitiethoadon
            }
        }
        return listMaThuHoi;
    }
}
