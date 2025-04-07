package decoratorpattern;



//3. Tạo một hệ thống thanh toán, nơi bạn có thể thanh toán bằng các phương thức
//khác nhau như Thẻ tín dụng, PayPal, và có thể thêm các phương thức
//thanh toán này với các tính năng bổ sung như Phí xử lý, Mã giảm giá.
interface Method {
    void method();
}
class MethodSimple implements Method{

    String tenPhuongthuc;

    public MethodSimple(String tenPhuongthuc) {
        this.tenPhuongthuc = tenPhuongthuc;
    }

    public String getTenPhuongthuc() {
        return tenPhuongthuc;
    }

    public void setTenPhuongthuc(String tenPhuongthuc) {
        this.tenPhuongthuc = tenPhuongthuc;
    }

    @Override
    public void method() {
        System.out.println("Method: ");
    }
}
abstract class MethodDecorator implements Method{
    Method m;

    public MethodDecorator(Method m) {
        this.m = m;
    }

    @Override
    public void method() {
        m.method();
    }
}

enum tenmethod {
    Credit, Paypal

}
class  PayPal extends MethodDecorator{

    public PayPal(Method m) {
        super(m);
    }
    public void PhiXuLy() {
        System.out.println("PhiXuLy: 100k");
    }
    @Override
    public void method() {
        m.method();
        System.out.println("PayPal: ");
        PhiXuLy();
    }

}
class  Credit extends MethodDecorator{



    public Credit(Method m) {
        super(m);
    }
    public void MaGiamGia() {
        System.out.println("MaGiamGia: 100k");
    }
    @Override
    public void method() {
        m.method();
        System.out.println("Creidt: ");
        MaGiamGia();
    }

}


public class ongk2 {
    public static void main(String[] args) {
        String str = "Credit";
        if (str.equals("Credit")) {
            Credit cd = new Credit(new MethodSimple("credit"));
            cd.MaGiamGia();
            cd.method();
        } else if (str.equals("Paypal")) {
            PayPal pp = new PayPal(new MethodSimple("payPal"));
            pp.PhiXuLy();
            pp.method();

        }


    }
}
