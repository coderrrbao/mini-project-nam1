package danhsach;

import java.util.ArrayList;
import interfaces.QuanLyDanhSach;
import model.SanPham;

public class DanhSachSanPham implements QuanLyDanhSach<SanPham> {
    private ArrayList<SanPham> listSanPham; // danh sách sản phẩm
    // constructor khởi tạo danh sách
    public DanhSachSanPham(ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
    }

    public DanhSachSanPham() {
    }

    // getter & setter
    public ArrayList<SanPham> getListSanPham() {
        return listSanPham;
    }

    public void setListSanPham(ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
    }
    public int getSoLuong(){
        return listSanPham.size();
    }
    
    // thêm sản phẩm vào danh sách
    public boolean them(SanPham sanPham) {
        if (sanPham == null) {
            return false;
        }
        return listSanPham.add(sanPham);
    }

    // xóa sản phẩm theo mã serial
    public boolean xoa(String ma) {
        if (listSanPham == null) {
            return false;
        }
        SanPham sanPham = tim(ma);
        if (sanPham == null) {
            return false;
        }
        return listSanPham.remove(sanPham);
    }

    // xóa sản phẩm theo đối tượng
    public boolean xoa(SanPham sanPham) {
        if (listSanPham == null) {
            return false;
        }
        if (sanPham == null) {
            return false;
        }
        return listSanPham.remove(sanPham);
    }

    // tìm sản phẩm theo tên
    public SanPham timSanPhamTheoTen(String ten) {
        if (listSanPham == null) {
            return null;
        }
        for (int i = 0; i < listSanPham.size(); i++) {
            if (listSanPham.get(i).getTen().equals(ten)) {
                return listSanPham.get(i);
            }
        }
        return null;
    }

    // tìm sản phẩm theo mã serial
    public SanPham tim(String ma) {
        if (listSanPham == null) {
            return null;
        }
        for (int i = 0; i < listSanPham.size(); i++) {
            if (listSanPham.get(i).getSerial().equals(ma)) {
                return listSanPham.get(i);
            }
        }
        return null;
    }

    // tìm sản phẩm chưa bán và chưa bị trả hàng
    public SanPham timSanPhamChuaBan(String serial) {
        if (listSanPham == null) {
            return null;
        }
        for (int i = 0; i < listSanPham.size(); i++) {
            if (listSanPham.get(i).getSerial().equals(serial)
                    && !listSanPham.get(i).getDaBan()
                    && !listSanPham.get(i).getTraHang()) {
                return listSanPham.get(i);
            }
        }
        return null;
    }
}
