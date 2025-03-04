package nopattern;

class NhanVien {
    private String hoTen;
    private String chucVu;

    public NhanVien(String hoTen, String chucVu) {
        this.hoTen = hoTen;
        this.chucVu = chucVu;
    }

    public void thucHienCongViec() {
        System.out.println(hoTen + " - " + chucVu + ":");

        if (chucVu.equalsIgnoreCase("Đội trưởng")) {
            diTuan();
            giaoViec();
        } else if (chucVu.equalsIgnoreCase("Giám đốc")) {
            quanLyCongTy();
            duaRaChienLuoc();
        } else if (chucVu.equalsIgnoreCase("Nhân viên VP")) {
            phaTra();
            pheDuyetTaiLieu();
        } else if (chucVu.equalsIgnoreCase("Nhân viên xưởng")) {
            sanXuat();
            vanHanhMayMoc();
        } else if (chucVu.equalsIgnoreCase("Kế toán trưởng")) {
            quanLyTaiChinh();
            baoCaoKeToan();
        } else {
            System.out.println("Không xác định nhiệm vụ.");
        }

        System.out.println();
    }


    private void diTuan() {
        System.out.println("Đi tuần quanh khu vực làm việc.");
    }

    private void giaoViec() {
        System.out.println("Giao việc cho nhân viên trong đội.");
    }

    private void quanLyCongTy() {
        System.out.println("Quản lý toàn bộ công ty.");
    }

    private void duaRaChienLuoc() {
        System.out.println("Đưa ra chiến lược phát triển.");
    }

    private void phaTra() {
        System.out.println("Pha trà cho sếp.");
    }

    private void pheDuyetTaiLieu() {
        System.out.println("Phê duyệt các tài liệu văn phòng.");
    }

    private void sanXuat() {
        System.out.println("Thực hiện sản xuất sản phẩm.");
    }

    private void vanHanhMayMoc() {
        System.out.println("Vận hành các máy móc trong xưởng.");
    }

    private void quanLyTaiChinh() {
        System.out.println("Quản lý tài chính của công ty.");
    }

    private void baoCaoKeToan() {
        System.out.println("Lập báo cáo kế toán hàng tháng.");
    }
}


public class NoPattern {
    public static void main(String[] args) {
        // Tạo danh sách nhân viên
        NhanVien[] danhSachNhanVien = {
                new NhanVien("Đinh Trần Phú Khang", "Đội trưởng"),
                new NhanVien("Đinh Trần Phúc", "Giám đốc"),
                new NhanVien("Đinh Khánh", "Nhân viên VP"),
                new NhanVien("Phạm Thị Dung", "Nhân viên xưởng"),
                new NhanVien("Hoàng Văn E", "Kế toán trưởng")
        };


        for (NhanVien nv : danhSachNhanVien) {
            nv.thucHienCongViec();
        }
    }
}
