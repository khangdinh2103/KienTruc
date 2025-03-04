package statepattern;
import entities.*;


public class StatePattern {
    public static void main(String[] args) {

        NhanVien nv1 = new NhanVien("Đinh Trần Phú Khang", new DoiTruong());
        NhanVien nv2 = new NhanVien("Trần Thị Bình", new GiamDoc());
        NhanVien nv3 = new NhanVien("Đinh Trần Phú", new NhanVienVP());
        NhanVien nv4 = new NhanVien("Phạm Thị Dung", new NhanVienXuong());
        NhanVien nv5 = new NhanVien("Đinh Trần Phúc", new KeToanTruong());


        NhanVien[] danhSachNhanVien = {nv1, nv2, nv3, nv4, nv5};
        for (NhanVien nv : danhSachNhanVien) {
            nv.thucHienCongViec();
        }


        System.out.println(" Chuyển Đinh Trần Phú Khang thành Giám đốc...");
        nv1.doiChucVu(new GiamDoc());
        nv1.thucHienCongViec();
    }
}

