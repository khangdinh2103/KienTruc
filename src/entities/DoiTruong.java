package entities;

import entities.ChucVu;

public class DoiTruong implements ChucVu {
    public void diTuan() {
        System.out.println("Đi tuần quanh khu vực làm việc.");
    }

    public void giaoViec() {
        System.out.println("Giao việc cho nhân viên trong đội.");
    }

    @Override
    public void thucHienNhiemVu() {
        diTuan();
        giaoViec();
    }
}
