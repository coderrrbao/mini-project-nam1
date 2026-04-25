package file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import database.Database;
import model.*;
import util.ThoiGian;
import util.XulyString;

public class GhiFile {
    private Database db;

    public GhiFile(Database db) {
        this.db = db;
    }

    // ghi_NhanVientxt: maNV_cmnd_hoTen_ngaySinh_sdt_gioiTinh_luong
    public void ghi_NhanVientxt(String path) {
        ArrayList<NhanVien> listNhanVien = db.getListNhanVien();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listNhanVien.size(); i++) {
                line = listNhanVien.get(i).getMa() + " " +
                        listNhanVien.get(i).getCccd() + " " +
                        XulyString.dongGoiStr(listNhanVien.get(i).getTen()) + " " +
                        listNhanVien.get(i).getNgaySinh() + " " +
                        listNhanVien.get(i).getSdt() + " " +
                        listNhanVien.get(i).getGioiTinh() + " " +
                        listNhanVien.get(i).getLuong();

                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_QuanLytxt: maQL_cmnd_hoTen_ngaySinh_sdt_gioiTinh
    public void ghi_QuanLytxt(String path) {
        ArrayList<QuanLy> listQuanly = db.getListQuanLy();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listQuanly.size(); i++) {
                line = listQuanly.get(i).getMa() + " " +
                        listQuanly.get(i).getCccd() + " " +
                        XulyString.dongGoiStr(listQuanly.get(i).getTen()) + " " +
                        listQuanly.get(i).getNgaySinh() + " " +
                        listQuanly.get(i).getSdt() + " " +
                        listQuanly.get(i).getGioiTinh();
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_QuanLytxt: maQL_cmnd_hoTen_ngaySinh_sdt_gioiTinh
    public void ghi_ThongTinSanPhamtxt(String path) {
        ArrayList<ThongTinSanPham> listSanPham = db.getListThongTinSanPham();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listSanPham.size(); i++) {
                line = listSanPham.get(i).getMa() + " " +
                        XulyString.dongGoiStr(listSanPham.get(i).getTen()) + " " +
                        XulyString.dongGoiStr(listSanPham.get(i).getDanhMuc()) + " " +
                        XulyString.dongGoiStr(listSanPham.get(i).getThuongHieu()) + " " +
                        listSanPham.get(i).getGia() + " " +
                        XulyString.dongGoiStr(listSanPham.get(i).getMoTa()) + " " +
                        XulyString.dongGoiStr(listSanPham.get(i).getTrangThai());
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_MaGiamGiatxt:
    // ma_tenMa_loaiDoanhMuc_loaiThuongHieu_tienGiam_ngayBatDau_ngayKetThuc
    public void ghi_MaGiamGiatxt(String path) {
        ArrayList<MaGiamGia> listMaGiamGia = db.getListMaGiamGia();
        ArrayList<MaGiamGia> listMaGiamGiaDq = db.getListMaGiamGiaDq();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            // Ghi ma giam gia thuong
            for (int i = 0; i < listMaGiamGia.size(); i++) {
                line = listMaGiamGia.get(i).getMa() + " " +
                        XulyString.dongGoiStr(listMaGiamGia.get(i).getTenMa()) + " " +
                        listMaGiamGia.get(i).getLoaiDoanhMuc() + " " +
                        listMaGiamGia.get(i).getLoaiThuongHieu() + " " +
                        listMaGiamGia.get(i).getTienGiam() + " " +
                        listMaGiamGia.get(i).getNgayBatDau() + " " +
                        listMaGiamGia.get(i).getNgayKetThuc();
                bw.write(line);
                bw.newLine();
            }
            // Ghi ma giam gia doc quyen
            for (int i = 0; i < listMaGiamGiaDq.size(); i++) {
                line = listMaGiamGiaDq.get(i).getMa() + " " +
                        XulyString.dongGoiStr(listMaGiamGiaDq.get(i).getTenMa()) + " " +
                        listMaGiamGiaDq.get(i).getLoaiDoanhMuc() + " " +
                        listMaGiamGiaDq.get(i).getLoaiThuongHieu() + " " +
                        listMaGiamGiaDq.get(i).getTienGiam() + " " +
                        listMaGiamGiaDq.get(i).getNgayBatDau() + " " +
                        listMaGiamGiaDq.get(i).getNgayKetThuc();
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_HangThanhVientxt: tenHang_moTa_[danhSachMaGiamGiaDQ]
    public void ghi_HangThanhVientxt(String path) {
        ArrayList<HangThanhVien> listHangThanhVien = db.getListHangThanhVien();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listHangThanhVien.size(); i++) {
                line = listHangThanhVien.get(i).getTenHang() + " " +
                        XulyString.dongGoiStr(listHangThanhVien.get(i).getMoTa());
                for (int j = 0; j < listHangThanhVien.get(i).getListMaGiamGiaDQ().size(); j++) {
                    line = line + " " +
                            listHangThanhVien.get(i).getListMaGiamGiaDQ().get(j).getMa();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_KhachHangtxt: maKh_tenKh_sdt_hangThanhVien
    public void ghi_KhachHangtxt(String path) {
        ArrayList<KhachHang> listKhachHang = db.getListKhachHang();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listKhachHang.size(); i++) {
                line = listKhachHang.get(i).getMaKh() + " " +
                        XulyString.dongGoiStr(listKhachHang.get(i).getTenKh()) + " " +
                        listKhachHang.get(i).getSdt() + " " +
                        ((listKhachHang.get(i).getHangThanhVien() == null) ? "Null"
                                : listKhachHang.get(i).getHangThanhVien().getTenHang());
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_BaoHanhtxt: maBh_loaiBaoHanh_maSanPham_gia
    public void ghi_BaoHanhtxt(String path) {
        ArrayList<BaoHanh> listBaoHanh = db.getListBaoHanh();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listBaoHanh.size(); i++) {
                line = listBaoHanh.get(i).getMaBh() + " " +
                        listBaoHanh.get(i).getLoaiBaoHanh() + " " +
                        listBaoHanh.get(i).getSanPham().getThongTinSanPham().getMa() + " " +
                        listBaoHanh.get(i).getGia();
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_PhieuBaoHanhtxt:
    // maBaoHanh_maKhachHang_serialSanPham_ngayBaoHanh_chiTietLoi
    public void ghi_PhieuBaoHanhtxt(String path) {
        ArrayList<PhieuBaoHanh> listPhieuBaoHanh = db.getListPhieuBaoHanh();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listPhieuBaoHanh.size(); i++) {
                line = listPhieuBaoHanh.get(i).getMaBaoHanh() + " " +
                        listPhieuBaoHanh.get(i).getKhachHang().getMaKh() + " " +
                        listPhieuBaoHanh.get(i).getSanPham().getSerial() + " " +
                        listPhieuBaoHanh.get(i).getNgayBaoHanh() + " " +
                        XulyString.dongGoiStr(listPhieuBaoHanh.get(i).getChiTietLoi());
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_PhieuTraHangtxt: maTraHang_maKhachHang_serialSanPham_ngayTra_lyDoTra
    public void ghi_PhieuTraHangtxt(String path) {
        ArrayList<PhieuTraHang> listPhieuTraHang = db.getListPhieuTraHang();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listPhieuTraHang.size(); i++) {
                line = listPhieuTraHang.get(i).getMaTraHang() + " " +
                        listPhieuTraHang.get(i).getKhachHang().getMaKh() + " " +
                        ((listPhieuTraHang.get(i).getSanPham() == null)
                                ? "null"
                                : listPhieuTraHang.get(i).getSanPham().getSerial())
                        + " " +
                        listPhieuTraHang.get(i).getNgayTra() + " " +
                        XulyString.dongGoiStr(listPhieuTraHang.get(i).getLyDoTra());
                bw.write(line);
                bw.newLine();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_ChiTietHoaDontxt: maChiTietHoaDon_thanhTien
    public void ghi_ChiTietHoaDontxt(String path) {
        ArrayList<ChiTietHoaDon> listChiTietHoaDon = db.getListChiTietHoaDon();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listChiTietHoaDon.size(); i++) {
                line = listChiTietHoaDon.get(i).getMa() + " " + listChiTietHoaDon.get(i).getThanhTien();
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_SanPhamtxt: maSanPham_serial_traHang_maBaoHanh_daBan
    public void ghi_SanPhamtxt(String path) {
        ArrayList<SanPham> listSanPham = db.getListSanPham();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (SanPham sanPham : listSanPham) {
                String maSp = sanPham.getThongTinSanPham().getMa();
                String serial = sanPham.getSerial();
                String traHang = sanPham.getTraHang() ? "true" : "false";
                String maBaoHanh = (sanPham.getBaoHanh() == null) ? "null" : sanPham.getBaoHanh().getMaBh();
                String daBan = sanPham.getDaBan() == true ? "true" : "false";
                bw.write(maSp + " " + serial + " " + traHang + " " + maBaoHanh + " " + daBan);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_ChiTietHoaDon_SanPhamtxt: maChiTietHoaDon_[danhSachSerial]
    public void ghi_ChiTietHoaDon_SanPhamtxt(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (ChiTietHoaDon chiTietHoaDon : db.getListChiTietHoaDon()) {
                String line;
                line = chiTietHoaDon.getMa();
                for (SanPham sanPham : chiTietHoaDon.getListSanPham()) {
                    line += " " + sanPham.getSerial();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // ghi_ChiTietHoaDon_MaGiamGiaDaDungtxt:
    // maChiTietHoaDon_maMaGiamGia_serialSanPhamDaDung
    public void ghi_ChiTietHoaDon_MaGiamGiaDaDungtxt(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (HoaDon hoaDon : db.getListHoaDon()) {
                for (ChiTietHoaDon chiTietHoaDon : hoaDon.getListChiTietHoaDon()) {
                    for (MaGiamGia maGiamGia : chiTietHoaDon.getListMaGiamGia()) {
                        String line = chiTietHoaDon.getMa() + " "
                                + maGiamGia.getMa() + " " + maGiamGia.getSanPhamDaDung().getSerial();
                        bw.write(line);
                        bw.newLine();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_HoaDontxt:
    // maHoaDon_maKhachHang_ngayTaoHoaDon_ghiChu_thanhTien_[danhSachChiTietHoaDon]
    public void ghi_HoaDontxt(String path) {
        ArrayList<HoaDon> listHoaDon = db.getListHoaDon();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listHoaDon.size(); i++) {
                line = listHoaDon.get(i).getMa() + " " +
                        listHoaDon.get(i).getKhachHang().getMaKh() + " " +
                        listHoaDon.get(i).getNgayTaoHoaDon() + " " +
                        XulyString.dongGoiStr(listHoaDon.get(i).getGhiChu()) + " " +
                        listHoaDon.get(i).getThanhTien();

                for (ChiTietHoaDon chiTietHoaDon : listHoaDon.get(i).getListChiTietHoaDon()) {
                    line += " " + chiTietHoaDon.getMa();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_KhachHang_PhieuBaoHanhtxt: maKhachHang_[danhSachPhieuBaoHanh]
    public void ghi_KhachHang_PhieuBaoHanhtxt(String path) {
        ArrayList<KhachHang> listKhachHang = db.getListKhachHang();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listKhachHang.size(); i++) {
                line = listKhachHang.get(i).getMaKh();
                for (int j = 0; j < listKhachHang.get(i).getListPhieuBaoHanh().size(); j++) {
                    line = line + " " +
                            listKhachHang.get(i).getListPhieuBaoHanh().get(j).getMaBaoHanh();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_KhachHang_BaoHanhtxt: maKhachHang_maBaoHanh_serialSanPham_ngayBatDau
    public void ghi_KhachHang_BaoHanhtxt(String path) {
        ArrayList<KhachHang> listKhachHang = db.getListKhachHang();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listKhachHang.size(); i++) {
                for (int j = 0; j < listKhachHang.get(i).getListBaoHanh().size(); j++) {
                    BaoHanh bh = listKhachHang.get(i).getListBaoHanh().get(j);
                    line = listKhachHang.get(i).getMaKh() + " " +
                            bh.getMaBh() + " " + bh.getSanPham().getSerial() + " " +
                            bh.getNgayBatDau();
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_KhachHang_MaGiamGiatxt: maKhachHang_[danhSachMaGiamGia]
    public void ghi_KhachHang_MaGiamGiatxt(String path) {
        ArrayList<KhachHang> listKhachHang = db.getListKhachHang();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listKhachHang.size(); i++) {
                line = listKhachHang.get(i).getMaKh();
                for (int j = 0; j < listKhachHang.get(i).getListMaGiamGia().size(); j++) {
                    line = line + " " + listKhachHang.get(i).getListMaGiamGia().get(j).getMa();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_KhachHang_HoaDontxt: maKhachHang_[danhSachHoaDon]
    public void ghi_KhachHang_HoaDontxt(String path) {
        ArrayList<KhachHang> listKhachHang = db.getListKhachHang();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listKhachHang.size(); i++) {
                line = listKhachHang.get(i).getMaKh();
                for (int j = 0; j < listKhachHang.get(i).getListHoaDon().size(); j++) {
                    line = line + " " + listKhachHang.get(i).getListHoaDon().get(j).getMa();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_KhachHang_PhieuTraHangtxt: maKhachHang_[danhSachPhieuTraHang]
    public void ghi_KhachHang_PhieuTraHangtxt(String path) {
        ArrayList<KhachHang> listKhachHang = db.getListKhachHang();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listKhachHang.size(); i++) {
                line = listKhachHang.get(i).getMaKh();
                for (int j = 0; j < listKhachHang.get(i).getListPhieuTraHang().size(); j++) {
                    line = line + " " +
                            listKhachHang.get(i).getListPhieuTraHang().get(j).getMaTraHang();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_CaLamtxt: maCaLam_soCa_gioBatDau_gioKetThuc_soLuongCan_[danhSachNhanVien]
    public void ghi_CaLamtxt(String path) {
        ArrayList<CaLam> listCaLam = db.getListCaLam();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listCaLam.size(); i++) {
                line = listCaLam.get(i).getMa() + " " +
                        listCaLam.get(i).getSo() + " " +
                        listCaLam.get(i).getGioBatDau() + " " +
                        listCaLam.get(i).getGioKetThuc() + " " +
                        listCaLam.get(i).getSoLuongCan();
                for (NhanVien nhanVien : listCaLam.get(i).getListNhanVien().getMapNhanVien().keySet()) {
                    line = line + " " + nhanVien.getMa();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_CaLam_NhanVienDiemDanh: maCaLam_[danhSachNhanVienDiemDanh]
    public void ghi_CaLam_NhanVienDiemDanh(String path) {
        ArrayList<CaLam> listCaLam = db.getListCaLam();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listCaLam.size(); i++) {
                line = listCaLam.get(i).getMa();

                for (NhanVien nhanVien : listCaLam.get(i).getListNhanVien().getMapNhanVien().keySet()) {
                    if (listCaLam.get(i).getListNhanVien().kiemTraDiemDanh(nhanVien)) {
                        line = line + " " + nhanVien.getMa();
                    }
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_LichTrongNgaytxt: maLichTrongNgay_thu_ngay_[danhSachCaLam]
    public void ghi_LichTrongNgaytxt(String path) {
        ArrayList<LichTrongNgay> listLichTrongNgay = db.getListLichTrongNgay();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listLichTrongNgay.size(); i++) {
                line = listLichTrongNgay.get(i).getMa() + " " +
                        listLichTrongNgay.get(i).getThu() + " " +
                        listLichTrongNgay.get(i).getNgay();
                for (int j = 0; j < listLichTrongNgay.get(i).getListCaLam().size(); j++) {
                    line = line + " " + listLichTrongNgay.get(i).getListCaLam().get(j).getMa();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_LichTrongTuantxt:
    // maLichTrongTuan_ngayThu2_ngayChuNhat_[danhSachLichTrongNgay]
    public void ghi_LichTrongTuantxt(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            LichTrongTuan lichTrongTuan = db.getLichTrongTuanNay();
            if (lichTrongTuan == null || !ThoiGian.ngayTrongKhoan(ThoiGian.layNgayHienTaiStr(),
                    lichTrongTuan.getNgayThu2(), lichTrongTuan.getNgayCn())) {
                return;
            }
            line = lichTrongTuan.getMa() + " " + lichTrongTuan.getNgayThu2() + " " +
                    lichTrongTuan.getNgayCn();
            for (LichTrongNgay lichTrongNgay : lichTrongTuan.getListLichTrongNgay()) {
                line += " " + lichTrongNgay.getMa();
            }
            bw.write(line);
            bw.newLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_TaiKhoantxt: tenDangNhap_matKhau
    public void ghi_TaiKhoantxt(String path) {
        ArrayList<TaiKhoan> listTaiKhoan = db.getListTaiKhoan();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listTaiKhoan.size(); i++) {
                line = listTaiKhoan.get(i).getTenDangNhap() + " " +
                        listTaiKhoan.get(i).getMatKhau();
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_TinNhantxt: maTinNhan_tenNguoiGui_noiDung_ngayGui
    public void ghi_TinNhantxt(String path) {
        ArrayList<TinNhan> listTinNhan = db.getListTinNhan();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listTinNhan.size(); i++) {
                line = listTinNhan.get(i).getMa() + " " +
                        XulyString.dongGoiStr(listTinNhan.get(i).getTenNgGui()) + " " +
                        XulyString.dongGoiStr(listTinNhan.get(i).getNoiDung()) + " " +
                        listTinNhan.get(i).getNgayGui();
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi_User_TinNhantxt: maUser_[danhSachTinNhan]
    public void ghi_User_TinNhantxt(String path) {
        ArrayList<User> listUser = db.getListUser();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String line;
            for (int i = 0; i < listUser.size(); i++) {
                line = listUser.get(i).getMa();
                for (int j = 0; j < listUser.get(i).getListTinNhan().size(); j++) {
                    line = line + " " + listUser.get(i).getListTinNhan().get(j).getMa();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ghi database vao data , ko quan trọng thứ tự
    public void ghi_DatasVaoDatabase() {
        ghi_NhanVientxt("datas/NhanVien.txt");
        ghi_QuanLytxt("datas/QuanLy.txt");
        ghi_ThongTinSanPhamtxt("datas/ThongTinSanPham.txt");
        ghi_MaGiamGiatxt("datas/MaGiamGia.txt");
        ghi_HangThanhVientxt("datas/HangThanhVien.txt");
        ghi_KhachHangtxt("datas/KhachHang.txt");
        ghi_BaoHanhtxt("datas/BaoHanh.txt");
        ghi_PhieuBaoHanhtxt("datas/PhieuBaoHanh.txt");
        ghi_PhieuTraHangtxt("datas/PhieuTraHang.txt");
        ghi_ChiTietHoaDontxt("datas/ChiTietHoaDon.txt");
        ghi_SanPhamtxt("datas/SanPham.txt");
        ghi_ChiTietHoaDon_SanPhamtxt("datas/ChiTietHoaDon_SanPham.txt");
        ghi_ChiTietHoaDon_MaGiamGiaDaDungtxt("datas/ChiTietHoaDon_MaGiamGiaDaDung.txt");
        ghi_HoaDontxt("datas/HoaDon.txt");
        ghi_KhachHang_PhieuBaoHanhtxt("datas/KhachHang_PhieuBaoHanh.txt");
        ghi_KhachHang_BaoHanhtxt("datas/KhachHang_BaoHanh.txt");
        ghi_KhachHang_MaGiamGiatxt("datas/KhachHang_MaGiamGia.txt");
        ghi_KhachHang_HoaDontxt("datas/KhachHang_HoaDon.txt");
        ghi_KhachHang_PhieuTraHangtxt("datas/KhachHang_PhieuTraHang.txt");
        ghi_CaLamtxt("datas/CaLam.txt");
        ghi_CaLam_NhanVienDiemDanh("datas/CaLam_NhanVienDiemDanh.txt");
        ghi_LichTrongNgaytxt("datas/LichTrongNgay.txt");
        ghi_LichTrongTuantxt("datas/LichTrongTuan.txt");
        ghi_TaiKhoantxt("datas/TaiKhoan.txt");
        ghi_TinNhantxt("datas/TinNhan.txt");
        ghi_User_TinNhantxt("datas/User_TinNhan.txt");

    }
}