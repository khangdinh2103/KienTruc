package entities;


public class NhanVienXuong implements entities.ChucVu {
    public void sanXuat() {
        System.out.println("Thực hiện sản xuất sản phẩm.");
    }

    public void vanHanhMayMoc() {
        System.out.println("Vận hành các máy móc trong xưởng.");
    }

    @Override
    public void thucHienNhiemVu() {
        sanXuat();
        vanHanhMayMoc();
    }
}


