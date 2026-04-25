import java.util.Scanner;

public class Nhap {
    static Scanner sc = new Scanner(System.in);

    static int nhapLuaChonInt(String tuKhoa) {
        System.out.println(tuKhoa);
        int luaChon = sc.nextInt();
        sc.nextLine();
        return luaChon;
    }

    static String nhapLuaChonStr(String tuKhoa) {
        System.out.println(tuKhoa);
        return sc.nextLine();
    }

    static char nhapLuaChonChar(String tuKhoa) {
        System.out.println(tuKhoa);
        return sc.next().charAt(0);
    }
}
