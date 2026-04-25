package model;

public class TinNhan {
    private String ma;
    private String tenNgGui;
    private String noiDung;
    private String ngayGui;

    public TinNhan(String ma, String tenNgGui, String noiDung, String ngayGui) {
        this.ma = ma;
        this.tenNgGui = tenNgGui;
        this.noiDung = noiDung;
        this.ngayGui = ngayGui;
    }

    public TinNhan() {
    };

    public String getMa() {
        return ma;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public String getTenNgGui() {
        return tenNgGui;
    }

    public String getNgayGui() {
        return ngayGui;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setTenNgGui(String tenNgGui) {
        this.tenNgGui = tenNgGui;
    }

    public void setNgayGui(String ngayGui) {
        this.ngayGui = ngayGui;
    }

    @Override
    public String toString() {
        return "  Ma         : " + ma + "\n" +
                "  NoiDung    : " + noiDung + "\n" +
                "  NguoiGui   : " + tenNgGui + "\n" +
                "  NgayGui    : " + ngayGui + "\n";
    }
}
