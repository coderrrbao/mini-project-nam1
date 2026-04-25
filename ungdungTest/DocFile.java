import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class DocFile {

    public static void docFile_MonHoctxt(String tenFile, Database db) { ///////////////////////////
        ArrayList<MonHoc> listMon = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(tenFile))) {
            String line = null;
            while ((line = rd.readLine()) != null) {
                MonHoc monHoc = new MonHoc(line);
                listMon.add(monHoc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.setListMonHoc(listMon);
    }

    public static void docFile_DanhSachLoptxt(String tenFile, Database db) { ///////////////////////////
        ArrayList<Lop> listLop = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(tenFile))) {
            String line = null;
            while ((line = rd.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                for (int i = 1; i < thanhPhan.length; i++) {
                    if (thanhPhan[i].length() != 0)
                        listLop.add(new Lop(thanhPhan[i], thanhPhan[0]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.setListLop(listLop);
    }

    public static void docFile_HocSinhtxt(String tenFile, Database db) {
        ArrayList<HocSinh> listhHocSinh = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(tenFile))) {
            String line = null;
            while ((line = rd.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                String maSv = thanhPhan[0];
                String hoTen = thanhPhan[1].replace("_", " ");
                String ngaySinh = thanhPhan[2];
                String sdt = thanhPhan[3];
                String gioiTinh = thanhPhan[4];
                String nienKhoa = thanhPhan[5];
                HocSinh newHocSinh = new HocSinh(maSv, hoTen, ngaySinh, sdt, gioiTinh, nienKhoa);
                listhHocSinh.add(newHocSinh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listhHocSinh == null || listhHocSinh.size() == 0) {
            System.out.println("khong doc dc hoc sinh nao");
        }
        db.setListHocSinh(listhHocSinh);
    }

    public static void docFile_GiaoVientxt(String tenFile, Database db) {
        ArrayList<GiaoVien> listGiaoVien = new ArrayList<>();

        try (BufferedReader rd = new BufferedReader(new FileReader(tenFile))) {
            String line = null;
            while ((line = rd.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                String maGv = thanhPhan[0];
                String hoTen = thanhPhan[1].replace("_", " ");
                String ngaySinh = thanhPhan[2];
                String sdt = thanhPhan[3];
                String gioiTinh = thanhPhan[4];
                String chuyenMon = thanhPhan[5];
                GiaoVien gv = new GiaoVien(maGv, hoTen, ngaySinh, sdt, gioiTinh, db.timMonHoc(chuyenMon));
                listGiaoVien.add(gv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.setListGiaoVien(listGiaoVien);
    }

    public static void docFile_CauHoitxt(String tenFile, Database db) { ///////////// doc xong Mon hoc
        try (BufferedReader rd = new BufferedReader(new FileReader(tenFile))) {
            db.setListCauHoi(new ArrayList<>());
            String line;
            MonHoc monHoc = null;
            while ((line = rd.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;

                if (line.startsWith("@")) {
                    monHoc = db.timMonHoc(line.substring(1).trim());
                } else {
                    if (monHoc != null) {
                        String[] lines = line.split("\\.\\s+");
                        CauHoi cauHoi = TaoDoiTuong.taoCauHoi(rd, lines[0], lines[1]);
                        if (cauHoi != null) {
                            monHoc.themCauHoi(cauHoi);
                            db.themCauHoi(cauHoi);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void docFile_HocSinhCuaLoptxt(String tenFile, Database db) {
        try (BufferedReader rd = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = rd.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                Lop lop = db.timLop(thanhPhan[0]);
                if (lop != null) {
                    for (int i = 1; i < thanhPhan.length; i++) {
                        lop.themMotHocSinh(db.timHocSinh(thanhPhan[i]));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void docFile_GiaoVienCuaLoptxt(String tenFile, Database db) {
        try (BufferedReader rd = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = rd.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                Lop lop = db.timLop(thanhPhan[0]);
                if (lop != null) {
                    for (int i = 1; i < thanhPhan.length; i++) {
                        GiaoVien giaoVien = db.timGiaoVien(thanhPhan[i]);
                        if (giaoVien != null) {
                            giaoVien.themLop(lop);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void docFile_TaiKhoantxt(String tenFile, Database db) { // goi khi doc xong GiaoVien va hocSinh
        ArrayList<TaiKhoan> listTaiKhoan = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = rd.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                User user = db.timUser(thanhPhan[0]);
                if (user != null) {
                    TaiKhoan taiKhoan = new TaiKhoan(thanhPhan[0], thanhPhan[1], user);
                    user.setTaiKhoan(taiKhoan);
                    listTaiKhoan.add(taiKhoan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.setListTaiKhoan(listTaiKhoan);
    }

    // public static void docFileBaiKiemTratxt(String tenFile, Database db) { ///
    // doc thong tin cho bai kiem tra , chua co cau hoi --> viet lai (luu dang
    // foder)
    // ArrayList<BaiKiemTra> listBaiKiemTra = new ArrayList<>();
    // try (BufferedReader rd = new BufferedReader(new FileReader(tenFile))) {
    // String line = rd.readLine();
    // if (line == null) {
    // return;
    // }
    // String[] thanhPhan = line.split("\\s+");
    // BaiKiemTra newBaiKiemTra = new BaiKiemTra(thanhPhan[0],
    // Integer.parseInt(thanhPhan[1]), thanhPhan[2],
    // thanhPhan[3], thanhPhan[4], thanhPhan[5], Integer.parseInt(thanhPhan[6]),
    // Integer.parseInt(thanhPhan[7]));
    // listBaiKiemTra.add(newBaiKiemTra);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // db.setListBaiKiemTra(listBaiKiemTra);
    // }

    ////////////////////////////////////////////////////////////////
    public static void doc_HocSinh_TinNhan(String tenFile, HocSinh hocSinh) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                TinNhan newTn = new TinNhan(thanhPhan[0], thanhPhan[1], thanhPhan[2]);//// : ma_tenNgGui_NoiDung
                hocSinh.nhanTinNhan(newTn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_HocSinh_Diem(String tenFile, HocSinh hocSinh) {////@@@@@@@@@@@@@@@@@@@bug o ten _
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                Diem newDiem = new Diem(thanhPhan[0], thanhPhan[1], Double.parseDouble(thanhPhan[2]), thanhPhan[3],
                        Integer.parseInt(thanhPhan[4]), Integer.parseInt(thanhPhan[5]));
                hocSinh.luuDiem(newDiem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_HocSinh_ThongTin(String tenFile, HocSinh hocSinh) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                System.out.println(" Thong tin hoc sinh khong co du lieu");
                return;
            }
            String[] thanhPhan = line.split("\\s+");

            hocSinh.setMa(thanhPhan[0]);
            hocSinh.setTen(thanhPhan[1].replaceAll("_", " "));
            hocSinh.setNamSinh(thanhPhan[2]);
            hocSinh.setSdt(thanhPhan[3]);
            hocSinh.setGioiTinh(thanhPhan[4]);
            hocSinh.setNienKhoa(thanhPhan[5]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_HocSinh_BaiKiemTra(String tenFile, HocSinh hocSinh, Database db) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            for (int i = 0; i < thanhPhan.length; i++) {
                hocSinh.nhanBaiKiemTra(db.timBaiKiemTra(
                        thanhPhan[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_GiaoVien_TinNhan(String tenFile, GiaoVien giaoVien) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                TinNhan newTn = new TinNhan(thanhPhan[0], thanhPhan[1], thanhPhan[2]);//// : ma_tenNgGui_NoiDung
                giaoVien.nhanTinNhan(newTn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_GiaoVien_ThongTin(String tenFile, GiaoVien giaoVien, Database db) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                System.out.println(" Thong tin giao vien khong co du lieu");
                return;
            }
            String[] thanhPhan = line.split("\\s+");

            giaoVien.setMa(thanhPhan[0]);
            giaoVien.setTen(thanhPhan[1].replaceAll("_", " "));
            giaoVien.setNamSinh(thanhPhan[2]);
            giaoVien.setSdt(thanhPhan[3]);
            giaoVien.setGioiTinh(thanhPhan[4]);
            giaoVien.setChuyenMon(db.timMonHoc(thanhPhan[5]));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_GiaoVien_Lop(String tenFile, GiaoVien giaoVien,
            Database db) {//////////////////////////////////////////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            for (int i = 0; i < thanhPhan.length; i++) {
                giaoVien.themLop(db.timLop(thanhPhan[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_Lop_HocSinh(String tenFile, Lop lop, Database db) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            for (int i = 0; i < thanhPhan.length; i++) {
                lop.themMotHocSinh(db.timHocSinh(thanhPhan[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_Lop_BaiKiemTra(String tenFile, Lop lop, Database db) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            for (int i = 0; i < thanhPhan.length; i++) {
                lop.themBaiKiemTra(db.timBaiKiemTra(thanhPhan[i]));///////////////////////////////////
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_Lop_ThongTin(String tenFile, Lop lop, Database db) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            lop.setTenLop(thanhPhan[0]);
            lop.setLopMon(thanhPhan[1]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_Data_Lop(Database db, String path) {
        ArrayList<Lop> listLop = new ArrayList<>();
        File file = new File(path);
        File[] listFile = file.listFiles();
        for (File f : listFile) {
            if (f.isDirectory()) {
                Lop lop = new Lop();
                doc_Lop_ThongTin(file.getPath() + "\\" + f.getName() + "\\ThongTin.txt", lop, db);
                doc_Lop_BaiKiemTra(file.getPath() + "\\" + f.getName() + "\\BaiKiemTra.txt", lop, db);
                doc_Lop_HocSinh(file.getPath() + "\\" + f.getName() + "\\HocSinh.txt", lop, db);
                listLop.add(lop);
            }
        }
        db.setListLop(listLop);
    }

    public static void doc_BaiKiemTra_Diem(String tenFile, BaiKiemTra baiKiemTra) {////@@@@@@@@@@@@@@@@@@@bug o ten _
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                Diem newDiem = new Diem(thanhPhan[0], thanhPhan[1], Double.parseDouble(thanhPhan[2]), thanhPhan[3],
                        Integer.parseInt(thanhPhan[4]), Integer.parseInt(thanhPhan[5]));
                baiKiemTra.luuDiem(newDiem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_BaiKiemTra_CauHoi(String tenFile, BaiKiemTra baiKiemTra, Database db) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            for (int i = 0; i < thanhPhan.length; i++) {
                baiKiemTra.themCauHoi(db.timCauHoi(thanhPhan[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_BaiKiemTra_ThongTin(String tenFile, BaiKiemTra baiKiemTra, Database db) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            baiKiemTra.setMaBaiKt(thanhPhan[0]);
            baiKiemTra.setMon(thanhPhan[1]);
            baiKiemTra.setNamHoc(Integer.parseInt(thanhPhan[2]));
            baiKiemTra.setHocKi(Integer.parseInt(thanhPhan[3]));
            baiKiemTra.setTongSoCau(Integer.parseInt(thanhPhan[4]));
            baiKiemTra.setGiaoVien(thanhPhan[5].replaceAll("_", " "));
            baiKiemTra.setNgayBd(thanhPhan[6]);
            baiKiemTra.setNgayKt(thanhPhan[7]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_Data_BaiKiemTra(Database db, String path) {//////
        ArrayList<BaiKiemTra> listBaiKiemTra = new ArrayList<>();
        File file = new File(path);
        File[] listFile = file.listFiles();
        for (File f : listFile) {
            if (f.isDirectory()) {
                BaiKiemTra baiKiemTra = new BaiKiemTra();
                doc_BaiKiemTra_CauHoi(file.getPath() + "\\" + f.getName() + "\\CauHoi.txt", baiKiemTra, db);
                doc_BaiKiemTra_ThongTin(file.getPath() + "\\" + f.getName() + "\\ThongTin.txt", baiKiemTra, db);
                doc_BaiKiemTra_Diem(file.getPath() + "\\" + f.getName() + "\\Diem.txt", baiKiemTra);
                listBaiKiemTra.add(baiKiemTra);
            }
        }
        db.setListBaiKiemTra(listBaiKiemTra);
    }

    public static void doc_MonHoc_ThongTin(String tenFile, MonHoc monHoc) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            monHoc.setTenMon(thanhPhan[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_MonHoc_CauHoi(String tenFile, MonHoc monHoc, Database db) {
        ArrayList<CauHoi> listCauHoi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            for (int i = 0; i < thanhPhan.length; i++) {
                listCauHoi.add(db.timCauHoi(thanhPhan[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        monHoc.setListCauHoi(listCauHoi);
    }

    public static void doc_Data_MonHoc(Database db, String path) {
        ArrayList<MonHoc> listMonHoc = new ArrayList<>();
        File file = new File(path);
        File[] listFile = file.listFiles();
        for (File f : listFile) {
            if (f.isDirectory()) {
                MonHoc monHoc = new MonHoc();
                doc_MonHoc_ThongTin(file.getPath() + "\\" + f.getName() + "\\ThongTin.txt", monHoc);
                doc_MonHoc_CauHoi(file.getPath() + "\\" + f.getName() + "\\CauHoi.txt", monHoc, db);
                listMonHoc.add(monHoc);
            }
        }
        db.setListMonHoc(listMonHoc);
    }

    public static void doc_Data_HocSinh(Database db, String path) {
        ArrayList<HocSinh> listHocSinh = new ArrayList<>();
        File file = new File(path);
        File[] listFile = file.listFiles();
        for (File f : listFile) {
            if (f.isDirectory()) {
                HocSinh hocSinh = new HocSinh();
                doc_HocSinh_ThongTin(file.getPath() + "\\" + f.getName() + "\\ThongTin.txt", hocSinh);
                doc_HocSinh_TinNhan(file.getPath() + "\\" + f.getName() + "\\TinNhan.txt", hocSinh);
                doc_HocSinh_BaiKiemTra(file.getPath() + "\\" + f.getName() + "\\BaiKiemTra.txt", hocSinh, db);
                doc_HocSinh_Diem(file.getPath() + "\\" + f.getName() + "\\Diem.txt", hocSinh);
                doc_User_TaiKhoan(db, hocSinh, file.getPath() + "\\" + f.getName() + "\\TaiKhoan.txt");
                listHocSinh.add(hocSinh);
            }
        }
        db.setListHocSinh(listHocSinh);
    }

    public static void doc_Data_GiaoVien(Database db, String path) {
        ArrayList<GiaoVien> listGiaoVien = new ArrayList<>();
        File file = new File(path);
        File[] listFile = file.listFiles();
        for (File f : listFile) {
            if (f.isDirectory()) {
                GiaoVien giaoVien = new GiaoVien();
                doc_GiaoVien_ThongTin(file.getPath() + "\\" + f.getName() + "\\ThongTin.txt", giaoVien, db);
                doc_GiaoVien_Lop(file.getPath() + "\\" + f.getName() + "\\Lop.txt", giaoVien, db);
                doc_GiaoVien_TinNhan(file.getPath() + "\\" + f.getName() + "\\TinNhan.txt", giaoVien);
                doc_User_TaiKhoan(db, giaoVien, file.getPath() + "\\" + f.getName() + "\\TaiKhoan.txt");
                listGiaoVien.add(giaoVien);
            }
        }
        db.setListGiaoVien(listGiaoVien);
    }

    public static void doc_User_TaiKhoan(Database db, User user, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thanhPhan = line.split("\\s+");
                TaiKhoan tk = new TaiKhoan(thanhPhan[0], thanhPhan[1]);
                user.setTaiKhoan(tk);
                db.themTaiKhoan(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc_Data_CauHoitxt(Database db, String path) {
        ArrayList<CauHoi> listCauHoi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                CauHoi cauHoi = TaoDoiTuong.taoCauHoi(br, line);
                if (cauHoi != null) {
                    listCauHoi.add(cauHoi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.setListCauHoi(listCauHoi);
    }

}
