package quanli;

import java.util.ArrayList;

import danhsach.DanhSachUser;
import database.Database;
import model.TinNhan;
import model.User;
import util.Nhap;
import util.TaoDoiTuong;

public class QuanLyTinNhan {
    private Database db;

    public QuanLyTinNhan(Database db) {
        this.db = db;
    }

    // gửi tin nhắn
    public void guiTinNhan(User nguoiGui) {
        DanhSachUser danhSachUser = db.getDanhSachUser();
        User nguoiNhan = danhSachUser.timUser(Nhap.nhapStr("Nhap ma nguoi nhan : "));
        if (nguoiNhan == null) {
            System.out.println("khong tim thay nguoi nhan");
            return;
        }
        if (nguoiNhan.equals(nguoiGui)) {
            System.out.println("Nguoi nhan voi nguoi gui trung nhau");
            return;
        }
        TinNhan tinNhan = TaoDoiTuong.taoTinNhan(nguoiGui.getTen(), db);
        nguoiNhan.nhanTinNhan(tinNhan);
        db.getDanhSachTinNhan().them(tinNhan);
        System.out.println("Da gui tin nhan thanh cong");
    }

    /// xem tất cả tin nhắn
    public void xemTatCaTinNhan(User user) {
        ArrayList<TinNhan> listTinNhan = user.getListTinNhan();
        if (listTinNhan == null || listTinNhan.size() == 0) {
            System.out.println("Hop thu trong");
            return;
        }
        for (TinNhan tinNhan : listTinNhan) {
            System.out.println("---------------------------");
            System.out.println(tinNhan);
        }
    }
}
