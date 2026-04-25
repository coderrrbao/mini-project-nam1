package danhsach;

import java.util.ArrayList;

import interfaces.QuanLyDanhSach;
import model.ChiTietHoaDon;
import model.HoaDon;
import util.ThoiGian;

public class DanhSachHoaDon implements QuanLyDanhSach<HoaDon> {
    private ArrayList<HoaDon> listHoaDon; // Danh sách chứa các hóa đơn
    private int soLuong = 0; // Biến đếm số lượng hóa đơn hiện có

    // Constructor có tham số - khởi tạo danh sách hóa đơn từ bên ngoài
    public DanhSachHoaDon(ArrayList<HoaDon> listHoaDon) {
        this.listHoaDon = listHoaDon;
    }

    // Constructor mặc định
    public DanhSachHoaDon() {
    };

    // Getter trả về danh sách hóa đơn
    public ArrayList<HoaDon> getListHoaDon() {
        return listHoaDon;
    }

    // Getter trả về số lượng hóa đơn
    public int getSoLuong() {
        return soLuong;
    }

    // Setter gán danh sách hóa đơn mới
    public void setListHoaDon(ArrayList<HoaDon> listHoaDon) {
        this.listHoaDon = listHoaDon;
    }

    // Setter gán lại số lượng hóa đơn
    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // ==================== CÁC HÀM XỬ LÝ ====================

    // Tìm hóa đơn theo mã
    public HoaDon tim(String ma) {
        if (listHoaDon == null) {
            return null; // Nếu danh sách rỗng thì trả về null
        }
        for (int i = 0; i < listHoaDon.size(); i++) {
            if (listHoaDon.get(i).getMa().equals(ma)) {
                return listHoaDon.get(i); // Trả về hóa đơn nếu mã trùng khớp
            }
        }
        return null; // Không tìm thấy
    }

    // Xóa hóa đơn theo mã
    public boolean xoa(String ma) {
        HoaDon hoaDon = tim(ma); // Tìm hóa đơn trước
        if (hoaDon == null) {
            return false; // Không tồn tại thì trả về false
        }
        soLuong--; // Giảm biến đếm
        return listHoaDon.remove(hoaDon); // Xóa khỏi danh sách
    }

    // Xóa hóa đơn theo đối tượng
    public boolean xoa(HoaDon hoaDon) {
        if (hoaDon == null) {
            return false; // Nếu null thì không thể xóa
        }
        soLuong--;
        return listHoaDon.remove(hoaDon);
    }

    // Thêm hóa đơn mới
    public boolean them(HoaDon hd) {
        if (hd == null) {
            return false; // Không thêm nếu hóa đơn rỗng
        }
        soLuong++;
        return listHoaDon.add(hd);
    }

    // Tìm hóa đơn theo chi tiết hóa đơn
    public HoaDon tim(ChiTietHoaDon chiTietHoaDon) {
        for (HoaDon hoaDon : listHoaDon) {
            for (ChiTietHoaDon chiTietHd : hoaDon.getListChiTietHoaDon()) {
                if (chiTietHd.equals(chiTietHoaDon)) {
                    return hoaDon; // Trả về hóa đơn chứa chi tiết tương ứng
                }
            }
        }
        return null; // Không tìm thấy
    }

    // Tính doanh thu trong khoảng thời gian [ngày bắt đầu, ngày kết thúc]
    public long tinhDoanhThuTrongKhoan(String ngayBatDau, String ngayKetThuc) {
        long tongTien = 0;
        for (int i = 0; i < listHoaDon.size(); i++) {
            if (ThoiGian.ngayTrongKhoan(listHoaDon.get(i).getNgayTaoHoaDon(), ngayBatDau, ngayKetThuc)) {
                tongTien += listHoaDon.get(i).getThanhTien();
            }
        }
        return tongTien;
    }

    // Tính doanh thu trong một ngày cụ thể
    public long tinhDoanhThuTrongNgay(String ngay) {
        long tongTien = 0;
        for (int i = 0; i < listHoaDon.size(); i++) {
            if (listHoaDon.get(i).getNgayTaoHoaDon().equals(ngay)) {
                tongTien += listHoaDon.get(i).getThanhTien();
            }
        }
        return tongTien;
    }

    // Đếm số hóa đơn được tạo trong ngày hiện tại
    public int soHoaDonTrongNgay() {
        int count = 0;
        for (HoaDon hoaDon : listHoaDon) {
            if (hoaDon.getNgayTaoHoaDon().equals(ThoiGian.layNgayHienTaiStr())) {
                count++;
            }
        }
        return count;
    }

    // Đếm số hóa đơn trong khoảng thời gian [ngày bắt đầu, ngày kết thúc]
    public int soHoaDonTrongKhoan(String ngayBd, String ngayKt) {
        int count = 0;
        for (HoaDon hoaDon : listHoaDon) {
            if (ThoiGian.ngayTrongKhoan(hoaDon.getNgayTaoHoaDon(), ngayBd, ngayKt)) {
                count++;
            }
        }
        return count;
    }

}
