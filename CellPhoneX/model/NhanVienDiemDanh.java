package model;

import java.util.HashMap;
import java.util.Map;

public class NhanVienDiemDanh {
    //// thuộc tính của ca làm, theo giỏi list nhân viên và tính trạng điểm danh
    private Map<NhanVien, Boolean> mapNhanVien;

    public NhanVienDiemDanh() {
        mapNhanVien = new HashMap<>();
    }

    public Map<NhanVien, Boolean> getMapNhanVien() {
        return mapNhanVien;
    }

    public Boolean kiemTraDiemDanh(NhanVien nhanVien) {
        if (nhanVien == null) {
            return false;
        }
        return mapNhanVien.get(nhanVien);
    }

    public boolean themNhanVien(NhanVien nhanVien) {
        if (nhanVien == null) {
            return false;
        }
        mapNhanVien.put(nhanVien, false);
        return true;
    }

    public boolean xoaNhanVien(NhanVien nhanVien) {
        if (nhanVien == null) {
            return false;
        }
        return mapNhanVien.remove(nhanVien);
    }

    public boolean diemDanhNhanVien(NhanVien nhanVien) {
        if (nhanVien == null) {
            return false;
        }
        if (mapNhanVien.containsKey(nhanVien)) {
            mapNhanVien.put(nhanVien, true);
            return true;
        }
        return false;
    }

    public int soNhanVien() {
        return mapNhanVien.size();
    }

    public boolean tonTaiNhanVien(NhanVien nhanVien) {
        return mapNhanVien.containsKey(nhanVien);
    }
}
