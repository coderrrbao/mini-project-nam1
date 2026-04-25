import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MonHoc {
    private String tenMon;
    private ArrayList<CauHoi> listCauHoi;

    public MonHoc() {

    }

    public MonHoc(String tenMon) {
        this.tenMon = tenMon;
        listCauHoi = new ArrayList<>();
    }

    public ArrayList<CauHoi> getListCauHoi() {
        return listCauHoi;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setListCauHoi(ArrayList<CauHoi> listCauHoi) {
        this.listCauHoi = listCauHoi;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public void themCauHoi(CauHoi ch) {
        if (ch == null) {
            System.out.println("cau hoi null");
            return;
        }
        listCauHoi.add(ch);
    }

    public BaiKiemTra taoBaiKiemTra(int soCau, String tenGv, int thuTuDtb) {
        if (soCau > listCauHoi.size()) {
            System.out.println("khong du so cau de tao bai kiem tra");
            return null;
        }
        BaiKiemTra kt = TaoDoiTuong.taoBaiKiemTra(this.tenMon, tenGv);
        Set<Integer> set = new HashSet<>();
        Random rd = new Random();
        while (soCau > 0) {
            int index = rd.nextInt(listCauHoi.size());
            if (!set.contains(index)) {
                set.add(index);
                kt.themCauHoi(listCauHoi.get(index));
                soCau--;
            }

        }
        kt.setMaBaiKt(TienIch.capMaCauHoi(thuTuDtb));
        return kt;
    }

    public void hienThiThongTin() {
        System.out.println(tenMon);
    }

    public void hienThiTatCaCauHoi() {
        if (listCauHoi == null)
            return;
        for (int i = 0; i < listCauHoi.size(); i++) {
            if (listCauHoi.get(i) != null) {
                listCauHoi.get(i).hienThiCauHoi();
            }
        }
    }
}
