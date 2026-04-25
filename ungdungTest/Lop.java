import java.util.ArrayList;

public class Lop {
    private String tenLop;
    private String lopMon;
    private ArrayList<HocSinh> listHocSinh = new ArrayList<>();
    private ArrayList<BaiKiemTra> lisBaiKiemTra = new ArrayList<>();

    public Lop() {
    }

    public Lop(String tenLop, String lopMon) {
        this.tenLop = tenLop;
        this.lopMon = lopMon;
    }

    public Lop(String lopMon, ArrayList<HocSinh> listHocSinh) {
        this.lopMon = lopMon;
        this.listHocSinh = listHocSinh;
    }

    // Getter & Setter
    public ArrayList<BaiKiemTra> getLisBaiKiemTra() {
        return lisBaiKiemTra;
    }

    public void setLisBaiKiemTra(ArrayList<BaiKiemTra> lisBaiKiemTra) {
        this.lisBaiKiemTra = lisBaiKiemTra;
    }

    public String getLopMon() {
        return lopMon;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setLopMon(String lopMon) {
        this.lopMon = lopMon;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public ArrayList<HocSinh> getListHocSinh() {
        return listHocSinh;
    }

    public void setListHocSinh(ArrayList<HocSinh> listHocSinh) {
        this.listHocSinh = listHocSinh;
    }
    // cac phuong thuc

    public void giaoBaiKiemTra(BaiKiemTra newBaiKT) {
        if (newBaiKT == null) {
            return;
        }
        if (listHocSinh == null) {
            System.out.println("lop khong co hoc sinh de giao");
        }
        for (int i = 0; i < listHocSinh.size(); i++) {
            listHocSinh.get(i).nhanBaiKiemTra(newBaiKT);
        }
        lisBaiKiemTra.add(newBaiKT);
    }

    public void themBaiKiemTra(BaiKiemTra baiKt) {
        if (baiKt == null) {
            return;
        }
        lisBaiKiemTra.add(baiKt);
    }

    public void themMotHocSinh(HocSinh newHocSinh) {
        if (newHocSinh == null) {
            System.out.println("khong the them");
            return;
        }
        listHocSinh.add(newHocSinh);
    }

    public int getSoLuong() {
        return listHocSinh.size();
    }

    public void xoaMotHocSinh(String ma) {
        if (listHocSinh == null) {
            return;
        }
        for (int i = 0; i < listHocSinh.size(); i++) {
            if (listHocSinh.get(i).getMa().equals(ma)) {
                listHocSinh.remove(i);
                System.out.println("da xoa");
                return;
            }
        }
        System.out.println("khong tim thay hoc sinh can xoa");
    }

    public void timHocSinhTrongLopBangMa(String ma) {
        if (listHocSinh == null) {
            return;
        }
        for (int i = 0; i < listHocSinh.size(); i++) {
            if (listHocSinh.get(i).getMa().equals(ma)) {
                listHocSinh.get(i).hienThiThongTin();
                return;
            }
        }
        System.out.println("khong tim thay hoc sinh");
    }

    public void thongKeKetQuaBaikiemTra() {
        if (lisBaiKiemTra == null) {
            System.out.println("khong co bai kiem tra de hien thi");
            return;
        }
        for (int i = 0; i < lisBaiKiemTra.size(); i++) {
            lisBaiKiemTra.get(i).hienThiKetQua();
        }
    }

    public void hienThiTatCaHs() {
        if (listHocSinh == null) {
            System.out.println("khong co hoc sinh de hien thi");
            return;
        }
        for (int i = 0; i < listHocSinh.size(); i++) {
            listHocSinh.get(i).hienThiThongTin();
        }
    }

    public void hienThiThongTin() {
        System.out.println(tenLop + " : " + lopMon);
    }
}
