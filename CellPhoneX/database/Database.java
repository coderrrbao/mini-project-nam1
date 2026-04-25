package database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import file.DocFile;
import danhsach.*;
import model.*;

public class Database {
    // Chứa các dánh sách đối tượng đối tượng của chương trình, quản lí phải lấy
    // tham chiếu thông qua đây
    private DanhSachNhanVien danhSachNhanVien;
    private DanhSachQuanLy danhSachQuanLy;
    private DanhSachHoaDon danhSachHoaDon;
    private DanhSachTaiKhoan danhSachTaiKhoan;
    private DanhSachThongTinSanPham danhSachThongTinSanPham;
    private DanhSachChiTietHoaDon danhSachChiTietHoaDon;
    private DanhSachBaoHanh danhSachBaoHanh;
    private DanhSachPhieuBaoHanh danhSachPhieuBaoHanh;
    private DanhSachPhieuTraHang danhSachPhieuTraHang;
    private DanhSachKhachHang danhSachKhachHang;
    private DanhSachMaGiamGia danhSachMaGiamGia;
    private DanhSachMaGiamGia danhSachMaGiamGiaDq;
    private DanhSachHangThanhVien danhSachHangThanhVien;
    private DanhSachTinNhan danhSachTinNhan;
    private DanhSachCaLam danhSachCaLam;
    private DanhSachLichTrongNgay danhSachLichTrongNgay;
    private LichTrongTuan lichTrongTuanNay;
    private DanhSachUser danhSachUser;
    private DanhSachSanPham danhSachSanPham;
    private Set<String> khoSerial;

    public Database() {
        danhSachNhanVien = new DanhSachNhanVien(new ArrayList<NhanVien>());
        danhSachQuanLy = new DanhSachQuanLy(new ArrayList<QuanLy>());
        danhSachHoaDon = new DanhSachHoaDon(new ArrayList<HoaDon>());
        danhSachTaiKhoan = new DanhSachTaiKhoan(new ArrayList<TaiKhoan>());
        danhSachThongTinSanPham = new DanhSachThongTinSanPham(new ArrayList<ThongTinSanPham>());
        danhSachChiTietHoaDon = new DanhSachChiTietHoaDon(new ArrayList<ChiTietHoaDon>());
        danhSachBaoHanh = new DanhSachBaoHanh(new ArrayList<BaoHanh>());
        danhSachPhieuBaoHanh = new DanhSachPhieuBaoHanh(new ArrayList<PhieuBaoHanh>());
        danhSachPhieuTraHang = new DanhSachPhieuTraHang(new ArrayList<PhieuTraHang>());
        danhSachKhachHang = new DanhSachKhachHang(new ArrayList<KhachHang>());
        danhSachMaGiamGia = new DanhSachMaGiamGia(new ArrayList<MaGiamGia>());
        danhSachMaGiamGiaDq = new DanhSachMaGiamGia(new ArrayList<MaGiamGia>());
        danhSachHangThanhVien = new DanhSachHangThanhVien(new ArrayList<HangThanhVien>());
        danhSachTinNhan = new DanhSachTinNhan(new ArrayList<TinNhan>());
        danhSachCaLam = new DanhSachCaLam(new ArrayList<CaLam>());
        danhSachLichTrongNgay = new DanhSachLichTrongNgay(new ArrayList<LichTrongNgay>());
        lichTrongTuanNay = null;
        danhSachSanPham = new DanhSachSanPham(new ArrayList<SanPham>());
        khoSerial = new HashSet<>();
        DocFile docFile = new DocFile(this);
        docFile.doc_DatasVaoDatabase();
    }

    public void taoDanhSachUser() {
        danhSachUser = new DanhSachUser(getListUser());
    }

    // ===== GET =====
    public ArrayList<NhanVien> getListNhanVien() {
        return danhSachNhanVien.getListNhanVien();
    }

    public ArrayList<SanPham> getListSanPham() {
        return danhSachSanPham.getListSanPham();
    }

    public ArrayList<QuanLy> getListQuanLy() {
        return danhSachQuanLy.getListQuanLy();
    }

    public ArrayList<HoaDon> getListHoaDon() {
        return danhSachHoaDon.getListHoaDon();
    }

    public ArrayList<TaiKhoan> getListTaiKhoan() {
        return danhSachTaiKhoan.getListTaiKhoan();
    }

    public Set<String> getKhoSerial() {
        return khoSerial;
    }

    public DanhSachThongTinSanPham getDanhSachThongTinSanPham() {
        return danhSachThongTinSanPham;
    }

    public ArrayList<ChiTietHoaDon> getListChiTietHoaDon() {
        return danhSachChiTietHoaDon.getListChiTietHoaDon();
    }

    public ArrayList<BaoHanh> getListBaoHanh() {
        return danhSachBaoHanh.getListBaoHanh();
    }

    public ArrayList<PhieuBaoHanh> getListPhieuBaoHanh() {
        return danhSachPhieuBaoHanh.getListPhieuBaoHanh();
    }

    public ArrayList<PhieuTraHang> getListPhieuTraHang() {
        return danhSachPhieuTraHang.getListPhieuTraHang();
    }

    public ArrayList<KhachHang> getListKhachHang() {
        return danhSachKhachHang.getListKhachHang();
    }

    public ArrayList<MaGiamGia> getListMaGiamGia() {
        return danhSachMaGiamGia.getListMaGiamGia();
    }

    public ArrayList<MaGiamGia> getListMaGiamGiaDq() {
        return danhSachMaGiamGiaDq.getListMaGiamGia();
    }

    public ArrayList<HangThanhVien> getListHangThanhVien() {
        return danhSachHangThanhVien.getListHangThanhVien();
    }

    public ArrayList<TinNhan> getListTinNhan() {
        return danhSachTinNhan.getListTinNhan();
    }

    public ArrayList<CaLam> getListCaLam() {
        return danhSachCaLam.getListCaLam();
    }

    public ArrayList<LichTrongNgay> getListLichTrongNgay() {
        return danhSachLichTrongNgay.getListLichTrongNgay();
    }

    public ArrayList<ThongTinSanPham> getListThongTinSanPham() {
        return danhSachThongTinSanPham.getListThongTinSanPham();
    }

    // ===== SET =====
    public void setListNhanVien(ArrayList<NhanVien> listNhanVien) {
        danhSachNhanVien.setListNhanVien(listNhanVien);
    }

    public void setListQuanLy(ArrayList<QuanLy> listQuanLy) {
        danhSachQuanLy.setListQuanLy(listQuanLy);
    }

    public void setListHoaDon(ArrayList<HoaDon> listHoaDon) {
        danhSachHoaDon.setListHoaDon(listHoaDon);
    }

    public void setListTaiKhoan(ArrayList<TaiKhoan> listTaiKhoan) {
        danhSachTaiKhoan.setListTaiKhoan(listTaiKhoan);
    }

    public void setDanhSachThongTinSanPham(DanhSachThongTinSanPham danhSachThongTinSanPham) {
        this.danhSachThongTinSanPham = danhSachThongTinSanPham;
    }

    public void setListChiTietHoaDon(ArrayList<ChiTietHoaDon> listChiTietHoaDon) {
        danhSachChiTietHoaDon.setListChiTietHoaDon(listChiTietHoaDon);
    }

    public void setListBaoHanh(ArrayList<BaoHanh> listBaoHanh) {
        danhSachBaoHanh.setListBaoHanh(listBaoHanh);
    }

    public void setListPhieuBaoHanh(ArrayList<PhieuBaoHanh> listPhieuBaoHanh) {
        danhSachPhieuBaoHanh.setListPhieuBaoHanh(listPhieuBaoHanh);
    }

    public void setListPhieuTraHang(ArrayList<PhieuTraHang> listPhieuTraHang) {
        danhSachPhieuTraHang.setListPhieuTraHang(listPhieuTraHang);
    }

    public void setListKhachHang(ArrayList<KhachHang> listKhachHang) {
        danhSachKhachHang.setListKhachHang(listKhachHang);
    }

    public void setListMaGiamGia(ArrayList<MaGiamGia> listMaGiamGia) {
        danhSachMaGiamGia.setListMaGiamGia(listMaGiamGia);
    }

    public void setListMaGiamGiaDq(ArrayList<MaGiamGia> listMaGiamGiaDq) {
        danhSachMaGiamGiaDq.setListMaGiamGia(listMaGiamGiaDq);
    }

    public void setListHangThanhVien(ArrayList<HangThanhVien> listHangThanhVien) {
        danhSachHangThanhVien.setListHangThanhVien(listHangThanhVien);
    }

    public void setListTinNhan(ArrayList<TinNhan> listTinNhan) {
        danhSachTinNhan.setListTinNhan(listTinNhan);
    }

    public void setListCaLam(ArrayList<CaLam> listCaLam) {
        danhSachCaLam.setListCaLam(listCaLam);
    }

    public void setListLichTrongNgay(ArrayList<LichTrongNgay> listLichTrongNgay) {
        danhSachLichTrongNgay.setListLichTrongNgay(listLichTrongNgay);
    }

    public DanhSachNhanVien getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public DanhSachUser getDanhSachUser() {
        danhSachUser = new DanhSachUser(getListUser());
        return danhSachUser;
    }

    public DanhSachQuanLy getDanhSachQuanLy() {
        return danhSachQuanLy;
    }

    public DanhSachHoaDon getDanhSachHoaDon() {
        return danhSachHoaDon;
    }

    public DanhSachTaiKhoan getDanhSachTaiKhoan() {
        return danhSachTaiKhoan;
    }

    public DanhSachSanPham getDanhSachSanPham() {
        return danhSachSanPham;
    }

    public DanhSachChiTietHoaDon getDanhSachChiTietHoaDon() {
        return danhSachChiTietHoaDon;
    }

    public DanhSachBaoHanh getDanhSachBaoHanh() {
        return danhSachBaoHanh;
    }

    public DanhSachPhieuBaoHanh getDanhSachPhieuBaoHanh() {
        return danhSachPhieuBaoHanh;
    }

    public DanhSachPhieuTraHang getDanhSachPhieuTraHang() {
        return danhSachPhieuTraHang;
    }

    public LichTrongTuan getLichTrongTuanNay() {
        return lichTrongTuanNay;
    }

    public DanhSachKhachHang getDanhSachKhachHang() {
        return danhSachKhachHang;
    }

    public DanhSachMaGiamGia getDanhSachMaGiamGia() {
        return danhSachMaGiamGia;
    }

    public DanhSachMaGiamGia getDanhSachMaGiamGiaDq() {
        return danhSachMaGiamGiaDq;
    }

    public DanhSachHangThanhVien getDanhSachHangThanhVien() {
        return danhSachHangThanhVien;
    }

    public DanhSachTinNhan getDanhSachTinNhan() {
        return danhSachTinNhan;
    }

    public DanhSachCaLam getDanhSachCaLam() {
        return danhSachCaLam;
    }

    public DanhSachLichTrongNgay getDanhSachLichTrongNgay() {
        return danhSachLichTrongNgay;
    }

    public void setDanhSachNhanVien(DanhSachNhanVien danhSachNhanVien) {
        this.danhSachNhanVien = danhSachNhanVien;
    }

    public void setKhoSerial(Set<String> khoSerial) {
        this.khoSerial = khoSerial;
    }

    public void setDanhSachQuanLy(DanhSachQuanLy danhSachQuanLy) {
        this.danhSachQuanLy = danhSachQuanLy;
    }

    public void setDanhSachHoaDon(DanhSachHoaDon danhSachHoaDon) {
        this.danhSachHoaDon = danhSachHoaDon;
    }

    public void setDanhSachTaiKhoan(DanhSachTaiKhoan danhSachTaiKhoan) {
        this.danhSachTaiKhoan = danhSachTaiKhoan;
    }

    public void setDanhSachSanPham(DanhSachSanPham danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }

    public void setDanhSachChiTietHoaDon(DanhSachChiTietHoaDon danhSachChiTietHoaDon) {
        this.danhSachChiTietHoaDon = danhSachChiTietHoaDon;
    }

    public void setDanhSachBaoHanh(DanhSachBaoHanh danhSachBaoHanh) {
        this.danhSachBaoHanh = danhSachBaoHanh;
    }

    public void setDanhSachPhieuBaoHanh(DanhSachPhieuBaoHanh danhSachPhieuBaoHanh) {
        this.danhSachPhieuBaoHanh = danhSachPhieuBaoHanh;
    }

    public void setDanhSachPhieuTraHang(DanhSachPhieuTraHang danhSachPhieuTraHang) {
        this.danhSachPhieuTraHang = danhSachPhieuTraHang;
    }

    public void setDanhSachKhachHang(DanhSachKhachHang danhSachKhachHang) {
        this.danhSachKhachHang = danhSachKhachHang;
    }

    public void setDanhSachMaGiamGia(DanhSachMaGiamGia danhSachMaGiamGia) {
        this.danhSachMaGiamGia = danhSachMaGiamGia;
    }

    public void setDanhSachMaGiamGiaDq(DanhSachMaGiamGia danhSachMaGiamGiaDq) {
        this.danhSachMaGiamGiaDq = danhSachMaGiamGiaDq;
    }

    public void setDanhSachHangThanhVien(DanhSachHangThanhVien danhSachHangThanhVien) {
        this.danhSachHangThanhVien = danhSachHangThanhVien;
    }

    public void setDanhSachTinNhan(DanhSachTinNhan danhSachTinNhan) {
        this.danhSachTinNhan = danhSachTinNhan;
    }

    public void setDanhSachCaLam(DanhSachCaLam danhSachCaLam) {
        this.danhSachCaLam = danhSachCaLam;
    }

    public void setDanhSachLichTrongNgay(DanhSachLichTrongNgay danhSachLichTrongNgay) {
        this.danhSachLichTrongNgay = danhSachLichTrongNgay;
    }

    public void setLichTrongTuanNay(LichTrongTuan lichTrongTuanNay) {
        this.lichTrongTuanNay = lichTrongTuanNay;
    }

    public ArrayList<User> getListUser() {
        ArrayList<User> listUser = new ArrayList<>();
        listUser.addAll(getListNhanVien());
        listUser.addAll(getListQuanLy());
        return listUser;
    }

}