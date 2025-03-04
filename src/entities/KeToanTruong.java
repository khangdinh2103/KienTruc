package entities;


public class KeToanTruong implements entities.ChucVu {
    public void quanLyTaiChinh() {
        System.out.println("Quản lý tài chính của công ty.");
    }

    public void baoCaoKeToan() {
        System.out.println("Lập báo cáo kế toán hàng tháng.");
    }

    @Override
    public void thucHienNhiemVu() {
        quanLyTaiChinh();
        baoCaoKeToan();
    }
}


