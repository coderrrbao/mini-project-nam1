package util;

import java.util.Scanner;

public class Nhap {
    static Scanner sc = new Scanner(System.in);

    // Kiểm tra chuỗi có phải số nguyên không
    private static boolean laSo(String so) {
        if (so == "") {
            return false;
        }
        for (int i = 0; i < so.length(); i++) {
            if (i == 0 && so.length() > 1 && so.charAt(i) == '-')
                continue;
            if (!Character.isDigit(so.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Nhập số nguyên từ người dùng, kiểm tra hợp lệ
    public static int nhapInt(String tuKhoa) {
        String luaChon;
        do {
            System.out.print(tuKhoa);
            luaChon = sc.nextLine();
            if (!laSo(luaChon)) {
                System.out.println("Chi duoc nhap so nguyen");
            }
        } while (!laSo(luaChon));

        return Integer.parseInt(luaChon);
    }

    // Nhập số nguyên dài (long) từ người dùng
    public static long nhapLong(String tuKhoa) {
        String luaChon;
        do {
            System.out.print(tuKhoa);
            luaChon = sc.nextLine();
            if (!laSo(luaChon)) {
                System.out.println("Chi duoc nhap so nguyen");
            }
        } while (!laSo(luaChon));

        return Long.parseLong(luaChon);
    }

    // Nhập chuỗi từ người dùng
    public static String nhapStr(String tuKhoa) {
        System.out.print(tuKhoa);
        return sc.nextLine();
    }

    // Nhập ký tự, đảm bảo chỉ nhập 1 ký tự
    public static char nhapChar(String tuKhoa) {
        String luaChon;
        do {
            System.out.print(tuKhoa);
            luaChon = sc.nextLine();
            if (luaChon.length() != 1) {
                System.out.println("Chi duoc nhap 1 ky tu");
            }
        } while (luaChon.length() != 1);

        return luaChon.charAt(0);
    }

    // Tạm dừng chương trình, chờ enter
    public static void pause() {
        System.out.println();
        System.out.println("----------------------");
        Nhap.nhapStr("Nhan enter de tiep tuc");
    }

    // Kiểm tra định dạng ngày hợp lệ dd/mm/yyyy
    private static boolean ngayHopLe(String ngay) {
        if (ngay == null) {
            return false;
        }
        String[] thanhPhan = ngay.split("/");
        if (thanhPhan.length != 3) {
            return false;
        }
        if (thanhPhan[0].length() > 2 || !laSo(thanhPhan[0]) || thanhPhan[1].length() > 2 || !laSo(thanhPhan[1])
                || thanhPhan[2].length() >= 4 || !laSo(thanhPhan[2])) {
            return false;
        }
        return true;
    }

    // Nhập ngày, chuẩn hóa định dạng dd/mm/yyyy
    public static String nhapNgay(String tuKhoa) {
        String ngay;
        do {
            System.out.print(tuKhoa);
            ngay = sc.nextLine();
            ngay = ngay.trim();
            if (!ngayHopLe(ngay)) {
                System.out.println("vui long nhap theo ngay theo dinh dang dd/mm/yyyy");
            }
        } while (!ngayHopLe(ngay));
        String[] thanhPhan = ngay.split("/");
        while (thanhPhan[0].length() < 2) {
            thanhPhan[0] = "0" + thanhPhan[0];
        }
        while (thanhPhan[1].length() < 2) {
            thanhPhan[1] = "0" + thanhPhan[1];
        }
        while (thanhPhan[2].length() < 4) {
            thanhPhan[2] = "0" + thanhPhan[2];
        }
        return thanhPhan[0] + "/" + thanhPhan[1] + "/" + thanhPhan[2];
    }
}
