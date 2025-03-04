package entities;


public class NhanVienVP implements entities.ChucVu {
    public void phaTra() {
        System.out.println("Pha trà cho sếp.");
    }

    public void pheDuyetTaiLieu() {
        System.out.println("Phê duyệt các tài liệu văn phòng.");
    }

    @Override
    public void thucHienNhiemVu() {
        phaTra();
        pheDuyetTaiLieu();
    }
}


