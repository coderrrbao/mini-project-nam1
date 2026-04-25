package danhsach;

import java.util.ArrayList;

import database.Database;
import interfaces.QuanLyDanhSach;
import model.LichTrongNgay;
import util.CapMa;
import util.ThoiGian;

public class DanhSachLichTrongNgay implements QuanLyDanhSach<LichTrongNgay> {
    private ArrayList<LichTrongNgay> listLichTrongNgay; // Danh sách chứa các lịch trong ngày
    private int soLuong = 0; // Biến lưu số lượng lịch hiện có

    // Constructor có tham số - khởi tạo danh sách lịch từ bên ngoài
    public DanhSachLichTrongNgay(ArrayList<LichTrongNgay> listLichTrongNgay) {
        this.listLichTrongNgay = listLichTrongNgay;
    }

    // Constructor mặc định
    public DanhSachLichTrongNgay() {

    }

    // Getter - trả về danh sách lịch trong ngày
    public ArrayList<LichTrongNgay> getListLichTrongNgay() {
        return listLichTrongNgay;
    }

    // Getter - trả về số lượng lịch
    public int getSoLuong() {
        return soLuong;
    }

    // Setter - gán danh sách lịch trong ngày
    public void setListLichTrongNgay(ArrayList<LichTrongNgay> listLichTrongNgay) {
        this.listLichTrongNgay = listLichTrongNgay;
    }

    // Setter - gán lại số lượng lịch
    public void setSoLuong(int soLuong) {
        if (soLuong<0){
            return;
        }
        this.soLuong = soLuong;
    }


    // ==================== CÁC HÀM XỬ LÝ ====================

    // Tìm lịch trong ngày theo mã
    public LichTrongNgay tim(String ma) {
        if (listLichTrongNgay == null) {
            return null; // Nếu danh sách chưa được khởi tạo thì trả về null
        }
        for (int i = 0; i < listLichTrongNgay.size(); i++) {
            if (listLichTrongNgay.get(i).getMa().equals(ma)) {
                return listLichTrongNgay.get(i); // Trả về lịch trùng mã
            }
        }
        return null; // Không tìm thấy
    }

    // Tìm lịch ứng với ngày hiện tại (ngày hôm nay)
    public LichTrongNgay lichTrongNgayHomNay() {
        for (LichTrongNgay lichTrongNgay : listLichTrongNgay) {
            if (lichTrongNgay.getNgay().equals(ThoiGian.layNgayHienTaiStr())) {
                return lichTrongNgay; // Trả về lịch của ngày hiện tại
            }
        }
        return null; // Không có lịch cho ngày hôm nay
    }

    // Thêm lịch vào danh sách (theo đối tượng)
    public boolean them(LichTrongNgay lichTrongNgay) {
        if (lichTrongNgay == null) {
            return false; // Không thêm nếu null
        }
        soLuong++; // Tăng biến đếm
        return listLichTrongNgay.add(lichTrongNgay);
    }

    // Thêm lịch vào danh sách (theo mã)
    public boolean them(String ma) {
        LichTrongNgay lichTrongNgay = tim(ma); // Tìm lịch theo mã
        if (lichTrongNgay == null) {
            return false; // Không tồn tại lịch có mã đó
        }
        soLuong++;
        return listLichTrongNgay.add(lichTrongNgay);
    }

    // Xóa lịch trong ngày theo đối tượng
    public boolean xoa(LichTrongNgay lichTrongNgay) {
        if (lichTrongNgay == null) {
            return false;
        }
        soLuong--;
        return listLichTrongNgay.remove(lichTrongNgay);
    }

    // Xóa lịch trong ngày theo mã
    public boolean xoa(String ma) {
        LichTrongNgay lichTrongNgay = tim(ma);
        if (lichTrongNgay == null) {
            return false;
        }
        soLuong--;
        return listLichTrongNgay.remove(lichTrongNgay);
    }

    // tạo 1 list lichTrongNgay để hỗ trợ khởi tạo lịch trong tuần
    public ArrayList<LichTrongNgay> taoListLichTrongNgay(Database db, String ngay) {
        ArrayList<LichTrongNgay> listLichTrongNgayMau = new ArrayList<>();
        for (int thu = 2; thu <= 8; thu++) {
            String ma = CapMa.capMaLichTrongNgay(db);
            LichTrongNgay lichTrongNgay = new LichTrongNgay(ma, thu, ngay);
            lichTrongNgay.setListCaLam(db.getDanhSachCaLam().taoListCaLamTrongTrongNgay(db));
            listLichTrongNgayMau.add(lichTrongNgay);
            db.getDanhSachLichTrongNgay().them(lichTrongNgay);
            ngay = ThoiGian.ngayTiepTheo(ngay);
        }
        return listLichTrongNgayMau;
    }
}
