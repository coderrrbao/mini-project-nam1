import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class GhiFile {

    public static void taoDuLieuDangFile(Database db) {
        ///////   Tao cho Hoc Sinh va Giao Vien
        ArrayList<HocSinh> listHocSinh = db.getListHocSinh();
        ArrayList<GiaoVien> listGiaoVien = db.getListGiaoVien();
        File hocSinh = new File("Data\\HocSinh");
        hocSinh.mkdir();
        for (int i = 0; i < listHocSinh.size(); i++) {

            File newFoder = new File("Data\\HocSinh\\" + listHocSinh.get(i).getMa());
            newFoder.mkdir();
        }
        File giaoVien = new File("Data\\GiaoVien");
        giaoVien.mkdir();
        for (int i = 0; i < listGiaoVien.size(); i++) {
            File newFoder = new File("Data\\GiaoVien\\" + listGiaoVien.get(i).getMa());

            newFoder.mkdir();
        }
        ////////// Tao cho lop va Mon Hoc
        ArrayList<Lop> listLop = db.getListLop();
        ArrayList<MonHoc> listMonHoc = db.getListMonHoc();
        File lop = new File("Data\\Lop");
        lop.mkdir();
        for (int i = 0; i < listLop.size(); i++) {
            File newFoder = new File("Data\\Lop\\" + listLop.get(i).getTenLop());
            newFoder.mkdir();
        }

        File mon = new File("Data\\MonHoc");
        mon.mkdir();
        for (int i = 0; i < listMonHoc.size(); i++) {
            File newFoder = new File("Data\\MonHoc\\" + listMonHoc.get(i).getTenMon());
            newFoder.mkdir();
        }
        /////// Tao cho Bai kiem Tra
        File baiKT = new File("Data\\BaiKiemTra");
        baiKT.mkdir();
        ArrayList<BaiKiemTra> listBaiKiemTra = db.getListBaiKiemTra();
        for (BaiKiemTra baiKiemTra : listBaiKiemTra) {
            File baiKt = new File("Data\\BaiKiemTra\\" + baiKiemTra.getMaBaiKt());
            baiKt.mkdir();
        }
    }

    public static void ghi_HocSinh_TinNhan(HocSinh hocSinh) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("Data\\HocSinh\\" + hocSinh.getMa() + "\\TinNhan.txt"))) {
            ArrayList<TinNhan> listTinNhan = hocSinh.getListTinNhan();
            if (listTinNhan == null) {
                return;
            }
            for (int i = 0; i < listTinNhan.size(); i++) {
                bw.write(listTinNhan.get(i).getMa() + " " + listTinNhan.get(i).getTen() + " "
                        + listTinNhan.get(i).getNoiDung());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_HocSinh_Diem(HocSinh hocSinh) {
        try (BufferedWriter br = new BufferedWriter(
                new FileWriter("Data\\HocSinh\\" + hocSinh.getMa() + "\\Diem.txt"))) {
            ArrayList<Diem> listDiem = hocSinh.getListDiem();
            if (listDiem == null) {
                return;
            }
            for (int i = 0; i < listDiem.size(); i++) {
                br.write(listDiem.get(i).getMa() + " " + listDiem.get(i).getTen().replaceAll(" ", "_") + " "
                        + listDiem.get(i).getDiem()
                        + " " + listDiem.get(i).getTenMon() + " " + listDiem.get(i).getHocKi() + " "
                        + listDiem.get(i).getNamHoc());
                br.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_HocSinh_BaiKiemTra(HocSinh hocSinh) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\HocSinh\\" + hocSinh.getMa()
                + "\\BaiKiemTra.txt"))) {
            ArrayList<BaiKiemTra> lisKiemTra = hocSinh.getListBaiKiemTra();
            if (lisKiemTra == null) {
                return;
            }
            for (int i = 0; i < lisKiemTra.size(); i++) {
                bw.write(lisKiemTra.get(i).getMaBaiKt() + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_HocSinh_ThongTin(HocSinh hocSinh) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\HocSinh\\" + hocSinh.getMa()
                + "\\ThongTin.txt"))) {
            bw.write(hocSinh.getMa() + " " + hocSinh.getTen().replace(" ", "_") + " " + hocSinh.getNamSinh() + " "
                    + hocSinh.getSdt()
                    + " " + hocSinh.getGioiTinh() + " " + hocSinh.getNienKhoa());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_GiaoVien_TinNhan(GiaoVien giaoVien) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\GiaoVien\\" + giaoVien.getMa()
                + "\\TinNhan.txt"))) {
            ArrayList<TinNhan> listTinNhan = giaoVien.getListTinNhan();
            if (listTinNhan == null) {
                return;
            }
            for (int i = 0; i < listTinNhan.size(); i++) {
                bw.write(listTinNhan.get(i).getMa() + " " + listTinNhan.get(i).getTen() + " "
                        + listTinNhan.get(i).getNoiDung());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_GiaoVien_Lop(GiaoVien giaoVien) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\GiaoVien\\" + giaoVien.getMa()
                + "\\Lop.txt"))) {
            ArrayList<Lop> listLop = giaoVien.getListLop();
            if (listLop == null) {
                return;
            }
            for (int i = 0; i < listLop.size(); i++) {
                bw.write(listLop.get(i).getTenLop() + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_GiaoVien_ThongTin(GiaoVien giaoVien) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\giaoVien\\" + giaoVien.getMa()
                + "\\ThongTin.txt"))) {
            bw.write(giaoVien.getMa() + " " + giaoVien.getTen().replace(" ", "_") + " " + giaoVien.getNamSinh() + " "
                    + giaoVien.getSdt()
                    + " " + giaoVien.getGioiTinh() + " " + giaoVien.getChuyenMon().getTenMon());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_Lop_HocSinh(Lop lop) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\Lop\\" + lop.getTenLop()
                + "\\HocSinh.txt"))) {
            ArrayList<HocSinh> listHocSinh = lop.getListHocSinh();
            if (listHocSinh == null) {
                return;
            }
            for (int i = 0; i < listHocSinh.size(); i++) {
                bw.write(listHocSinh.get(i).getMa() + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_Lop_BaiKiemTra(Lop lop) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\Lop\\" + lop.getTenLop()
                + "\\BaiKiemTra.txt"))) {
            ArrayList<BaiKiemTra> listBaiKiemTra = lop.getLisBaiKiemTra();
            if (listBaiKiemTra == null) {
                return;
            }
            for (int i = 0; i < listBaiKiemTra.size(); i++) {
                bw.write(listBaiKiemTra.get(i).getMaBaiKt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_Lop_ThongTin(Lop lop) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\Lop\\" + lop.getTenLop()
                + "\\ThongTin.txt"))) {
            bw.write(lop.getTenLop() + " " + lop.getLopMon());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_BaiKiemTra_ThongTin(BaiKiemTra baiKiemTra) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\BaiKiemTra\\" + baiKiemTra.getMaBaiKt()
                + "\\ThongTin.txt"))) {
            bw.write(baiKiemTra.getMaBaiKt() + " ");
            bw.write(baiKiemTra.getMon() + " ");
            bw.write(baiKiemTra.getNamHoc() + " ");
            bw.write(baiKiemTra.getHocKi() + " ");
            bw.write(baiKiemTra.getTongSoCau() + " ");
            bw.write(baiKiemTra.getGiaoVien().replaceAll(" ", "_") + " ");
            bw.write(baiKiemTra.getNgayBd() + " ");
            bw.write(baiKiemTra.getNgayKt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_BaiKiemTra_Diem(BaiKiemTra baiKiemTra) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\BaiKiemTra\\" + baiKiemTra.getMaBaiKt()
                + "\\Diem.txt"))) {
            ArrayList<Diem> listDiem = baiKiemTra.getListDiem();
            if (listDiem == null) {
                return;
            }
            for (int i = 0; i < listDiem.size(); i++) {
                bw.write(listDiem.get(i).getMa() + " " + listDiem.get(i).getTen().replaceAll(" ", "_") + " "
                        + listDiem.get(i).getDiem()
                        + " " + listDiem.get(i).getTenMon() + " " + listDiem.get(i).getHocKi() + " "
                        + listDiem.get(i).getNamHoc());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_BaiKiemTra_CauHoi(BaiKiemTra baiKiemTra) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\BaiKiemTra\\" + baiKiemTra.getMaBaiKt()
                + "\\CauHoi.txt"))) {
            ArrayList<CauHoi> listCauHoi = baiKiemTra.getListCauHoi();
            if (listCauHoi == null) {
                return;
            }
            for (CauHoi cauHoi : listCauHoi) {
                bw.write(cauHoi.getMa() + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ghi_HocSinh_GiaoVien_TaiKhoan(TaiKhoan tk, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(tk.getTenDangNhap() + " " + tk.getMatKhau());
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_MonHoc_ThongTin(MonHoc monHoc) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data\\MonHoc\\" + monHoc.getTenMon()
                + "\\ThongTin.txt"))) {
            bw.write(monHoc.getTenMon().replaceAll(" ", "_"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghi_MonHoc_CauHoi(MonHoc monHoc) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("Data\\MonHoc\\" + monHoc.getTenMon() + "\\CauHoi.txt"))) {
            ArrayList<CauHoi> listCauHoi = monHoc.getListCauHoi();
            if (listCauHoi == null) {
                return;
            }
            for (CauHoi cauHoi : listCauHoi) {
                bw.write(cauHoi.getMa() + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ghiDuLieuDoiTuongFileTk(Database db) {
        ArrayList<HocSinh> listHocSinh = db.getListHocSinh();
        ArrayList<GiaoVien> listGiaoVien = db.getListGiaoVien();

        for (HocSinh hocSinh : listHocSinh) {
            ghi_HocSinh_GiaoVien_TaiKhoan(hocSinh.getTaiKhoan(),
                    "Data\\HocSinh\\" + hocSinh.getMa() + "\\TaiKhoan.txt");

        }
        for (GiaoVien giaoVien : listGiaoVien) {
            ghi_HocSinh_GiaoVien_TaiKhoan(giaoVien.getTaiKhoan(),
                    "Data\\GiaoVien\\" + giaoVien.getMa() + "\\TaiKhoan.txt");
        }
    }

    public static void ghiDulieulenFileBaiKt(Database db) {
        ArrayList<BaiKiemTra> listBaiKiemTra = db.getListBaiKiemTra();
        if (listBaiKiemTra == null) {
            return;
        }
        for (int i = 0; i < listBaiKiemTra.size(); i++) {
            ghi_BaiKiemTra_CauHoi(listBaiKiemTra.get(i));
            ghi_BaiKiemTra_Diem(listBaiKiemTra.get(i));
            ghi_BaiKiemTra_ThongTin(listBaiKiemTra.get(i));
        }
    }

    public static void ghiDuLieuDoiTuongFileHs(Database db) {
        ArrayList<HocSinh> listHocSinh = db.getListHocSinh();
        if (listHocSinh == null) {
            return;
        }
        for (int i = 0; i < listHocSinh.size(); i++) {
            ghi_HocSinh_TinNhan(listHocSinh.get(i));
            ghi_HocSinh_Diem(listHocSinh.get(i));
            ghi_HocSinh_BaiKiemTra(listHocSinh.get(i));
            ghi_HocSinh_ThongTin(listHocSinh.get(i));
        }
    }

    public static void ghiDuLieuDoiTuongFileGv(Database db) {
        ArrayList<GiaoVien> listGiaoVien = db.getListGiaoVien();
        if (listGiaoVien == null) {
            return;
        }
        for (int i = 0; i < listGiaoVien.size(); i++) {
            ghi_GiaoVien_Lop(listGiaoVien.get(i));
            ghi_GiaoVien_TinNhan(listGiaoVien.get(i));
            ghi_GiaoVien_ThongTin(listGiaoVien.get(i));
        }
    }

    public static void ghiDuLieuDoiTuongFileLop(Database db) {
        ArrayList<Lop> listLop = db.getListLop();
        if (listLop == null) {
            return;
        }
        for (int i = 0; i < listLop.size(); i++) {
            ghi_Lop_HocSinh(listLop.get(i));
            ghi_Lop_BaiKiemTra(listLop.get(i));
            ghi_Lop_ThongTin(listLop.get(i));
        }
    }

    public static void ghiDuLieuDoiTuongFileMh(Database db) {
        ArrayList<MonHoc> listMonHoc = db.getListMonHoc();
        if (listMonHoc == null) {
            return;
        }
        for (int i = 0; i < listMonHoc.size(); i++) {
            ghi_MonHoc_ThongTin(listMonHoc.get(i));
            ghi_MonHoc_CauHoi(listMonHoc.get(i));
        }
    }

    public static void ghiDuLieuDoiTuongFileCh(Database db) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("Data\\" + "CauHoi.txt"))) {
            ArrayList<CauHoi> listCauHoi = db.getListCauHoi();
            if (listCauHoi == null) {
                return;
            }
            for (CauHoi cauHoi : listCauHoi) {
                bw.write(cauHoi.getMa());
                bw.newLine();
                bw.write(cauHoi.getCauHoi());
                bw.newLine();
                bw.write(cauHoi.getA());
                bw.newLine();
                bw.write(cauHoi.getB());
                bw.newLine();
                bw.write(cauHoi.getC());
                bw.newLine();
                bw.write(cauHoi.getD());
                bw.newLine();
                bw.write(cauHoi.getCauDung());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saoLuaDuLieu(Database db) {
        taoDuLieuDangFile(db);
        ghiDuLieuDoiTuongFileHs(db);
        ghiDuLieuDoiTuongFileGv(db);
        ghiDuLieuDoiTuongFileLop(db);
        ghiDulieulenFileBaiKt(db);
        ghiDuLieuDoiTuongFileMh(db);
        ghiDuLieuDoiTuongFileCh(db);
        ghiDuLieuDoiTuongFileTk(db);
    }

}
