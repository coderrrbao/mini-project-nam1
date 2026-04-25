public class TinNhan {
    private String ma;
    private String ten;
    private String noiDung;

    public TinNhan(String ma, String ten, String noiDung) {
        this.ma = ma;
        this.ten = ten;
        this.noiDung = noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void hienThiThongTin() {
        System.out.println("nguoi gui : " + ma + " " + ten);
        System.out.println("noi dung : " + noiDung);
        System.out.println("-------------------");
    }
}