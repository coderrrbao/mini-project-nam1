package danhsach;

import java.util.ArrayList;

import interfaces.QuanLyDanhSach;
import model.HoaDon;
import model.KhachHang;

public class DanhSachKhachHang implements QuanLyDanhSach<KhachHang> {
    private ArrayList<KhachHang> listKhachHang; // Danh sách chứa các khách hàng
    private int soLuong = 0; // Biến lưu số lượng khách hàng hiện có

    // Constructor có tham số - khởi tạo danh sách khách hàng từ bên ngoài
    public DanhSachKhachHang(ArrayList<KhachHang> listKhachHang) {
        this.listKhachHang = listKhachHang;
    }

    // Constructor mặc định
    public DanhSachKhachHang() {
    };

    // Getter - trả về danh sách khách hàng
    public ArrayList<KhachHang> getListKhachHang() {
        return listKhachHang;
    }

    // Getter - trả về số lượng khách hàng
    public int getSoLuong() {
        return soLuong;
    }

    // Setter - gán danh sách khách hàng mới
    public void setListKhachHang(ArrayList<KhachHang> listKhachHang) {
        this.listKhachHang = listKhachHang;
    }

    // Setter - gán lại số lượng khách hàng
    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // ==================== CÁC HÀM XỬ LÝ ====================

    // Thêm khách hàng mới vào danh sách
    public boolean them(KhachHang khachHang) {
        if (khachHang == null) { // Nếu đối tượng null thì không thêm
            return false;
        }
        soLuong++; // Tăng biến đếm
        return listKhachHang.add(khachHang);
    }

    // Tìm khách hàng theo mã khách hàng
    public KhachHang tim(String ma) {
        if (listKhachHang == null) {
            return null; // Danh sách chưa khởi tạo
        }
        for (int i = 0; i < listKhachHang.size(); i++) {
            if (listKhachHang.get(i).getMaKh().equals(ma)) {
                return listKhachHang.get(i); // Trả về khách hàng nếu mã trùng khớp
            }
        }
        return null; // Không tìm thấy
    }

    // Tìm khách hàng theo số điện thoại
    public KhachHang timKhachHangTheoSdt(String sdt) {
        if (listKhachHang == null) {
            return null; // Danh sách rỗng
        }
        for (int i = 0; i < listKhachHang.size(); i++) {
            if (listKhachHang.get(i).getSdt().equals(sdt)) {
                return listKhachHang.get(i); // Trả về khách hàng có SDT trùng khớp
            }
        }
        return null; // Không tìm thấy
    }

    // Xóa khách hàng theo mã
    public boolean xoa(String ma) {
        KhachHang khachHang = tim(ma); // Tìm khách hàng trước
        if (khachHang == null) {
            return false; // Không tồn tại trong danh sách
        }
        soLuong--; // Giảm biến đếm
        return listKhachHang.remove(khachHang); // Xóa khách hàng khỏi danh sách
    }

    // Xóa khách hàng theo đối tượng
    public boolean xoa(KhachHang khachHang) {
        if (khachHang == null) {
            return false; // Nếu null thì không thể xóa
        }
        soLuong--;
        return listKhachHang.remove(khachHang);
    }

    // Cập nhật tổng tiền đã chi của một khách hàng cụ thể
    public void setTienDaChi(KhachHang khachHang) {
        long tong = 0;
        ArrayList<HoaDon> listHoaDon = khachHang.getListHoaDon(); // Lấy danh sách hóa đơn của khách hàng
        for (int i = 0; i < listHoaDon.size(); i++) {
            tong += listHoaDon.get(i).getThanhTien(); // Cộng dồn tổng tiền
        }
        khachHang.setTienDaChi(tong); // Gán lại tổng tiền đã chi
    }

    // Cập nhật tổng tiền đã chi cho tất cả khách hàng trong danh sách
    public void setTienDaChi() {
        for (int i = 0; i < listKhachHang.size(); i++) {
            setTienDaChi(listKhachHang.get(i)); // Gọi hàm trên cho từng khách hàng
        }
    }
}
