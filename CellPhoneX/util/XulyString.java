package util;

public class XulyString {
    /// chuyển lại mặc đinh khi đọc dữ liệu
    public static String dongGoiStr(String s) {
        return s.trim().replaceAll("\\s+", "_");
    }
    /// đóng gói string để ghi dữ liệu
    public static String chuyenLaiDangStrMacDinh(String s) {
        return s.replaceAll("_", " ");
    }
}
