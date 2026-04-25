package model;

import java.util.ArrayList;

public class KhachHang {
    private String maKh;
    private String tenKh;
    private String sdt; 
    private HangThanhVien hangThanhVien;
    private long tienDaChi;

    private ArrayList<PhieuBaoHanh> listPhieuBaoHanh; // list phiếu bảo hành khi cần bảo hành sản phẩm hỏng (phải có bảo hành tương ứng)
    private ArrayList<BaoHanh> listBaoHanh; // list bảo hành của 1 sản phẩm cụ thể
    private ArrayList<MaGiamGia> listMaGiamGia; // list mã giảm giá (tạo riêng 1 đối tượng giống thông tin trong database để xóa dễ hơn)
    private ArrayList<HoaDon> listHoaDon; // list Hóa đơn
    private ArrayList<PhieuTraHang> listPhieuTraHang; // List Phiếu trả hàng khi trả sản phẩm

    public KhachHang(String maKh, String tenKh, String sdt, HangThanhVien hangThanhVien) {
        this.maKh = maKh;
        this.tenKh = tenKh;
        this.sdt = sdt;
        this.hangThanhVien = hangThanhVien;
        this.listBaoHanh = new ArrayList<>();
        this.listPhieuBaoHanh = new ArrayList<>();
        this.listMaGiamGia = new ArrayList<>();
        if (hangThanhVien != null) {
            this.listMaGiamGia.addAll(hangThanhVien.getListMaGiamGiaDQ());
        }
        this.listHoaDon = new ArrayList<>();
        this.listPhieuTraHang = new ArrayList<>();
    }

    public KhachHang(String maKh, String tenKh, String sdt) {
        this.maKh = maKh;
        this.tenKh = tenKh;
        this.sdt = sdt;
        this.listBaoHanh = new ArrayList<>();
        this.listPhieuBaoHanh = new ArrayList<>();
        this.listMaGiamGia = new ArrayList<>();
        this.listHoaDon = new ArrayList<>();
        this.listPhieuTraHang = new ArrayList<>();
    }

    public HangThanhVien getHangThanhVien() {
        return hangThanhVien;
    }

    public ArrayList<BaoHanh> getListBaoHanh() {
        return listBaoHanh;
    }

    public ArrayList<PhieuBaoHanh> getListPhieuBaoHanh() {
        return listPhieuBaoHanh;
    }

    public ArrayList<MaGiamGia> getListMaGiamGia() {
        return listMaGiamGia;
    }

    public String getMaKh() {
        return maKh;
    }

    public String getSdt() {
        return sdt;
    }

    public ArrayList<HoaDon> getListHoaDon() {
        return listHoaDon;
    }

    public ArrayList<PhieuTraHang> getListPhieuTraHang() {
        return listPhieuTraHang;
    }

    public String getTenKh() {
        return tenKh;
    }

    public long getTienDaChi() {
        return tienDaChi;
    }

    public void setHangThanhVien(HangThanhVien hangThanhVien) {
        this.hangThanhVien = hangThanhVien;
        if (hangThanhVien != null) {
            this.listMaGiamGia.addAll(hangThanhVien.getListMaGiamGiaDQ());
        }
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public void setListBaoHanh(ArrayList<BaoHanh> listBaoHanh) {
        this.listBaoHanh = listBaoHanh;
    }

    public void setListPhieuBaoHanh(ArrayList<PhieuBaoHanh> listPhieuBaoHanh) {
        this.listPhieuBaoHanh = listPhieuBaoHanh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setListHoaDon(ArrayList<HoaDon> listHoaDon) {
        this.listHoaDon = listHoaDon;
    }

    public void setListPhieuTraHang(ArrayList<PhieuTraHang> listPhieuTraHang) {
        this.listPhieuTraHang = listPhieuTraHang;
    }

    public void setListMaGiamGia(ArrayList<MaGiamGia> listMaGiamGia) {
        this.listMaGiamGia = listMaGiamGia;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public void setTienDaChi(long tienDaChi) {
        this.tienDaChi = tienDaChi;
    }

    public boolean themBaoHanh(BaoHanh baoHanh) {
        if (baoHanh == null) {
            return false;
        }
        return listBaoHanh.add(baoHanh);
    }

    public boolean themPhieuBaoHanh(PhieuBaoHanh phieuBaoHanh) {
        if (phieuBaoHanh == null) {
            return false;
        }
        return listPhieuBaoHanh.add(phieuBaoHanh);
    }

    public boolean themMaGiamGia(MaGiamGia maGiamGia) {
        if (maGiamGia == null) {
            return false;
        }
        return listMaGiamGia.add(maGiamGia);
    }

    public boolean xoaMaGiamGia(MaGiamGia maGiamGia) {
        if (maGiamGia == null) {
            return false;
        }
        for (MaGiamGia maGg : listMaGiamGia){
            if (maGg.getMa().equals(maGiamGia.getMa())){
                listMaGiamGia.remove(maGg);
                return true;
            }
        }
        return false;
    }

    public boolean themHoaDon(HoaDon hoaDon) {
        if (hoaDon == null) {
            return false;
        }
        return listHoaDon.add(hoaDon);
    }

    public boolean themPhieuTraHang(PhieuTraHang phieuTraHang) {
        if (phieuTraHang == null) {
            return false;
        }
        return listPhieuTraHang.add(phieuTraHang);
    }

    public void tangTienDaChi(long so) {
        this.tienDaChi += so;
    }

    public void giamTienDaChi(long so) {
        this.tienDaChi -= so;
    }

    @Override
    public String toString() {
        return "maKh : " + maKh + "\n" +
                "tenKh : " + tenKh + "\n" +
                "sdt : " + sdt + "\n" +
                "hangThanhVienMa : " + (hangThanhVien != null ? hangThanhVien.getTenHang() : "null") + "\n" +
                "tienDaChi : " + tienDaChi + "\n";
    }
}
