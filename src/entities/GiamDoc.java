package entities;


public class GiamDoc implements entities.ChucVu {
    public void quanLyCongTy() {
        System.out.println("Quản lý toàn bộ công ty.");
    }

    public void duaRaChienLuoc() {
        System.out.println("Đưa ra chiến lược phát triển.");
    }

    @Override
    public void thucHienNhiemVu() {
        quanLyCongTy();
        duaRaChienLuoc();
    }
}


