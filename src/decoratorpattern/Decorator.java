package decoratorpattern;


import entities.ChucVu;
import entities.*;


abstract class ChucVuDecorator implements ChucVu {
    protected ChucVu chucVu;

    public ChucVuDecorator(ChucVu chucVu) {
        this.chucVu = chucVu;
    }


    public void thucHienNhiemVu() {
        chucVu.thucHienNhiemVu();
    }
}

class LamThemGio extends ChucVuDecorator {
    public LamThemGio(ChucVu chucVu) {
        super(chucVu);
    }


    @Override
    public void thucHienNhiemVu() {
        super.thucHienNhiemVu();
        System.out.println("Làm thêm ngoài giờ để hoàn thành công việc.");

    }
}

class BaoCaoDacBiet extends ChucVuDecorator {
    public BaoCaoDacBiet(ChucVu chucVu) {
        super(chucVu);
    }

    @Override
    public void thucHienNhiemVu() {
        super.thucHienNhiemVu();
        System.out.println("Chuẩn bị báo cáo đặc biệt cho cấp trên.");
    }
}

public class Decorator {
    public static void main(String[] args) {
        // Đội trưởng bình thường
        System.out.println("🔥 Đội trưởng làm việc bình thường:");
        ChucVu doiTruong = new DoiTruong();
        doiTruong.thucHienNhiemVu();
        System.out.println();

        // Giám đốc có thêm báo cáo đặc biệt
        System.out.println("🔥 Giám đốc làm việc và có thêm báo cáo đặc biệt:");
        ChucVu giamDoc = new BaoCaoDacBiet(new GiamDoc());
        giamDoc.thucHienNhiemVu();
        System.out.println();

        // Nhân viên văn phòng làm thêm giờ
        System.out.println("🔥 Nhân viên văn phòng làm thêm giờ:");
        ChucVu nvVanPhong = new LamThemGio(new NhanVienVP());
        nvVanPhong.thucHienNhiemVu();
        System.out.println();

        // Giám đốc làm thêm giờ và có thêm báo cáo đặc biệt
        System.out.println("🔥 Giám đốc làm thêm giờ và có báo cáo đặc biệt:");
        ChucVu giamDocFull = new LamThemGio(new BaoCaoDacBiet(new GiamDoc()));
        giamDocFull.thucHienNhiemVu();
        System.out.println();
    }
}
