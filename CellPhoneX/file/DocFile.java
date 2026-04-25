package file;

import java.io.BufferedReader;
import java.io.FileReader;

import danhsach.*;
import database.Database;
import model.*;
import util.XulyString;

public class DocFile {
    Database db;

    public DocFile(Database db) {
        this.db = db;
    }

    ////////////////////////////////// doc file tu datas

    // maNV_cmnd_hoTen_ngaySinh_sdt_gioiTinh_luong
    public void doc_NhanVientxt(String path) {
        DanhSachNhanVien danhSachNhanVien = db.getDanhSachNhanVien();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String maNV = thanhPhan[0];
                String cmnd = thanhPhan[1];
                String hoTen = thanhPhan[2];
                hoTen = XulyString.chuyenLaiDangStrMacDinh(hoTen);
                String ngaySinh = thanhPhan[3];
                String sdt = thanhPhan[4];
                String gioiTinh = thanhPhan[5];
                long luong = Long.parseLong(thanhPhan[6]);
                NhanVien nhanVien = new NhanVien(maNV, cmnd, hoTen, ngaySinh, sdt, gioiTinh, luong);
                danhSachNhanVien.them(nhanVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_QuanLytxt: maQL_cmnd_hoTen_ngaySinh_sdt_gioiTinh
    public void doc_QuanLytxt(String path) {
        DanhSachQuanLy danhSachQuanLy = db.getDanhSachQuanLy();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String maQL = thanhPhan[0];
                String cmnd = thanhPhan[1];
                String hoTen = thanhPhan[2];
                hoTen = XulyString.chuyenLaiDangStrMacDinh(hoTen);
                String ngaySinh = thanhPhan[3];
                String sdt = thanhPhan[4];
                String gioiTinh = thanhPhan[5];
                QuanLy quanLy = new QuanLy(maQL, cmnd, hoTen, ngaySinh, sdt, gioiTinh);
                danhSachQuanLy.them(quanLy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_ThongTinSanPhamtxt: ma_ten_danhMuc_thuongHieu_gia_moTa_trangThai
    public void doc_ThongTinSanPhamtxt(String path) {
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String ma = thanhPhan[0];
                String ten = XulyString.chuyenLaiDangStrMacDinh(thanhPhan[1]);
                String danhMuc = XulyString.chuyenLaiDangStrMacDinh(thanhPhan[2]);
                String thuongHieu = XulyString.chuyenLaiDangStrMacDinh(thanhPhan[3]);
                long gia = Long.parseLong(thanhPhan[4]);
                String moTa = XulyString.chuyenLaiDangStrMacDinh(thanhPhan[5]);
                String trangThai = XulyString.chuyenLaiDangStrMacDinh(thanhPhan[6]);
                trangThai = XulyString.chuyenLaiDangStrMacDinh(trangThai);
                ThongTinSanPham newTTSp = new ThongTinSanPham(ma, ten, danhMuc, thuongHieu, gia, moTa,
                        trangThai);
                danhSachThongTinSanPham.them(newTTSp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_MaGiamGiatxt:
    // ma_tenMa_loaiDoanhMuc_loaiThuongHieu_tienGiam_ngayBatDau_ngayKet
    public void doc_MaGiamGiatxt(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGia();
            DanhSachMaGiamGia danhSachMaGiamGiaDq = db.getDanhSachMaGiamGiaDq();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String ma = thanhPhan[0];
                String tenMa = thanhPhan[1];
                tenMa = XulyString.chuyenLaiDangStrMacDinh(tenMa);
                String loaiDoanhMuc = thanhPhan[2];
                String loaiThuongHieu = thanhPhan[3];
                String tienGiam = thanhPhan[4];
                String ngayBatDau = thanhPhan[5];
                String ngayKetThuc = thanhPhan[6];
                MaGiamGia maGiamGia = new MaGiamGia(ma, tenMa, loaiDoanhMuc, loaiThuongHieu, tienGiam, ngayBatDau,
                        ngayKetThuc);

                if (danhSachMaGiamGiaDq.laMaGiamGiaDocQuyen(maGiamGia)) {
                    danhSachMaGiamGiaDq.them(maGiamGia);
                    continue;
                }
                danhSachMaGiamGia.them(maGiamGia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_HangThanhVientxt: tenHang_moTa_[danhSachMaGiamGiaDQ]
    public void doc_HangThanhVientxt(String path) {
        DanhSachHangThanhVien danhSachHangThanhVien = db.getDanhSachHangThanhVien();
        DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGiaDq();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                HangThanhVien hangThanhVien = new HangThanhVien(thanhPhan[0],
                        XulyString.chuyenLaiDangStrMacDinh(thanhPhan[1]));
                for (int i = 2; i < thanhPhan.length; i++) {
                    hangThanhVien.themMaGiamGia(danhSachMaGiamGia.tim(thanhPhan[i]));
                }
                danhSachHangThanhVien.them(hangThanhVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_KhachHangtxt: maKh_tenKh_sdt_hangThanhVien
    public void doc_KhachHangtxt(String path) {
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        DanhSachHangThanhVien danhSachHangThanhVien = db.getDanhSachHangThanhVien();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String maKh = thanhPhan[0];
                String tenKh = thanhPhan[1];
                tenKh = XulyString.chuyenLaiDangStrMacDinh(tenKh);
                String sdt = thanhPhan[2];
                String hangThanhVien = thanhPhan[3];
                KhachHang khachHang = new KhachHang(maKh, tenKh, sdt,
                        danhSachHangThanhVien.tim(hangThanhVien));
                danhSachKhachHang.them(khachHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_BaoHanhtxt: maBh_loaiBaoHanh_maSanPham_gia
    public void doc_BaoHanhtxt(String path) {
        DanhSachBaoHanh danhSachBaoHanh = db.getDanhSachBaoHanh();
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                SanPham sanPham = new SanPham();
                sanPham.setThongTinSanPham(danhSachThongTinSanPham.tim(thanhPhan[2]));
                danhSachBaoHanh.them(new BaoHanh(thanhPhan[0], thanhPhan[1],
                        sanPham, Long.parseLong(thanhPhan[3])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_PhieuBaoHanhtxt:
    // maBaoHanh_maKhachHang_serialSanPham_ngayBaoHanh_chiTietLoi
    public void doc_PhieuBaoHanhtxt(String path) {
        DanhSachPhieuBaoHanh danhSachPhieuBaoHanh = db.getDanhSachPhieuBaoHanh();
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }

                String[] thanhPhan = line.split("\\s+");
                danhSachPhieuBaoHanh.them(new PhieuBaoHanh(thanhPhan[0],
                        danhSachKhachHang.tim(thanhPhan[1]), danhSachSanPham.tim(thanhPhan[2]), thanhPhan[3],
                        XulyString.chuyenLaiDangStrMacDinh(thanhPhan[4])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_PhieuTraHangtxt: maTraHang_maKhachHang_serialSanPham_ngayTra_lyDoTra
    public void doc_PhieuTraHangtxt(String path) { ///////
        DanhSachPhieuTraHang danhSachPhieuTraHang = db.getDanhSachPhieuTraHang();
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                danhSachPhieuTraHang.them(new PhieuTraHang(thanhPhan[0],
                        danhSachKhachHang.tim(thanhPhan[1]),
                        danhSachSanPham.tim(thanhPhan[2]), thanhPhan[3],
                        XulyString.chuyenLaiDangStrMacDinh(thanhPhan[4])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_ChiTietHoaDontxt: maChiTietHoaDon_thanhTien
    public void doc_ChiTietHoaDontxt(String path) {
        DanhSachChiTietHoaDon danhSachChiTietHoaDon = db.getDanhSachChiTietHoaDon();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                danhSachChiTietHoaDon.them(
                        new ChiTietHoaDon(thanhPhan[0], Long.parseLong(thanhPhan[1])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_SanPhamtxt: maSanPham_serial_traHang_maBaoHanh_daBan
    public void doc_SanPhamtxt(String path) {
        DanhSachBaoHanh danhSachBaoHanh = db.getDanhSachBaoHanh();
        DanhSachThongTinSanPham danhSachThongTinSanPham = db.getDanhSachThongTinSanPham();
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                ThongTinSanPham thongTinSanPham = danhSachThongTinSanPham.tim(thanhPhan[0]);
                if (thongTinSanPham == null) {
                    continue;
                }
                String serial = thanhPhan[1];
                boolean traHang = thanhPhan[2].equals("true") ? true : false;
                BaoHanh baoHanh = thanhPhan[3].equals("null") ? null : danhSachBaoHanh.tim(thanhPhan[3]);
                boolean daBan = Boolean.parseBoolean(thanhPhan[4]);
                SanPham sanPham = new SanPham(serial, thongTinSanPham, traHang, baoHanh, daBan);
                danhSachSanPham.them(sanPham);
                db.getKhoSerial().add(serial);
                if (!sanPham.getDaBan()) {
                    thongTinSanPham.tangTonKho();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_ChiTietHoaDon_SanPhamtxt: maChiTietHoaDon_[danhSachSerial]
    public void doc_ChiTietHoaDon_SanPhamtxt(String path) { //////////////
        DanhSachChiTietHoaDon danhSachChiTietHoaDon = db.getDanhSachChiTietHoaDon();
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                ChiTietHoaDon chiTietHoaDon = danhSachChiTietHoaDon.tim(thanhPhan[0]);
                if (chiTietHoaDon == null) {
                    continue;
                }
                for (int i = 1; i < thanhPhan.length; i++) {
                    chiTietHoaDon.themSanPham(danhSachSanPham.tim(thanhPhan[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_ChiTietHoaDon_MaGiamGiaDaDungtxt:
    // maChiTietHoaDon_maMaGiamGia_serialSanPhamDaDung
    public void doc_ChiTietHoaDon_MaGiamGiaDaDungtxt(String path) {
        DanhSachChiTietHoaDon danhSachChiTietHoaDon = db.getDanhSachChiTietHoaDon();
        DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGia();
        DanhSachMaGiamGia danhSachMaGiamGiaDq = db.getDanhSachMaGiamGiaDq();
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");

                ChiTietHoaDon chiTietHoaDon = danhSachChiTietHoaDon.tim(thanhPhan[0]);
                if (chiTietHoaDon == null) {
                    continue;
                }
                MaGiamGia maGiamGia = danhSachMaGiamGia.tim(thanhPhan[1]);
                if (maGiamGia == null) {
                    maGiamGia = danhSachMaGiamGiaDq.tim(thanhPhan[1]);
                }

                SanPham sanPham = danhSachSanPham.tim(thanhPhan[2]);

                MaGiamGia maDaDung = new MaGiamGia(maGiamGia);
                maDaDung.setSanPhamDaDung(sanPham);
                chiTietHoaDon.themMaGiamGia(maDaDung);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_HoaDontxt:
    // maHoaDon_maKhachHang_ngayTaoHoaDon_ghiChu_thanhTien_[danhSachChiTietHoaDon]
    public void doc_HoaDontxt(String path) {
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        DanhSachChiTietHoaDon danhSachChiTietHoaDon = db.getDanhSachChiTietHoaDon();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String ma = thanhPhan[0];
                KhachHang khachHang = danhSachKhachHang.tim(thanhPhan[1]);
                String ngayTaoHoaDon = thanhPhan[2];
                String ghiChu = thanhPhan[3];
                long ThanhTien = Long.parseLong(thanhPhan[4]);
                ghiChu = XulyString.chuyenLaiDangStrMacDinh(ghiChu);
                HoaDon hoaDon = new HoaDon(ma, khachHang, ngayTaoHoaDon, ghiChu, ThanhTien);
                danhSachHoaDon.them(hoaDon);
                for (int i = 5; i < thanhPhan.length; i++) {
                    ChiTietHoaDon chiTietHoaDon = danhSachChiTietHoaDon.tim(thanhPhan[i]);
                    hoaDon.themChiTietHoaDon(chiTietHoaDon);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_KhachHang_PhieuBaoHanhtxt: maKhachHang_[danhSachPhieuBaoHanh]
    public void doc_KhachHang_PhieuBaoHanhtxt(String path) {
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        DanhSachPhieuBaoHanh danhSachPhieuBaoHanh = db.getDanhSachPhieuBaoHanh();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                KhachHang khachHang = danhSachKhachHang.tim(thanhPhan[0]);
                if (khachHang == null) {
                    continue;
                }
                for (int i = 1; i < thanhPhan.length; i++) {
                    khachHang.themPhieuBaoHanh(danhSachPhieuBaoHanh.tim(thanhPhan[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_KhachHang_BaoHanhtxt: maKhachHang_maBaoHanh_serialSanPham_ngayBatDau
    public void doc_KhachHang_BaoHanhtxt(String path) { /// ////////////////////////////
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        DanhSachBaoHanh danhSachBaoHanh = db.getDanhSachBaoHanh();
        DanhSachSanPham danhSachSanPham = db.getDanhSachSanPham();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                KhachHang khachHang = danhSachKhachHang.tim(thanhPhan[0]);
                if (khachHang == null) {
                    continue;
                }
                BaoHanh baoHanhGoc = danhSachBaoHanh.tim(thanhPhan[1]);
                BaoHanh baoHanh = new BaoHanh(baoHanhGoc);
                SanPham sanPham = danhSachSanPham.tim(thanhPhan[2]);
                baoHanh.setSanPham(sanPham);
                khachHang.themBaoHanh(baoHanh);
                if (baoHanh != null) {
                    baoHanh.setNgayBatDau(thanhPhan[3]);
                    baoHanh.setNgayKetThuc();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_KhachHang_MaGiamGiatxt: maKhachHang_[danhSachMaGiamGia]
    public void doc_KhachHang_MaGiamGiatxt(String path) {
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        DanhSachMaGiamGia danhSachMaGiamGia = db.getDanhSachMaGiamGia();
        DanhSachMaGiamGia danhSachMaGiamGiaDq = db.getDanhSachMaGiamGiaDq();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                KhachHang khachHang = danhSachKhachHang.tim(thanhPhan[0]);
                if (khachHang == null) {
                    continue;
                }
                for (int i = 1; i < thanhPhan.length; i++) {
                    MaGiamGia maGiamGia = danhSachMaGiamGia.tim(thanhPhan[i]);
                    DanhSachMaGiamGia danhSachMggKhachHang = new DanhSachMaGiamGia(khachHang.getListMaGiamGia());
                    if (maGiamGia != null) {
                        danhSachMggKhachHang.them(new MaGiamGia(maGiamGia));
                    }
                    MaGiamGia maGiamGiaDq = danhSachMaGiamGiaDq.tim(thanhPhan[i]);
                    if (maGiamGiaDq != null) {
                        danhSachMggKhachHang.them(new MaGiamGia(maGiamGiaDq));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_KhachHang_HoaDontxt: maKhachHang_[danhSachHoaDon]
    public void doc_KhachHang_HoaDontxt(String path) {
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        DanhSachHoaDon danhSachHoaDon = db.getDanhSachHoaDon();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                KhachHang khachHang = danhSachKhachHang.tim(thanhPhan[0]);
                if (khachHang == null) {
                    continue;
                }
                for (int i = 1; i < thanhPhan.length; i++) {
                    khachHang.themHoaDon(danhSachHoaDon.tim(thanhPhan[i]));
                }
            }
            danhSachKhachHang.setTienDaChi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_KhachHang_PhieuTraHangtxt: maKhachHang_[danhSachPhieuTraHang]
    public void doc_KhachHang_PhieuTraHangtxt(String path) {
        DanhSachKhachHang danhSachKhachHang = db.getDanhSachKhachHang();
        DanhSachPhieuTraHang danhSachPhieuTraHang = db.getDanhSachPhieuTraHang();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                KhachHang khachHang = danhSachKhachHang.tim(thanhPhan[0]);
                if (khachHang == null) {
                    continue;
                }
                for (int i = 1; i < thanhPhan.length; i++) {
                    khachHang.themPhieuTraHang(danhSachPhieuTraHang.tim(thanhPhan[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_CaLamtxt: maCaLam_soCa_gioBatDau_gioKetThuc_soLuongCan_[danhSachNhanVien]
    public void doc_CaLamtxt(String path) {
        DanhSachNhanVien danhSachNhanVien = db.getDanhSachNhanVien();
        DanhSachCaLam danhSachCaLam = db.getDanhSachCaLam();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String ma = thanhPhan[0];
                int so = Integer.parseInt(thanhPhan[1]);
                String gioBd = thanhPhan[2];
                String gioKt = thanhPhan[3];
                int soLuongCan = Integer.parseInt(thanhPhan[4]);
                CaLam caLam = new CaLam(ma, so, gioBd, gioKt, soLuongCan);
                for (int i = 5; i < thanhPhan.length; i++) {
                    caLam.themNhanVienVaoCa(danhSachNhanVien.tim(thanhPhan[i]));
                }
                danhSachCaLam.them(caLam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_CaLam_NhanVienDiemDanh: maCaLam_[danhSachNhanVienDiemDanh]
    public void doc_CaLam_NhanVienDiemDanh(String path) {
        DanhSachNhanVien danhSachNhanVien = db.getDanhSachNhanVien();
        DanhSachCaLam danhSachCaLam = db.getDanhSachCaLam();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String ma = thanhPhan[0];
                CaLam caLam = danhSachCaLam.tim(ma);
                if (caLam == null) {
                    continue;
                }
                for (int i = 1; i < thanhPhan.length; i++) {
                    caLam.getListNhanVien().diemDanhNhanVien((danhSachNhanVien.tim(thanhPhan[i])));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_LichTrongNgaytxt: maLichTrongNgay_thu_ngay_[danhSachCaLam]
    public void doc_LichTrongNgaytxt(String path) {
        DanhSachCaLam danhSachCaLam = db.getDanhSachCaLam();
        DanhSachLichTrongNgay danhSachLichTrongNgay = db.getDanhSachLichTrongNgay();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String ma = thanhPhan[0];
                int thu = Integer.parseInt(thanhPhan[1]);
                String ngay = thanhPhan[2];
                LichTrongNgay lichTrongNgay = new LichTrongNgay(ma, thu, ngay);
                for (int i = 3; i < thanhPhan.length; i++) {
                    lichTrongNgay.themCaLam(danhSachCaLam.tim(thanhPhan[i]));
                }
                danhSachLichTrongNgay.them(lichTrongNgay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_LichTrongTuantxt:
    // maLichTrongTuan_ngayThu2_ngayChuNhat_[danhSachLichTrongNgay]
    public void doc_LichTrongTuantxt(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] thanhPhan = line.split("\\s+");
            String ma = thanhPhan[0];
            String ngayThu2 = thanhPhan[1];
            String ngayCn = thanhPhan[2];
            LichTrongTuan lichTrongTuan = new LichTrongTuan(ma, ngayThu2, ngayCn);
            DanhSachLichTrongNgay danhSachLichTrongNgay = db.getDanhSachLichTrongNgay();
            for (int i = 3; i < thanhPhan.length; i++) {
                lichTrongTuan.themLichTrongNgay(danhSachLichTrongNgay.tim(thanhPhan[i]));
            }
            db.setLichTrongTuanNay(lichTrongTuan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_TaiKhoantxt: tenDangNhap_matKhau
    public void doc_TaiKhoantxt(String path) {
        DanhSachTaiKhoan danhSachTaiKhoan = db.getDanhSachTaiKhoan();
        DanhSachUser danhSachUser = db.getDanhSachUser();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                TaiKhoan taiKhoan = new TaiKhoan(thanhPhan[0], thanhPhan[1]);
                danhSachUser.ganTaiKhoanChoUser(taiKhoan);
                danhSachTaiKhoan.them(taiKhoan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_TinNhantxt: maTinNhan_tenNguoiGui_noiDung_ngayGui
    public void doc_TinNhantxt(String path) {
        DanhSachTinNhan danhSachTinNhan = db.getDanhSachTinNhan();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                String ma = thanhPhan[0];
                String tenNgGui = thanhPhan[1];
                tenNgGui = XulyString.chuyenLaiDangStrMacDinh(tenNgGui);
                String noiDung = thanhPhan[2];
                noiDung = XulyString.chuyenLaiDangStrMacDinh(noiDung);
                String ngayGui = thanhPhan[3];
                danhSachTinNhan.them(new TinNhan(ma, tenNgGui, noiDung, ngayGui));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // doc_User_TinNhantxt: maUser_[danhSachTinNhan]
    public void doc_User_TinNhantxt(String path) {
        DanhSachTinNhan danhSachTinNhan = db.getDanhSachTinNhan();
        DanhSachUser danhSachUser = db.getDanhSachUser();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.replaceAll("\\s+", "").length() == 0) {
                    continue;
                }
                String[] thanhPhan = line.split("\\s+");
                User user = danhSachUser.timUser(thanhPhan[0]);
                for (int i = 1; i < thanhPhan.length; i++) {
                    user.nhanTinNhan(danhSachTinNhan.tim(thanhPhan[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////// hàm gọi tất cả , thứ tự gọi hàm cực kì
    /// quan trọng
    public void doc_DatasVaoDatabase() {
        doc_NhanVientxt("datas/NhanVien.txt");
        doc_QuanLytxt("datas/QuanLy.txt");
        doc_ThongTinSanPhamtxt("datas/ThongTinSanPham.txt");
        doc_MaGiamGiatxt("datas/MaGiamGia.txt");
        doc_HangThanhVientxt("datas/HangThanhVien.txt");
        doc_KhachHangtxt("datas/KhachHang.txt");
        doc_BaoHanhtxt("datas/BaoHanh.txt");
        doc_SanPhamtxt("datas/SanPham.txt");
        doc_PhieuTraHangtxt("datas/PhieuTraHang.txt");
        doc_PhieuBaoHanhtxt("datas/PhieuBaoHanh.txt");
        doc_ChiTietHoaDontxt("datas/ChiTietHoaDon.txt");
        doc_ChiTietHoaDon_SanPhamtxt("datas/ChiTietHoaDon_SanPham.txt");
        doc_ChiTietHoaDon_MaGiamGiaDaDungtxt("datas/ChiTietHoaDon_MaGiamGiaDaDung.txt");
        doc_HoaDontxt("datas/HoaDon.txt");
        doc_KhachHang_PhieuBaoHanhtxt("datas/KhachHang_PhieuBaoHanh.txt");
        doc_KhachHang_BaoHanhtxt("datas/KhachHang_BaoHanh.txt");
        doc_KhachHang_MaGiamGiatxt("datas/KhachHang_MaGiamGia.txt");
        doc_KhachHang_HoaDontxt("datas/KhachHang_HoaDon.txt");
        doc_KhachHang_PhieuTraHangtxt("datas/KhachHang_PhieuTraHang.txt");
        doc_CaLamtxt("datas/CaLam.txt");
        doc_CaLam_NhanVienDiemDanh("datas/CaLam_NhanVienDiemDanh.txt");
        doc_LichTrongNgaytxt("datas/LichTrongNgay.txt");
        doc_LichTrongTuantxt("datas/LichTrongTuan.txt");
        db.taoDanhSachUser();
        doc_TaiKhoantxt("datas/TaiKhoan.txt");
        doc_TinNhantxt("datas/TinNhan.txt");
        doc_User_TinNhantxt("datas/User_TinNhan.txt");
    }

}