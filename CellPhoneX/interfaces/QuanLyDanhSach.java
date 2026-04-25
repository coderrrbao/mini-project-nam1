package interfaces;

public interface QuanLyDanhSach<T> {
    boolean them(T doiTuong);

    boolean xoa(T doiTuong);

    T tim(String ma);
}
