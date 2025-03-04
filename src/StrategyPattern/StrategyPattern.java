package StrategyPattern;
import entities.*;

public class StrategyPattern {
        public static void main(String[] args) {
            // Tạo danh sách nhân viên với chức vụ cụ thể
            NhanVien[] danhSachNhanVien = {
                    new NhanVien("Đinh Trần Phú Khang", new DoiTruong()),
                    new NhanVien("Đinh Trần Phúc", new GiamDoc()),
                    new NhanVien("Đinh Khánh", new NhanVienVP()),
                    new NhanVien("Phạm Thị Dung", new NhanVienXuong()),
                    new NhanVien("Hoàng Văn E", new KeToanTruong())
            };

            for (NhanVien nv : danhSachNhanVien) {
                nv.thucHienCongViec();
            }


        }

}
