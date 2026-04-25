import java.util.ArrayList;

public class TienIch {
    public static boolean lamCauHoi(CauHoi ch) {
        System.out.println(ch.getCauHoi());
        System.out.println("(a)" + ch.getA());
        System.out.println("(b)" + ch.getB());
        System.out.println("(c)" + ch.getC());
        System.out.println("(d)" + ch.getD());
        if (ch.kiemTra(Nhap.nhapLuaChonChar("nhap cau tra loi : "))) {
            return true;
        }
        return false;
    }

    public static int lamBaiKiemTra(BaiKiemTra baiKt) {
        int soCauDung = 0;
        ArrayList<CauHoi> listCauHoi = baiKt.getListCauHoi();
        for (int i = 0; i < listCauHoi.size(); i++) {
            System.out.print("cau " + i + 1 + ": ");
            if (lamCauHoi(listCauHoi.get(i))) {
                soCauDung++;
            }
        }
        return soCauDung;
    }

    public static TaiKhoan dangNhap(Database db) {
        TaiKhoan tk = TaoDoiTuong.taoTaiKhoan();
        int dem = 0;
        while ((tk = db.dangNhap(tk)) == null) {
            dem++;
            if (dem >= 3) {
                return null;
            }
            System.out.println("tai khoan hoac mat khau khong dung vui long nhap lai");
            tk = TaoDoiTuong.taoTaiKhoan();
        }
        return tk;

    }

    private static int so0Can(int so) {
        int dem = 0;
        while (so > 0) {
            so /= 10;
            dem++;
        }
        return 6 - dem;
    }

    public static String capMaCauHoi(int thuTuCau) {
        String ma = "";
        int so0 = so0Can(thuTuCau);
        for (int i = 0; i < so0; i++) {
            ma += "0";
        }
        return ma + String.valueOf(thuTuCau);
    }
}
