package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;

public class ThoiGian {

    // Lấy ngày hiện tại dưới dạng chuỗi "dd/MM/yyyy"
    public static String layNgayHienTaiStr() {
        LocalDate today = LocalDate.now(); // Lấy ngày hôm nay
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng chuỗi
        return today.format(fmt); // Chuyển LocalDate thành chuỗi theo định dạng
    }

    // Lấy giờ hiện tại dưới dạng chuỗi "HH:mm"
    public static String layGioHienTai() {
        LocalTime now = LocalTime.now(); // Lấy giờ hiện tại
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm"); // Định dạng giờ
        return now.format(fmt); // Chuyển LocalTime thành chuỗi
    }

    // Kiểm tra 1 thời điểm giờ có nằm trong khoảng giờ bắt đầu - giờ kết thúc hay không
    public static boolean gioTrongKhoan(String gio, String gioBatDau, String gioKetThuc) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm"); // Định dạng giờ
        LocalTime g = LocalTime.parse(gio, fmt); // Chuyển chuỗi giờ cần kiểm tra thành LocalTime
        LocalTime batDau = LocalTime.parse(gioBatDau, fmt); // Chuyển giờ bắt đầu thành LocalTime
        LocalTime ketThuc = LocalTime.parse(gioKetThuc, fmt); // Chuyển giờ kết thúc thành LocalTime

        // Nếu giờ kết thúc trước giờ bắt đầu (qua nửa đêm)
        if (ketThuc.isBefore(batDau)) {
            return !g.isBefore(batDau) || !g.isAfter(ketThuc);
        }
        // Nếu giờ kết thúc sau giờ bắt đầu bình thường
        return !g.isBefore(batDau) && !g.isAfter(ketThuc);
    }

    // Kiểm tra 1 ngày có nằm trong khoảng ngày bắt đầu - ngày kết thúc hay không
    public static boolean ngayTrongKhoan(String ngay, String ngayBatDau, String ngayKetThuc) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng ngày
        LocalDate g = LocalDate.parse(ngay, fmt); // Chuyển chuỗi ngày cần kiểm tra thành LocalDate
        LocalDate batDau = LocalDate.parse(ngayBatDau, fmt); // Chuyển ngày bắt đầu
        LocalDate ketThuc = LocalDate.parse(ngayKetThuc, fmt); // Chuyển ngày kết thúc

        return (!g.isBefore(batDau)) && (!g.isAfter(ketThuc)); // Trả về true nếu nằm trong khoảng
    }

    // Chuyển chuỗi ngày "dd/MM/yyyy" thành đối tượng LocalDate
    public static LocalDate chuyenStrThanhDate(String ngay) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(ngay, fmt);
    }

    // Chuyển LocalDate thành chuỗi "dd/MM/yyyy"
    public static String chuyenDateThanhStr(LocalDate ngay) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ngay.format(fmt);
    }

    // Lấy danh sách tất cả ngày trong tuần hiện tại
    public static ArrayList<String> layTuanHienTaiStr() {
        ArrayList<String> listNgay = new ArrayList<>();
        LocalDate today = LocalDate.now(); // Ngày hiện tại
        TemporalField dayOfWeekField = WeekFields.ISO.dayOfWeek(); // Lấy thông tin tuần ISO
        LocalDate batDau = today.with(dayOfWeekField, 1); // Ngày thứ 2 tuần hiện tại
        LocalDate ketThuc = today.with(dayOfWeekField, 7); // Ngày Chủ nhật tuần hiện tại
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = batDau;
        while (!date.isAfter(ketThuc)) { // Duyệt từ ngày đầu đến ngày cuối tuần
            listNgay.add(date.format(formatter)); // Thêm vào danh sách
            date = date.plusDays(1); // Tăng ngày
        }
        return listNgay;
    }

    // Lấy tháng hiện tại dưới dạng chuỗi "MM/yyyy"
    public static String layThangHienTai() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return today.format(formatter);
    }

    // Lấy ngày đầu tuần hiện tại
    public static String layNgayDauTuanStr() {
        LocalDate today = LocalDate.now();
        TemporalField dayOfWeekField = WeekFields.ISO.dayOfWeek();
        LocalDate batDau = today.with(dayOfWeekField, 1); // Thứ 2 tuần này
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return batDau.format(formatter);
    }

    // Lấy ngày đầu của tuần thứ soTuan trong năm
    public static String layNgayDauTuanStr(int soTuan) {
        LocalDate today = LocalDate.now();
        int nam = today.getYear(); // Lấy năm hiện tại
        WeekFields wf = WeekFields.ISO;
        LocalDate ngayDauTuan = LocalDate
                .ofYearDay(nam, 1)
                .with(wf.weekOfYear(), soTuan)
                .with(wf.dayOfWeek(), 1); // Ngày đầu tuần
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ngayDauTuan.format(fmt);
    }

    // Lấy ngày cuối tuần hiện tại
    public static String layNgayCuoiTuanStr() {
        LocalDate today = LocalDate.now();
        TemporalField dayOfWeekField = WeekFields.ISO.dayOfWeek();
        LocalDate batDau = today.with(dayOfWeekField, 7); // Chủ nhật tuần này
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return batDau.format(formatter);
    }

    // Lấy ngày cuối tháng hiện tại
    public static String layNgayCuoiThangStr() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        YearMonth ym = YearMonth.now(); // Tháng hiện tại
        LocalDate lastDay = ym.atEndOfMonth(); // Ngày cuối tháng
        return lastDay.format(fmt);
    }

    // Lấy ngày đầu tháng hiện tại
    public static String layNgayDauThangStr() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1); // Ngày 1 tháng hiện tại
        return firstDay.format(fmt);
    }

    // Tính số ngày giữa 2 ngày
    public static long khoangCachNgay(String ngayBd, String ngayKt) {
        LocalDate ngayBdLocal = chuyenStrThanhDate(ngayBd);
        LocalDate ngayKtLocal = chuyenStrThanhDate(ngayKt);
        return ChronoUnit.DAYS.between(ngayBdLocal, ngayKtLocal);
    }

    // Lấy ngày sau n tháng kể từ ngày truyền vào
    public static String ngaySauNThang(String ngay, int n) {
        LocalDate localDate = chuyenStrThanhDate(ngay);
        localDate = localDate.plusMonths(n); // Cộng n tháng
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(fmt);
    }

    // Lấy ngày tiếp theo của 1 ngày
    public static String ngayTiepTheo(String ngay) {
        LocalDate ngayLCD = chuyenStrThanhDate(ngay);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ngayLCD.plusDays(1).format(fmt);
    }

    // Lấy n ngày tiếp theo của 1 ngày
    public static String nNgayTiepTheo(String ngay, int n) {
        LocalDate ngayLCD = chuyenStrThanhDate(ngay);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ngayLCD.plusDays(n).format(fmt);
    }

    // Lấy số tuần hiện tại trong năm
    public static int soTuanHienTai() {
        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.ISO;
        return today.get(weekFields.weekOfYear());
    }

    // Lấy số tuần trong năm của 1 ngày bất kỳ
    public static int laySoTuanTrongNam(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        WeekFields weekFields = WeekFields.ISO;
        int weekOfYear = date.get(weekFields.weekOfWeekBasedYear());
        return weekOfYear;
    }
}
