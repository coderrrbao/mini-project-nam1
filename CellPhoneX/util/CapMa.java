package util;

import database.Database;
import model.HangThanhVien;

public class CapMa {

    // PTHxxx: Mã phiếu trả hàng
    public static String capMaPhieuTraHang(Database db) {
        String soPTH = "1";
        if (db.getListPhieuTraHang().size() > 0) {
            soPTH = String.valueOf(Integer.parseInt(
                    db.getListPhieuTraHang()
                            .get(db.getListPhieuTraHang().size() - 1)
                            .getMaTraHang()
                            .replaceAll("PTH", ""))
                    + 1);
        }
        while (soPTH.length() < 3) {
            soPTH = "0" + soPTH;
        }
        return "PTH" + soPTH;
    }

    // BHxxx: Mã bảo hành
    public static String capMaBaoHanh(Database db) {
        String soBH = "1";
        if (db.getListBaoHanh().size() > 0) {
            soBH = String.valueOf(Integer.parseInt(
                    db.getListBaoHanh()
                            .get(db.getListBaoHanh().size() - 1)
                            .getMaBh()
                            .replaceAll("BH", ""))
                    + 1);
        }
        while (soBH.length() < 3) {
            soBH = "0" + soBH;
        }
        return "BH" + soBH;
    }

    // PBHxxx: Mã phiếu bảo hành
    public static String capMaPhieuBaoHanh(Database db) {
        String soPBH = "1";
        if (db.getListPhieuBaoHanh().size() > 0) {
            soPBH = String.valueOf(Integer.parseInt(
                    db.getListPhieuBaoHanh()
                            .get(db.getListPhieuBaoHanh().size() - 1)
                            .getMaBaoHanh()
                            .replaceAll("PBH", ""))
                    + 1);
        }
        while (soPBH.length() < 3) {
            soPBH = "0" + soPBH;
        }
        return "PBH" + soPBH;
    }

    // KHxxx: Mã khách hàng
    public static String capMaKhachHang(Database db) {
        String soKH = "1";
        if (db.getListKhachHang().size() > 0) {
            soKH = String.valueOf(Integer.parseInt(
                    db.getListKhachHang()
                            .get(db.getListKhachHang().size() - 1)
                            .getMaKh()
                            .replaceAll("KH", ""))
                    + 1);
        }
        while (soKH.length() < 3) {
            soKH = "0" + soKH;
        }
        return "KH" + soKH;
    }

    // CTHDxxx: Mã chi tiết hóa đơn
    public static String capMaChiTietHoaDon(Database db) {
        String soCTHD = "1";
        if (db.getListChiTietHoaDon().size() > 0) {
            soCTHD = String.valueOf(Integer.parseInt(
                    db.getListChiTietHoaDon()
                            .get(db.getListChiTietHoaDon().size() - 1)
                            .getMa()
                            .replaceAll("CTHD", ""))
                    + 1);
        }
        while (soCTHD.length() < 3) {
            soCTHD = "0" + soCTHD;
        }
        return "CTHD" + soCTHD;
    }

    // HDxxx: Mã hóa đơn
    public static String capMaHoaDon(Database db) {
        String soHD = "1";
        if (db.getListHoaDon().size() > 0) {
            soHD = String.valueOf(Integer.parseInt(
                    db.getListHoaDon()
                            .get(db.getListHoaDon().size() - 1)
                            .getMa()
                            .replaceAll("HD", ""))
                    + 1);
        }
        while (soHD.length() < 3) {
            soHD = "0" + soHD;
        }
        return "HD" + soHD;
    }

    // MGGxxx: Mã giảm giá
    public static String capMaMaGiamGia(Database db) {
        String soMGG = "1";
        if (db.getListMaGiamGia().size() > 0) {
            soMGG = String.valueOf(Integer.parseInt(
                    db.getListMaGiamGia()
                            .get(db.getListMaGiamGia().size() - 1)
                            .getMa()
                            .replaceAll("MGG", ""))
                    + 1);
        }
        while (soMGG.length() < 3) {
            soMGG = "0" + soMGG;
        }
        return "MGG" + soMGG;
    }

    // MGG+HANGxxx: Mã giảm giá độc quyền theo hạng thành viên
    public static String capMaMaGiamGiaDocQuyen(Database db, HangThanhVien hangThanhVien) {
        String soMGG = "1";
        if (hangThanhVien.getListMaGiamGiaDQ().size() > 0) {
            soMGG = String.valueOf(Integer.parseInt(
                    db.getListMaGiamGia()
                            .get(hangThanhVien.getListMaGiamGiaDQ().size() - 1)
                            .getMa()
                            .replaceAll("\\D+", ""))
                    + 1);
        }

        return "MGG" + hangThanhVien.getTenHang().toUpperCase() + soMGG;
    }

    // NVxxx: Mã nhân viên
    public static String capMaNhanVien(Database db) {
        String soNV = "1";
        if (db.getListNhanVien().size() > 0) {
            soNV = String.valueOf(Integer.parseInt(
                    db.getListNhanVien()
                            .get(db.getListNhanVien().size() - 1)
                            .getMa()
                            .replaceAll("NV", ""))
                    + 1);
        }
        while (soNV.length() < 3) {
            soNV = "0" + soNV;
        }
        return "NV" + soNV;
    }

    // QLxxx: Mã quản lý
    public static String capMaQuanLy(Database db) {
        String soQL = "1";
        if (db.getListQuanLy().size() > 0) {
            String ma = db.getListQuanLy()
                    .get(db.getListQuanLy().size() - 1)
                    .getMa()
                    .replaceAll("\\D+", "");
            if (ma.length() > 0) {
                soQL = String.valueOf(Integer.parseInt(ma) + 1);
            }

        }
        while (soQL.length() < 3) {
            soQL = "0" + soQL;
        }
        return "QL" + soQL;
    }

    // SPxxx: Mã thông tin sản phẩm
    public static String capMaThongTinSanPham(Database db) {
        String soSP = "1";
        if (db.getListThongTinSanPham().size() > 0) {
            soSP = String.valueOf(Integer.parseInt(
                    db.getListThongTinSanPham()
                            .get(db.getListThongTinSanPham().size() - 1)
                            .getMa()
                            .replaceAll("SP", ""))
                    + 1);
        }
        while (soSP.length() < 3) {
            soSP = "0" + soSP;
        }
        return "SP" + soSP;
    }

    // TNxxx: Mã tin nhắn
    public static String capMaTinNhan(Database db) {
        String soTN = "1";
        if (db.getListTinNhan().size() > 0) {
            soTN = String.valueOf(Integer.parseInt(
                    db.getListTinNhan()
                            .get(db.getListTinNhan().size() - 1)
                            .getMa()
                            .replaceAll("TN", ""))
                    + 1);
        }
        while (soTN.length() < 3) {
            soTN = "0" + soTN;
        }
        return "TN" + soTN;
    }

    // CAxxx: Mã ca làm
    public static String capMaCaLam(Database db) {
        String soCL = "1";
        if (db.getListCaLam().size() > 0) {
            soCL = String.valueOf(Integer.parseInt(
                    db.getListCaLam()
                            .get(db.getListCaLam().size() - 1)
                            .getMa()
                            .replaceAll("CA", ""))
                    + 1);
        }
        while (soCL.length() < 3) {
            soCL = "0" + soCL;
        }
        return "CA" + soCL;
    }

    // LTNxxx: Mã lịch trong ngày
    public static String capMaLichTrongNgay(Database db) {
        String soLTN = "1";
        if (db.getListLichTrongNgay().size() > 0) {
            soLTN = String.valueOf(Integer.parseInt(
                    db.getListLichTrongNgay()
                            .get(db.getListLichTrongNgay().size() - 1)
                            .getMa()
                            .replaceAll("LTN", ""))
                    + 1);
        }
        while (soLTN.length() < 3) {
            soLTN = "0" + soLTN;
        }
        return "LTN" + soLTN;
    }

    // LTT001: Mã lịch trong tuần (mặc định 001)
    public static String capMaLichTrongTuan(Database db) {
        return "LTT" + "001";
    }
}
