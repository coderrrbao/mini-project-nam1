public class CauHoi {
    private String ma;
    private String cauHoi;
    private String A;
    private String B;
    private String C;
    private String D;
    private char cauDung;

    public CauHoi(String cauHoi, String A, String B, String C, String D, char cauDung) {
        this.cauHoi = cauHoi;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.cauDung = cauDung;
    }

    public CauHoi(String ma, String cauHoi, String A, String B, String C, String D, char cauDung) {
        this.cauHoi = cauHoi;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.cauDung = cauDung;
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public CauHoi() {

    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }

    public char getCauDung() {
        return cauDung;
    }

    public void setCauDung(char cauDung) {
        this.cauDung = cauDung;
    }

    public boolean kiemTra(char luaChon) {
        if (luaChon == cauDung) {
            return true;
        }
        return false;
    }

    public void hienThiCauHoi() {
        System.out.println(cauHoi);
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println(D);
        System.out.println();
    }
}
