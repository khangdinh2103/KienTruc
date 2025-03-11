package observerpattern;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        LopTruong lopTruong = new LopTruong("CNTT-01");

        SinhVien sv1 = new SinhVien("Nguyễn Văn A");
        SinhVien sv2 = new SinhVien("Trần Thị B");
        SinhVien sv3 = new SinhVien("Lê Văn C");

        // Thêm sinh viên vào danh sách nhận thông báo
        lopTruong.attach(sv1);
        lopTruong.attach(sv2);
        lopTruong.attach(sv3);

        // Lớp trưởng gửi thông báo
        lopTruong.sendMessage("Ngày mai nghỉ học!");
    }
}