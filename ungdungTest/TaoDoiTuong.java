import java.io.BufferedReader;
import java.io.IOException;

public class TaoDoiTuong {
    public static TaiKhoan taoTaiKhoan() {
        System.out.println("DANG NHAP");
        String tk = Nhap.nhapLuaChonStr("Tai khoan : ");
        String mk = Nhap.nhapLuaChonStr("Mat khau : ");
        return new TaiKhoan(tk, mk);
    }

    public static CauHoi taoCauHoi(int thuTu) {
        String cauHoi = Nhap.nhapLuaChonStr("nhap cau hoi : ");
        String A = Nhap.nhapLuaChonStr("cau a : ");
        String B = Nhap.nhapLuaChonStr("cau B : ");
        String C = Nhap.nhapLuaChonStr("cau C : ");
        String D = Nhap.nhapLuaChonStr("cau D : ");
        char cauDung = Nhap.nhapLuaChonChar("nhap cau dung : ");
        CauHoi ch = new CauHoi(TienIch.capMaCauHoi(thuTu), cauHoi, A, B, C, D, cauDung);
        return ch;
    }

    public static CauHoi taoCauHoi(BufferedReader rd, String ma, String cauHoi) throws IOException {
        if (cauHoi == null || cauHoi.isEmpty())
            return null;

        String A = rd.readLine();
        String B = rd.readLine();
        String C = rd.readLine();
        String D = rd.readLine();
        String cauDungLine = rd.readLine();

        if (A == null || B == null || C == null || D == null || cauDungLine == null || cauDungLine.isEmpty()) {
            return null;
        }

        char cauDung = cauDungLine.trim().charAt(0);
        return new CauHoi(ma, cauHoi, A, B, C, D, cauDung);
    }

    public static CauHoi taoCauHoi(BufferedReader rd, String ma) throws IOException {
        String cauHoi = rd.readLine();
        String A = rd.readLine();
        String B = rd.readLine();
        String C = rd.readLine();
        String D = rd.readLine();
        String cauDungLine = rd.readLine();

        if (A == null || B == null || C == null || D == null || cauDungLine == null || cauDungLine.isEmpty()) {
            return null;
        }

        char cauDung = cauDungLine.trim().charAt(0);
        return new CauHoi(ma, cauHoi, A, B, C, D, cauDung);
    }

    public static HocSinh taoHocSinh() {
        System.out.println("nhap hoc sinh :");
        String maSv = Nhap.nhapLuaChonStr("Nhap ma sinh vien can them : ");
        String ten = Nhap.nhapLuaChonStr("Ten : ");
        String ngaySinh = Nhap.nhapLuaChonStr("Ngay sinh : ");
        String sdt = Nhap.nhapLuaChonStr("SDT : ");
        String gioiTinh = Nhap.nhapLuaChonStr("Gioi Tinh : ");
        String nienKhoa = Nhap.nhapLuaChonStr("Nien khoa : ");
        return new HocSinh(maSv, ten, ngaySinh, sdt, gioiTinh, nienKhoa);
    }

    public static BaiKiemTra taoBaiKiemTra(String tenMon, String tenGv) {
        String ngayBd = Nhap.nhapLuaChonStr("ngay bat dau : ");
        String ngayKt = Nhap.nhapLuaChonStr("ngay ket thuc : ");
        int namHoc = Nhap.nhapLuaChonInt("nam hoc : ");
        int hocKi = Nhap.nhapLuaChonInt("hoc ki : ");
        return new BaiKiemTra(tenMon, tenGv, ngayBd, ngayKt, namHoc, hocKi);
    }

    public static BaiKiemTra taoBaiKiemTra(String tenMon, String tenGv, int soCau, Database db) {
        BaiKiemTra baiKiemTra = taoBaiKiemTra(tenMon, tenGv);
        for (int i = 0; i < soCau; i++) {
            CauHoi cauHoi = taoCauHoi(db.laySoCauHoi());
            db.themCauHoi(cauHoi);
            baiKiemTra.themCauHoi(cauHoi);
        }
        return baiKiemTra;
    }
}
