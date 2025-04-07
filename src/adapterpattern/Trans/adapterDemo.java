package adapterpattern.Trans;

import java.util.Scanner;

interface Target {
    public void send(String st);
}
class Adapter implements Target {

    JP jp;
    public Adapter() {
        jp = new JP();
    }
    @Override
    public void send(String st) {
        jp.receive(Trans(st));

    }

    public String Trans(String st) {
        if (st.equals("xin chao")) {
            return "Chao cc";
        }
        else if (st.equals("xin chao")) {
            return "Chao cc";
        }
        return "khong the dich";
    }
}

class JP {
    public void receive(String st) {
        System.out.println(st);
    }
}
public class adapterDemo {
    public static void main(String[] args) {
        Target target = new Adapter();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tiếng Việt: ");
        String input = sc.nextLine();  // Đọc dòng nhập từ bàn phím

        target.send(input);  // Gửi nội dung nhập được
        sc.close();
    }
}
