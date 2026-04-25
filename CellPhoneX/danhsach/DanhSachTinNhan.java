package danhsach;

import java.util.ArrayList;
import interfaces.QuanLyDanhSach;
import model.TinNhan;

public class DanhSachTinNhan implements QuanLyDanhSach<TinNhan> {
    private ArrayList<TinNhan> listTinNhan; // danh sách tin nhắn
    // constructor

    public DanhSachTinNhan(ArrayList<TinNhan> listTinNhan) {
        this.listTinNhan = listTinNhan;
    }

    public DanhSachTinNhan() {
    }

    // getter & setter
    public ArrayList<TinNhan> getListTinNhan() {
        return listTinNhan;
    }

    public int getSoLuong() {
        return listTinNhan.size();
    }

    public void setListTinNhan(ArrayList<TinNhan> listTinNhan) {
        this.listTinNhan = listTinNhan;
    }

    // tìm tin nhắn theo mã
    public TinNhan tim(String ma) {
        if (listTinNhan == null) {
            return null;
        }
        for (int i = 0; i < listTinNhan.size(); i++) {
            if (listTinNhan.get(i).getMa().equals(ma)) {
                return listTinNhan.get(i);
            }
        }
        return null;
    }

    // thêm tin nhắn object
    public boolean them(TinNhan tinNhan) {
        if (tinNhan == null) {
            return false;
        }
        return listTinNhan.add(tinNhan);
    }

    // thêm tin nhắn theo mã (nếu tồn tại)
    public boolean them(String ma) {
        TinNhan tinNhan = tim(ma);
        if (tinNhan == null) {
            return false;
        }
        return listTinNhan.add(tinNhan);
    }

    // xóa tin nhắn object
    public boolean xoa(TinNhan tinNhan) {
        if (tinNhan == null) {
            return false;
        }
        return listTinNhan.remove(tinNhan);
    }

    // xóa tin nhắn theo mã
    public boolean xoa(String ma) {
        TinNhan tinNhan = tim(ma);
        if (tinNhan == null) {
            return false;
        }
        return listTinNhan.remove(tinNhan);
    }
}
