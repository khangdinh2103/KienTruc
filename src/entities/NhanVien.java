package entities;


public class NhanVien {
    private String hoTen;
    private entities.ChucVu chucVu;

    public NhanVien(String hoTen, entities.ChucVu chucVu) {
        this.hoTen = hoTen;
        this.chucVu = chucVu;
    }

    public void doiChucVu(entities.ChucVu chucVuMoi) {
        this.chucVu = chucVuMoi;
    }

    public void thucHienCongViec() {
        System.out.println(hoTen + " - Công việc:");
        chucVu.thucHienNhiemVu();
        System.out.println();
    }
}

