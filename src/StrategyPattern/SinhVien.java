package StrategyPattern;

public class SinhVien implements IObserver {
        private String ten;

        public SinhVien(String ten) {
            this.ten = ten;
        }

        @Override
        public void update(String message) {
            System.out.println(ten + " nhận thông báo: " + message);
        }
}
