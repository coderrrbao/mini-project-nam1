public interface GiangDay {
    void themMotHocSinhVaoLop(Lop lop, HocSinh newHs);

    void quanLyLop(Database db);

    void themCauHoi(int thuTu, Database db);

    void themLop(Lop lop);

    void GiaoBaiKiemTra(Database db);
}